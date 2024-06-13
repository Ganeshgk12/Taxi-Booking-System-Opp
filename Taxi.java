package TaxiBookingSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Taxi implements Comparable<Taxi>{

    private int taxiId;
    private static int id = 101;
    private char currentSpot;
    private int freeTime;
    private int totalEarnings;
    private boolean booked;
    private List<String> trips;

    public Taxi (){
        this.taxiId = id++;
        this.currentSpot = 'A';
        this.freeTime = 6;
        this.totalEarnings = 0;
        this.booked = false;
        this.trips = new ArrayList<String>();
    }


    public char getCurrentSpot() {
        return currentSpot;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public int getTaxiId() {
        return taxiId;
    }

    public int getTotalEarning() {
        return totalEarnings;
    }

    @Override
    public int compareTo(Taxi o) {
        return Integer.compare(this.totalEarnings, o.totalEarnings);
    }

    public void setDetails(boolean booked,char currentSpot,int freeTime,int totalEarnings,String tripDetail)
    {
        this.booked = booked;
        this.currentSpot = currentSpot;
        this.freeTime = freeTime;
        this.totalEarnings = totalEarnings;
        this.trips.add(tripDetail);
    }

    public void printDetails() {
        System.out.println("Taxi - "+ this.taxiId + " Total Earnings - " + this.totalEarnings);
        System.out.println("TaxiID    BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
        System.out.println("  |          |             |           |      |        |             |           | ");
        System.out.println("  -          -             -           -      -        -             -           - ");
        for(String trip : trips)
        {
            System.out.println(trip);
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public void printTaxiDetails(){
        System.out.println("Taxi - "+ this.taxiId + " Total Earnings - " + this.totalEarnings + " Current spot - " + this.currentSpot +" Free Time - " + this.freeTime);
        System.out.println("-".repeat(50));
    }


}
