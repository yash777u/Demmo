## Backend Setup and Configuration

This section guides you through setting up and running the backend of the project, which uses Spring Boot, Spring Security, and Redis for session management.

### Prerequisites

Before you start, ensure you have the following installed:

- **Java 17** or higher
- **Maven** (for dependency management and building the project)
- **Redis** (for session storage)
- **MySQL** or **PostgreSQL** (for database storage)

### Steps to Configure and Run the Backend

1. **Clone the Repository**

   ```bash
   git clone <your-repository-url>
   cd <your-repository-directory>
   ```

2. **Set Up Redis**

   Make sure Redis is installed and running. You can install Redis using the following command:

   ```bash
   # On Ubuntu
   sudo apt update
   sudo apt install redis-server

   # On macOS (using Homebrew)
   brew install redis

   # Start Redis server
   redis-server
   ```

   By default, Redis runs on port `6379`.

3. **Configure MySQL/PostgreSQL**

   Ensure you have MySQL/PostgreSQL installed and running. Create a database for the project:

   ```sql
   CREATE DATABASE your_database_name;
   ```

4. **Set Up Environment Variables**

   Create a `.env` file in the root directory of the project and add the following configuration details:

   ```plaintext
   SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/your_database_name
   SPRING_DATASOURCE_USERNAME=root
   SPRING_DATASOURCE_PASSWORD=root
   SPRING_JPA_HIBERNATE_DDL_AUTO=update

   # Redis Configuration
   SPRING_REDIS_HOST=localhost
   SPRING_REDIS_PORT=6379

   # Other configurations if needed
   ```

5. **Build the Project**

   Use Maven to build the project:

   ```bash
   mvn clean install
   ```

6. **Run the Application**

   Start the Spring Boot application using Maven:

   ```bash
   mvn spring-boot:run
   ```

   The backend server will start on `http://localhost:8080`.

### Endpoints

- **Login Endpoint**

  ```plaintext
  POST /api/login
  ```

  Request Body:

  ```json
  {
      "username": "your_username",
      "password": "your_password"
  }
  ```

  Response:

  ```json
  {
      "message": "Login successful! Token: <token>"
  }
  ```

- **User Details Endpoint**

  ```plaintext
  GET /api/user-details
  ```

  Response:

  ```json
  {
      "username": "your_username",
      // other user details as needed
  }
  ```

### Testing

After setting up and running the application, you can test the endpoints using a tool like Postman or cURL.

1. **Login Request:**

   ```bash
   curl -X POST http://localhost:8080/api/login \
        -H "Content-Type: application/json" \
        -d '{"username": "your_username", "password": "your_password"}'
   ```

   Ensure you receive a successful login response with a token.

2. **Get User Details Request:**

   Ensure you are logged in and have a valid session (cookie) in your browser or use the token received from the login request.

   ```bash
   curl -X GET http://localhost:8080/api/user-details \
        -H "Content-Type: application/json"
   ```

3. **Check Session at Redis **
   ``` redis-cli ```
   ``` Keys * ```
   ``` FLUSHALL ``` Remove all Session

   Ensure you receive the user details as JSON response.

### Troubleshooting

- **Redis Connection Issues:**
  - Ensure Redis is running on the specified host and port.
  - Check firewall settings if Redis is running on a remote server.

- **Database Connection Issues:**
  - Ensure MySQL/PostgreSQL is running and accessible.
  - Verify the database URL, username, and password in the `.env` file.

- **Authentication Issues:**
  - Ensure the login credentials are correct.
  - Check the Spring Security configuration if you encounter unauthorized errors.

---

This documentation should help users set up, configure, and test the backend part of your project effectively.
