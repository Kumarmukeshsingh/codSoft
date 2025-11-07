import java.io.*;
import java.util.*;

// 1️⃣ Student Class
class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;
    private int age;

    public Student(String name, String rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + ", Name: " + name + ", Grade: " + grade + ", Age: " + age;
    }
}

// 2️⃣ Student Management System Class
class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudents();
    }

    // Add a new student
    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(student.getRollNumber())) {
                System.out.println(" Roll number already exists! Please try again.");
                return;
            }
        }
        students.add(student);
        saveStudents();
        System.out.println(" Student added successfully!");
    }

    // Remove a student
    public void removeStudent(String rollNumber) {
        boolean removed = students.removeIf(s -> s.getRollNumber().equalsIgnoreCase(rollNumber));
        if (removed) {
            saveStudents();
            System.out.println(" Student removed successfully!");
        } else {
            System.out.println(" Student not found!");
        }
    }

    // Search a student by roll number
    public void searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                System.out.println("🔍 Found: " + s);
                return;
            }
        }
        System.out.println(" Student not found!");
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println(" No students available.");
            return;
        }
        System.out.println("\n List of Students:");
        students.forEach(System.out::println);
    }

    // Save data to file
    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println(" Error saving student data: " + e.getMessage());
        }
    }

    // Load data from file
    @SuppressWarnings("unchecked")
    private void loadStudents() {
        File file = new File(FILE_NAME);
        if (!file.exists())
            return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            System.out.println(" Error loading student data: " + e.getMessage());
        }
    }
}

// 3️⃣ Main Class (User Interface)
public class StudentManagementApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentManagementSystem sms = new StudentManagementSystem();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=====  Student Management System =====");
            System.out.println(" 1 Add Student");
            System.out.println(" 2 Remove Student");
            System.out.println(" 3 Search Student");
            System.out.println(" 4 Display All Students");
            System.out.println(" 5 Exit");
            System.out.print(" Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println(" Invalid choice, please enter a number!");
                continue;
            }

            switch (choice) {
                case 1 -> addStudentUI();
                case 2 -> removeStudentUI();
                case 3 -> searchStudentUI();
                case 4 -> sms.displayAllStudents();
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println(" Invalid option! Please try again.");
            }
        }
    }

    // Add Student (with input validation)
    private static void addStudentUI() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println(" Name cannot be empty!");
            return;
        }

        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine().trim();
        if (roll.isEmpty()) {
            System.out.println(" Roll number cannot be empty!");
            return;
        }

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine().trim();
        if (grade.isEmpty()) {
            System.out.println(" Grade cannot be empty!");
            return;
        }

        System.out.print("Enter Age: ");
        int age;
        try {
            age = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid age!");
            return;
        }

        sms.addStudent(new Student(name, roll, grade, age));
    }

    // Remove Student
    private static void removeStudentUI() {
        System.out.print("Enter Roll Number to remove: ");
        String roll = sc.nextLine();
        sms.removeStudent(roll);
    }

    // Search Student
    private static void searchStudentUI() {
        System.out.print("Enter Roll Number to search: ");
        String roll = sc.nextLine();
        sms.searchStudent(roll);
    }
}
