package org.example.entities;

public class Admin extends User
{

    public Admin(String name, String lastName, String dni, String email, String user, String password, long phoneNumber, UserType userType)
    {
        super(name, lastName, dni, email, user, password, phoneNumber, userType);
    }

    public Admin()
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
        return "Admin{} " + super.toString();
    }
}
