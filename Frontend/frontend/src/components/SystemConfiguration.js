import React, { useState } from "react";
import axios from "axios";

const SystemConfiguration = ({ setSimulationRunning, clearLogs }) => {
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
      await axios.post("http://localhost:8080/api/config", config);
      alert("Configuration saved successfully!");
    } catch (error) {
      console.error("Error saving configuration:", error);
      alert("Failed to save configuration.");
    }
  };

  const startSimulation = async () => {
    try {
      clearLogs(); // Clear logs before starting a new simulation
      await axios.post(
        `http://localhost:8080/api/simulation/start`,
        null, // No body is sent, so this is null
        {
          params: {
            totalTickets: config.totalTickets,
            ticketReleaseRate: config.ticketReleaseRate,
            retrievalRate: config.customerRetrievalRate,
          },
        }
      );
      alert("Simulation started successfully!");
      setSimulationRunning(true); // Notify App.js that simulation is running
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
