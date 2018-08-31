package com.psl.read;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromConfigFile {
	
	public static String readUsingFiles(String fileName) {
		try {
			return new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
