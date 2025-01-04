# Football Manager Web Application

This project is a REST API football management system developed using Java and Spring Boot. It allows users to manage football teams and players.

## Features

- **Team Management**: Create, retrieve, update, and delete football teams.
- **Player Management**: Add players to teams, retrieve player info, update player information, and remove players.
- **Player transfers between teams**: Move one player from seller team to buyer team.

## Technologies Used

- **Backend**: Java, Spring Boot, Hibernate, JDBC Template, Slf4j (for logging)
- **Database**: H2 Database in-memory
- **Build Tool**: Maven

## Server port and H2-console
   **Server port**: By default, the application runs on port `8090`. You can change the port by modifying the `application.properties` file:
   ```properties
   server.port=8090
   ```
   **H2-console**: 
   ```url
   http://localhost:8090/h2-console
   ```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17
- Maven 3.9.9

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Essur/FootballManagerWebApp.git
2. **Navigate to project dir**:
   ```bash
   cd FootballManagerWebApp
3. **Build and start project in one command**:
   ```bash
   ./mvnw clean package spring-boot:run

## Postman collection

**There are two options to get collection**
1. Import from the file Football-Manager-Web-App.postman_collection.json
2. Go to public collection on [Link](https://www.postman.com/essur/workspace/public-workspace/collection/24976968-615ce228-c69c-4a28-b24f-7ec0999b8dda?action=share&creator=24976968)
