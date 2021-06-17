package org.example.entities;

public class Room {

    private boolean isAvailable; // setear en false cuando el estado del booking sea INITIATED, volver a true cuando sea FINALIZED
    private int roomNumber;
    private RoomType roomType;

    public Room() {
    }

    public Room(boolean isAvailable, int roomNumber, RoomType roomType)
    {
        this.isAvailable = isAvailable;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
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
        return "\t [Habitación N°" + roomNumber +"] \n"+
                "Datos de la habitacion: \n" +
                "Disponibilidad: [" + isAvailable +"]\n"+
                "Tipo: [" + roomType + "] \n" +
                "***";
    }
}
