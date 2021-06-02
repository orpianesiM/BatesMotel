package org.example;

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
    public String toString()
    {
        return "Employee{} " + super.toString();
    }
}
