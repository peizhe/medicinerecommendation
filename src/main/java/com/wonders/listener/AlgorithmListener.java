package com.wonders.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 推荐算法监听器，当服务启动时，应启动此监听器，以实现该监听器去启动推荐算法
 * @author xh
 *
 */
public class AlgorithmListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		Job job = new Job();
		job.excute();
		System.out.println("---------推荐算法监听器已启动----------");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
