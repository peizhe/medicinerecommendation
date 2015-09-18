package com.wonders.algorithm.itemcf;

public class CalculateRecommendation {

	/**
	 * @param args
	 */
	final static int userSum = 6040;
	final static int itemSum = 3952;
	static double[][] topKSimilarItem;
	static double[][] interestMatrix;
	static int[][] topNRecommendation;
	//取出TopK个最相似的用户和其相似值
	public boolean getTopKSimilarItem(double[][]simMatrix, int K) {
		double[][] matrixIn = new double[itemSum][itemSum];
		matrixIn = simMatrix;
		topKSimilarItem = new double[itemSum][K];
		int itemID;
		double maxSimilarity;
		for(int i=0;i<itemSum;++i){
			for(int k=0;k<K;++k){
				itemID = 0;
				maxSimilarity = matrixIn[i][itemID];
				for(int j=1;j<itemSum;++j){
					if(matrixIn[i][j]>maxSimilarity){
						maxSimilarity = matrixIn[i][j];
						itemID = j;
					}
				}
				topKSimilarItem[i][k] = itemID+maxSimilarity;
				matrixIn[i][itemID] = 0;
			}
		}
		return true;
	}
	
	
	
	public boolean getInterestMatrix(int[][]train, int K) {
		interestMatrix = new double[userSum][itemSum];
		int simID;
		double simValue;
		for(int i=0;i<userSum;++i){
			for(int j=0;j<itemSum;++j){
				if(train[i][j]==0){
					for(int k=0;k<K;k++){
						simID = (int)topKSimilarItem[j][k];
						simValue = topKSimilarItem[j][k] - (double)simID;
						if(train[i][simID]==1){
							 //基于用户行为相似计算的物品相似度，用户喜欢目标物品的相似物品，那么很可能就喜欢目标物品
							interestMatrix[i][j] += simValue;      
						}
					}
				}
				else {
					interestMatrix[i][j]=0;
				}
			}
		}
		return true;
	}
	
	public boolean getTopNRecommendation(int N) {
		topNRecommendation = new int[userSum][N];
		int recItemID;
		double recValue;
		for(int i=0;i<userSum;++i){
			for(int k=0;k<N;++k){
				recItemID = 0;
				recValue = interestMatrix[i][recItemID];
				for(int j=1;j<itemSum;++j){
					if(interestMatrix[i][j]>recValue){
						recValue = interestMatrix[i][j];
						recItemID = j;
					}
				}
				topNRecommendation[i][k] = recItemID;
				interestMatrix[i][recItemID] = 0;
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
