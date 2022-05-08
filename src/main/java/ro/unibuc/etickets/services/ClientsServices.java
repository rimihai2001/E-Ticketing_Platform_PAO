package ro.unibuc.etickets.services;

import ro.unibuc.etickets.client.Card;
import ro.unibuc.etickets.client.Client;
import ro.unibuc.etickets.services.csv.ClientCSV;

import java.util.ArrayList;

public class ClientsServices {
    private ArrayList<Client> storage = new ArrayList<Client>();

    private final ClientCSV clientCSV = ClientCSV.getInstance();

    private final String clientCSVPath = "./csv/clients.csv";

    public ClientsServices() {
        try {
            storage.addAll(clientCSV.load(clientCSVPath));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addClient(String firstName, String lastName, String email, String phone, String address, String password, String cardNumber, String ownerName, String expDate ){
        Client tempClient = new Client(firstName, lastName, email,phone, address, password, new Card(cardNumber, ownerName, expDate));
        storage.add(tempClient);
        clientCSV.add(clientCSVPath, tempClient);
    }

    public ArrayList<Client> getClients(){
        return storage;
    }
}
