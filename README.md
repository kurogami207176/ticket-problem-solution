# Ticketing System
## Overview
This is the solution for the ticketing system.
## Build & Test
Run
```shell
./mvnw clean install
```
## Run
Run
```shell
./mvnw spring-boot:run
```
## Entry Point
API definition is located at `src/main/resources/openapi.yml`
## Configuration
Current configuration is done via `application.yml`. This includes the following:
- Pricing
- Discount
- Customer Categorisation
## Domains
### Ordering API
This is the API layer. This is the domain responsible for taking the orders and delegating the request to the ticketing service.

### Ticketing
This is the domain responsible for categorising the customers into a ticket type and offering discounts. Orchestrating is done here.

### Pricing
This is the domain responsible for providing the pricing for a ticket. This will have a prices data source.

### Common
This is the common domain. This includes the common objects across the different domains. 

### Transaction
This manages the transactionId for the response.

## Persist Layer
This project has no persist layer and any data source are currently stored as properties. This can potentially be changed to a different source as complexity grows.

## Unit Test
Unit test only covers service classes.

## Black Box Test
There are 2 black box tests classes. 
- The `BlackBoxTests.java` is the original test case. This was hand-coded from the example in the problem set.
  - Pros: Easier to setup by code. Extensible.
  - Cons: Hand-crafted. Prone to mistake
- The `FromFileBlackBoxTests.java` is the same test cases but the input was copied directly from the problem set into the case json.
  - Pros: Copied from problem set. Smaller probability of mistake in setting up.
  - Cons: Not really extensible. Hard to understand.

Normally, I'd just pick a test and stick with it. Alternatively, we could also use BDD test.

