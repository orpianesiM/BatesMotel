package org.example.controllers;

import org.example.*;

import java.util.Scanner;

public class ControllerPassenger {

    private static final Scanner sc = new Scanner(System.in);

    public static void viewMenuPrincipal() {
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*\n");
        System.out.println("*-*-*-*-*-*-*-***BIENVENIDO****-*-*-*-*-*-*\n");
        System.out.println("1. Quiero hospedarme");    //newBooking
        System.out.println("2. Ya soy huésped");      //bookingDetails, roomService, myTicket
        System.out.println("3. Conocé Bates Motel"); //rooms
        System.out.println("0. Salir");
    }

    private static void controllerMenuPrincipal(Hotel hotel) {
        boolean flag = false;
        viewMenuPrincipal();
        do {
            String option = sc.nextLine();
            if (isInteger(option)) {
                switch (option) {
                    case "1":
                        optionNewBooking(hotel);
                        break;
                    case "2":
                        controllerOptionTwo(hotel);
                        break;
                    case "3":
                        optionRooms(hotel);
                        break;
                    case "0":
                        //deslogeo ?
                        break;
                }
            } else flag = messageError();
        } while (flag);
    }

    /*******************************************METHOD*******************************************/
    /*******************************************CONTROLLERS*******************************************/
     private static void optionNewBooking(Hotel hotel){
         int roomType=0;
         Booking addNewBooking;
         RoomType type = null;
         String checkInDate, checkOutDate;
        Passenger passengerFound = searchPassenger(hotel);
        if(passengerFound!=null){
            System.out.println("Ingrese el tipo de habitación que quiere reservar: ");
            viewRoomType();
            roomType = sc.nextInt();
            switch (roomType){
                case 1:
                    type = RoomType.SINGLE;
                    break;
                case 2:
                    type = RoomType.TWIN;
                    break;
                case 3:
                    type = RoomType.MATRIMONIAL;
                    break;
                case 4:
                    type = RoomType.TRIPLE;
                    break;
                case 5:
                    type = RoomType.QUAD;
                    break;
            }
            System.out.println("Ingrese el dia de checkIn: ");
            checkInDate = sc.nextLine();
            System.out.println("Ingrese el dia de checkOut: ");
            checkOutDate = sc.nextLine();

            addNewBooking = hotel.makeBooking(checkInDate, checkOutDate, type, passengerFound);
            if(addNewBooking != null){
                System.out.println("Reserva creada con éxito \n" + addNewBooking.toString());
            }else System.out.println("Error 404");
        }
     }

     private static void controllerOptionTwo(Hotel hotel) {
         boolean flag = false;
         Passenger passengerFound = searchPassenger(hotel);
         if (passengerFound != null) {
             viewOptionTwo();
             System.out.println("Ingrese una opción para continuar");
             do {
                 String option = sc.nextLine();
                 if (isInteger(option)) {
                     switch (option) {
                         case "1":
                             hotel.getBookingByDni(passengerFound); //ToDo
                             break;
                         case "2":
                             optionTwoRoomService(hotel);
                             break;
                         case "3":
                             //ToDo ticket
                             break;
                         case "0":
                             controllerMenuPrincipal(hotel);
                             break;
                     }
                 } else flag = messageError();
             } while (flag);
         }
     }

     private static void optionTwoRoomService(Hotel hotel) {
         String flag;
         do {
             Product.getProducts();
             System.out.println("0. Salir");
             System.out.println("Ingrese el numero de lo que desea pedir: ");
             int product = sc.nextInt();
             if (product == 1) setProductToRoomService(product); //ToDo
             if (product == 2) setProductToRoomService(product);
             if (product == 3) setProductToRoomService(product);
             if (product == 4) setProductToRoomService(product);
             else controllerMenuPrincipal(hotel);

             System.out.println("Desea pedir algo mas? S/N");
             flag = sc.nextLine().toUpperCase();
         }while(flag.equals("S"));
     }

     private static void optionRooms(Hotel hotel){
         String controller;
         System.out.println("*-*-*-*-*-*-*-***Habitaciones****-*-*-*-*-*-*\n");
         hotel.seeRooms();
         System.out.println("Desea reservar alguna habitación? S/N");
         controller = sc.nextLine().toUpperCase();
         if(controller.equals("S")) optionNewBooking(hotel);
         if(controller.equals("N")) controllerMenuPrincipal(hotel);
     }
     

    /*******************************************VIEW*******************************************/
    public static void viewRoomType(){
        System.out.println("1. Single");
        System.out.println("2. Twin");
        System.out.println("3. Matrimonial");
        System.out.println("4. Triple");
        System.out.println("5. Quad");
    }
    public static void viewOptionTwo(){
        System.out.println("1. Detalles de mi reserva");
        System.out.println("2. Solicitar Room Service");
        System.out.println("3. Imprimir ticket");
        System.out.println("0. Salir");
    }

    /*******************************************HELPERS*******************************************/
    private static boolean messageError() {
        String answ;
        System.out.println("Ingreso incorrectamente. Desea volver a intentarlo? S/N");
        answ = sc.nextLine().toUpperCase();
        if (answ.equals("S")) return true;
        return false;
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static Passenger searchPassenger (Hotel hotel){
        String flag = null;
        Passenger passengerFound;
        do {
            System.out.println("Ingrese su DNI para continuar: ");
            String dni = sc.nextLine();
            passengerFound = hotel.getPassenger(dni);
            if (passengerFound != null) {
                System.out.println("Búsqueda exitosa");
                return passengerFound;
            } else {
                System.out.println("El dni que ingreso es INEXISTENTE");
                System.out.println("Quiere volver a intentarlo ? S/N");
                flag = sc.nextLine();
            }
        } while (flag.equals("N"));
        return null;
    }
}