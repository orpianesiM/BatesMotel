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
        System.out.println("1)Registrarse\n 2) Logearse ");
        option = sc.nextInt();

        switch (option) {
            case 1:
                Passenger passengerNew;
                boolean add = false;
                String controller = null, controllerAdd, name, lastName, email, dni, origin, originAddress, user, pass;
                long phone;
                System.out.println("-------**Crear Usuario**------\n");

                    System.out.println("INGRESE LOS DATOS");
                    System.out.println("Nombre: ");
                    name = sc.nextLine();
                    name = sc.nextLine();
                    System.out.println("Apellido: ");
                    lastName = sc.nextLine();
                    System.out.println("DNI: ");
                    dni = sc.nextLine();
                    System.out.println("Email: ");
                    email = sc.nextLine();
                    System.out.println("Telefono: ");
                    phone = sc.nextLong();
                    System.out.println("Origen: ");
                    origin = sc.nextLine();
                    origin = sc.nextLine();
                    System.out.println("Dirección de origen: ");
                    //originAddress = sc.nextLine();
                    originAddress = sc.nextLine();
                    System.out.println("Usuario: ");
                    //user = sc.nextLine();
                    user = sc.nextLine();
                    System.out.println("Password: ");
                    //pass = sc.nextLine();
                    pass = sc.nextLine();
                    passengerNew = new Passenger(name,lastName,dni,email,user,pass,phone,origin,originAddress);
                    System.out.println(passengerNew);

                    System.out.println("Los datos son correctos ? S/N");
                    controllerAdd = sc.nextLine().toUpperCase();
                    if (controllerAdd.equals("S")) {
                        add = hotel.addPassenger(passengerNew);
                        if (add) System.out.println("Usuario creado con exito");
                        if (!add) System.out.println("Error al cargar nuevo usuario");

                    }
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
                        userFound.signIn(userFound, hotel); //se diferencian los sign in ?
                    }
                    else {
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
        int phoneNumber;

        do {
            System.out.println("*-*-*-*-*-*-*-***Bates Motel****-*-*-*-*-*-*\n");
            System.out.println("*-*-*-*-*-*-*-***Registrarse****-*-*-*-*-*-*\n");
            System.out.println("NOMBRE: ");
            name = sc.nextLine();
            System.out.println("APELLIDO: ");
            lastName = sc.nextLine();
            dni = validateDni();           //valido no repetir dni
            System.out.println("EMAIL: ");
            email = sc.nextLine();
            System.out.println("TELÉFONO: ");
            phoneNumber = sc.nextInt();
            System.out.println("ORIGEN: ");
            origin = sc.nextLine();
            System.out.println("DIRECCIÓN DE ORIGEN: ");
            originAddress = sc.nextLine();
            user = validateUserName();    //valido no repetir username
            System.out.println("CONTRASEÑA: ");
            password = sc.nextLine();

            /**tiene que ser tipo passenger porque es el unico que va a acceder al metodo para registrarse**/
            Passenger passenger = new Passenger(name, lastName, dni, email, user, password, phoneNumber, origin, originAddress);
            System.out.println("Datos ingresados: " + passenger);

            System.out.println("Los datos son correctos? S/N");
            answ = sc.nextLine().toUpperCase();
            if (answ.equals("S")) {
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
        } while (!flag);
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
        } while (!flag);
        return dni;
    }
}
