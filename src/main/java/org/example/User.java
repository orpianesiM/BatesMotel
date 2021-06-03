package org.example;

public abstract class User
{
    private String name, lastName, dni, email, user, password;
    private int phoneNumber;

    public User(String name, String lastName, String dni, String email, String user, String password, int phoneNumber)
    {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.user = user;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User()
    {
    }

    public User(String name, String lastName, String dni, Integer phoneNumber, String email) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public abstract boolean signIn();
    public abstract boolean signOut();

    @Override
    public String toString()
    {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                "} \n";
    }

    public String getDni()
    {
        return dni;
    }
}
