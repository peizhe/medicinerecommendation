package com.wonders.algorithm.tagsim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wonders.mr.util.Constant;

public class GetData {

	final static int tagSum = 1842;
	final static int itemSum = 3952;
	static int[][] tagItemMatrix;
	String urlString;       //连接数据库的字符串
	String sqlString;       //执行数据SQL查询操作的字符串
	Connection connection;  //数据库连接变量
	Statement statement;    //数据库操作对象
	ResultSet resultSet;    //数据记录集对象
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getTagItem() {
		String tagID;
		String itemIDList;
		tagItemMatrix = new int[itemSum][tagSum];
		urlString = Constant.JDBC_URL;
		try{
			connection = DriverManager.getConnection(urlString, Constant.USER_NAME, Constant.PASSWORD);
			statement = connection.createStatement();
			sqlString = "select * from tag_items";
			resultSet = statement.executeQuery(sqlString);
			while(resultSet.next()){
				tagID = resultSet.getString(2);
				itemIDList = resultSet.getString(3);
				String[] arryStrings = itemIDList.split(",");
				int row,line;
				for(int i=0;i<arryStrings.length;++i){
					row = Integer.parseInt(arryStrings[i])-1; 
					line = Integer.parseInt(tagID)-1;
					tagItemMatrix[row][line] = 1;
				}
			}
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
/*		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetData getData = new GetData();
		getData.getTagItem();
		int count = 0;
		for(int i=0;i<itemSum;++i){
			for(int j=0;j<tagSum;++j){
				if(tagItemMatrix[i][j]==1){
					count += 1;
				}
			}
		}
		System.out.println("ѵ�������ܹ��м�¼����"+count);
	}
*/

}
