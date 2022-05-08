package ro.unibuc.etickets.services.csv;

import ro.unibuc.etickets.client.Client;
import ro.unibuc.etickets.events.Concert;
import ro.unibuc.etickets.events.CulturalEvent;
import ro.unibuc.etickets.events.Event;
import ro.unibuc.etickets.events.SportEvent;
import ro.unibuc.etickets.locations.Location;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class EventCSV implements GenericCSV<Event>{
    private static final EventCSV INSTANCE = new EventCSV();

    private final String auditPath = "./csv/audit.csv";

    private EventCSV() {
    }

    @Override
    public ArrayList<Event> load(String fileName) throws FileNotFoundException {
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
        ArrayList<Event> events = new ArrayList<Event>();
        try {
            String line = csvReader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                Event event;
                if(data.length==9){
                    event = new Concert(data[0], parseInt(data[1]), parseDouble(data[2]), new Location(data[3], data[4], data[5], data[6], data[7]), data[8]);
                    events.add(event);
                } else if (data.length==10) {
                    event = new CulturalEvent(data[0], parseInt(data[1]), parseDouble(data[2]), new Location(data[3], data[4], data[5], data[6], data[7]), data[8], parseInt(data[9]));
                    events.add(event);
                } else if (data.length==11) {
                    event = new SportEvent(data[0], parseInt(data[1]), parseDouble(data[2]), new Location(data[3], data[4], data[5], data[6], data[7]), data[8], data[9], data[10]);
                    events.add(event);
                }
                line = csvReader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        audit(auditPath, "load_events");
        return events;
    }

    @Override
    public void add(String fileName, Event content) {
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
        audit(auditPath, "add_event");
    }

    public static EventCSV getInstance() {
        return INSTANCE;
    }
}
