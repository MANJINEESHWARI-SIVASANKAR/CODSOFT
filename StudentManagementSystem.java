import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    static class Student {
        private String name;
        private int rollNumber;
        private String grade;

        public Student(String name, int rollNumber, String grade) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
        }

        public String getName() { return name; }
        public int getRollNumber() { return rollNumber; }
        public String getGrade() { return grade; }

        public void setName(String name) { this.name = name; }
        public void setGrade(String grade) { this.grade = grade; }

        @Override
        public String toString() {
            return "Roll: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
        }
    }

    private static Map<Integer, Student> students = new HashMap<>();
    private static final String FILE_NAME = "students.txt";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();

        while (true) {
            System.out.println("\n**** Student Management System ****");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Remove Student");
            System.out.println("6. Save and Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> addStudent();
                case "2" -> editStudent();
                case "3" -> searchStudent();
                case "4" -> displayAll();
                case "5" -> removeStudent();
                case "6" -> {
                    saveToFile();
                    System.out.println("Data saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Roll Number: ");
        String rollStr = sc.nextLine().trim();
        System.out.print("Enter Grade: ");
        String grade = sc.nextLine().trim();

        if (name.isEmpty() || rollStr.isEmpty() || grade.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        try {
            int roll = Integer.parseInt(rollStr);
            if (students.containsKey(roll)) {
                System.out.println("Student with this roll number already exists.");
                return;
            }
            Student s = new Student(name, roll, grade);
            students.put(roll, s);
            System.out.println(" Student added.");
        } catch (NumberFormatException e) {
            System.out.println("Roll number must be numeric.");
        }
    }

    private static void editStudent() {
        System.out.print("Enter Roll Number of student to edit: ");
        try {
            int roll = Integer.parseInt(sc.nextLine());
            Student s = students.get(roll);
            if (s == null) {
                System.out.println("Student not found.");
                return;
            }

            System.out.print("Enter new name (or press Enter to keep '" + s.getName() + "'): ");
            String name = sc.nextLine().trim();
            if (!name.isEmpty()) s.setName(name);

            System.out.print("Enter new grade (or press Enter to keep '" + s.getGrade() + "'): ");
            String grade = sc.nextLine().trim();
            if (!grade.isEmpty()) s.setGrade(grade);

            System.out.println("Student updated.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number.");
        }
    }

    private static void searchStudent() {
        System.out.print("Enter Roll Number to search: ");
        try {
            int roll = Integer.parseInt(sc.nextLine());
            Student s = students.get(roll);
            if (s != null) {
                System.out.println("🎓 " + s);
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number.");
        }
    }

    private static void displayAll() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\n Student List:");
        for (Student s : students.values()) {
            System.out.println(s);
        }
    }

    private static void removeStudent() {
        System.out.print("Enter Roll Number to remove: ");
        try {
            int roll = Integer.parseInt(sc.nextLine());
            if (students.remove(roll) != null) {
                System.out.println("Student removed.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number.");
        }
    }

    private static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students.values()) {
                writer.println(s.getName() + "," + s.getRollNumber() + "," + s.getGrade());
            }
        } catch (IOException e) {
            System.out.println(" Error saving to file.");
        }
    }

    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int roll = Integer.parseInt(parts[1]);
                    String grade = parts[2];
                    students.put(roll, new Student(name, roll, grade));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading saved data. Starting fresh.");
        }
    }
}
