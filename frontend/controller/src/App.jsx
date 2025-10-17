import { useEffect, useState } from 'react'
import './App.css'
import RemoteButton from './components/RemoteButton'

function App() {
  const ip = import.meta.env.VITE_IP;
  const port = import.meta.env.VITE_PORT;
  return (
    <div className="min-h-screen w-full flex flex-col justify-center items-center bg-gray-500 p-12">
      
      {/* Main section : navigation + OK */}
      <div className="grid grid-cols-3 grid-rows-3 gap-x-8 gap-y-6 items-center justify-items-center mb-12">
        <div></div>
        <RemoteButton
          className="bg-gray-700 hover:bg-gray-600 text-black w-20 h-20 flex items-center justify-center rounded-xl shadow-md active:scale-95 transition text-2xl"
          label="Vol +"
          endpoint={`http://${ip}:${port}/navigation/volume/up`}
        />
        <div></div>

        <RemoteButton
          className="bg-gray-700 hover:bg-gray-600 text-black w-20 h-20 flex items-center justify-center rounded-xl shadow-md active:scale-95 transition text-2xl"
          label="◀"
          endpoint={`http://${ip}:${port}/navigation/previous`}
        />
        <RemoteButton
          className="bg-blue-600 hover:bg-blue-500 text-black w-24 h-24 flex items-center justify-center rounded-full shadow-lg text-xl font-bold active:scale-95 transition"
          label="OK"
          endpoint={`http://${ip}:${port}/navigation/enter`}
        />
        <RemoteButton
          className="bg-gray-700 hover:bg-gray-600 text-black w-20 h-20 flex items-center justify-center rounded-xl shadow-md active:scale-95 transition text-2xl"
          label="▶"
          endpoint={`http://${ip}:${port}/navigation/next`}
        />

        <div></div>
        <RemoteButton
          className="bg-gray-700 hover:bg-gray-600 text-black w-20 h-20 flex items-center justify-center rounded-xl shadow-md active:scale-95 transition text-2xl"
          label="Vol -"
          endpoint={`http://${ip}:${port}/navigation/volume/down`}
        />
        <div></div>
      </div>

      {/* echap button */}
      <RemoteButton
        className="bg-red-700 hover:bg-red-600 text-black px-8 py-3 rounded-lg shadow-md active:scale-95 transition text-lg"
        label="⎋ Escape"
        endpoint={`http://${ip}:${port}/navigation/escape`}
      />
    </div>
  );
}

export default App
