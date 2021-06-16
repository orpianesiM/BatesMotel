package org.example.entities;

public abstract class User
{
    private String name, lastName, dni, email, user, password;
    private UserType userType;
    private long phoneNumber;

    public User() {
    }

    public User(String name, String lastName, String dni, String email, long phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(String name, String lastName, String dni, String email, String user, String password, long phoneNumber, UserType userType)
    {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.user = user;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }


    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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

    public long getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getDni()
    {
        return dni;
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

   /* //public int compareTo(User o)
    {
        return this.getDni().compareTo(o.getDni());
    }
*/

}
