package project_1;

import  java.util.ArrayList;
import java.util.List;

/**
 * project_1.TrainDatabase — stores all available trains.
 * Acts as an in-memory "database" of trains across India.
 * Uses ArrayList; search is case-insensitive.
 */
public class TrainDatabase {

    private List<Train> trains = new ArrayList<>();

    public TrainDatabase() {
        loadTrains();
    }

    // ── Load sample trains ────────────────────────────────────────────────────
    private void loadTrains() {

        // Delhi routes
        trains.add(new Train("12301", "Rajdhani Express",      "DELHI",   "MUMBAI",    "16:00", "08:15+1", 500, 1450.00));
        trains.add(new Train("12302", "Rajdhani Express",      "MUMBAI",  "DELHI",     "17:00", "09:30+1", 500, 1450.00));
        trains.add(new Train("12001", "Shatabdi Express",      "DELHI",   "BHOPAL",    "06:00", "13:45",   450,  750.00));
        trains.add(new Train("12002", "Shatabdi Express",      "BHOPAL",  "DELHI",     "14:30", "22:15",   450,  750.00));
        trains.add(new Train("12311", "Kalka Mail",            "DELHI",   "KOLKATA",   "07:45", "09:40+1", 600,  980.00));
        trains.add(new Train("12312", "Kalka Mail",            "KOLKATA", "DELHI",     "13:00", "15:05+1", 600,  980.00));
        trains.add(new Train("12565", "Bihar Sampark Kranti",  "DELHI",   "PATNA",     "15:30", "05:00+1", 550,  620.00));
        trains.add(new Train("12566", "Bihar Sampark Kranti",  "PATNA",   "DELHI",     "17:00", "06:30+1", 550,  620.00));

        // Mumbai routes
        trains.add(new Train("12951", "Mumbai Rajdhani",       "MUMBAI",  "DELHI",     "17:00", "09:55+1", 480, 1350.00));
        trains.add(new Train("22221", "Duronto Express",       "MUMBAI",  "PUNE",      "07:10", "09:25",   400,  250.00));
        trains.add(new Train("22222", "Duronto Express",       "PUNE",    "MUMBAI",    "18:00", "20:15",   400,  250.00));
        trains.add(new Train("11001", "Udyan Express",         "MUMBAI",  "BANGALORE", "08:05", "06:10+1", 600,  850.00));
        trains.add(new Train("11002", "Udyan Express",         "BANGALORE","MUMBAI",   "20:15", "18:30+1", 600,  850.00));

        // South India routes
        trains.add(new Train("12657", "Chennai Mail",          "BANGALORE","CHENNAI",  "23:00", "05:45+1", 550,  420.00));
        trains.add(new Train("12658", "Chennai Mail",          "CHENNAI", "BANGALORE", "21:30", "04:15+1", 550,  420.00));
        trains.add(new Train("12621", "Tamil Nadu Express",    "DELHI",   "CHENNAI",   "22:30", "07:40+2", 480, 1650.00));
        trains.add(new Train("12622", "Tamil Nadu Express",    "CHENNAI", "DELHI",     "10:00", "19:10+1", 480, 1650.00));
        trains.add(new Train("16526", "Kanyakumari Express",   "BANGALORE","TRIVANDRUM","12:30","06:30+1", 520,  760.00));

        // East India routes
        trains.add(new Train("12303", "Poorva Express",        "DELHI",   "KOLKATA",   "08:00", "05:55+1", 580,  920.00));
        trains.add(new Train("12304", "Poorva Express",        "KOLKATA", "DELHI",     "22:55", "20:40+1", 580,  920.00));
        trains.add(new Train("12505", "North East Express",    "DELHI",   "GUWAHATI",  "18:00", "04:00+2", 520, 1100.00));
        trains.add(new Train("12506", "North East Express",    "GUWAHATI","DELHI",     "06:00", "16:05+1", 520, 1100.00));

        // Rajasthan / Gujarat routes
        trains.add(new Train("12958", "Ahmedabad Rajdhani",    "DELHI",   "AHMEDABAD", "19:55", "09:40+1", 460, 1200.00));
        trains.add(new Train("12957", "Ahmedabad Rajdhani",    "AHMEDABAD","DELHI",    "23:00", "11:30+1", 460, 1200.00));
        trains.add(new Train("12015", "Ajmer Shatabdi",        "DELHI",   "AJMER",     "06:05", "12:30",   400,  680.00));
        trains.add(new Train("12016", "Ajmer Shatabdi",        "AJMER",   "DELHI",     "13:45", "20:10",   400,  680.00));

        // Hyderabad routes
        trains.add(new Train("12723", "Telangana Express",     "HYDERABAD","DELHI",    "06:25", "10:25+1", 560, 1050.00));
        trains.add(new Train("12724", "Telangana Express",     "DELHI",   "HYDERABAD", "22:30", "02:30+2", 560, 1050.00));
        trains.add(new Train("12765", "Amaravathi Express",    "HYDERABAD","CHENNAI",  "17:00", "07:15+1", 480,  580.00));

        System.out.println("[DB] " + trains.size() + " trains loaded successfully.");
    }

    // ── Search ────────────────────────────────────────────────────────────────

    /**
     * Search trains by source and destination (case-insensitive).
     */
    public List<Train> searchTrains(String source, String destination) {
        List<Train> result = new ArrayList<>();
        String src  = source.trim().toUpperCase();
        String dest = destination.trim().toUpperCase();

        for (Train t : trains) {
            if (t.getSource().equals(src) && t.getDestination().equals(dest)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Find a specific train by train number.
     */
    public Train findByNumber(String trainNumber) {
        for (Train t : trains) {
            if (t.getTrainNumber().equals(trainNumber.trim())) {
                return t;
            }
        }
        return null;
    }

    /**
     * Get all unique source stations.
     */
    public List<String> getAllStations() {
        List<String> stations = new ArrayList<>();
        for (Train t : trains) {
            if (!stations.contains(t.getSource()))      stations.add(t.getSource());
            if (!stations.contains(t.getDestination())) stations.add(t.getDestination());
        }
        java.util.Collections.sort(stations);
        return stations;
    }

    public List<Train> getAllTrains() {
        return trains;
    }
}
