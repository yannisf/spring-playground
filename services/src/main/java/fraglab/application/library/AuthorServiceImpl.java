package fraglab.application.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author find(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void delete(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @EventListener
    @Override
    public void processAuthorCreatedEvent(AuthorCreatedEvent event) {
        System.out.println(event);
    }

}
