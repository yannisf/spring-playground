package fraglab.application.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenre(Genre genre);

    List<Book> findByAuthorId(Long authorId);

    List<Book> findByAuthorNameStartingWith(String nameLike);

}
