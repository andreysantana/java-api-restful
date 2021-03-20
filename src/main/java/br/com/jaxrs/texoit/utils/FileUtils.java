package br.com.jaxrs.texoit.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {
	
	public FileUtils() {}
	
	public static Stream<String> loadCSV(final String filePath) throws Exception {
		Stream<String> records = null;
		try {
			if (isValidFile(filePath)) {
				records = Files.lines(Paths.get(filePath));
			}

			return records;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static boolean isValidFile(final String filePath) {
		Path path = null;
		try {
			path = Paths.get(filePath);
			if (filePath == null
					|| filePath.isEmpty() 
					|| !Files.exists(path) 
					|| !filePath.toLowerCase().contains(".csv")) {
				return false;
			} 
		} catch (Exception e) {
			return false;
		} finally {
			path = null;
		}
		return true;
	}
}
