package bf.quartzapp.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import bf.quartzapp.ScheduledTask;

public class ScheduledJobRunner extends QuartzJobBean {

	final static Logger logger = Logger.getLogger(ScheduledJobRunner.class);
	private static final String TRANSFORMATIONOR_JOB_DESCRIPTION = "transformationorJobDescription";
	private static final String OUTPUT_FORMAT = "outputFormat";
	private static final String IS_REPOSITORY_ENABLE = "isRepositoryEnable";
	private static final String TRANSFORMATIONOR_JOB_DIRECTORY = "transformationorJobDirectory";
	private static final String TRANSFORMATIONOR_JOB_NAME = "transformationorJobName";
	private static final String IS_RUNNING_KEY = "isRunning";
	private ScheduledTask scheduledTask;

	public void setScheduledTask(ScheduledTask scheduledTask) {
		this.scheduledTask = scheduledTask;
	}

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		JobDataMap datamap = context.getJobDetail().getJobDataMap();
		boolean isRunning = datamap.getBoolean(IS_RUNNING_KEY);
		if (!isRunning) {

			String transformationorJobDirectory = datamap
					.getString(TRANSFORMATIONOR_JOB_DIRECTORY);
			String transformationorJobName = datamap
					.getString(TRANSFORMATIONOR_JOB_NAME);
			boolean isRepositoryEnable = datamap
					.getBoolean(IS_REPOSITORY_ENABLE);
			String format = datamap.getString(OUTPUT_FORMAT);
			String transformationorJobDescription = datamap
					.getString(TRANSFORMATIONOR_JOB_DESCRIPTION);
			context.getJobDetail().getJobDataMap().put(IS_RUNNING_KEY, true);
			try {
				context.getScheduler().addJob(context.getJobDetail(), true);
				String logString = String
						.format("Start running Task: Name[%s] Description[%s]",
								transformationorJobName,
								transformationorJobDescription);
				// System.out.println(logString);
				logger.info(logString);
				scheduledTask.runScheduledJob(isRepositoryEnable,
						transformationorJobDirectory, transformationorJobName,
						format);
				isRunning = false;
				context.getJobDetail().getJobDataMap()
						.put(IS_RUNNING_KEY, isRunning);
				context.getScheduler().addJob(context.getJobDetail(), true);
			} catch (SchedulerException e) {
				logger.error(e.getMessage(), e);
				// e.printStackTrace();
			}

		} else {
			logger.info(datamap.getString(TRANSFORMATIONOR_JOB_NAME)
					+ " Already running");
		}

	}
}