package com.pub.tutorial.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pub.tutorial.constants.CommonConstants;

public class TextFileReader implements IFileReader {

	@Override
	public List<String> readFile(String fileName) {
		List<String> cotentList = new ArrayList<String>();
		int index = 0;
		StringBuilder fileContent = new StringBuilder("");
		String commaDelim = "";
		String content = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((content = br.readLine()) != null) {
				index++;
				fileContent.append(commaDelim);
				fileContent.append(content);
				commaDelim = CommonConstants.COMMA_DELIMITER;
				// Creates comma separated string of 100 stocks and add string
				// to list.
				if (index % CommonConstants.BATCH_SIZE == 0) {
					cotentList.add(fileContent.toString().trim());
					fileContent = new StringBuilder("");
					commaDelim = "";
				}
			}
			// Add remaining content to list
			cotentList.add(fileContent.toString().trim());
		} catch (FileNotFoundException fileNotFoundExp) {
			System.out.println("File: " + fileName + ", is not present: "
					+ fileNotFoundExp.getMessage());
		} catch (IOException ioExp) {
			System.out.println("Exception occured while reading file: "
					+ fileName + ", " + ioExp.getMessage());
		} catch (Exception exp) {
			System.out.println("Unknown exception while reading file: "
					+ fileName + ", " + exp.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ioExp) {
					System.out.println("Exception in closing buffered stream: "
							+ ioExp.getMessage());
				}
			}
		}
		return cotentList;
	}

}
