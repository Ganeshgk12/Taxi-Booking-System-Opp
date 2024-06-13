package TaxiBookingSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Booking {
    private static int bookingId = 1;
    public static Taxi nearesstTaxi(Customer customer,List<Taxi> freeTaxis) {

        int min = Integer.MAX_VALUE;
        Taxi t = null;

        for (Taxi taxi : freeTaxis) {
            int distanceBetweenCustomerAndTaxi = Math.abs((taxi.getCurrentSpot() - '0')
                    - (customer.getPickupSpot() - '0')) * 15;
            if (distanceBetweenCustomerAndTaxi < min) {
                min = distanceBetweenCustomerAndTaxi;
                t = taxi;
            }
        }
        return t;
    }

    public static void bookTaxi (Taxi taxi,Customer customer) {

        int distanceBetweenPicupDrop = Math.abs((customer.getDropPoint() - '0') -
                (customer.getPickupSpot() - '0')) * 15;

        int earningOfTheTrip = (distanceBetweenPicupDrop - 5) * 10 + 100;
        int dropTime = customer.getPickupTime() + distanceBetweenPicupDrop / 15;
        int nextFreeTimeOfTaxi = dropTime;
        char nextStop = customer.getDropPoint();

        String tripDetails =  taxi.getTaxiId() + "            " + Booking.bookingId++ + "            " + customer.getCutomerId() + "         " + customer.getPickupSpot() +  "       " + customer.getDropPoint() + "        " + customer.getPickupTime() + "            " +dropTime + "            " + earningOfTheTrip;

        taxi.setDetails(true,nextStop,nextFreeTimeOfTaxi,taxi.getTotalEarning() + earningOfTheTrip,tripDetails);

        System.out.println("Taxi " + taxi.getTaxiId() + " Booked");
        System.out.println("^".repeat(30));
    }


    public static List<Taxi> getFreeTaxis (List<Taxi> taxis,int pickUpTime, char pickUpPoint) {
        List<Taxi> freeTaxi = new ArrayList<>();

        for (Taxi taxi : taxis) {
            if (taxi.getFreeTime() <= pickUpTime && (Math.abs((taxi.getCurrentSpot() - '0')
                    - (pickUpPoint - '0'))) <= pickUpTime - taxi.getFreeTime()){

                freeTaxi.add(taxi);
            }
        }
        return freeTaxi;

    }
    public static List<Taxi> createTaxis (int n) {

        List<Taxi> taxis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }

    public static void main(String[] args) {

        List<Taxi> taxis = createTaxis(5);
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("1. Book Taxi");
            System.out.println("2. Print Taxi Details");
            System.out.println("   Enter Any No to Exit");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Pick up Point - (A to F)");
                    char pickup = sc.next().charAt(0);
                    if (pickup < 'A' || pickup > 'F') {
                        System.out.println("Please Enter valid Pick up point");
                        return;
                    }
                    System.out.println("Enter Drop Point - (A to F)");
                    char drop = sc.next().charAt(0);
                    if (drop < 'A' || drop > 'F') {
                        System.out.println("Please Enter valid Drop point");
                        return;
                    }
                    System.out.println("Enter Pick up Time - (1 to 12)");
                    int picupTime = sc.nextInt();
                    sc.nextLine();
                    List<Taxi> freeTaxis = getFreeTaxis(taxis,picupTime,pickup);

                    if (freeTaxis.isEmpty()) {
                        System.out.println("Sorry No Taxis Available Now ! Exitting ");
                        return;
                    }
                    Collections.sort(freeTaxis);
                    Customer customer = new Customer(pickup,drop,picupTime);
                    Taxi nearst = nearesstTaxi(customer,freeTaxis);
                    bookTaxi(nearst,customer);
                    break;

                case 2:
                    for(Taxi t : taxis) {
                        t.printTaxiDetails();
                    }
                    for(Taxi t : taxis) {
                        t.printDetails();
                    }
                    break;

                default:
                    return;
            }
        } while (true);
    }
}
