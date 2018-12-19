package sakilaDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sakila";

	static final String USER = "root";
	static final String PASS = "password";

	public static void accessDB() {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			///Opening Connection///
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connecting to the database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection made");	
			
			///Create///
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			String sql = "INSERT INTO Actor (first_name, last_name) VALUES ('Ben','Leadbeater')";
			stmt.executeUpdate(sql);
			System.out.println("Inserted records into the table...");
			
			///Read///
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql2 = "SELECT actor_id, first_name, last_name FROM actor limit 10";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				int id = rs.getInt("actor_id");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				System.out.println("ID: " + id + ",First Name: " + firstname + ", Last Name: " + lastname);
				}
			rs.close();
			
			///Update///
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql3 = "Update ACTOR SET first_name = 'Jeremy' WHERE actor_id = 5";
			stmt.executeUpdate(sql3);
			
			///Delete///
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql4 = "DELETE FROM Actor where id = 201";
			stmt.executeUpdate(sql4);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
		}
	}
}
