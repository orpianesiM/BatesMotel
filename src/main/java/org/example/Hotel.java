package org.example;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public class Hotel {

    private boolean isOpen;
    private List<Room> listOfRooms;
    private HashSet<Booking> bookingList;




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


    public Booking makeBooking ( LocalDateTime checkIn, LocalDateTime checkOut,  RoomType roomType, Passenger bookingPassenger ){


        Room newRoom = verifyBooking(checkIn, checkOut, roomType);
        if (newRoom != null){
            Booking newBooking = new Booking();
            newBooking.setCheckInDate(checkIn);
            newBooking.setCheckOutDate(checkOut);
            newBooking.setBookedRoom(newRoom);
            newBooking.setBookingPassenger(bookingPassenger);

            insertBooking(newBooking);

            return newBooking; // pensar si es boolean
        }

        return null; // avisar en main si el retorno es null para saber que no se hizo la reserva
    }

    public void insertBooking ( Booking booking){

        bookingList.add(booking);

    }


    public Booking searchBooking (String passengerDni){

        for (Booking booking : bookingList ) {

           // if ()   // comparo dni
        }
    }


}
