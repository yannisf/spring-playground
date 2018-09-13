package fraglab.application.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;

@RestController
@RequestMapping("/author")
@Transactional(timeout = 5)
public class AuthorResource {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorResource.class);

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public Author save(@RequestParam String name) {
        Author author = new Author(name);
        return entityManager.merge(author);
    }

    @PostMapping(value = "/{id}", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public Author saveId(@PathVariable Long id, @RequestParam String name) {
        LOG.info("Finding author[{}]", id);
        Author author = entityManager.find(Author.class, id);
        author.setName(name);

        Query query = entityManager.createNativeQuery("select pg_sleep(10)");
        query.getSingleResult();

        LOG.info("Updating author[{}]", id);
        return entityManager.merge(author);
    }

}
