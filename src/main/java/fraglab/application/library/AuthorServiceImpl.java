package fraglab.application.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorJpaRepository authorJpaRepository;

    @Override
    public Optional<Author> find(Long id) {
        return Optional.ofNullable(authorJpaRepository.findOne(id));
    }

    @Override
    public Author save(Author author) {
        return authorJpaRepository.save(author);
    }

    @Override
    public void delete(Long authorId) {
        authorJpaRepository.delete(authorId);
    }

}
