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
                    System.out.println("Usuario: ");
                    username = sc.nextLine();
                    System.out.println("Password: ");
                    password = sc.nextLine();

                    userFound = isValidUser(username, password, hotel);
                    if (userFound != null) {
                        if(userFound instanceof Passenger) ControllerPassenger.controllerMenuPrincipal(hotel);
                        if(userFound instanceof Admin) ControllerAdmin.controllerMenuHotel(hotel);
                        if(userFound instanceof Employee) ControllerEmployee.controllerMenuEmployee(hotel);
                    }else {
                        flag = IControllerHelper.messageError();
                        if (!flag) {
                            System.out.println("Desea registrarse ? S/N");
                            sc.nextLine();             //cleaned buffer
                            answ = sc.nextLine().toUpperCase();
                            if (answ.equals("S")) {
                                newUser(hotel);
                                login(hotel);
                            }
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
        ArrayList<Passenger> p = hotel.getPassengerList();
        ArrayList<Admin> a = hotel.getAdminList();
        ArrayList<Employee> e = hotel.getEmployeeList();
        users.addAll(p);
        users.addAll(a);
        users.addAll(e);
        return users;
    }

    public static User isValidUser(String username, String pass, Hotel hotel) {
        List<User> users = listOfAllUsers(hotel);
        if (!(users.isEmpty())) {
            for (User u : users) {
                if (u.getUser().equals(username)) {
                    if (u.getPassword().equals(pass)) {
                        return u;
                    }
                }
            }
        }
        return null;
    }

    public static void newUser(Hotel hotel) {
        String name, lastName, dni, email, user, password, origin, originAddress, answ;
        long phoneNumber;
        boolean add = false;

        do {
            System.out.println("*-*-*-*-*-*-*-***Registrarse****-*-*-*-*-*-*");
            sc.nextLine();             //cleaned buffer
            System.out.println("NOMBRE: ");
            name = sc.nextLine();
            System.out.println("APELLIDO: ");
            lastName = sc.nextLine();
            dni = validateDni(hotel);           //valido no repetir dni
            System.out.println("EMAIL: ");
            email = sc.nextLine();
            System.out.println("TELÉFONO: ");
            phoneNumber = sc.nextLong();
            sc.nextLine();             //cleaned buffer
            System.out.println("ORIGEN: ");
            origin = sc.nextLine();
            System.out.println("DIRECCIÓN DE ORIGEN: ");
            originAddress = sc.nextLine();
            user = validateUserName(hotel);    //valido no repetir username
            System.out.println("CONTRASEÑA: ");
            password = sc.nextLine();

            /**tiene que ser tipo passenger porque es el unico que va a acceder al metodo para registrarse**/
            Passenger passenger = new Passenger(name, lastName, dni, email, user, password, phoneNumber, origin, originAddress);
            System.out.println("Datos ingresados: " + passenger);

            System.out.println("Los datos son correctos? S/N");
            answ = sc.nextLine().toUpperCase();
            if (answ.equals("S")) {
                    add = hotel.addPassenger(passenger);
                    if (add) System.out.println("Usuario creado con exito");
                    if (!add) System.out.println("Error al cargar nuevo usuario");
                if (hotel.addPassenger(passenger)) ;
                System.out.println("Usuario creado con éxito!");
            }
        } while (answ.equals("N"));
    }

    public static boolean userNameExist(String username, Hotel hotel) {
        List<User> userSet = listOfAllUsers(hotel);
        if (!(userSet.isEmpty())) {
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
            System.out.println("USUARIO: ");
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
            System.out.println("DNI: ");
            dni = sc.nextLine();
            flag = dniExist(dni, hotel);
            if (flag) System.out.println("El dni ya existe vuelva a intentarlo");
        } while (flag);
        return dni;
    }
}
