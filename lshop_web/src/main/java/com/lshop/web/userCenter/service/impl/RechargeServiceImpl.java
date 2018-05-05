package com.lshop.web.userCenter.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.gv.base.model.spring.ApplicationContextHolder;
import com.gv.business.ws.boss.rechargeCard.RechargeableDto;
import com.gv.business.ws.boss.rechargeCard.SOAPException_Exception;
import com.gv.business.ws.boss.rechargeCard.WSRechargeableService;
import com.gv.business.ws.boss.rechargeRecord.RechargeRecordDto;
import com.gv.business.ws.boss.rechargeRecord.SynRechargeRecordDto;
import com.gv.business.ws.boss.rechargeRecord.WSSynRechargeRecordService;
import com.gv.business.ws.boss.resumeRecord.StbResumeDto;
import com.gv.business.ws.boss.resumeRecord.SynStbResumeItemDetailDto;
import com.gv.business.ws.boss.resumeRecord.SynStbResumeItemDto;
import com.gv.business.ws.boss.resumeRecord.SynStbResumeRecordDto;
import com.gv.business.ws.boss.resumeRecord.SynStbResumeRecordsDto;
import com.gv.business.ws.boss.resumeRecord.WSStbResumeRecordService;
import com.gv.business.ws.boss.userinfo.UserInfoDto;
import com.gv.business.ws.boss.userinfo.WSUserInfoService;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.StringUtil;
import com.gv.epg.common.util.AES;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvRecharge;
import com.lshop.common.pojo.logic.LvVbPlans;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.web.userCenter.pojo.BossUser;
import com.lshop.web.userCenter.service.RechargeService;
@Service("RechargeService")
public class RechargeServiceImpl implements RechargeService {
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
//	@Resource
//	private WSRechargeableService chargecardWs;//充值卡充值
//	@Resource
//	private WSStbResumeRecordService resumeRecordWs;//充值查询
//	@Resource
//	private WSSynRechargeRecordService rechargeRecordWs;//
	

	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/conf/service/spring-base-context.xml",
				"/conf/cxf/cxf-base.xml",
				"/conf/cxf/cxf-boss-client.xml"
				);
		
		Object obj = context.getBean("chargecardWs");
		System.out.println(obj.toString());
	}
	
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		String email=dto.getAsString("email");
		String year=dto.getAsString("year");
		String storeFlag=dto.getAsString("storeFlag");
		String storeFlagStr=" ";
		if("www".equals(storeFlag)||"en".equals(storeFlag)||"mtmcn".equals(storeFlag)){
			storeFlagStr="'www','en','mtmcn'";
		}else if("bmen".equals(storeFlag)||"bmcn".equals(storeFlag)||"mbmcn".equals(storeFlag)){
			storeFlagStr="'bmen','bmcn','mbmcn'";
		}
		String hql="from LvRecharge where cuserEmail=:email and storeFlag in("+storeFlagStr+") and rtype!=1 and createDate like '"+year+"%' order by createDate desc";
		Map params =new HashMap();
		params.put("email", email);

		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
	    Pagination pag = lvlogicReadDao.find(finder, page.getPageNum(), 10);
		return pag;
	}

	@Override
	public LvRecharge saveOrder(Dto dto) throws ServiceException {
		LvRecharge recharge=(LvRecharge)dto.get("model");
		String type=dto.getAsString("type");
		String currency=dto.getAsString("currency");
		Float money=0f;
		if("USD".equalsIgnoreCase(currency)){
		money=recharge.getVbNum()*Constants.rateVbNum;
	    }
		if("CNY".equalsIgnoreCase(currency)){
		money=recharge.getVbNum()*Constants.rateVbNumCny;
		}
		recharge.setCurrency(currency);
		recharge.setMoney(((float) Math.round(money * 100)) / 100);
		recharge.setRnum(createOrderId("q"));
		recharge.setRtype(0);//表示未选择支付方式
		recharge.setStatus(0);
		recharge.setPayStatus(0);
		recharge.setCreateDate(new Date());
		recharge.setCode(CodeUtils.getCode());
		//if("1".equals(type)){
			recharge.setIsForOther(0);//0给自己充值
		//}else if("2".equals(type)){
			//recharge.setAccounts(recharge.getAccounts().trim());
			//recharge.setIsForOther(1);//1给别人充值
		//}
		lvlogicWriteDao.save(recharge);
		return recharge;
	}
	@Override
	public LvRecharge updateOrder(Dto dto) throws ServiceException {
		LvRecharge recharge=(LvRecharge)dto.get("model");
		lvlogicWriteDao.update(recharge);
		return recharge;
	}	
    
	
	@Override
	public LvRecharge getOrder(Dto dto) throws ServiceException {
		String rnum=dto.getAsString("rnum");//订单号
		return (LvRecharge)lvlogicReadDao.findUniqueByProperty(LvRecharge.class, "rnum", rnum);
	}
	@Override
	public Map cardCharge(Dto dto) throws ServiceException, SOAPException_Exception {
        Integer rechargeResource = dto.getAsInteger("rechargeResource");
        String type=dto.getAsString("type");
        LvRecharge recharge=(LvRecharge)dto.get("model");
        String cardNum=recharge.getRcardNum();//卡号
        String cardPwd=new AES().encrypt(dto.getAsString("cardPwd"));//卡号密码
		Map map=new HashMap();
		RechargeableDto resDto=null;
		WSRechargeableService chargecardWs=(WSRechargeableService) ApplicationContextHolder.getBean("chargecardWs");
		//if("1".equals(type)){//自己充值
			//recharge.setAccounts(recharge.getCuserEmail());
			resDto=chargecardWs.recharge(recharge.getAccounts() , cardNum, cardPwd, 1, null, rechargeResource);
			recharge.setIsForOther(0);
	    //}
		/*else if("2".equals(type)){//给别人充值
	    	recharge.setAccounts(recharge.getAccounts().trim());
	    	resDto=chargecardWs.recharge(recharge.getCuserEmail(),  recharge.getRcardNum(), cardPwd, 2, recharge.getAccounts(), rechargeResource);
	    	recharge.setIsForOther(1);
	    }*/
		if(resDto.getStatus()==1){
			recharge.setRnum(createOrderId("f"));
			recharge.setRtype(1);//充值卡充值
			recharge.setCreateDate(new Date());
			recharge.setCode(CodeUtils.getCode());
			recharge.setMoney(0f);
			recharge.setVbNum(resDto.getAmount());
			recharge.setStatus(1);
			this.lvlogicWriteDao.save(recharge);
		}
		map.put("status", resDto.getStatus());
		map.put("amount", resDto.getAmount());//充值卡面值
		map.put("balnace", resDto.getBalance());//账号余额
		map.put("orderno", resDto.getOrderno());//订单号
		return map;
	}

	@Override
	public Dto findConsumeRecordList(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String account=dto.getAsString("email");
		String year=dto.getAsString("year");
		SimplePage page = (SimplePage)dto.get("page");
		WSStbResumeRecordService resumeRecordWs=(WSStbResumeRecordService) ApplicationContextHolder.getBean("resumeRecordWs");
		StbResumeDto resumeDto=resumeRecordWs.synResumeRecord(account,null,null, null,  year,page.getPageNum(), 10);
		dto.put("status", resumeDto.getResult().getStatus());//查询是否成功失败
		SynStbResumeRecordsDto dataDto=resumeDto.getRecords();
		List<SynStbResumeRecordDto>recordList=dataDto.getRecord();
		List <Map> list= new ArrayList();
		Pagination pag = null;
		dto.put("totalamt",resumeDto.getTotalamt());
		if(recordList!=null&&recordList.size()>0){//封装成pagg list数据
			com.gv.business.ws.boss.resumeRecord.PageInfoDto pageInfo=resumeDto.getPageinfo();
			pag=new Pagination(pageInfo.getPageno(), pageInfo.getPagesize(), pageInfo.getTotal());
			for(SynStbResumeRecordDto item:recordList){
				List<SynStbResumeItemDto>  itemList=item.getItems();
				StringBuffer remark=new StringBuffer();
				if(itemList!=null&&itemList.size()>0){
					SynStbResumeItemDto itemDto=itemList.get(0);
					if(itemDto!=null){
						List<SynStbResumeItemDetailDto> iList=itemDto.getItem();
						if(iList!=null&&iList.size()>0){
							for (int i = 0; i < iList.size(); i++) {
								if(i>0){
									remark.append(";");
								}
								//解析json格式的备注
								remark.append(getJsonStr( iList.get(i).getRemark(),"cn"));
							}
						}
					}
				}
				
			     	 Map map=new HashMap();
		             map.put("remark",remark.toString());
			     	 map.put("tradeno",item.getTradeno());//交易流水号
				     map.put("mac", item.getMac());
				     map.put("createDate", item.getDate());//充值时间
				     map.put("amt", item.getAmt());//消费金额
				     list.add(map);
					}
				}else{
					pag=new Pagination(0, 0, 0);
				}
			    pag.setList(list);
			    dto.put("pag", pag);
	           return dto;
	}

	@Override
	public Dto findChargeRecordList(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String account=dto.getAsString("email");
		String year=dto.getAsString("year");
		SimplePage page = (SimplePage)dto.get("page");
		WSSynRechargeRecordService rechargeRecordWs=(WSSynRechargeRecordService) ApplicationContextHolder.getBean("rechargeRecordWs");
		SynRechargeRecordDto dataDto=rechargeRecordWs.rechargeRecord(account, null, null, year, null, page.getPageNum(), 10); 
		dto.put("status", dataDto.getResult().getStatus());//返回查询是否成功失败
		List<RechargeRecordDto> recordList=dataDto.getRecord();
	    Pagination  pag=null;
		List <Map> list= new ArrayList();
		dto.put("totalamt", dataDto.getTotalamt());
		if(recordList!=null&&recordList.size()>0){//封装成pagg list数据
			com.gv.business.ws.boss.rechargeRecord.PageInfoDto pageInfo=dataDto.getPageinfo();
			 pag=new Pagination(pageInfo.getPageno(), pageInfo.getPagesize(), pageInfo.getTotal());
			  for(RechargeRecordDto item:recordList){
			     	 Map map=new HashMap();
				     map.put("createDate", item.getDate());//充值时间
				     map.put("amt", item.getAmt());
				     map.put("tradeno", item.getTradeno());//交易号
				     map.put("source", item.getClient());//来源
				     list.add(map);
				}
		}else{
			pag=new Pagination(0, 0, 0);
		}
	    pag.setList(list);
	    dto.put("pag", pag);
	    return dto;
	}

	@Override
	public void sysCalcelOrder() throws ServiceException {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -10);
		Date date=cal.getTime();
		String hql="from LvRecharge where payStatus=0 and status=0 and rtype>1 and createDate<:createDate";
		Map params =new HashMap();
		params.put("createDate", date);
		List<LvRecharge> tList=lvlogicReadDao.find(hql, params);
	    if(tList!=null&&tList.size()>0){
		for(LvRecharge t:tList){
			if(t.getStatus()==0){
				t.setStatus(3);
				lvlogicWriteDao.update(t);
			}
		}
	   }	
	}
	
	public BossUser baseinfo(Dto dto) throws ServiceException {
		String account = dto.getAsString("email");

		//************boss查询V币余额***************
		WSUserInfoService userinfoWs=(WSUserInfoService) ApplicationContextHolder.getBean("userinfoWs");

		UserInfoDto uDto = userinfoWs.queryUserInfo(account, "");
		
		BossUser user = new BossUser();
		user.setAccountno(uDto.getAccountno());//用户账号
		user.setBalance(uDto.getBalance());//V币余额
//		user.setActivestatus(uDto.getActivestatus());
//		user.setAnnualsum(uDto.getAnnualsum());
//		user.setMsg(uDto.getMsg());
//		user.setRegisterdate(uDto.getRegisterdate());//注册日期
//		user.setStatus(uDto.getStatus());
//		user.setUserid(uDto.getUserid());//用户id
		if (uDto.getUserstatus() != null) {
			user.setUserstatus(uDto.getUserstatus());
		} else {
			user.setUserstatus(-1);
		}
		return user;
	}
	
	public static synchronized String  createOrderId(String mark){
		int no=new Random().nextInt(99999);
		while (no<10000||no>99999) {
			no=new Random().nextInt(99999);
		}
		return mark+StringUtil.dateFormat(new Date(), "yyyyMMddHHmmss")+no;
	}
	
	public static  String getJsonStr(String remarkstr,String i18n){
		//解析json格式的备注
		if(remarkstr.matches("\\{.*\\}")){
			JSONArray array = JSONArray.fromObject("["+remarkstr+"]");
			if (array.size() > 0) {
               if(i18n.equals("en_US")){
               	i18n="en";
               }
				JSONObject obj = array.getJSONObject(0);
				remarkstr = obj.getString(i18n);
			}
		}
	   return remarkstr;
	}
	
	/**
	 * 获取支付方式列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvPaymentStyle> getPaymentStyleList(Dto dto) throws ServiceException {
		String storeFlag = dto.getAsString("storeFlag");
		Integer payType = dto.getAsInteger("payType");
		Map param = new HashMap();
		param.put("storeFlag", storeFlag);
		param.put("payType", Constants.PAYTYPE_LINEPAY_VB);
		String hql = "FROM LvPaymentStyle WHERE storeFlag=:storeFlag AND payType=:payType AND isActivity=1";
		return lvlogicReadDao.find(hql, param);
	}

	@Override
	public List<LvVbPlans> getLvVbPlansList(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String mallFlag = dto.getAsString("mallFlag");
		Map param=new HashMap();
		param.put("mallFlag", mallFlag);
		return lvlogicReadDao.find("from LvVbPlans where mallFlag=:mallFlag order by porder asc", param);
	}

	@Override
	public void updatePayMethod(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		LvRecharge recharge=(LvRecharge)dto.get("model");
		Map param=new HashMap();
		param.put("rtype", recharge.getRtype());
		param.put("rnum", recharge.getRnum());
		lvlogicWriteDao.update("update LvRecharge set rtype=:rtype where rnum=:rnum", param);
	}

	
//	public WSRechargeableService getChargecardWs() {
//		return chargecardWs;
//	}
//
//	public void setChargecardWs(WSRechargeableService chargecardWs) {
//		this.chargecardWs = chargecardWs;
//	}

//	public WSStbResumeRecordService getResumeRecordWs() {
//		return resumeRecordWs;
//	}
//
//	public void setResumeRecordWs(WSStbResumeRecordService resumeRecordWs) {
//		this.resumeRecordWs = resumeRecordWs;
//	}

//	public WSSynRechargeRecordService getRechargeRecordWs() {
//		return rechargeRecordWs;
//	}
//
//	public void setRechargeRecordWs(WSSynRechargeRecordService rechargeRecordWs) {
//		this.rechargeRecordWs = rechargeRecordWs;
//	}	
//	
}
