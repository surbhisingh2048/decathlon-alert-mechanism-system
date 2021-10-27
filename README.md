# decathlon-alert-mechanism-system
Design an alert mechanism system

## Problem statement
Please refer the problem statement named as decathlon_backend_interview.pdf

## Resources used
* Service framework: Spring-boot
* ORM: JPA
* In-memory database: H2
* Testing library: Mockito

## API
### POST
**URL endpoint:** <host_url>:8080/team  
**Request:** 
```json
{
  "developers": [
    {
      "name": "Manoj",
      "phoneNumber": "8339832998"
    },
    {
      "name": "Surbhi",
      "phoneNumber": "8339832997"
    }
  ],
  "teamName": "crackers"
}
```
**Response**:
```json
team created with id 1
```

### POST
**URL endpoint:** <host_url>:8080/<id>/alert
**Request:**
```json
{
  "phone_number":"9123456780"
}
```
**Response**:
```json
notified the below dev

name='Surbhi', phoneNumber='8339832997'
```