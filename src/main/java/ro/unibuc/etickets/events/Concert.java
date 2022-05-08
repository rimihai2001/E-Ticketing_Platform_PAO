package ro.unibuc.etickets.events;

import ro.unibuc.etickets.locations.Location;

public class Concert extends Event {
    private String artistName;
    public Concert(){

    }
    public Concert(String name, Integer numberTickets, Double price, Location location, String artistName){
        super(name,numberTickets,price,location);
        this.artistName=artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return super.toString()+","+String.format("%s", artistName);
    }
}
