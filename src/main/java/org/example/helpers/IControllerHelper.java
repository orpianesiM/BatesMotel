package org.example.helpers;

import org.example.entities.*;

import java.time.DateTimeException;
import java.time.LocalDate;
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
        System.out.print("Ingreso incorrectamente. Desea volver a intentarlo? S/N");
        answ = sc.nextLine().toUpperCase();
        if(answ.equals("S")) return true;
        return false;
    }

    static boolean flowProgram() {
        String answ;
        sc.nextLine();             //cleaned buffer
        System.out.print("Desea continuar en este menú? S/N");
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
            System.out.print("Ingrese el DNI: ");
            dni = sc.nextLine();
            passengerFound = hotel.getPassengerbyDni(dni);
            if (passengerFound != null) {
                System.out.println("Busqueda éxitosa");
                return passengerFound;
            } else {
                System.out.println("El dni que ingresó es INEXISTENTE");
                System.out.print("Quiere volver a intentarlo ? S/N");
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
            System.out.print("Ingrese el dni del pasajero: ");
            dni = sc.nextLine();
            bookingFound = hotel.getBookingByDni(dni);
            if (bookingFound != null) {
                System.out.println("Reserva encontrada");
                return bookingFound;
            } else {
                System.out.println("El dni que fue buscado es INEXISTENTE");
                System.out.print("Quiere volver a intentarlo ? S/N");
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
            System.out.print("Ingrese la habitación: ");
            roomNum = sc.nextInt();
            roomFound = hotel.getRoom(roomNum);
            if (roomFound != null) {
                System.out.println("Habitación encontrada");
                return roomFound;
            } else {
                System.out.println("La habitación es INEXISTENTE");
                System.out.print("Quiere volver a intentarlo ? S/N");
                sc.nextLine();             //cleaned buffer
                flag = sc.nextLine();
            }
        } while (flag.equals("S"));
        return null;
    }

 static LocalDate createLocalDate(){
        int year, month, dayOfMonth;
        LocalDate localDate=null;
        do {
            System.out.print("Año <format:yyyy>: ");
            year = sc.nextInt();
            if (year < LocalDate.now().getYear() || year > 2025) {
                System.out.println("Año incorrecto, vuelva a intentarlo");
            }
        }while(year<2020 || year>2025);

        do{
            System.out.print("Mes <format:m>: ");
            month = sc.nextInt();
            if(month>12 || month<1 || month<LocalDate.now().getMonthValue()){
                System.out.println("Mes incorrecto, vuelva a intentarlo");
            }
        }while(month>12 || month<1 || month<LocalDate.now().getMonthValue());

        do{
            System.out.print("Dia: <format:d>: ");
            dayOfMonth = sc.nextInt();
            if(dayOfMonth<1 || dayOfMonth>31){
                System.out.println("Dia incorrecto, vuelva a intentarlo");
            }
        }while(dayOfMonth<1 || dayOfMonth>31);

        try{
            localDate = LocalDate.of(year,month,dayOfMonth);
        }catch (DateTimeException e){
            System.out.println("No fue posible cargar la fecha, vuelva a intentarlo");
            createLocalDate();
        }
        return localDate;
}

}
