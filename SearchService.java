package project_1;

import java.util.List;
import java.util.Scanner;

/**
 * project_1.SearchService — handles the full train search flow.
 * Features:
 *   - Search by source + destination
 *   - Show available stations list
 *   - Filter results by availability
 *   - Sort by fare / departure time
 *   - View detailed info of a specific train
 */
public class SearchService {

    private TrainDatabase db;
    private Scanner scanner;

    public SearchService(TrainDatabase db, Scanner scanner) {
        this.db      = db;
        this.scanner = scanner;
    }

    // ── Entry point ───────────────────────────────────────────────────────────
    public void start() {
        boolean back = false;
        while (!back) {
            System.out.println("\n========== TRAIN SEARCH ==========");
            System.out.println("  1. Search by Source & Destination");
            System.out.println("  2. View All Stations");
            System.out.println("  3. View All Trains");
            System.out.println("  4. Search by project_1.Train Number");
            System.out.println("  0. Back to Main Menu");
            System.out.print("  Enter choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": searchByRoute();       break;
                case "2": showAllStations();     break;
                case "3": showAllTrains();       break;
                case "4": searchByNumber();      break;
                case "0": back = true;           break;
                default:  System.out.println("  [!] Invalid choice.");
            }
        }
    }

    // ── Search by route ───────────────────────────────────────────────────────
    private void searchByRoute() {
        System.out.println("\n--- Search Trains ---");
        System.out.print("  Enter Source Station (e.g. DELHI)     : ");
        String source = scanner.nextLine().trim().toUpperCase();

        System.out.print("  Enter Destination Station (e.g. MUMBAI): ");
        String destination = scanner.nextLine().trim().toUpperCase();

        if (source.isEmpty() || destination.isEmpty()) {
            System.out.println("  [!] Source and destination cannot be empty.");
            return;
        }

        List<Train> results = db.searchTrains(source, destination);

        if (results.isEmpty()) {
            System.out.println("\n  [!] No trains found from " + source + " to " + destination + ".");
            System.out.println("  Tip: Use /2 to see all available station names.");
            return;
        }

        System.out.println("\n  Found " + results.size() + " train(s) from " + source + " to " + destination + ":");
        displayTrainList(results);
        offerSortAndDetail(results);
    }

    // ── Display list ──────────────────────────────────────────────────────────
    private void displayTrainList(List<Train> trains) {
        System.out.println();
        System.out.printf("  %-8s %-26s %-8s %-8s %-6s %s%n",
                "project_1.Train No", "Name", "Dep", "Arr", "Seats", "Fare (Rs.)");
        System.out.println("  " + "-".repeat(72));

        for (int i = 0; i < trains.size(); i++) {
            Train t = trains.get(i);
            String avail = t.getAvailableSeats() > 0
                    ? String.valueOf(t.getAvailableSeats())
                    : "WL";
            System.out.printf("  %-8s %-26s %-8s %-8s %-6s Rs. %.0f%n",
                    t.getTrainNumber(),
                    t.getTrainName(),
                    t.getDepartureTime(),
                    t.getArrivalTime(),
                    avail,
                    t.getFarePerSeat());
        }
        System.out.println("  " + "-".repeat(72));
    }

    // ── Sort + detail options after search ────────────────────────────────────
    private void offerSortAndDetail(List<Train> results) {
        System.out.println("\n  Options:");
        System.out.println("    S1 — Sort by Fare (low to high)");
        System.out.println("    S2 — Sort by Departure Time");
        System.out.println("    D  — View detailed info of a train");
        System.out.println("    Enter — Go back");
        System.out.print("  Choice: ");

        String opt = scanner.nextLine().trim().toUpperCase();

        switch (opt) {
            case "S1":
                results.sort((a, b) -> Double.compare(a.getFarePerSeat(), b.getFarePerSeat()));
                System.out.println("\n  [Sorted by Fare]");
                displayTrainList(results);
                break;

            case "S2":
                results.sort((a, b) -> a.getDepartureTime().compareTo(b.getDepartureTime()));
                System.out.println("\n  [Sorted by Departure Time]");
                displayTrainList(results);
                break;

            case "D":
                System.out.print("  Enter project_1.Train Number for details: ");
                String num = scanner.nextLine().trim();
                Train found = null;
                for (Train t : results) {
                    if (t.getTrainNumber().equals(num)) { found = t; break; }
                }
                if (found != null) found.displayInfo();
                else System.out.println("  [!] project_1.Train not found in results.");
                break;

            default:
                break;
        }
    }

    // ── Search by train number ────────────────────────────────────────────────
    private void searchByNumber() {
        System.out.print("\n  Enter project_1.Train Number: ");
        String num = scanner.nextLine().trim();
        Train t = db.findByNumber(num);
        if (t != null) t.displayInfo();
        else System.out.println("  [!] No train found with number: " + num);
    }

    // ── Show all stations ─────────────────────────────────────────────────────
    private void showAllStations() {
        List<String> stations = db.getAllStations();
        System.out.println("\n  Available Stations (" + stations.size() + "):");
        System.out.print("  ");
        for (int i = 0; i < stations.size(); i++) {
            System.out.printf("%-15s", stations.get(i));
            if ((i + 1) % 5 == 0) System.out.print("\n  ");
        }
        System.out.println();
    }

    // ── Show all trains ───────────────────────────────────────────────────────
    private void showAllTrains() {
        List<Train> all = db.getAllTrains();
        System.out.println("\n  All Trains (" + all.size() + "):");
        displayTrainList(all);
    }
}
