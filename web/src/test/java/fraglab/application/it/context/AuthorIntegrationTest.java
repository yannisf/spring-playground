package fraglab.application.it.context;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fraglab.application.it.AbstractContextIntegrationTest;
import fraglab.application.library.*;
import org.assertj.core.api.Assertions;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static fraglab.application.library.AuthorSpecifications.*;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.data.jpa.domain.Specification.where;

public class AuthorIntegrationTest extends AbstractContextIntegrationTest {

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        createTestData("author1", "title2", "publisher3");
        createTestData("author4", "title5", "publisher6");
        createTestData("author7", "title8", "publisher9");
    }

    @Test
    @Transactional
    public void addAuthor() {
        String name = "test name";
        Author author = authorService.save(new Author(name));
        Long id = author.getId();
        Assertions.assertThat(id).isNotNull();
        Author foundAuthor = authorService.find(id);
        Assertions.assertThat(foundAuthor).isNotNull();
        Assertions.assertThat(foundAuthor.getName()).isEqualTo(name);
        authorService.delete(foundAuthor.getId());
        Author deletedAuthor = authorService.find(id);
        Assertions.assertThat(deletedAuthor).isNull();
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

    @Test
    public void test2() {
        List<Author> authors = authorRepository.findAll(
                where(nameEndsWith("4")
                        .and(hasBookThatEndsWith("5")))
                        .and(hasBookThatHasPublisherThatEndsWith("6")));
        assertThat(authors).hasSize(1);
        Author author = authors.get(0);
        assertThat(author.getName()).isEqualTo("author4");
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
