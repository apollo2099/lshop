/**
 * <p>Title: IDGenerator3.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */
package com.lshop.common.hibernate;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.mapping.Table;
import org.hibernate.type.Type;
import org.hibernate.util.StringHelper;

/**
 * @author xusl
 *
 */
public class IDGenerator3 implements IdentifierGenerator, Configurable
{
	private static final Log	logger	= LogFactory.getLog(IDGenerator3.class);
	private long next;
	private String sql;
	private Class returnClass;
	public static Long BASE_NUM =1000000L;
	private long baseNum =BASE_NUM;
	/* (non-Javadoc)
	 * @see org.hibernate.id.IdentifierGenerator#generate(org.hibernate.engine.SessionImplementor, java.lang.Object)
	 */
	@Override
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException
	{
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled())
		{
			logger.info("*****IDGenerator3: generate method begin*****");
		}
		if (sql!=null) {
			getNext( session );
		}
		if (logger.isInfoEnabled())
		{
			logger.info("*****IDGenerator3: generate method end*****");
		}
		return createId(next++, returnClass);
	}

	/* (non-Javadoc)
	 * @see org.hibernate.id.Configurable#configure(org.hibernate.type.Type, java.util.Properties, org.hibernate.dialect.Dialect)
	 */
	@Override
	public void configure(Type type, Properties params, Dialect dialect)
			throws MappingException
	{
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled())
		{
			logger.info("*****IDGenerator3: configure method begin*****");
		}
		String tableList = params.getProperty("tables");
		if (tableList==null) tableList = params.getProperty(PersistentIdentifierGenerator.TABLES);
		String[] tables = StringHelper.split(", ", tableList);
		String column = params.getProperty("column");
		if (column==null) column = params.getProperty(PersistentIdentifierGenerator.PK);
		String schema = params.getProperty(PersistentIdentifierGenerator.SCHEMA);
		String catalog = params.getProperty(PersistentIdentifierGenerator.CATALOG);
		returnClass = type.getReturnedClass();

		StringBuffer buf = new StringBuffer();
		for ( int i=0; i<tables.length; i++ ) {
			if (tables.length>1) {
				buf.append("select ").append(column).append(" from ");
			}
			buf.append( Table.qualify( catalog, schema, tables[i] ) );
			if ( i<tables.length-1) buf.append(" union ");
		}
		if (tables.length>1) {
			buf.insert(0, "( ").append(" ) ids_");
			column = "ids_." + column;
		}
		
		sql = "select max(" + column + ") from " + buf.toString(); // +" where id <" +(baseNum+999999999L) +" and id>="+baseNum ;
		if (logger.isInfoEnabled())
		{
			logger.info("*****IDGenerator3: configure method end*****");
		}

	}
	/**
	 * 
	 *2010-3-23 
	 *xusl
	 *@param session
	 */
	private void getNext( SessionImplementor session ) {

		logger.debug("fetching initial value: " + sql);
		
		try {
			baseNum = BASE_NUM;
			String sqlTemp = sql + " where id <" +(baseNum+999999999L) +" and id>=" + baseNum;

			//PreparedStatement st = session.getBatcher().prepareSelectStatement(sql);
			PreparedStatement st = session.getBatcher().prepareSelectStatement(sqlTemp);
			try {
				ResultSet rs = st.executeQuery();
				try {
					if ( rs.next() ) {
						next = rs.getLong(1) + 1;
						if ( next < baseNum ) next = baseNum;
					}
					else {
						next = baseNum;
					}
					sql=null;
					logger.debug("first free id: " + next);
				}
				finally {
					rs.close();
				}
			}
			finally {
				session.getBatcher().closeStatement(st);
			}
			
		}
		catch (SQLException sqle) {
			throw JDBCExceptionHelper.convert(
					session.getFactory().getSQLExceptionConverter(),
					sqle,
					"could not fetch initial value for increment generator",
					sql
				);
		}
	}
	/**
	 * 根据配置文件定义的数据类型进行类型转换
	 * @param value
	 * @param clazz
	 * @return
	 * @throws IdentifierGenerationException
	 */
	private Serializable createId(long value, Class clazz) throws IdentifierGenerationException {
		if ( clazz==Long.class ) {
			return new Long(value);
		}
		else if ( clazz==Integer.class ) {
			return new Integer( (int) value );
		}
		else if ( clazz==Short.class ) {
			return new Short( (short) value );
		}
		else {
			throw new IdentifierGenerationException("this id generator generates long, integer, short");
		}
	}
}
