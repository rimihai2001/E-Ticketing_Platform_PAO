package ro.unibuc.etickets.client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Person {

    private static int _id = 1000;
    private final int personId;


    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public Person(String firstName, String lastName, String email, String phone, String address) {
        this.personId = ++_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Person(ResultSet result) throws SQLException {
        this.personId = ++_id;
        this.firstName=result.getString("FirstName");
        this.lastName=result.getString("LastName");
        this.email=result.getString("Email");
        this.phone=result.getString("Phone");
        this.address=result.getString("Address");
    }


    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", getFirstName(), getLastName(), getEmail(), getPhone(), getAddress());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return getPersonId() == person.getPersonId();
    }
}
