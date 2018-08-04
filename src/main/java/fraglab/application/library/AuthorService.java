package fraglab.application.library;

import fraglab.application.library.Author;

import java.util.Optional;

public interface AuthorService {

    Author find(Long id);

    Author save(Author author);

    void delete(Long id);
}
