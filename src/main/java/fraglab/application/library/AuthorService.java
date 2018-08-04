package fraglab.application.library;

public interface AuthorService {

    Author find(Long id);

    Author save(Author author);

    void delete(Long id);
}
