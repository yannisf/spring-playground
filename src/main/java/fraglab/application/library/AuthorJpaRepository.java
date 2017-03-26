package fraglab.application.library;

import fraglab.application.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorJpaRepository extends JpaRepository<Author, Long> {

}
