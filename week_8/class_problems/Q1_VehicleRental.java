package week_8.class_problems;
import java.util.*;

public class Q1_VehicleRental {

    static abstract class Vehicle {

        protected String name;
        protected boolean available = true;

        public Vehicle(String name) {
            this.name = name;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public abstract double calculateCharge(int days);
    }

    static class Sedan extends Vehicle {

        public Sedan(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 50;
        }
    }

    static class SUV extends Vehicle {

        public SUV(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 80;
        }
    }

    static class Truck extends Vehicle {

        public Truck(String name) {
            super(name);
        }

        @Override
        public double calculateCharge(int days) {
            return days * 100;
        }
    }

    static class Customer {

        String name;

        public Customer(String name) {
            this.name = name;
        }
    }

    static class Rental {

        Vehicle vehicle;
        Customer customer;
        int days;

        public Rental(Vehicle vehicle, Customer customer, int days) {
            this.vehicle = vehicle;
            this.customer = customer;
            this.days = days;
        }

        public double getCharge() {
            return vehicle.calculateCharge(days);
        }
    }

    static class RentalSystem {

        ArrayList<Rental> rentals = new ArrayList<>();

        public void rentVehicle(
                Customer customer,
                Vehicle vehicle,
                int days) {

            if (!vehicle.isAvailable()) {
                System.out.println(
                    vehicle.name + " is currently unavailable."
                );
                return;
            }

            Rental rental =
                new Rental(vehicle, customer, days);

            rentals.add(rental);
            vehicle.setAvailable(false);

            System.out.println(
                vehicle.name + " rented successfully by "
                + customer.name + "."
            );

            System.out.println(
                "Rental charge: $" + rental.getCharge()
            );
        }

        public void returnVehicle(
                Customer customer,
                Vehicle vehicle) {

            for (Rental rental : rentals) {

                if (rental.vehicle == vehicle
                        && rental.customer == customer) {

                    vehicle.setAvailable(true);
                    rentals.remove(rental);

                    System.out.println(
                        vehicle.name + " returned by "
                        + customer.name + "."
                    );

                    return;
                }
            }
        }
    }

    public static void main(String[] args) {

        RentalSystem system = new RentalSystem();

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        system.rentVehicle(c1, sedanA, 3);

        system.rentVehicle(c2, sedanA, 2);

        system.returnVehicle(c1, sedanA);

        system.rentVehicle(c3, suvB, 5);
    }
}