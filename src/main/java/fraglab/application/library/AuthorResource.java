package fraglab.application.library;

import fraglab.application.annotations.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    AuthorService authorService;

    @Timed
    @GetMapping(value = "/{id}")
    public Author find(@PathVariable Long id) {
        return authorService.find(id);
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Author saveFromParams(@RequestParam String name) {
        return authorService.save(new Author(name));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Author saveFromJson(@RequestBody Author author) {
        Author createdAuthor = authorService.save(author);
        publisher.publishEvent(new AuthorCreatedEvent(this, createdAuthor));
        return createdAuthor;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }

}
