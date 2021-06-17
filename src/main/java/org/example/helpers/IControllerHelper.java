package org.example.helpers;

import org.example.entities.*;

import java.util.Scanner;

public interface IControllerHelper {

    Scanner sc = new Scanner(System.in);

    static boolean isInteger (String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean messageError () {
        String answ;
        sc.nextLine();             //cleaned buffer
        System.out.println("Ingreso incorrectamente. Desea volver a intentarlo? S/N");
        answ = sc.nextLine().toUpperCase();
        if(answ.equals("S")) return true;
        return false;
    }

    static boolean flowProgram() {
        String answ;
        sc.nextLine();             //cleaned buffer
        System.out.println("Desea continuar en este menú? S/N");
        answ = sc.nextLine().toUpperCase();
        if(answ.equals("S")) return true;
        return false;
    }

    static Passenger searchPassenger (Hotel hotel){
        String flag = null;
        String dni;
        Passenger passengerFound;
        do {
            sc.nextLine();             //cleaned buffer
            System.out.println("Ingrese el DNI: ");
            dni = sc.nextLine();
            passengerFound = hotel.getPassengerbyDni(dni);
            if (passengerFound != null) {
                System.out.println("Busqueda éxitosa");
                return passengerFound;
            } else {
                System.out.println("El dni que ingresó es INEXISTENTE");
                System.out.println("Quiere volver a intentarlo ? S/N");
                sc.nextLine();             //cleaned buffer
                flag = sc.nextLine();
            }
        } while (flag.equals("S"));
        return null;
    }

    static Booking searchBooking (Hotel hotel){
        String flag = null;
        String dni;
        Booking bookingFound;
        do {
            sc.nextLine();             //cleaned buffer
            System.out.println("Ingrese el dni del pasajero: ");
            dni = sc.nextLine();
            bookingFound = hotel.getBookingByDni(dni);
            if (bookingFound != null) {
                System.out.println("Reserva encontrada");
                return bookingFound;
            } else {
                System.out.println("El dni que fue buscado es INEXISTENTE");
                System.out.println("Quiere volver a intentarlo ? S/N");
                sc.nextLine();             //cleaned buffer
                flag = sc.nextLine();
            }
        } while (flag.equals("S"));
        return null;
    }


    static Room searchRoom (Hotel hotel){
        String flag = null;
        int roomNum;
        Room roomFound;
        do {
            sc.nextLine();             //cleaned buffer
            System.out.println("Ingrese la habitación: ");
            roomNum = sc.nextInt();
            roomFound = hotel.getRoom(roomNum);
            if (roomFound != null) {
                System.out.println("Habitación encontrada");
                return roomFound;
            } else {
                System.out.println("La habitación es INEXISTENTE");
                System.out.println("Quiere volver a intentarlo ? S/N");
                sc.nextLine();             //cleaned buffer
                flag = sc.nextLine();
            }
        } while (flag.equals("S"));
        return null;
    }

}
