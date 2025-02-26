# Monolith vs Microservices Architecture

## Monolithic Architecture
A monolithic architecture combines all functionalities into a single application.

### Advantages
- Simplified development process
- Straightforward deployment
- Better performance characteristics
- Streamlined maintenance
- Comprehensive testing capabilities
- Efficient debugging
- Easier knowledge transfer

### Disadvantages
- Creates a single point of failure
- Requires full application redeployment for any change
- Limited scalability options
- Reduced reliability (component failure affects entire system)
- Decreased availability (component downtime impacts whole application)

## Microservices Architecture
Microservices architecture splits functionalities into multiple independent services/APIs, each serving a specific purpose.

### Advantages
- Loose coupling between services
- Accelerated development cycles
- Rapid release capabilities
- Flexible scalability
- Eliminated single point of failure
- Freedom to choose different technologies

### Challenges
- Complex service boundary definitions
- Extensive configuration requirements
- Service visibility and monitoring
- Complex testing scenarios
- Distributed debugging complexity

## Key Components

1. **Service Registry**
    - Manages registration of backend services
    - Tracks service names, URLs, and health status
    - Typically implemented using Eureka Server

2. **Admin Server**
    - Provides centralized monitoring and management
    - Offers UI for service oversight
    - Centralizes access to Actuator endpoints

3. **Zipkin Server**
    - Enables distributed log tracing
    - Visualizes application execution flows
    - Monitors service performance and latency

4. **Backend Services (REST APIs)**
    - Implements core business logic
    - Functions as independent microservices
    - Integrates with service registry, admin server, and Zipkin
    - Enables inter-service communication

5. **API Gateway**
    - Serves as the single entry point
    - Mediates between clients and backend services
    - Implements authentication filters
    - Handles request routing

6. **Feign Client**
    - Facilitates inter-service communication
    - Enables integration with third-party APIs
    - Alternative to RestTemplate or WebClient

Note: Microservices architecture is flexible and can be customized based on specific project requirements.
