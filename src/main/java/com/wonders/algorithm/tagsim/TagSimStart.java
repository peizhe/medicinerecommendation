package com.wonders.algorithm.tagsim;

/**
 * 标签相似
 * @author xh
 *
 */
public class TagSimStart {
	
	static int K = 10; //推荐的相似物品个数
	
	public void doComputer() {
		
		System.out.println("开始读取数据：总共33211条记录");
		GetData getData = new GetData();       //读取数据
		getData.getTagItem();
		
		System.out.println("开始计算相似度");
		CalculateTagSimilarity calculateTagSimilarity = new CalculateTagSimilarity();
		calculateTagSimilarity.calculateSim(GetData.tagItemMatrix);
		for(int i=0;i<10;++i){
			for(int j=0;j<10;++j){
				System.out.println(i+","+j+","+CalculateTagSimilarity.itemSimilarity[i][j]);
			}
		}
		
		System.out.println("开始选取TopK个最相似物品");
		GetSimilarItems getSimilarItems = new GetSimilarItems();
		getSimilarItems.getTopKSimilarItem(CalculateTagSimilarity.itemSimilarity, K);
		for(int i=0;i<10;++i){
			for(int j=0;j<10;++j){
				System.out.println(i+","+j+","+GetSimilarItems.tagSimRec[i][j]);
			}
		}
		
		System.out.println("开始存储结果");
		PutData putData = new PutData();
		putData.putTagItemSim(GetSimilarItems.tagSimRec, K);
		System.out.println("计算完毕");
		
		
	}
	
}
