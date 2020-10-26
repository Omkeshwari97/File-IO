package fileio;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.Test;

public class NIOFileAPITest 
{
	private static final String HOME = System.getProperty("user.home");
	private static final String PLAY_WITH_NIO = "TempPlayGround";

	@Test
	public void givenPathWhenChecked_ThenConfirm() throws IOException
	{
		Path homePath = Paths.get(HOME);
		assertTrue(Files.exists(homePath));
		
		Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
		if(Files.exists(playPath))
		{
			FileUtils.deleteFiles(playPath.toFile());
		}
		assertTrue(Files.notExists(playPath));
		
		Files.createDirectory(playPath);
		assertTrue(Files.exists(playPath));
		
		IntStream.range(1, 10).forEach(cntr -> {
			Path tempFile = Paths.get(playPath + "/temp" + cntr);
			assertTrue(Files.notExists(tempFile));
			
			try
			{
				Files.createFile(tempFile);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			assertTrue(Files.exists(tempFile));
		});
		
		Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
		Files.newDirectoryStream(playPath).forEach(System.out::println);
		Files.newDirectoryStream(playPath, path -> path.toFile().isFile() &&
											path.toString().startsWith("temp"))
		.forEach(System.out::println);
	}
}
