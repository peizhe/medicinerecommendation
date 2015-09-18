package com.wonders.algorithm.tagsim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.wonders.mr.util.Constant;

public class PutData {
	private static final int itemSum = 3952;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	public boolean putTagItemSim(int[][]tagSimRec, int K) {
		String urlString = Constant.JDBC_URL;
		String sqlString = "insert into rec_item_tag_sim(item_id,item_id_list) values(?,?)";
		int maxInsert = 100;
		try {
			Connection connection = DriverManager.getConnection(urlString, Constant.USER_NAME, Constant.PASSWORD);
			/*清空表，再插入*/
			Statement statement = connection.createStatement(); 
			statement.execute("delete from rec_item_tag_sim");
			/*******/				
			PreparedStatement ps = connection.prepareStatement(sqlString);
			int count = 0;
			String list;
			for(int i=0;i<itemSum;++i){
				list = "";
				for(int j=0;j<K;++j){
					list += Integer.toString(tagSimRec[i][j]+1);
					if(j<K-1){
						list += ",";
					}
				}
				ps.setString(1, Integer.toString(i+1));
				ps.setString(2, list);
				ps.addBatch();
				if(++count % maxInsert == 0){
					ps.executeBatch();
				}
			}
			ps.executeBatch();
			ps.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return true;
	}
}
