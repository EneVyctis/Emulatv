import { useEffect, useState } from "react";
import ServiceBlocks from "./ServiceBlocks";
import "./SmartTVUI.css"

function SmartTVUI() {
  const [services, setServices] = useState([]);

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

export default SmartTVUI;
