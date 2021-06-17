package org.example.controllers;

import org.example.entities.*;
import org.example.helpers.IControllerHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class ControllerEmployee implements IControllerHelper {

    private static final Scanner sc = new Scanner(System.in);

    public static void viewMenuEmployee(){
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***Menu Principal****-*-*-*-*-*");
        System.out.println("1. Realizar check in");
        System.out.println("2. Realizar check out");
        System.out.println("3. Info. habitaciones");
        System.out.println("4. Info. reservas");
        System.out.println("5. Info. pasajeros");
        System.out.println("0. Salir");
    }

    public static void controllerMenuEmployee(Hotel hotel){
        boolean flag = true;
        do {
            viewMenuEmployee();
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        controllerCheckIn(hotel);
                        break;
                    case "2":
                        controllerCheckOut(hotel);
                        break;
                    case "3":
                        controllerMenuRoom(hotel);
                        break;
                    case "4":
                        controllerMenuBooking(hotel);
                        break;
                    case "5":
                        controllerMenuPassenger(hotel);
                        break;
                    case "0":
                        hotel.save();
                        ControllerLogin.login(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = IControllerHelper.messageError();
        }while(flag);
    }

    /*******************************************CHECK IN*******************************************/
    private static void controllerCheckIn(Hotel hotel){
        boolean flag = false;
        Booking bookingToCheckIn = IControllerHelper.searchBooking(hotel);
        if(bookingToCheckIn != null && bookingToCheckIn.getCheckInDate().equals(LocalDate.now())){
            flag = hotel.checkIn(bookingToCheckIn);

        }else System.out.println("No se puede realizar check in");

        if(flag) System.out.println("Check in realizado con éxito!");
    }

    /*******************************************CHECK OUT*******************************************/
    private static void controllerCheckOut(Hotel hotel){
        boolean flag;
        Booking bookingToCheckOut = IControllerHelper.searchBooking(hotel);
        if(bookingToCheckOut!=null) {
            boolean available = bookingToCheckOut.getBookedRoom().isAvailable();
            if (!available) {
                flag = hotel.checkOut(bookingToCheckOut);
                if (flag) System.out.println("La habitación fue liberada");
                else System.out.println("ERROR. La habitación no esta ocupada");
            }else System.out.println("No ha sido posible realizar checkout. Compruebe la fecha.");
        }
    }

    /*******************************************PASSENGER*******************************************/
    private static void controllerMenuPassenger(Hotel hotel) {
        boolean flag = false;
        do {
            viewMenuPassenger();
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        Passenger passengerFound = IControllerHelper.searchPassenger(hotel);
                        if(passengerFound != null) System.out.println(passengerFound.toString());
                        break;
                    case "2":
                        hotel.getPassengerList().forEach(System.out::println);
                        break;
                    case "0":
                        controllerMenuEmployee(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = IControllerHelper.messageError();
        }while(flag);
    }

    /*******************************************ROOM*******************************************/
    private static void controllerMenuRoom(Hotel hotel){
        boolean flag = false;
        do {
            viewMenuRoom();
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        for (Room variable : hotel.getRoomList()){
                            if (!(variable.isAvailable())) System.out.println(variable.toString());
                        }
                        break;
                    case "2":
                        for (Room variable : hotel.getRoomList()){
                            if (variable.isAvailable()) System.out.println(variable.toString());
                        }
                        break;
                    case "3":
                        for (Room variable : hotel.getRoomList()) System.out.println(variable.toString());
                        break;
                    case "0":
                        controllerMenuEmployee(hotel);
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = IControllerHelper.messageError();
        }while(flag);
    }

    /*******************************************BOOKING*******************************************/
    private static void controllerMenuBooking(Hotel hotel){
        boolean flag = false;
        do {
            viewMenuBooking();
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        showBookingFromRoom(hotel);
                        break;
                    case "2":
                        hotel.getBookingList().forEach(System.out::println);
                        break;
                    case "0":
                        controllerMenuEmployee(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = IControllerHelper.messageError();
        }while(!flag);
    }

    private static void showBookingFromRoom(Hotel hotel){
        List<Booking> bookingList = null;
        Room roomFound = IControllerHelper.searchRoom(hotel);
        bookingList = hotel.getBookingByRoom(roomFound);
        if(bookingList!=null) bookingList.forEach(System.out::println);
        else System.out.println("La habitación no tiene reservas.");
    }

    /*******************************************VIEW*******************************************/
    public static void viewMenuPassenger(){
        System.out.println("*-*-*-*-*-*-*-***Pasajeros***-*-*-*-*-*-*");
        System.out.println("1. Buscar un pasajero");
        System.out.println("2. Listar todos los pasajeros");
        System.out.println("0. Salir");
    }

    public static void viewMenuRoom(){
        System.out.println("*-*-*-*-*-*-*-***Habitaciones***-*-*-*-*-*-*");
        System.out.println("1. Listar habitaciones ocupadas");
        System.out.println("2. Listar habitaciones desocupadas");
        System.out.println("3. Listar todas las habitaciones");
        System.out.println("0. Salir");
    }

    public static void viewMenuBooking(){
        System.out.println("*-*-*-*-*-*-*-***Reservas***-*-*-*-*-*-*");
        System.out.println("1. Buscar reserva por habitación");
        System.out.println("2. Listar todas las reservas");
        System.out.println("0. Salir");
    }
    /*******************************************HELPERS*******************************************/

}
