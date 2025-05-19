# CW_OOP-Fullstack-Application

# Real-Time Ticket Simulator

A real-time ticket simulator application that allows users to configure ticketing simulations, track ticket release and retrieval rates, and view real-time logs. The project includes a React-based frontend and a Spring Boot-based backend with MySQL integration.

---

## Features

### Frontend Features
1. **Welcome Page**
    - A visually appealing introduction with the title "Real-Time Ticket Simulator".
    - Includes a button to open the simulator configuration page.

2. **Simulator Configuration**
    - Allows users to configure simulation parameters:
        - Total Tickets
        - Ticket Release Rate (tickets per second)
        - Customer Retrieval Rate (tickets per second)
        - Maximum Ticket Capacity
    - Buttons to save configurations, start the simulation, and stop the simulation.

3. **System Logs**
    - Displays real-time logs of ticket releases and retrievals.
    - Logs are timestamped for tracking the sequence of events.

### Backend Features
1. **Configuration Management**
    - APIs to save and retrieve system configurations.

2. **Simulation Management**
    - Starts and stops ticket release and retrieval simulations.
    - Handles real-time ticket processing.

3. **Logging**
    - Logs important system events, such as ticket releases, retrievals, and configurations.
    - Provides formatted logs to the frontend.

4. **Database Interaction**
    - Stores tickets, configurations, and logs in a MySQL database using JPA.

---

## Technologies Used

### Frontend
- **React.js**: For creating the user interface.
- **CSS**: For styling.

### Backend
- **Spring Boot**: For creating RESTful APIs.
- **Spring Web**: For web application development.
- **Spring JPA**: For database interactions.
- **MySQL Driver**: For connecting to the MySQL database.
- **Dev Tools**: For rapid backend development.

### Tools
- **Postman**: For testing APIs.
- **MySQL Workbench**: For database management.

---

## Setup and Installation

### Prerequisites
1. Node.js and npm installed.
2. Java JDK 17+ installed.
3. MySQL installed and running.

### Steps

#### Backend
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo-url.git
   ```
2. Navigate to the backend directory:
   ```bash
   cd Backend
   ```
3. Install dependencies and run the backend:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Configure the MySQL database in the `application.properties` file.

#### Frontend
1. Navigate to the frontend directory:
   ```bash
   cd Frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the React app:
   ```bash
   npm start
   ```

---

## API Endpoints

### Configuration
- **POST** `/api/config`
    - Saves the configuration.
    - Request Body:
      ```json
      {
        "totalTickets": 100,
        "ticketReleaseRate": 10,
        "customerRetrievalRate": 5,
        "maxTicketCapacity": 200
      }
      ```

### Simulation
- **POST** `/api/simulation/start`
    - Starts the simulation.
    - Parameters:
        - `totalTickets`
        - `ticketReleaseRate`
        - `retrievalRate`

- **POST** `/api/simulation/stop`
    - Stops the simulation.

- **GET** `/api/simulation/logs`
    - Retrieves formatted logs.

### Logs
- **GET** `/api/logs`
    - Fetches all formatted logs.

---

## Future Enhancements
1. Add WebSocket support for real-time log updates.
2. Implement more advanced ticket simulation scenarios.
3. Deploy the application on cloud platforms like AWS or Heroku.

---

## License
This project is licensed under the MIT License.

---

## Contact
For inquiries, reach out to [your-email@example.com].
