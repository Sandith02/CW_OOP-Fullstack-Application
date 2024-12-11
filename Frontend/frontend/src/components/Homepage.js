import React from "react";
import { useNavigate } from "react-router-dom";
import "../homepage.css";

const Homepage = () => {
  const navigate = useNavigate();

  const openSimulator = () => {
    navigate("/simulator");
  };

  return (
    <div className="homepage-container">
      {/* Images for random corners */}
      <div className="picture1"></div>
      <div className="picture2"></div>
      <div className="picture3"></div>
      {/* Main content */}
      <div className="titleM">Real-Time Ticket Simulator</div>
      <button className="homepage-button" onClick={openSimulator}>
        Open Simulator
      </button>
    </div>
  );
};

export default Homepage;
