package com.lshop.web.userCenter.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.MD5;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.pojo.user.LvAccountInfo;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.web.userCenter.service.UserCenterService;

/**
 * 用户中心模块
 * @author zhengxue
 * @since 2.0 2012-07-03
 *
 */
@Service("UserCenterService")
public class UserCenterServiceImpl implements UserCenterService {
	
	@Resource
	private HibernateBaseDAO lvuserReadDao;
	@Resource
	private HibernateBaseDAO lvuserWriteDao;
	
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	private static final Log logger = LogFactory.getLog(UserCenterServiceImpl.class);
	
	/**
	 * 判断账号是否存在
	 * 由邮箱和昵称共同判断，当邮箱和昵称均存在的情况下，则表示已经注册。
	 * @param dto
	 * @return boolean
	 * @throws ServiceException
	 */
	@Override
	public Boolean isExistsAccount(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.isExistsAccount() method begin*****");
		}	
		
		String nickname=dto.getAsString("nickname");
		LvAccount lvAccount = (LvAccount) dto.getDefaultPo();
		
		Boolean flag = true;
		
		//判断昵称是否注册
		if (!StringUtil.IsNullOrEmpty(lvAccount.getNickname())) {
			//判断和修改前的昵称是否一样
			if(lvAccount.getNickname().trim().equals(nickname)){
				flag=false;
			}else{
				//如果和修改前的昵称不一样，再查看数据库中该昵称是否已被人注册
				String hql = "select count(*) from LvAccount where nickname = :nickname";
				Map<String, String> params = new HashMap<String, String>(1);
				params.put("nickname", lvAccount.getNickname().trim());
				Long rs = (Long) lvuserReadDao.findUnique(hql, params);
				if (rs.intValue() > 0) {
					flag = true;
				} else {
					flag = false;
				}
			}
		}
		
		//判断邮箱是否注册
//		if(flag==false){
			if (!StringUtil.IsNullOrEmpty(lvAccount.getEmail())) {
				String hql = "select count(*) from LvAccount where email = :email";
				Map<String, String> params = new HashMap<String, String>(1);
				params.put("email", lvAccount.getEmail().trim());
				Long rs = (Long) lvuserReadDao.findUnique(hql, params);
				if (rs.intValue() > 0) {
					flag = true;
				} else {
					flag = false;
				}
			}
//		}
			
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.isExistsAccount() method end*****");
		}		
		return flag;
	}
	
	
	/**
	 * 添加账号
	 * 在LvAccount,LvAccountInfo,LvAccountAddress中皆需要保存用户信息
	 * @param dto
	 * @return LvUser
	 * @throws ServiceException
	 */
	@Override
	public LvAccount addAccount(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.addAccount() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		//保存LvAccoun
		LvAccount lvAccount = (LvAccount) dto.getDefaultPo();
		try {
			lvAccount.setPwd(MD5.convert32(lvAccount.getPwd().trim()));
			lvAccount.setLastTime(new Date());
			lvAccount.setUserType(Short.parseShort("0"));
			lvAccount.setStatus(Short.parseShort("1"));
			lvAccount.setStoreId(storeFlag);
			lvAccount.setCreateTime(new Date());
			lvuserWriteDao.save(lvAccount);
				
			//保存LvAccountInfo
			LvAccountInfo info =new LvAccountInfo();
			info.setUserCode(lvAccount.getCode());
			info.setLastTime(new Date());
			info.setStoreId(storeFlag);
			info.setCode(CodeUtils.getCode());
			info.setCreateTime(new Date());
			lvuserWriteDao.save(info);
		} catch (Exception e) {
			return null;
		}
			
/**
	//保存LvAccountAddress
		LvAccountAddress address=new LvAccountAddress();
		address.setRelCode(lvAccount.getCode());
		address.setStoreId(storeFlag);
		address.setCode(CodeUtils.getCode());
		address.setCreateTime(new Date());
		address.setIsDefault((short)1);
		lvuserWriteDao.save(address);
 */	

		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.addAccount() method end*****");
		}	
		
		return lvAccount;
	}
	
	/**
	 * 查询账号信息
	 * 根据用户code查找
	 * @param dto
	 * @return LvAccount
	 * @throws ServiceException
	 */
	@Override
	public LvAccount getAccount(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAccount() method begin*****");
		}	
		
		LvAccount account=(LvAccount)lvuserReadDao.findUniqueByProperty(LvAccount.class, "code", dto.getAsString("uid"));
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAccount() method end*****");
		}	
		return account;
	}
	
	/**
	 * 用户登陆
	 * @param dto
	 * @return LvAccount
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvAccount login(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.login() method begin*****");
		}	
		
		//用邮箱登陆的情况
		String hql = "from LvAccount as u where u.email = :email  and u.pwd = :pwd and u.userType=0 ";
		Map param = new HashMap();
		String pwd=MD5.convert32(dto.getAsString("pwd"));
		param.put("email", dto.getAsString("uname"));
		param.put("pwd", pwd);
		LvAccount user= (LvAccount) lvuserReadDao.findUnique(hql, param);
		if(user!=null){
			 //修改最后登陆时间
			 SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 hql="update LvAccount set lastTime=:lastTime where id=:id";
			 param.clear();
			 param.put("lastTime", new Date());
			 param.put("id", user.getId());
			 lvuserWriteDao.update(hql,param);
			 dto.put("loginMark", 1); //标识是用邮箱登陆
			 return user;
		}
		
		//用昵称登陆的情况
		hql = "from LvAccount as u where u.nickname = :nickname  and u.pwd = :pwd and u.userType=0";
		param.clear();
		param.put("nickname", dto.getAsString("uname"));
		param.put("pwd", pwd);
		user= (LvAccount) lvuserReadDao.findUnique(hql, param);
		if(user!=null){
			 //修改最后登陆时间
			 SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 hql="update LvAccount set lastTime=:lastTime where id=:id";
			 param.clear();
			 param.put("lastTime", new Date());
			 param.put("id", user.getId());
			 lvuserWriteDao.update(hql,param);
			 dto.put("loginMark", 2); //标识是用昵称登陆
			 return user;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.login() method end*****");
		}	
		return null;
	}
	
	/**
	 * 找回密码
	 * 根据邮件找回，系统随机生成密码并发邮件给用户
	 * @param dto
	 * @return LvAccount
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvAccount password(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.password() method begin*****");
		}	
		
		String hql = "from LvAccount as u where u.email = :email and u.userType=0";
		Map param = new HashMap();
		param.put("email", dto.getAsString("email"));
		LvAccount account=(LvAccount) lvuserReadDao.findUnique(hql, param);
		
		if (account!=null) {
			String pwd=MD5.convert32(dto.getAsString("pwd").trim());
			String updatehql = "update LvAccount  set pwd =:pwd where id = :id";
			param.clear();
			param.put("pwd", pwd);
			param.put("id", account.getId());
			lvuserWriteDao.update(updatehql,param);
			
			if (logger.isInfoEnabled()){
				logger.info("*****UserCenterServiceImpl.password() method end*****");
			}	
		    return account;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.password() method end*****");
		}	
		return null;
	}

	/**
	 * 用户中心——我的账户
	 * 获取提醒的订单状态数据
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto getOrderNum(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getOrderNum() method begin*****");
		}	
		
		String em = dto.getAsString("em"); //得到邮箱
		String mallSystem = dto.getAsString("mallSystem"); //当前店铺所属系统（华扬orBanana）
		List<String> storeFlags = Constants.MALL_SYSTEM_TO_STORE.get(mallSystem); //当前系统下面所有的店铺标识
		String flags = storeFlags.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
	    HttpServletRequest  request=(HttpServletRequest)dto.get("request"); //得到请求
	    
	    Map param=new HashMap();
	    param.put("userEmail",em);
	    
	    //统计待付款数
	    Object notPlayNum = lvlogicReadDao.countQueryResult(Finder.create("select id FROM LvOrder WHERE userEmail=:userEmail and payStatus=0 and isdelete=0 and storeId in ("+flags+")"), param);
	    request.setAttribute("notPlayNum", notPlayNum);
	   //待提醒卖家发贷
		Object notFaHuoNum=lvlogicReadDao.countQueryResult(Finder.create("select id FROM LvOrder WHERE userEmail=:userEmail and  payStatus=1 and status=0 and isdelete=0 and storeId in ("+flags+")"), param);
		request.setAttribute("notFaHuoNum", notFaHuoNum);
		//待确认收货
	    Object notShouHuoNum=lvlogicReadDao.countQueryResult(Finder.create("select id FROM LvOrder WHERE userEmail=:userEmail and  payStatus=1 and status=1 and isdelete=0 and storeId in ("+flags+")"), param);
	    request.setAttribute("notShouHuoNum", notShouHuoNum);
	    //待评价
	    Object notCommentNum=lvlogicReadDao.countQueryResult(Finder.create("select id FROM LvOrder WHERE userEmail=:userEmail and  payStatus=1 and status=2 and isdelete=0 and storeId in ("+flags+")"), param);
	    request.setAttribute("notCommentNum", notCommentNum);
	    
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getOrderNum() method end*****");
		}	
	    return dto;
	}

	/**
	 * 用户中心——我的资料
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvAccountInfo getAccountInfo(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAccountInfo() method begin*****");
		}	
		
		LvAccountInfo info=(LvAccountInfo)lvuserReadDao.findUniqueByProperty(LvAccountInfo.class, "userCode", dto.getAsString("uid"));
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAccountInfo() method end*****");
		}	
		return info;
	}

	/**
	 * 用户中心——我的资料_编辑资料
	 * @param dto
	 * @throws ServiceException
	 */
	@Override
	public void editInfo(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.editInfo() method begin*****");
		}	
		
		LvAccountInfo lvAccountInfo = (LvAccountInfo) dto.getDefaultPo();
		
		//修改用户登录详细信息
		lvuserWriteDao.update(lvAccountInfo);
		//移动端第一次编辑资料要设置呢称
		if (StringUtils.isNotBlank(dto.getAsString("nickname"))) {
			String sql = "update LvAccount SET nickname=:nickname WHERE code=:code";
			Map<String, String> param = new HashMap<String, String>();
			param.put("nickname", dto.getAsString("nickname"));
			param.put("code", lvAccountInfo.getUserCode());
			lvuserWriteDao.update(sql, param);
		}
		/**
		String hql="from LvAccountInfo where userCode='"+dto.getAsString("uid")+"'";
		LvAccountInfo info=(LvAccountInfo) lvuserReadDao.find(hql,null).get(0);
		info.setName(lvAccountInfo.getName());
		info.setTel(lvAccountInfo.getTel());
		info.setMobile(lvAccountInfo.getMobile());
		info.setMsn(lvAccountInfo.getMsn());
		info.setSex(lvAccountInfo.getSex());
		info.setPostCode(lvAccountInfo.getPostCode());
		info.setQq(lvAccountInfo.getQq());
		info.setContryName(lvAccountInfo.getContryName());
		info.setCityName(lvAccountInfo.getCityName());
		info.setProvinceName(lvAccountInfo.getProvinceName());
		info.setAddress(lvAccountInfo.getAddress());
		info.setLastTime(new Date());
		info.setModifyTime(new Date());
		info.setModifyUserId(dto.getAsInteger("modifyUserId"));
		info.setModifyUserName(dto.getAsString("modifyUserName"));
		lvuserWriteDao.update(info);
		*/
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.editInfo() method end*****");
		}	
	}

	/**
	 * 用户中心——修改密码
	 * @param dto
	 * @throws ServiceException
	 */
	@Override
	public void updatePassword(Dto dto) throws ServiceException {

		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.updatePassword() method begin*****");
		}	
		
		if(null==dto){
			logger.error("缺少必要参数 ！");
			throw new ServiceException("缺少参数,不能执行此操作!");
		}
		
		String ucode = dto.getAsString("uid");
		String origPawd = dto.getAsString("origPwd");
		String newPawd = dto.getAsString("newPwd");
		if(null==ucode || StringUtil.IsNullOrEmpty(origPawd) || StringUtil.IsNullOrEmpty(newPawd)){
			logger.error("缺少必要参数 ！userCode["+ucode+"] orgiPwd["+origPawd+"] newPwd[+"+newPawd+"]");
			throw new ServiceException("缺少参数,不能执行此操作!");
		}
		
		//修改新密码
		String hql = "update LvAccount  set pwd =:pwd where code = :code";
		Map<String,Object> paraMap = new HashMap<String,Object>(2);
		paraMap.put("pwd", newPawd);
		paraMap.put("code", ucode);
	    lvuserWriteDao.update(hql, paraMap);
	    
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.updatePassword() method end*****");
		}	
	}

	/**
	 * 用户中心——我的订单
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination getOrderlist(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getOrderlist() method begin*****");
		}	
		
		Short payStatus = null; //搜索——支付状态
		Short status = null; //搜索——状态
		String orderId = dto.getAsString("orderId"); //搜索——订单号
		String storeName = dto.getAsString("storeName"); //搜索——商家名称
		storeName = storeName.replaceAll(" ", ""); //过滤掉空格
		String startTime = dto.getAsString("startTime"); //搜索——订单时间（起）
		String endTime = dto.getAsString("endTime"); //搜索——订单时间（止）
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mallSystem = dto.getAsString("mallSystem"); //当前店铺所属系统（华扬orBanana）
		String couponCode = dto.getAsString("couponCode"); //搜索——優惠碼
		
		List<String> storeFlags = Constants.MALL_SYSTEM_TO_STORE.get(mallSystem); //当前系统下面所有的店铺标识
		String flags = storeFlags.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		
		if(null!=dto.getAsString("payStatus")&&!"".equals(dto.getAsString("payStatus"))){
			payStatus=Short.parseShort(dto.getAsString("payStatus"));
		}
		
		if(null!=dto.getAsString("status")&&!"".equals(dto.getAsString("status"))){
			status=Short.parseShort(dto.getAsString("status"));
		}
	
		String em = dto.getAsString("em");
		Pagination page = dto.getDefaultPage();
		
	    Map<String,Object> param=new  HashMap();
	    StringBuffer hql=new StringBuffer();
	    
	    if(StringUtils.isNotBlank(couponCode)){
		    hql.append("select o from LvOrder o,LvOrderCoupon oc where o.oid=oc.orderId and o.userEmail=:userEmail and o.isdelete=0 and o.storeId in ("+flags+") and oc.couponCode like :couponCode");
		    param.put("userEmail",em);
		    param.put("couponCode","%"+couponCode+"%");
	    }else{
		    hql.append("select o from LvOrder o where o.userEmail=:userEmail and o.isdelete=0 and o.storeId in ("+flags+")");
		    param.put("userEmail",em);
	    }

		
		//搜索——支付状态
		if(null!=payStatus&&!"".equals(payStatus)){
			hql.append(" and o.payStatus=:payStatus");
			param.put("payStatus",payStatus);
		}
		//搜索——状态
		if(null!=status&&!"".equals(status)){
			hql.append(" and o.status=:status");
			param.put("status",status);
		}
		//搜索——订单号
		if(null!=orderId&&!"".equals(orderId)){
			hql.append(" and o.oid like :orderId");
			param.put("orderId","%"+orderId+"%");
		}
		//搜索——商家名称
		if(null!=storeName&&!"".equals(storeName)){
//			List<String> storeList = (List<String>)lvlogicReadDao.find("select distinct storeFlag from LvStore where name like '%"+storeName+"%' and storeFlag in ("+storeFlags+")", null);
			String sql = "select distinct storeFlag from LvStore where name like :storeName";
			HashMap map = new HashMap();
			map.put("storeName", "%"+storeName+"%");
			List<String> storeList = (List<String>)lvlogicReadDao.find(sql, map);
			if(null!=storeList && storeList.size()>0){
				String stores=storeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
				hql.append(" and o.storeId in ("+stores+")");
			}else{
				return null;
			}
				
		}else{
//			hql+=" and storeId in ("+storeFlags+")";
		}
		//搜索——订单时间（起）
		if(null!=startTime&&!"".equals(startTime)){
				try {
					hql.append(" and o.createTime >= :startTime");
					param.put("startTime",date.parse(startTime));
				} catch (ParseException e) {
					e.printStackTrace();
				}
		}
		//搜索——订单时间（止）
		if(null!=endTime&&!"".equals(endTime)){
				try {
					hql.append(" and o.createTime <= :endTime");
					param.put("endTime",date.parse(endTime));
				} catch (ParseException e) {
					e.printStackTrace();
				}
		}
		hql.append(" ORDER BY o.createTime DESC");	
		
		page = lvlogicReadDao.find(Finder.create(hql.toString()).setParams(param), page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getOrderlist() method end*****");
		}	
	    return page;
	}

	/**
	 * 我的评论——我发表的评论
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public Pagination getCommentList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getCommentList() method begin*****");
		}	
		
		String mallSystem = dto.getAsString("mallSystem"); //当前店铺所属系统（华扬orBanana）
		List<String> storeFlags = Constants.MALL_SYSTEM_TO_STORE.get(mallSystem); //当前系统下面所有的店铺标识
		String flags = storeFlags.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		HttpServletRequest request =(HttpServletRequest)dto.get("request");
		Pagination page = dto.getDefaultPage();
		String uid=dto.getAsString("uid");
		String hql = "";
		HashMap param = new HashMap();
		
		//评论列表
		hql = "from LvOrderComment  where uid=:uid and isDelete=0 and replyId=0 and storeId in ("+flags+") order by id desc";
		param.put("uid", uid);
		page = lvlogicReadDao.find(Finder.create(hql).setParams(param),  page.getPageNum(),page.getNumPerPage());
		
		//统计我发布的评论数
		request.setAttribute("coutMyComment", page.getTotalCount());
		
		//统计回复我评论数
		StringBuffer sql=new StringBuffer("select id from LvOrderComment  where   uid=:uid");
		sql.append(" and id in (select replyId from LvOrderComment where replyId<>0 and isDelete=0 ) and storeId in ("+flags+")");
		param.clear();
		param.put("uid", uid);
		request.setAttribute("coutReplyComment", this.lvlogicReadDao.countQueryResult(Finder.create(sql.toString()), param));
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getCommentList() method end*****");
		}	
	 	return  page;
	}

	/**
	 * 我的评论——管理员回复的评论
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination getReplyList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getReplyList() method begin*****");
		}	
		
		String mallSystem = dto.getAsString("mallSystem"); //当前店铺所属系统（华扬orBanana）
		List<String> storeFlags = Constants.MALL_SYSTEM_TO_STORE.get(mallSystem); //当前系统下面所有的店铺标识
		String flags = storeFlags.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		HttpServletRequest request =(HttpServletRequest)dto.get("request");
		Pagination page = dto.getDefaultPage();
		String uid=dto.getAsString("uid");
		HashMap param = new HashMap();
		
		//被回复的评论列表
		StringBuffer sql=new StringBuffer("from LvOrderComment  where   uid=:uid");
		sql.append(" and id in (select replyId from LvOrderComment where replyId<>0 and isDelete=0)");
		sql.append("  and storeId in ("+flags+") order by id desc");
		param.put("uid", uid);
		Finder finder = Finder.create(sql.toString());
	    page=(Pagination)lvlogicReadDao.find(finder.setParams(param),  page.getPageNum(), page.getNumPerPage());
	    
	    //统计被回复的评论数
	    request.setAttribute("coutReplyComment", page.getTotalCount());
	    
	  //统计我发布的评论数
	    Finder f=Finder.create("select id from LvOrderComment  where uid=:uid");
	    f.append("  and isDelete=0 and replyId=0  and storeId in ("+flags+")");
	    param.clear();
	    param.put("uid", uid);
		request.setAttribute("coutMyComment", this.lvlogicReadDao.countQueryResult(f, param));
		
		//管理员回复的评论
	    if(page.getList()!=null&&page.getList().size()>0){
	    	String hql = "from LvOrderComment where replyId in (select id from LvOrderComment where uid=:uid and isDelete=0 and replyId=0) and isDelete=0  and storeId in ("+flags+")";
	    	param.clear();
	    	param.put("uid", uid);
	    	request.setAttribute("replyList", this.lvlogicReadDao.find(hql,param));
	    }
	    
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getReplyList() method end*****");
		}	
		return page;
	}

	/**
	 * 用户中心——收货地址
	 * 显示收货地址列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination getAddressList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAddressList() method begin*****");
		}	
		
//		String storeFlag=dto.getAsString("storeFlag");
		Pagination page = dto.getDefaultPage();
		String uid=dto.getAsString("uid");
		
		String hql = "from LvAccountAddress where relCode=:uid order by createTime desc";
		HashMap param = new HashMap();
		param.put("uid", uid);
//		page = lvuserReadDao.find( Finder.create("from LvAccountAddress where relCode='"+uid+"' and storeId='"+storeFlag+"'"), page.getPageNum(),5);
		page = lvuserReadDao.find( Finder.create(hql).setParams(param), page.getPageNum(),5);
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAddressList() method end*****");
		}	
		return page;
	}

	/**
	 * 用户中心——收货地址——通过code查找账户地址LvAccountAddress
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvAccountAddress getAddressByCode(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAddressByCode() method begin*****");
		}	
		
		String addressCode=dto.getAsString("addressCode");
		LvAccountAddress address=(LvAccountAddress)lvuserReadDao.findUniqueByProperty(LvAccountAddress.class, "code", addressCode);
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAddressByCode() method end*****");
		}	
		return address;
	}

	/**
	 * 获取默认收货地址
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvAccountAddress getDefaultAddress(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getDefaultAddress() method begin*****");
		}	
		
//		String storeFlag=dto.getAsString("storeFlag");
		String uid=dto.getAsString("uid");
		
		String hql = "from LvAccountAddress where relCode=:uid and isDefault=1 ";
		HashMap param = new HashMap();
		param.put("uid", uid);
//		List<LvAccountAddress> dAddressList=(List<LvAccountAddress>)lvuserReadDao.find("from LvAccountAddress where relCode='"+uid+"' and isDefault=1 and storeId='"+storeFlag+"'", null);
		List<LvAccountAddress> dAddressList=(List<LvAccountAddress>)lvuserReadDao.find(hql, param);
		if(dAddressList.size()>0){
			if (logger.isInfoEnabled()){
				logger.info("*****UserCenterServiceImpl.getDefaultAddress() method end*****");
			}	
			return dAddressList.get(0);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getDefaultAddress() method end*****");
		}	
		return null;
	}

	/**
	 * 获取非默认收货地址列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvAccountAddress> getNonDefaultAddress(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getNonDefaultAddress() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String uid=dto.getAsString("uid");
		
		String hql = "from LvAccountAddress where relCode=:uid and isDefault=0 and storeId=:storeFlag";
		HashMap param = new HashMap();
		param.put("uid", uid);
		param.put("storeFlag", storeFlag);
		List<LvAccountAddress> ndAddressList=(List<LvAccountAddress>)lvuserReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getNonDefaultAddress() method end*****");
		}	
		return ndAddressList;
	}

	/**
	 * 获取所有收货地址列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvAccountAddress> getAllAddress(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAllAddress() method begin*****");
		}	
		
//		String storeFlag=dto.getAsString("storeFlag");
		String uid=dto.getAsString("uid");
		
		String hql = "from LvAccountAddress where relCode=:uid order by createTime desc";
		HashMap param = new HashMap();
		param.put("uid", uid);
//		List<LvAccountAddress> addressList=(List<LvAccountAddress>)lvuserReadDao.find("from LvAccountAddress where relCode='"+uid+"' and storeId='"+storeFlag+"'", null);
		List<LvAccountAddress> addressList=(List<LvAccountAddress>)lvuserReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAllAddress() method end*****");
		}	
		return addressList;
	}

	/**
	 * 根据国家ID获取对应的洲省
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvArea> getProvinces(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getProvinces() method begin*****");
		}	
		
		Integer parentid=dto.getAsInteger("parentid");
//		String storeId=dto.getAsString("storeId");
		
		HashMap map=new HashMap();
		map.put("parentid", parentid);
//		map.put("storeId", storeId);
		String hql="from LvArea where parentid=:parentid";
		List<LvArea> areas=(List<LvArea>)lvlogicReadDao.find(hql, map);
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getProvinces() method end*****");
		}	
		
		return areas;
	}

	/**
	 * add by dingh
	 */
	@Override
	public LvAccount getAccountByEmail(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAccountByEmail() method begin*****");
		}	
		
		LvAccount account=(LvAccount)lvuserReadDao.findUniqueByProperty(LvAccount.class, "email", dto.getAsString("email"));
		
		if(account != null){
			String code = dto.getAsString("uid");//如果同时有传入code，则判断传入的code和实际code是否匹配
			if(!code.equals(account.getCode())){//如果不匹配则修改为传入的code
				lvuserWriteDao.update("UPDATE LvAccount SET code='"+code+"' WHERE email='"+account.getEmail()+"'", null);
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAccountByEmail() method end*****");
		}	
		return account;
	}
	
	
	public LvAccount getAccountByEmail(String email)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAccountByEmail() method begin*****");
		}	
		
		LvAccount account=null;
		if(ObjectUtils.isNotEmpty(email)){
			String hql="from LvAccount where email=:email and status=1";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("email", email);
			List<LvAccount> list= lvuserReadDao.find(hql, param);
			if(ObjectUtils.isNotEmpty(list)){
				account=list.get(0);
			}
		}
	
		
		if (logger.isInfoEnabled()){
			logger.info("*****UserCenterServiceImpl.getAccountByEmail() method end*****");
		}	
		return account;
	}

}
