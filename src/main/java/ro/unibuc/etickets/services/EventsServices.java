package ro.unibuc.etickets.services;

import ro.unibuc.etickets.events.Event;
import ro.unibuc.etickets.locations.Location;
import ro.unibuc.etickets.services.csv.EventCSV;
import ro.unibuc.etickets.services.csv.LocationCSV;

import java.util.ArrayList;

public class EventsServices {
    private ArrayList<Event> storage = new ArrayList<Event>();

    private final EventCSV eventCSV = EventCSV.getInstance();

    private final String eventCSVPath = "./csv/events.csv";

    public EventsServices() {
        try {
            storage.addAll(eventCSV.load(eventCSVPath));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addEvent(Event e){
        storage.add(e);
        eventCSV.add(eventCSVPath, e);
    }

    public ArrayList<Event> getEvents(){
        return storage;
    }
}
