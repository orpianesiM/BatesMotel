package org.example.controllers;

import org.example.entities.*;
import org.example.helpers.IControllerHelper;

import java.time.LocalDate;
import java.util.Scanner;


public class ControllerPassenger implements IControllerHelper{

    private static final Scanner sc = new Scanner(System.in);

    public static void viewMenuPrincipal() {
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***BIENVENIDO****-*-*-*-*-*-*");
        System.out.println("1. Quiero hospedarme");    //newBooking
        System.out.println("2. Ya soy huésped");       //bookingDetails, roomService, myTicket
        System.out.println("3. Conocé Bates Motel");   //rooms
        System.out.println("0. Salir");
    }

    public static void controllerMenuPrincipal(Hotel hotel) {
        boolean flag = true;
        do {
            viewMenuPrincipal();
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
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
                        hotel.save();
                        ControllerLogin.login(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = IControllerHelper.messageError();
        } while (flag);
    }

    /*******************************************METHOD*******************************************/
    /*******************************************CONTROLLERS*******************************************/
     private static void optionNewBooking(Hotel hotel){
         int roomType, duration, roomNumber;
         Booking addNewBooking;
         RoomType type = null;
         LocalDate checkInDate;

         System.out.println("Enter para continuar..");
        Passenger passengerFound = IControllerHelper.searchPassenger(hotel);
        if(passengerFound!=null){
            System.out.println("Ingrese el tipo de habitación que quiere reservar: ");
            System.out.println("Enter para continuar..");
            sc.nextLine();             //cleaned buffer
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
                default:
                    System.out.println("Ingreso incorrectamente.");
            }
            sc.nextLine();             //cleaned buffer
            System.out.println("Enter para continuar..");
            roomNumber = roomPicked(hotel, type);
            System.out.println("Ingrese el dia de checkIn: ");
            checkInDate = IControllerHelper.createLocalDate();
            System.out.print("Ingrese cantidad de días a alojarse: ");
            duration = sc.nextInt();

            addNewBooking = hotel.makeBooking(checkInDate, checkInDate.plusDays(duration), passengerFound, roomNumber);
            if(addNewBooking != null){
                System.out.println("Reserva creada con éxito \n" + addNewBooking.toString());
            }else System.out.println("Error 404");
        }
     }

    private static int roomPicked(Hotel hotel, RoomType type)
    {
        int option;

        for (Room variable : hotel.getRoomList()) {
            if (variable.getRoomType() == type) {
                System.out.println(variable.toString());
            }
        }
        System.out.println("Escriba el numero de la habitacion que desea reservar: \n");
        option = sc.nextInt();
        return option;
    }

     private static void controllerOptionTwo(Hotel hotel) {
         boolean flag = true;
         Passenger passengerFound = IControllerHelper.searchPassenger(hotel);
         if (passengerFound != null) {
             viewOptionTwo();
             System.out.println("Ingrese una opción para continuar");
             do {
                 String option = sc.nextLine();
                 if (IControllerHelper.isInteger(option)) {
                     switch (option) {
                         case "1":
                             hotel.getBookingByDni(passengerFound.getDni());
                             break;
                         case "2":
                             optionTwoRoomService(hotel, hotel.getBookingByDni(passengerFound.getDni()));
                             break;
                         case "3":
                             hotel.getTicket(passengerFound.getDni());
                             break;
                         case "0":
                             controllerMenuPrincipal(hotel);
                             break;
                         default:
                             System.out.println("Ingreso incorrectamente.");
                     }
                 } else flag = IControllerHelper.messageError();
             } while (flag);
         }
     }

     private static void optionTwoRoomService(Hotel hotel, Booking booking) {
         String flag;
         do {
             Product.getProducts();
             System.out.println("0. Salir");
             System.out.println("Ingrese el numero de lo que desea pedir: ");
             System.out.println("Enter para continuar..");
             sc.nextLine();             //cleaned buffer
             int product = sc.nextInt();
             if (product == 0) hotel.setProductToRoomService(product,booking); //Se precisa también la instancia de booking por parametro.
             if (product == 1) hotel.setProductToRoomService(product,booking);
             if (product == 2) hotel.setProductToRoomService(product,booking);
             if (product == 3) hotel.setProductToRoomService(product,booking);
             else controllerMenuPrincipal(hotel);

             sc.nextLine();             //cleaned buffer
             System.out.println("Desea pedir algo mas? S/N");
             flag = sc.nextLine().toUpperCase();
         }while(flag.equals("S"));
     }

     private static void optionRooms(Hotel hotel){
         String controller;
         System.out.println("*-*-*-*-*-*-*-***Habitaciones****-*-*-*-*-*-*");
         hotel.getRoomList().forEach(System.out::println);
         System.out.println("Enter para continuar..");
         sc.nextLine();             //cleaned buffer
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

}