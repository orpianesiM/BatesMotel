package org.example;

import org.example.helpers.FileHelper;

import java.util.Set;

public abstract class User
{
    private String name, lastName, dni, email, user, password;
    private int phoneNumber;

    public User() {
    }

    public User(String name, String lastName, String dni, String email, int phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setDni(String dni)
    {
        this.dni = dni;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getDni()
    {
        return dni;
    }


    public boolean signIn(User userHandle, Hotel hotel){
        return false;
    }

    public boolean signOut(){
        return false;
    }

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


}
