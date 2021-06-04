package org.example;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public class Hotel {

    private boolean isOpen;
    private List<Room> roomList;
    private HashSet<Booking> bookingList;
    private List<User> userList;


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


   /* public Booking searchBooking (String passengerDni){

        for (Booking booking : bookingList ) {

           // if ()   // comparo dni
        }
    }*/



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
                if (variable.isAvailable())
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
                if (!(variable.isAvailable()))
                {
                    System.out.println(variable.toString);//Consultar como evitar este print
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

    public void setOpen(boolean open)
    {
        isOpen = open;
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

                    booking.setBookingState(INITIATED);

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

                    booking.setBookingState(FINALIZED);

                    /*Falta ver la parte del ticket y como se incorpora
                    * dentro del checkOut*/


                    return true;

                }
            }
        }

        return false;
    }

}
