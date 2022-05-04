package ro.unibuc.etickets.services;

import ro.unibuc.etickets.client.Client;
import ro.unibuc.etickets.client.Person;
import ro.unibuc.etickets.locations.Location;

import java.util.ArrayList;

public class PersonsServices {
    private ArrayList<Client> storage = new ArrayList<Client>();

    public void addPerson(String firstName, String lastName, String email, String phone, String address, String password){
        storage.add(new Client(firstName, lastName, email,phone, address, password));
    }

    public ArrayList<Client> getPersons(){
        return storage;
    }
}
