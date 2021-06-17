package org.example.entities;


import java.util.ArrayList;

public class Passenger extends User {

    private String origin, originAddress;
    private ArrayList<Ticket> history;

    public Passenger() {
    }

    public Passenger(String name, String lastName, String dni, String email, String user, String password, long phoneNumber, String origin, String originAddress) {
        super(name, lastName, dni, email, user, password, phoneNumber);
        this.origin = origin;
        this.originAddress = originAddress;
        this.history = new ArrayList<>();
    }

    public Passenger(String name, String lastName, String dni, String email, long phoneNumber, String origin, String originAddress) {
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
    public String toString() {
        return "\t [Pasajero] \n" +
                super.toString() +
                "Origen: [" + origin +"] \n"+
                "Dirección de origen: [" + originAddress +"] \n"+
                "Historial: [" + history +"] \n";
    }

   /**ToString modificado para checkear el historial, llamar clase.detallePasajero **/
    public String detallePasajero() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t [Pasajero] \n")
                .append(super.toString())
                .append("Origen: [")
                .append(origin)
                .append("] \n")
                .append("Dirección de origen: [")
                .append(originAddress)
                .append("] \n");
        if (!(this.getHistory().isEmpty())) {
            sb.append("Historial: [")
                    .append(history)
                    .append("] \n");
        }
        return sb.toString();
    }

}

