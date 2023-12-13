# Vet Clinic Management System API

## Description
- This is a simple vet clinic management system that allows the user to create, read, update, and delete data.
- The data is stored in a PostgreSQL database. The API is built using Java Spring Boot and tested using Postman.
- The API is hosted on the local machine and can be accessed using the following URL: http://localhost:8080/api/v1/

## CRUD Constraints
- There can't be two animals with the same name and owner. (Unique constraint)
- Customers and doctors are unique based on their phone numbers and emails together. (Unique constraint) 
- A single doctor is on-call each day.
- A new vaccine can't be created for an animal if that animal has an active protection with that vaccine.
- Appointments are hourly. Appointment start times are always at the beginning of the hour.
- Cascade delete is enabled for all entities where the remaining data would be irrelevant after deletion. (If a customer is deleted, all of their animals and appointments are deleted as well.)

## Endpoints
- Endpoints and their functionalities are listed below:![endpoints.png](src%2Fendpoints.png)