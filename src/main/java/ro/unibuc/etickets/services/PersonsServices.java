package ro.unibuc.etickets.services;

import ro.unibuc.etickets.client.Card;
import ro.unibuc.etickets.client.Client;
import ro.unibuc.etickets.client.Person;
import ro.unibuc.etickets.locations.Location;

import java.util.ArrayList;

public class PersonsServices {
    private ArrayList<Client> storage = new ArrayList<Client>();

    public void addPerson(String firstName, String lastName, String email, String phone, String address, String password, String cardNumber, String ownerName, String expDate ){
        storage.add(new Client(firstName, lastName, email,phone, address, password, new Card(cardNumber, ownerName, expDate)));
    }

    public ArrayList<Client> getPersons(){
        return storage;
    }
}
