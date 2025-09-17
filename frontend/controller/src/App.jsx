import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

async function callAPI(endpoint) {
  try {
    const response = await fetch(endpoint);
    console.log("OK:", response.status);
  } catch (error) {
    console.error("error", error);
  }
}

function App() {

  return (
    <>
      <div className="card">
        <button onClick={() => callAPI("http://localhost:8080/navigation/previous")}>
          gauche
        </button>
        <button onClick={() => callAPI("http://localhost:8080/navigation/next")}>
          droite
        </button>
      </div>
    </>
  )
}

export default App
