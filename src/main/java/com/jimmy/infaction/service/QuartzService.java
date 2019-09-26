package com.jimmy.infaction.service;

/**
 * @author jimmy on 2019/9/26 8:57
 */
public interface QuartzService {
	/**
	 * @Author jimmy on 9:02 2019/9/26
	 * @Description //TODO
	 * @Param [jobName, jobGroupName, triggerName, triggerGroupName, cls, cron]
	 * @return void
	 **/
	public void addJob(String jobName, String jobGroupName,String triggerName, String triggerGroupName, Class cls, String cron);
	/**
	 * @Author jimmy on 9:03 2019/9/26
	 * @Description //TODO
	 * @Param [oldjobName, oldjobGroup, oldtriggerName, oldtriggerGroup, jobName, jobGroup, triggerName, triggerGroup, cron]
	 * @return boolean
	 **/
	public boolean modifyJobTime(String oldjobName,String oldjobGroup, String oldtriggerName, String oldtriggerGroup, String jobName, String jobGroup,String triggerName, String triggerGroup, String cron);
	/**
	 * @Author jimmy on 9:06 2019/9/26
	 * @Description //TODO
	 * @Param [triggerName, triggerGroupName, cron]
	 * @return void
	 **/
	public void modifyJobTime(String triggerName,
	                          String triggerGroupName, String cron);

	public void pauseJob(String jobName,String jobGroupName);

	public void resumeJob(String jobName,String jobGroupName);
	/**
	 * @Author jimmy on 9:06 2019/9/26
	 * @Description //TODO
	 * @Param [jobName, jobGroupName, triggerName, triggerGroupName]
	 * @return void
	 **/
	public void removeJob(String jobName, String jobGroupName,
	                      String triggerName, String triggerGroupName);

	public void startSchedule();

	public void shutdownSchedule();

}
