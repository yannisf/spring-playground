package fraglab.application.it.context;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fraglab.application.it.AbstractContextIntegrationTest;
import fraglab.application.library.Author;
import fraglab.application.library.Book;
import fraglab.application.library.QAuthor;
import fraglab.application.library.QBook;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class QueryDslIntegrationTest extends AbstractContextIntegrationTest {


    @Before
    public void setup() {
        Author author1 = new Author("Antonis Samarakis");
        author1 = authorService.save(author1);

        Author author2 = new Author("Franz Kafka");
        author2 = authorService.save(author2);
    }

    @Test
    public void testQueryDsl() {
        QAuthor author = QAuthor.author;
        JPAQueryFactory factory = new JPAQueryFactory(entityManager);
        Author antonis_samarakis = factory
                .selectFrom(author)
                .where(author.name.startsWithIgnoreCase("antonis"))
                .fetchOne();
        QBook book = QBook.book;
        List<Book> samarakisShortStories = factory
                .selectFrom(book)
                .where(
                        book.author.name.containsIgnoreCase("samarakis")
                ).fetch();
        System.out.println("Found: " + antonis_samarakis);
    }

}
