package week_3.assignment_problems;

class Employee {
    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees created: " + employeeCount);
    }
}

public class M5_InstanceVsStaticEmployee {
    public static void main(String[] args) {

        Employee e1 = new Employee("Ravi", 50000);
        Employee e2 = new Employee("Priya", 60000);
        Employee e3 = new Employee("Arjun", 45000);

        Employee.printCompanyInfo();
    }
}