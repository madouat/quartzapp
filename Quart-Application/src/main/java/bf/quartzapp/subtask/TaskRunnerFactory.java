package bf.quartzapp.subtask;

import java.util.Map;

import bf.associateconsultant.core.XRepository;

public class TaskRunnerFactory {

	public enum TaskType {

		TRANSFORMATION, JOB
	}

	public static TaskRunner build(TaskType taskType, XRepository repository,
			String taskName, String taskDirectory,
			Map<String, String> parameters, String outputFormat) {
		TaskRunner tskRunner = null;
		if (taskType.equals(TaskType.TRANSFORMATION)) {
			tskRunner = new TransformationRunner(repository, taskName,
					taskDirectory, parameters, outputFormat);
		} else {
			tskRunner = new JobRunner(repository, taskName, taskDirectory,
					parameters, outputFormat);
		}
		return tskRunner;
	}
}
