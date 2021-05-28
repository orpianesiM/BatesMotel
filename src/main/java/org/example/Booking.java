package org.example;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {

    private UUID bookingId ;
    private Passenger bookingPassenger;
    private Room bookedRoom; // cambiar por roomNumber
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    // booking number consultar


    public Booking(Passenger bookingPassenger , Room bookedRoom, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        this.bookingPassenger= bookingPassenger;
        this.bookedRoom = bookedRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Booking() {
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public Passenger getBookingPassenger() {
        return bookingPassenger;
    }

    public void setBookingPassenger(Passenger bookingPassenger) {
        this.bookingPassenger = bookingPassenger;
    }

    public Room getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(Room bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
