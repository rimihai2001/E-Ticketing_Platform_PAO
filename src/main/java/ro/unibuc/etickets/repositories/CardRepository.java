package ro.unibuc.etickets.repositories;

import ro.unibuc.etickets.client.Card;
import ro.unibuc.etickets.database.config.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CardRepository {
    public static void addCardDB(Card card) {
        String query = "insert into card values (null, ?, ?, ?)";
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, card.getNumber());
            statement.setString(2, card.getOwnerName());
            statement.setString(3, card.getExpiryDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Card> load() throws SQLException {
        String query = "select * from card";
        ArrayList<Card> cardsUsedDB = new ArrayList<>();
        Statement statement= DbConnection.getInstance().createStatement();
        ResultSet result = ((Statement) statement).executeQuery(query);
        while (result.next()){
            Card card= new Card(result);
            cardsUsedDB.add(card);
        }
        return cardsUsedDB;

    }

    public static void deleteCard(int idCard) throws SQLException{
        String query = "delete from card where idCard="+ idCard;
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCard(Card card, int idCard) throws SQLException{
        String query = "update card set CardNumber = ?, OwnerName = ? and ExpiryDate = ? where idCard="+ idCard;
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, card.getNumber());
            statement.setString(2, card.getOwnerName());
            statement.setString(3, card.getExpiryDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
