package ro.unibuc.etickets.services;

import ro.unibuc.etickets.client.Card;
import ro.unibuc.etickets.client.Client;
import ro.unibuc.etickets.repositories.CardRepository;
import ro.unibuc.etickets.services.csv.CardCSV;
import ro.unibuc.etickets.services.csv.ClientCSV;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;

public class ClientsServices {
    private ArrayList<Client> storage = new ArrayList<Client>();
    private static NavigableSet<Card> usedCards = new TreeSet<>();


    private final ClientCSV clientCSV = ClientCSV.getInstance();
    private final CardCSV cardCSV = CardCSV.getInstance();

    private final CardRepository cardRepository = new CardRepository();

    private final String clientCSVPath = "./csv/clients.csv";
    private final String cardCSVPath = "./csv/cards.csv";

    public ClientsServices() {
        try {
            storage.addAll(clientCSV.load(clientCSVPath));
            usedCards.addAll(cardCSV.load(cardCSVPath));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addClient(String firstName, String lastName, String email, String phone, String address, String password, String cardNumber, String ownerName, String expDate ){
        Card tempCard = new Card(cardNumber, ownerName, expDate);
        Client tempClient = new Client(firstName, lastName, email,phone, address, password, tempCard);
        storage.add(tempClient);
        usedCards.add(tempCard);
        clientCSV.add(clientCSVPath, tempClient);
        cardCSV.add(cardCSVPath, tempCard);
        cardRepository.addCardDB(tempCard);
    }

    public ArrayList<Client> getClients(){
        return storage;
    }

    public NavigableSet<Card> getCards(){return usedCards;}
}
