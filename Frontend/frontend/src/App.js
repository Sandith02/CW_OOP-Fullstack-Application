// App.js
import './Styles.css';
import React, { useState, useEffect } from "react";
import axios from "axios";
import Homepage from "./components/Homepage";
import Simulator from "./components/Simulator"; // Update path if needed
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

const App = () => {
  const [logs, setLogs] = useState([]); // Centralized state for logs
  const [simulationRunning, setSimulationRunning] = useState(false); // Track simulation state

  useEffect(() => {
    let interval;
    if (simulationRunning) {
      interval = setInterval(fetchLogs, 1000); // Fetch logs every second
    }
    return () => clearInterval(interval); // Clear interval on unmount or when simulation stops
  }, [simulationRunning]);

  const fetchLogs = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/simulation/logs");
      setLogs(response.data);
    } catch (error) {
      console.error("Error fetching logs:", error);
    }
  };

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Homepage />} />
        <Route
          path="/simulator"
          element={
            <Simulator
              logs={logs}
              setLogs={setLogs}
              simulationRunning={simulationRunning}
              setSimulationRunning={setSimulationRunning}
            />
          }
        />
      </Routes>
    </Router>
  );
};

export default App;
