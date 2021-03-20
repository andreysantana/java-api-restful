package br.com.jaxrs.texoit;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.Test;

import br.com.jaxrs.texoit.utils.FileUtils;
import junit.framework.TestCase;

public class FileUtilsTest extends TestCase {
	
	@Test
    public void testIsValidFile() throws IOException {
		assertTrue(FileUtils.isValidFile(Main.filePath));
	}
	
	@Test
    public void testIsNotEmpty() throws Exception {
		Stream<String> records = FileUtils.loadCSV(Main.filePath);
		assertNotNull(records);
		assertTrue(records.count() > 0);
	}
}
