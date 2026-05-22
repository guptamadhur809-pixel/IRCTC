package project_1;

/**
 * project_1.Train — represents a single train with its route, timing, and seat availability.
 */
public class Train {

    private String trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private double farePerSeat;

    public Train(String trainNumber, String trainName, String source, String destination,
                 String departureTime, String arrivalTime, int totalSeats, double farePerSeat) {
        this.trainNumber    = trainNumber;
        this.trainName      = trainName;
        this.source         = source;
        this.destination    = destination;
        this.departureTime  = departureTime;
        this.arrivalTime    = arrivalTime;
        this.totalSeats     = totalSeats;
        this.availableSeats = totalSeats;
        this.farePerSeat    = farePerSeat;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public String getTrainNumber()   { return trainNumber; }
    public String getTrainName()     { return trainName; }
    public String getSource()        { return source.toUpperCase(); }
    public String getDestination()   { return destination.toUpperCase(); }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime()   { return arrivalTime; }
    public int    getTotalSeats()    { return totalSeats; }
    public int    getAvailableSeats(){ return availableSeats; }
    public double getFarePerSeat()   { return farePerSeat; }

    // ── Seat management ──────────────────────────────────────────────────────
    public boolean hasAvailableSeats(int count) {
        return availableSeats >= count;
    }

    public boolean bookSeats(int count) {
        if (!hasAvailableSeats(count)) return false;
        availableSeats -= count;
        return true;
    }

    public void cancelSeats(int count) {
        availableSeats = Math.min(totalSeats, availableSeats + count);
    }

    // ── Display ──────────────────────────────────────────────────────────────
    public void displayInfo() {
        System.out.println("+---------------------------------------------------------+");
        System.out.printf("  project_1.Train No   : %-10s  Name : %s%n", trainNumber, trainName);
        System.out.printf("  From       : %-15s  To   : %s%n", source, destination);
        System.out.printf("  Departure  : %-15s  Arr  : %s%n", departureTime, arrivalTime);
        System.out.printf("  Seats Avail: %-10d  Fare : Rs. %.2f per seat%n", availableSeats, farePerSeat);
        System.out.println("+---------------------------------------------------------+");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s -> %s | Dep: %s | Avail: %d | Rs.%.0f",
                trainNumber, trainName, source, destination,
                departureTime, availableSeats, farePerSeat);
    }
}
