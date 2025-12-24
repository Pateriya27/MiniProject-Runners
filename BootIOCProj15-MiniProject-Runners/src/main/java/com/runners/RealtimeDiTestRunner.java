package com.runners;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.controller.EmployeeOperationsController;
import com.model.Employee;
@Component
public class RealtimeDiTestRunner implements CommandLineRunner {
	@Autowired
	private EmployeeOperationsController controller;

	@Override
	public void run(String... args) throws Exception {
		try(Scanner sc=new Scanner(System.in);){
			
			boolean loopVar = true;
			while (loopVar) {
				System.out.println(
						"Welcome to Employee Management System :: \n1.View Records Based on Desg\n2.Insert Record\n3.Exit\nEnter Your Choice : ");

				int choice = Integer.parseInt(sc.nextLine());

				switch (choice) {
				case 1 -> {
					System.out.print("Enter Desg 1 : ");
					String desg1 = sc.nextLine();
					System.out.print("Enter Desg 2 : ");
					String desg2 = sc.nextLine();
					System.out.print("Enter Desg 3 : ");
					String desg3 = sc.nextLine();

					try {
						List<Employee> list = controller.fetchEmployeesByDesgs(desg1, desg2, desg3);
						list.forEach(System.out::println);
						System.out.println();
					} catch (Exception e) {
						System.out.println("Internal Error----" + e.getMessage());
						e.printStackTrace();
					}
				}

				case 2 -> {
					System.out.print("Enter Employee Name : ");
					String name = sc.nextLine();
					System.out.print("Enter Employee Desg : ");
					String desg = sc.nextLine();
					System.out.print("Enter Employee Salary : ");
					Double sal = Double.parseDouble(sc.nextLine());
					Employee e = new Employee(name, desg, sal);
					String registration = controller.processEmployeeRegistration(e);
					System.out.println(registration);
					System.out.println();
				}

				case 3 -> {
					System.out.println("Exiting........");
					loopVar = false;
				}

				default -> System.out.println("Unexpected value: " + choice);
				}
			}

		} catch (Exception e) {
			System.out.println("Internal Error----" + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
