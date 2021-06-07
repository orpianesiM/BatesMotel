package org.example;

import java.time.LocalDateTime;
//import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;


public class Hotel {

    /* [Atributos] */

    private boolean isOpen;
    private ArrayList<Room> roomList;
    private HashSet<Booking> bookingList;
    private TreeSet<User> userList;


    /* [Constructores] */


    public Hotel(boolean isOpen, TreeSet userList, ArrayList roomList, HashSet bookingList)
    {
        this.isOpen = isOpen;
        this.userList = userList;
        this.roomList = roomList;
        this.bookingList = bookingList;
    }

    public Hotel()
    {
        this.userList = new TreeSet<>();
        this.roomList = new ArrayList<>();
        this.bookingList = new HashSet<>();

    }

    /* [Métodos] */



    public Room verifyBooking(LocalDateTime checkIn, LocalDateTime checkOut,  RoomType roomType ){

        if (roomList !=null){

            for (Booking bookedRoom : bookingList) {
                if (bookedRoom.getBookedRoom().getRoomType() == roomType && checkIn.isAfter(bookedRoom.getCheckInDate() )&& checkOut.isBefore(bookedRoom.getCheckOutDate() ) ){
                    return bookedRoom.getBookedRoom();
                }
            }
        }
        else{
            return null;
        }
        return null;
    }


    public Booking makeBooking ( String checkIn, String checkOut,  RoomType roomType, Passenger bookingPassenger ){

        Room newRoom = verifyBooking(Booking.stringToLocalDateTime(checkIn), Booking.stringToLocalDateTime(checkOut), roomType);

        if (newRoom != null){
            Booking newBooking = new Booking();
            newBooking.setCheckInDate(Booking.stringToLocalDateTime(checkIn));
            newBooking.setCheckOutDate(Booking.stringToLocalDateTime(checkOut));
            newBooking.setBookedRoom(newRoom);
            newBooking.setBookingPassenger(bookingPassenger);

            insertBooking(newBooking);

            return newBooking;
        }

        return null;
    }

    public void insertBooking ( Booking booking){

        bookingList.add(booking);

    }

    public double getTicket (String bookingId){

        if (this.bookingList !=null){

            for (Booking booking : bookingList ) {

                if (booking.getBookingId().equals(bookingId)){

                    return booking.getSpentMoney(); // retorna valor de lo gastado hasta el momento en que se hace la consulta
                }
            }
        }
        return -1;
    }


    public boolean addPassenger(Passenger newPassenger)
    {
        return userList.add(newPassenger);
    }

    //getBookingByRoom(Room)
    public List getBookingByRoom (Room room){

        if (bookingList!=null){
            int flag=0;
            List<Booking> bookings = new ArrayList<>();

            for (Booking booking  : bookingList ) {

                if (booking.getBookedRoom().equals(room)){  // consultar si hace falta sobreescribir el método equals y hashcode

                    flag=1;
                    bookings.add(booking);
                }
            }

            if (flag!=0){
                return bookings;
            }
            else
                return null;
        }
        return null;
    }

    //getBookingByDni - retorna List porque puede haber mas de un Booking realizado por una misma persona

    public List getBookingByDni (String dni){

        if (bookingList!=null){
            int flag=0;
            List<Booking> bookings = new ArrayList<>();

            for (Booking booking  : bookingList ) {

                if (booking.getBookingPassenger().getDni()==dni){

                    flag=1;
                    bookings.add(booking);
                }
            }

            if (flag!=0){
                return bookings;
            }
            else
                return null;
        }
        return null;
    }


    //getAllBooking

    public HashSet getAllBooking (){    // consultar si es necesario o se puede recorrer directamente bookingList

        if (bookingList !=null){
            HashSet<Booking> allBokings = this.bookingList;
            return allBokings;
        }
        return null;

    }

    //getAllRooms

    public List getAllRooms(){

        if (roomList!=null){
            List<Room> allRooms = this.roomList;
            return allRooms;
        }
        return null;
    }


    //getpassengerbyDni por dni

    public Passenger getpassengerbyDni(String dni){

        if (userList!=null){

            for (User user : userList) {
                if (user instanceof Passenger && user.getDni().equals(dni)){

                    return (Passenger) user;
                }
            }
        }
        return null;

    }


    public void setProductToRoomService (int productChoice, Booking booking){

        for ( Product product: Product.values() ) {

            if (product.ordinal()== productChoice){

                booking.setSpentMoney(booking.getSpentMoney()+ product.getValue());
            }
        }
    }



    public int getHotelSize()
    {
        if (roomList != null)
        {
            int roomCounter = 0;

            for (Room variable : roomList)
            {
                roomCounter++;
            }

            return roomCounter;
        }
        else
            return 0;
    }

    public boolean seeWorkers()
    {
        if (userList != null)
        {
            for (Object variable : userList)
            {
                if (variable instanceof Employee || variable instanceof Admin)
                {
                    System.out.println(variable.toString()); //Consultar como evitar este print
                }
            }

            return true;
        }
        else
            return false;
    }

    public boolean seeRooms()
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                System.out.println(variable.toString()); //Consultar como evitar este print
            }

            return true;

        }
        else
            return false;

    }

    public boolean availableRooms()
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                if (variable.isAvailable())
                {
                    System.out.println(variable.toString()); //Consultar como evitar este print
                }
            }

            return true;
        }
        else
            return false;

    }

    public boolean unavailableRooms()
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                if (!(variable.isAvailable()))
                {
                    System.out.println(variable.toString());//Consultar como evitar este print
                }
            }

            return true;
        }
        else
            return false;

    }



    public ArrayList<Passenger> getAllPassengers()
    {
        if (userList != null)
        {
           ArrayList<Passenger> auxList = new ArrayList<Passenger>();

            for (User variable : userList)
            {
                if (variable instanceof Passenger)
                {
                    auxList.add((Passenger)variable);
                }
            }

            if (!(auxList.isEmpty())) //Considero que no se haya encontrado ningun pasajero.
            {
                return auxList;
            }
        }

        return null;

    }

    public Room getRoom (int roomNumber)
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                if (variable.getRoomNumber() == roomNumber)
                {
                    return variable;
                }

            }

        }

        return null;
    }

    public boolean checkDateAvailability(LocalDateTime lookedDate)
    {
       if (bookingList == null)
       {
           return true;
       }
       else
       {
           for (Booking variable : bookingList)
           {
               if (variable.getCheckInDate() == lookedDate)
               {
                   return false;
               }
           }

           return true;
       }
    }

    public void changeRoomAvailability(Room roomChanged)
    {
        if (roomChanged.isAvailable())
        {
            roomChanged.setAvailable(false);
        }
        else
            roomChanged.setAvailable(true);
    }


    public boolean checkIn(Booking booking)
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                if (booking.getBookedRoom().equals(variable))
                {
                    changeRoomAvailability(variable);
                    booking.setBookingState(BookingState.INITIATED);
                    return true;
                }
            }

        }

        return false;
    }

    public boolean checkOut (Booking booking)
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                if (booking.getBookedRoom().equals(variable))
                {

                    changeRoomAvailability(variable);
                    booking.setBookingState(BookingState.FINALIZED);

                    /*Falta ver la parte del ticket y como se incorpora
                    * dentro del checkOut*/

                    return true;

                }
            }
        }

        return false;
    }


    /*Getters & Setters */

    public boolean isOpen()
    {
        return isOpen;
    }

    public void setOpen(boolean open)
    {
        isOpen = open;
    }

    public ArrayList<Room> getRoomList()
    {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList)
    {
        this.roomList = roomList;
    }

    public HashSet<Booking> getBookingList()
    {
        return bookingList;
    }

    public void setBookingList(HashSet<Booking> bookingList)
    {
        this.bookingList = bookingList;
    }

    public TreeSet<User> getUserList()
    {
        return userList;
    }

    public void setUserList(TreeSet<User> userList)
    {
        this.userList = userList;
    }
}
