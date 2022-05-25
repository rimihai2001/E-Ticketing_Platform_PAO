package ro.unibuc.etickets.locations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Location {

    private String locationName;
    private String country;
    private String city;
    private String address;
    private String ZIP;
    public Location(){

    }
    public Location(String locationName, String country, String city, String address, String ZIP){
        if(country.isEmpty() || city.isEmpty() || address.isEmpty() || locationName.isEmpty()){
            throw new RuntimeException("Empty attributes");
        }
        this.locationName=locationName;
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

    public Location(ResultSet result) throws SQLException {
        this.locationName=result.getString("LocationName");
        this.country=result.getString("Country");
        this.city=result.getString("City");
        this.address=result.getString("Address");
        this.ZIP=result.getString("ZIP");
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

    public String getName(){
        return locationName;
    }

    public void setName(String locationName) {
        this.locationName = locationName;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", locationName, address, city, country, ZIP);
    }
}
