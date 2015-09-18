package com.wonders.algorithm.usercf;

public class CalculateRecommendation {

	/**
	 * @param args
	 */
	final static int userSum = 6040;
	final static int itemSum = 3952;
	static double[][] topKSimilarUser;         //存放最相似的K个相似用户ID和相似值 ，由方法getTopKSimilarUser()获得
	static double[][] interestMatrix;          //后续被破坏，存放用户对未有行为物品的感兴趣度 ，由方法getInterestMatrix()获得
	static int[][] topNRecommendation;         //存放TopN个推荐的推荐列表，由方法getTopNRecommendation()获得
	//取出最Top-K个最相似的用户以及相似值，存放到topKSimilarUser[][]中，数组整数部分为用户ID,小数部分为相似值；
	public boolean getTopKSimilarUser(double[][]simMatrix, int K){
		double[][]matrixIn = new double[userSum][userSum];
		matrixIn = simMatrix;             //定义辅助相似度矩阵，避免后面对原始相似度矩阵的更改操作
		topKSimilarUser = new double[userSum][K];
		double maxSimilarity;
		int userID;
		for(int i=0;i<userSum;++i){
			for(int k=0;k<K;++k){
				maxSimilarity = 0;         //初始化第k轮比较的最大相似值
				userID = 0;            //初始化第k轮比较的最大相似用户id
				for(int j=0;j<userSum;j++){
					if(matrixIn[i][j] > maxSimilarity){
						maxSimilarity = matrixIn[i][j];
						userID = j;
					}
				}
				topKSimilarUser[i][k] = userID + maxSimilarity; //将第k相似的用户ID和相似值用整数加小数形式存储
				matrixIn[i][userID] = 0;      //重置相似度，防止对下一轮比较干扰
			}
		}
		return true;
	}
	
	//计算用户u对所有未购买过物品i的感兴趣度，已经购买过的不做考虑，置0；
	//思想：我的相似用户们对你感兴趣，那么我也对你感兴趣，相似度作为决策的权值。
	public boolean getInterestMatrix(int[][]train, int K) {
		interestMatrix = new double[userSum][itemSum];
		int simID;
		double simValue;
		for(int i=0;i<userSum;++i){
			for(int j=0;j<itemSum;++j){
				if(train[i][j]==1){           //只考虑用户没有过行为的物品
					interestMatrix[i][j] = 0;
				}
				else{
					for(int k=0;k<K;++k){
						simID = (int)topKSimilarUser[i][k];
						simValue = topKSimilarUser[i][k] - (double)simID;
						if(train[simID][j]==1){                  //如果该相似用户对物品j感兴趣
							interestMatrix[i][j] += simValue;     //以相似度作为感兴趣权值
						}
					}
				}
			}
		}
		return true;
	}
	
	//根据感兴趣矩阵，选出用户最感兴趣的Top-N个物品
	public boolean getTopNRecommendation(int N) {
		topNRecommendation = new int[userSum][N];
		int recItemID;
		double recValue;
		for(int i=0;i<userSum;++i){                   //对于每个用户i
			for(int k=0;k<N;++k){                    //挑选出最感兴趣的N个物品
				recItemID=0;
				recValue=interestMatrix[i][0];
				for(int j=1;j<itemSum;++j){           //比较并选出最大感兴趣度的物品ID
					if(interestMatrix[i][j]>recValue){
						recItemID = j;
						recValue = interestMatrix[i][j];
					}
				}
				topNRecommendation[i][k]=recItemID;   //将选出的推荐物品ID保存到推荐列表
				interestMatrix[i][recItemID] = 0;     //将本轮选出的最相似物品的感兴趣度赋值为0；表示已选出，防止下轮选取的干扰
			}
		}
		return true;
	}
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
*/
}
