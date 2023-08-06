# Online-Booking-Scheduler

To run the project, clone the project and import it in some IDE(let's say IntelliJ), set up JDK version 18 or above, and run the project. All API endpoints, requests, etc have been attached. For the database, in-memory database H2 is used. (For local use: http://localhost:8080/h2-console/)

## Book Appointment Functionality: 
Based on start_time, end_time, operator_id, and status(1:Active, 5:inactive) first appointment will be checked, if any appointment is found, then "Already appointment in the requested time slot" will be returned else, appointment will be booked successfully.

Endpoint: localhost:8080/appointment/bookAppointment

Method: POST
{
    "operator_id": 1,
    "start_time": "2023-08-06 11:00:00",
    "end_time": "2023-08-06 12:00:00"
}

Response:
{
    "status": 200,
    "message": "Appointment booked successfully"
}

## Reschedule Appointment Functionality:
Based on appointment_id, first appointments will be fetched. All the existing appointment will be cancelled, based on start_time, end_time if slot is available then appointment will be booked, else entire transaction will be rolled back with a message "Unable to reschedule, please try with a different time slot".

Endpoint: localhost:8080/appointment/rescheduleAppointment

Method: POST

Request:
{
    "appointment_id": 1,
    "start_time": "2023-08-06 19:00:00",
    "end_time": "2023-08-06 20:00:00"
}

Response:
{
    "status": 400,
    "message": "Unable to reschedule, please try with a different time slot"
}

## Cancel Appointment Functionality:
Based on appointment_id, appointment will be deleted(In our case, appointment status will be set to 5 denoting inactive record)

Endpoint: localhost:8080/appointment/cancelAppointment?appointment_id=1

Method: PUT

Response:
{
    "status": 200,
    "message": "Appointment cancelled successfully"
}

## Get All Appointments for a given operator
Based on operator_id, booked time slots will be returned.

Endpoint: localhost:8080/operator/getAllAppointments?operator_id=1

Method: GET

Response:
[
    "15-16",
    "16-17",
    "17-18"
]

## Get merged slots for a particular operator for a particular date

Endpoint: localhost:8080/operator/getOpenSlots?operator_id=1&date=06/08/2023

Method: GET

Response:
[
    "0-17",
    "19-24"
]
