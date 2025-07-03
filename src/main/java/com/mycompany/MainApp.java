package com.mycompany;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getChoice();

            try {
                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        viewAllEmployees();
                        break;
                    case 3:
                        updateEmployee();
                        break;
                    case 4:
                        deleteEmployee();
                        break;
                    case 5:
                        System.out.println("Exiting application.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (SQLException e) {
                System.err.println("\n[ERROR] Database operation failed: " + e.getMessage());
                // e.printStackTrace(); // Uncomment for detailed debug info
            } catch (InputMismatchException e) {
                System.err.println("\n[ERROR] Invalid input. Please enter numbers where required.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Employee Database Menu ---");
        System.out.println("1. Add a new Employee");
        System.out.println("2. View all Employees");
        System.out.println("3. Update an Employee");
        System.out.println("4. Delete an Employee");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // clear buffer
            return -1; // Invalid choice
        }
    }

    private static void addEmployee() throws SQLException, InputMismatchException {
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter position: ");
        String position = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(name, position, salary);
        employeeDAO.addEmployee(employee);
        System.out.println("==> Employee added successfully!");
    }

    private static void viewAllEmployees() throws SQLException {
        List<Employee> employees = employeeDAO.getAllEmployees();
        System.out.println("\n--- All Employees ---");
        if (employees.isEmpty()) {
            System.out.println("No employees found in the database.");
        } else {
            employees.forEach(System.out::println);
        }
    }

    private static void updateEmployee() throws SQLException, InputMismatchException {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new position: ");
        String position = scanner.nextLine();
        System.out.print("Enter new salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(name, position, salary);
        employee.setId(id);
        employeeDAO.updateEmployee(employee);
        System.out.println("==> Employee updated successfully!");
    }

    private static void deleteEmployee() throws SQLException, InputMismatchException {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        employeeDAO.deleteEmployee(id);
        System.out.println("==> Employee deleted successfully!");
    }
}