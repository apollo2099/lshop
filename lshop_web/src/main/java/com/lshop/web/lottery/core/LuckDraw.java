package com.lshop.web.lottery.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 保证单例，最好在交给spring管理，容器启动的时候初始化
 * @author jiangch
 *
 */
public class LuckDraw {
	
	//中奖概率，map的key为几等奖，value为该奖项中奖的概率，例如一等奖的中奖概率为5%，key=1，value=5；注意，map中value之和等于100
	private Map<Integer,Integer> praiseProbabilityMap;
	
	//中奖数目，map的key为几等奖，value为该奖项数目
	private Map<Integer,Integer> praiseNumberMap;
	
	//flag为true表示按中奖数目及概率混合抽奖，flag为false表示单纯按概率抽奖，默认为true
	private boolean flag = true;
	
	//概率节点数组
	private int[] praisePoint;
	
	/**
	 * 抽奖类的构造方法
	 * @param praiseProbabilityMap 中奖概率，map的key为几等奖，value为该奖项中奖的概率,最多两位小数，例如一等奖的中奖概率为5%，key=1，value=5；注意，map中value之和等于100
	 * @param flag flag为true表示按中奖数目及概率混合抽奖，flag为false表示单纯按概率抽奖，默认为true
	 * @param praiseNumberMap 中奖数目，map的key为几等奖，value为该奖项数目,如果使用单纯的概率抽奖方式，该参数传null
	 */
	public LuckDraw(Map<Integer,Float> praiseProbabilityMap,Boolean flag,Map<Integer,Integer> praiseNumberMap){
		if(praiseProbabilityMap == null){
			throw new RuntimeException("praiseProbabilityMap参数为null，初始化失败");
		}
		Set<Integer> keySet = praiseProbabilityMap.keySet();
		this.praiseProbabilityMap = new HashMap<Integer,Integer>(keySet.size());
		for(Integer key : keySet){
			this.praiseProbabilityMap.put(key, (int)(praiseProbabilityMap.get(key)*100));
		}
		//this.praiseProbabilityMap = praiseProbabilityMap;
		this.praiseNumberMap = praiseNumberMap;
		if(flag != null){
			this.flag = flag;
		}
		
		praisePoint = new int[this.praiseProbabilityMap.size()];
		//初始化概率节点数组
		for (Integer key : this.praiseProbabilityMap.keySet()) {
			if (key > 1) {
				praisePoint[key - 1] = praisePoint[key - 2]
						+ this.praiseProbabilityMap.get(key);
			} else {
				praisePoint[key - 1] = this.praiseProbabilityMap.get(key);
			}
		}
	}
	

	
	/**
	 * 抽奖,返回的是几等奖
	 * @param draw
	 * @return
	 */
	public int accessAward() {
		//10000以内的随机数
		Random ran = new Random();
		int total = 10000;
		int award = awards(praisePoint, ran.nextInt(total));
		//如果是基于概率+奖项数目的混合型抽奖方式
		if(flag && praiseNumberMap != null){
			Integer praizeNumber = praiseNumberMap.get(award);
			if(praizeNumber != null && praizeNumber > 0){
				//该奖项数目减一
				this.praiseNumberMap.put(award, --praizeNumber);
				//如果该奖项数目为0，即该奖项已抽完,则需要重新初始化概率节点数组，使得该奖项抽奖概率为0，在以后的抽奖过程中就不会抽出该奖项
				if(praizeNumber < 1){
					initPraisePoint(award);
				}
			}
		}
		
		return award;
	}
	
	/**
	 * 通过概率节点数组数组和随机数决定该随机数落在几等奖内
	 * @param arr
	 * @param randomNumber
	 * @return
	 */
	private int awards(int[] arr, int randomNumber) {
		int i = 0;
		for (; i < arr.length; i++) {
			if (randomNumber < arr[i]) {
				break;
			}
		}
		return i + 1;
	}

	
	/**
	 * 初始化概率节点数组，单某一项奖项的中奖数目减为0，就需要重新初始化praisePoint
	 * 把中奖项目减为0的那个奖项概率加到“谢谢参与”奖项（默认最后一项）
	 * @param praise
	 */
	private void initPraisePoint(int praise){
		if(praiseProbabilityMap != null ){
			//获取奖项数减为0奖项 的中奖概率
			Integer probability = praiseProbabilityMap.get(praise);
			if(probability != null && probability > 0){
				//把中奖概率变为0
				praiseProbabilityMap.put(praise, 0);
				int size = praiseProbabilityMap.size();
				//把中奖概率加到“谢谢参与”
				praiseProbabilityMap.put(size, praiseProbabilityMap.get(size) + probability);
				
				//初始化概率节点数组
				for (Integer key : praiseProbabilityMap.keySet()) {
					if (key > 1) {
						praisePoint[key - 1] = praisePoint[key - 2]
								+ praiseProbabilityMap.get(key);
					} else {
						praisePoint[key - 1] = praiseProbabilityMap.get(key);
					}
				}

			}
		}
	}
}
