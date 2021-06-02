package org.example;

public class Admin extends User
{

    public Admin(String name, String lastName, String dni, String email, String user, String password, int phoneNumber)
    {
        super(name, lastName, dni, email, user, password, phoneNumber);
    }

    public Admin()
    {
    }

    @Override
    public String toString()
    {
        return "Admin{} " + super.toString();
    }
}
