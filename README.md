# Airline Management System

A desktop airline reservation and administration application written in **Java (Swing)**, built to showcase core **Object-Oriented Programming** principles: encapsulation, abstraction, inheritance and modular class design.

## Features

**Admin**
- Secure admin login
- Add and manage flights
- Search flights and passengers

**Passenger**
- Passenger registration and login
- Search available flights
- Book tickets (multiple seats per booking, total fare calculated automatically)
- Search and cancel tickets

## Tech Stack

| Area | Technology |
|------|------------|
| Language | Java |
| GUI | Java Swing / AWT |
| Storage | In-memory (`AppData`) |

## Project Structure

```
AirlineManagement/src/airline/
├── Flight.java, Passenger.java, Ticket.java, User.java     # Domain models
├── FlightData.java, PassengerData.java, TicketData.java    # Data / business logic layers
├── AppData.java                                            # Shared application state
├── AdminGUI.java, PassengerLogin_GUI.java                  # Login screens
├── AddFlight_GUI.java, AddPassenger_GUI.java               # Admin/registration forms
├── BookTicket_GUI.java, TicketCancel_GUI.java              # Booking workflow
├── SearchFlight_GUI.java, SearchPassenger_GUI.java,
│   TicketSearch_GUI.java                                   # Search screens
├── AirlineFrame.java, PassengerFrame.java                  # Main windows
└── UITheme.java                                            # Shared colours and styling
```

## OOP Concepts Demonstrated

- **Encapsulation:** private fields with getters in `Ticket`, `Flight`, `Passenger`
- **Abstraction / separation of concerns:** model classes, data classes and GUI classes are kept separate
- **Composition:** a `Ticket` holds a `Passenger` and a `Flight`
- **Reusability:** `UITheme` centralises styling across all screens

## Getting Started

### Prerequisites
- JDK 8 or later

### Run

```bash
git clone https://github.com/saadsddiqi-cell/Airline-Management-System.git
cd Airline-Management-System/AirlineManagement/src
javac airline/*.java
java airline.AirlineFrame
```

> Use whichever class contains `main` as the entry point (`AirlineFrame` is the main window).

### Default admin credentials (development only)
The admin login is currently hard-coded as `admin` / `admin123`. Change this before using the project for anything beyond a demo.

## Limitations and Future Work

- Data is held in memory and lost on exit; add file or database persistence (JDBC / SQLite)
- Move admin credentials out of source code
- Add unit tests
- Add input validation for dates and seat capacity

## Contributors

This project was developed collaboratively by:

- [Muhammad Saad Siddiqui](https://github.com/saadsddiqi-cell)
- [Syed Muhammad Zain Hassan](https://github.com/zainhassan5763-git)
- [MUhammad Arqam](https://github.com/)

Developed as part of academic project work
at Sir Syed University of Engineering & Technology.

