package fraglab.application.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LibraryEventListener {

    @Autowired
    private AuthorJpaRepository authorJpaRepository;

    @EventListener
    public void handleAuthorEvent(AuthorEvent event) {
        System.out.println(authorJpaRepository.count() + " authors in the database");
    }

}
