package ro.unibuc.etickets.services;

import ro.unibuc.etickets.events.Event;
import ro.unibuc.etickets.seller.Seller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class SellersServices {
    private ArrayList<Seller> storage = new ArrayList<>();

    public void addSeller(String name, String addressURL, HashMap< Event, Integer> ticketsStock){
        storage.add(new Seller(name, addressURL, ticketsStock));
        storage.sort(Comparator.comparing(Seller::getName));
    }

    public ArrayList<Seller> getSellers(){
        return storage;
    }

    public void deleteSeller(int id){
        storage.remove(id);
    }

    public void modifyTicketsStock(int id, HashMap<Event, Integer> stock){
        storage.get(id).setTicketsStock(stock);
    }
}
