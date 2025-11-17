import { useState } from "react";
import AddServiceForm from "./AddServiceForm";
import DeleteServiceForm from "./DeleteServiceForm";

export default function AdminPanel() {
  const [activeForm, setActiveForm] = useState("add");


  return (
    <div style={{display: "flex", height:"100vh" }}>
      <div style={{
        width: "200px",
        backgroundColor: "#f0f0f0",
        padding: "1rem",
        borderRigth: "1px solid #ddd",
      }}>
        <h3>Menu</h3>
        <ul style={{ listStyle: "none", padding: 0}}>
            <li style={{ 
              marginBottom: "0.5rem",
              cursor: "pointer",
              color: activeForm === "add"? "blue" : "black",
            }}
            onClick={() => setActiveForm("add")}
            >
            Add service  
            </li>
            <li style={{ 
              marginBottom: "0.5rem",
              cursor: "pointer",
              color: activeForm === "delete"? "blue" : "black",
            }}
            onClick={() => setActiveForm("delete")}
            >
            Delete service
            </li>
        </ul>
      </div>
            <div style={{flex: 1, padding: "2rem" }}>
            <h1>Admin Panel</h1>
            {activeForm === "add" && <AddServiceForm />}
            {activeForm === "delete" && <DeleteServiceForm />}
            </div>
    </div>
  );
}
