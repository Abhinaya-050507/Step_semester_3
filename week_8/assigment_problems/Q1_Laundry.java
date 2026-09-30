package week_8.assigment_problems;

import java.util.ArrayList;

public class Q1_Laundry {

    interface WashType {
        int getDuration();
        double getCharge();
        String getName();
    }

    static class QuickWash implements WashType {
        public int getDuration() {
            return 30;
        }

        public double getCharge() {
            return 20;
        }

        public String getName() {
            return "Quick";
        }
    }

    static class NormalWash implements WashType {
        public int getDuration() {
            return 45;
        }

        public double getCharge() {
            return 30;
        }

        public String getName() {
            return "Normal";
        }
    }

    static class HeavyWash implements WashType {
        public int getDuration() {
            return 60;
        }

        public double getCharge() {
            return 45;
        }

        public String getName() {
            return "Heavy";
        }
    }

    static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }
    }

    static class WashingMachine {
        private String machineId;
        private boolean busy;

        public WashingMachine(String machineId) {
            this.machineId = machineId;
            busy = false;
        }

        public boolean isBusy() {
            return busy;
        }

        private void setBusy(boolean busy) {
            this.busy = busy;
        }

        public WashCycle startWash(
                Student student,
                WashType washType) {

            if (busy) {
                System.out.println(
                    "Machine " + machineId + " is currently busy."
                );
                return null;
            }

            setBusy(true);

            WashCycle cycle =
                new WashCycle(student, this, washType);

            System.out.println(
                washType.getName()
                + " wash started on "
                + machineId
                + " for "
                + student.name
                + " (" + washType.getDuration() + " min)."
            );

            System.out.printf(
                "Charge: ₹%.2f%n",
                washType.getCharge()
            );

            return cycle;
        }

        private void completeWash() {
            setBusy(false);
            System.out.println(
                machineId + " cycle completed."
            );
            System.out.println(
                machineId + " is now free."
            );
        }
    }

    static class WashCycle {

        Student student;
        WashingMachine machine;
        WashType washType;

        public WashCycle(
                Student student,
                WashingMachine machine,
                WashType washType) {

            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        public void complete() {
            machine.completeWash();
        }
    }

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 =
            new WashingMachine("M1");

        WashingMachine m2 =
            new WashingMachine("M2");

        WashCycle cycle1 =
            m1.startWash(
                asha,
                new QuickWash()
            );

        m1.startWash(
            ravi,
            new HeavyWash()
        );

        WashCycle cycle2 =
            m2.startWash(
                ravi,
                new HeavyWash()
            );

        cycle1.complete();

        m1.startWash(
            neha,
            new NormalWash()
        );
    }
}