package com.lshop.common.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.gv.base.model.spring.ApplicationContextHolder;

public class JDBCUtils {

	private static final Logger logger = Logger.getLogger(JDBCUtils.class);

	private static JDBCUtils jdbcUtils = null;

	/**
	 * 创建JDBC工具类实例
	 * 
	 * @return
	 */
	public static synchronized JDBCUtils getInstance() {

		if (jdbcUtils == null) {
			jdbcUtils = new JDBCUtils();
		}
		return jdbcUtils;
	}

	/**
	 * 获取主库数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnectionForMaster() throws SQLException {
		DataSource dataSource = (DataSource) ApplicationContextHolder.getBean("lvlogicWritedataSource");
		return dataSource.getConnection();
	}

	/**
	 * 获取从库数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnectionForSlave() throws SQLException {
		DataSource dataSource = (DataSource) ApplicationContextHolder.getBean("lvlogicReaddataSource");
		return dataSource.getConnection();
	}

	/**
	 * 关闭数据库相关连接
	 * 
	 * @param connection
	 */
	public void close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
