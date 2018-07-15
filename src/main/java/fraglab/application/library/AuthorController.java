package fraglab.application.library;

import fraglab.application.jms.PlaygroundJmsListenerContainerFactory;
import fraglab.application.jms.PlaygroundMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
    private PlaygroundJmsListenerContainerFactory listenerContainerFactory;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String process(@PathVariable Long id) {
        return authorService.process();
    }

    @RequestMapping(value = "/publish", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String publish() {
        authorService.publish(Book.Builder.createBuilder().title("Το Λάθος [To Lathos]").build());
        return "acknowledged";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void start() {
        listenerContainerFactory.start();
    }

}
