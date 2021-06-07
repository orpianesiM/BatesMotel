package org.example;

import java.time.LocalDateTime;
import java.util.*;


public class Hotel {

    /* [Atributos] */

    private boolean isOpen;
    private ArrayList<Room> roomList;
    private HashSet<Booking> bookingList;
    private TreeSet<User> userList;


    /* [Constructores] */

    public Hotel(boolean isOpen, TreeSet<User> userList, ArrayList<Room> roomList, HashSet<Booking> bookingList)
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

        return null; // avisar en main si el retorno es null para saber que no se hizo la reserva
    }

    public void insertBooking ( Booking booking){

        bookingList.add(booking);

    }


    public boolean addPassenger(Passenger newPassenger)
    {
        return userList.add(newPassenger);
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

    public Passenger getPassenger(String dniBuscado)
    {
        if (userList != null)
        {
            for (User variable : userList)
            {
                if (variable instanceof Passenger && dniBuscado.equals(((Passenger) variable).getDni()))
                {
                    return (Passenger)variable;
                }
            }

        }

        return null;
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
