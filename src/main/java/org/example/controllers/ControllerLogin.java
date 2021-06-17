package org.example.controllers;

import org.example.entities.*;
import org.example.helpers.FileHelper;
import org.example.helpers.IControllerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ControllerLogin implements IControllerHelper
{
    private static final Scanner sc = new Scanner(System.in);

    public static void login(Hotel hotel)
    {
        String username, password, answ;
        int option;
        User userFound;
        boolean flag = true;
        System.out.println("*-*-*-*-*-*-*-***Bates Motel***-*-*-*-*-*-*");
        System.out.println("*-*-*-*-*-*-*-***LOGIN***-*-*-*-*-*-*");
        System.out.println("1. Registrarse");
        System.out.println("2. Logearse ");
        System.out.println("0. Salir");
        option = sc.nextInt();
        switch (option) {
            case 1:
               newUser(hotel);
               login(hotel);
                break;
            case 2:
                do {
                    sc.nextLine();             //cleaned buffer
                    System.out.print("Usuario: ");
                    username = sc.nextLine();
                    System.out.print("Password: ");
                    password = sc.nextLine();

                    userFound = isValidUser(username, password, hotel);
                    if (userFound != null) {
                        if(userFound instanceof Passenger) ControllerPassenger.controllerMenuPrincipal(hotel, (Passenger) userFound);
                        if(userFound instanceof Admin) ControllerAdmin.controllerMenuHotel(hotel);
                        if(userFound instanceof Employee) ControllerEmployee.controllerMenuEmployee(hotel);
                    }else {
                        flag = IControllerHelper.messageError();
                        if (!flag) {
                            System.out.println("Desea registrarse ? S/N");
                            sc.nextLine();             //cleaned buffer
                            System.out.println("Enter para continuar..");
                            answ = sc.nextLine().toUpperCase();
                            if (answ.equals("S")) {
                                newUser(hotel);
                                login(hotel);
                            }else System.exit(0);
                        }
                    }
                } while (flag);
                break;
            case 0:
                System.out.println("*-*-***Gracias por utilizar Bates Motel***-*-*");
                System.exit(0);
                break;
        }
    }

    public static ArrayList<User> listOfAllUsers(Hotel hotel){
        ArrayList<User> users = new ArrayList<>();

        users.addAll(hotel.getPassengerList());
        users.addAll(hotel.getAdminList());
        users.addAll(hotel.getEmployeeList());
        return users;
    }

    public static User isValidUser(String username, String pass, Hotel hotel) {
        User userFound = null;
        List<User> users = listOfAllUsers(hotel);
         if (!users.isEmpty()) {
             for (User u : users) {
                     if (u.getUser().equals(username)) {
                         if (u.getPassword().equals(pass)) {
                             userFound = u;
                         }
                     }
                 }
             }
        return userFound;
    }

    public static void newUser(Hotel hotel) {
        String name, lastName, dni, email, user, password, origin, originAddress, answ;
        long phoneNumber;
        boolean add = false;

        do {
            System.out.println("*-*-*-*-*-*-*-***Registrarse****-*-*-*-*-*-*");
            sc.nextLine();             //cleaned buffer
            System.out.print("NOMBRE: ");
            name = sc.nextLine();
            System.out.print("APELLIDO: ");
            lastName = sc.nextLine();
            dni = validateDni(hotel);           //valido no repetir dni
            System.out.print("EMAIL: ");
            email = sc.nextLine();
            System.out.print("TELÉFONO: ");
            phoneNumber = sc.nextLong();
            sc.nextLine();             //cleaned buffer
            System.out.print("ORIGEN: ");
            origin = sc.nextLine();
            System.out.print("DIRECCIÓN DE ORIGEN: ");
            originAddress = sc.nextLine();
            user = validateUserName(hotel);    //valido no repetir username
            System.out.print("CONTRASEÑA: ");
            password = sc.nextLine();

            /**tiene que ser tipo passenger porque es el unico que va a acceder al metodo para registrarse**/
            Passenger passenger = new Passenger(name, lastName, dni, email, user, password, phoneNumber, origin, originAddress);
            System.out.println("Datos ingresados: " + passenger.detallePasajero());

            System.out.println("Los datos son correctos? S/N");
            answ = sc.nextLine().toUpperCase();
            if (answ.equals("S")) {
                    add = hotel.addPassenger(passenger);
                    if (add) System.out.println("Usuario creado con exito");
                    if (!add) System.out.println("Error al cargar nuevo usuario");
                if (hotel.addPassenger(passenger)) ;
                System.out.println("Usuario agregado con éxito!");
            }
        } while (answ.equals("N"));
    }

    public static boolean userNameExist(String username, Hotel hotel) {
        List<User> userSet = listOfAllUsers(hotel);
        if (!userSet.isEmpty()) {
            for (User users : userSet) {
                if (users.getUser().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dniExist(String dni, Hotel hotel) {
        List<User> userSet = listOfAllUsers(hotel);
        if (!(userSet.isEmpty())) {
            for (User users : userSet) {
                if (users.getDni().equals(dni)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String validateUserName(Hotel hotel) {
        boolean flag;
        String user;
        do {
            sc.nextLine();             //cleaned buffer
            System.out.print("USUARIO: ");
            user = sc.nextLine();
            flag = userNameExist(user,hotel);
            if (flag) System.out.println("El usuario ya existe vuelva a intentarlo");
        } while (flag);
        return user;
    }

    public static String validateDni(Hotel hotel) {
        boolean flag;
        String dni;
        do {
            sc.nextLine();             //cleaned buffer
            System.out.print("DNI: ");
            dni = sc.nextLine();
            flag = dniExist(dni, hotel);
            if (flag) System.out.println("El dni ya existe vuelva a intentarlo");
        } while (flag);
        return dni;
    }
}
