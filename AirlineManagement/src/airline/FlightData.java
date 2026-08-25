package airline;

import java.util.ArrayList;

public class FlightData {
    private ArrayList<Flight> flights = new ArrayList<>();
    private int counter = 1;

    public String generateFlightId() {
        return "FL" + String.format("%03d", counter++);
    }

    public void addFlight(Flight f) { flights.add(f); }

    public void showAllFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights found.");
            return;
        }
        System.out.println("\n===== ALL FLIGHTS =====");
        for (Flight f : flights) {
            System.out.println(f);
        }
    }

    public Flight searchById(String flightId) {
        for (Flight f : flights) {
            if (f.getFlightId().equalsIgnoreCase(flightId)) return f;
        }
        return null;
    }

    public ArrayList<Flight> getAllFlights() { return flights; }
}
