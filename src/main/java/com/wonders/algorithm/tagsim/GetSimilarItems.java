package com.wonders.algorithm.tagsim;

public class GetSimilarItems {
	final static int itemSum = 3952;
	final static int tagSum = 1842;
	final static int K = 10;
	static int[][] tagSimRec;
	public boolean getTopKSimilarItem(double[][] itemSim, int K) {
		tagSimRec = new int[itemSum][K];
		double maxSimilarity;
		int itemID;
		for(int i=0;i<itemSum;++i){
			for(int k=0;k<K;++k){
				itemID = 0;
				maxSimilarity = itemSim[i][itemID];
				for(int j=1;j<itemSum;++j){
					if(itemSim[i][j] > maxSimilarity){
						maxSimilarity = itemSim[i][j];
						itemID = j;
					}
				}
				tagSimRec[i][k] = itemID;
				itemSim[i][itemID] = 0; 
			}
		}
		return true;
	}
}
