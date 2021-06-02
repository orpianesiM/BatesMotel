package org.example;

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


        System.out.println("ad");
        Hotel hotel = new Hotel("probando", "123");

        //TreeSet<Hotel> users = new TreeSet<>();
        //users.addAll(FileHelper.getUsersFromJson()); //obtengo todo que tengo guardado para pisar
       // users.add(hotel);                         //agrego nuevo
        FileHelper.setUsersToJson(hotel);             //agrego todos

        System.out.println(FileHelper.getUsersFromJson());

    }

}
