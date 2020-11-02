# Spring-Security-Demo

POC for authenticating with Spring Security

To run: `./mvnw spring-boot:run`

## Testing
In Postman, make request to localhost:8080/hello with Authorization type "Basic Auth".

Here are the possible results available:

| Path           | Username | Password | Result |
|----------------|----------|----------|--------|
| Happy          | john     | password | 200    |
| Unallowed user | jane     | password | 403    |
| No user        |          |          | 403    |
