package org.example.controllers;

import org.example.*;
import org.example.helpers.IControllerHelper;

import java.time.LocalDateTime;
import java.util.List;


public class ControllerEmployee implements IControllerHelper {

    public static void viewMenuEmployee(){
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*\n");
        System.out.println("*-*-*-*-*-*-*-***Menu Principal****-*-*-*-*-*-*\n");
        System.out.println("1. Realizar check in");
        System.out.println("2. Realizar check out");
        System.out.println("3. Info. habitaciones");
        System.out.println("4. Info. reservas");
        System.out.println("5. Info. pasajeros");
        System.out.println("0. Salir");
    }

    public static void controllerMenuEmployee(Hotel hotel){
        boolean flag = false;
        viewMenuEmployee();
        do {
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
                        //deslogeo ?
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
        if(bookingToCheckIn != null && bookingToCheckIn.getCheckInDate().equals(LocalDateTime.now())){
            flag = hotel.checkIn(bookingToCheckIn);
            bookingToCheckIn.setBookingState(BookingState.INITIATED);   // linea agregada para cambiar el estado del booking a INITIATED
        }else System.out.println("No se puede realizar check in");
        if(flag) System.out.println("Check in realizado con éxito!");
    }

    /*******************************************CHECK OUT*******************************************/
    private static void controllerCheckOut(Hotel hotel){
        boolean flag;
        Room roomToCheckOut = IControllerHelper.searchRoom(hotel);
        if(roomToCheckOut!=null) {
            boolean available = roomToCheckOut.isAvailable();
            if (!available) {
                flag = hotel.checkOut(roomToCheckOut);
                if (flag) System.out.println("La habitación fue liberada");
                else System.out.println("ERROR. La habitación no esta ocupada");
            }
        }
    }

    /*******************************************PASSENGER*******************************************/
    private static void controllerMenuPassenger(Hotel hotel) {
        boolean flag = false;
        viewMenuPassenger();
        do {
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        Passenger passengerFounded = IControllerHelper.searchPassenger(hotel);
                        if(passengerFounded != null) System.out.println(passengerFounded.toString());
                        break;
                    case "2":
                        hotel.getAllPassengers().forEach(System.out::println);
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
        int i;
        viewMenuRoom();
        do {
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        for (i = 0; i < hotel.getHotelSize(); i++) {
                            if (!(hotel.getAllRoom(i).isAvailable())) System.out.println(hotel.getAllRoom(i));
                        }
                        break;
                    case "2":
                        for (i = 0; i < hotel.getHotelSize(); i++) {
                            if (hotel.getAllRoom(i).isAvailable()) System.out.println(hotel.getAllRoom(i));
                        }
                        break;
                    case "0":
                        for (i = 0; i < hotel.getHotelSize(); i++) System.out.println(hotel.getAllRoom(i));
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = IControllerHelper.messageError();
        }while(flag);
    }

    /*******************************************BOOKING*******************************************/
    private static void controllerMenuBooking(Hotel hotel){
        boolean flag = false;
        viewMenuBooking();
        do {
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        showBookingFromRoom(hotel);
                        break;
                    case "2":
                        for(int i=0; i<hotel.getHotelSize(); i++){
                          //  if(!(hotel.getAllBooking)) hotel.getBookings(i).forEach(sout)
                                //ver bookingState
                        }
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

    private static void showBookingFromRoom(Hotel hotel){
        List<Booking> bookingList = null;
        Room roomFound = IControllerHelper.searchRoom(hotel);
        bookingList = hotel.getBookingByRoom(roomFound); //ToDo: metodo que busque reservas teniendo un Room por parametro
        if(bookingList!=null) bookingList.forEach(System.out::println);
        else System.out.println("La habitación no tiene reservas.");
    }

    /*******************************************VIEW*******************************************/
    public static void viewMenuPassenger(){
        System.out.println("*-*-*-*-*-*-*-***Pasajeros***-*-*-*-*-*-*\n");
        System.out.println("1. Buscar un pasajero");
        System.out.println("2. Ver todos los pasajeros");
        System.out.println("0. Salir");
    }

    public static void viewMenuRoom(){
        System.out.println("*-*-*-*-*-*-*-***Habitaciones***-*-*-*-*-*-*\n");
        System.out.println("1. Listar habitaciones ocupadas");
        System.out.println("2. Listar habitaciones desocupadas");
        System.out.println("3. Listar todas las habitaciones");
    }

    public static void viewMenuBooking(){
        System.out.println("*-*-*-*-*-*-*-***Reservas***-*-*-*-*-*-*\n");
        System.out.println("1. Buscar reserva por habitación");
        System.out.println("2. Listar todas las reservas");
    }
    /*******************************************HELPERS*******************************************/

}
