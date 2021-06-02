package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Hotel
{
    private boolean isOpen;
    private List userList;
    private List roomList;
    private List bookingList;

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

    public void setOpen(boolean open)
    {
        isOpen = open;
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

    public Passenger getPassenger(String dniBuscado)
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
        }
            return null;
    }

    public Room getRoom (int roomNumber)
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                if (variable.getRoomNumber == roomNumber)
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
               if (booking.getCheckInDate() == lookedDate)
               {
                   return false;
               }
           }

           return true;
       }

    }

    public void changeRoomAvailability(Room roomChanged)
    {
        if ((roomChanged.getIsAvailable) == true)
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
                if (booking.getBookedRoom.equals(variable))
                {
                    variable.setAvailable(false);

                    booking.setState(ON_CURRENCY);
                }

            }

            return true;
        }
        else
            return false;

    }

    public boolean checkOut (Booking booking)
    {
        if (roomList != null)
        {
            for (Room variable : roomList)
            {
                if (booking.getBookedRoom.equals(variable))
                {
                    variable.setAvailable(true);

                    booking.setState(FINISHED);
                }

            }

        }
    }

}
