package studentDBApp;

import java.util.Scanner;

public class Student {
    private String firstName;
    private String lastName;
    private int gradeYear;
    private String studentID;
    private String courses = "";
    private double tuitionBalance = 0;
    private static double costOfCourse = 600;
    private static int id = 1000;

    // Constructor: prompt user to enter student's name and year
    public Student() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter student first name: ");
        this.firstName = in.nextLine();

        System.out.print("Enter student last name: ");
        this.lastName = in.nextLine();

        System.out.print("1 - Freshmen\n2 - Sophomore\n3 - Junior\n4 - Senior\nEnter student grade level: ");
        this.gradeYear = in.nextInt();

        // Assign a student id
        setStudentId();
    }

    // Generate an ID
    private void setStudentId() {
        // Grade level + id
        this.studentID = gradeYear + "" + id;
        id++;
    }

    // Enrollment in Courses
    public void enroll() {
        // Sentinel Control loop, use Q as sentinel
        String course;
        do {
            System.out.print("Enter course to enroll (Q to quit): ");
            Scanner in = new Scanner(System.in);
            course = in.nextLine();
            if (!course.equals("Q")) {
                courses += "\n\t" + course;
                tuitionBalance = tuitionBalance + costOfCourse;
            }
        } while (!course.equals("Q"));
    }

    // View balance
    public void viewBalance() {
        System.out.println("Your balance is: $" + tuitionBalance);
    }

    // Pay tuition
    public void payTuition() {
        System.out.println("");
        viewBalance();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your payment: ");
        double payment = in.nextDouble();
        tuitionBalance -= payment;
        System.out.println("Transaction Processed");
        viewBalance();
        System.out.println("");
    }

    // Show status
    public String toString()
    {
        return "Name: " + firstName + " " + lastName +
                "\nSchool Year: " + gradeYear + "\nStudent ID: " +
                studentID + "\nCourses Enrolled:" + courses +
                "\nBalance: $" + tuitionBalance;
    }
}
