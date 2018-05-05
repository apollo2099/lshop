package com.lshop.manage.lvAccount.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.cache.LvAreaCache;
import com.lshop.common.https.InterfaceRequest;
import com.lshop.common.https.InterfaceResponse;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.pojo.user.UnifiedUser;
import com.lshop.common.util.ApiUrlConstants;
import com.lshop.common.util.Constants;
import com.lshop.common.util.ExportExcel;
import com.lshop.common.xml.XmlParse;
import com.lshop.manage.lvAccount.service.LvAccountService;
/**
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvAccount.service.impl.LvAccountServiceImpl.java]  
 * @ClassName:    [LvAccountServiceImpl]   
 * @Description:  [用户账户信息管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-2 上午11:12:49]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-2 上午11:12:49]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0] 
 *
 */
@Service("LvAccountService")
@SuppressWarnings("unchecked")
public class LvAccountServiceImpl extends BaseServiceImpl implements LvAccountService {
	private static final Log logger	= LogFactory.getLog(LvAccountServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询用户列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 上午11:55:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 上午11:55:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.getList() method end*****");
		}
		Pagination page = (Pagination) dto.get("page");
		UnifiedUser unifiedUser = (UnifiedUser) dto.get("unifiedUser");
		
		InterfaceRequest request = new InterfaceRequest(ApiUrlConstants.USER_LIST);
		request.addParameter("pageno", page.getPageNum());
		request.addParameter("pagesize", page.getNumPerPage());
		request.addParameter("sortfield", 1);	// 按创建时间排序
		request.addParameter("sorttype", 1);	// 降序排序
		
		if (unifiedUser != null) {
			if (StringUtils.isNotBlank(unifiedUser.getAccount())) {
				request.addParameter("account", unifiedUser.getAccount());
			}
			if (StringUtils.isNotBlank(unifiedUser.getStatus())) {
				request.addParameter("status", unifiedUser.getStatus());
			}
			if(StringUtils.isNotBlank(unifiedUser.getCreateTimeBegin())){
				request.addParameter("createtimefirst", unifiedUser.getCreateTimeBegin()+" 00:00:00");
			}
			if(StringUtils.isNotBlank(unifiedUser.getCreateTimeEnd())){
				request.addParameter("createtimeend", unifiedUser.getCreateTimeEnd()+" 23:59:59");
			}
		}
		InterfaceResponse response = request.post();	// 调用接口
		
		if (response.getResponseStatus() == InterfaceResponse.STATUS_FAIL) {	// http请求返回失败状态
			String responseMessage = "接口请求错误：" + response.getResponseMessage();
			dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_FIAL);
			dto.put(Constants.MESSAGE_KEY, responseMessage);
			return dto;
		}
		XmlParse parse = new XmlParse(response.getResponseBody());
		String resultStatus = parse.getElementText("result.status");
		
		if (!Constants.STATUS_OK.equals(resultStatus)) {	// 请求的接口返回非成功状态
			String resultMessage = parse.getElementText("result.message");
			resultMessage = "接口请求错误：【" +  resultStatus + "】  " + resultMessage;
			dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_FIAL);
			dto.put(Constants.MESSAGE_KEY, resultMessage);
			return dto;
		}
		
		List<Element> userlist = parse.getElement("userlist").selectNodes("user");
		if (userlist != null && !userlist.isEmpty()) {
			List<UnifiedUser> list = new ArrayList<UnifiedUser>();
			for (int i = 0; i < userlist.size(); i++) {
				Element element = userlist.get(i);
				String status = parse.getElementText(element, "status");
				if (!"-1".equals(status)) {	// 过滤删除状态的用户
					UnifiedUser user = new UnifiedUser();
					user.setCode(parse.getElementText(element, "code"));
					user.setAccount(parse.getElementText(element, "account"));
					user.setNickname(parse.getElementText(element, "nickname"));
					user.setStatus(status);
					user.setLoginTime(parse.getElementText(element, "logindtime"));
					user.setCreateTime(parse.getElementText(element, "createtime"));
					user.setRegisterSource(parse.getElementText(element, "regbiz"));
					list.add(user);
				}
			}
			int pageno = Integer.parseInt(parse.getElementText("pageinfo.pageno"));
			int pagesize = Integer.parseInt(parse.getElementText("pageinfo.pagesize"));
			int total = Integer.parseInt(parse.getElementText("pageinfo.total"));
			
			page.setPageNum(pageno);
			page.setNumPerPage(pagesize);
			page.setTotalCount(total);
			page.setList(list);
			dto.setDefaultPage(page);
		}
		dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_SUCCESS);
		return dto;
	}
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除用户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 上午11:47:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 上午11:47:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.delete() method begin*****");
		}	
		//LvAccount lvAccount=(LvAccount) dto.get("lvAccount");
		String ids = dto.getAsString("ids");
		String hql="update LvAccount set status=-1 where id in ("+ids+")";
		dao.update(hql,null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.delete() method end*****");
		}	
	}
	
	/**
	 * 
	 * @Method: deleteByEmail 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-31 下午02:17:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-31 下午02:17:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void deleteByEmail (Dto dto) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.deleteByEmail() method begin*****");
		}	
		String ids = dto.getAsString("ids");
		LvAccount lvAccount=(LvAccount) dto.get("lvAccount");
		if(lvAccount!=null){
			if(ObjectUtils.isNotEmpty(lvAccount.getEmail())){
				String hql="update LvAccount set status=-1 where email = :email";
				Map<String ,Object> params=new HashMap<String ,Object>();
				params.put("email", lvAccount.getEmail());
				dao.update(hql,params);
			}
		}
		if(ObjectUtils.isNotEmpty(ids)){
			String hql="update LvAccount set status=-1 where email in ("+ids+")";
			dao.update(hql,null);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.deleteByEmail() method end*****");
		}	
	}
    /**
     * 
     * @Method: get 
     * @Description:  [获取用户详细信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-2 上午11:47:34]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-2 上午11:47:34]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Dto get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.get() method begin*****");
		}
		UnifiedUser unifiedUser = (UnifiedUser) dto.get("unifiedUser");
		
		InterfaceRequest request = new InterfaceRequest(ApiUrlConstants.USER_LIST);
		request.addParameter("codes", unifiedUser.getCode());
		
		InterfaceResponse response = request.post();	// 调用接口
		
		if (response.getResponseStatus() == InterfaceResponse.STATUS_FAIL) {	// http请求返回失败状态
			String responseMessage = "接口请求错误：" + response.getResponseMessage();
			dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_FIAL);
			dto.put(Constants.MESSAGE_KEY, responseMessage);
			return dto;
		}
		XmlParse parse = new XmlParse(response.getResponseBody());
		String resultStatus = parse.getElementText("result.status");
		
		if (!Constants.STATUS_OK.equals(resultStatus)) {	// 请求的接口返回非成功状态
			String resultMessage  = parse.getElementText("result.message");
			resultMessage = "接口请求错误：【" +  resultStatus + "】  " + resultMessage;
			dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_FIAL);
			dto.put(Constants.MESSAGE_KEY, resultMessage);
			return dto;
		}
		
		List<Element> userlist = parse.getElement("userlist").selectNodes("user");
		UnifiedUser user = null;
		LvAccount lvAccount = null;
		
		if (userlist != null && !userlist.isEmpty()) {
			Element element = userlist.get(0);
			String code = parse.getElementText(element, "code");
			String countryCode = parse.getElementText(element, "country");
			String status = parse.getElementText(element, "status");
			
			if (!"-1".equals(status)) {	// 过滤删除状态的用户
				user = new UnifiedUser();
				user.setCode(code);
				user.setAccount(parse.getElementText(element, "account"));
				user.setPassword(parse.getElementText(element, "password"));
				user.setNickname(parse.getElementText(element, "nickname"));
				user.setSex(parse.getElementText(element, "sex"));
				user.setQq(parse.getElementText(element, "qq"));
				user.setMsn(parse.getElementText(element, "msn"));
				user.setName(parse.getElementText(element, "name"));
				user.setTel(parse.getElementText(element, "tel"));
				user.setPhone(parse.getElementText(element, "phone"));
				user.setCountryCode(countryCode);
				user.setProvince(parse.getElementText(element, "province"));
				user.setCity(parse.getElementText(element, "city"));
				user.setAddress(parse.getElementText(element, "address"));
				user.setStatus(status);
				user.setLoginTime(parse.getElementText(element, "logindtime"));
				user.setCreateTime(parse.getElementText(element, "createtime"));
				user.setUpdateTime(parse.getElementText(element, "updatetime"));
				
				if (StringUtils.isNotBlank(countryCode)) {
					// 根据国家code获取国家名称
					String country = LvAreaCache.getCountryNameen(countryCode);
					user.setCountry(country);
				}
				lvAccount = getLocalAccount(code);
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvAccountServiceImpl.get() method end*****");
		}
		dto.put("lvAccount", lvAccount);
		dto.put("unifiedUser", user);
		dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_SUCCESS);
		return dto;
	}



	@Override
	public Dto save(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改用户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 上午11:55:10]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 上午11:55:10]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.update() method begin*****");
		}	
		UnifiedUser unifiedUser=(UnifiedUser) dto.get("unifiedUser");
		LvAccount lvAccount=(LvAccount) dto.get("lvAccount");
		
		Dto dto2 = new BaseDto();
		dto2.put("unifiedUser", unifiedUser);
		UnifiedUser user = (UnifiedUser) get(dto2).get("unifiedUser");
		if (user == null) {
			dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_FIAL);
			dto.put(Constants.MESSAGE_KEY, "用户不存在");
			return dto;
		}
		
		InterfaceRequest request = new InterfaceRequest(ApiUrlConstants.USER_EDIT);
		request.addParameter("code", unifiedUser.getCode());
		
		if (StringUtils.isNotBlank(unifiedUser.getNickname())) {
			request.addParameter("nickname", unifiedUser.getNickname());
		}
		if (StringUtils.isNotBlank(unifiedUser.getPassword())) {
			request.addParameter("password", unifiedUser.getPassword());
		}
		if (StringUtils.isNotBlank(unifiedUser.getSex())) {
			request.addParameter("sex", unifiedUser.getSex());
		}
		if (StringUtils.isNotBlank(unifiedUser.getBirthday())) {
			request.addParameter("birthday", unifiedUser.getBirthday());
		}
		if (StringUtils.isNotBlank(unifiedUser.getAddress())) {
			request.addParameter("address", unifiedUser.getAddress());
		}
		if (StringUtils.isNotBlank(unifiedUser.getPhone())) {
			request.addParameter("phone", unifiedUser.getPhone());
		}
		if (StringUtils.isNotBlank(unifiedUser.getCountryCode())) {
			request.addParameter("country", unifiedUser.getCountryCode());
		}
		if (StringUtils.isNotBlank(unifiedUser.getProvince())) {
			request.addParameter("province", unifiedUser.getProvince());
		}
		if (StringUtils.isNotBlank(unifiedUser.getCity())) {
			request.addParameter("city", unifiedUser.getCity());
		}
		if (StringUtils.isNotBlank(unifiedUser.getStatus())) {
			request.addParameter("status", unifiedUser.getStatus());
		}
		if (StringUtils.isNotBlank(unifiedUser.getQq())) {
			request.addParameter("qq", unifiedUser.getQq());
		}
		if (StringUtils.isNotBlank(unifiedUser.getMsn())) {
			request.addParameter("msn", unifiedUser.getMsn());
		}
		if (StringUtils.isNotBlank(unifiedUser.getName())) {
			request.addParameter("name", unifiedUser.getName());
		}
		if (StringUtils.isNotBlank(unifiedUser.getTel())) {
			request.addParameter("tel", unifiedUser.getTel());
		}
		
		InterfaceResponse response = request.post();	// 调用接口
		
		if (response.getResponseStatus() == InterfaceResponse.STATUS_FAIL) {	// http请求返回失败状态
			String responseMessage = "接口请求错误：" + response.getResponseMessage();
			dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_FIAL);
			dto.put(Constants.MESSAGE_KEY, responseMessage);
			return dto;
		}
		XmlParse parse = new XmlParse(response.getResponseBody());
		String resultStatus = parse.getElementText("result.status");
		
		if (!Constants.STATUS_OK.equals(resultStatus)) {	// 请求的接口返回非成功状态
			String resultMessage  = parse.getElementText("result.message");
			if ("427".equals(resultStatus)) {
				resultMessage = "昵称已被使用";
			} else {
				resultMessage = "接口请求错误：【" +  resultStatus + "】  " + resultMessage;
			}
			dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_FIAL);
			dto.put(Constants.MESSAGE_KEY, resultMessage);
			return dto;
		}
		
		Dto dto3 = new BaseDto();
		dto3.put("accountCode", lvAccount.getCode());
		LvAccount dbAccount = getAccount(dto3);
		if (dbAccount != null) {
			dbAccount.setModifyUserId(lvAccount.getModifyUserId());
			dbAccount.setModifyUserName(lvAccount.getModifyUserName());
			dbAccount.setModifyTime(lvAccount.getModifyTime());
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountServiceImpl.update() method end*****");
		}
		dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_SUCCESS);
		return dto;
	}
	
	/**
	 * 导出excel
	 */
	public Dto exportExcel(Dto dto) throws ServiceException {
		String codes = dto.getAsString("codes");
		UnifiedUser unifiedUser = (UnifiedUser) dto.get("unifiedUser");
		
		InterfaceRequest request = new InterfaceRequest(ApiUrlConstants.USER_LIST);
		request.addParameter("pageno", 1);
		request.addParameter("pagesize", Integer.MAX_VALUE);
		
		if (unifiedUser != null) {
			if (StringUtils.isNotBlank(unifiedUser.getAccount())) {
				request.addParameter("account", unifiedUser.getAccount());
			}
			if (StringUtils.isNotBlank(unifiedUser.getStatus())) {
				request.addParameter("status", unifiedUser.getStatus());
			}
		}
		if (StringUtils.isNotBlank(codes)) {
			request.addParameter("codes", codes);
		}
		
		InterfaceResponse response = request.post();	// 调用接口
		
		if (response.getResponseStatus() == InterfaceResponse.STATUS_FAIL) {	// http请求返回失败状态
			String responseMessage = "接口请求错误：" + response.getResponseMessage();
			dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_FIAL);
			dto.put(Constants.MESSAGE_KEY, responseMessage);
			return dto;
		}
		XmlParse parse = new XmlParse(response.getResponseBody());
		String resultStatus = parse.getElementText("result.status");
		
		if (!Constants.STATUS_OK.equals(resultStatus)) {	// 请求的接口返回非成功状态
			String resultMessage  = parse.getElementText("result.message");
			resultMessage = "接口请求错误：【" +  resultStatus + "】  " + resultMessage;
			dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_FIAL);
			dto.put(Constants.MESSAGE_KEY, resultMessage);
			return dto;
		}
		
		List<String[]> resultList = new ArrayList<String[]>();
		String[] titles = {"账户", "昵称", "状态", "注册来源", "注册时间", "最近登录时间", "真实姓名", "电话", "手机", "地址", "性别", "QQ", "MSN"};
		int[] widths = {25, 15, 15, 15, 20, 20, 20, 20, 20, 60, 10, 15, 15};
		
		List<Element> userlist = parse.getElement("userlist").selectNodes("user");
		
		if (userlist != null && !userlist.isEmpty()) {
			for (int i = 0; i < userlist.size(); i++) {
				Element element = userlist.get(i);
				String status = parse.getElementText(element, "status");
				if (!"-1".equals(status)) {	// 过滤删除状态的用户
					UnifiedUser user = new UnifiedUser();
					String account = parse.getElementText(element, "account");
					String nickname = parse.getElementText(element, "nickname");
					
					String registerSource ="" ;
					String sourceFlag= parse.getElementText(element, "regbiz");
					if(ObjectUtils.isNotEmpty(sourceFlag)){
						if(sourceFlag.equals("0007")){
							registerSource="Banana商城";
						}else if(sourceFlag.equals("0008")){
							registerSource="Tvpad商城";
						}else{
							registerSource="其他";
						}
					}
					String createtime = parse.getElementText(element, "createtime");
					String logintime = parse.getElementText(element, "logindtime");
					String name = parse.getElementText(element, "name");
					String tel = parse.getElementText(element, "tel");
					String phone = parse.getElementText(element, "phone");
					String countryCode = parse.getElementText(element, "country");
					String province = parse.getElementText(element, "province");
					String city = parse.getElementText(element, "city");
					String address = parse.getElementText(element, "address");
					String sex = parse.getElementText(element, "sex");
					String qq = parse.getElementText(element, "qq");
					String msn = parse.getElementText(element, "msn");
					String wholeAddress = LvAreaCache.getCountryNameen(countryCode) + province + city + address;
					wholeAddress = "null".equals(wholeAddress) ? "" : wholeAddress;
					
					if ("0".equals(status)) {
						status = "停用";
					} else if ("1".equals(status)) {
						status = "正常";
					} else if ("2".equals(status)) {
						status = "未激活";
					}
					
					if ("0".equals(sex)) {
						sex = "男";
					} else if ("1".equals(sex)) {
						sex = "女";
					}
					
					String[] item = new String[titles.length];
					item[0] = account;
					item[1] = nickname;
					item[2] = status;
					item[3] = registerSource;
					item[4] = createtime;
					item[5] = logintime;
					item[6] = name;
					item[7] = tel;
					item[8] = phone;
					item[9] = wholeAddress;
					item[10] = sex;
					item[11] = qq;
					item[12] = msn;
					resultList.add(item);
				}
			}
		}
		String path = dto.getAsString("path");
		ExportExcel.exportSimpleExcel(path, titles, resultList, widths);
		dto.put(Constants.STATUS_KEY, Constants.STATUS_CODE_SUCCESS);
		return dto;
	}
	
	/**
	 * 
	 * @Method: getAccount 
	 * @Description:  [根据用户code查询用户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-1-23 下午4:40:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-1-23 下午4:40:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvAccount getAccount(Dto dto) throws ServiceException {
		String accountCode=(String) dto.get("accountCode");
		LvAccount lvAccount=(LvAccount) dao.findUniqueByProperty(LvAccount.class, "code", accountCode);
		return lvAccount;
	}
	
	/**
	 * 
	 * @Method: getAccountByEmail 
	 * @Description:  [根据用户email查询用户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-1-23 下午4:40:44]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-1-23 下午4:40:44]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvAccount getAccountByEmail(Dto dto) throws ServiceException {
		String email=(String) dto.get("userEmail");
		if (ObjectUtils.isNotEmpty(email)) {
			String hql="from LvAccount where email=:email ";
			Map<String ,Object> params=new HashMap<String ,Object>();
			params.put("email",email);
			LvAccount lvAccount=(LvAccount) dao.findUnique(hql, params);
			return lvAccount;
		}
		return null;
	}
	
	/**
	 * 
	 * @Method: isAccountNickName 
	 * @Description:  [判断是否存在昵称]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-1-23 下午4:39:56]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-1-23 下午4:39:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Boolean
	 */
	public Boolean isExistAccountNickName(Dto dto)throws ServiceException{
		String nickname=(String) dto.get("nickname");
		String hql="from LvAccount where nickname=:nickname  and status<>-1";
		Map<String ,Object> param=new HashMap<String ,Object>();
		param.put("nickname",nickname);
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}

	/**
	 * 根据code获取本地账号对象
	 * @return
	 */
	private LvAccount getLocalAccount(String code) {
		String hql  = "FROM LvAccount WHERE code=:code";
		Map param = new HashMap();
		param.put("code", code);
		LvAccount lvAccount = (LvAccount) dao.findUnique(hql, param);
		return lvAccount;
	}
}
