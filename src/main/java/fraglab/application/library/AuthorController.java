package fraglab.application.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String process(@PathVariable Long id) {
        return authorService.process();
    }

    @RequestMapping(value = "/publish", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String publish() {
        authorService.publish(Book.Builder.createBuilder().title("Cool title").build());
        return "acknowledged";
    }

}
