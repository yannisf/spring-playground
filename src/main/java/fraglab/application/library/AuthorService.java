package fraglab.application.library;

import org.springframework.context.event.EventListener;

public interface AuthorService {

    Author find(Long id);

    Author save(Author author);

    void delete(Long id);

    @EventListener
    void processAuthorCreatedEvent(AuthorCreatedEvent event);
}
