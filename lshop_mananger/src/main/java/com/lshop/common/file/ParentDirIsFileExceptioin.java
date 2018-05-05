package com.lshop.common.file;


@SuppressWarnings("serial")
public class ParentDirIsFileExceptioin extends RuntimeException {
	private String parentDir;

	/**
	 * @param parentDir
	 *            The parent dir, which is a file.
	 */
	public ParentDirIsFileExceptioin(String parentDir) {
		this.parentDir = parentDir;
	}

	@Override
	public String getMessage() {
		return "parent directory is a file: " + parentDir;
	}

	/**
	 * Get the parent dir, which is a file.
	 * 
	 * @return
	 */
	public String getParentDir() {
		return parentDir;
	}
}
