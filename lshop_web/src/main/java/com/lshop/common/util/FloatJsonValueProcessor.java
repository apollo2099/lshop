package com.lshop.common.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class FloatJsonValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object value, JsonConfig arg1) {
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		return process(value);
	}

	private Object process(Object value) {
		Float val = (Float) value;
		return val.toString();
	}
}
