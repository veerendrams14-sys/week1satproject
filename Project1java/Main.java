import java.util.ArrayList;
import java.util.Scanner;

abstract class Student {

    private int studentId;
    private String name;
    private double marks;
    private char grade;

    public Student(int studentId, String name, double marks) {
        this.studentId = studentId;
        this.name = name;
        this.marks = marks;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public char getGrade() {
        return grade;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public abstract void calculateGrade();

    public void displayDetails() {
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
        System.out.println("---------------------");
    }
}
class RegularStudent extends Student {

    public RegularStudent(int id, String name, double marks) {
        super(id, name, marks);
    }

    public void calculateGrade() {

        double marks = getMarks();

        if (marks >= 90)
            setGrade('A');
        else if (marks >= 75)
            setGrade('B');
        else if (marks >= 60)
            setGrade('C');
        else
            setGrade('F');
    }
}
class ScholarshipStudent extends Student {

    public ScholarshipStudent(int id, String name, double marks) {
        super(id, name, marks);
    }

    public void calculateGrade() {

        double marks = getMarks() + 5;

        if (marks >= 90)
            setGrade('A');
        else if (marks >= 75)
            setGrade('B');
        else if (marks >= 60)
            setGrade('C');
        else
            setGrade('F');
    }
}
class StudentService {

    ArrayList<Student> list = new ArrayList<>();

    public void addStudent(Student s) {
        s.calculateGrade();
        list.add(s);
        System.out.println("Student added successfully ✅");
    }

    public void displayAll() {

        if (list.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : list) {
            s.displayDetails();
        }
    }

    public Student search(int id) {

        for (Student s : list) {
            if (s.getStudentId() == id)
                return s;
        }

        return null;
    }

    public void updateMarks(int id, double marks) {

        Student s = search(id);

        if (s != null) {
            s.setMarks(marks);
            s.calculateGrade();
            System.out.println("Marks updated successfully ✅");
        } else {
            System.out.println("Student not found ❌");
        }
    }

    public void findTopper() {

        if (list.isEmpty()) {
            System.out.println("No students.");
            return;
        }

        Student top = list.get(0);

        for (Student s : list) {
            if (s.getMarks() > top.getMarks())
                top = s;
        }

        System.out.println("Topper Details 🏆");
        top.displayDetails();
    }
}
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Marks");
            System.out.println("5. Find Topper");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("1. Regular Student");
                    System.out.println("2. Scholarship Student");

                    int type = sc.nextInt();

                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();

                    if (type == 1)
                        service.addStudent(new RegularStudent(id, name, marks));
                    else
                        service.addStudent(new ScholarshipStudent(id, name, marks));

                    break;

                case 2:
                    service.displayAll();
                    break;

                case 3:

                    System.out.print("Enter ID: ");
                    int searchId = sc.nextInt();

                    Student s = service.search(searchId);

                    if (s != null)
                        s.displayDetails();
                    else
                        System.out.println("Student not found ❌");

                    break;

                case 4:

                    System.out.print("Enter ID: ");
                    int updateId = sc.nextInt();

                    System.out.print("Enter new marks: ");
                    double newMarks = sc.nextDouble();

                    service.updateMarks(updateId, newMarks);

                    break;

                case 5:
                    service.findTopper();
                    break;

                case 6:
                    System.out.println("Thank you 👋");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 6);
        sc.close();
    }
}