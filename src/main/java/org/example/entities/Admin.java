package org.example.entities;

public class Admin extends User
{

    public Admin(String name, String lastName, String dni, String email, String user, String password, long phoneNumber)
    {
        super(name, lastName, dni, email, user, password, phoneNumber);
    }

    public Admin()
    {
    }


    @Override
    public String toString()
    {
        return  "\t [Administrador]" + "\n\n"+
                super.toString();
    }
}
