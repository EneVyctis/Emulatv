import { useEffect, useState } from 'react'
import './App.css'
import ServiceBlocks, {Service} from "./components/ServiceBlocks"
import "./components/ServiceBlocks.css"

function App() {
  const [services, setServices] = useState([]);
  const ip = import.meta.env.VITE_IP;
  const port = import.meta.env.VITE_PORT;

  useEffect(() => {
    const endpoint = "/api/list/services";

    const fetchData = async () => {
      try {
        const response = await fetch(endpoint);
        const json = await response.json();
        setServices(json);
      } catch (error) {
        console.log("error", error);
      }
    };

    fetchData();
  }, []);
  
  return (
      <div className="service-grid">
      {services.map((service) => (
        <ServiceBlocks
          key={service.id}
          name={service.name}
          website={service.website}
        />
      ))}
    </div>
  );
}

export default App
