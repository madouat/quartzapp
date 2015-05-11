package bf.quartzapp;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import bf.associateconsultant.core.XRepository;
import bf.quartzapp.subtask.TaskRunner;
import bf.quartzapp.subtask.TaskRunnerFactory;
import bf.quartzapp.subtask.TaskRunnerFactory.TaskType;

public class ScheduledTask {

	final static Logger logger = Logger.getLogger(ScheduledTask.class);

	private DatabaseInfo databaseInfos;

	public DatabaseInfo getDatabaseInfos() {
		return databaseInfos;
	}

	public void setDatabaseInfos(DatabaseInfo databaseInfos) {
		this.databaseInfos = databaseInfos;
	}

	private String repositoryUser;

	private String repositoryPassword;

	public String getRepositoryUser() {
		return repositoryUser;
	}

	public void setRepositoryUser(String repositoryUser) {
		this.repositoryUser = repositoryUser;
	}

	public String getRepositoryPassword() {
		return repositoryPassword;
	}

	public void setRepositoryPassword(String repositoryPassword) {
		this.repositoryPassword = repositoryPassword;
	}

	public void runScheduledJob(boolean isRepositoryEnable,
			String transformationorJobDirectory,
			String transformationorJobName, String format) {

		XRepository xrepository = initializeRepositoryIfEnabled(isRepositoryEnable);

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("ID_PARAM", "2");
		TaskRunner tskRunner = TaskRunnerFactory.build(TaskType.JOB,
				xrepository, transformationorJobName,
				transformationorJobDirectory, parameters, format);

		try {
			String runTask = tskRunner.runTask();
			logger.info(runTask);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		/*
		 * String typeExecution = "b";
		 * 
		 * if (typeExecution.equalsIgnoreCase("a")) { tskRunner = new
		 * TransformationRunner(repository, "trans_test2.ktr", transpath, null,
		 * "xml"); } else { Map<String, String> parameters = new HashMap<String,
		 * String>(); parameters.put("ID_PARAM", "2"); tskRunner = new
		 * JobRunner(repository, "myjob_parameters", "/param", parameters,
		 * "xml"); }
		 */

	}

	private XRepository initializeRepositoryIfEnabled(boolean isRepositoryEnable) {
		XRepository repository = null;
		if (isRepositoryEnable) {

			repository = new XRepository()
					.withDbHost(databaseInfos.getDbHost())
					.withDbName(databaseInfos.getDbName())
					.withDbType(databaseInfos.getDbType())
					.withDbPort(databaseInfos.getDbPort())
					.withDbUsername(databaseInfos.getDbUser())
					.withDbPassword(
							new String(Base64.decodeBase64(databaseInfos
									.getDbPassword())))
					.withRepositoryuser(repositoryUser)
					.withRepositoryPassword(
							new String(Base64.decodeBase64(repositoryPassword)));
		}
		return repository;
	}

}