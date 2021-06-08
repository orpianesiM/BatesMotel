package org.example;

import org.example.controllers.ControllerAdmin;

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
    public boolean signIn(User userHandle, Hotel hotel) {
        if(userHandle instanceof Admin) ControllerAdmin.controllerMenuHotel(hotel);
        return false;
    }

    @Override
    public boolean signOut() {
        return super.signOut();
    }

    @Override
    public String toString()
    {
        return "Admin{} " + super.toString();
    }
}
