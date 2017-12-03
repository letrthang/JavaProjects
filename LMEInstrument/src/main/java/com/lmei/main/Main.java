package com.lmei.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	/** logger. */
	private static final Logger LOG = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.debug("hello world!");
		System.out.print("Enter something:");
		String input = System.console().readLine();
		System.out.print(input);
	}

}
