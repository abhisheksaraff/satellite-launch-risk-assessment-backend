# Satellite Launch Risk Assessment Backend

This project is a backend system designed to assess the risk factors associated with satellite launches and operations in orbit. The system calculates various parameters such as collision risks, drag forces, and satellite behavior under different environmental conditions. It uses a RESTful API built with **Java** and **Spring Boot** to simulate the risks based on key input variables such as satellite size, orbit altitude, drag coefficient, and solar radiation.

## Technologies Used
- **Java**: Programming language used to develop the core logic.
- **Spring Boot**: Framework used to build the RESTful API and handle backend operations.
- **Monte Carlo Simulations**: Used for collision risk assessment, generating random values to simulate collision scenarios.
- **Postman**: Tool used for testing RESTful API endpoints.

## Features
- **Collision Risk Calculation**: Simulates the likelihood of satellite collisions based on debris density, satellite size, and orbit altitude.
- **Drag Force Calculation**: Computes the drag force acting on the satellite considering atmospheric density, orbital velocity, drag coefficient, and solar radiation.
- **Simulations**: Run Monte Carlo simulations to estimate the number of detected collisions over a specified number of simulations.
- **RESTful API**: Exposes endpoints to calculate and return the collision risk, drag force, and other relevant metrics.
- **Postman Testing**: API endpoints have been tested using Postman to ensure functionality.

## API Endpoints

### 1. `POST /api/launches`
**Description**: Simulates risk for a given satellite based on input parameters.

**Request Body**:
json
{
    "satellite_name": "Mission-X1",
    "orbit_altitude_km": 600.0,
    "satellite_size_m2": 15.5,
    "maneuver_compatibility_percent": 0.0025,
    "drag_coefficient": 80.0,
    "number_of_simulations": 10000
}

### 2. `GET /api/launches`
**Description**: Fetches all satellite launch simulations.

### 3. `GET /api/launches/{id}`
**Description**: Fetches a specific satellite launch simulation by ID.

### 4. `PUT /api/launches/{id}`
**Description**: Updates the satellite launch simulation with the given ID.

**Request Body**:
json
{
    "satellite_name": "Mission-X1",
    "orbit_altitude_km": 600.0,
    "satellite_size_m2": 15.5,
    "maneuver_compatibility_percent": 0.005,
    "drag_coefficient": 85.0,
    "number_of_simulations": 12000
}

### 5. `DELETE /api/launches/{id}`
**Description**: Deletes the satellite launch simulation with the given ID.


## Setup Instructions
1. Clone the repository:
    ```bash
    git clone https://github.com/abhisheksaraff/satellite-launch-risk-assessment-backend.git
    ```

2. Navigate into the project directory:
    ```bash
    cd satellite-launch-risk-assessment-backend
    ```

3. Install the necessary dependencies:
    ```bash
    ./mvnw clean install
    ```

4. Start the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```

5. The application will run on `http://localhost:8080`. You can now use Postman to test the available API endpoints.

## Testing with Postman
1. **POST /api/launches**:
    - Open Postman and create a POST request to `http://localhost:8080/api/launches`.
    - Add the sample JSON body and click **Send** to test the simulation.

2. **GET /api/launches**:
    - Create a GET request to `http://localhost:8080/api/launches` to fetch all satellite launch simulations.

3. **GET /api/launches/{id}**:
    - Replace `{id}` with a valid launch ID and make a GET request to fetch a specific simulation.

4. **PUT /api/launches/{id}**:
    - Replace `{id}` with a valid launch ID and create a PUT request to update the simulation.

5. **DELETE /api/launches/{id}**:
    - Replace `{id}` with a valid launch ID and create a DELETE request to remove the simulation.

## Contribution
1. Fork this repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -am 'Add your feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a new Pull Request.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to contribute and improve the project!