package ro.unibuc.etickets.services.csv;

import ro.unibuc.etickets.client.Card;
import ro.unibuc.etickets.client.Client;
import ro.unibuc.etickets.locations.Location;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class CardCSV implements GenericCSV<Card>{
    private static final CardCSV INSTANCE = new CardCSV();

    private final String auditPath = "./csv/audit.csv";

    private CardCSV() {
    }

    @Override
    public ArrayList<Card> load(String fileName) throws FileNotFoundException {
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
        ArrayList<Card> cards = new ArrayList<Card>();
        try {
            String line = csvReader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                Card card = new Card(data[0], data[1], data[2]);
                cards.add(card);
                line = csvReader.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        audit(auditPath, "load_cards");
        return cards;
    }

    @Override
    public void add(String fileName, Card content) {
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
        audit(auditPath, "add_card");
    }

    public static CardCSV getInstance() {
        return INSTANCE;
    }
}
