package fraglab.application.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author find(@PathVariable Long id) {
        return authorService.find(id).orElseThrow(RuntimeException::new);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody Author author) {
        authorService.save(author);
        AuthorEvent event = new AuthorEvent(this);
        applicationEventPublisher.publishEvent(event);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }

}
