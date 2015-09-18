package com.wonders.algorithm.itemcf;

import com.wonders.algorithm.itemcf.CalculateRecommendation;
import com.wonders.algorithm.itemcf.CalculateSimilarity;
import com.wonders.algorithm.itemcf.GetData;

/**
 * 药品相似计算
 * @author xh
 *
 */
public class ItemcfStart {

	public void doComputer() {
		int K = 160;
		int N = 10;
		GetData getData = new GetData();
		getData.getActionData();           //从action表中读取数据集
		System.out.println("读取数据完毕：1000209条数据");
		
		CalculateSimilarity calculateSimilarity = new CalculateSimilarity();
		calculateSimilarity.itemSimilarity(GetData.train);      //计算物品相似度
		System.out.println("计算物品相似度完毕");
/*		
		for(int i=0;i<10;++i){
			for(int j=0;j<10;++j){
				System.out.println(i+","+j+","+CalculateSimilarity.simMatrix[i][j]);
			}
		}
*/		
		CalculateRecommendation calculateRecommendation = new CalculateRecommendation();
		calculateRecommendation.getTopKSimilarItem(CalculateSimilarity.simMatrix, K);  //取出TopK相似物品ID和相似值
		System.out.println("*************取出TopK相似物品ID和相似值,整数部分为ID，小数部分为相似值*************************");
		for(int i=0;i<10;++i){
			for(int j=0;j<10;++j){
				System.out.println(i+","+j+","+CalculateRecommendation.topKSimilarItem[i][j]);
			}
		}
		
		calculateRecommendation.getInterestMatrix(GetData.train, K);   //计算用户对未行为过物品感兴趣
		System.out.println("*************计算用户对未行为过物品感兴趣*************************");
		for(int i=0;i<10;++i){
			for(int j=0;j<10;++j){
				System.out.println(i+","+j+","+CalculateRecommendation.interestMatrix[i][j]);
			}
		}
		
		calculateRecommendation.getTopNRecommendation(N);          //计算推荐列表
		System.out.println("*************计算推荐列表*************************");
		for(int i=0;i<10;++i){
			for(int j=0;j<10;++j){
				System.out.println(i+","+j+","+CalculateRecommendation.topNRecommendation[i][j]);
			}
		}
		System.out.println("*************存储结果*************************");		
		PutData putData = new PutData();
		putData.putItemSimData();
		putData.putRecData();
		System.out.println("*************计算结束*************************");
	}
	
}