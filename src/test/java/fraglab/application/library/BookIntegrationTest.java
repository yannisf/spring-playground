package fraglab.application.library;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static fraglab.application.library.Genre.FICTION;
import static fraglab.application.library.Genre.SHORT_STORIES;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dispatcher-servlet.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookIntegrationTest {

    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorJpaRepository authorJpaRepository;

    @Autowired
    BookJpaRepository bookJpaRepository;

    static Long authorId;

    @Test
    public void a_cleanup() {
        authorJpaRepository.findAll().stream()
                .forEach(a -> authorJpaRepository.delete(a));
    }

    @Test
    public void b_testInsert() {
        Author author1 = Author.Builder.createBuilder()
                .name("Antonis Samarakis")
                .books(
                        Book.Builder.createBuilder().title("To Lathos").genre(FICTION).build(),
                        Book.Builder.createBuilder().title("Ziteitai Elpis").genre(SHORT_STORIES).build(),
                        Book.Builder.createBuilder().title("To Diavatirio").genre(SHORT_STORIES).build()
                )
                .build();
        author1 = authorService.save(author1);

        Author author2 = Author.Builder.createBuilder()
                .name("Franz Kafka")
                .books(
                        Book.Builder.createBuilder().title("I diki").genre(FICTION).build(),
                        Book.Builder.createBuilder().title("O Pirgos").genre(FICTION).build(),
                        Book.Builder.createBuilder().title("I Metamorfosi").genre(SHORT_STORIES).build()
                )
                .build();
        author2 = authorService.save(author2);
    }

    @Test
    public void c_testFindByGenre() {
        List<Book> fiction = bookJpaRepository.findByGenre(FICTION);
        List<Book> shortStories = bookJpaRepository.findByGenre(SHORT_STORIES);
    }

    @Test
    public void d_testFindByAuthor() {
        List<Book> samarakisBooks = bookJpaRepository.findByAuthorId(1L);
        List<Book> kafkaBooks = bookJpaRepository.findByAuthorNameStartingWith("Franz");
    }

    @Test
    public void e_removeOneBook() {
        Author author = authorJpaRepository.findAll().get(0);
        Book book = author.getBooks().get(0);
        author.removeBook(book);
        authorService.save(author);
        authorId = author.getId();
    }

    @Test
    public void f_loadAAuthor() {
        Author author = authorService.find(authorId).get();
        System.out.println(author);
    }

}