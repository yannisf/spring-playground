package fraglab.application.library;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

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
    BookJpaRepository bookJpaRepository;

    @Test
    public void a_testInsert() {
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
    public void b_testFindByGenre() {
        List<Book> fiction = bookJpaRepository.findByGenre(FICTION);
        List<Book> shortStories = bookJpaRepository.findByGenre(SHORT_STORIES);
        System.out.println("stop");
    }

    @Test
    public void c_testFindByAuthor() {
        List<Book> samarakisBooks = bookJpaRepository.findByAuthorId(1L);
        List<Book> kafkaBooks = bookJpaRepository.findByAuthorNameStartingWith("Franz");
        System.out.println("stop");
    }

    @Test
    public void d_removeOneBook() {
        Author author = authorService.find(1L).get();
        Book book = author.getBooks().get(1);
        author.removeBook(book);
        authorService.save(author);
    }

    @Test
    public void e_loadAAuthon() {
        Author author = authorService.find(1L).get();
        System.out.println(author);
    }

}
