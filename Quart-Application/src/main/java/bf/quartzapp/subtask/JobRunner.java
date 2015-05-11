package bf.quartzapp.subtask;

import java.util.Map;

import bf.associateconsultant.core.XJob;
import bf.associateconsultant.core.XRepository;

public class JobRunner implements TaskRunner {

	public JobRunner(XRepository repository, String jobName, String jobPath,
			Map<String, String> parameters, String outputFormat) {
		super();
		this.jobName = jobName;
		this.jobPath = jobPath;
		this.repository = repository;
		this.outputFormat = outputFormat;
		this.parameters = parameters;
	}

	private XJob job = new XJob();
	private String jobName;
	private String jobPath;
	private XRepository repository;
	private String outputFormat;
	Map<String, String> parameters;

	@Override
	public String runTask() {
		job.setOutputFormat(outputFormat);
		String response = job.execute(repository, jobPath, jobName, parameters);
		return response;
	}

	public XJob getJob() {
		return job;
	}

	public void setJob(XJob job) {
		this.job = job;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobPath() {
		return jobPath;
	}

	public void setJobPath(String jobPath) {
		this.jobPath = jobPath;
	}

	public XRepository getRepository() {
		return repository;
	}

	public void setRepository(XRepository repository) {
		this.repository = repository;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

}
