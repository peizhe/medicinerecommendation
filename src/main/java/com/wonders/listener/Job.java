package com.wonders.listener;

import java.util.Timer;
import java.util.TimerTask;

import com.wonders.algorithm.itemcf.ItemcfStart;
import com.wonders.algorithm.tagsim.TagSimStart;
import com.wonders.algorithm.usercf.UsercfStart;
import com.wonders.mr.util.Constant;

/**
 * 执行推荐算法任务类
 * @author xh
 *
 */
public class Job {

	private ItemcfStart itemcfStart = new ItemcfStart();
	private TagSimStart tagSimStart = new TagSimStart();
	private UsercfStart usercfStart = new UsercfStart();
	
	/**
	 * 运行任务
	 */
	public void excute() {
		Timer timer = new Timer();
		//定时器，一天执行一次
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				itemcfStart.doComputer();
				tagSimStart.doComputer();
				usercfStart.doComputer();
			}
		},Constant.JOB_EXCUTE_PERIOD, Constant.JOB_EXCUTE_PERIOD);
	}
}
