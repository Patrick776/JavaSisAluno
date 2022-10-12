package br.edu.unicid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	
	public static Connection getConnection() throws Exception{
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db_aluno", "root", "");
					
		} catch (Exception e) {
			throw new Exception (e.getMessage());
		}
	}

	public static void main (String args[])
	{
		try {
			Connection conn = ConnectionFactory.getConnection();
			JOptionPane.showMessageDialog(null, "DB connection");
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		close(conn, stmt, rs);
	}

	public static void closeConnection(Connection conn, Statement stmt) throws Exception {
		close(conn, stmt, null);
	}

	public static void closeConnection(Connection conn) throws Exception {
		close(conn, null, null);
	}

	private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
