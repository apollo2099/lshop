package com.lshop.common.payutil.payDollar.secure;

public class PaydollarSecureFactory {
	public static PaydollarSecure getPaydollarSecure(String secureType) {
		if ("SHA".equals(secureType))
			return new SHAPaydollarSecure();
		else
			return new SHAPaydollarSecure();
	}
}

