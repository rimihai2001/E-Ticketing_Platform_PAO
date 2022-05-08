package ro.unibuc.etickets.services.csv;

import ro.unibuc.etickets.client.Card;
import ro.unibuc.etickets.client.Client;
import ro.unibuc.etickets.events.Event;
import ro.unibuc.etickets.seller.Seller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class SellerCSV implements GenericCSV<Seller>{
    private static final SellerCSV INSTANCE = new SellerCSV();

    private final String auditPath = "./csv/audit.csv";

    private SellerCSV() {
    }

    @Override
    public ArrayList<Seller> load(String fileName) throws FileNotFoundException {
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        try {
            String line = csvReader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                HashMap<Event, Integer> tickets = new HashMap<>();
                Seller seller = new Seller(data[0], data[1], tickets);
                sellers.add(seller);
                line = csvReader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        audit(auditPath, "load_sellers");
        return sellers;
    }

    @Override
    public void add(String fileName, Seller content) {
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(fileName, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            csvWriter.append(content.toString());
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        audit(auditPath, "add_seller");
    }

    public static SellerCSV getInstance() {
        return INSTANCE;
    }
}
