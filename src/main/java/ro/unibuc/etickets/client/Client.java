package ro.unibuc.etickets.client;

public class Client extends Person{
    private String password;
    private Card paymentCard;


    public Client(String firstName, String lastName, String email, String phone, String address, String password, Card paymentCard) {
        super(firstName, lastName, email, phone, address);
        this.password = password;
        this.paymentCard = paymentCard;
    }


    public Client(String firstName, String lastName, String email, String phone, String address, String password) {
        super(firstName, lastName, email, phone, address);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Card getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(Card paymentCard) {
        this.paymentCard = paymentCard;
    }

    @Override
    public String toString() {
        return super.toString()+","+String.format("%s", getPassword())+","+paymentCard.toString();
    }

}
