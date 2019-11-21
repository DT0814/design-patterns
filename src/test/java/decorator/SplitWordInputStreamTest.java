package decorator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class SplitWordInputStreamTest {
    private SplitWordInputStream splitWordInputStream;
    private Path path = Path.of("test.txt");

    @BeforeEach
    void setUp() throws IOException {
        path = Path.of("test.txt");
        Files.deleteIfExists(path);
        File file = path.toFile();
        file.createNewFile();
    }

    @Test
    void should_be_return_empty_when_file_not_data() throws IOException {
        //Arrange
        splitWordInputStream = new SplitWordInputStream(path.toFile());
        //Act
        String s = splitWordInputStream.readOneWord();
        //Assert
        Assertions.assertTrue(s.isBlank());
    }

    @Test
    void should_be_return_one_word_when_file_has_word() throws IOException {
        //Arrange
        Files.writeString(path, "hello world");
        splitWordInputStream = new SplitWordInputStream(path.toFile());
        //Act
        String oneWord = splitWordInputStream.readOneWord();
        String twoWord = splitWordInputStream.readOneWord();
        //Assert
        Assertions.assertEquals("hello", oneWord);
        Assertions.assertEquals("world", twoWord);
    }

    @Test
    void should_be_return_all_words_when_file_has_word() throws IOException {
        //Arrange
        Files.writeString(path, "hello world");
        splitWordInputStream = new SplitWordInputStream(path.toFile());
        //Act
        String[] words = splitWordInputStream.readAllWords();
        //Assert
        Assertions.assertIterableEquals(Arrays.asList("hello", "world"), Arrays.asList(words));
    }

    @Test
    void should_be_return_all_words_when_file_has_word_test_read() throws IOException {
        //Arrange
        Files.writeString(path, "hello world");
        splitWordInputStream = new SplitWordInputStream(path.toFile());
        //Act
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = splitWordInputStream.read(bytes,0,1024))>0){
            sb.append(new String(bytes,0,len));
        }
        //Assert
        Assertions.assertEquals("hello\nworld",sb.toString());
    }
}
