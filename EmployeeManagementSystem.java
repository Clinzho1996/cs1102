import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("John Doe", 35, "Engineering", 60000),
                new Employee("Jane Smith", 28, "HR", 50000),
                new Employee("David Johnson", 40, "Finance", 70000),
                new Employee("Emily Davis", 32, "Marketing", 55000));

        // Function to concatenate name and department
        Function<Employee, String> concatenateNameAndDept = emp -> emp.getName() + " - " + emp.getDepartment();

        // Stream to generate concatenated strings
        List<String> concatenatedNames = employees.stream()
                .map(concatenateNameAndDept)
                .collect(Collectors.toList());

        // Print concatenated names
        System.out.println("Concatenated Names:");
        concatenatedNames.forEach(System.out::println);

        // Calculate average salary
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
        System.out.println("\nAverage Salary: " + averageSalary);

        // Filter employees above age threshold
        int ageThreshold = 30;
        List<Employee> filteredEmployees = employees.stream()
                .filter(emp -> emp.getAge() > ageThreshold)
                .collect(Collectors.toList());

        // Print filtered employees
        System.out.println("\nEmployees above " + ageThreshold + " years old:");
        filteredEmployees.forEach(emp -> System.out.println(emp.getName() + " - Age: " + emp.getAge()));
    }
}
