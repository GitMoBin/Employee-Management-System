package com.qsp.controller;

import java.sql.Connection;
import java.util.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qsp.model.Employee;

public class EmployeeController {
	static Connection con;
	static String url="jdbc:postgresql://localhost:5432/qsp";
	static String password="root";
	static String userName="postgres";
	static {
		try {
			//1st step
			Class.forName("org.postgresql.Driver");
			//2nd step
			con= DriverManager.getConnection(url,userName,password);
//			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insert(Employee e) {
		try {
			//3rd step
			PreparedStatement ps = con.prepareStatement("insert into employee values(?,?,?)");
			ps.setInt(1,e.getId());
			ps.setString(2,e.getName());
			ps.setDouble(3,e.getSal());
			//4th step
			ps.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static boolean updateNameById(int id,String name) {
		Employee emp = fetchById(id);
		if(emp.getName() !=null){
			try {
				//3rd step
				PreparedStatement ps = con.prepareStatement("update employee set name =? where id=?");
				ps.setString(1, name);
				ps.setInt(2, id);
//				4th step
				ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return true;
		}else {
			return false;
		}
	}
	public static Employee fetchById(int id) {
		Employee e=new Employee();
		try {
			PreparedStatement ps = con.prepareStatement("select * from employee where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSal(rs.getDouble("sal"));				
			}
		} catch (Exception e2) {
		
		}
		return e;	
	}
	public static boolean deleteById(int id) {
		Employee e = fetchById(id);
		if(e.getName()  !=null) {
			try {
				//3rd step
				PreparedStatement ps = con.prepareStatement("delete from employee where id=?");
				ps.setInt(1, id);
				//4th step
				ps.executeUpdate();
				} catch (Exception e3) {
					e3.printStackTrace();
					}
			return true;
			}else {
			return false;
		}
	}
	public static List<Employee> fetchAll() {
		List<Employee> li = new ArrayList<Employee>();
		try {
			//3rd step
			PreparedStatement ps = con.prepareStatement("select * from employee");
			//4th step
			ResultSet re = ps.executeQuery();
			while(re.next()) {
				Employee emp = new Employee();
				emp.setId(re.getInt("id"));
				emp.setName(re.getString("name"));
				emp.setSal(re.getDouble("sal"));
				li.add(emp);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;		
	}
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
