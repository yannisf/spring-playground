package fraglab.application.it.context;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fraglab.application.it.AbstractContextIntegrationTest;
import fraglab.application.library.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BookIntegrationTest extends AbstractContextIntegrationTest {

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        createTestData("author1", "title2", "publisher3");
        createTestData("author4", "title5", "publisher6");
        createTestData("author7", "title8", "publisher9");
    }

    @Test
    public void test1() {
        QAuthor qAuthor = QAuthor.author;
        QBook qBook = QBook.book;
        QPublisher qPublisher = QPublisher.publisher;
        JPAQueryFactory factory = new JPAQueryFactory(entityManager);
        QueryResults<Author> results = factory
                .selectFrom(qAuthor)
                .join(qAuthor.books, qBook)
                .on(qBook.title.endsWith("8"))
                .join(qBook.publisher, qPublisher)
                .on(qPublisher.name.endsWith("9"))
                .where(qAuthor.name.startsWithIgnoreCase("author"))
                .fetchResults();
        assertThat(results.getTotal()).isEqualTo(1);
        assertThat(results.getResults().get(0).getName()).isEqualTo("author7");
    }

    private void createTestData(String authorName, String bookTitle, String publisherName) {
        Author author = new Author(authorName);
        Book book = new Book(bookTitle);
        author.addBook(book);
        authorService.save(author);
        Publisher publisher = new Publisher(publisherName);
        publisher = publisherRepository.save(publisher);
        publisher.addBook(book);
        bookRepository.save(book);
    }

}
