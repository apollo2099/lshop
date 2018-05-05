package com.lshop.web.userCenter.bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBHelper {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private String url = null ;
	private String user = null ;
	private String password = null ;
	
	public Connection conn;

	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public DBHelper(String url,String user,String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public Connection getDBConn() {
		try {
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return this.conn;
	}

	
	public void closeConnection() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			
			if (conn != null) {
				this.conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
