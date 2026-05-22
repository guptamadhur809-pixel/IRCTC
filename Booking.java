package project_1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static project_1.IRCTCMain.CYAN;

/**
 * project_1.Booking — represents a confirmed train ticket booking.
 * PNR is auto-generated (first 8 chars of UUID, uppercase).
 */
public class Booking {

    private String pnr;
    private Train  train;
    private String passengerName;
    private int    seats;
    private double totalFare;
    private String bookingTime;
    private String status; // CONFIRMED / CANCELLED

    public Booking(Train train, String passengerName, int seats) {
        this.pnr           = generatePNR();
        this.train         = train;
        this.passengerName = passengerName;
        this.seats         = seats;
        this.totalFare     = seats * train.getFarePerSeat();
        this.bookingTime   = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
        this.status        = "CONFIRMED";
    }

    private String generatePNR() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public String getPnr()           { return pnr; }
    public Train getTrain()         { return train; }
    public String getPassengerName() { return passengerName; }
    public int    getSeats()         { return seats; }
    public double getTotalFare()     { return totalFare; }
    public String getStatus()        { return status; }
    public String getBookingTime()   { return bookingTime; }

    public void cancel()             { this.status = "CANCELLED"; }
    public boolean isActive()        { return status.equals("CONFIRMED"); }

    // ── Display ticket (Updated Aesthetic Version) ──────────────────────────────
    public void printTicket() {

        String C = CYAN;
        String R = IRCTCMain.R;
        String G = IRCTCMain.GREEN;
        String B = IRCTCMain.B;
        String Y = IRCTCMain.GOLD;
        String P = IRCTCMain.PURPLE;

        System.out.println("\n" + P + "  ╔══════════════════════════════════════════════════════╗" + R);
        System.out.println(P + "  ║" + B + G + "              OFFICIAL RAILWAY E-TICKET               " + R + P + "║" + R);
        System.out.println(P + "  ╠══════════════════════════════════════════════════════╣" + R);
        System.out.printf (P + "  ║ " + R + "PNR: " + B + Y + "%-12s" + R + "     Status: " + (status.equals("CONFIRMED") ? G : IRCTCMain.RED) + B + "%-14s" + R + P + "║%n", pnr, status);
        System.out.println(P + "  ╟──────────────────────────────────────────────────────╢" + R);
        System.out.printf (P + "  ║ " + R + "Passenger : " + B + "%-41s" + R + P + "║%n", passengerName.toUpperCase());
        System.out.printf (P + "  ║ " + R + "Train     : %-41s" + P + "║%n", train.getTrainName() + " (" + train.getTrainNumber() + ")");
        System.out.printf (P + "  ║ " + R + "From/To   : " + CYAN + "%-41s" + R + P + "║%n", train.getSource() + " ➔ " + train.getDestination());
        System.out.printf (P + "  ║ " + R + "Departure : %-41s" + P + "║%n", train.getDepartureTime());
        System.out.println(P + "  ╟──────────────────────────────────────────────────────╢" + R);
        System.out.printf (P + "  ║ " + R + "PAYMENT   : " + G + B + "Rs. %-36.2f" + R + P + "║%n", totalFare);
        System.out.println(P + "  ╚══════════════════════════════════════════════════════╝" + R);
    }
    }
