package com.jimmy.infaction.job;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 * @author jimmy on 2019/9/26 9:08
 */
public class HelloWorldJob  implements  Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("----hello world---" + new Date());
	}
}
