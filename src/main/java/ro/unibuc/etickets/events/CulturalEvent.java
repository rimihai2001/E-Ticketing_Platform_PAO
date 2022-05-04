package ro.unibuc.etickets.events;

import ro.unibuc.etickets.locations.Location;


public class CulturalEvent extends Event {

    public enum ShowType {
        THEATER,
        MOVIE,
        OPERA
    }
    protected ShowType culturalEventType;
    protected Integer showLength;
    public CulturalEvent(){

    }
    public CulturalEvent(String name, Integer numberTickets, Double price, Location location, String culturalEventType, Integer showLength){
        super(name,numberTickets,price,location);
        this.culturalEventType= ShowType.valueOf(culturalEventType);
        this.showLength=showLength;
    }

    public ShowType getCulturalEventType() {
        return culturalEventType;
    }

    public void setCulturalEventType(ShowType culturalEventType) {
        this.culturalEventType = culturalEventType;
    }

    public Integer getShowLength() {
        return showLength;
    }

    public void setShowLength(Integer showLength) {
        this.showLength = showLength;
    }

    @Override
    public String toString() {
        return super.toString()+String.format("%s: Duration %s", culturalEventType, Integer.toString(showLength));
    }
}
