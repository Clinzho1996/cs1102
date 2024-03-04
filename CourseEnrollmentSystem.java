import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CourseEnrollmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseManagement courseManagement = new CourseManagement();

        // Declaration of courseCode outside the loop
        String courseCode;

        // Administrator Interface
        int choice;
        do {
            System.out.println("\nCourse Enrollment and Grade Management System");
            System.out.println("1. Add new course");
            System.out.println("2. Enroll student");
            System.out.println("3. Assign grade");
            System.out.println("4. Calculate overall grade");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nAdd New Course:");
                    System.out.print("Enter course code: ");
                    courseCode = scanner.next(); // Use next() instead of nextLine()
                    System.out.print("Enter course name: ");
                    scanner.nextLine(); // Consume newline
                    String courseName = scanner.nextLine();
                    System.out.print("Enter maximum capacity: ");
                    int maxCapacity = scanner.nextInt();
                    System.out.print("Enter course credits: ");
                    int credits = scanner.nextInt();
                    courseManagement.addCourse(courseCode, courseName, maxCapacity, credits);
                    break;
                case 2:
                    System.out.println("\nEnroll Student:");
                    System.out.print("Enter student name: ");
                    scanner.nextLine(); // Consume newline
                    String studentName = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    System.out.print("Enter course code to enroll: ");
                    courseCode = scanner.next();
                    courseManagement.enrollStudent(studentName, studentID, courseCode);
                    break;
                case 3:
                    System.out.println("\nAssign Grade:");
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter course code: ");
                    courseCode = scanner.next();
                    System.out.print("Enter grade: ");
                    int grade = scanner.nextInt();
                    courseManagement.assignGrade(studentID, courseCode, grade);
                    break;
                case 4:
                    System.out.println("\nCalculate Overall Grade:");
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextInt();
                    courseManagement.calculateOverallGrade(studentID);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }
}

class Student {
    private String name;
    private int studentID;
    private List<Course> enrolledCourses;
    private Map<Course, Integer> grades; // New field to store grades

    public Student(String name, int studentID) {
        this.name = name;
        this.studentID = studentID;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getStudentID() {
        return studentID;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void assignGrade(Course course, int grade) {
        // Check if the student is enrolled in the specified course
        if (enrolledCourses.contains(course)) {
            // If the grades map does not exist, create a new one
            if (grades == null) {
                grades = new HashMap<>();
            }

            // Assign the grade for the specified course
            grades.put(course, grade);
            System.out.println("Grade assigned successfully.");
        } else {
            System.out.println("Student is not enrolled in the specified course.");
        }
    }

    public double calculateOverallGrade() {
        if (grades == null || grades.isEmpty()) {
            System.out.println("No grades available for the student.");
            return 0.0;
        }

        int totalCredits = 0;
        double weightedSum = 0.0;

        for (Map.Entry<Course, Integer> entry : grades.entrySet()) {
            Course course = entry.getKey();
            int grade = entry.getValue();
            int credits = course.getCredits(); // Adjust this based on your actual data structure

            totalCredits += credits;
            weightedSum += grade * credits;
        }

        if (totalCredits > 0) {
            double overallGrade = weightedSum / totalCredits;
            System.out.println("Overall grade calculated: " + overallGrade);
            return overallGrade;
        } else {
            System.out.println("Total credits are zero. Cannot calculate overall grade.");
            return 0.0;
        }
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private int totalEnrolledStudents;
    private int credits; // New field to store course credits

    public Course(String courseCode, String courseName, int maxCapacity, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.totalEnrolledStudents = 0;
        this.credits = credits;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    public int getCredits() {
        return credits;
    }

    public void enrollStudent() {
        if (totalEnrolledStudents < maxCapacity) {
            totalEnrolledStudents++;
        } else {
            System.out.println("Course capacity reached. Cannot enroll more students.");
        }
    }
}

class CourseManagement {
    private List<Course> courses;
    private List<Student> students;

    public CourseManagement() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(String courseCode, String courseName, int maxCapacity, int credits) {
        Course newCourse = new Course(courseCode, courseName, maxCapacity, credits);
        courses.add(newCourse);
    }

    public void enrollStudent(String name, int studentID, String courseCode) {
        Course course = findCourse(courseCode);
        if (course != null) {
            Student student = new Student(name, studentID); // Corrected instantiation of Student
            course.enrollStudent();
            student.enrollInCourse(course);
            students.add(student);
            System.out.println("Student enrolled successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void assignGrade(int studentID, String courseCode, int grade) {
        Student student = findStudent(studentID);
        Course course = findCourse(courseCode);

        if (student != null && course != null) {
            student.assignGrade(course, grade);
            System.out.println("Grade assigned successfully.");
        } else {
            System.out.println("Student or course not found.");
        }
    }

    public void calculateOverallGrade(int studentID) {
        Student student = findStudent(studentID);

        if (student != null) {
            student.calculateOverallGrade();
        } else {
            System.out.println("Student not found.");
        }
    }

    private Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private Student findStudent(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }
}
