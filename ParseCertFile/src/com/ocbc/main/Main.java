package com.ocbc.main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 */

/**
 * @author Thang Le
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String textFile = null;
		String inputFileName = null;

		if (args.length > 0 && args[0].trim().isEmpty() == false) {
			inputFileName = args[0];
		} else {
			System.out.println("no input file");
			return;
		}

		try {
			textFile = readFile(inputFileName, Charset.defaultCharset());

			System.out.println(textFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	

}
