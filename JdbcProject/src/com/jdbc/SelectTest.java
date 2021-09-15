package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {

		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Driver Loaded");
			System.out.println();
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "pass");
			System.out.println("*************************");
			System.out.println("Connection Established " + conn);
			
			
			Statement statement=  conn.createStatement();
			System.out.println("Statement created ");
			
			ResultSet rs = statement.executeQuery("select * from emp where hiredate between '01-Jan-81' and '01-Jan-82'");
			while(rs.next()) {
			String val = "";
			for (int i = 1; i < 9; i++) {
				val += rs.getString(i)  + " ";
			}
			System.out.println(val);

			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
	}

}


// Implement Driver Interface
// Two important step
// 1. Loding the driver	DriverManager.registerDriver();
// 2. Acquire the connection

//XE =
//(DESCRIPTION =
//  (ADDRESS = (PROTOCOL = TCP)(HOST = LAPTOP-49NN6V1K)(PORT = 1521))
//  (CONNECT_DATA =
//    (SERVER = DEDICATED)
//    (SERVICE_NAME = XE)
//  )
//)