# back-end-spring-microservice-archetype
Spring Microservice Archetype

You can deploy the project on Railway using the following button:

[![Deploy on Railway](https://railway.app/button.svg)](https://railway.app/template/JvYvDw?referralCode=jesus-unir)


If you want to deploy this project within an entire Spring microservices ecosystem, you can use the following button:

[![Deploy on Railway](https://railway.app/button.svg)](https://railway.app/template/f6CKpT?referralCode=jesus-unir)

### API Definition

| Método HTTP | URI                    | Query Params                                                   | Cuerpo de la Petición                                                                                                                            | Cuerpo de la Respuesta                                                                                                                                             | Códigos de Respuesta                                                   |
|-------------|------------------------|----------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| POST        | /bookings              | -                                                              | {"children": "3", "startDate": "2024-06-15", "endDate": "2024-06-18", "adults": "2", "hotelId": "45", "rooms": "1", "contact": "test@gmail.com"} | {"bookingId": 1, "children": "3", "startDate": "2024-06-15", "endDate": "2024-06-18", "adults": "2", "hotelId": "45", "rooms": "1", "contact": "test@gmail.com"}   | 201 Created  <br/> 400 Bad Request  <br/>500 Internal Server Error     |
| GET         | /bookings/{boookingId} | -                                                              | N/A                                                                                                                                              | {"bookingId": 1, "children": "3", "startDate": "2024-06-15", "endDate": "2024-06-18", "adults": "2", "hotelId": "45", "rooms": "1", "contact": "test@gmail.com"}   | 200 OK  <br/>404 Not Found  <br/>500 Internal Server Error             |
| PUT         | /bookings/{boookingId} | -                                                              | {"children": "3", "startDate": "2024-06-15", "endDate": "2024-06-18", "adults": "2", "hotelId": "45", "rooms": "3", "contact": "test@gmail.com"} | {"bookingId": 1, "children": "3", "startDate": "2024-06-15", "endDate": "2024-06-18", "adults": "2", "hotelId": "45", "rooms": "3", "contact": "test@gmail.com"}   | 200 OK  <br/> 400 Bad Request  <br/>404 Not Found  <br/>500 Internal Server Error    |
| DELETE      | /bookings/{boookingId} | -                                                              | N/A                                                                                                                                              | N/A                                                                                                                                                                | 200 OK  <br/>404 Not Found  <br/>500 Internal Server Error             |
| GET         | /bookings              | children, rooms, adults, contact, hotelId, startDate, endDate | N/A                                                                                                                                              | [{"bookingId": 1, "children": "3", "startDate": "2024-06-15", "endDate": "2024-06-18", "adults": "2", "hotelId": "45", "rooms": "1", "contact": "test@gmail.com"}] | 200 OK  <br/>500 Internal Server Error                                 |
| PATCH       | /bookings/{bookingId}  | -                                                              | {"rooms": "5"}                                                                                                                                   | {"bookingId": 1, "children": "3", "startDate": "2024-06-15", "endDate": "2024-06-18", "adults": "2", "hotelId": "45", "rooms": "5", "contact": "test@gmail.com"}   | 200 OK  <br/> 400 Bad Request  <br/>404 Not Found  <br/>500 Internal Server Error                                                                    |

