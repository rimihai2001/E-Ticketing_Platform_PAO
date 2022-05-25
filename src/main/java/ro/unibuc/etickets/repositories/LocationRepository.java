package ro.unibuc.etickets.repositories;

import ro.unibuc.etickets.client.Card;
import ro.unibuc.etickets.database.config.DbConnection;
import ro.unibuc.etickets.locations.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LocationRepository {
    public static void addLocationDB(Location location) {
        String query = "insert into location values (null, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, location.getName());
            statement.setString(2, location.getCountry());
            statement.setString(3, location.getCity());
            statement.setString(4, location.getAddress());
            statement.setString(5, location.getZIP());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Location> load() throws SQLException {
        String query = "select * from location";
        ArrayList<Location> locationsDB = new ArrayList<>();
        Statement statement= DbConnection.getInstance().createStatement();
        ResultSet result = ((Statement) statement).executeQuery(query);
        while (result.next()){
            Location location= new Location(result);
            locationsDB.add(location);
        }
        return locationsDB;

    }

    public static void deleteLocation(int idLocation) throws SQLException{
        String query = "delete from location where idLocation="+ idLocation;
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateLocation(Location location, int idLocation) throws SQLException{
        String query = "update location set LocationName = ?, Country = ?, City = ?, Address = ?, ZIP = ? where idLocation="+ idLocation;
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, location.getName());
            statement.setString(2, location.getCountry());
            statement.setString(3, location.getCity());
            statement.setString(4, location.getAddress());
            statement.setString(5, location.getZIP());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
