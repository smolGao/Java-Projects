package studentDBApp;

import java.util.Scanner;

public class StudentDBAPP {
    // Array as the students database
    private static Student[] students;
    // Size of the Array
    private static int studentAmount;

    public static void main(String[] args) {
        // Query on how many new users to add
        System.out.print("Enter amount of student to enroll: ");
        Scanner in = new Scanner(System.in);
        studentAmount = in.nextInt();

        // Create an array for n number of new students
        students = new Student[studentAmount];

        // Fill students database
        addStudentDB();

        // Show student database
        showDB();
    }

    // Add studentAmount number of students to students array
    private static void addStudentDB() {
        for (int i = 0; i < studentAmount; i++)
        {
            students[i] = new Student();
            students[i].enroll();
            students[i].payTuition();
        }
    }

    public static void showDB() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < studentAmount; i++)
        {
            sb.append(students[i].toString() + "\n\n");
        }
        System.out.println(sb.toString());
    }
}
