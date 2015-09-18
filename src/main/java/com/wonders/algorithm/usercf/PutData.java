package com.wonders.algorithm.usercf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.wonders.mr.util.Constant;

public class PutData {
	
	final static int userSum = 6040;
	final static int itemSum = 3952;
	final static int MAX = 10; //每种结果只存放10个结果
	int count;       //统计处理了多少行数据了
	int maxInsert = 300;
	String list;     //存放每MAX个的推荐记录，字符串型
	final static String urlString = Constant.JDBC_URL;
	Connection connection;  //数据库连接变量
//	Statement statement;    //数据库操作对象
//	String sqlString;       //数据库插入语句
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean putUserSimData() {            

		try{
			connection = DriverManager.getConnection(urlString, Constant.USER_NAME, Constant.PASSWORD);
			/*清空表，再插入*/
			Statement statement = connection.createStatement(); 
			statement.execute("delete from rec_user_sim");
			/*******/			
			String sqlString = "insert into rec_user_sim(user_id,user_id_list) values(?,?)";
			PreparedStatement ps = connection.prepareStatement(sqlString);
			count = 0;
			for(int i=0;i<userSum;++i){
				list = "";
				for(int j=0;j<MAX;++j){
					list += Integer.toString((int)CalculateRecommendation.topKSimilarUser[i][j]+1);  //相似ID加1，与现实从1开始编号对应
					if(j<MAX-1){              //最后一个不加逗号
						list += ",";
					}
				}
				ps.setString(1, Integer.toString(i+1));   //编号加1，读数据的时候减过1
				ps.setString(2, list);
				ps.addBatch();
				if(++count % maxInsert == 0){           //实现maxIsert大小的，批量插入
					ps.executeBatch();
				}
			}
			ps.executeBatch();         //插入剩余的数据
			ps.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean putRecData() {            

		try{
			connection = DriverManager.getConnection(urlString, Constant.USER_NAME, Constant.PASSWORD);
			/*清空表，再插入*/
			Statement statement = connection.createStatement(); 
			statement.execute("delete from rec_table_ucf");
			/*******/	
			String sqlString = "insert into rec_table_ucf(user_id,item_id_list) values(?,?)";
			PreparedStatement ps = connection.prepareStatement(sqlString);
			count = 0;
			for(int i=0;i<userSum;++i){
				list = "";
				for(int j=0;j<MAX;++j){
					list += Integer.toString((int)CalculateRecommendation.topNRecommendation[i][j]+1);  //相似ID加1，与现实从1开始编号对应
					if(j<MAX-1){              //最后一个不加逗号
						list += ",";
					}
				}
				ps.setString(1, Integer.toString(i+1));   //编号加1，读数据的时候减过1
				ps.setString(2, list);
				ps.addBatch();
				if(++count % maxInsert == 0){           //实现maxInsert大小的，批量插入
					ps.executeBatch();
				}
			}
			ps.executeBatch();         //插入剩余的数据
			ps.close();
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

	}
*/
}
