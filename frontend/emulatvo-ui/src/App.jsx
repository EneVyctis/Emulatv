import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import './components/ServiceBlocks'
import ServiceBlocks from './components/ServiceBlocks'

function App() {
  const [services, setServices] = useState([]);

  useEffect(() => {
    const url = "http://localhost:8080/list/services";

    const fetchData = async () => {
      try {
        const response = await fetch(url);
        const json = await response.json();
        setServices(json);
      } catch (error) {
        console.log("error", error);
      }
    };

    fetchData();
  }, []);
  
  return (
    <div>
      {services.map( (service) => (
        <ServiceBlocks key={service.id} id={service.id} name={service.name} website={service.website} />
      ))}
    </div>
  );
}

export default App
