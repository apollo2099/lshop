package com.lshop.web.group.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvCarriageSet;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvGroupProperty;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductPackage;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.OrderHelp;
import com.lshop.web.group.service.GroupService;
import com.lshop.web.onlineMall.service.OnlineMallService;
import com.lshop.web.product.service.ProductService;
import com.lshop.web.shopCart.service.ShopCartService;
import com.lshop.web.store.service.StoreService;
import com.lshop.web.userCenter.service.UserCenterService;

/**
 * 团购模块
 * @author zhengxue
 * @version 2.0 2012-08-08
 *
 */
@Service("GroupService")
public class GroupServiceImpl implements GroupService {
	
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	@Resource
	private OnlineMallService onlineMallServie;
	
	@Resource
	private UserCenterService userCenterService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ShopCartService shopCartService;
	
	@Resource
	private StoreService storeService;
	

	/**
	 * 获取团购信息lv_group_buy
	 * @param dto groupCode
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvGroupBuy getGroupByCode(Dto dto) throws ServiceException {
		String groupCode=dto.getAsString("groupCode");
		LvGroupBuy groupBuy=(LvGroupBuy)lvlogicReadDao.findUniqueByProperty(LvGroupBuy.class, "code", groupCode);
		return groupBuy;
	}

	/**
	 * 获取团购属性信息lv_group_property
	 * @param dto groupCode
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvGroupProperty> getGroupPropertyList(Dto dto)
			throws ServiceException {
		String groupCode=dto.getAsString("groupCode");
		String storeFlag=dto.getAsString("storeFlag");
		List<LvGroupProperty> propertyList=(List<LvGroupProperty>)lvlogicReadDao.find("from LvGroupProperty where groupCode='"+groupCode+"' and storeId='"+storeFlag+"' order by sortId asc", null);
		return propertyList;
	}

	/**
	 * 获取团购信息
	 * 根据code获取
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvGroupBuy getGroupBuyByCode(Dto dto) throws ServiceException {
		
		String code=dto.getAsString("code");
		LvGroupBuy groupBuy=(LvGroupBuy)lvlogicReadDao.findUniqueByProperty(LvGroupBuy.class, "code", code);
		return groupBuy;
	}

	/**
	 * 保存订单信息——团购中
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Dto saveOrderForGroup(Dto dto) throws ServiceException {
		
		//获取参数
		Integer shopNum=dto.getAsInteger("shopNum"); //购买数量
		LvGroupBuy lvGroupBuy=(LvGroupBuy)dto.get("lvGroupBuy"); //团购
		LvOrder order=(LvOrder)dto.get("lvOrder"); //订单信息
		Integer bestDeliveryValue=dto.getAsInteger("bestDeliveryValue"); //最佳收货时间
		
		//获取收货地址信息
		LvAccountAddress address=userCenterService.getAddressByCode(dto); 
		
		//获取当前店铺信息
		dto.put("storeFlag", lvGroupBuy.getStoreId());
		LvStore store = storeService.getStoreByFlag(dto);
		
		//获取当前店铺币种
		String currency = "USD";
		if(null!=store.getCurrency()){
			currency = store.getCurrency();
		}
		
		//获取运费信息，由地址的区域编号确定
		Float carriage=0f;
		if(address!=null){
			carriage = shopCartService.getDeliverCarrigage(lvGroupBuy.getStoreId(), address.getContryId());
			//当店铺币种和支付币种不一致时，则需转换成支付所需要的币种。当支付方式为3，13，18时显示人民币，其余均显示美元
			if(currency.equalsIgnoreCase("RMB")||currency.equalsIgnoreCase("CNY")){
				if(null!=order.getPaymethod()){
					if(!(order.getPaymethod()==3 || order.getPaymethod()==13 || order.getPaymethod()==17 || order.getPaymethod()==18)){ //支付方式不是显示人民币，则需要将人民币转换成美元
						float ca = carriage*Constants.rateNumCNY; //将RMB转换成美元
						carriage=(float)(Math.round(ca*100))/100; //保证两位小数点，四舍五入
					}
				}
			}else if(currency.equalsIgnoreCase("USD")){
				if(null!=order.getPaymethod()){
					if(order.getPaymethod()==3 || order.getPaymethod()==13 || order.getPaymethod()==17 || order.getPaymethod()==18){ //支付方式显示人民币，则需要将美元转换成RMB
						float ca=carriage*(Constants.rateNum); //将美元转换成RMB
						carriage=(float)(Math.round(ca*100))/100; //保证两位小数点，四舍五入
					}
				}
			}
		}
		
		//获取团购信息
		dto.put("code", lvGroupBuy.getCode());
		LvGroupBuy groupBuy=this.getGroupBuyByCode(dto);
		
		//获取产品信息
		dto.put("pcode", groupBuy.getProductCode());
		LvProduct product=productService.getProductByCode(dto);
		
		//保存订单信息LvOrder
		LvAccount account=userCenterService.getAccount(dto); //获取登陆用户信息
		
		//订单编号规则变更：T+店铺id（2位）+时间（14位）+随机码（5位）
    	Integer id=Constants.STORE_FLAG_TO_IDS.get(lvGroupBuy.getStoreId());
    	String mark=null;
    	if(ObjectUtils.isNotEmpty(id)){
    		if(id<10){
        		mark="T"+"0"+id.toString();
        	}else{
        		mark="T"+id.toString();
        	}
    	}else{
    		mark="T";
    	}
		order.setOid(OrderHelp.createOrderId(mark));
		order.setPayStatus(Short.parseShort("0"));
		order.setStatus(Short.parseShort("0"));
		order.setMemid(account.getId());
		order.setUserEmail(account.getEmail());
		order.setIsGroup(1);
		order.setGroupCode(lvGroupBuy.getCode());
		order.setIsServiceAudit(Short.parseShort("0"));
		order.setIsFinanceAudit(0);
		order.setCurrency("USD");
		//如果是支付宝，则显示人民币
		if(null!=order.getPaymethod() && (order.getPaymethod()==3||order.getPaymethod()==13||order.getPaymethod()==17||order.getPaymethod()==18)){
			order.setCurrency("CNY");
		}
		order.setIsdelete(Short.parseShort("0"));
		order.setFlag(0);
		order.setCode(CodeUtils.getCode());
		order.setCreateTime(new Date());
		order.setStoreId(lvGroupBuy.getStoreId());
		order.setCouponNum(0);
		order.setPostprice(carriage);
		
		float price=groupBuy.getPresentPrice();
		//当店铺币种和支付币种不一致时，则需转换成支付所需要的币种
		if(currency.equalsIgnoreCase("RMB")||currency.equalsIgnoreCase("CNY")){
			if(null!=order.getPaymethod()){
				if(!(order.getPaymethod()==3 || order.getPaymethod()==13 || order.getPaymethod()==17 || order.getPaymethod()==18)){ //支付方式不是显示人民币，则需要将人民币转换成美元
					price=(float)Math.round(price*Constants.rateNumCNY*100)/100; //将RMB转换成美元
				}
			}
		}else if(currency.equalsIgnoreCase("USD")){
			if(null!=order.getPaymethod()){
				if(order.getPaymethod()==3 || order.getPaymethod()==13 || order.getPaymethod()==17 || order.getPaymethod()==18){ //支付方式显示人民币，则需要将美元转换成RMB
					price=(float)Math.round(price*Constants.rateNum*100)/100; //将美元转换成RMB
				}
			}
		}
		order.setTotalPrice(price*shopNum+carriage);
		Integer orderId=(Integer)lvlogicWriteDao.save(order);
		
		//保存订单地址表LvOrderAddress
		LvOrderAddress orderAddress=new LvOrderAddress();
		orderAddress.setOrderId(order.getOid());
		orderAddress.setRelCode(address.getRelCode());
		orderAddress.setRelName(address.getRelName());
		orderAddress.setPostCode(address.getPostCode());
		orderAddress.setMobile(address.getMobile());
		orderAddress.setTel(address.getTel());
		orderAddress.setContryId(address.getContryId());
		orderAddress.setContryName(address.getContryName());
		orderAddress.setProvinceId(address.getProvinceId());
		orderAddress.setProvinceName(address.getProvinceName());
		orderAddress.setCityId(address.getCityId());
		orderAddress.setCityName(address.getCityName());
		orderAddress.setAdress(address.getAdress());
		orderAddress.setCode(CodeUtils.getCode());
		orderAddress.setCreateTime(new Date());
		orderAddress.setStoreId(lvGroupBuy.getStoreId());
		orderAddress.setBestDeliveryTime(bestDeliveryValue);
		Integer orderAddressId=(Integer)lvlogicWriteDao.save(orderAddress);
		
		//保存订单详情表LvOrderDetails
		LvOrderDetails orderDetail=new LvOrderDetails();
		orderDetail.setOrderId(order.getOid());
		orderDetail.setProductCode(groupBuy.getProductCode());
		float oprice = groupBuy.getPresentPrice();
		//当店铺币种和支付币种不一致时，则需转换成支付所需要的币种
		if(currency.equalsIgnoreCase("RMB")||currency.equalsIgnoreCase("CNY")){
			if(null!=order.getPaymethod()){
				if(!(order.getPaymethod()==3 || order.getPaymethod()==13 || order.getPaymethod()==17 || order.getPaymethod()==18)){ //支付方式不是显示人民币，则需要将人民币转换成美元
					oprice=(float)Math.round(oprice*Constants.rateNumCNY*100)/100; //将RMB转换成美元
				}
			}
		}else if(currency.equalsIgnoreCase("USD")){
			if(null!=order.getPaymethod()){
				if(order.getPaymethod()==3 || order.getPaymethod()==13 || order.getPaymethod()==17 || order.getPaymethod()==18){ //支付方式显示人民币，则需要将美元转换成RMB
					oprice=(float)Math.round(oprice*Constants.rateNum*100)/100; //将美元转换成RMB
				}
			}
		}
		orderDetail.setOprice(oprice);
		orderDetail.setOremark(order.getOrderRemark());
		orderDetail.setPnum(shopNum);
		orderDetail.setPostPrice(order.getPostprice());
		orderDetail.setCurrency(order.getCurrency());
		orderDetail.setIsDelete(0);
		orderDetail.setIsPackage(product.getIsSetMeal());
		orderDetail.setPcode(product.getPcode());
		orderDetail.setCode(CodeUtils.getCode());
		orderDetail.setCreateTime(new Date());
		orderDetail.setStoreId(lvGroupBuy.getStoreId());
		lvlogicWriteDao.save(orderDetail);
		
		LvOrder myOrder=(LvOrder)lvlogicReadDao.load(LvOrder.class, orderId);
		LvOrderAddress myOrderAddress=(LvOrderAddress)lvlogicReadDao.load(LvOrderAddress.class, orderAddressId);
		dto.clear();
		dto.put("lvOrder", myOrder);
		dto.put("lvOrderAdress", myOrderAddress);
		return dto;
	}

	/**
	 * 检验该用户之前是否团购过此产品
	 * 需传递参数uid,groupCode
	 * @param dto
	 * @return
	 * @throws ServiceExceptionn
	 */
	@SuppressWarnings("unchecked")
	@Override
	public LvOrder checkGroupOrder(Dto dto) throws ServiceException {
		//获取参数
		String groupCode=dto.getAsString("groupCode");
		Integer uid=dto.getAsInteger("uid");
		String storeFlag=dto.getAsString("storeFlag");
		
		String hql=" from LvOrder where memid=:memid and groupCode=:groupCode and storeId=:storeId and status!=3 and isdelete=0";
		Map map=new HashMap();
		map.put("memid", uid);
		map.put("groupCode", groupCode);
		map.put("storeId", storeFlag);
		List<LvOrder> orderList=(List<LvOrder>)lvlogicReadDao.find(hql, map);
		
		LvOrder order=null;
		if(null!=orderList && orderList.size()>0){
			order=orderList.get(0);
		}

		return order;
	}

	@Override
	public ResultMsg finishOrderGroup(LvOrder order) throws Exception {
		
		LvGroupBuy groupBuy=(LvGroupBuy)lvlogicReadDao.findUniqueByProperty(LvGroupBuy.class,"code", order.getGroupCode());
		if(null!=groupBuy){
			String hql="update LvGroupBuy set purchasedNum=:purchasedNum where code=:code";
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("purchasedNum", groupBuy.getPurchasedNum()+1); //统计已购买人数，一个用户只能下一个订单，但订单中的产品数不限制
			param.put("code", groupBuy.getCode());
			lvlogicWriteDao.update(hql, param);
		}
		return null;
	}
	
}
