package com.pageObjectEcom.vr.helper.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDBQuery {
	
	public int getEmpSalary(int empID) throws NumberFormatException, SQLException {
		int salary = 0;
		String dbQuery = "SELECT salary FROM person,employee where idemployee="+empID;
		ResultSet result = DataBaseHelper.getResultSet(dbQuery);
		
		while(result.next()) {
			salary = Integer.parseInt(result.getString("salary"));
			}
		
		return salary;
	}
	
	
	public List<Employee> getEmployee() throws NumberFormatException, SQLException {
		List<Employee> data = new ArrayList<Employee>();
		String dbQuery = "SELECT * FROM person,employee";
		ResultSet result = DataBaseHelper.getResultSet(dbQuery);
		while(result.next()) {
			Employee emp = new Employee();
			emp.setEmpID(Integer.parseInt(result.getString("idemployee")));
			emp.setSalary(Integer.parseInt(result.getString("salary")));
			emp.setName(result.getString("name"));
			emp.setName(result.getString("address"));
			data.add(emp);
		}
		return data;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, SQLException {
		ApplicationDBQuery applicationDBQurey = new ApplicationDBQuery();
		int salary = applicationDBQurey.getEmpSalary(2);
		System.out.println(salary);
		List<Employee> listData = applicationDBQurey.getEmployee();
		for(Employee data: listData) {
			System.out.println("empID is: "+data.getEmpID()+" emp salary is: "+data.getSalary()+" emp name is: "+data.getName());
		}
		
		
	}

}
