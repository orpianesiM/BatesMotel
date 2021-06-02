package org.example.controllers;

import java.util.Scanner;

public class ControllerAdmin {
    private static final Scanner sc = new Scanner(System.in);

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
            if (isInteger(option)) {
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
                }
            } else flag = messageError();
        }while(flag);
    }

    /**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*ROOM*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**/
    /*******************************************CONTROLLERS*******************************************/
    private static void controllerRooms(Hotel hotel) {
        boolean flag=false;
        viewMenuRooms();
        do {
            String option = sc.nextLine();
            if (isInteger(option)) {
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
                        //ToDo
                        break;
                    case "4":
                        controllerRoomStatus(hotel);
                        controllerRooms(hotel);
                        break;
                    case "0":
                        controllerMenuHotel(hotel);
                        break;
                }
            } else flag = messageError();
        } while (flag);
    }

    private static void controllerRoomSearch(Hotel hotel) {
        boolean flag = false;
        do {
            if (searchRoom(hotel) != null) {
                viewMenuRoomsSearch();
                String num = sc.nextLine();
                if (isInteger(num)) {
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
                    }
                } else flag = messageError();
            }
        }while (flag) ;
    }

    private static void controllerRoomService(Hotel hotel){
        Room room = searchRoom(hotel);
        if(room != null){
            if(hotel.getRoom(room).isAvailable()){
                System.out.println("Ingrese el consumo de la habitación <format:$xx.xx>: $");
                hotel.getRoom(room).setRoomService(sc.nextFloat()); //ToDo setRoomService()
            }else System.out.println("La habitación no tiene huéspedes");
        }
    }
    private static void controllerRoomList(Hotel hotel) {
        int i = 0;
        boolean flag = false;
        do {
            viewMenuRoomsList();
            String num = sc.nextLine();
            if (isInteger(num)) {
                switch (num) {
                    case "1":
                        //ToDo getSize() y getRoom() in class Hotel
                        for (i = 0; i < hotel.getHotelSize(); i++) {
                            if (!(hotel.getRoom(i).isAvailable())) System.out.println(hotel.getRoom(i));
                        }
                        break;
                    case "2":
                        for (i = 0; i < hotel.getHotelSize(); i++) {
                            if (hotel.getRoom(i).isAvailable()) System.out.println(hotel.getRoom(i));
                            }
                        break;
                    case "3":
                        for (i = 0; i < hotel.getHotelSize(); i++) System.out.println(hotel.getRoom(i));
                        break;
                }
            } else flag = messageError();
        } while (flag);
    }

    private static void controllerRoomAdjust(Hotel hotel) {
        String option, answer;
        boolean flag = false;
        do{
            Room room = searchRoom(hotel);
            if (room != null) {
                System.out.println("*-*-*-*-*-*-*-***Modificar Habitación****-*-*-*-*-*-*\n");
                System.out.println("1. Numero de habitación");
                System.out.println("2. Disponibilidad");
                System.out.println("3. Tipo de habitación");
                System.out.println("0. Salir");
                System.out.println("Seleccione lo que desea cambiar ");
                option = sc.nextLine();
                if (isInteger(option)) {
                    switch (option) {
                        case "1":
                            System.out.println("Ingrese nuevo numero de habitación");
                            hotel.getRoom(room).setRoom(sc.nextInt());
                            System.out.println("Datos cambiados con exito! \n" + hotel.getRoom(room));
                            break;
                        case "2":
                            boolean status = hotel.getRoom(room).getStatus();
                            System.out.println("El estado actual de la habitación es: " + status);
                            System.out.println("Desea cambiarlo? S/N");
                            answer = sc.nextLine().toUpperCase();
                            if (answer.equals("S") && status) hotel.getRoom(room).getStatus(false);
                            if (answer.equals("S") && !status) hotel.getRoom(room).getStatus(true);
                            System.out.println("Estado cambiado a : " + hotel.getRoom(room).getStatus());
                            break;
                        case "3":
                            int type = 0;
                            System.out.println("El tipo de habitación es: " + hotel.getRoom(room).getRoomType());
                            System.out.println("Seleccione un tipo");
                            System.out.println("1. Single");
                            System.out.println("2. Twin");
                            System.out.println("3. Matrimonial");
                            System.out.println("4. Triple");
                            System.out.println("5. Quad");
                            type = sc.nextInt();
                            switch (type) {
                                case 1:
                                    hotel.getRoom(room).setRoomType(RoomType.SINGLE);
                                    break;
                                case 2:
                                    hotel.getRoom(room).setRoomType(RoomType.TWIN);
                                    break;
                                case 3:
                                    hotel.getRoom(room).setRoomType(RoomType.MATRIMONIAL);
                                    break;
                                case 4:
                                    hotel.getRoom(room).setRoomType(RoomType.TRIPLE);
                                    break;
                                case 5:
                                    hotel.getRoom(room).setRoomType(RoomType.QUAD);
                                    break;
                            }
                            break;
                        case "0":
                            controllerRooms(hotel);
                            break;
                    }
                } else flag = messageError();
            }
        } while (flag);
    }


    private static void controllerRoomAdjustStatus(Hotel hotel){
        if (searchRoom(hotel) != null) {
            System.out.println(hotel.getRoom(room).getStatus()); //ToDo getStatus en classRoom (available,etc)
        }                                                       //o sin getStatus se puede mostrar la habitacion completa
    }

    private static void controllerRoomStatus(Hotel hotel) {
        int rooms = hotel.getHotelSize();
        int ocupated = 0;
        System.out.println("*-*-*-*-*-*-*-***Estado General****-*-*-*-*-*-*\n");
        System.out.println("Habitaciones: " + rooms);
        for (int i = 0; i < rooms; i++) {
            if (hotel.getRoom().isAvailable()) { //ToDo getAllRooms()
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
        System.out.println("3. Agregar consumos a una habitación");
        System.out.println("4. Datos generales del Hotel");
        System.out.println("0. Salir");
    }

    public static void viewMenuRoomsList(){
        System.out.println("*-*-*-*-*-*-*-***Menu Listar Habitaciones****-*-*-*-*-*-*\n");
        System.out.println("1. Habitaciones desocupadas");
        System.out.println("2. Habitaciones ocupadas");
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
            if (isInteger(option)) {
                switch (option) {
                    case "1":
                        hotel.getAllPassengers().forEach(System.out::println);//ToDo getAllPassengers()
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
                }
            } else flag = messageError();
        } while (flag);
    }

    private static void controllerPassengersAdd(Hotel hotel){
        Passenger passengerNew;
        boolean add = false;
        String controller = null, controllerAdd, name, lastName, email;
        Integer dni, phone;
        System.out.println("*-*-*-*-*-*-*-***Crear Pasajero****-*-*-*-*-*-*\n");
        do{
            System.out.println("INGRESE LOS DATOS");
            System.out.println("Nombre: ");
            name = sc.nextLine();
            System.out.println("Apellido: ");
            lastName = sc.nextLine();
            System.out.println("DNI: ");
            dni = sc.nextInt();
            System.out.println("Telefono: ");
            phone = sc.nextInt();
            System.out.println("Email: ");
            email = sc.nextLine();
            ///User and password ??
            passengerNew = new Passenger(name, lastName, dni, phone, email);
            System.out.println(passengerNew);

            System.out.println("Desea agregar al nuevo pasajero ? S/N");
            controllerAdd = sc.nextLine().toUpperCase();
            if(controllerAdd.equals("S")){
                add = hotel.addPassenger(passengerNew); //ToDo addPassenger boolean in class Hotel
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
            Passenger passengerFound = searchPassenger(hotel);
            if (passengerFound != null) {
                viewMenuPassengerSearch();
                String num = sc.nextLine();
                if (isInteger(num)) {
                    switch (num) {
                        case "1":
                            controllerPassengerSearchAdjust(hotel);
                            controllerPassengers(hotel);
                            break;
                        case "2":
                            System.out.println("Datos: \n" + passengerFound);
                            controllerPassengers(hotel);
                            break;
                        case "3":
                            controllerPassengerSearchBooking(hotel);
                            controllerPassengers(hotel);
                            break;
                        case "4":
                            controllerPassengerSearchHistory(hotel);
                            controllerPassengers(hotel);
                            break;
                        case "0":
                            controllerMenuHotel(hotel);
                            break;
                    }
                } else flag = messageError();
            }
        }while(flag);
    }

    private static void controllerPassengerSearchBooking(Hotel hotel){

    }

    private static void controllerPassengerSearchAdjust(Hotel hotel) {
        boolean flag = false;
        String option;
        do {
            Passenger passengerFound = searchPassenger(hotel);
            if (passengerFound != null) {
                System.out.println("Seleccione atributo a cambiar");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Telefono");
                System.out.println("4. Email");
                System.out.println("5. DNI");
                option = sc.nextLine();
                if(isInteger(option)){
                    switch (option){
                        case "1":
                            System.out.println("Ingrese nuevo nombre");
                            hotel.getPassenger(passengerFound).setName(sc.nextLine());
                            break;
                        case "2":
                            System.out.println("Ingrese nuevo apellido");
                            hotel.getPassenger(passengerFound).setLastName(sc.nextLine());
                            break;
                        case "3":
                            System.out.println("Ingrese nuevo teléfono");
                            hotel.getPassenger(passengerFound).setPhone(sc.nextInt());
                            break;
                        case "4":
                            System.out.println("Ingrese nuevo email");
                            hotel.getPassenger(passengerFound).setEmail(sc.nextLine());
                            break;
                        case "5":
                            System.out.println("Ingrese nuevo dni");
                            hotel.getPassenger(passengerFound).setDni(sc.nextInt());
                            break;
                    }
                }else flag=messageError();
            }
        }while(flag);
    }

    private static void controllerPassengerSearchHistory(Hotel hotel){
        Passenger passengerFound = searchPassenger(hotel);
        if(passengerFound != null){
            if(!(passengerFound.getHistory().isEmpty())){   //ToDo getHistory() in passenger
                passengerFound.getHistory().forEach(System.out::println);
            }else{
                System.out.println("El pasajero" +passengerFound.getName +" "+ passengerFound.getLastName +
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
            if (isInteger(option)) {
                switch (option) {
                    case "1":
                        //ToDo controllerBookingCreate()
                        controllerBooking(hotel);
                        break;
                    case "2":
                        controllerBookingDelete(hotel);
                        controllerBooking(hotel);
                        break;
                    case "3":
                        hotel.getAllBookings().forEach(System.out::println); //ToDo fn getAllBookings
                        controllerBooking(hotel);
                        break;
                    case "4":
                        Booking booking = searchBooking(hotel);
                        System.out.println("DATOS: " + booking); //hotel.getBooking(booking) ?
                        controllerMenuHotel(hotel);
                        break;
                    case "0":
                        controllerMenuHotel(hotel);
                        break;
                }
            } else flag = messageError();
        } while (flag);
    }

    private static void controllerBookingCreate(Hotel hotel){

    }

    private static void controllerBookingDelete(Hotel hotel){
        String answer=null, answerController=null;
        do {
            Booking booking = searchBooking(hotel);
            if (booking!=null) {
                System.out.println("Datos: " + booking); ///Que pasa cuando el pasajero tiene mas de 1 reservA?
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
        System.out.println("1. Realizar Reserva");
        System.out.println("2. Eliminar Reserva");
        System.out.println("3. Ver todas las reservas");
        System.out.println("4. Buscar reserva por pasajero");
        System.out.println("0. Salir");
    }

/*******************************************HELPERS*******************************************/
    private static Booking searchBooking (Hotel hotel){
     String flag = null;
        Booking bookingFound;
        do {
            System.out.println("Ingrese el dni del pasajero: ");
            String dni = sc.nextLine();
            bookingFound = hotel.getBooking(dni); //ToDo busca reserva por dni de pasajero
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

    private static Room searchRoom (Hotel hotel){
    String flag = null;
    Room roomFound;
    do {
        System.out.println("Ingrese la habitación: ");
        String roomNum = sc.nextLine();
        roomFound = hotel.getRoom(roomNum);
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

private static Passenger searchPassenger (Hotel hotel){
    String flag = null;
    Passenger passengerFound;
    do {
        System.out.println("Ingrese el DNI: ");
        String dni = sc.nextLine();
        passengerFound = hotel.getPassenger(dni);
        if (passengerFound != null) {
            System.out.println("Pasajero encontrado");
            return passengerFound;
        } else {
            System.out.println("El pasajero es INEXISTENTE");
            System.out.println("Quiere volver a intentarlo ? S/N");
            flag = sc.nextLine();
        }
    } while (flag.equals("N"));
    return null;
}

private static boolean messageError () {
    String answ;
        System.out.println("Ingreso incorrectamente. Desea volver a intentarlo? S/N");
        answ = sc.nextLine().toUpperCase();
        if(answ.equals("S")) return true;
    return false;
}
 private static boolean isInteger (String str){
    try {
        Integer.parseInt(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

}

