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
    public String process(@PathVariable Long id) {
        return authorService.process();
    }

}
