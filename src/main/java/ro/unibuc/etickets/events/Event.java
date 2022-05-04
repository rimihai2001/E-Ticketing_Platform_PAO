package ro.unibuc.etickets.events;

import ro.unibuc.etickets.locations.Location;

public class Event {
    protected String name;
    protected Integer numberTickets;
    protected Double price;
    protected Location location;

    public Event() {

    }

    public Event(String name, Integer numberTickets, Double price, Location location) {
        this.name = name;
        this.numberTickets = numberTickets;
        this.price = price;
        this.location = location;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNumberTickets(Integer numberTickets){
        this.numberTickets = numberTickets;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public void setLocation(Location l){
        this.location=l;
    }

    public String getName(){
        return name;
    }

    public Integer getNumberTickets() {
        return numberTickets;
    }

    public Double getPrice(){
        return price;
    }

    public Location getLocation(){
        return location;
    }

    @Override
    public String toString() {
        return String.format("%s: \n No. Tickets: %s \n Price: %s \n Location ID: %s \n", name, Integer.toString(numberTickets), Double.toString(price), location.getCountry());
    }


}
