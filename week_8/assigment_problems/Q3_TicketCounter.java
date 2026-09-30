package week_8.assigment_problems;

import java.util.ArrayList;

public class Q3_TicketCounter {

    static abstract class Seat {

        String seatNumber;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public abstract double getPrice();
    }

    static class RegularSeat extends Seat {

        public RegularSeat(String seatNumber) {
            super(seatNumber);
        }

        public double getPrice() {
            return 150;
        }
    }

    static class PremiumSeat extends Seat {

        public PremiumSeat(String seatNumber) {
            super(seatNumber);
        }

        public double getPrice() {
            return 250;
        }
    }

    static class ReclinerSeat extends Seat {

        public ReclinerSeat(String seatNumber) {
            super(seatNumber);
        }

        public double getPrice() {
            return 400;
        }
    }

    static class Customer {

        String name;

        public Customer(String name) {
            this.name = name;
        }
    }

    static class Show {

        String showTime;
        ArrayList<Seat> bookedSeats =
            new ArrayList<>();

        public Show(String showTime) {
            this.showTime = showTime;
        }

        public boolean isAvailable(Seat seat) {
            return !bookedSeats.contains(seat);
        }

        public Booking book(
                Customer customer,
                Seat[] seats) {

            if (seats.length > 6) {
                System.out.println(
                    "Maximum 6 seats per booking."
                );
                return null;
            }

            for (Seat seat : seats) {
                if (!isAvailable(seat)) {
                    System.out.println(
                        "Seat "
                        + seat.seatNumber
                        + " is already booked for this show."
                    );
                    return null;
                }
            }

            for (Seat seat : seats) {
                bookedSeats.add(seat);
            }

            Booking booking =
                new Booking(
                    customer,
                    this,
                    seats
                );

            booking.printBooking();

            return booking;
        }

        public void releaseSeats(Seat[] seats) {

            for (Seat seat : seats) {
                bookedSeats.remove(seat);
            }
        }
    }

    static class Booking {

        Customer customer;
        Show show;
        Seat[] seats;
        boolean active = true;

        public Booking(
                Customer customer,
                Show show,
                Seat[] seats) {

            this.customer = customer;
            this.show = show;
            this.seats = seats;
        }

        public double getTotal() {

            double total = 0;

            for (Seat seat : seats) {
                total += seat.getPrice();
            }

            return total;
        }

        public void printBooking() {

            System.out.print(
                "Booking confirmed for "
                + customer.name + ": "
            );

            for (int i = 0; i < seats.length; i++) {

                System.out.print(
                    seats[i].seatNumber
                );

                if (i < seats.length - 1) {
                    System.out.print(", ");
                }
            }

            System.out.printf(
                ". Total: ₹%.2f%n",
                getTotal()
            );
        }

        public void cancel() {

            if (!active) {
                return;
            }

            active = false;

            show.releaseSeats(seats);

            System.out.println(
                customer.name
                + "'s booking cancelled."
            );

            System.out.print("Seats ");

            for (int i = 0; i < seats.length; i++) {

                System.out.print(
                    seats[i].seatNumber
                );

                if (i < seats.length - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println(" released.");
        }
    }

    public static void main(String[] args) {

        Show show = new Show("7 PM");

        Customer asha =
            new Customer("Asha");

        Customer ravi =
            new Customer("Ravi");

        Customer neha =
            new Customer("Neha");

        Seat a1 =
            new RegularSeat("A1");

        Seat a2 =
            new RegularSeat("A2");

        Seat f5 =
            new PremiumSeat("F5");

        Seat r1 =
            new ReclinerSeat("R1");

        Booking ashaBooking =
            show.book(
                asha,
                new Seat[]{a1, a2, f5}
            );

        show.book(
            ravi,
            new Seat[]{a2}
        );

        show.book(
            ravi,
            new Seat[]{r1}
        );

        ashaBooking.cancel();

        show.book(
            neha,
            new Seat[]{a2}
        );
    }
}