// src/App.js
import React from "react";
import SystemConfiguration from "./components/SystemConfiguration";
import Logs from "./components/Logs";
import Controls from "./components/Controls";

const App = () => {
  return (
    <div>
      <h1>Real-Time Event Ticketing System</h1>
      <SystemConfiguration />
      <Logs />
      <Controls />
    </div>
  );
};

export default App;
