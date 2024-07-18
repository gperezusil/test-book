# Book House Microservice

## Descripción
Este microservicio se encarga de gestionar la reserva de casas. Proporciona un endpoint POST para realizar reservas de casas.

## Características
- Proporciona un endpoint para realizar reservas de casas.
- Utiliza Spring Boot y Spring Web.

## Requisitos
- Java 11 o superior.
- Maven 3.6.3 o superior.
- PostgreSQL (para la configuración de la base de datos).

## Probar Endpoint 
curl -X POST "http://localhost:8080/bideafactory/book" \
-H "Content-Type: application/json" \
-d '{
  "id": "14564088-4",
  "name": "Gonzalo",
  "lastname": "Pérez",
  "age": 33,
  "phoneNumber": "56988123222",
  "startDate": "2024-08-04",
  "endDate": "2024-12-04",
  "houseId": "213132",
  "discountCode": "D0542A23"
}'
