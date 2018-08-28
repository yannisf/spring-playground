package fraglab.application.it;

import fraglab.application.ApplicationConfiguration;
import fraglab.application.library.AuthorRepository;
import fraglab.application.library.AuthorService;
import fraglab.application.library.BookRepository;
import fraglab.application.library.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {ApplicationConfiguration.class})
@WebAppConfiguration
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public abstract class AbstractContextIntegrationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected WebApplicationContext wac;

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected AuthorService authorService;

    @Autowired
    protected AuthorRepository authorRepository;

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected PublisherRepository publisherRepository;

    @BeforeClass
    public void beforeClass() {
    }

    @AfterClass
    public void afterClass() {
    }

    @Test
    public void contextTest() {
        ServletContext servletContext = wac.getServletContext();
        assertThat(servletContext).isNotNull();
        assertThat(servletContext).isInstanceOf(MockServletContext.class);
        assertThat(wac.getBean("authorResource")).isNotNull();
    }

}
