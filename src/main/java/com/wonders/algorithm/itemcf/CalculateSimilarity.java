package com.wonders.algorithm.itemcf;

public class CalculateSimilarity {

	/**
	 * @param args
	 */
	
	final static int userSum = 6040;
	final static int itemSum = 3952;
	static double[][] simMatrix;
	public boolean itemSimilarity(int[][]train) {
		simMatrix = new double[itemSum][itemSum];
		for(int i=0;i<itemSum;++i){
			for(int j=0;j<i;++j){
				int iCount = 0;
				int jCount = 0;
				int bothCount = 0;
				double similarity = 0;
				for(int k=0;k<userSum;++k){
					if(train[k][i]==1&&train[k][j]==1){
						bothCount += 1;
					}
					if(train[k][i]==1){
						iCount += 1;
					}
					if(train[k][j]==1){
						jCount += 1;
					}
				}
				if(iCount!=0&&jCount!=0&&bothCount!=0){
					similarity = bothCount/(Math.sqrt(1.0*iCount*jCount)*Math.log(1+itemSum));
					simMatrix[i][j] = similarity;
					simMatrix[j][i] = similarity;
				}
				else{
					simMatrix[i][j] = 0;
					simMatrix[j][i] = 0;
				}
			}
			simMatrix[i][i] = 0;
		}
		return true;
	}
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
*/
}
