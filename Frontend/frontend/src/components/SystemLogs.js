import React from "react";

const SystemLogs = ({ logs }) => {
  return (
    <div>
      <h2>System Logs</h2>
      <div className="system-logs-container">
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
