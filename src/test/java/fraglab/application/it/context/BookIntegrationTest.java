package fraglab.application.it.context;

import fraglab.application.it.AbstractContextIntegrationTest;
import fraglab.application.library.Author;
import fraglab.application.library.Book;
import org.junit.Test;

public class BookIntegrationTest extends AbstractContextIntegrationTest {

    @Test
    public void test() {
        Author author = new Author("name");
        authorService.save(author);
        Book book = new Book();
        book.setTitle("title ha");
        author.addBook(book);
        authorService.save(author);
    }

}
