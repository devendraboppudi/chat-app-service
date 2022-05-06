# Spring Boot Private Chat Project

1-1 instant messaging project designed to demonstrate WebSockets. Users can register, login/logout, see a list of users, private message, and send/receive notifications. WebSocket usages include user presence monitoring, notifications, and chat messages.

## Notable Technologies/Design Decisions
- Backend: Java 11 with Spring Boot
- Frontend: Reactjs
- Database: H2 (Embedded)
- ORM: Spring Data Jpa
- WebSocket messaging protocol: Stomp
- WebSocket handler: Sock.js (with cross-browser fallbacks)
- Security: Spring Security
- Spring Controllers couple REST as well as WebSocket traffic
- Fractal Design

## Setup
1. Install system dependencies: latest versions (at the time of this writing) of Java, Node, NPM
2. Update `src/main/resources/application.properties` with your H2 database location
3. `cd` to root of the project and execute `$ mvn spring-boot:run` or  (`$ mvn spring-boot:run -Drun.jvmArguments='-Dserver.port={{your port here}}'` if you wish to run a few servers)
4. Visit [http://localhost:8081/](http://localhost:8081)


## Notes
- Chat messages are persisted to the database, notifications are not.
- Friendlist feature currently is just every user in the database other than the current user (simple feature for demo, not meant to be realistic)


## METRICS
- Visit [http://localhost:8081/actuator/info](http://localhost:8081/actuator/info) to check the version of the application
- Visit [http://localhost:8081/actuator/health](http://localhost:8081/actuator/health) to check the health of the application
- We can add more metrics as we needed

## Todos
- Add the Unit Tests and Integration Tests
- Add Code coverage tool along with the build tool to give metrics of code coverage

