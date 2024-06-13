package TaxiBookingSystem;

public class Customer {

    private int cutomerId;
    private static int iid = 1;
    private char pickupSpot;
    private char dropPoint;
    private int pickupTime;

    protected Customer(char currentSpot, char dropPoint,int pickupTime) {
        this.pickupSpot = currentSpot;
        this.dropPoint = dropPoint;
        this.pickupTime = pickupTime;
        this.cutomerId = iid++;
    }

    public char getPickupSpot() {
        return pickupSpot;
    }

    public int getCutomerId() {
        return cutomerId;
    }

    public char getDropPoint() {
        return dropPoint;
    }

    public int getPickupTime() {
        return pickupTime;
    }
}
