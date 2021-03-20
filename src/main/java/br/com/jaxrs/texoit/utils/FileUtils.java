package br.com.jaxrs.texoit.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {
	
	public FileUtils() {}
	
	public static Stream<String> loadCSV(final String filePath) throws Exception {
		try {
			if (!isValidFile(filePath)) 
				throw new Exception("File invalid!");
			
			return Files.lines(Paths.get(filePath));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private static boolean isValidFile(final String filePath) {
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
