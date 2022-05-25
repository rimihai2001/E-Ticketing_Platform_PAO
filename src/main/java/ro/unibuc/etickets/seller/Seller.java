package ro.unibuc.etickets.seller;

import ro.unibuc.etickets.events.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Seller implements Comparable<Seller>{
    private static int _id = 100;
    private final int sellerId;

    private String name;
    private String addressURL;
    private HashMap<Event, Integer> ticketsStock;


    public Seller(String name, String addressURL) {
        this.name = name;
        this.addressURL = addressURL;
        this.sellerId = ++_id;
        ticketsStock = new HashMap<Event, Integer>();
    }

    public Seller(String name, String addressURL, HashMap<Event, Integer> ticketsStock) {
        this.name = name;
        this.addressURL = addressURL;
        this.sellerId = ++_id;
        this.ticketsStock = ticketsStock;
    }

    public Seller(ResultSet result) throws SQLException {
        this.name=result.getString("SellerName");
        this.addressURL=result.getString("URL");
        this.sellerId=++_id;
    }

    public int getSupermarketId() {
        return sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return addressURL;
    }

    public void setAddress(String addressURL) {
        this.addressURL = addressURL;
    }

    public HashMap<Event, Integer> getTicketsStock() {
        return ticketsStock;
    }

    public void setTicketsStock(HashMap<Event, Integer> ticketsStock) {
        this.ticketsStock = ticketsStock;
    }

    public void addTicket(Event ticket, int quantity) {
        ticketsStock.put(ticket, quantity);
    }

    public void removeTicket(Event ticket) {
        ticketsStock.remove(ticket);
    }

    public void removeTicket(Event ticket, int quantity) {
        int currentQuantity = ticketsStock.get(ticket);
        if(currentQuantity - quantity < 0) {
            throw new IllegalArgumentException("Not enough tickets in the stock");
        }
        ticketsStock.put(ticket, currentQuantity - quantity);
    }

    public void clearTicketsStock() {
        ticketsStock.clear();
    }

    public Event getTicket(int index) {
        return ticketsStock.keySet().toArray(new Event[ticketsStock.size()])[index];
    }

    public int getTicketQuantity(Event ticket) {
        return ticketsStock.get(ticket);
    }

    public int getTicketCount() {
        return ticketsStock.size();
    }

    public boolean containsTicket(Event ticket, int quantity) {
        return ticketsStock.containsKey(ticket) && ticketsStock.get(ticket) >= quantity;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", name, addressURL);
    }
    @Override
    public int compareTo(Seller o) {
        return this.name.compareTo(o.name);
    }
}
