package org.example;

public class Room {

    private boolean isAvailable;
    private Passenger roomGuest;
    private int roomNumber;
    private RoomType roomType;


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Passenger getGuest() {
        return roomGuest;
    }

    public void setGuest(Passenger guest) {
        this.roomGuest = guest;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
