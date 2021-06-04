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

    public Passenger ()
    {}


    @Override
    public String toString()
    {
        return "Passenger{" +
                "origin='" + origin + '\'' +
                ", originAdress='" + originAdress + '\'' +
                "} " + super.toString();

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
        return originAdress;
    }

    public void setOriginAdress(String originAdress)
    {
        this.originAdress = originAdress;
    }

    @Override
    public String getName()
    {
        return super.getName();
    }

    @Override
    public String getLastName()
    {
        return super.getLastName();
    }
}
