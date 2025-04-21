import java.util.*;

class Student {
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Course: " + course);
    }
}

public class StudentManagementSystem {
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("\u001B[34m1. Add Student\u001B[0m");
            System.out.println("\u001B[34m2. View Students\u001B[0m");
            System.out.println("\u001B[34m3. Search Student by ID\u001B[0m");
            System.out.println("\u001B[34m4. Search Student by Name or Course\u001B[0m");
            System.out.println("\u001B[34m5. Delete Student\u001B[0m");
            System.out.println("\u001B[31m6. Exit\u001B[0m");
            System.out.print("Choose an option: ");

            int choice = getValidInput(1, 6);  // Validate user choice

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudentById();
                case 4 -> searchStudentByNameOrCourse();
                case 5 -> deleteStudent();
                case 6 -> {
                    System.out.println("\u001B[31mExiting... Goodbye!\u001B[0m");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = getValidInput(1, Integer.MAX_VALUE);  // Validate ID

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = getValidInput(18, 100);  // Validate age

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        Student student = new Student(id, name, age, course);
        students.add(student);

        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(Student::displayInfo);
        }
    }

    private static void searchStudentById() {
        System.out.print("Enter student ID to search: ");
        int id = getValidInput(1, Integer.MAX_VALUE);  // Validate ID

        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                student.displayInfo();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudentByNameOrCourse() {
        System.out.print("Enter Name or Course to search: ");
        String query = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(query) || student.getCourse().toLowerCase().contains(query)) {
                student.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found matching the query.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = getValidInput(1, Integer.MAX_VALUE);  // Validate ID

        Iterator<Student> iterator = students.iterator();
        boolean removed = false;
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == id) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Method Overloading: Validates user input for any integer value within a range.
    private static int getValidInput(int min, int max) {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a valid number between " + min + " and " + max + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
    }
}
