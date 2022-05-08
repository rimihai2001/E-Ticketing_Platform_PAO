package ro.unibuc.etickets.events;

import ro.unibuc.etickets.locations.Location;

public class SportEvent extends Event {
    public enum Sport {
        TENNIS,
        BASKETBALL,
        FOOTBALL
    }
    protected Sport sportName;
    protected String player1;
    protected String player2;

    public SportEvent(){

    }
    public SportEvent(String name, Integer numberTickets, Double price, Location location, String sportName, String p1, String p2){
        super(name,numberTickets,price,location);
        this.sportName= Sport.valueOf(sportName);
        this.player1=p1;
        this.player2=p2;
    }

    public Sport getSportName() {
        return sportName;
    }

    public void setSportName(Sport sportName) {
        this.sportName = sportName;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayers(String p1, String p2) {
        this.player1=p1;
        this.player2=p2;
    }

    @Override
    public String toString() {
        return super.toString()+","+String.format("%s,%s,%s", sportName, player1, player2);
    }
}
