package org.example;

import org.example.controllers.ControllerAdmin;
import org.example.controllers.ControllerLogin;
import org.example.helpers.FileHelper;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App

{
    public static void main( String[] args )
    {
        Hotel hotel = new Hotel();
        ControllerLogin.login(hotel);
        hotel.save();
    }

}

