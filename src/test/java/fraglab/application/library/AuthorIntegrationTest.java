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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dispatcher-servlet.xml")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorIntegrationTest {

    private static Long authorId;

    @Autowired
    AuthorService authorService;

    @Test
    public void a_testFindNotFound() {
        Author author = authorService.find(1L);
    }

    @Test
    public void b_testInsert() {
        Author author = new Author("Antonis Samarakis");
        author = authorService.save(author);
        authorId = author.getId();
    }

    @Test
    public void c_testLoadFound() {
        Author author = authorService.find(authorId);
    }

    @Test
    public void d_testDelete() {
        authorService.delete(authorId);
    }

    @Test
    public void e_testLoadNotFoundAfterDelete() {
        Author author = authorService.find(1L);
    }

}
