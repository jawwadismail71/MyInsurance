package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*  
 *  ODBC
 * 	JDBC API - Java's Database Connectivity 
 * 		
 * 						Driver <-- interface
 * 							|
 * 				-----------------------------			
 * 				|			|				|
 * 		Oracle's driver  MS-Sql's Driver	HSQLDB's driver
 * 
 * 			Database <------driver ------> Your Java Application
 
  
 1. load/register the driver  	
       DriverManager.registerDriver() 
       			org.hsqldb.jdbc.JDBCDriver()
 2. acquire the connection   
    Connection <-DriverManager.getConnection("dsn","username","password"); 
 jdbc:hsqldb:hsql://localhost/mydb", "SA","")
 */
public class SelectTest1 {
	public static void main(String[] args) {
		
	    //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:???",
		//"system", "manager");
		
		try {
			//DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Driver registered....");
		
			//Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydb","SA","");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system", "pass");
					

			System.out.println("Connected to the database "+conn);
			Statement statement= conn.createStatement(); //query for select, DML
			
			
			System.out.println("Statement created : "+statement);
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter job to search : ");
			String v_job = scan.nextLine();
			ResultSet rs = statement.executeQuery("select * from emp where job="+"'"+v_job+"'");
			
			while(rs.next()) {
				System.out.println("Emp number  : " + rs.getInt(1) );
				System.out.println("Emp name    : " + rs.getString(2) );
				System.out.println("Emp Job     : " + rs.getString(3) );
				System.out.println("Emp Manager : " + rs.getString(4) );
				System.out.println("Emp Joining : " + rs.getString(5) );
				System.out.println("Emp Salary  : " + rs.getInt(6) );
				System.out.println("Emp Comm    : " + rs.getInt(7) );
				System.out.println("Emp Dept    : " + rs.getInt(8) );
				System.out.println("-----------------------");
			}
			System.out.println("=============================================");
			Scanner scan2 = new Scanner(System.in);
			System.out.println("Enter starting date : ");
			String fromDate = scan2.nextLine();
			
			Scanner scan3 = new Scanner(System.in);
			System.out.println("Enter ending date   : ");
			String toDate = scan3.nextLine();
			
			//ResultSet dateRs = statement.executeQuery("select * from emp where hiredate between '01-Jan-81' and '31-Dec-81'");
			ResultSet dateRs = statement.executeQuery("select * from emp where hiredate between "+"'"+fromDate+"'"+" and "+"'"+toDate+"'");
			
			while(dateRs.next()) {
				System.out.println("Emp number  : " + dateRs.getInt(1) );
				System.out.println("Emp name    : " + dateRs.getString(2) );
				System.out.println("Emp Job     : " + dateRs.getString(3) );
				System.out.println("Emp Manager : " + dateRs.getString(4) );
				System.out.println("Emp Joining : " + dateRs.getString(5) );
				System.out.println("Emp Salary  : " + dateRs.getInt(6) );
				System.out.println("Emp Comm    : " + dateRs.getInt(7) );
				System.out.println("Emp Dept    : " + dateRs.getInt(8) );
				System.out.println("-----------------------");
			}
			
			
			rs.close();
			statement.close();
			conn.close();
			System.out.println("DB resources are closed....");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}