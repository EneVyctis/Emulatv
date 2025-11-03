import express, { json } from "express";
import { keyboard, Key, sleep } from "@nut-tree-fork/nut-js";
import multer from "multer";
import path from "path";

const app = express();
const PORT = process.env.NODE_API_PORT || 3001;

// Middleware JSON
app.use(json());

// File where icons are stored
const ICONS_DIR = path.join(process.cwd(),"..","frontend","emulatv-ui", "public", "icons");

// Multer config
const storage = multer.diskStorage({
    destination: (req, file, cb) => {
        cb(null, ICONS_DIR);
    },
    filename: (req, file, cb) => {
        cb(null, file.originalname);
    }
});

const upload = multer({
    storage: storage,
    fileFilter: (req, file, cb) => {
        if (file.mimetype === "image/svg+xml") cb(null, true);
        else cb(new Error("Seuls les fichiers SVG sont acceptés"));
    }
});

// Helper function to simulate a keyTap
async function keyTap(key) {
    await keyboard.pressKey(key);
    await keyboard.releaseKey(key);
    await sleep(10);
}

// Maps
app.get("/navigation/enter", async (req, res) => {
    await keyTap(Key.Enter);
    res.sendStatus(200);
});

app.get("/navigation/escape", async (req, res) => {
    await keyTap(Key.Escape);
    res.sendStatus(200);
});

app.get("/navigation/next", async (req, res) => {
    await keyTap(Key.Tab);
    res.sendStatus(200);
});

app.get("/navigation/previous", async (req, res) => {
    await keyboard.pressKey(Key.LeftShift);
    await keyTap(Key.Tab);
    await keyboard.releaseKey(Key.LeftShift);
    res.sendStatus(200);
});

app.get("/navigation/volume/up", async (req, res) => {
    await keyTap(Key.Up);
    res.sendStatus(200);
});

app.get("/navigation/volume/down", async (req, res) => {
    await keyTap(Key.Down);
    res.sendStatus(200);
});

// --- Upload ---

app.post("/upload-service", upload.single("icon"), (req, res) => {
    if(!req.file) {
        return res.status(400).send("No files received");
    }

    console.log(`Received file : ${req.file.filename}`);
    res.status(200).send("SVG file store with success ! ");
});

app.listen(PORT, () => {
    console.log(`Emulatv Agent (nut.js) ready on port ${PORT}`);
});
