package ro.unibuc.etickets.client;

public class Card{
    private String cardNumber;
    private String ownerName;
    private String expiryDate;


    public Card(String cardNumber, String ownerName, String expiryDate) {
        this.cardNumber = cardNumber;
        this.ownerName = ownerName;
        this.expiryDate = expiryDate;
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
        StringBuilder cn= new StringBuilder();
        cn.append("*".repeat(cardNumber.length()));
        return String.format("\n Card Info: \n Card Number:%s\n Owner: %s, Expires on: %s\n", cn, ownerName, expiryDate);
    }


}
