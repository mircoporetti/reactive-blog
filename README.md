<h2>Reactive Blog</h2>

A very simple REST service using Project Reactor.

In this example I will use a functional approach using
Routers and Handlers instead of Controllers.

<h3>Requirements</h3>
    
  - Docker
  - Java 17
  - Maven 3.x

<h3>How to run it?</h3>

Create a Mongo Database:

    docker compose up

Compile everything:

    mvn clean package

Run the application:

    java -jar web-application/target/reactive-blog.jar    