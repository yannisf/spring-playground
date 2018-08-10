package fraglab.application.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author find(@PathVariable Long id) {
        return authorService.find(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Author saveFromParams(@RequestParam String name) {
        return authorService.save(new Author(name));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Author saveFromJson(@RequestBody Author author) {
        return authorService.save(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }

}
