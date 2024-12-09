// src/components/Controls.js
import React from "react";
import axios from "axios";

const Controls = () => {
  const startSimulation = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/start");
      alert(response.data);
    } catch (error) {
      console.error("Error starting simulation:", error);
      alert("Failed to start simulation.");
    }
  };

  const stopSimulation = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/stop");
      alert(response.data);
    } catch (error) {
      console.error("Error stopping simulation:", error);
      alert("Failed to stop simulation.");
    }
  };

  return (
    <div>
      <button onClick={startSimulation} style={{ backgroundColor: "green", color: "white" }}>
        Start
      </button>
      <button onClick={stopSimulation} style={{ backgroundColor: "red", color: "white" }}>
        Stop
      </button>
    </div>
  );
};

export default Controls;
