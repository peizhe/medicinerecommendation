package com.wonders.algorithm.usercf;
import java.sql.Connection;  //导入数据库连接对象
import java.sql.DriverManager; //导入数据库驱动管理对象
import java.sql.ResultSet;    //导入数据记录集对象
import java.sql.SQLException; //导入数据SQL操作异常对象
import java.sql.Statement;   //导入SQL操作接口对象

import com.wonders.mr.util.Constant;

public class GetData {

	final static int userSum = 6040;
	final static int itemSum = 3952;
	String urlString;       //连接数据库的字符串
	String sqlString;       //执行数据SQL查询操作的字符串
	Connection connection;  //数据库连接变量
	Statement statement;    //数据库操作对象
	ResultSet resultSet;    //数据记录集对象
	static int[][] train; 
	
	
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getActionData() {
		int row;
		int line;
		train = new int[userSum][itemSum];
		urlString = Constant.JDBC_URL;
		try{
			connection = DriverManager.getConnection(urlString, Constant.USER_NAME, Constant.PASSWORD);
			statement = connection.createStatement();
			sqlString = "select * from action";
			resultSet = statement.executeQuery(sqlString);
			while(resultSet.next()){
				row = Integer.parseInt(resultSet.getString(2));
				line = Integer.parseInt(resultSet.getString(3));
				train[row-1][line-1] = 1;                 //数据集中的ID从1开始编号，代码中从0开始编号
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
		getData.getActionData();
		int count = 0;
		for(int i=0;i<userSum;++i){
			for(int j=0;j<itemSum;++j){
				if(train[i][j]==1){
					count += 1;
				}
			}
		}
		System.out.println("训练集中总共有记录条数："+count);
	}
*/
}
