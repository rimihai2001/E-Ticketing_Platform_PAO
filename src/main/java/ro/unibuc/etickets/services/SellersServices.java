package ro.unibuc.etickets.services;

import ro.unibuc.etickets.events.Event;
import ro.unibuc.etickets.seller.Seller;
import ro.unibuc.etickets.services.csv.SellerCSV;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class SellersServices {
    private ArrayList<Seller> storage = new ArrayList<>();

    private final SellerCSV sellerCSV = SellerCSV.getInstance();

    private final String sellerCSVPath = "./csv/sellers.csv";

    public SellersServices() {
        try {
            storage.addAll(sellerCSV.load(sellerCSVPath));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addSeller(String name, String addressURL, HashMap< Event, Integer> ticketsStock){
        Seller tempSeller = new Seller(name, addressURL, ticketsStock);
        storage.add(tempSeller);
        storage.sort(Comparator.comparing(Seller::getName));
        sellerCSV.add(sellerCSVPath,tempSeller);
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
