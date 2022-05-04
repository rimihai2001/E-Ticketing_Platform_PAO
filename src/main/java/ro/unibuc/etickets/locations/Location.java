package ro.unibuc.etickets.locations;

import java.util.*;

public class Location {
    private String country;
    private String city;
    private String address;
    private String ZIP;
    public Location(){

    }
    public Location(String country, String city, String address, String ZIP){
        if(country.isEmpty() || city.isEmpty() || address.isEmpty()){
            throw new RuntimeException("Empty attributes");
        }
        this.country=country;
        this.city=city;
        this.address=address;
        this.ZIP=ZIP;
        for(int i=0;i<ZIP.length();i++) {
            if (ZIP.charAt(i) < 48 || ZIP.charAt(i) > 57) {
                throw new RuntimeException("Your ZIP must contains only digits!");
            }
        }

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }
}
