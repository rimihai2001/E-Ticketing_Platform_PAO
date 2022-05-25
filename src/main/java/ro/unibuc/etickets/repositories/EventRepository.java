package ro.unibuc.etickets.repositories;

import ro.unibuc.etickets.client.Card;
import ro.unibuc.etickets.database.config.DbConnection;
import ro.unibuc.etickets.events.Event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EventRepository {
    public static void addEventDB(Event event) {
        String query = "insert into event values (null, ?, ?, ?)";
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, event.getName());
            statement.setInt(2, event.getNumberTickets());
            statement.setDouble(3, event.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Event> load() throws SQLException {
        String query = "select * from event";
        ArrayList<Event> eventsDB = new ArrayList<>();
        Statement statement= DbConnection.getInstance().createStatement();
        ResultSet result = ((Statement) statement).executeQuery(query);
        while (result.next()){
            Event event= new Event(result);
            eventsDB.add(event);
        }
        return eventsDB;

    }

    public static void deleteEvent(int idEvent) throws SQLException{
        String query = "delete from event where edevent="+ idEvent;
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateEvent(Event event, int idCard) throws SQLException{
        String query = "update event set EventName = ?, TicketsNumber = ? and Price = ? where idEvent="+ idCard;
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, event.getName());
            statement.setInt(2, event.getNumberTickets());
            statement.setDouble(3, event.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
