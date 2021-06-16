package org.example.helpers;

import org.example.*;

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
        System.out.println("Ingreso incorrectamente. Desea volver a intentarlo? S/N");
        answ = sc.nextLine().toUpperCase();
        if(answ.equals("S")) return true;
        return false;
    }

    static User searchPassenger (Hotel hotel){ //ToDo ver esto con relacion al passenger
        String flag = null;
        User passengerFound;
        do {
            System.out.println("Ingrese el DNI: ");
            String dni = sc.nextLine();
            passengerFound = hotel.getpassengerbyDni(dni);
            if (passengerFound != null) {
                System.out.println("Busqueda éxitosa");
                return passengerFound;
            } else {
                System.out.println("El dni que ingresó es INEXISTENTE");
                System.out.println("Quiere volver a intentarlo ? S/N");
                flag = sc.nextLine();
            }
        } while (flag.equals("N"));
        return null;
    }

    static Booking searchBooking (Hotel hotel){
        String flag = null;
        Booking bookingFound;
        do {
            System.out.println("Ingrese el dni del pasajero: ");
            String dni = sc.nextLine();
            bookingFound = hotel.getBookingByDni(dni);
            if (bookingFound != null) {
                System.out.println("Reserva encontrada");
                return bookingFound;
            } else {
                System.out.println("El dni que fue buscado es INEXISTENTE");
                System.out.println("Quiere volver a intentarlo ? S/N");
                flag = sc.nextLine();
            }
        } while (flag.equals("N"));
        return null;
    }


    static Room searchRoom (Hotel hotel){
        String flag = null;
        Room roomFound;
        do {
            roomFound = validateRoom(hotel);
            if (roomFound != null) {
                System.out.println("Habitación encontrada");
                return roomFound;
            } else {
                System.out.println("La habitación es INEXISTENTE");
                System.out.println("Quiere volver a intentarlo ? S/N");
                flag = sc.nextLine();
            }
        } while (flag.equals("N"));
        return null;
    }

    static Room validateRoom(Hotel hotel){
        Room roomFound;
        int roomNum;
        do {
            System.out.println("Ingrese la habitación: ");
            roomNum = sc.nextInt();
            if (roomNum==0 || roomNum>hotel.getHotelSize())
                System.out.println("La habitación no existe, vuelva a intentarlo");
        }while(roomNum==0 || roomNum>hotel.getHotelSize());

        roomFound = hotel.getRoom(roomNum);
        return roomFound;
    }
}
