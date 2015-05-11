package bf.quartzapp.subtask;

import java.util.Map;

import bf.associateconsultant.core.XRepository;
import bf.associateconsultant.core.XTransformation;

public class TransformationRunner implements TaskRunner {

	public TransformationRunner(XRepository repository, String transName,
			String transPath, Map<String, String> parameters,
			String outputFormat) {
		super();
		this.transName = transName;
		this.transPath = transPath;
		this.repository = repository;
		this.outputFormat = outputFormat;
		this.parameters = parameters;
	}

	public TransformationRunner() {

	}

	private XTransformation xTransformation = new XTransformation();
	private String transName;
	private String transPath;
	private XRepository repository;
	private String outputFormat;
	Map<String, String> parameters;

	public String runTask() {
		xTransformation.setOutputFormat(outputFormat);
		String response = xTransformation.execute(repository, transPath,
				transName, parameters);
		return response;
	}

	public XTransformation getTransformation() {
		return xTransformation;
	}

	public void setTransformation(XTransformation transformation) {
		this.xTransformation = transformation;
	}

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getTransPath() {
		return transPath;
	}

	public void setTransPath(String transPath) {
		this.transPath = transPath;
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
