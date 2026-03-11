package labmst;

import java.io.*;
import java.util.*;

class Student {
    int id;
    String name;
    double marks;

    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
}


class StudentManager {

    Scanner sc = new Scanner(System.in);
    String fileName = "students.txt";

    void writeStudent() {

        try {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();

            Student s = new Student(id, name, marks);

            FileWriter fw = new FileWriter(fileName, true);
            fw.write(s.toString() + "\n");
            fw.close();

            System.out.println("Student record saved successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter correct data types.");
            sc.nextLine();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    void readStudents() {

        try {

            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("File does not exist.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            System.out.println("\nStudent Records:");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                System.out.println("ID: " + data[0] +
                                   " | Name: " + data[1] +
                                   " | Marks: " + data[2]);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }
    }
}


public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        int choice;

        do {

            System.out.println("\n===== Student File Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    manager.writeStudent();
                    break;

                case 2:
                    manager.readStudents();
                    break;

                case 3:
                    System.out.println("Program exited.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 3);

        sc.close();
    }
}