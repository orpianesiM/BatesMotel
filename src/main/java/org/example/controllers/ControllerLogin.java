package org.example.controllers;

//import jdk.swing.interop.SwingInterOpUtils;
import org.example.*;
import org.example.helpers.FileHelper;
import org.example.helpers.IControllerHelper;

import java.util.List;
import java.util.Locale;
import java.util.Set;


public class ControllerLogin implements IControllerHelper
{

    public static void login(Hotel hotel)
    {
        String username, password, answ;
        int option;
        User userFound;
        boolean flag = false;
        System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*\n");
        System.out.println("*-*-*-*-*-*-*-***LOGIN****-*-*-*-*-*-*\n");
        System.out.println("1. Registrarse");
        System.out.println("2. Logearse ");
        option = sc.nextInt();

        switch (option) {
            case 1:
               newUser(hotel);
               login(hotel);
                break;
            case 2:
                do {
                    System.out.println("Usuario: ");
                    username = sc.nextLine();
                    username = sc.nextLine();
                    System.out.println("Password: ");
                    password = sc.nextLine();

                    userFound = isValidUser(username, password);
                    if (userFound != null) {
                        if(userFound.getUserType().equals(UserType.PASSENGER)) ControllerPassenger.controllerMenuPrincipal(hotel);
                        if(userFound.getUserType().equals(UserType.ADMIN)) ControllerAdmin.controllerMenuHotel(hotel);
                        if(userFound.getUserType().equals(UserType.EMPLOYEE)) ControllerEmployee.controllerMenuEmployee(hotel);
                    }else {
                        flag = IControllerHelper.messageError();
                        if (!flag) {
                            System.out.println("Desea registrarse ? S/N");
                            answ = sc.nextLine().toUpperCase();
                            if (answ.equals("S")) {
                                newUser(hotel);
                                login(hotel);
                            }
                        }
                    }
                } while (flag);
                break;
        }
    }

    public static User isValidUser(String username, String pass) {
        List<User> userSet = FileHelper.getUsersFromJson();
        if (userSet != null) {
            for (User users : userSet) {
                if (users.getUser().equals(username)) {
                    if (users.getPassword().equals(pass)) {
                        return users;
                    }
                }
            }
        }
        return null;
    }

    public static boolean newUser(Hotel hotel) {
        String name, lastName, dni, email, user, password, origin, originAddress, answ;
        long phoneNumber;
        boolean add = false;

        do {
            System.out.println("*-*-*-*-*-*-*-***Registrarse****-*-*-*-*-*-*\n");
            System.out.println("NOMBRE: ");
            name = sc.nextLine();
            name = sc.nextLine();
            System.out.println("APELLIDO: ");
            lastName = sc.nextLine();
            dni = validateDni();           //valido no repetir dni
            System.out.println("EMAIL: ");
            email = sc.nextLine();
            System.out.println("TELÉFONO: ");
            phoneNumber = sc.nextLong();
            System.out.println("ORIGEN: ");
            origin = sc.nextLine();
            origin = sc.nextLine();
            System.out.println("DIRECCIÓN DE ORIGEN: ");
            originAddress = sc.nextLine();
            user = validateUserName();    //valido no repetir username
            System.out.println("CONTRASEÑA: ");
            password = sc.nextLine();

            /**tiene que ser tipo passenger porque es el unico que va a acceder al metodo para registrarse**/
            Passenger passenger = new Passenger(name, lastName, dni, email, user, password, phoneNumber, origin, originAddress, UserType.PASSENGER);
            System.out.println("Datos ingresados: " + passenger);

            System.out.println("Los datos son correctos? S/N");
            answ = sc.nextLine().toUpperCase();
            if (answ.equals("S")) {
                    add = hotel.addPassenger(passenger);
                    if (add) System.out.println("Usuario creado con exito");
                    if (!add) System.out.println("Error al cargar nuevo usuario");
                if (hotel.addPassenger(passenger)) ;
                System.out.println("Usuario creado con éxito!");
                return true;
            }
        } while (answ.equals("N"));
        return false;
    }

    public static boolean userNameExist(String username) {
        List<User> userSet = FileHelper.getUsersFromJson();
        if (userSet != null) {
            for (User users : userSet) {
                if (users.getUser().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dniExist(String dni) {
        List<User> userSet = FileHelper.getUsersFromJson();
        if (userSet != null) {
            for (User users : userSet) {
                if (users.getDni().equals(dni)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String validateUserName() {
        boolean flag;
        String user;
        do {
            System.out.println("USUARIO: ");
            user = sc.nextLine();
            flag = userNameExist(user);
            if (flag) System.out.println("El usuario ya existe vuelva a intentarlo");
        } while (flag);
        return user;
    }

    public static String validateDni() {
        boolean flag;
        String dni;
        do {
            System.out.println("DNI: ");
            dni = sc.nextLine();
            flag = dniExist(dni);
            if (flag) System.out.println("El dni ya existe vuelva a intentarlo");
        } while (flag);
        return dni;
    }
}
