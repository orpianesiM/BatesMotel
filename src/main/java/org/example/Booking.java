package org.example;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;


public class Booking {

    private String bookingId ; // id unico de 12 caracteres
    private Passenger bookingPassenger;
    private Room bookedRoom;
    private BookingState bookingState;  // ir seteando de acuerdo a la fecha
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private double spentMoney;



    public Booking(Passenger bookingPassenger , Room bookedRoom, String checkInDate, String checkOutDate, BookingState bookingState) {
        this.bookingPassenger= bookingPassenger;
        this.bookedRoom = bookedRoom;
        this.bookingState=bookingState;
        this.checkInDate = stringToLocalDateTime(checkInDate);  // conversion de String a LocalDateTime
        this.checkOutDate = stringToLocalDateTime(checkOutDate);
        this.bookingId= shortUUID();
        this.spentMoney= reservedDays(stringToLocalDateTime(checkInDate),stringToLocalDateTime(checkOutDate))* bookedRoom.getRoomType().getValue(); //inicia con el valor por noche de la habitacion multiplicado por la cantidad de dias que se hospeda

    }

    public Booking() {
    }

    // hacer metodo para generar ticket en el checkout





    // calcular los dias entre checkin y check out
    public static long reservedDays (LocalDateTime checkInDate, LocalDateTime checkOutDate){

        long daysBetween= DAYS.between(checkOutDate,checkInDate);
        return daysBetween;
    }



    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

    public static LocalDateTime stringToLocalDateTime (String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime formatDateTime = LocalDateTime.parse(date, formatter);

        return formatDateTime;  // agregar .format(formatter) en el toString

    }


    public BookingState getBookingState() {
        return bookingState;
    }

    public void setBookingState(BookingState bookingState) {
        this.bookingState = bookingState;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
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

    public double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(double spentMoney) {
        this.spentMoney = spentMoney;
    }
}
