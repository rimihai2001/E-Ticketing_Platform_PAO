package ro.unibuc.etickets.events;

import ro.unibuc.etickets.locations.Location;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Event(ResultSet result) throws SQLException {
        this.name=result.getString("EventName");
        this.numberTickets=result.getInt("TicketsNumber");
        this.price=result.getDouble("Price");
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
        return String.format("%s,%s,%s,%s", name, Integer.toString(numberTickets), Double.toString(price), location.toString());
    }


}
