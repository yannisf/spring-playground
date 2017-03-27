package fraglab.application.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "text/plain")
    public String find(@PathVariable String id) {
        return bookService.generate(id);
    }

}
