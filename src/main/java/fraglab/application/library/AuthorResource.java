package fraglab.application.library;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/author")
@Transactional(timeout = 10)
public class AuthorResource {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public Author save(@RequestParam String name) {
        Author author = new Author(name);
        return entityManager.merge(author);
    }

    @PostMapping(value = "/{id}", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    public Author saveId(@PathVariable Long id, @RequestParam String name) {
        Author author = new Author(name);
        return entityManager.merge(author);
    }

}
