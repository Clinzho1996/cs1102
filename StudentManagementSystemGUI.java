import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentManagementSystemGUI extends JFrame implements ActionListener {
    private JTextField idField, nameField, courseIdField, gradeField;
    private JButton addButton, updateButton, viewButton, enrollButton, gradeButton;
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

        addButton = new JButton("Add Student");
        updateButton = new JButton("Update Student");
        viewButton = new JButton("View Students");
        enrollButton = new JButton("Enroll Student");
        gradeButton = new JButton("Assign Grade");

        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        viewButton.addActionListener(this);
        enrollButton.addActionListener(this);
        gradeButton.addActionListener(this);

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
        add(new JScrollPane(displayArea));

        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String id = idField.getText();
            String name = nameField.getText();
            students.add(new Student(Integer.parseInt(id), name));
            displayArea.append("Added Student: " + name + " (ID: " + id + ")\n");
        } else if (e.getSource() == updateButton) {
            String id = idField.getText();
            String newName = nameField.getText();
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    student.setName(newName);
                    displayArea.append("Updated Student: " + student.getName() + " (ID: " + student.getId() + ")\n");
                    break;
                }
            }
        } else if (e.getSource() == viewButton) {
            displayArea.setText("");
            for (Student student : students) {
                displayArea.append(student.toString() + "\n");
            }
        } else if (e.getSource() == enrollButton) {
            String studentId = idField.getText();
            String courseId = courseIdField.getText();
            for (Student student : students) {
                if (student.getId().equals(studentId)) {
                    for (Course course : courses) {
                        if (course.getId().equals(courseId)) {
                            student.enroll(course);
                            displayArea.append(
                                    "Enrolled Student " + student.getName() + " in Course " + course.getName() + "\n");
                            break;
                        }
                    }
                    break;
                }
            }
        } else if (e.getSource() == gradeButton) {
            String studentId = idField.getText();
            String courseId = courseIdField.getText();
            double grade = Double.parseDouble(gradeField.getText());
            for (Student student : students) {
                if (student.getId().equals(studentId)) {
                    for (Course course : student.getCourses()) {
                        if (course.getId().equals(courseId)) {
                            student.setGrade(course, grade);
                            displayArea.append("Assigned Grade " + grade + " to Student " + student.getName()
                                    + " for Course " + course.getName() + "\n");
                            break;
                        }
                    }
                    break;
                }
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

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getters and setters

    public void enroll(Course course) {
        courses.add(course);
        grades.add(0.0); // Initialize grade as 0.0
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

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }
}

class Course {
    private int id;
    private String name;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
