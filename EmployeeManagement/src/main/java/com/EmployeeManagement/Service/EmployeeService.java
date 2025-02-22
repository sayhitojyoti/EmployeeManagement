package com.EmployeeManagement.Service;

import java.sql.*;
 
import java.util.Scanner;

public class EmployeeService {
	private static Connection con = null;
	private static String url = "jdbc:postgresql://localhost:5432/Jspider?user=postgres&password=123";

	static {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {

		}
	}

	public int save() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Employee ID:");
		int id = s.nextInt();
		System.out.println("Enter Employee Name:");
		String name = s.next();
		System.out.println("Enter Employee Age:");
		int age = s.nextInt();
		s.nextLine();
		System.out.println("Enter Employee Gender `:");
		String gender = s.nextLine();
		String sql = "INSERT INTO employee VALUES (?, ?, ?, ?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, gender);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {

			}
		} catch (SQLException e) {

		}

		return 1;
	}
public void fetchAllEmployeeDetails()
{
	String sql = "SELECT * FROM employee";
	Statement stm = null;
	try {
		stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
	while(rs.next())
	{
		System.out.println("============================");
		System.out.println("Employee ID: " + rs.getInt(1));
		System.out.println("Employee NAME: " + rs.getString(2));
		System.out.println("Employee AGE: " +rs.getInt(3));
		System.out.println("Employee GENDER: " +rs.getString(4));
		System.out.println("============================" + "\n");
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public void fetchEmployeeDetails() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Employee ID to fetch details:");
		int id = s.nextInt();
		String sql = "SELECT * FROM employee WHERE id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				System.out.println("============================");
				System.out.println("Employee ID: " + id);
				System.out.println("Employee NAME: " + name);
				System.out.println("Employee AGE: " + age);
				System.out.println("Employee GENDER: " + gender);
				System.out.println("============================" + "\n");

			} else {
				System.out.println("Employee not found with ID: " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateEmployeeDetails() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Employee ID to update:");
		int id = s.nextInt();
		s.nextLine();

		while (true) {
			System.out.println("\nWhat would you like to update?");
			System.out.println("1. Name");
			System.out.println("2. Age");
			System.out.println("3. Gender");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			int choice = s.nextInt();
			s.nextLine();

			String sql = "";
			boolean exit = false;
			switch (choice) {
			case 1:
				System.out.println("Enter new Name for Employee ID " + id + ":");
				String name = s.nextLine();
				sql = "UPDATE employee SET name = ? WHERE id = ?";
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setString(1, name);
					ps.setInt(2, id);
					executeUpdate(ps, id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;

			case 2:
				System.out.println("Enter new Age for Employee ID " + id + ":");
				int age = s.nextInt();
				s.nextLine();
				sql = "UPDATE employee SET age = ? WHERE id = ?";
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setInt(1, age);
					ps.setInt(2, id);
					executeUpdate(ps, id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;

			case 3:
				System.out.println("Enter new Gender (M/F) for Employee ID " + id + ":");
				String gender = s.nextLine();
				sql = "UPDATE employee SET gender = ? WHERE id = ?";
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setString(1, gender);
					ps.setInt(2, id);
					executeUpdate(ps, id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;

			case 4:
				exit = true;
				break;

			default:
				System.out.println("Invalid choice. Please try again.");
			}

			if (exit) {
				System.out.println("Exiting update menu.");
				break;
			}
		}
	}

	private void executeUpdate(PreparedStatement ps, int id) throws SQLException {
		int rowsAffected = ps.executeUpdate();
		if (rowsAffected > 0) {
			System.out.println("Employee details updated successfully for ID: " + id + ".\n");
		} else {
			System.out.println("Employee not found with ID: " + id);
		}
	}

	public void deleteEmployeeDetails() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Employee ID to delete:");
		int id = s.nextInt();
		String sql = "DELETE FROM employee WHERE id = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Employee ID " + id + " deleted successfully." + "\n");
			} else {
				System.out.println("Employee not found with ID: " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}