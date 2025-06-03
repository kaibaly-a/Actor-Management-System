🎭Actor Management System

This is a Spring Boot application for managing actors and their associated data. It allows you to create, read, update, and delete (CRUD) actor records, and can be extended to include additional functionality like filmography, awards, and more.

📌 Features
Add new actors

View list of all actors

Update actor details

Delete actors

Integration with a relational database Oracle

Optional: Web interface using JSP

🛠️ Tech Stack
Java 17+

Spring Boot

Spring Data JPA

Spring Web

Oracle

Lombok

Maven


📂 Project Structure
src/
 └── main/
     ├── java/
     │   └── com.example.actormanagement
     │       ├── controller/
     │       ├── model/
     │       ├── repository/
     │       ├── service/
     │       └── ActorManagementApplication.java
     └── resources/
         ├── application.properties
         └── static/templates (if using a UI)
