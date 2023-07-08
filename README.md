# TDD-MiniProject
This is a Spring Boot project that implements a simple Order Management System while following the principles of Test Driven Development (TDD). The Order Management System is designed as a web application that allows users to handle the basic functionalities related to order handling.

Run the application:
   - Run the main class 
The application should run locally. You can access the API endpoints using tools like cURL, Postman, or any other HTTP client.

Endpoints

The application provides the following API endpoints for managing orders:
- GET `/orders`: Retrieve a list of all orders.
- GET `/orders/{id}`: Retrieve an order by its ID.
- POST `/orders`: Create a new order.
- PUT `/orders/{id}`: Update an existing order.
- DELETE `/orders/{id}`: Delete an order.

The API endpoints accept and return JSON data in the request/response bodies. Detailed information about the request/response structures can be found in the API documentation.

Testing
This project follows the principles of Test Driven Development (TDD), which means that the tests are an integral part of the development process. The project includes a set of tests covering different scenarios to ensure the accuracy and reliability of the Order Management System.

To run the tests for this project:
- Navigate to the test package
- Run the test classes

