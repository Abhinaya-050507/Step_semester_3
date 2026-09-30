package week_8.class_problems;

import java.util.*;

public class Q4_HotelBooking {

    static abstract class Room {

        protected String roomNumber;
        protected boolean available = true;

        public Room(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public abstract double calculatePrice(int days);

        public boolean isAvailable() {
            return available;
        }
    }

    static class StandardRoom extends Room {

        public StandardRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(int days) {
            return days * 100;
        }
    }

    static class DeluxeRoom extends Room {

        public DeluxeRoom(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(int days) {
            return days * 150;
        }
    }

    static class Suite extends Room {

        public Suite(String roomNumber) {
            super(roomNumber);
        }

        @Override
        public double calculatePrice(int days) {
            return days * 250;
        }
    }

    static class Customer {

        String name;

        public Customer(String name) {
            this.name = name;
        }
    }

    static class Reservation {

        Customer customer;
        Room room;
        String startDate;
        String endDate;
        int days;
        boolean active = true;

        public Reservation(
                Customer customer,
                Room room,
                String startDate,
                String endDate,
                int days) {

            this.customer = customer;
            this.room = room;
            this.startDate = startDate;
            this.endDate = endDate;
            this.days = days;
        }

        public double getPrice() {
            return room.calculatePrice(days);
        }
    }

    static class BookingSystem {

        ArrayList<Reservation> reservations =
            new ArrayList<>();

        public boolean isAvailable(Room room) {

            for (Reservation reservation : reservations) {

                if (reservation.room == room
                        && reservation.active) {
                    return false;
                }
            }

            return true;
        }

        public void checkAvailability(
                Room room,
                String startDate,
                String endDate) {

            if (isAvailable(room)) {
                System.out.println(
                    room.roomNumber
                    + " is available from "
                    + startDate + " to " + endDate + "."
                );
            } else {
                System.out.println(
                    room.roomNumber
                    + " is not available from "
                    + startDate + " to " + endDate + "."
                );
            }
        }

        public Reservation reserve(
                Customer customer,
                Room room,
                String startDate,
                String endDate,
                int days) {

            if (!isAvailable(room)) {
                System.out.println(
                    room.roomNumber
                    + " is not available from "
                    + startDate + " to " + endDate + "."
                );
                return null;
            }

            Reservation reservation =
                new Reservation(
                    customer,
                    room,
                    startDate,
                    endDate,
                    days
                );

            reservations.add(reservation);
            room.available = false;

            System.out.println(
                "Reservation confirmed for "
                + customer.name + ", "
                + room.getClass().getSimpleName()
                + " " + room.roomNumber
                + " (" + startDate + "-" + endDate + ")."
            );

            System.out.println(
                "Price: $" + reservation.getPrice()
            );

            return reservation;
        }

        public void cancel(Reservation reservation) {

            if (reservation != null
                    && reservation.active) {

                reservation.active = false;
                reservation.room.available = true;

                System.out.println(
                    "Reservation for "
                    + reservation.customer.name
                    + ", "
                    + reservation.room.getClass().getSimpleName()
                    + " "
                    + reservation.room.roomNumber
                    + " cancelled successfully."
                );
            }
        }
    }

    public static void main(String[] args) {

        BookingSystem system =
            new BookingSystem();

        Customer customerA =
            new Customer("Customer A");

        Customer customerB =
            new Customer("Customer B");

        Customer customerC =
            new Customer("Customer C");

        Room standard101 =
            new StandardRoom("101");

        Room deluxe201 =
            new DeluxeRoom("201");

        system.checkAvailability(
            standard101,
            "Jan 1",
            "Jan 5"
        );

        Reservation r1 =
            system.reserve(
                customerA,
                standard101,
                "Jan 1",
                "Jan 5",
                4
            );

        system.reserve(
            customerB,
            standard101,
            "Jan 3",
            "Jan 7",
            4
        );

        system.cancel(r1);

        system.reserve(
            customerC,
            deluxe201,
            "Feb 10",
            "Feb 12",
            2
        );
    }
}