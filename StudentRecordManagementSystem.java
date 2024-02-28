import java.util.Scanner;

class Student {
    String name;
    int id;
    double age;
    double grade;

    public Student(String name, int id, double age, double grade) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.grade = grade;
    }
}

class StudentManagement {
    static int totalStudents = 0;
    static Student[] students = new Student[100];

    public static void addStudent(String name, double age, double grade) {
        students[totalStudents++] = new Student(name, totalStudents, age, grade);
        System.out.println("Student added successfully!");
    }

    public static void updateStudent(int id, String name, double age, double grade) {
        for (int i = 0; i < totalStudents; i++) {
            if (students[i].id == id) {
                students[i].name = name;
                students[i].age = age;
                students[i].grade = grade;
                System.out.println("Student information updated successfully!");
                return;
            }
        }
        System.out.println("Student ID not found.");
    }

    public static void viewStudentDetails(int id) {
        for (int i = 0; i < totalStudents; i++) {
            if (students[i].id == id) {
                System.out.println("Student ID: " + students[i].id);
                System.out.println("Name: " + students[i].name);
                System.out.println("Age: " + students[i].age);
                System.out.println("Grade: " + students[i].grade);
                return;
            }
        }
        System.out.println("Student ID not found.");
    }
}

public class StudentRecordManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Record Management System");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. View Student Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    double age;
                    while (true) {
                        System.out.print("Enter student age: ");
                        try {
                            age = Double.parseDouble(scanner.nextLine().trim());
                            break; // Break out of the loop if parsing is successful
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter a valid number for age.");
                        }
                    }

                    double grade;
                    while (true) {
                        System.out.print("Enter student grade: ");
                        try {
                            grade = Double.parseDouble(scanner.nextLine().trim());
                            break; // Break out of the loop if parsing is successful
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter a valid number for grade.");
                        }
                    }

                    StudentManagement.addStudent(name, age, grade);
                    break;

                case 2:
                    System.out.print("Enter student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Enter new student name: ");
                    String updateName = scanner.nextLine();

                    double updateAge;
                    while (true) {
                        System.out.print("Enter new student age: ");
                        try {
                            updateAge = Double.parseDouble(scanner.nextLine().trim());
                            break; // Break out of the loop if parsing is successful
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter a valid number for age.");
                        }
                    }

                    double updateGrade;
                    while (true) {
                        System.out.print("Enter new student grade: ");
                        try {
                            updateGrade = Double.parseDouble(scanner.nextLine().trim());
                            break; // Break out of the loop if parsing is successful
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter a valid number for grade.");
                        }
                    }

                    StudentManagement.updateStudent(updateId, updateName, updateAge, updateGrade);
                    break;

                case 3:
                    System.out.print("Enter student ID to view details: ");
                    int viewId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    StudentManagement.viewStudentDetails(viewId);
                    break;

                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
