package org.example.controllers;

import org.example.*;
import org.example.helpers.IControllerHelper;


public class ControllerPassenger implements IControllerHelper{

    public static void viewMenuPrincipal() {
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*\n");
        System.out.println("*-*-*-*-*-*-*-***BIENVENIDO****-*-*-*-*-*-*\n");
        System.out.println("1. Quiero hospedarme");    //newBooking
        System.out.println("2. Ya soy huésped");       //bookingDetails, roomService, myTicket
        System.out.println("3. Conocé Bates Motel");   //rooms
        System.out.println("0. Salir");
    }

    public static void controllerMenuPrincipal(Hotel hotel) {
        boolean flag = false;
        viewMenuPrincipal();
        do {
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
                        //deslogeo ?
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
         int roomType=0;
         Booking addNewBooking;
         RoomType type = null;
         String checkInDate, checkOutDate;
        Passenger passengerFound = IControllerHelper.searchPassenger(hotel);
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
                default:
                    System.out.println("Ingreso incorrectamente.");
            }
            System.out.println("Ingrese el dia de checkIn: ");
            checkInDate = sc.nextLine();
            System.out.println("Ingrese el dia de checkOut: "); //podria ingresar cuantos dias se queda y hacer un plusDays en makeBookig
            checkOutDate = sc.nextLine();

            addNewBooking = hotel.makeBooking(checkInDate, checkOutDate, type, passengerFound);
            if(addNewBooking != null){
                System.out.println("Reserva creada con éxito \n" + addNewBooking.toString());
            }else System.out.println("Error 404");
        }
     }

     private static void controllerOptionTwo(Hotel hotel) {
         boolean flag = false;
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
                             optionTwoRoomService(hotel);
                             break;
                         case "3":
                             //ToDo ticket segun id de booking
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

     private static void optionTwoRoomService(Hotel hotel) {
         String flag;
         do {
             Product.getProducts();
             System.out.println("0. Salir");
             System.out.println("Ingrese el numero de lo que desea pedir: ");
             int product = sc.nextInt();
             if (product == 1) hotel.setProductToRoomService(product, /*instancia de booking*/); //Se precisa también la instancia de booking por parametro.
             if (product == 2) hotel.setProductToRoomService(product);
             if (product == 3) hotel.setProductToRoomService(product);
             if (product == 4) hotel.setProductToRoomService(product);
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

}