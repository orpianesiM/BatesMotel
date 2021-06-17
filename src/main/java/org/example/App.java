package org.example;

import org.example.controllers.ControllerAdmin;
import org.example.controllers.ControllerLogin;
import org.example.entities.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class App

{
    public static void main(String[] args )
    {
        Hotel hotel = new Hotel();
        ControllerLogin.login(hotel);
        hotel.save();
    }

}

