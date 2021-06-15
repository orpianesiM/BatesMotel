package org.example.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.Booking;
import org.example.Hotel;
import org.example.Room;
import org.example.User;


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import org.example.User;
import java.io.*;


public class FileHelper {

    /********************************Final*Static**********************************/

   /* private final static String usersFile = "C:\\Users\\Juako\\Desktop\\UTN\\Segundo año\\Primer cuatrimestre\\Laboratorio III\\Projects\\BatesMotel\\main\\java\\org\\example\\files\\UserFile.json";
    private final static String roomFile = "C:\\Users\\Juako\\Desktop\\UTN\\Segundo año\\Primer cuatrimestre\\Laboratorio III\\Projects\\BatesMotel\\main\\java\\org\\example\\files\\RoomFile.json";
    private final static String bookingsFile = "C:\\Users\\Juako\\Desktop\\UTN\\Segundo año\\Primer cuatrimestre\\Laboratorio III\\Projects\\BatesMotel\\main\\java\\org\\example\\files\\BookingFile.json";*/
    private final static String usersFile = ".\\src\\main\\java\\org\\example\\files\\UserFile.json";
    private final static String roomFile = ".\\src\\main\\java\\org\\example\\files\\RoomFile.json";
    private final static String bookingsFile = ".\\src\\main\\java\\org\\example\\files\\BookingFile.json";

    /********************************Users**********************************/
    /**
     * Save Users in the UserFile
     * @param users
     */
    public static void setUsersToJson(ArrayList<User> users){
        File file = new File(usersFile);
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, ArrayList.class, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Users from JsonFile
     * @return
     */
    public static List<User> getUsersFromJson()
    {
        List<User> users = new ArrayList<>();
        File file = new File(usersFile);
        try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            users = gson.fromJson(buffer, new TypeToken<List<User>>()
            {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }/* finally
        {
        }*/
        return users;
    }
    /*******************************FIN*Users**********************************/

    /*******************************Booking**********************************/
    /**
     * Save Bookings in the BookingFile
     * @param bookings
     */
    public static void setBookingsToJson(HashSet<Booking> bookings) {
        File file = new File(bookingsFile);
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(bookings, HashSet.class, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Bookings from JsonFile
     * @return
     */
    public static HashSet<Booking> getBookingsFromJson(){
        HashSet<Booking> bookings = new HashSet<>();
        File file = new File(bookingsFile);
        try (BufferedReader buffer = new BufferedReader(new FileReader(file))){
            Gson gson = new Gson();
            bookings = gson.fromJson(buffer, new TypeToken<HashSet<Booking>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    /*******************************Fin*Booking**********************************/

    /********************************Room**********************************/

    /**
     * Save Rooms in the RoomsFile
     * @param rooms
     */
    public static void setRoomsToJson(ArrayList<Room> rooms){
        File file = new File(roomFile);
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(rooms, ArrayList.class, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Rooms from JsonFile
     * @return
     */
    public static ArrayList<Room> getRoomsFromJson(){
        ArrayList<Room> rooms = new ArrayList<>();
        File file = new File(roomFile);
        try (BufferedReader buffer = new BufferedReader(new FileReader(file))){
            Gson gson = new Gson();
            rooms = gson.fromJson(buffer, new TypeToken<ArrayList<Room>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    /*******************************Fin*Room**********************************/

}
