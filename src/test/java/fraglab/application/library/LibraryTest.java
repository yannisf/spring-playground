package fraglab.application.library;

import org.junit.Assert;
import org.junit.Test;

public class LibraryTest {

    @Test
    public void testAuthorBuilder() {
        Author author = Author.Builder.createBuilder()
                .name("Antonis Samarakis")
                .books(
                        Book.Builder.createBuilder().title("Lathos").build(),
                        Book.Builder.createBuilder().title("Ziteitai elpis").build()
                ).build();

        Assert.assertEquals("Antonis Samarakis", author.getName());
        Assert.assertEquals(2, author.getBooks().size());
    }

    @Test
    public void testBookBuilder() {
        Book book = Book.Builder.createBuilder()
                .title("To Lathos")
                .build();

        Assert.assertEquals("To Lathos", book.getTitle());
    }

}
