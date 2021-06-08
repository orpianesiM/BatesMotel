package org.example;

import org.example.controllers.ControllerEmployee;

public class Employee extends User
{

    public Employee(String name, String lastName, String dni, String email, String user, String password, int phoneNumber)
    {
        super(name, lastName, dni, email, user, password, phoneNumber);
    }

    public Employee()
    {
    }

    @Override
    public boolean signIn(User userHandle, Hotel hotel) {
        if(userHandle instanceof Employee) ControllerEmployee.controllerMenuEmployee(hotel);
        return false;
    }

    @Override
    public boolean signOut() {
        return false;
    }

    @Override
    public String toString()
    {
        return "Employee{} " + super.toString();
    }
}
