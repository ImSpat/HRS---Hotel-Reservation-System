## Used Technologies
- Spring Framework
- Spring Security
- Spring Data JPA
- Hibernate
- H2 & MariaDB
- Flyway
- Maven
- Thymeleaf
- JUnit
- REST

## About the Project
The project aims to simulate software for managing registrations in a hotel. It consists of two main parts. The first is a GUI intended for hotel staff to manage guest and room operations, including browsing, adding, deleting, and editing. Reservations can be added, viewed, and deleted. The second part is a REST API designed for communication with other services, such as Booking.com.

## User Guide
### GUI
1. Go to [http://bartlomiejszczudlo.alwaysdata.net/](http://bartlomiejszczudlo.alwaysdata.net/). Please be patient; the page may take about 10 seconds to open (due to limitations of free hosting).
2. Log in to the application:
   1. username `kasiakowalska` and password `kasiakowalska` - user with limited permissions, unable to delete or edit rooms.
   2. username `jankowalski` and password `jankowalski` - user with full permissions.
3. Use the top menu to navigate through the application.
4. To create a new reservation, fill out the form and provide an active email address, as the application sends an email with a confirmation link. If the reservation is not confirmed within an hour a [ScheduledTask](https://github.com/ImSpat/HRS---Hotel-Reservation-System/blob/master/src/main/java/com/example/HRS/domain/reservation/scheduledTasks/RemoveUnconfirmedReservations.java) is implemented to check and remove unconfirmed reservations every 15 minutes.
5. Clicking on the confirmation link redirects to a thank-you page. There is no menu there because it is the only part of the application designed for interaction with the hotel client. After confirmation, the client should close the page and not take any further action.
6. The logout button is available on the main page.

### REST API
1. API documentation is available at [https://bartlomiejszczudlo.alwaysdata.net/swagger-ui/index.html](https://bartlomiejszczudlo.alwaysdata.net/swagger-ui/index.html)
2. To log in, send a POST request to https://bartlomiejszczudlo.alwaysdata.net/api/login with JSON format login data, for example: `{"username": "kasiakowalska","password": "kasiakowalska"}`.
3. The response should provide JWT tokens - an access token (valid for 15 minutes) and a refresh token (valid for 120 minutes).
4. Now you can send requests according to the documentation. For example, to get a list of all rooms, send a GET request to https://bartlomiejszczudlo.alwaysdata.net/api/rooms. Choose Bearer Token as the authentication method and provide the received access token.
5. If the access token expires you can send a request to the /api/refreshToken endpoint providing the refresh token to get a new access token.
