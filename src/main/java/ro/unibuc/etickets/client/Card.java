package ro.unibuc.etickets.client;

import ro.unibuc.etickets.seller.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Card implements Comparable<Card>{
    private String cardNumber;
    private String ownerName;
    private String expiryDate;


    public Card(String cardNumber, String ownerName, String expiryDate) {
        this.cardNumber = cardNumber;
        this.ownerName = ownerName;
        this.expiryDate = expiryDate;
    }

    public Card(ResultSet result) throws SQLException {
        this.cardNumber=result.getString("CardNumber");
        this.ownerName=result.getString("OwnerName");
        this.expiryDate=result.getString("ExpiryDate");
    }

    public String getNumber() {
        return cardNumber;
    }

    public void setNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card that)) return false;
        return getNumber().equals(that.getNumber()) && getOwnerName().equals(that.getOwnerName()) && getExpiryDate().equals(that.getExpiryDate());
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", getNumber(), getOwnerName(), getExpiryDate());
    }

    @Override
    public int compareTo(Card o) {
        return this.cardNumber.compareTo(o.cardNumber);
    }

}
