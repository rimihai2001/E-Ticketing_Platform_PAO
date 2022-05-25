package ro.unibuc.etickets.services;

import ro.unibuc.etickets.client.Client;
import ro.unibuc.etickets.locations.Location;
import ro.unibuc.etickets.repositories.EventRepository;
import ro.unibuc.etickets.repositories.LocationRepository;
import ro.unibuc.etickets.services.csv.ClientCSV;
import ro.unibuc.etickets.services.csv.LocationCSV;

import java.util.ArrayList;

public class LocationsServices {
    private ArrayList<Location> storage = new ArrayList<Location>();

    private final LocationCSV locationCSV = LocationCSV.getInstance();

    private final LocationRepository locationRepository = new LocationRepository();

    private final String locationCSVPath = "./csv/locations.csv";

    public LocationsServices() {
        try {
            storage.addAll(locationCSV.load(locationCSVPath));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addLocation(String locationName, String country, String city, String address, String ZIP){
        Location tempLocation = new Location(locationName, country, city, address, ZIP);
        storage.add(tempLocation);
        locationCSV.add(locationCSVPath, tempLocation);
        locationRepository.addLocationDB(tempLocation);
    }

    public ArrayList<Location> getLocations(){
        return storage;
    }

}
