import React, { useEffect, useState } from "react";
import axios from "axios";

const Logs = () => {
  const [logs, setLogs] = useState([]);
  const [autoRefresh, setAutoRefresh] = useState(false);

  const fetchLogs = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/status");
      setLogs(response.data.logs || []);
    } catch (error) {
      console.error("Error fetching logs:", error);
      alert("Failed to fetch logs.");
    }
  };

  useEffect(() => {
    if (autoRefresh) {
      const interval = setInterval(fetchLogs, 10000); // Refresh every 10 seconds
      return () => clearInterval(interval); // Clear interval on component unmount
    }
  }, [autoRefresh]);

  const toggleAutoRefresh = () => {
    setAutoRefresh(!autoRefresh);
  };

  return (
    <div>
      <h2>Logs</h2>
      <button onClick={fetchLogs}>Refresh Logs</button>
      <button onClick={toggleAutoRefresh}>
        {autoRefresh ? "Disable Auto-Refresh" : "Enable Auto-Refresh"}
      </button>
      <ul>
        {logs.map((log, index) => (
          <li key={index}>{log.logMessage}</li>
        ))}
      </ul>
    </div>
  );
};

export default Logs;
