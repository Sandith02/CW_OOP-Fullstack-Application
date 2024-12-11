import './Styles.css';
import React, { useState, useEffect } from "react";
import SystemConfiguration from "./components/SystemConfiguration";
import SystemLogs from "./components/SystemLogs";
import Controls from "./components/Controls";
import axios from "axios";

const App = () => {
  const [logs, setLogs] = useState([]); // Centralized state for logs
  const [simulationRunning, setSimulationRunning] = useState(false); // Track simulation state

  // Fetch logs periodically if the simulation is running
  useEffect(() => {
    let interval;
    if (simulationRunning) {
      interval = setInterval(fetchLogs, 1000); // Fetch logs every second
    }
    return () => clearInterval(interval); // Clear interval on unmount or when simulation stops
  }, [simulationRunning]);

  // Function to fetch logs from the backend
  const fetchLogs = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/simulation/logs");
      setLogs(response.data);
    } catch (error) {
      console.error("Error fetching logs:", error);
    }
  };

  // Function to clear logs when a new simulation starts
  const clearLogs = () => {
    setLogs([]); // Clear logs state
  };

  return (
    <div>
      <div className='Mtitle'>Real-Time Event Ticketing Simulator</div>
      <SystemConfiguration setSimulationRunning={setSimulationRunning} clearLogs={clearLogs} />
      <Controls setSimulationRunning={setSimulationRunning} />
      <SystemLogs logs={logs} />
    </div>
  );
};

export default App;
