package org.example;

import org.example.controllers.ControllerLogin;
import org.example.controllers.ControllerPassenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Passenger extends User {

    private String origin, originAddress;
    //private List<String> history;
    private ArrayList<Ticket> history;

    public Passenger() {
    }

    public Passenger(String name, String lastName, String dni, String email, String user, String password, long phoneNumber, String origin, String originAddress) {
        super(name, lastName, dni, email, user, password, phoneNumber);
        this.origin = origin;
        this.originAddress = originAddress;
        this.history = new ArrayList<>();
    }

    public Passenger(String name, String lastName, String dni, String email, int phoneNumber, String origin, String originAddress) {
        super(name, lastName, dni, email, phoneNumber);
        this.origin = origin;
        this.originAddress = originAddress;
    }


    @Override
    public String getName() { return super.getName(); }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public void setDni(String dni) {
        super.setDni(dni);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getUser() {
        return super.getUser();
    }

    @Override
    public void setUser(String user) {
        super.setUser(user);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public long getPhoneNumber() {
        return super.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(long phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

    @Override
    public String getDni() {
        return super.getDni();
    }

    public ArrayList<Ticket> getHistory() {
        return history;
    }

    public void setHistory(Ticket ticket) {
        this.history.add(ticket);
    }


    @Override
    public boolean signIn(User userHandle, Hotel hotel) {
        if(userHandle instanceof Passenger) ControllerPassenger.controllerMenuPrincipal(hotel);
        return false;
    }

    @Override
    public boolean signOut() {
        return false;
    }

    @Override
    public String toString() {
        return "" + super.toString() +
                "origin='" + origin + '\'' +
                ", originAddress='" + originAddress + '\'' +
                ", history=" + history +
                "} \n";
    }

    public String getOrigin()
    {
        return origin;
    }

    public void setOrigin(String origin)
    {
        this.origin = origin;
    }

    public String getOriginAdress()
    {
        return originAddress;
    }

    public void setOriginAdress(String originAdress)
    {
        this.originAddress = originAdress;
    }

    /*Equals & HashCode*/

   /* @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(origin, passenger.origin) && Objects.equals(originAddress, passenger.originAddress) && Objects.equals(history, passenger.history);
    }
*/
/*    @Override
    public int hashCode()
    {
        return Objects.hash(origin, originAddress, history);
    }*/

}

