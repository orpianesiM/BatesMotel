package org.example.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.util.*;

import org.example.entities.*;


public class FileHelper {

    /********************************Final*Static**********************************/
    private final static String passengerFile = ".\\src\\main\\java\\org\\example\\files\\PassengerFile.json";
    private final static String adminFile = ".\\src\\main\\java\\org\\example\\files\\AdminFile.json";
    private final static String employeeFile = ".\\src\\main\\java\\org\\example\\files\\EmployeeFile.json";
    private final static String roomFile = ".\\src\\main\\java\\org\\example\\files\\RoomFile.json";
    private final static String bookingsFile = ".\\src\\main\\java\\org\\example\\files\\BookingFile.json";
    /********************************Users**********************************/
    /**
     * Save Passenger in the PassengerFile
     * @param users
     */
    public static void setPassengersToJson(ArrayList<Passenger> users){
        File file = new File(passengerFile);
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, ArrayList.class, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Passenger from JsonFile
     * @return
     */
    public static ArrayList<Passenger> getPassengersFromJson()
    {
        ArrayList<Passenger> users = new ArrayList<>();
        File file = new File(passengerFile);
        try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            users = gson.fromJson(buffer, new TypeToken<ArrayList<Passenger>>()
            {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    /**
     * Save Admin in the AdminFile
     * @param users
     */
    public static void setAdminToJson(ArrayList<Admin> users){
        File file = new File(adminFile);
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, ArrayList.class, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Admin from JsonFile
     * @return
     */
    public static ArrayList<Admin> getAdminFromJson()
    {
        ArrayList<Admin> users = new ArrayList<>();
        File file = new File(adminFile);
        try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            users = gson.fromJson(buffer, new TypeToken<ArrayList<Admin>>()
            {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    /** Save Employee in the EmployeeFile
     * @param users
     */
    public static void setEmployeeToJson(ArrayList<Employee> users){
        File file = new File(employeeFile);
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, ArrayList.class, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Employee from JsonFile
     * @return
     */
    public static ArrayList<Employee> getEmployeeFromJson()
    {
        ArrayList<Employee> users = new ArrayList<>();
        File file = new File(employeeFile);
        try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            users = gson.fromJson(buffer, new TypeToken<ArrayList<Employee>>()
            {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
