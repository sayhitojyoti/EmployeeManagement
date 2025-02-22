package com.EmployeeManagementDriver;

import java.util.Scanner;
import com.EmployeeManagement.Service.EmployeeService;

public class EmployeeDriver {
	public static void main(String[] args) {
		System.out.println("====================================");
		System.out.println("=     WELCOME TO MY APPLICATION    =");
		System.out.println("====================================");
		System.out.println("MENU");
		System.out.println("ENTER 1 TO ADD EMPLOYEE DETAILS");
		System.out.println("ENTER 2 TO FETCH ALL EMPLOYEE DETAILS");
		System.out.println("ENTER 3 TO FETCH EMPLOYEE DETAILS USING SPECIFIC ID");
		System.out.println("ENTER 4 TO UPDATE EMPLOYEES");
		System.out.println("ENTER 5 TO DELETE EMPLOYEE DETAILS");
		System.out.println("ENTER 6 TO EXIT");

		Scanner s = new Scanner(System.in);
		EmployeeService service = new EmployeeService();

		while (true) {
			System.out.println("ENTER YOUR CHOICE:");
			int choice = s.nextInt();

			switch (choice) {
			case 1:
				int resAdd = service.save();
				if (resAdd == 1) {
					System.out.println("Employee details added successfully." + "\n");
				} else {
					System.out.println("Failed to add employee details.");
				}
				break;

			case 2:

				service.fetchAllEmployeeDetails();
				break;
			case 3:

				service.fetchEmployeeDetails();
				break;
			case 4:
				service.updateEmployeeDetails();
				break;

			case 5:

				service.deleteEmployeeDetails();
				break;

			case 6:

				System.out.println("Exiting the application.");
				s.close();
				return;

			default:
				System.out.println("Invalid choice. Please enter a valid option.");
				break;
			}
		}
	}
}