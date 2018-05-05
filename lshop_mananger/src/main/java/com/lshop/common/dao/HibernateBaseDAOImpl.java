package com.lshop.common.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.impl.BasePo;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.DAOException;
import com.gv.core.hibernate3.Condition;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.hibernate3.OrderBy;

public class HibernateBaseDAOImpl extends HibernateBaseDAO {

	private static final String SET_STORE_ID_METHOD = "setStoreId";

	@Override
	public int countByProperty(Class entityClass, String property, Object value) throws DAOException {
		// TODO Auto-generated method stub
		return super.countByProperty(entityClass, property, value);
	}

	@Override
	public int countQueryResult(Finder finder, Map param) {
		finder = setFinderStoreId(finder);
		return super.countQueryResult(finder, param);
	}

	private Finder setFinderStoreId(Finder finder) {
		String hql=finder.getOrigHql();
		hql = setHQLrStoreId(hql);
		Finder finderTmp=Finder.create(hql);
		return finderTmp;
	}

	private  String setHQLrStoreId(String hql) {
		int findex=hql.indexOf(" from ");
		findex=findex!=-1?findex:hql.indexOf(" FROM ");
		if(hql.indexOf(" Lv", findex)!=-1){
			int windex=hql.indexOf(" where ");
			windex=windex!=-1?windex:hql.indexOf(" WHERE ");
			if (windex!=-1) {
				int lwindex=windex+" WHERE ".length();
				hql=hql.substring(0, windex)+" WHERE storeId = "+"'tvpadcn' AND "+hql.substring(lwindex);
			}else {
				int gindex=hql.indexOf(" GROUP ");
				gindex=gindex!=-1?gindex:hql.indexOf(" group ");
				if (gindex!=-1) {
					hql=hql.substring(0, gindex)+" WHERE storeId = "+"'tvpadcn'"+hql.substring(gindex);
				}else {
					int oindex=hql.indexOf(" ORDER ");
					oindex=oindex!=-1?oindex:hql.indexOf(" order ");
					if (oindex!=-1) {
						hql=hql.substring(0, oindex)+" WHERE storeId = "+"'tvpadcn'"+hql.substring(oindex);
					}else {
						hql=hql+" WHERE storeId = "+"'tvpadcn'";
					}
				}
			}
			
		}
		return hql;
	}


	@Override
	public void delete(BasePo entity) throws DAOException {
		setBasePoStoreId(entity);
		super.delete(entity);
	}

	@Override
	public void delete(List<BasePo> BasePoList) throws DAOException {
		// TODO Auto-generated method stub
		super.delete(BasePoList);
	}

	@Override
	public void delete(String hql, Map param) throws DAOException {
		// TODO Auto-generated method stub
		hql=setHQLrStoreId(hql);
		super.delete(hql, param);
	}

	@Override
	public Pagination find(Finder finder, int pageNo, int pageSize) throws DAOException {
		finder = setFinderStoreId(finder);
		return super.find(finder, pageNo, pageSize);
	}

	@Override
	public Pagination find(Finder finder) throws DAOException {
		finder = setFinderStoreId(finder);
		return super.find(finder);
	}

	@Override
	public List find(String hql, Map param) throws DAOException {
		hql=setHQLrStoreId(hql);
		return super.find(hql, param);
	}

	@Override
	public Pagination findAll(Class<?> entityClass, int pageNo, int pageSize, OrderBy... orders) throws DAOException {
		// TODO Auto-generated method stub
		return super.findAll(entityClass, pageNo, pageSize, orders);
	}

	@Override
	public Pagination findAll(Class<?> entityClass, OrderBy... orders) throws DAOException {
		// TODO Auto-generated method stub
		return super.findAll(entityClass, orders);
	}

	@Override
	public Pagination findByEg(Object eg, boolean anyWhere, Condition[] conds, int page, int pageSize, String... exclude) throws DAOException {
		// TODO Auto-generated method stub
		return super.findByEg(eg, anyWhere, conds, page, pageSize, exclude);
	}

	@Override
	public Pagination findByEgList(Object eg, boolean anyWhere, Condition[] conds, String... exclude) throws DAOException {
		// TODO Auto-generated method stub
		return super.findByEgList(eg, anyWhere, conds, exclude);
	}

	@Override
	public List<Object> findByProperty(Class entityClass, String property, Object value) throws DAOException {
		// TODO Auto-generated method stub
		return super.findByProperty(entityClass, property, value);
	}

	@Override
	public Object findUnique(String hql, Map param) throws DAOException {
		hql=setHQLrStoreId(hql);
		return super.findUnique(hql, param);
	}

	@Override
	public Object findUniqueByProperty(Class entityClass, String property, Object value) throws DAOException {
		// TODO Auto-generated method stub
		return super.findUniqueByProperty(entityClass, property, value);
	}

	@Override
	public void flush() throws DAOException {
		// TODO Auto-generated method stub
		super.flush();
	}

	@Override
	public Pagination getMapListByHql(String hql, int targetPage, int pagesize, Map param) throws DAOException {
		hql=setHQLrStoreId(hql);
		return super.getMapListByHql(hql, targetPage, pagesize, param);
	}

	@Override
	public Pagination getMapListByHql(String hql, Map param) throws DAOException {
		hql=setHQLrStoreId(hql);
		return super.getMapListByHql(hql, param);
	}

	@Override
	public Object load(Class<?> entityClass, Serializable id) throws DAOException {
		// TODO Auto-generated method stub
		return super.load(entityClass, id);
	}

	@Override
	public Object merge(BasePo entity) throws DAOException {
		// TODO Auto-generated method stub
		return super.merge(entity);
	}

	@Override
	public void refresh(Object entity) throws DAOException {
		setBasePoStoreId(entity);
		super.refresh(entity);
	}

	@Override
	public Serializable save(BasePo entity) throws DAOException {
		setBasePoStoreId(entity);
		return super.save(entity);
	}

	

	@Override
	public void saveOrUpdate(BasePo entity) throws DAOException {
		setBasePoStoreId(entity);
		super.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdate(Collection<BasePo> objList) throws DAOException {
		for (BasePo basePo : objList) {
			setBasePoStoreId(basePo);
		}
		super.saveOrUpdate(objList);
	}

	@Override
	public Object update(BasePo entity) throws DAOException {
		setBasePoStoreId(entity);
		return super.update(entity);
	}

	@Override
	public void update(String updatehql, Map param) {
		updatehql=setHQLrStoreId(updatehql);
		super.update(updatehql, param);
	}

	@Override
	public Object updateDefault(Object entity) {
		setBasePoStoreId(entity);
		return super.updateDefault(entity);
	}
	/**
	 * 设置实体数据店铺
	 * @param entity
	 */
	private void setBasePoStoreId(Object entity) {
		try {
			
			if (!(entity instanceof BaseUsers)) {
				Class entityClass=entity.getClass();
				Method setmethod=entityClass.getMethod(SET_STORE_ID_METHOD, String.class);
				setmethod.invoke(entity, "tvpadcn");
			}
		} catch (Exception e) {}
	}

}
