import React from "react";
import axios from "axios";

const Controls = ({ setSimulationRunning }) => {
  const stopSimulation = async () => {
    try {
      await axios.post("http://localhost:8080/api/simulation/stop");
      alert("Simulation stopped successfully!");
      setSimulationRunning(false); // Notify App.js that simulation stopped
    } catch (error) {
      console.error("Error stopping simulation:", error);
      alert("Failed to stop simulation.");
    }
  };

  return (
    <div>
      <button
        onClick={stopSimulation}
        style={{ backgroundColor: "red", color: "white" }}
      >
        Stop Simulation
      </button>
    </div>
  );
};

export default Controls;
