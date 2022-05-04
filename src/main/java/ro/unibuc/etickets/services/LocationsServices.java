package ro.unibuc.etickets.services;

import ro.unibuc.etickets.locations.Location;

import java.util.ArrayList;

public class LocationsServices {
    private ArrayList<Location> storage = new ArrayList<Location>();

    public void addLocation(String country, String city, String address, String ZIP){
        storage.add(new Location(country, city, address, ZIP));
    }

    public ArrayList<Location> getLocations(){
        return storage;
    }

}
