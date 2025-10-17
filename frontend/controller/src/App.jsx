import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import RemoteButton from './components/RemoteButton'
import remoteStyles from './Remote.module.css'

function App() {
  const ip = import.meta.env.VITE_IP;
  const port = import.meta.env.VITe_PORT;
  return (
    <>
    <div className={remoteStyles.grid}>
      <RemoteButton className={remoteStyles.okButton} label="Ok" endpoint={`http://${ip}:${port}/navigation/enter`} />
      <RemoteButton className={remoteStyles.leftButton} label="Prev" endpoint={`http://${ip}:${port}/navigation/previous`} />
      <RemoteButton className={remoteStyles.rightButton} label="Next" endpoint={`http://${ip}:${port}/navigation/next`} />
      <RemoteButton className={remoteStyles.topButton} label="Up" endpoint={`http://${ip}:${port}/navigation/volume/up`} />
      <RemoteButton className={remoteStyles.bottomButton} label="Down" endpoint={`http://${ip}:${port}/navigation/volume/down`} />
      <RemoteButton className={remoteStyles.escapeButton} label="escape" endpoint={`http://${ip}:${port}/navigation/escape`} />
    </div>
    </>
  )
}

export default App
