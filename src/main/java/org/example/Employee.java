package org.example;

import org.example.controllers.ControllerEmployee;

public class Employee extends User
{

    public Employee(String name, String lastName, String dni, String email, String user, String password, int phoneNumber, UserType userType)
    {
        super(name, lastName, dni, email, user, password, phoneNumber, userType);
    }

    public Employee()
    {
    }

    @Override
    public UserType getUserType() {
        return super.getUserType();
    }

    @Override
    public void setUserType(UserType userType) {
        super.setUserType(userType);
    }

    @Override
    public String toString()
    {
        return "Employee{} " + super.toString();
    }
}
