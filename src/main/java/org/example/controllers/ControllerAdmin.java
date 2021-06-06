package org.example.controllers;

import org.example.*;
import org.example.helpers.IControllerHelper;

public class ControllerAdmin implements IControllerHelper{

    public static void viewMenuHotel(){
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*\n");
        System.out.println("*-*-*-*-*-*-*-***Menu Principal****-*-*-*-*-*-*\n");
        System.out.println("1. Habitaciones");
        System.out.println("2. Pasajeros");
        System.out.println("3. Reservas");
        System.out.println("0. Salir");
        System.out.println("Ingrese una opción: ");
    }

    public static void controllerMenuHotel(Hotel hotel){
        boolean flag = false;
        viewMenuHotel();
        do {
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        controllerRooms(hotel);
                        break;
                    case "2":
                        controllerPassengers(hotel);
                        break;
                    case "3":
                        controllerBooking(hotel);
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

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*ROOM*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************CONTROLLERS*******************************************/
    private static void controllerRooms(Hotel hotel) {
        boolean flag=false;
        viewMenuRooms();
        do {
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        controllerRoomList(hotel);
                        controllerRooms(hotel);
                        break;
                    case "2":
                       controllerRoomSearch(hotel);
                       controllerRooms(hotel);
                        break;
                    case "3":
                        controllerRoomStatus(hotel);
                        controllerRooms(hotel);
                        break;
                    case "0":
                        controllerMenuHotel(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = IControllerHelper.messageError();
        } while (flag);
    }

    private static void controllerRoomSearch(Hotel hotel) {
        boolean flag = false;
        do {
            if (IControllerHelper.searchRoom(hotel) != null) {
                viewMenuRoomsSearch();
                String num = sc.nextLine();
                if (IControllerHelper.isInteger(num)) {
                    switch (num) {
                        case "1":
                            System.out.println("*-*-*-*-*-*-*-***Modificar Habitación****-*-*-*-*-*-*\n");
                            controllerRoomAdjust(hotel);
                            break;
                        case "2":
                            System.out.println("*-*-*-*-*-*-*-***Estado Habitación****-*-*-*-*-*-*\n");
                            controllerRoomAdjustStatus(hotel);
                            break;
                        case "3":
                            System.out.println("*-*-*-*-*-*-*-***Agregar consumo****-*-*-*-*-*-*\n");
                            controllerRoomService(hotel);
                            break;
                        default:
                            System.out.println("Ingreso incorrectamente.");
                    }
                } else flag = IControllerHelper.messageError();
            }
        }while (flag) ;
    }

    private static void controllerRoomService(Hotel hotel){
        Booking bookingFound = IControllerHelper.searchBooking(hotel);
        if(bookingFound != null){
            System.out.println("Ingrese el consumo de la habitación <format:$xx.xx>: $");
            bookingFound.addSpentMoney(sc.nextDouble());
            }else System.out.println("La habitación no tiene huéspedes");
        }

    private static void controllerRoomList(Hotel hotel) {
        int i = 0;
        boolean flag = false;
        do {
            viewMenuRoomsList();
            String num = sc.nextLine();
            if (IControllerHelper.isInteger(num)) {
                switch (num) {
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
                    case "3":
                        for (i = 0; i < hotel.getHotelSize(); i++) System.out.println(hotel.getAllRoom(i));
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = IControllerHelper.messageError();
        } while (flag);
    }

    private static void controllerRoomAdjust(Hotel hotel) {
        String option, answer;
        boolean flag = false;
        do{
            Room room = IControllerHelper.searchRoom(hotel);
            if (room != null) {
                System.out.println("*-*-*-*-*-*-*-***Modificar Habitación****-*-*-*-*-*-*\n");
                System.out.println("1. Numero de habitación");
                System.out.println("2. Disponibilidad");
                System.out.println("3. Tipo de habitación");
                System.out.println("0. Salir");
                System.out.println("Seleccione lo que desea cambiar ");
                option = sc.nextLine();
                if (IControllerHelper.isInteger(option)) {
                    switch (option) {
                        case "1":
                            System.out.println("Ingrese nuevo numero de habitación");
                            room.setRoomNumber(sc.nextInt());
                            System.out.println("Datos cambiados con exito! \n" +room);
                            break;
                        case "2":
                            boolean status = room.isAvailable();
                            System.out.println("El estado actual de la habitación es: " + status);
                            System.out.println("Desea cambiarlo? S/N");
                            answer = sc.nextLine().toUpperCase();
                            if (answer.equals("S") && status) room.setAvailable(false);
                            if (answer.equals("S") && !status) room.setAvailable(true);
                            System.out.println("Estado cambiado a : " + room.isAvailable());
                            break;
                        case "3":
                            int type = 0;
                            System.out.println("El tipo de habitación es: " + room.getRoomType());
                            System.out.println("Seleccione un tipo");
                            System.out.println("1. Single");
                            System.out.println("2. Twin");
                            System.out.println("3. Matrimonial");
                            System.out.println("4. Triple");
                            System.out.println("5. Quad");
                            type = sc.nextInt();
                            switch (type) {
                                case 1:
                                    room.setRoomType(RoomType.SINGLE);
                                    break;
                                case 2:
                                    room.setRoomType(RoomType.TWIN);
                                    break;
                                case 3:
                                    room.setRoomType(RoomType.MATRIMONIAL);
                                    break;
                                case 4:
                                    room.setRoomType(RoomType.TRIPLE);
                                    break;
                                case 5:
                                    room.setRoomType(RoomType.QUAD);
                                    break;
                            }
                            break;
                        case "0":
                            controllerRooms(hotel);
                            break;
                        default:
                            System.out.println("Ingreso incorrectamente.");
                    }
                } else flag = IControllerHelper.messageError();
            }
        } while (flag);
    }


    private static void controllerRoomAdjustStatus(Hotel hotel){
        Room roomFound = IControllerHelper.searchRoom(hotel);
        if (roomFound != null) {
            System.out.println(roomFound.toString());
        }
    }

    private static void controllerRoomStatus(Hotel hotel) {
        int rooms = hotel.getHotelSize();
        int ocupated = 0;
        System.out.println("*-*-*-*-*-*-*-***Estado General****-*-*-*-*-*-*\n");
        System.out.println("Habitaciones: " + rooms);
        for (int i = 0; i < rooms; i++) {
            if (!(hotel.getAllRoom.isAvailable())){
                ocupated++;
            }
        }
            System.out.println("Habitaciones ocupadas: " + ocupated);
            System.out.println("Habitaciones desocupadas: " + (rooms - ocupated));
    }

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*ROOM*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************VIEW*******************************************/
    public static void viewMenuRooms(){
        System.out.println("*-*-*-*-*-*-*-***Menu Habitaciones****-*-*-*-*-*-*\n");
        System.out.println("1. Listar habitaciones");
        System.out.println("2. Buscar habitación");
        System.out.println("3. Datos generales del Hotel");
        System.out.println("0. Salir");
    }

    public static void viewMenuRoomsList(){
        System.out.println("*-*-*-*-*-*-*-***Menu Listar Habitaciones****-*-*-*-*-*-*\n");
        System.out.println("2. Habitaciones ocupadas");
        System.out.println("1. Habitaciones desocupadas");
        System.out.println("3. Todas las habitaciones");
    }

    public static void viewMenuRoomsSearch(){
        System.out.println("*-*-*-*-*-*-*-***Acciones sobre la habitación****-*-*-*-*-*-*\n");
        System.out.println("1. Modificar habitación");
        System.out.println("2. Estado de la habitación");
        System.out.println("3. Agregar consumos a la habitación");
    }

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*PASSENGER*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************CONTROLLERS*******************************************/
    private static void controllerPassengers(Hotel hotel){
        boolean flag=false;
        viewMenuPassengers();
        do {
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        hotel.getAllPassenger().forEach(System.out::println);
                        controllerPassengers(hotel);
                        break;
                    case "2":
                        controllerPassengersSearch(hotel);
                        controllerPassengers(hotel);
                        break;
                    case "3":
                        controllerPassengersAdd(hotel);
                        controllerPassengers(hotel);
                        break;
                    case "0":
                        controllerMenuHotel(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = IControllerHelper.messageError();
        } while (flag);
    }

    private static void controllerPassengersAdd(Hotel hotel){
        Passenger passengerNew;
        boolean add = false;
        String controller = null, controllerAdd, name, lastName, email, dni, origin, originAddress;
        int phone;
        System.out.println("*-*-*-*-*-*-*-***Crear Pasajero****-*-*-*-*-*-*\n");
        do{
            System.out.println("INGRESE LOS DATOS");
            System.out.println("Nombre: ");
            name = sc.nextLine();
            System.out.println("Apellido: ");
            lastName = sc.nextLine();
            System.out.println("DNI: ");
            dni = sc.nextLine();
            System.out.println("Email: ");
            email = sc.nextLine();
            System.out.println("Telefono: ");
            phone = sc.nextInt();
            System.out.println("Origen: ");
            origin = sc.nextLine();
            System.out.println("Dirección de origen: ");
            originAddress = sc.nextLine();
            passengerNew = new Passenger(name, lastName, dni, email, phone, origin, originAddress);
            System.out.println(passengerNew);

            System.out.println("Desea agregar al nuevo pasajero ? S/N");
            controllerAdd = sc.nextLine().toUpperCase();
            if(controllerAdd.equals("S")){
                add = hotel.addPassenger(passengerNew);
                if(add) System.out.println("Pasajero creado con exito");
                if(!add) System.out.println("Error al cargar nuevo pasajero");
            }else{
                System.out.println("Desea cargar nuevamente? S/N");
                controller = sc.nextLine().toUpperCase();
            }
        }while(controller.equals("N"));
    }

    private static void controllerPassengersSearch(Hotel hotel) {
        boolean flag = false;
        do {
            Passenger passengerFound = IControllerHelper.searchPassenger(hotel);
            if (passengerFound != null) {
                viewMenuPassengerSearch();
                String num = sc.nextLine();
                if (IControllerHelper.isInteger(num)) {
                    switch (num) {
                        case "1":
                            controllerPassengerSearchAdjust(hotel);
                            controllerPassengers(hotel);
                            break;
                        case "2":
                            System.out.println("Datos: \n" + passengerFound.toString());
                            controllerPassengers(hotel);
                            break;
                        case "3":
                            Booking bookingFound = IControllerHelper.searchBooking(hotel);
                            if(bookingFound!=null) System.out.println(bookingFound.toString());
                            controllerPassengers(hotel);
                            break;
                        case "4":
                            controllerPassengerSearchHistory(hotel);
                            controllerPassengers(hotel);
                            break;
                        case "0":
                            controllerMenuHotel(hotel);
                            break;
                        default:
                            System.out.println("Ingreso incorrectamente.");
                    }
                } else flag = IControllerHelper.messageError();
            }
        }while(flag);
    }

    private static void controllerPassengerSearchAdjust(Hotel hotel) {
        boolean flag = false;
        String option;
        do {
            Passenger passengerFound = IControllerHelper.searchPassenger(hotel);
            if (passengerFound != null) {
                System.out.println("Seleccione atributo a cambiar");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Teléfono");
                System.out.println("4. Email");
                System.out.println("5. DNI");
                option = sc.nextLine();
                if(IControllerHelper.isInteger(option)){
                    switch (option){
                        case "1":
                            System.out.println("Ingrese nuevo nombre");
                            passengerFound.setName(sc.nextLine());
                            break;
                        case "2":
                            System.out.println("Ingrese nuevo apellido");
                            passengerFound.setLastName(sc.nextLine());
                            break;
                        case "3":
                            System.out.println("Ingrese nuevo teléfono");
                            passengerFound.setPhoneNumber(sc.nextInt());
                            break;
                        case "4":
                            System.out.println("Ingrese nuevo email");
                            passengerFound.setEmail(sc.nextLine());
                            break;
                        case "5":
                            System.out.println("Ingrese nuevo dni");
                            passengerFound.setDni(sc.nextLine());
                            break;
                    }
                }else flag=IControllerHelper.messageError();
            }
        }while(flag);
    }

    private static void controllerPassengerSearchHistory(Hotel hotel){
        Passenger passengerFound = IControllerHelper.searchPassenger(hotel);
        if(passengerFound != null){
            if(!(passengerFound.getHistory().isEmpty())){
                passengerFound.getHistory().forEach(System.out::println);
            }else{
                System.out.println("El pasajero" +passengerFound.getName() +" "+ passengerFound.getLastName() +
                        " no tiene historial en el hotel");
            }
        }
    }
    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*PASSENGER*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************VIEW*******************************************/
    public static void viewMenuPassengers(){
        System.out.println("*-*-*-*-*-*-*-***Menu Pasajeros****-*-*-*-*-*-*\n");
        System.out.println("1. Listar Pasajeros");
        System.out.println("2. Buscar Pasajero");
        System.out.println("3. Agregar Pasajero");
        System.out.println("0. Salir");
    }

    public static void viewMenuPassengerSearch(){
        System.out.println("*-*-*-*-*-*-*-***Acciones sobre el pasajero****-*-*-*-*-*-*\n");
        System.out.println("1. Modificar Pasajero");
        System.out.println("2. Ver Pasajero");
        System.out.println("3. Reservas del pasajero");
        System.out.println("4. Ver historial del pasajero");
    }

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*BOOKING*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************CONTROLLERS*******************************************/
    private static void controllerBooking(Hotel hotel) {
        boolean flag = false;
        viewMenuBooking();
        do {
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        controllerBookingDelete(hotel);
                        controllerBooking(hotel);
                        break;
                    case "2":
                        hotel.getAllBooking().forEach(System.out::println);
                        controllerBooking(hotel);
                        break;
                    case "3":
                        Booking booking = IControllerHelper.searchBooking(hotel);
                        if(booking != null) System.out.println("DATOS: " + booking.toString());
                        controllerMenuHotel(hotel);
                        break;
                    case "0":
                        controllerMenuHotel(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            } else flag = IControllerHelper.messageError();
        } while (flag);
    }


    private static void controllerBookingDelete(Hotel hotel){
        String answer=null, answerController=null;
        do {
            Booking booking = IControllerHelper.searchBooking(hotel);
            if (booking!=null) {
                System.out.println("Datos: " + booking.toString());
                System.out.println("\nDesea eliminar la reserva? S/N");
                answer = sc.nextLine().toUpperCase();
                if (answer.equals("S")) {
                    booking.remove(); ///?????????? remuevo segun que
                    System.out.println("Reserva cancelada!");
                }else{
                    System.out.println("Quiere volver a intentarlo? S/N");
                    answerController = sc.nextLine().toUpperCase();
                }
            }
        }while(answerController.equals("S"));
    }

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*BOOKING*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************VIEW*******************************************/
    public static void viewMenuBooking () {
        System.out.println("*-*-*-*-*-*-*-***Menu Reservas****-*-*-*-*-*-*\n");
        System.out.println("1. Eliminar Reserva");
        System.out.println("2. Ver todas las reservas");
        System.out.println("3. Buscar reserva por pasajero");
        System.out.println("0. Salir");
    }

}

