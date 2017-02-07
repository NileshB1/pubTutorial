package com.pub.tutorial.io;

import java.util.List;

public interface IFileWriter<T> {
	/**
	 * Writes list of Object of given type T to file
	 * 
	 * @param fileName
	 *            name of file.
	 * @param list
	 *            collection of object of given type to write to file.
	 */
	public void writeToFile(String fileName, List<T> list);

	/**
	 * Writes header values to given file.
	 * 
	 * @param fileName
	 *            name of file
	 */
	public void writeHeader(String fileName);
}
