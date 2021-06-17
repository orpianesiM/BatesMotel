package org.example.controllers;

import org.example.entities.*;
import org.example.helpers.IControllerHelper;

import java.util.Locale;
import java.util.Scanner;

public class ControllerAdmin implements IControllerHelper
{
    private static final Scanner sc = new Scanner(System.in);

    public static void viewMenuHotel()
    {
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***Menu Principal****-*-*-*-*-*");
        System.out.println("1. Habitaciones");
        System.out.println("2. Pasajeros");
        System.out.println("3. Reservas");
        System.out.println("4. Crear nuevo usuario");
        System.out.println("0. Salir");
        System.out.println("Ingrese una opción: ");
    }

    public static void controllerMenuHotel(Hotel hotel)
    {
        boolean flag = true;
        do {
            viewMenuHotel();
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
                    case "4":
                        controllerUser(hotel);
                        break;
                    case "0":
                        hotel.save();
                        ControllerLogin.login(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            }
            else flag = IControllerHelper.messageError();
        } while (flag);
    }

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*ROOM*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************CONTROLLERS*******************************************/
    private static void controllerRooms(Hotel hotel)
    {
        boolean flag = true;
        do {
            viewMenuRooms();
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        controllerRoomList(hotel);
                        break;
                    case "2":
                        controllerRoomSearch(hotel);
                        break;
                    case "3":
                        controllerRoomStatus(hotel);
                        break;
                    case "0":
                        controllerMenuHotel(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            }
            else flag = IControllerHelper.messageError();
        } while (flag);
    }

    private static void controllerRoomSearch(Hotel hotel)
    {
        String num;
        Room roomFound = IControllerHelper.searchRoom(hotel);
        boolean flag = true;
        do {
            if (roomFound != null) {
                viewMenuRoomsSearch();
                num = sc.nextLine();
                if (IControllerHelper.isInteger(num)) {
                    switch (num) {
                        case "1":
                            controllerRoomAdjust(hotel, roomFound);
                            break;
                        case "2":
                            controllerRoomAdjustStatus(roomFound);
                            break;
                        case "3":
                            controllerRoomService(hotel);
                            break;
                        case "0":
                            controllerRooms(hotel);
                            break;
                        default:
                            System.out.println("Ingreso incorrectamente.");
                    }
                }
                else flag = IControllerHelper.messageError();
            }
        } while (flag);
    }

    private static void controllerRoomService(Hotel hotel)
    {
        System.out.println("*-*-*-*-*-*-*-***Agregar consumo****-*-*-*-*-*-*");
        Booking bookingFound = IControllerHelper.searchBooking(hotel);
        if (bookingFound != null) {
            System.out.println("Ingrese el consumo de la habitación <format:$xx.xx>: $");
            System.out.println("Enter para continuar..");
            sc.nextLine();             //cleaned buffer
            bookingFound.setSpentMoney(sc.nextDouble());
        }
        else System.out.println("La habitación no tiene huéspedes");
    }

    private static void controllerRoomList(Hotel hotel)
    {
        int i = 0;
        boolean flag = true;
        do {
            viewMenuRoomsList();
            String num = sc.nextLine();
            if (IControllerHelper.isInteger(num)) {
                switch (num) {
                    case "1":
                        for (Room variable : hotel.getRoomList()) {
                            if (!(variable.isAvailable())) System.out.println(variable.toString());
                        }
                        break;
                    case "2":
                        for (Room variable : hotel.getRoomList()) {
                            if (variable.isAvailable()) System.out.println(variable.toString());
                        }
                        break;
                    case "3":
                        for (Room variable : hotel.getRoomList()) System.out.println(variable.toString());
                        break;
                    case "0":
                        controllerRooms(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            }
            else flag = IControllerHelper.messageError();
        } while (flag);
    }

    private static void controllerRoomAdjust(Hotel hotel, Room room)
    {
        String option, answer;
        boolean flag = true;
        do {
            if (room != null) {
                System.out.println("*-*-*-*-*-*-*-***Modificar Habitación****-*-*-*-*-*-*");
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
                            System.out.println("Datos cambiados con exito! \n" + room);
                            break;
                        case "2":
                            boolean status = room.isAvailable();
                            System.out.println("El estado actual de la habitación es: " + status);
                            System.out.println("Desea cambiarlo? S/N");
                            answer = sc.nextLine().toUpperCase();
                            if (answer.equals("S") && status) room.setAvailable(false);
                            if (answer.equals("S") && !status) room.setAvailable(true);
                            System.out.println("Estado actual: " + room.isAvailable());
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
                            System.out.println("Enter para continuar..");
                            sc.nextLine();             //cleaned buffer
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
                }
                else flag = IControllerHelper.messageError();
            }
        } while (flag);
    }


    private static void controllerRoomAdjustStatus(Room room)
    {
        System.out.println("*-*-*-*-*-*-*-***Estado Habitación****-*-*-*-*-*-*");
        if (room != null) {
            System.out.println(room.toString());
        }
    }

    private static void controllerRoomStatus(Hotel hotel)
    {
        int rooms = hotel.getHotelSize();
        int available = 0;
        System.out.println("*-*-*-*-*-*-*-***Estado General****-*-*-*-*-*-*");
        System.out.println("Habitaciones: " + rooms);
        for (Room variable : hotel.getRoomList()) {
            if (variable.isAvailable()) {
                available++;
            }
        }
        System.out.println("Habitaciones ocupadas: " +(rooms - available));
        System.out.println("Habitaciones desocupadas: " + available);
    }

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*ROOM*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************VIEW*******************************************/
    public static void viewMenuRooms()
    {
        System.out.println("*-*-*-*-*-*-*-***Menu Habitaciones****-*-*-*-*-*-*");
        System.out.println("1. Listar habitaciones");
        System.out.println("2. Buscar habitación");
        System.out.println("3. Datos generales del Hotel");
        System.out.println("0. Salir");
    }

    public static void viewMenuRoomsList()
    {
        System.out.println("*-*-*-*-*-*-*-***Menu Listar Habitaciones***-*-*-*-*-*-*");
        System.out.println("1. Habitaciones ocupadas");
        System.out.println("2. Habitaciones desocupadas");
        System.out.println("3. Todas las habitaciones");
        System.out.println("0. Salir");
    }

    public static void viewMenuRoomsSearch()
    {
        System.out.println("*-*-*-*-*-*-***Acciones sobre la habitación***-*-*-*-*-*");
        System.out.println("1. Modificar habitación");
        System.out.println("2. Estado de la habitación");
        System.out.println("3. Agregar consumos a la habitación");
        System.out.println("0. Salir");
    }

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*PASSENGER*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************CONTROLLERS*******************************************/
    private static void controllerPassengers(Hotel hotel)
    {
        boolean flag = true;
        do {
            viewMenuPassengers();
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        hotel.getPassengerList().forEach(System.out::println);
                        break;
                    case "2":
                        controllerPassengersSearch(hotel);
                        break;
                    case "3":
                        controllerPassengersAdd(hotel);
                        break;
                    case "0":
                        controllerMenuHotel(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            }
            else flag = IControllerHelper.messageError();
        } while (flag);
    }

    private static void controllerPassengersAdd(Hotel hotel)
    {
        Passenger passengerNew;
        String controller = "N";
        String controllerAdd, name, lastName, email, dni, origin, originAddress;
        long phone;
        System.out.println("*-*-*-*-*-*-*-***Crear Pasajero****-*-*-*-*-*-*");
        do {
            System.out.println("Enter para continuar..");
            sc.nextLine();             //cleaned buffer
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
            phone = sc.nextLong();
            sc.nextLine();             //cleaned buffer
            System.out.println("Origen: ");
            origin = sc.nextLine();
            System.out.println("Dirección de origen: ");
            originAddress = sc.nextLine();
            passengerNew = new Passenger(name, lastName, dni, email, phone, origin, originAddress);
            System.out.println(passengerNew);

            System.out.println("Enter para continuar..");
            sc.nextLine();             //cleaned buffer
            System.out.println("Desea agregar al nuevo pasajero ? S/N");
            controllerAdd = sc.nextLine().toUpperCase();
            if (controllerAdd.equals("S")) {
                if (hotel.addPassenger(passengerNew))
                {
                    System.out.println("Pasajero creado con exito");
                }
                else
                    System.out.println("Error al cargar nuevo pasajero");

            }
            else {
                System.out.println("Desea cargar nuevamente? S/N");
                sc.nextLine();             //cleaned buffer
                controller = sc.nextLine().toUpperCase();
            }
        } while (controller.equals("S"));
    }

    private static void controllerPassengersSearch(Hotel hotel) {
        boolean flag = true;
        Passenger passengerFound = IControllerHelper.searchPassenger(hotel);
        if (passengerFound != null) {
            do {
                viewMenuPassengerSearch();
                String num = sc.nextLine();
                if (IControllerHelper.isInteger(num)) {
                    switch (num) {
                        case "1":
                            controllerPassengerSearchAdjust(passengerFound);
                            break;
                        case "2":
                            System.out.println("Datos: \n" + passengerFound.toString());
                            break;
                        case "3":
                            Booking bookingFound = IControllerHelper.searchBooking(hotel);
                            if (bookingFound != null){
                                System.out.println(bookingFound.toString());
                            }else System.out.println("El dni buscado no tiene reservas");
                            break;
                        case "4":
                            controllerPassengerSearchHistory(passengerFound);
                            break;
                        case "0":
                            controllerMenuHotel(hotel);
                            break;
                        default:
                            System.out.println("Ingreso incorrectamente.");
                    }
                } else flag = IControllerHelper.messageError();
                flag = IControllerHelper.flowProgram();
            } while (flag);
        }
    }

    private static void controllerPassengerSearchAdjust(Passenger passengerFound)
    {
        String flag = null;
        String option;
        do {
            if (passengerFound != null) {
                System.out.println("Seleccione atributo a cambiar");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Teléfono");
                System.out.println("4. Email");
                System.out.println("5. DNI");
                option = sc.nextLine();
                sc.nextLine();             //cleaned buffer
                if (IControllerHelper.isInteger(option)) {
                    switch (option) {
                        case "1":
                            System.out.println("Ingrese nuevo nombre");
                            passengerFound.setName(sc.nextLine());
                            System.out.println("Modificación exitosa");
                            break;
                        case "2":
                            System.out.println("Ingrese nuevo apellido");
                            passengerFound.setLastName(sc.nextLine());
                            System.out.println("Modificación exitosa");
                            break;
                        case "3":
                            System.out.println("Ingrese nuevo teléfono");
                            passengerFound.setPhoneNumber(sc.nextInt());
                            System.out.println("Modificación exitosa");
                            break;
                        case "4":
                            System.out.println("Ingrese nuevo email");
                            passengerFound.setEmail(sc.nextLine());
                            System.out.println("Modificación exitosa");
                            break;
                        case "5":
                            System.out.println("Ingrese nuevo dni");
                            passengerFound.setDni(sc.nextLine());
                            System.out.println("Modificación exitosa");
                            break;
                    }
                }
            }
            sc.nextLine();             //cleaned buffer
            System.out.println("Quiere cambiar otro atributo? S/N");
            flag = sc.nextLine().toUpperCase();
        } while (flag.equals("S"));
    }

    private static void controllerPassengerSearchHistory(Passenger passengerFound)
    {
        if (passengerFound != null) {
            if (!(passengerFound.getHistory().isEmpty())) {
                passengerFound.getHistory().forEach(System.out::println);
            }
            else {
                System.out.println("El pasajero" + passengerFound.getName() + " " + passengerFound.getLastName() +
                        " no tiene historial en el hotel");
            }
        }
    }
    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*PASSENGER*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************VIEW*******************************************/
    public static void viewMenuPassengers()
    {
        System.out.println("*-*-*-*-*-*-*-***Menu Pasajeros****-*-*-*-*-*-*");
        System.out.println("1. Listar Pasajeros");
        System.out.println("2. Buscar Pasajero");
        System.out.println("3. Agregar Pasajero");
        System.out.println("0. Salir");
    }

    public static void viewMenuPassengerSearch()
    {
        System.out.println("*-*-*-*-*-*-*-***Acciones sobre el pasajero****-*-*-*-*-*-*");
        System.out.println("1. Modificar Pasajero");
        System.out.println("2. Ver Pasajero");
        System.out.println("3. Reservas del pasajero");
        System.out.println("4. Ver historial del pasajero");
    }

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*BOOKING*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************CONTROLLERS*******************************************/
    private static void controllerBooking(Hotel hotel)
    {
        boolean flag = true;
        do {
            viewMenuBooking();
            String option = sc.nextLine();
            if (IControllerHelper.isInteger(option)) {
                switch (option) {
                    case "1":
                        controllerBookingDelete(hotel);
                        break;
                    case "2":
                        hotel.getBookingList().forEach(System.out::println);
                        flag = true;
                        break;
                    case "3":
                        Booking booking = IControllerHelper.searchBooking(hotel);
                        if (booking != null){
                            System.out.println("DATOS: " + booking.toString());
                        }else System.out.println("El pasajero buscado no contiene reservas");
                        break;
                    case "0":
                        controllerMenuHotel(hotel);
                        break;
                    default:
                        System.out.println("Ingreso incorrectamente.");
                }
            }else flag = IControllerHelper.messageError();
        } while (flag);
    }


    private static void controllerBookingDelete(Hotel hotel)
    {
        String answer = null, answerController = "N";
        do {
            Booking booking = IControllerHelper.searchBooking(hotel);
            if (booking != null) {
                System.out.println("Datos: " + booking.toString());
                sc.nextLine();             //cleaned buffer
                System.out.println("\nDesea eliminar la reserva? S/N");
                answer = sc.nextLine().toUpperCase();
                if (answer.equals("S")) {
                    if (hotel.removeBooking(booking)) {
                        System.out.println("Reserva cancelada!");
                    }
                }
                else {
                    sc.nextLine();             //cleaned buffer
                    System.out.println("Quiere volver a intentarlo? S/N");
                    answerController = sc.nextLine().toUpperCase();
                }
            }
        } while (answerController.equals("S"));
    }

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*BOOKING*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************VIEW*******************************************/
    public static void viewMenuBooking()
    {
        System.out.println("*-*-*-*-*-*-*-***Menu Reservas****-*-*-*-*-*-*");
        System.out.println("1. Eliminar Reserva");
        System.out.println("2. Ver todas las reservas");
        System.out.println("3. Buscar reserva por pasajero");
        System.out.println("0. Salir");
    }
    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*USER*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    private static void controllerUser(Hotel hotel){
        Employee employee;
        Admin admin;
        int type;
        String controller = "N";
        String name, lastName, email, dni, user, password;
        long phone;
        System.out.println("*-*-*-*-*-*-*-***Crear Usuario****-*-*-*-*-*-*");
        do {
            System.out.println("Enter para continuar..");
            sc.nextLine();             //cleaned buffer
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
            phone = sc.nextLong();
            sc.nextLine();             //cleaned buffer
            System.out.println("Usuario");
            user = sc.nextLine();
            System.out.println("Password");
            password = sc.nextLine();
            System.out.println("Ingrese 1.Empleado o 2.Admin, según permisos que tendrá");
            type = sc.nextInt();

            if(type==1) {
                employee = new Employee(name, lastName, dni, email, user, password, phone);
                hotel.addEmployee(employee);
            }
            if(type==2) {
                admin = new Admin(name, lastName, dni, email, user, password, phone);
                hotel.addAdmin(admin);
            }
            System.out.println("Enter para continuar..");
            sc.nextLine();             //cleaned buffer
            System.out.println("Desea cargar otro usuario? S/N");
            controller = sc.nextLine().toUpperCase();
        } while (controller.equals("S"));
    }
}

