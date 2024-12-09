import React, { useState } from "react";
import axios from "axios";

const SystemConfiguration = () => {
  const [config, setConfig] = useState({
    totalTickets: "",
    ticketReleaseRate: "",
    customerRetrievalRate: "",
    maxTicketCapacity: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setConfig({ ...config, [name]: value });
  };

  const saveConfiguration = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/config", config);
      alert(response.data);
    } catch (error) {
      console.error("Error saving configuration:", error);
      alert("Failed to save configuration.");
    }
  };

  const startSimulation = async () => {
    try {
      const { totalTickets, ticketReleaseRate, customerRetrievalRate } = config;

      if (!totalTickets || !ticketReleaseRate || !customerRetrievalRate) {
        alert("Please fill in all required fields.");
        return;
      }

      const response = await axios.post("http://localhost:8080/api/start", null, {
        params: {
          totalTickets,
          releaseRate: ticketReleaseRate,
          retrievalRate: customerRetrievalRate,
        },
      });

      alert(response.data);
    } catch (error) {
      console.error("Error starting simulation:", error);
      alert("Failed to start simulation.");
    }
  };

  return (
    <div>
      <h2>System Configuration</h2>
      <div>
        <label>Total Tickets:</label>
        <input
          type="number"
          name="totalTickets"
          value={config.totalTickets}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Ticket Release Rate (tickets per second):</label>
        <input
          type="number"
          name="ticketReleaseRate"
          value={config.ticketReleaseRate}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Customer Retrieval Rate (tickets per second):</label>
        <input
          type="number"
          name="customerRetrievalRate"
          value={config.customerRetrievalRate}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Maximum Ticket Capacity:</label>
        <input
          type="number"
          name="maxTicketCapacity"
          value={config.maxTicketCapacity}
          onChange={handleChange}
        />
      </div>
      <button onClick={saveConfiguration}>Save Configuration</button>
      <button onClick={startSimulation}>Start Simulation</button>
    </div>
  );
};

export default SystemConfiguration;
