package fraglab.application.it.context;

import fraglab.application.it.AbstractContextIntegrationTest;
import fraglab.application.library.Author;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorIntegrationTest extends AbstractContextIntegrationTest {

    @Test
    @Transactional
    public void addAuthor() {
        String name = "test name";
        Author author = authorService.save(new Author(name));
        Long id = author.getId();
        assertThat(id).isNotNull();
        Author foundAuthor = authorService.find(id);
        assertThat(foundAuthor).isNotNull();
        assertThat(foundAuthor.getName()).isEqualTo(name);
        authorService.delete(foundAuthor.getId());
        Author deletedAuthor = authorService.find(id);
        assertThat(deletedAuthor).isNull();
    }

}
