package com.wonders.mr.util;

public class Constant {

	/*******************用户相关标示 begin***********************/
	/**
	 * 标示用户未删除
	 */
	public final static Integer USER_DELETE = 0;
	/**
	 * 标示用户已删除
	 */
	public final static Integer USER_NOT_DELETE = 1;
	/*******************用户相关标示 end***********************/
	/**
	 * 任务执行间隔时间
	 */
	public final static Integer JOB_EXCUTE_PERIOD = 6000 * 60 * 24;
	
	/**
	 * 数据库连接url
	 */
	public final static String JDBC_URL = "jdbc:mysql://10.1.30.2:3306/medicinerecomendation";
	
	/**
	 * 数据库连接用户名
	 */
	public final static String USER_NAME = "root";
	
	/**
	 * 数据库连接密码
	 */
	public final static String PASSWORD = "123456";
	
}
