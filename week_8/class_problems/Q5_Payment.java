package week_8.class_problems;
import java.util.ArrayList;

public class Q5_Payment {

    static class Customer {

        String name;

        public Customer(String name) {
            this.name = name;
        }
    }

    static class Product {

        String name;
        double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    static class OrderItem {

        Product product;
        int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public double getTotal() {
            return product.price * quantity;
        }
    }

    interface PaymentMethod {

        boolean processPayment(double amount);
    }

    static class CreditCardPayment implements PaymentMethod {

        @Override
        public boolean processPayment(double amount) {
            return true;
        }
    }

    static class PayPalPayment implements PaymentMethod {

        @Override
        public boolean processPayment(double amount) {
            return false;
        }
    }

    static class BankTransferPayment implements PaymentMethod {

        @Override
        public boolean processPayment(double amount) {
            return true;
        }
    }

    static class Order {

        Customer customer;
        ArrayList<OrderItem> items = new ArrayList<>();
        String status = "Pending";

        public Order(Customer customer) {
            this.customer = customer;

            System.out.println(
                "Order created for " + customer.name + "."
            );
        }

        public void addProduct(Product product, int quantity) {
            items.add(new OrderItem(product, quantity));
        }

        public double getTotal() {

            double total = 0;

            for (OrderItem item : items) {
                total += item.getTotal();
            }

            return total;
        }

        public void pay(PaymentMethod paymentMethod) {

            if (items.isEmpty()) {
                System.out.println(
                    "Cannot process payment for an empty order."
                );
                return;
            }

            System.out.println(
                "Payment initiated for order of "
                + customer.name + "."
            );

            boolean success =
                paymentMethod.processPayment(getTotal());

            if (success) {
                status = "Paid";

                System.out.println(
                    "Payment successful."
                );
            } else {
                System.out.println(
                    "Payment failed."
                );
            }

            System.out.println(
                "Order status: " + status
            );
        }
    }

    public static void main(String[] args) {

        Customer customerX =
            new Customer("Customer X");

        Product productA =
            new Product("Product A", 100);

        Product productB =
            new Product("Product B", 50);

        Order orderX =
            new Order(customerX);

        orderX.addProduct(productA, 2);
        orderX.addProduct(productB, 1);

        orderX.pay(
            new CreditCardPayment()
        );


        Customer customerY =
            new Customer("Customer Y");

        Order orderY =
            new Order(customerY);

        orderY.pay(
            new CreditCardPayment()
        );


        Customer customerZ =
            new Customer("Customer Z");

        Product productC =
            new Product("Product C", 200);

        Order orderZ =
            new Order(customerZ);

        orderZ.addProduct(productC, 1);

        orderZ.pay(
            new PayPalPayment()
        );
    }
}