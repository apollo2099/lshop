package com.lshop.manage.lvOrder.vo;

public class OrderImport {
	
	private String orderNumber;        //第三方订单号
	private String orderDate;          //下单时间
	private String orderStatus;        //订单状态
	private String orderPaymentMethod; //支付方式(1：paypal，2：paydollar)
	private String OrderShippingMethod;//运输方式
	private Float orderShip;           //运费
	private Float orderGrandTotal;     //订单总额
	private String customerName;       //客户名称
	private String customerEmail;      //客户Email
	private String shippingName;       //收货人姓名
	private String street;             //街道
	private String zip;                //邮编
	private String city;               //城市
	private String state;              //洲省编码
	private String stateName;          //洲省
	private String country;            //国家编码
	private String countryName;        //国家
	private String phoneNumber;        //联系电话
	private String itemName;           //商品名称
	private String itemSKU;            //商品编码
	private Float itemPrice;           //商品价格
	private Integer itemQty;           //商品数量
	private Float itemTotal;           //商品总价
	private String orderRemark;        //订单备注
	private String orderPayNumber;     //第三方支付单号
	private String orderPayDate;       //支付日期
	private String currency;           //币种
	private Short orderSources;        //订单来源(1=亚马逊，2=淘宝，3=速卖通，4=ebuy，5=Banana TV)
	private String errorCode;          //错误代码
	private String opreate;            //操作者
	
	public OrderImport() {

	}
	
	public OrderImport(String orderNumber, String orderDate,
			String orderStatus, String orderPaymentMethod,
			String orderShippingMethod, Float orderShip,
			Float orderGrandTotal, String customerName, String customerEmail,
			String shippingName, String street, String zip, String city,
			String state, String stateName, String country, String countryName,
			String phoneNumber, String itemName, String itemSKU,
			Float itemPrice, Integer itemQty, Float itemTotal,
			String orderRemark, String orderPayNumber, String currency,
			Short orderSources,String errorCode,String opreate) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.orderPaymentMethod = orderPaymentMethod;
		OrderShippingMethod = orderShippingMethod;
		this.orderShip = orderShip;
		this.orderGrandTotal = orderGrandTotal;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.shippingName = shippingName;
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.state = state;
		this.stateName = stateName;
		this.country = country;
		this.countryName = countryName;
		this.phoneNumber = phoneNumber;
		this.itemName = itemName;
		this.itemSKU = itemSKU;
		this.itemPrice = itemPrice;
		this.itemQty = itemQty;
		this.itemTotal = itemTotal;
		this.orderRemark = orderRemark;
		this.orderPayNumber = orderPayNumber;
		this.currency = currency;
		this.orderSources = orderSources;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderPaymentMethod() {
		return orderPaymentMethod;
	}

	public void setOrderPaymentMethod(String orderPaymentMethod) {
		this.orderPaymentMethod = orderPaymentMethod;
	}

	public String getOrderShippingMethod() {
		return OrderShippingMethod;
	}

	public void setOrderShippingMethod(String orderShippingMethod) {
		OrderShippingMethod = orderShippingMethod;
	}

	public Float getOrderShip() {
		return orderShip;
	}

	public void setOrderShip(Float orderShip) {
		this.orderShip = orderShip;
	}

	public Float getOrderGrandTotal() {
		return orderGrandTotal;
	}

	public void setOrderGrandTotal(Float orderGrandTotal) {
		this.orderGrandTotal = orderGrandTotal;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemSKU() {
		return itemSKU;
	}

	public void setItemSKU(String itemSKU) {
		this.itemSKU = itemSKU;
	}

	public Float getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Float itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

	public Float getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(Float itemTotal) {
		this.itemTotal = itemTotal;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getOrderPayNumber() {
		return orderPayNumber;
	}

	public void setOrderPayNumber(String orderPayNumber) {
		this.orderPayNumber = orderPayNumber;
	}

	public String getOrderPayDate() {
		return orderPayDate;
	}

	public void setOrderPayDate(String orderPayDate) {
		this.orderPayDate = orderPayDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Short getOrderSources() {
		return orderSources;
	}

	public void setOrderSources(Short orderSources) {
		this.orderSources = orderSources;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getOpreate() {
		return opreate;
	}

	public void setOpreate(String opreate) {
		this.opreate = opreate;
	}
	
	
	
}
