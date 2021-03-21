package br.com.jaxrs.texoit;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

import br.com.jaxrs.texoit.utils.FileUtils;
import junit.framework.TestCase;

public class FileUtilsTest extends TestCase {
	
	@Test
    public void testIsValidFile() throws IOException {
		assertTrue(FileUtils.isValidFile(Paths.get(Main.filePath)));
	}
	
	@Test
    public void testIsNotEmpty() throws Exception {
		Stream<String> records = FileUtils.loadCSV();
		assertNotNull(records);
		assertTrue(records.count() > 0);
	}
}
