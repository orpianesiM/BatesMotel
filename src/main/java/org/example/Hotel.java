package org.example;

import org.example.helpers.FileHelper;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;


public class Hotel
{

    /* [Atributos] */
    
      /*private boolean isOpen;
    private List<Room> listOfRooms;
    private HashSet<Booking> bookingList;*/

    private boolean isOpen;
    private ArrayList<Room> roomList;
    private HashSet<Booking> bookingList;
    private List<User> userList;

    /* [Constructores] */

    public Hotel()
    {
        this.bookingList = FileHelper.getBookingsFromJson();
        this.roomList = FileHelper.getRoomsFromJson();
        this.userList = FileHelper.getUsersFromJson();
    }

/*

  public Hotel()
    {
        this.userList = new ArrayList<>();
        this.roomList = new ArrayList<>();
        this.bookingList = new HashSet<>();

    }
*/




    /* [Métodos] */


   /* public void getFiles()
    {
        this.bookingList.addAll(FileHelper.getBookingsFromJson());
        this.roomList.addAll(FileHelper.getRoomsFromJson());
        this.userList.addAll(FileHelper.getUsersFromJson());
    }*/

    public boolean removeBooking(Booking bookingDeleted)
    {
        if (bookingList != null) {
            for (Booking variable : bookingList) {
                if (bookingDeleted == variable) {
                    bookingList.remove(variable);

                    return true;
                }
            }
        }
        return false;
    }

    public Room verifyBooking(LocalDateTime checkIn, LocalDateTime checkOut, RoomType roomType)
    {

        if (roomList != null) {

            for (Booking bookedRoom : bookingList) {
                if (bookedRoom.getBookedRoom().getRoomType() == roomType && checkIn.isAfter(bookedRoom.getCheckInDate()) && checkOut.isBefore(bookedRoom.getCheckOutDate())) {
                    return bookedRoom.getBookedRoom();
                }
            }
        }
        else {
            return null;
        }
        return null;
    }


    public Booking makeBooking(String checkIn, String checkOut, RoomType roomType, Passenger bookingPassenger)
    {

        Room newRoom = verifyBooking(Booking.stringToLocalDateTime(checkIn), Booking.stringToLocalDateTime(checkOut), roomType);

        if (newRoom != null) {
            Booking newBooking = new Booking();
            newBooking.setCheckInDate(Booking.stringToLocalDateTime(checkIn));
            newBooking.setCheckOutDate(Booking.stringToLocalDateTime(checkOut));
            newBooking.setBookedRoom(newRoom);
            newBooking.setBookingPassenger(bookingPassenger);
            newBooking.setBookingState(BookingState.PENDING);

            insertBooking(newBooking);

            return newBooking;
        }

        return null;
    }

    public List getBookingByRoom(Room room)
    {

        if (bookingList != null) {
            int flag = 0;
            List<Booking> bookings = new ArrayList<>();

            for (Booking booking : bookingList) {

                if (booking.getBookedRoom().equals(room)) {  // consultar si hace falta sobreescribir el método equals y hashcode

                    flag = 1;
                    bookings.add(booking);
                }
            }

            if (flag != 0) {
                return bookings;
            }
            else
                return null;
        }
        return null;
    }

    public void insertBooking(Booking booking)
    {

        bookingList.add(booking);

    }

    public ArrayList<Passenger> getAllPassengers()
    {
        if (userList != null) {
            ArrayList<Passenger> auxList = new ArrayList<Passenger>();

            for (User variable : userList) {
                if (variable instanceof Passenger) {
                    auxList.add((Passenger) variable);
                }
            }

            if (!(auxList.isEmpty())) //Considero que no se haya encontrado ningun pasajero.
            {
                return auxList;
            }
        }

        return null;

    }

    public boolean addPassenger(Passenger newPassenger)
    {
        return userList.add(newPassenger);
    }


    //getBookingByDni - retorna List porque puede haber mas de un Booking realizado por una misma persona

    public Booking getBookingByDni(String wantedDni)
    {
        if (bookingList != null)
        {
            for (Booking variable : bookingList)
            {
                if (variable.getBookingPassenger().getDni().equals(wantedDni))
                {
                    return variable;
                }
            }
        }
        return null;
    }


    //getpassengerbyDni por dni

    public Passenger getpassengerbyDni(String dni)
    {

        if (userList != null) {

            for (User user : userList) {
                if (user instanceof Passenger && user.getDni().equals(dni)) {

                    return (Passenger) user;
                }
            }
        }
        return null;

    }


    public void setProductToRoomService(int productChoice, Booking booking)
    {

        for (Product product : Product.values()) {

            if (product.ordinal() == productChoice) {

                booking.setSpentMoney(booking.getSpentMoney() + product.getValue());
            }
        }
    }


    public int getHotelSize()
    {
        if (roomList != null) {
            int roomCounter = 0;

            for (Room variable : roomList) {
                roomCounter++;
            }

            return roomCounter;
        }
        else
            return 0;
    }


    public boolean seeWorkers()
    {
        if (userList != null) {
            for (Object variable : userList) {
                if (variable instanceof Employee || variable instanceof Admin) {
                    System.out.println(variable.toString()); //Consultar como evitar este print
                }
            }

            return true;
        }
        else
            return false;
    }


    public Room getRoom(int roomNumber)
    {
        if (roomList != null) {
            for (Room variable : roomList) {
                if (variable.getRoomNumber() == roomNumber) {
                    return variable;
                }

            }

        }

        return null;
    }

    public void seeRooms ()
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                variable.toString();
            }
        }
    }

    public boolean checkDateAvailability(LocalDateTime lookedDate)
    {
        if (bookingList == null) {
            return true;
        }
        else {
            for (Booking variable : bookingList) {
                if (variable.getCheckInDate() == lookedDate) {
                    return false;
                }
            }

            return true;
        }

    }

    public boolean checkIn(Booking booking)
    {
        if (roomList != null) {
            for (Room variable : roomList) {
                if (booking.getBookedRoom().equals(variable)) {
                    changeRoomAvailability(variable);
                    booking.setBookingState(BookingState.INITIATED);
                    return true;
                }
            }

        }

        return false;
    }

    public boolean checkOut(Booking booking)
    {
        if (roomList != null) {
            for (Room variable : roomList) {
                if (booking.getBookedRoom().equals(variable)) {

                    changeRoomAvailability(variable);
                    booking.setBookingState(BookingState.FINALIZED);
                    booking.getBookingPassenger().setHistory(getTicket(booking.getBookingId()));

                    return true;

                }
            }
        }

        return false;
    }

    public void changeRoomAvailability(Room roomChanged)
    {
        if (roomChanged.isAvailable()) {
            roomChanged.setAvailable(false);
        }
        else
            roomChanged.setAvailable(true);
    }

    public Ticket getTicket(String bookingId)
    {
        if (this.bookingList != null) {

            for (Booking booking : bookingList) {

                if (booking.getBookingId().equals(bookingId)) {

                    Ticket newTicket = new Ticket(booking.getCheckInDate(), booking.getCheckOutDate(), booking.getBookedRoom(), booking.getSpentMoney());

                    return newTicket;
                }
            }
        }
        return null;
    }

    public void uploadRooms()
    {
        ArrayList<Room> uploadedRooms = new ArrayList<>();

        Room newRoom = new Room(true,101,RoomType.TRIPLE);
        Room newRoom2 = new Room(true,102,RoomType.MATRIMONIAL);
        Room newRoom3 = new Room(true,103,RoomType.QUAD);
        Room newRoom4 = new Room(true,104,RoomType.SINGLE);
        Room newRoom5 = new Room(true,105,RoomType.TWIN);


        Room newRoom6 = new Room(true,201,RoomType.TRIPLE);
        Room newRoom7 = new Room(true,202,RoomType.MATRIMONIAL);
        Room newRoom8 = new Room(true,203,RoomType.QUAD);
        Room newRoom9 = new Room(true,204,RoomType.SINGLE);
        Room newRoom10 = new Room(true,205,RoomType.TWIN);


        Room newRoom11 = new Room(true,301,RoomType.TRIPLE);
        Room newRoom12 = new Room(true,302,RoomType.MATRIMONIAL);
        Room newRoom13 = new Room(true,303,RoomType.QUAD);
        Room newRoom14 = new Room(true,304,RoomType.SINGLE);
        Room newRoom15 = new Room(true,305,RoomType.TWIN);

        uploadedRooms.add(newRoom);
        uploadedRooms.add(newRoom2);
        uploadedRooms.add(newRoom3);
        uploadedRooms.add(newRoom4);
        uploadedRooms.add(newRoom5);
        uploadedRooms.add(newRoom6);
        uploadedRooms.add(newRoom7);
        uploadedRooms.add(newRoom8);
        uploadedRooms.add(newRoom9);
        uploadedRooms.add(newRoom10);
        uploadedRooms.add(newRoom11);
        uploadedRooms.add(newRoom12);
        uploadedRooms.add(newRoom13);
        uploadedRooms.add(newRoom14);
        uploadedRooms.add(newRoom15);

        FileHelper.setRoomsToJson(uploadedRooms);

    }


    public void save()
    {
        FileHelper.setUsersToJson((ArrayList<User>) this.userList);
        FileHelper.setRoomsToJson(this.roomList);
        FileHelper.setBookingsToJson(this.bookingList);

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

    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(ArrayList<User> userList)
    {
        this.userList = userList;
    }

}
