package org.example;


import org.example.helpers.FileHelper;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public class Hotel {

    private boolean isOpen;
    private List<Room> listOfRooms;
    private HashSet<Booking> bookingList;


    public Hotel(boolean isOpen, List userList, List roomList, List bookingList)
    {
        this.isOpen = isOpen;
        this.userList = userList;
        this.roomList = roomList;
        this.bookingList = bookingList;
    }

    public Hotel()
    {
        this.userList = new List();
        this.roomList = new List();
        this.bookingList = new List();

    }




    public Room verifyBooking(LocalDateTime checkIn, LocalDateTime checkOut,  RoomType roomType ){

        if (listOfRooms !=null){

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


    public int roomAmount()
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
                System.out.println(variable.toString); //Consultar como evitar este print
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
                if (variable.getIsAvailable)
                {
                    System.out.println(variable.toString); //Consultar como evitar este print
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
                if (!(variable.getIsAvailable))
                {
                    System.out.println(variable.toString);//Consultar como evitar este print
                }
            }

            return true;
        }
        else
            return false;

    }

    public Passenger searchPassenger(String dniBuscado)
    {
        if (userList != null)
        {
            for (Object variable : userList)
            {
                if (variable instanceof Passenger && dniBuscado.equals(((Passenger) variable).getDni()))
                {
                    return (Passenger)variable;
                }
            }

            return null;
        }
        else
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
               if (booking.getCheckInDate() == lookedDate)
               {
                   return false;
               }
           }

           return true;
       }

    }

    public boolean changeRoomAvailability(Room roomChanged)
    {

    }

    public void setOpen(boolean open)
    {
        isOpen = open;
    }

    public boolean checkIn(Booking booking)
    {
        if (roomList != null)    //la lista de room nunca va a estar vacia, habria que checkear que no este ocupada la habitacion
        {
            for (Room variable : roomList)
            {
                if (booking.getBookedRoom.equals(variable))
                {
                    variable.setAvailable(false);
                    variable.setGuest (booking.getPassenger);
                    variable.setSpentMoney (0);
                }

            }

            return true;
        }
        else
            return false;

    }

    public boolean checkOut (Room roomCheckedOut)
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                if (roomCheckedOut.equals(variable))
                {
                    variable.setAvailable(true);
                    variable.setGuest(null);
                    variable.setSpentMoney(0);

                    /*
                    Habría que ver como incluir la impresión del ticket en el checkout
                    * */
                }
            }
        }
    }
    /*************************NUEVO***************************************/
   /* public double checkOut(Room roomCheckedOut){
        Booking bookingFound = searchBookingByRoom(roomCheckedOut);
        double totalToPay=bookingFound.getSpentMoney();
        roomCheckedOut.getGuest().setHistory("\nPasajero :" + bookingFound.getBookingPassenger() +
                                            "\nHabitación: "+roomCheckedOut +
                                            "\nDesde: "+bookingFound.getCheckInDate() +
                                            "\nHasta: "+ bookingFound.getCheckOutDate() +
                                            "\nTotal a pagar" + bookingFound.getSpentMoney());
        roomCheckedOut.setAvailable(true);
        roomCheckedOut.setGuest(null);
        return totalToPay;
    }
  /***Ver donde agregar historial del pasajero***/

    public void saveHotel(){
        FileHelper.setBookingsToJson(bookingList);
        FileHelper.setRoomsToJson(listOfRooms);
        FileHelper.setUsersToJson(userList);
    }

    public Hotel(){
        this.bookingList.addAll(FileHelper.getBookingsFromJson());
        this.listOfRooms.addAll(FileHelper.getRoomsFromJson());
        this.userList.addAll(FileHelper.getUsersFromJson());
    }
}
