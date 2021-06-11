package org.example;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.UUID;

public final class Ticket
{
    /* [Atributos] */

    private final String ID;
    private final LocalDateTime checkInDate,checkOutDate;
    private final Room roomUsed;
    private final double moneyPaid;


    /* [Constructor] */


    public Ticket(LocalDateTime checkInDate, LocalDateTime checkOutDate,Room roomUsed, double moneyPaid)
    {
        this.ID = shortUUID();
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomUsed = roomUsed;
        this.moneyPaid = moneyPaid;
    }


    /* [Métodos] */

    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

    @Override
    public String toString()
    {
        return  "Ticket ID ='" + ID + '\'' +
                ", CheckIn date= " + checkInDate +
                ", CheckOut date= " + checkOutDate +
                ", Room used = " + roomUsed +
                ", Money spent =" + moneyPaid +
                "} \n";
    }
}
