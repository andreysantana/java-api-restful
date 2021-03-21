package br.com.jaxrs.texoit.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import br.com.jaxrs.texoit.Main;

public class FileUtils {
	
	public FileUtils() {}
	
	public static Stream<String> loadCSV() throws Exception {
		Stream<String> records = null;
		Path path = Paths.get(Main.filePath);
		//System.out.println(Files.readString(path));
		try {
			if (isValidFile(path)) 
				records = Files.lines(path);

			return records;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static boolean isValidFile(final Path path) {
		String filePath = path.getFileName().toString();
		try {
			if (filePath == null
					|| filePath.isEmpty() 
					|| !Files.exists(path) 
					|| !filePath.toLowerCase().contains(".csv")) {
				return false;
			} 
		} catch (Exception e) {
			return false;
		} 
		return true;
	}
}
