// Simulator.js
import React, { useState, useEffect } from "react";
import axios from "axios";

// System Configuration Component
const SystemConfiguration = ({ setSimulationRunning, clearLogs }) => {
  const [config, setConfig] = useState({
    totalTickets: "",
    ticketReleaseRate: "",
    customerRetrievalRate: "",
    maxTicketCapacity: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (value <= 0) {
      alert(`Total tickets value must be greater than zero.`);
      return;
    }
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
      clearLogs();
      await axios.post(
        `http://localhost:8080/api/simulation/start`,
        null,
        {
          params: {
            totalTickets: config.totalTickets,
            ticketReleaseRate: config.ticketReleaseRate,
            retrievalRate: config.customerRetrievalRate,
          },
        }
      );
      alert("Simulation started successfully!");
      setSimulationRunning(true);
    } catch (error) {
      console.error("Error starting simulation:", error);
      alert("Failed to start simulation.");
    }
  };

  const stopSimulation = async () => {
    try {
      await axios.post("http://localhost:8080/api/simulation/stop");
      alert("Simulation stopped successfully!");
      setSimulationRunning(false);
    } catch (error) {
      console.error("Error stopping simulation:", error);
      alert("Failed to stop simulation.");
    }
  };

  return (
    <div className="form-container">
      <div className="formTitle">Simulator Configuration</div>
      <div className="form-group">
        <label>Total Tickets:</label>
        <input
          type="number"
          name="totalTickets"
          value={config.totalTickets}
          onChange={handleChange}
        />
      </div>
      <div className="form-group">
        <label>Ticket Release Rate (Tickets per second):</label>
        <input
          type="number"
          name="ticketReleaseRate"
          value={config.ticketReleaseRate}
          onChange={handleChange}
        />
      </div>
      <div className="form-group">
        <label>Customer Retrieval Rate (Tickets per second):</label>
        <input
          type="number"
          name="customerRetrievalRate"
          value={config.customerRetrievalRate}
          onChange={handleChange}
        />
      </div>
      <div className="form-group">
        <label>Maximum Ticket Capacity:</label>
        <input
          type="number"
          name="maxTicketCapacity"
          value={config.maxTicketCapacity}
          onChange={handleChange}
        />
      </div>
      <div className="form-submit-btn-row">
        <button className="form-submit-btn full-width" onClick={saveConfiguration}>
          Save Configuration
        </button>
      </div>
      <div className="form-submit-btn-set">
        <button className="form-submit-btn half-width" onClick={startSimulation}>
          Start Simulation
        </button>
        <button className="form-submit-btn stop" onClick={stopSimulation}>
          Stop Simulation
        </button>
      </div>
    </div>
  );
};

// System Logs Component
const SystemLogs = ({ logs }) => {
  return (
    <div>
      <div className="logtitle">System Logs</div>
      <div className="system-logs-container">
        {logs.length > 0 ? (
          logs.map((log, index) => <p key={index}>{log}</p>)
        ) : (
          <p>Click the "Start Simulation" for system log integration </p>)
        }
      </div>
    </div>
  );
};

// Consolidated Export
const Simulator = ({ logs, setLogs, simulationRunning, setSimulationRunning }) => {
  const clearLogs = () => setLogs([]);

  return (
    <div>
      <SystemConfiguration
        setSimulationRunning={setSimulationRunning}
        clearLogs={clearLogs}
      />
      <SystemLogs logs={logs} />
    </div>
  );
};

export default Simulator;
