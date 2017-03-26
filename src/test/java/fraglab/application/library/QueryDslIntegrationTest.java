package fraglab.application.library;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Before;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class QueryDslIntegrationTest {

    @Autowired
    AuthorService authorService;

    @PersistenceContext
    EntityManager entityManager;

    @Before
    public void setup() {
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
    public void testQueryDsl() {
        QAuthor author = QAuthor.author;
        JPAQueryFactory factory = new JPAQueryFactory(entityManager);
        Author antonis_samarakis = factory
                .selectFrom(author)
                .where(author.name.startsWithIgnoreCase("antonis"))
                .fetchOne();
        System.out.println("Found: " + antonis_samarakis);
    }

}
