package fraglab.application.library;

import org.junit.Assert;
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

import java.util.Optional;

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
        Optional<Author> author = authorService.find(1L);
        Assert.assertFalse(author.isPresent());
    }

    @Test
    public void b_testInsert() {
        Author author = Author.Builder.createBuilder().name("Antonis Samarakis").build();
        author = authorService.save(author);
        authorId = author.getId();
    }

    @Test
    public void c_testLoadFound() {
        Optional<Author> author = authorService.find(authorId);
        Assert.assertTrue(author.isPresent());
    }

    @Test
    public void d_testDelete() {
        authorService.delete(authorId);
    }

    @Test
    public void e_testLoadNotFoundAfterDelete() {
        Optional<Author> author = authorService.find(1L);
        Assert.assertFalse(author.isPresent());
    }

}
