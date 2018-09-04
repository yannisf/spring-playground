package fraglab.application.library;

import org.springframework.context.ApplicationEvent;

public class AuthorCreatedEvent extends ApplicationEvent {

    private final Author author;

    public AuthorCreatedEvent(Object source, Author author) {
        super(source);
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }
}
