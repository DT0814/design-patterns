package observed;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class ObservedTest {
    @Test
    void should_be_call_reader_update_method() {
        //Arrange
        FirstReader firstReader = mock(FirstReader.class);
        SecondReader secondReader = mock(SecondReader.class);
        Author author = new HistoryAuthor();
        Article article = new Article("this is a history article", Instant.now());
        author.register(firstReader);
        author.register(secondReader);
        //Act
        author.release(article);
        //Assert
        verify(firstReader).update(article);
        verify(secondReader).update(article);
    }

    @Test
    void should_be_not_call_when_removed_reader_update() {
        //Arrange
        FirstReader firstReader = mock(FirstReader.class);
        SecondReader secondReader = mock(SecondReader.class);
        Author author = new HistoryAuthor();
        Article article = new Article("this is a history article", Instant.now());
        author.register(firstReader);
        author.register(secondReader);
        author.remove(secondReader);
        //Act
        author.release(article);

        //Assert
        verify(firstReader).update(article);
        verify(secondReader, never()).update(article);
    }
}
