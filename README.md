**Todo API**
A simple REST API built with Spring Boot for managing todo tasks.

**Features**
-Create a new todo
-List all todos
-Get a todo by ID
-Update a todo
-Mark a todo as completed
-Delete a todo
-List only completed todos

**Requirements**
-java 21
-maven

**Running the application**
-mvn spring-boot:run
-The application starts on:
http://localhost:8080/api/v1

-Swagger UI is available at:
http://localhost:8080/api/v1/swagger-ui/index.html

        **API Endpoints**
**Method** **Endpoint**   **Description**
GET      /todos              List all todos
GET      /todos/{id}         Get a todo by ID
POST     /todos              Create a new todo
PUT      /todos/{id}         Update a todo
PUT      /todos/{id}/done    Mark a todo as completed
GET      /todos/completed    List completed todos
DELETE   /todos/{id}         Delete a todo

**Example curl Commands**
**Create a todo**
curl -X POST http://localhost:8080/api/v1/todos \
-H "Content-Type: application/json" \
-d "{\"title\":\"Learn Spring Boot\",\"description\":\"Practice REST APIs\"}"
**Get all todos**
curl http://localhost:8080/api/v1/todos
**Mark a todo as completed**
Replace <id> with the todo's UUID.
curl -X PUT http://localhost:8080/api/v1/todos/<id>/done
