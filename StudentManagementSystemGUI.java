import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementSystemGUI extends JFrame implements ActionListener {
    private JTextField idField, nameField, courseIdField, gradeField, newCourseIdField, newCourseNameField;
    private JButton addButton, updateButton, viewButton, enrollButton, gradeButton, addCourseButton;
    private JTextArea displayArea;

    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    public StudentManagementSystemGUI() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        idField = new JTextField(10);
        nameField = new JTextField(20);
        courseIdField = new JTextField(10);
        gradeField = new JTextField(5);
        newCourseIdField = new JTextField(10);
        newCourseNameField = new JTextField(20);

        addButton = new JButton("Add Student");
        updateButton = new JButton("Update Student");
        viewButton = new JButton("View Students");
        enrollButton = new JButton("Enroll Student");
        gradeButton = new JButton("Assign Grade");
        addCourseButton = new JButton("Add Course");

        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        viewButton.addActionListener(this);
        enrollButton.addActionListener(this);
        gradeButton.addActionListener(this);
        addCourseButton.addActionListener(this);

        add(new JLabel("ID: "));
        add(idField);
        add(new JLabel("Name: "));
        add(nameField);
        add(addButton);
        add(updateButton);
        add(viewButton);
        add(new JLabel("Course ID: "));
        add(courseIdField);
        add(enrollButton);
        add(new JLabel("Grade: "));
        add(gradeField);
        add(gradeButton);
        add(new JLabel("New Course ID: "));
        add(newCourseIdField);
        add(new JLabel("New Course Name: "));
        add(newCourseNameField);
        add(addCourseButton);
        add(new JScrollPane(displayArea));

        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String id = idField.getText();
            String name = nameField.getText();
            if (!id.isEmpty() && !name.isEmpty()) {
                students.add(new Student(Integer.parseInt(id), name));
                displayArea.append("Added Student: " + name + " (ID: " + id + ")\n");
            } else {
                displayArea.append("Please enter ID and Name.\n");
            }
        } else if (e.getSource() == enrollButton) {
            String studentId = idField.getText();
            String courseId = courseIdField.getText();
            if (!studentId.isEmpty() && !courseId.isEmpty()) {
                boolean studentFound = false;
                boolean courseFound = false;
                for (Student student : students) {
                    if (Integer.toString(student.getId()).equals(studentId)) {
                        studentFound = true;
                        for (Course course : courses) {
                            if (Integer.toString(course.getId()).equals(courseId)) {
                                courseFound = true;
                                student.enroll(course);
                                displayArea.append(
                                        "Enrolled Student " + student.getName() + " in Course " + course.getName()
                                                + "\n");
                                break;
                            }
                        }
                        if (!courseFound) {
                            displayArea.append("Course with ID " + courseId + " not found.\n");
                        }
                        break;
                    }
                }
                if (!studentFound) {
                    displayArea.append("Student with ID " + studentId + " not found.\n");
                }
            } else {
                displayArea.append("Please enter Student ID and Course ID.\n");
            }
        } else if (e.getSource() == gradeButton) {
            String studentId = idField.getText();
            String courseId = courseIdField.getText();
            String gradeText = gradeField.getText();
            if (!studentId.isEmpty() && !courseId.isEmpty() && !gradeText.isEmpty()) {
                try {
                    double grade = Double.parseDouble(gradeText);
                    boolean studentFound = false;
                    boolean courseFound = false;
                    for (Student student : students) {
                        if (Integer.toString(student.getId()).equals(studentId)) {
                            studentFound = true;
                            for (Course course : student.getCourses()) {
                                if (Integer.toString(course.getId()).equals(courseId)) {
                                    courseFound = true;
                                    student.setGrade(course, grade);
                                    displayArea.append("Assigned Grade " + grade + " to Student " + student.getName()
                                            + " for Course " + course.getName() + "\n");
                                    break;
                                }
                            }
                            if (!courseFound) {
                                displayArea.append(
                                        "Student " + student.getName() + " not enrolled in Course with ID " + courseId
                                                + "\n");
                            }
                            break;
                        }
                    }
                    if (!studentFound) {
                        displayArea.append("Student with ID " + studentId + " not found.\n");
                    }
                } catch (NumberFormatException ex) {
                    displayArea.append("Please enter a valid grade.\n");
                }
            } else {
                displayArea.append("Please enter Student ID, Course ID, and Grade.\n");
            }
        } else if (e.getSource() == addCourseButton) {
            String courseId = newCourseIdField.getText();
            String courseName = newCourseNameField.getText();
            if (!courseId.isEmpty() && !courseName.isEmpty()) {
                courses.add(new Course(Integer.parseInt(courseId), courseName));
                displayArea.append("Added Course: " + courseName + " (ID: " + courseId + ")\n");
            } else {
                displayArea.append("Please enter Course ID and Name.\n");
            }
        } else if (e.getSource() == viewButton) {
            for (Student student : students) {
                displayArea.append(student.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        StudentManagementSystemGUI gui = new StudentManagementSystemGUI();
        gui.setVisible(true);
    }
}

class Student {
    private int id;
    private String name;
    private ArrayList<Course> courses;
    private ArrayList<Double> grades;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void enroll(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            grades.add(0.0); // Initialize grade as 0.0
        }
    }

    public void setGrade(Course course, double grade) {
        int index = courses.indexOf(course);
        if (index != -1) {
            grades.set(index, grade);
        }
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", courses=[");
        for (int i = 0; i < courses.size(); i++) {
            sb.append(courses.get(i).getName());
            if (i < courses.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}

class Course {
    private int id;
    private String name;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
