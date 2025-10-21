import express, { json } from "express";
import { keyboard, Key, sleep } from "@nut-tree-fork/nut-js";

const app = express();
const PORT = 3000;

// Middleware JSON
app.use(json());

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

app.listen(PORT, () => {
    console.log(`Emulatv Agent (nut.js) ready on port ${PORT}`);
});
