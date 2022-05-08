package ro.unibuc.etickets.services.csv;

import ro.unibuc.etickets.client.Card;
import ro.unibuc.etickets.client.Client;
import ro.unibuc.etickets.locations.Location;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class LocationCSV implements GenericCSV<Location>{
    private static final LocationCSV INSTANCE = new LocationCSV();

    private final String auditPath = "./csv/audit.csv";

    private LocationCSV() {
    }

    @Override
    public ArrayList<Location> load(String fileName) throws FileNotFoundException {
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
        ArrayList<Location> locations = new ArrayList<Location>();
        try {
            String line = csvReader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                Location location = new Location(data[0], data[1], data[2], data[3], data[4]);
                locations.add(location);
                line = csvReader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        audit(auditPath, "load_locations");
        return locations;
    }

    @Override
    public void add(String fileName, Location content) {
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(fileName, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            csvWriter.append(content.toString());
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        audit(auditPath, "add_location");
    }

    public static LocationCSV getInstance() {
        return INSTANCE;
    }
}
