package com.lshop.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GenericJDBCSupport {
	private static final Log log = LogFactory.getLog(GenericJDBCSupport.class);

	public static List<Object[]> queryBySQLForSlave(String sql, Object[] params) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Object[]> objList = new ArrayList<Object[]>();
		JDBCUtils utils = JDBCUtils.getInstance();
		try {
			conn = utils.getConnectionForSlave();
			// 创建PreparedStatement对象
			pstmt = conn.prepareStatement(sql);

			if (!ArrayUtils.isEmpty(params)) {

				for (int j = 0; j < params.length; j++) {

					pstmt.setObject(j + 1, params[j]);

				}
			}

			resultSet = pstmt.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount = rsmd.getColumnCount();
			while (resultSet.next()) {
				Object[] objs = new Object[colCount];
				for (int i = 0; i < colCount; i++) {
					objs[i] = resultSet.getObject(i + 1);
				}
				objList.add(objs);
			}
			if (log.isInfoEnabled()) {
				System.out.println(sql);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			close(pstmt, conn); // 关闭相关连接
		}
		return objList;
	}

	public static Object[] findUniqueBySQLForSlave(String sql, Object[] params) throws Exception {
		Object[] objs = null;
		List<Object[]> list = queryBySQLForSlave(sql, params);
		if (CollectionUtils.isNotEmpty(list)) {
			if (list.size() == 1) {
				objs = list.get(0);
			} else if (list.size() > 1) {
				log.error("返回非唯一  sql: " + sql + " " + params.toString());
				throw new Exception();
			}

		}
		return objs;
	}

	public static Object[] findUniqueBySQLForSlave(String sql, List<Object> params) throws Exception {
		Object[] objs = null;
		Object[] arrayParams = null;
		if (CollectionUtils.isNotEmpty(params)) {
			arrayParams = new Object[params.size()];
			for (int i = 0; i < params.size(); i++) {
				arrayParams[i] = params.get(i);
			}
		}

		List<Object[]> list = queryBySQLForSlave(sql, arrayParams);
		if (CollectionUtils.isNotEmpty(list)) {
			objs = list.get(0);
		}
		return objs;
	}

	public static Boolean isExistForSlave(String sql, Object[] params) throws Exception {
		Boolean isExist = false;
		List<Object[]> list = queryBySQLForSlave(sql, params);
		if (CollectionUtils.isNotEmpty(list) && !ArrayUtils.isEmpty(list.get(0))) {
			isExist = true;
		}
		return isExist;
	}

	public static void close(PreparedStatement pstmt, Connection conn) throws Exception {
		try {
			if (null != pstmt) {
				pstmt.close();
			}
			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public static List<List<Object[]>> getListPage(List<Object[]> list, Integer pageSize) {
		List<List<Object[]>> batchList = new ArrayList<List<Object[]>>();
		// 总记录数
		Integer rowCount = 0;
		if (null != list && list.size() > 0) {
			rowCount = list.size();
		}
		// 记录分页数
		Integer listPage = 0;
		if (rowCount % pageSize == 0) {
			listPage = rowCount / pageSize;
		} else {
			listPage = rowCount / pageSize + 1;
		}
		Integer pageNo = 0;
		// 数据分页
		for (int n = 0; n < listPage; n++) {
			pageNo = n + 1;
			// 每页开始行
			Integer startIndex = pageNo * pageSize - pageSize;
			// 每页结束行
			Integer endIndex = 0;
			if (startIndex + pageSize > rowCount) {
				endIndex = rowCount;
			} else {
				endIndex = startIndex + pageSize;
			}
			// 子Map
			List<Object[]> tmpObjsList = new ArrayList<Object[]>();
			// 划分list
			for (int i = startIndex; i < endIndex; i++) {
				tmpObjsList.add(list.get(i));
			}
			batchList.add(tmpObjsList);
		}
		return batchList;
	}

	public static boolean executeBatch(Map<String, List<Object[]>> sqlBatchs) throws Exception {
		boolean result = false;
		if (MapUtils.isNotEmpty(sqlBatchs)) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				JDBCUtils utils = JDBCUtils.getInstance();
				conn = utils.getConnectionForMaster();
				conn.setAutoCommit(false);
				for (Map.Entry<String, List<Object[]>> entry : sqlBatchs.entrySet()) {
					pstmt = conn.prepareStatement(entry.getKey());
					for (Object[] objects : entry.getValue()) {
						for (int i = 0; i < objects.length; i++) {
							pstmt.setObject(i + 1, objects[i]);
						}
						pstmt.addBatch();
					}
					pstmt.executeBatch();
					pstmt.close();
					if (log.isInfoEnabled()) {
						System.out.println(entry.getKey());
					}
						
				}
				conn.commit();
				result = true;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				log.error("出错 sql batchs: " + serializeSQLBatchs(sqlBatchs));
				try {
					conn.rollback();
				} catch (SQLException ex) {
					log.error(e.getMessage(), e);
					throw e;
				}
				throw e;
			} finally {
				close(pstmt, conn); // 关闭相关连接
			}
		}
		return result;
	}

	public static boolean executeBatch(Map<String, List<Object[]>> sqlBatchs, int batchCount) throws Exception {
		boolean result = false;
		if (MapUtils.isNotEmpty(sqlBatchs)) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				JDBCUtils utils = JDBCUtils.getInstance();
				conn = utils.getConnectionForMaster();
				conn.setAutoCommit(false);
				int rowCount = 0;
				for (Map.Entry<String, List<Object[]>> entry : sqlBatchs.entrySet()) {
					pstmt = conn.prepareStatement(entry.getKey());
					rowCount = 0;
					for (Object[] objects : entry.getValue()) {
						for (int i = 0; i < objects.length; i++) {
							pstmt.setObject(i + 1, objects[i]);
						}
						pstmt.addBatch();
						if (++rowCount % batchCount == 0) {
							pstmt.executeBatch();
						}
					}
					if (rowCount % batchCount != 0) {
						pstmt.executeBatch();
					}
					pstmt.close();
					if (log.isInfoEnabled()) {
						System.out.println(entry.getKey());
					}
				}
				conn.commit();
				result = true;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				log.error("出错 sql batchs: " + serializeSQLBatchs(sqlBatchs));
				try {
					conn.rollback();
				} catch (SQLException ex) {
					log.error(e.getMessage(), e);
					throw e;
				}
				throw e;
			} finally {
				close(pstmt, conn); // 关闭相关连接
			}
		}
		return result;
	}

	/**
	 * 
	 * @Description:序列化字符串
	 * @author CYJ
	 * @date 2014-4-2 上午10:56:16
	 * @param sqlBatchs
	 * @return
	 */
	private static String serializeSQLBatchs(Map<String, List<Object[]>> sqlBatchs) {

		StringBuilder sb = new StringBuilder();

		sb.append("Map SQLBatch { \n");

		for (Map.Entry<String, List<Object[]>> entry : sqlBatchs.entrySet()) {

			sb.append("\t sql = ").append(entry.getKey()).append("\n\t [");

			int index = 0;

			for (Object[] objects : entry.getValue()) {

				sb.append("\n\t\t sqlParams[").append(index++).append("] = ").append(Arrays.toString(objects));
			}

			sb.append("\n\t ] \n");
		}

		return sb.append("}").toString();
	}
}
