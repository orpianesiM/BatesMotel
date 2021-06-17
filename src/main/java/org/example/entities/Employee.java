package org.example.entities;

public class Employee extends User
{
    public Employee(String name, String lastName, String dni, String email, String user, String password, long phoneNumber) {
        super(name, lastName, dni, email, user, password, phoneNumber);
    }

    public Employee()
    {
    }

    @Override
    public String toString()
    {
        return  "\t [Empleado]" + "\n\n"+
                super.toString();
    }
}
