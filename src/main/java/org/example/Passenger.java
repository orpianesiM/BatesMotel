package org.example;

public class Passenger extends User
{
    private String origin, originAdress;

    public Passenger(String name, String lastName, String dni, String email, String user, String password, int phoneNumber, String origin, String originAdress)
    {
        super(name, lastName, dni, email, user, password, phoneNumber);
        this.origin = origin;
        this.originAdress = originAdress;
    }



    public Passenger(String origin, String originAdress)
    {
        this.origin = origin;
        this.originAdress = originAdress;
    }

    public Passenger(String name, String lastName, String dni, Integer phoneNumber, String email){
        super(name, lastName, dni, phoneNumber, email);
    }


    @Override
    public String toString()
    {
        return "Passenger{" +
                "origin='" + origin + '\'' +
                ", originAdress='" + originAdress + '\'' +
                "} " + super.toString();

    }
}
