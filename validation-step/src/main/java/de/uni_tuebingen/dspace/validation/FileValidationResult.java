package de.uni_tuebingen.dspace.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for passing file validation information from processing step to ui-binding.
 * @author Fabian Hamm
 *
 */
public class FileValidationResult {
	
	private String fileName;
	
	private List<String> problemMessages = new ArrayList<>();

	public FileValidationResult() {
	
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getProblemMessages() {
		return problemMessages;
	}

	public void setProblemMessages(List<String> problemMessages) {
		this.problemMessages = problemMessages;
	}
	
	public boolean canBeFixedByUser() {
		return this.problemMessages.size() > 0;
	}

	@Override
	public String toString() {
		return "FileValidationResult [fileName=" + fileName + ", problemMessages=" + problemMessages + "]";
	}
	
	
	
}
