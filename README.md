<h2>Reactive Blog</h2>

A very simple REST service using Project Reactor.

In this example I will use a functional approach using
Routers and Handlers instead of Controllers.
The app is structured inspiring by Clean Architecture and Hexagonal Architecture.
For Integration Testing I used Testcontainers with MongoDB.

<h3>Requirements</h3>
    
  - Java 17
  - Docker

<h3>How to run it?</h3>

Compile everything:

    ./mvnw clean package

Create the database and run the application:

    docker compose up   