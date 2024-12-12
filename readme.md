# Ticketing System

## Introduction
This Ticketing System is a real-time event ticket management application that demonstrates the use of Object-Oriented Programming (OOP) principles and the Producer-Consumer pattern. The system simulates a dynamic environment where tickets are concurrently released by vendors and purchased by customers, ensuring data integrity and thread safety.

The application consists of two main components:

- **Backend**: Built with Java using Spring Boot, handling business logic, data management, and API endpoints.
- **Frontend**: A simple web interface built with React, providing a user-friendly configuration and control panel.

## Setup Instructions

### Prerequisites
To run the application, ensure the following software is installed:

- **Java Development Kit (JDK)**: Version 11 or higher.
- **Node.js**: Version 16 or higher (for frontend development).
- **Maven**: For building and running the Java backend.
- **npm**: For managing frontend dependencies.

### Backend Setup
1. Navigate to the backend directory:
   ```bash
   cd backend
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```



   The frontend will be available at [http://localhost:54321](http://localhost:54321).

## Usage Instructions

### Configuration and Starting the System

1. **Access the Web Interface**:
   - Open a web browser and navigate to [http://localhost:54321](http://localhost:54321).

2. **Configure the System**:
   - Click on the **Configuration** tab.
   - Enter the desired values for:
     - **Max Ticket Capacity**: The maximum number of tickets that can be held in the pool.
     - **Total Tickets**: The initial number of tickets in the pool.
     - **Ticket Release Rate**: The rate (in milliseconds) at which vendors release tickets.
     - **Ticket Retrieval Rate**: The rate (in milliseconds) at which customers purchase tickets.
   - Ensure that **Total Tickets** is less than or equal to **Max Ticket Capacity**.
   - Click **Set Configuration** to apply the settings.
   - Optionally, click **Load Configuration** to load a previously saved configuration from a file.

3. **Start or Stop the System**:
   - Navigate to the **Control Panel** tab.
   - Click **Start System** to initiate the ticketing process.
   - Click **Stop System** to halt the ticketing process.

### Explanation of UI Controls

- **Configuration Tab**:
  - **Input Fields**: Enter numerical values for system parameters.
  - **Set Configuration**: Applies the entered configuration to the system.
  - **Load Configuration**: Loads a configuration from a persistent storage file.
  - **Messages/Errors**: Displays feedback messages or validation errors.

- **Control Panel Tab**:
  - **Start System**: Begins the ticket release and purchase processes.
  - **Stop System**: Stops the ongoing processes.
  - **Messages**: Displays confirmation messages for start/stop actions.

## Notes

- **Thread Safety**: The backend ensures thread safety in ticket pool operations using synchronization.
- **Persistence**: Configuration can be saved to and loaded from a JSON file.
- **Logging**: The application logs important events and errors for debugging and monitoring purposes.

Enjoy managing your event tickets with this robust system!

