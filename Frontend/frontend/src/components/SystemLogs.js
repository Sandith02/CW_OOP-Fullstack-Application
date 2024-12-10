import React from "react";

const SystemLogs = ({ logs }) => {
  return (
    <div>
      <h2>System Logs</h2>
      <div
        style={{
          border: "1px solid #ccc",
          padding: "10px",
          width: "400px",
          height: "300px",
          overflowY: "scroll",
          backgroundColor: "#f9f9f9",
        }}
      >
        {logs.length > 0 ? (
          logs.map((log, index) => <p key={index}>{log}</p>)
        ) : (
          <p>No logs available.</p>
        )}
      </div>
    </div>
  );
};

export default SystemLogs;
