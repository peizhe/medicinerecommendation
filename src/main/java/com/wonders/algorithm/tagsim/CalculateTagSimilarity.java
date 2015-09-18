package com.wonders.algorithm.tagsim;

public class CalculateTagSimilarity {
	
	final static int itemSum = 3952;
	final static int tagSum = 1842;
	static double[][] itemSimilarity;
	public boolean calculateSim(int[][] tagItemMatrix) {
		itemSimilarity = new double[itemSum][itemSum];
		for(int i=0;i<itemSum;++i){
			for(int j=0;j<i;++j){
				int iCount = 0;
				int jCount = 0;
				int bothCount = 0;
				double similarity = 0;
				for(int k=0;k<tagSum;++k){
					if(tagItemMatrix[i][k]==1&&tagItemMatrix[j][k]==1){
						bothCount += 1;
					}
					if(tagItemMatrix[i][k]==1){
						iCount += 1;
					}
					if(tagItemMatrix[j][k]==1){
						jCount += 1;
					}
				}
				if(bothCount!=0&&iCount!=0&&jCount!=0){
					similarity = bothCount/(Math.sqrt(1.0*iCount*jCount)); //相似度计算
					itemSimilarity[i][j] = similarity;
					itemSimilarity[j][i] = similarity;
				}
				else{
					itemSimilarity[i][j] = 0;
					itemSimilarity[i][j] = 0;
				}
			}
			itemSimilarity[i][i] = 0; //自己与自己相似度不做考虑
		}
		return true;
	}
}
