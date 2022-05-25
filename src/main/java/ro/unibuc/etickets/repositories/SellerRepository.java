package ro.unibuc.etickets.repositories;

import ro.unibuc.etickets.database.config.DbConnection;
import ro.unibuc.etickets.locations.Location;
import ro.unibuc.etickets.seller.Seller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SellerRepository {
    public static void addSellerDB(Seller seller) {
        String query = "insert into seller values (null, ?, ?)";
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, seller.getName());;
            statement.setString(2, seller.getAddress());;
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Seller> load() throws SQLException {
        String query = "select * from seller";
        ArrayList<Seller> sellersDB = new ArrayList<>();
        Statement statement= DbConnection.getInstance().createStatement();
        ResultSet result = ((Statement) statement).executeQuery(query);
        while (result.next()){
            Seller seller= new Seller(result);
            sellersDB.add(seller);
        }
        return sellersDB;

    }

    public static void deleteSeller(int idSeller) throws SQLException{
        String query = "delete from seller where idseller="+ idSeller;
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateSeller(Seller seller, int idSeller) throws SQLException{
        String query = "update seller set SellerName = ?, URL = ? where idSeller="+ idSeller;
        try(PreparedStatement statement = DbConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, seller.getName());;
            statement.setString(2, seller.getAddress());;
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
