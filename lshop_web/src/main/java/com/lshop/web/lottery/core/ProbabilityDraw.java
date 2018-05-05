package com.lshop.web.lottery.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gv.core.util.Calc;

/**
 * 概率抽奖算法
 * @author caicl
 *
 */
public class ProbabilityDraw implements LotteryDraw{

	@Override
	public String draw(Map<String, Integer> lotteryItem) {
		int len = lotteryItem.keySet().size();
		List<String> keys = Arrays.asList(lotteryItem.keySet().toArray(new String[0]));
		//计算概率
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += lotteryItem.get(keys.get(i)).intValue();
		}
		Map<Integer, Float> praiseProbabilityMap = new HashMap<Integer, Float>(len);
		int cur;
		double p;
		float fp;
		float fsum = 0f;
		for (int i = 0, slen = len -1; i < slen; i++) {
			cur = lotteryItem.get(keys.get(i)).intValue();
			p = Calc.div(Integer.toString(cur*100), Integer.toString(sum), 2);
			fp = Float.valueOf(Double.toString(p)).floatValue();
			fsum += fp;
			praiseProbabilityMap.put(i+1, fp);
		}
		//最后一个用减法计算,保证概率和为100
		fp = 100f - fsum;
		praiseProbabilityMap.put(len, fp);
		//概率计算
		LuckDraw luckDraw = new LuckDraw(praiseProbabilityMap,false,null);
		int awards = luckDraw.accessAward();
		
		return keys.get(awards -1);
	}

}
