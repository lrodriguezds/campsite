# Volcano island campsite API
Backend tech challenge for Upgrade.com

## Requirements
- Gradle
- PostgresDB

## Assumptions
- Just 1 instance will be launched.


## API Endpoints

All endpoints are below `/api`.

- `GET /reservations` - Checks campsite availability
- `POST /reservations` - Creates new reservation
- `GET /reservations/{id}` - Retrieves reservation details
- `PUT /reservations/{id}` - Update a reservation
- `DELETE /reservations/{id}` - Cancel a reservation