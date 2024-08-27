package com.qsp.view;

import java.util.List;
import java.util.Scanner;

import com.qsp.controller.EmployeeController;
import com.qsp.model.Employee;

public class EmployeeView {
	public static void main(String[] args) {
//		Employee e = new Employee();
//		e.setId(102);
//		e.setName("ABC");
//		e.setSal(9000);
//		EmployeeController.insert(e);
//		System.out.println("Inserted");
//		Employee e1 = EmployeeController.fetchById(101);
//		System.out.println(e1.getName());
//		boolean b = EmployeeController.updateNameById(101,"ABC");
//		System.out.println(b);
//		boolean b = EmployeeController.deleteById(101);
//		System.out.println(b);
//		List<Employee> li=EmployeeController.fetchAll();
//		for(Employee e:li) {
//			System.out.println(e);
		
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1.Insert Employee");
			System.out.println("2.Update Employee name by Id");
			System.out.println("3.Fetch Employee by Id");
			System.out.println("4.Delete Employee by Id");
			System.out.println("5.Fetch All Employee");
			System.out.println("6.Close Connection");
			System.out.print("Enter an option  : ");
			int opt;
		switch (sc.nextInt()) {
		case 1: {
			Employee e = new Employee();
			System.out.print("enter id : ");
			e.setId(sc.nextInt());
			System.out.print("enter name : ");
			e.setName(sc.next());
			System.out.print("enter salary : ");
			e.setSal(sc.nextDouble());
			EmployeeController.insert(e);
			System.out.println("---Inserted---");
	
		}
		break;
		case 2: {
			System.out.print("enter id : ");
			int id = sc.nextInt();
			System.out.print("enter new name : ");
			String newName = sc.next();
			boolean b = EmployeeController.updateNameById(id, newName);
			if(b) {
				System.out.println("---Updated---");
			}else {
				System.out.println("Id not present");
			}
		}
		break;
		case 3: {
			System.out.print("enter id : ");
			Employee e = EmployeeController.fetchById(sc.nextInt());
			System.out.println(e);
		}
		break;
		case 4: {
			System.out.print("enter id : ");		
			boolean b = EmployeeController.deleteById(sc.nextInt());
			if(b) {
				System.out.println("---Deleted---");
			}else {
				System.out.println("Id is not present");
			}
		}
		break;
		case 5: {
			List<Employee> li = EmployeeController.fetchAll();
			for(Employee e : li) {
				System.out.println(e);
			}
			
		}
		break;
		case 6: {
			EmployeeController.closeConnection();
			System.out.println("Connection Closed");
		}
		default:
			System.out.println("Wrong Info");break;
		}
		System.out.print("Enter y to continue : ");
		}while("Y".equalsIgnoreCase(sc.next()));
		
	}
}
