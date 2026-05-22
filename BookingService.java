package project_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * project_1.BookingService — manages all booking operations.
 * Features: Book ticket, Cancel ticket, Check PNR, View all bookings.
 */
public class BookingService {

    private TrainDatabase db;
    private Scanner scanner;
    private List<Booking> bookings = new ArrayList<>();

    public BookingService(TrainDatabase db, Scanner scanner) {
        this.db      = db;
        this.scanner = scanner;
    }

    // ── Entry point ───────────────────────────────────────────────────────────
    public void start() {
        boolean back = false;
        while (!back) {
            System.out.println("\n========== BOOKING MENU ==========");
            System.out.println("  1. Book a Ticket");
            System.out.println("  2. Cancel a Ticket");
            System.out.println("  3. Check PNR Status");
            System.out.println("  4. View My Bookings");
            System.out.println("  0. Back to Main Menu");
            System.out.print("  Enter choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": bookTicket();      break;
                case "2": cancelTicket();    break;
                case "3": checkPNR();        break;
                case "4": viewBookings();    break;
                case "0": back = true;       break;
                default:  System.out.println("  [!] Invalid choice.");
            }
        }
    }

    // ── Book Ticket ───────────────────────────────────────────────────────────
    private void bookTicket() {
        System.out.println("\n--- Book Ticket ---");

        // Step 1: Search route
        System.out.print("  From (e.g. DELHI)  : ");
        String source = scanner.nextLine().trim().toUpperCase();
        System.out.print("  To   (e.g. MUMBAI) : ");
        String dest   = scanner.nextLine().trim().toUpperCase();

        List<Train> found = db.searchTrains(source, dest);
        if (found.isEmpty()) {
            System.out.println("  [!] No trains found for this route.");
            return;
        }

        // Step 2: Show trains
        System.out.println("\n  Available Trains:");
        System.out.printf("  %-8s %-26s %-8s %-6s %s%n",
                "No", "Name", "Dep", "Seats", "Fare");
        System.out.println("  " + "-".repeat(65));
        for (Train t : found) {
            System.out.printf("  %-8s %-26s %-8s %-6d Rs.%.0f%n",
                    t.getTrainNumber(), t.getTrainName(),
                    t.getDepartureTime(), t.getAvailableSeats(), t.getFarePerSeat());
        }

        // Step 3: Select train
        System.out.print("\n  Enter project_1.Train Number to book: ");
        String trainNo = scanner.nextLine().trim();
        Train selected = null;
        for (Train t : found) {
            if (t.getTrainNumber().equals(trainNo)) { selected = t; break; }
        }
        if (selected == null) {
            System.out.println("  [!] Invalid train number.");
            return;
        }

        // Step 4: Number of seats
        System.out.print("  Number of seats (1-6): ");
        int seats;
        try {
            seats = Integer.parseInt(scanner.nextLine().trim());
            if (seats < 1 || seats > 6) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("  [!] Invalid seat count. Enter a number between 1 and 6.");
            return;
        }

        if (!selected.hasAvailableSeats(seats)) {
            System.out.println("  [!] Only " + selected.getAvailableSeats() + " seats available.");
            return;
        }

        // Step 5: Passenger name
        System.out.print("  Passenger Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("  [!] Name cannot be empty.");
            return;
        }

        // Step 6: Confirm
        double total = seats * selected.getFarePerSeat();
        System.out.println("\n  ---- project_1.Booking Summary ----");
        System.out.println("  project_1.Train     : " + selected.getTrainName() + " (" + selected.getTrainNumber() + ")");
        System.out.println("  Route     : " + source + " -> " + dest);
        System.out.println("  Passenger : " + name);
        System.out.println("  Seats     : " + seats);
        System.out.printf ("  Total Fare: Rs. %.2f%n", total);
        System.out.print("  Confirm booking? (Y/N): ");

        String confirm = scanner.nextLine().trim().toUpperCase();
        if (!confirm.equals("Y")) {
            System.out.println("  project_1.Booking cancelled.");
            return;
        }

        // Step 7: Create booking
        selected.bookSeats(seats);
        Booking booking = new Booking(selected, name, seats);
        bookings.add(booking);

        System.out.println("\n  project_1.Booking Confirmed!");
        booking.printTicket();
    }

    // ── Cancel Ticket ─────────────────────────────────────────────────────────
    private void cancelTicket() {
        System.out.println("\n--- Cancel Ticket ---");
        System.out.print("  Enter PNR Number: ");
        String pnr = scanner.nextLine().trim().toUpperCase();

        Booking b = findByPNR(pnr);
        if (b == null) {
            System.out.println("  [!] No booking found with PNR: " + pnr);
            return;
        }
        if (!b.isActive()) {
            System.out.println("  [!] This ticket is already cancelled.");
            return;
        }

        b.printTicket();
        System.out.print("\n  Are you sure you want to cancel? (Y/N): ");
        String confirm = scanner.nextLine().trim().toUpperCase();
        if (confirm.equals("Y")) {
            b.getTrain().cancelSeats(b.getSeats());
            b.cancel();
            System.out.println("  Ticket with PNR " + pnr + " has been CANCELLED.");
            System.out.printf ("  Refund of Rs. %.2f will be processed.%n", b.getTotalFare());
        } else {
            System.out.println("  Cancellation aborted.");
        }
    }

    // ── Check PNR ─────────────────────────────────────────────────────────────
    private void checkPNR() {
        System.out.println("\n--- PNR Status ---");
        System.out.print("  Enter PNR Number: ");
        String pnr = scanner.nextLine().trim().toUpperCase();

        Booking b = findByPNR(pnr);
        if (b == null) {
            System.out.println("  [!] No booking found with PNR: " + pnr);
        } else {
            b.printTicket();
        }
    }

    // ── View all bookings ─────────────────────────────────────────────────────
    private void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("\n  No bookings yet.");
            return;
        }
        System.out.println("\n  Your Bookings (" + bookings.size() + "):");
        System.out.printf("  %-10s %-20s %-20s %-6s %-10s %s%n",
                "PNR", "Passenger", "project_1.Train", "Seats", "Fare", "Status");
        System.out.println("  " + "-".repeat(78));
        for (Booking b : bookings) {
            System.out.printf("  %-10s %-20s %-20s %-6d Rs.%-7.0f %s%n",
                    b.getPnr(), b.getPassengerName(),
                    b.getTrain().getTrainName(), b.getSeats(),
                    b.getTotalFare(), b.getStatus());
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private Booking findByPNR(String pnr) {
        for (Booking b : bookings) {
            if (b.getPnr().equalsIgnoreCase(pnr)) return b;
        }
        return null;
    }

    public int getTotalBookings() { return bookings.size(); }
}

