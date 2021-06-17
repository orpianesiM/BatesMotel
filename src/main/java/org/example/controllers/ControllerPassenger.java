package org.example.controllers;

import org.example.entities.*;
import org.example.helpers.IControllerHelper;

import java.time.LocalDate;
import java.util.List;
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

    public static void controllerMenuPrincipal(Hotel hotel, Passenger passenger) {
        boolean flag = true;
        do {
            viewMenuPrincipal();
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        optionNewBooking(hotel, passenger);
                        break;
                    case "2":
                        controllerOptionTwo(hotel, passenger);
                        break;
                    case "3":
                        optionRooms(hotel, passenger);
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
     private static void optionNewBooking(Hotel hotel, Passenger passenger) {
         int roomType, duration, roomNumber;
         String option;
         Booking addNewBooking;
         RoomType type = null;
         LocalDate checkInDate;
         System.out.println("Enter para continuar..");
         sc.nextLine();             //cleaned buffer
         System.out.println("Ingrese el tipo de habitación que quiere reservar: ");
         viewRoomType();
         roomType = sc.nextInt();
         switch (roomType) {
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

         System.out.println("Tipo de habitación: " + type);
         System.out.println("Número: " + roomNumber);
         System.out.println("Check in: " + checkInDate);
         System.out.println("Check out: " + checkInDate.plusDays(duration));
         System.out.println("Enter para continuar..");
         sc.nextLine();             //cleaned buffer
         System.out.println("Desea enviar la reserva? S/N");
         option = sc.nextLine().toUpperCase();

         if(option.equals("S")) {
             addNewBooking = hotel.makeBooking(checkInDate, checkInDate.plusDays(duration), passenger, roomNumber);
             if (addNewBooking != null) {
                 System.out.println("Reserva creada con éxito \n" + addNewBooking.toString());
             } else System.out.println("Error 404");
         }else System.out.println("Reserva cancelada");
     }

    private static int roomPicked(Hotel hotel, RoomType type)
    {
        int option;
        boolean flag=false;

        for (Room variable : hotel.getRoomList()) {
            if (variable.getRoomType() == type) System.out.println(variable.toString());
        }
       do {
            System.out.print("Escriba el numero de la habitación que desea reservar: ");
            option = sc.nextInt();
           for (Room r : hotel.getRoomList()) {
               if (option == r.getRoomNumber()){
                   return option;
               }
               else flag = false;
            }
            if(!flag) System.out.println("Debe ingresar un numero de habitación correcto");
        }while(!flag);

        return option;
    }

     private static void controllerOptionTwo(Hotel hotel, Passenger passenger) {
         boolean flag = true;
         Ticket ticket;
         Booking bookingFound = hotel.getBookingByDni(passenger.getDni());
         if (bookingFound != null) {
             do {
                 viewOptionTwo();
                 System.out.println("Ingrese una opción para continuar");
                 String option = sc.nextLine();
                 if (IControllerHelper.isInteger(option)) {
                     switch (option) {
                         case "1":
                             System.out.println(bookingFound.toString());
                             break;
                         case "2":
                             optionTwoRoomService(hotel, bookingFound, passenger);
                             break;
                         case "3":
                             ticket = hotel.getTicket(passenger.getDni());
                             System.out.println(ticket.toString());
                             break;
                         case "0":
                             controllerMenuPrincipal(hotel, passenger);
                             break;
                         default:
                             System.out.println("Ingreso incorrectamente.");
                     }
                 } else flag = IControllerHelper.messageError();
             } while (flag);
         }else System.out.println("Usted no posee reservas.");
     }

     private static void optionTwoRoomService(Hotel hotel, Booking booking, Passenger passenger) {
         String flag;
         int product;
         do {
             Product.getProducts();
             System.out.println("0. Salir");
             System.out.println("Enter para continuar..");
             sc.nextLine();             //cleaned buffer
             System.out.println("Ingrese el numero de lo que desea pedir: ");
             product = sc.nextInt();
             if (product == 0) hotel.setProductToRoomService(product,booking); //Se precisa también la instancia de booking por parametro.
             if (product == 1) hotel.setProductToRoomService(product,booking);
             if (product == 2) hotel.setProductToRoomService(product,booking);
             if (product == 3) hotel.setProductToRoomService(product,booking);
             else controllerMenuPrincipal(hotel, passenger);

             System.out.println("Enter para continuar..");
             sc.nextLine();             //cleaned buffer
             System.out.println("Desea pedir algo mas? S/N");
             flag = sc.nextLine().toUpperCase();
         }while(flag.equals("S"));
     }

     private static void optionRooms(Hotel hotel, Passenger passenger){
         String controller="p";
         System.out.println("*-*-*-*-*-*-*-***Habitaciones****-*-*-*-*-*-*");
         hotel.getRoomList().forEach(System.out::println);
         System.out.println("Enter para continuar..");
         sc.nextLine();             //cleaned buffer
         System.out.println("Desea reservar alguna habitación? S/N");
         controller = sc.nextLine().toUpperCase();
         if(controller.equals("S")) optionNewBooking(hotel, passenger);
         if(controller.equals("N")) controllerMenuPrincipal(hotel, passenger);
     }
     

    /*******************************************VIEW*******************************************/

    public static void viewRoomType(){
        System.out.println("*-*-*-*-*-*-*-***Habitaciones****-*-*-*-*-*-*");
        System.out.println("1. Single");
        System.out.println("2. Twin");
        System.out.println("3. Matrimonial");
        System.out.println("4. Triple");
        System.out.println("5. Quad");
    }
    public static void viewOptionTwo(){
        System.out.println("*-*-*-*-*-*-*-***Mi Estadía****-*-*-*-*-*-*");
        System.out.println("1. Detalles de mi reserva");
        System.out.println("2. Solicitar Room Service");
        System.out.println("3. Imprimir ticket");
        System.out.println("0. Salir");
    }

}