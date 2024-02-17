import java.util.Scanner;

public class StudentRecordManagementSystem {
    private static int totalStudents = 0;
    private static final int MAX_STUDENTS = 100;
    private static final Student[] studentList = new Student[MAX_STUDENTS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student Information");
            System.out.println("3. View Student Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewStudent(scanner);
                    break;
                case 2:
                    updateStudentInformation(scanner);
                    break;
                case 3:
                    viewStudentDetails(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private static void addNewStudent(Scanner scanner) {
        if (totalStudents == MAX_STUDENTS) {
            System.out.println("Maximum number of students reached. Cannot add more students.");
            return;
        }

        System.out.print("Enter student name: ");
        String name = scanner.next();

        int id;
        while (true) {
            try {
                System.out.print("Enter student ID: ");
                id = scanner.nextInt();
                if (id < 0) {
                    throw new IllegalArgumentException("Invalid student ID. Please enter a positive integer value.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer value.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();

        studentList[totalStudents++] = new Student(name, id, age, grade);
        System.out.println("Student added successfully.");
    }

    private static void updateStudentInformation(Scanner scanner) {
        System.out.print("Enter student ID to update information: ");
        int id = scanner.nextInt();

        int index = findStudentIndexById(id);
        if (index == -1) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        System.out.print("Enter new student name: ");
        String name = scanner.next();
        System.out.print("Enter new student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter new student grade: ");
        double grade = scanner.nextDouble();

        studentList[index].setName(name);
        studentList[index].setAge(age);
        studentList[index].setGrade(grade);
        System.out.println("Student information updated successfully.");
    }

    private static void viewStudentDetails(Scanner scanner) {
        System.out.print("Enter student ID to view details: ");
        int id = scanner.nextInt();

        int index = findStudentIndexById(id);
        if (index == -1) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        System.out.println("Student Details:");
        System.out.println("Name: " + studentList[index].getName());
        System.out.println("ID: " + studentList[index].getId());
        System.out.println("Age: " + studentList[index].getAge());
        System.out.println("Grade: " + studentList[index].getGrade());
    }

    private static int findStudentIndexById(int id) {
        for (int i = 0; i < totalStudents; i++) {
            if (studentList[i].getId() == id) {
                return i;
            }
        }
        return -1; // Student not found
    }

    static class Student {
        private String name;
        private int id;
        private int age;
        private double grade;

        public Student(String name, int id, int age, double grade) {
            this.name = name;
            this.id = id;
            this.age = age;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getGrade() {
            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }
    }
}
