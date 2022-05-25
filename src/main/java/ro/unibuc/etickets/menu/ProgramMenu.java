package ro.unibuc.etickets.menu;

import ro.unibuc.etickets.client.Card;
import ro.unibuc.etickets.client.Person;
import ro.unibuc.etickets.database.config.DbConnection;
import ro.unibuc.etickets.events.Concert;
import ro.unibuc.etickets.events.CulturalEvent;
import ro.unibuc.etickets.events.Event;
import ro.unibuc.etickets.events.SportEvent;
import ro.unibuc.etickets.locations.Location;
import ro.unibuc.etickets.repositories.CardRepository;
import ro.unibuc.etickets.repositories.EventRepository;
import ro.unibuc.etickets.repositories.LocationRepository;
import ro.unibuc.etickets.repositories.SellerRepository;
import ro.unibuc.etickets.seller.Seller;
import ro.unibuc.etickets.services.EventsServices;
import ro.unibuc.etickets.services.LocationsServices;
import ro.unibuc.etickets.services.ClientsServices;
import ro.unibuc.etickets.services.SellersServices;

import java.util.HashMap;
import java.util.Scanner;

public class ProgramMenu {

    private Scanner s = new Scanner(System.in);

    private EventsServices eventsServices = new EventsServices();
    private LocationsServices locationsServices = new LocationsServices();
    private ClientsServices clientsServices = new ClientsServices();
    private SellersServices sellersServices = new SellersServices();

    private DbConnection dbConnection = new DbConnection();

    private CardRepository cardRepo = new CardRepository();

    private EventRepository eventRepo = new EventRepository();

    private LocationRepository locationRepo = new LocationRepository();

    private SellerRepository sellerRepo = new SellerRepository();


    public static void main(String args[]) {

        ProgramMenu menu = new ProgramMenu();

        System.out.println("Welcome to Rock Monsters Ticketing Platform!");

        while (true) {
            System.out.println("""
                    1. Add a new location
                    2. Add a new client
                    3. Add a new event
                    4. Add a new seller
                    5. Modify seller tickets stock
                    6. Show all events CSV
                    7. Show all clients CSV
                    8. Show all locations CSV
                    9. Show all sellers CSV
                    10. Show all cards used CSV
                    11. Delete seller 
                    12. Show all cards used DB
                    13. Delete card DB
                    14. Update card DB
                    15. Show all events DB
                    16. Delete event DB
                    17. Update event DB
                    18. Show all locations DB
                    19. Delete location DB
                    20. Update location DB
                    21. Show all sellers DB
                    22. Delete seller DB
                    23. Update seller DB
                    0. Exit""");
            System.out.println("Option: ");
            int option = menu.readOption();
            menu.execute(option);

        }
    }

    private int readOption() {
        try {
            int option = Integer.parseInt(s.nextLine());
            return option;
        } catch (Exception invalid) {
            System.out.println("Error: " + invalid.getMessage());
        }
        return readOption();
    }

    private void execute(int option) {
        switch (option) {
            case 1:
                try {
                    System.out.println("Location Name:");
                    String locationName = s.nextLine();
                    System.out.println("Country:");
                    String country = s.nextLine();
                    System.out.println("City: ");
                    String city = s.nextLine();
                    System.out.println("Address: ");
                    String address = s.nextLine();
                    System.out.println("ZIP:");
                    String ZIP = s.nextLine();
                    locationsServices.addLocation(locationName, country, city, address, ZIP);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 2:
                try {
                    System.out.println("First Name:");
                    String firstName = s.nextLine();
                    System.out.println("Last Name: ");
                    String lastName = s.nextLine();
                    System.out.println("Email: ");
                    String email = s.nextLine();
                    System.out.println("Phone: ");
                    String phone = s.nextLine();
                    System.out.println("Address: ");
                    String address = s.nextLine();
                    System.out.println("Password: ");
                    String password = s.nextLine();
                    System.out.println("Card Number: ");
                    String cardN = s.nextLine();
                    System.out.println("Owner Name: ");
                    String ownName = s.nextLine();
                    System.out.println("Expiry Date: ");
                    String expDate = s.nextLine();
                    clientsServices.addClient(firstName, lastName, email, phone, address, password, cardN, ownName, expDate );
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 3:
                try {
                    System.out.println("Choose event type: 1-Concert, 2-Cultural, 3-Sport");
                    int type = Integer.parseInt(s.nextLine());

                    System.out.println("Name:");
                    String name = s.nextLine();
                    System.out.println("No. of Tickets: ");
                    Integer noTickets = Integer.valueOf(s.nextLine());
                    System.out.println("Price: ");
                    Double price = Double.valueOf(s.nextLine());
                    System.out.println("Location ID: ");
                    Location locations = locationsServices.getLocations().get(Integer.parseInt(s.nextLine()));

                    if (type == 1){
                        System.out.println("Artist Name:");
                        String artName = s.nextLine();
                        Event e = new Concert(name, noTickets, price, locations, artName);
                        eventsServices.addEvent(e);
                    } else if (type == 2)
                    {
                        System.out.println("Cultural Event Type: (THEATER/MOVIE/OPERA)");
                        String evCulType = s.nextLine();
                        System.out.println("Duration: ");
                        Integer length = Integer.valueOf(s.nextLine());
                        Event e = new CulturalEvent(name, noTickets, price, locations, evCulType, length);
                        eventsServices.addEvent(e);
                    } else if (type ==3) {
                        System.out.println("Sport Event Type: (BASKETBALL/TENNIS/FOOTBALL)");
                        String sportsType = s.nextLine();
                        System.out.println("Team/Player 1:");
                        String p1 = s.nextLine();
                        System.out.println("Team/Player 2:");
                        String p2 = s.nextLine();
                        Event e = new SportEvent(name, noTickets, price, locations, sportsType, p1, p2);
                        eventsServices.addEvent(e);
                    }
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 4:
                try {
                    System.out.println("Name:");
                    String name = s.nextLine();
                    System.out.println("URL Address:");
                    String addressURL = s.nextLine();
                    System.out.println("No. of Events:");
                    int n = Integer.parseInt(s.nextLine());
                    HashMap<Event, Integer> ev = new HashMap<>();
                    for(int i=0; i<n; i++)
                    {
                        System.out.println("Choose Event ID");
                        int id = Integer.parseInt(s.nextLine());

                        System.out.println("Number of Tickets: ");
                        int noT = Integer.parseInt(s.nextLine());

                        ev.put(eventsServices.getEvents().get(id), noT);

                    }
                    sellersServices.addSeller(name,addressURL,ev);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 5:
                try {
                    System.out.println("Id Seller:");
                    int idSeller = Integer.parseInt(s.nextLine());
                    System.out.println("No. of Events:");
                    int n = Integer.parseInt(s.nextLine());
                    HashMap<Event, Integer> ev = new HashMap<>();
                    for(int i=0; i<n; i++)
                    {
                        System.out.println("Choose Event ID");
                        int id = Integer.parseInt(s.nextLine());

                        System.out.println("Number of Tickets: ");
                        int noT = Integer.parseInt(s.nextLine());

                        ev.put(eventsServices.getEvents().get(id), noT);

                    }
                    sellersServices.modifyTicketsStock(idSeller, ev);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 6:
                try {
                    System.out.println("-------------------------");
                    for(Event ev : eventsServices.getEvents()){
                        System.out.println(ev.toString());
                    };
                    System.out.println("-------------------------");
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 7:
                try {
                    System.out.println("-------------------------");
                    for(Person p : clientsServices.getClients()){
                        System.out.println(p.toString());
                    };
                    System.out.println("-------------------------");
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 8:
                try {
                    for (Location location : locationsServices.getLocations()){
                        System.out.println("----------------\n" + "Location Name:" + location.getName() + "\n" + "Country:" + location.getCountry() + "\n" + "City: " + location.getCity() + "\n" + "Address: " + location.getAddress() + "\n"+ "ZIP Code: " + location.getZIP() + "\n--------------------\n") ;
                    };
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 9:
                try {
                    for (Seller seller : sellersServices.getSellers()){
                        System.out.println("----------------\n" + "Name:" + seller.getName() + "\n" + "URL: " + seller.getAddress() + "\n");
                        if(seller.getTicketsStock().size()>0){
                            System.out.println("Tickets:\n");
                            for (Event ev: seller.getTicketsStock().keySet()) {
                                String key = ev.toString();
                                String value = seller.getTicketsStock().get(ev).toString();
                                System.out.println(key + "Number of tickets at seller: " + value + "\n");
                            }
                        }

                        System.out.println("----------------------------\n");
                    };
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 10:
                try {
                    System.out.println("-------------------------");
                    for(Card cardUsed : clientsServices.getCards()){
                        System.out.println(cardUsed.toString());
                    }
                    System.out.println("-------------------------");
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 11:
                try {
                    System.out.println("Index:");
                    int index = Integer.parseInt(s.nextLine());
                    sellersServices.deleteSeller(index);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 12:
                try {
                    System.out.println("-------------------------");
                    for(Card cardUsed : CardRepository.load()){
                        System.out.println(cardUsed.toString());
                    }
                    System.out.println("-------------------------");
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 13:
                try {
                    System.out.println("ID Card:");
                    int index = Integer.parseInt(s.nextLine());
                    CardRepository.deleteCard(index);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 14:
                try {
                    System.out.println("Card Number: ");
                    String cardN = s.nextLine();
                    System.out.println("Owner Name: ");
                    String ownName = s.nextLine();
                    System.out.println("Expiry Date: ");
                    String expDate = s.nextLine();
                    System.out.println("ID Card:");
                    int index = Integer.parseInt(s.nextLine());
                    cardRepo.updateCard(new Card(cardN, ownName, expDate), index);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();

                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 15:
                try {
                    System.out.println("-------------------------");
                    for(Event event : EventRepository.load()){
                        System.out.println(event.toString());
                    }
                    System.out.println("-------------------------");
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 16:
                try {
                    System.out.println("ID Event:");
                    int index = Integer.parseInt(s.nextLine());
                    EventRepository.deleteEvent(index);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();

                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 17:
                try {
                    System.out.println("Choose event type: 1-Concert, 2-Cultural, 3-Sport");
                    int type = Integer.parseInt(s.nextLine());

                    System.out.println("Name:");
                    String name = s.nextLine();
                    System.out.println("No. of Tickets: ");
                    Integer noTickets = Integer.valueOf(s.nextLine());
                    System.out.println("Price: ");
                    Double price = Double.valueOf(s.nextLine());
                    System.out.println("Location ID: ");
                    Location location = locationsServices.getLocations().get(Integer.parseInt(s.nextLine()));
                    Event ev = new Event(name, noTickets, price, location);
                    System.out.println("ID Event:");
                    int index = Integer.parseInt(s.nextLine());
                    EventRepository.updateEvent(ev, index);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 18:
                try {
                    System.out.println("-------------------------");
                    for(Location location : LocationRepository.load()){
                        System.out.println(location.toString());
                    }
                    System.out.println("-------------------------");
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();


                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 19:
                try {
                    System.out.println("ID Location:");
                    int index = Integer.parseInt(s.nextLine());
                    LocationRepository.deleteLocation(index);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();

                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 20:
                try {
                    System.out.println("Location Name:");
                    String locationName = s.nextLine();
                    System.out.println("Country:");
                    String country = s.nextLine();
                    System.out.println("City: ");
                    String city = s.nextLine();
                    System.out.println("Address: ");
                    String address = s.nextLine();
                    System.out.println("ZIP:");
                    String ZIP = s.nextLine();
                    System.out.println("ID Location:");
                    int index = Integer.parseInt(s.nextLine());
                    Location location = new Location(locationName, country, city, address, ZIP);
                    LocationRepository.updateLocation(location, index);
                    locationsServices.addLocation(locationName, country, city, address, ZIP);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();

                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 21:
                try {
                    System.out.println("-------------------------");
                    for(Seller seller : SellerRepository.load()){
                        System.out.println(seller.toString());
                    }
                    System.out.println("-------------------------");
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();

                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 22:
                try {
                    System.out.println("ID Seller:");
                    int index = Integer.parseInt(s.nextLine());
                    SellerRepository.deleteSeller(index);
                    System.out.println("Press Enter to go back to the menu.");
                    String skip = s.nextLine();

                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 23:
                try {
                    System.out.println("Name:");
                    String name = s.nextLine();
                    System.out.println("URL Address:");
                    String addressURL = s.nextLine();
                    HashMap<Event, Integer> ev = new HashMap<>();
                    System.out.println("ID Seller:");
                    int index = Integer.parseInt(s.nextLine());
                    Seller seller = new Seller(name, addressURL, ev);
                    SellerRepository.updateSeller(seller, index);
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option");
        }
    }
}
