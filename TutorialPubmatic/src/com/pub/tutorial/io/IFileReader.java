package com.pub.tutorial.io;

import java.util.List;

public interface IFileReader {
	/**
	 * Reads given file and returns comma separated collection of string of file
	 * contents. Collection contain upto 100 values or less.
	 * 
	 * @param fileName
	 *            name of file to be read
	 * @return comma separated string of file content
	 */
	public List<String> readFile(String fileName);
}
