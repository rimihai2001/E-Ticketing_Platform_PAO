package ro.unibuc.etickets.services;

import ro.unibuc.etickets.events.Event;
import ro.unibuc.etickets.locations.Location;

import java.util.ArrayList;

public class EventsServices {
    private ArrayList<Event> storage = new ArrayList<Event>();

    public void addEvent(Event e){
        storage.add(e);
    }

    public ArrayList<Event> getEvents(){
        return storage;
    }
}
