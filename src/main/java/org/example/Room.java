package org.example;

public class Room {

    private boolean isAvailable; // setear en false cuando el estado del booking sea INITIATED, volver a true cuando sea FINALIZED
    private Passenger roomGuest;
    private int roomNumber;
    private RoomType roomType;

    public Room() {
    }

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


    @Override
    public String toString() {
        return "Room{" +
                "isAvailable=" + isAvailable +
                ", roomGuest=" + roomGuest +
                ", roomNumber=" + roomNumber +
                ", roomType=" + roomType +
                '}';
    }
}
