package com.gv.base.springSecurity.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gv.base.springSecurity.common.pojo.BaseUserDetail;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.hibernate3.HibernateBaseDAO;

/**
 * 
 * 项目名称：base_demo   
 * 类名称：UserDetailsServiceImpl   
 * 类描述：   从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:03:56   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:03:56   
 * 修改备注：   
 * @version
 */
public class BaseUserDetailsServiceImpl implements UserDetailsService{

	@Resource private HibernateBaseDAO dao;
	public UserDetails loadUserByUsername(String userAccount)throws UsernameNotFoundException, DataAccessException {
			
			//Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
			//获取用户对象
//			BaseUsers baseUser = (BaseUsers) dao.findUniqueByProperty(BaseUsers.class, "userAccount", userAccount);
			String hql="from BaseUsers where status<>-1 and userAccount=:userAccount";
			Map param=new HashMap();
			param.put("userAccount", userAccount);
			BaseUsers baseUser =(BaseUsers)dao.findUnique(hql, param);
			if (null==baseUser) {
				throw new AuthenticationCredentialsNotFoundException("该用户不存在");
			}
			//取得用户的权限
			hql = "from BaseAuthorities where authoritiesCode in(select authorityCode from BaseRolesAuthorities where roleCode in(select roleCode from BaseUsersRoles where userCode='"+baseUser.getUserCode()+"') )";
			List<GrantedAuthority> auth = (List<GrantedAuthority>)dao.find(hql, null);
			
			if (auth.size()<=0) {
				throw new AuthenticationCredentialsNotFoundException("权限不足");
			}
			//取得用户的密码
			String password=baseUser.getUserPassword();
			BaseUserDetail user = new BaseUserDetail(userAccount, baseUser.getUserName(), baseUser.getUserPassword(),  baseUser.getUserCode(), true, true, true, true, auth);
			
	        return user;
	}
	
	
}


