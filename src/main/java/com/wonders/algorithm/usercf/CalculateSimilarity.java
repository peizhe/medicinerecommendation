package com.wonders.algorithm.usercf;

public class CalculateSimilarity {

	/*
	 * 计算改进的用户余弦相似度
	 * 找到不同用户的针对物品的行为
	 * 然后从他们的行为中找出一样的物品
	 * 在利用修正余弦相似度公式计算
	 * */
	final static int userSum = 6040;
	final static int itemSum = 3952;
	static double[][] simMatrix;
	public boolean userSimilarity(int[][]train) {
		simMatrix = new double[userSum][userSum];   //相似度矩阵，存放相似度计算结果
		for(int i=0;i<userSum;++i){
			for(int j=0;j<i;++j){               //相似度矩阵是对称矩阵
				int iCount = 0;
				int jCount = 0;
				int bothCount = 0;
				double similarity = 0;
				for(int k=0;k<itemSum;++k){
					if(train[i][k]==1&&train[j][k]==1){
						bothCount += 1;
					}
					if(train[i][k]==1){
						iCount += 1;
					}
					if(train[j][k]==1){
						jCount += 1;
					}
				}
				if(bothCount!=0&&iCount!=0&&jCount!=0){
					similarity = bothCount/(Math.sqrt(1.0*iCount*jCount)*Math.log(1+userSum)); //相似度计算，小于1，方便后面计算
					simMatrix[i][j] = similarity;
					simMatrix[j][i] = similarity;
				}
				else{
					simMatrix[i][j] = 0;
					simMatrix[i][j] = 0;
				}
			}
			simMatrix[i][i] = 0;                //自己与自己相似度不做考虑
		}
		return true;
	}
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
*/
}
