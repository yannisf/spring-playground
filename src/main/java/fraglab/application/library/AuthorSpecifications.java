package fraglab.application.library;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class AuthorSpecifications {

    public static Specification<Author> nameStartsWith(String startsWith) {
        return (root, query, builder) -> builder.like(root.get(Author_.name), startsWith + "%");
    }

    public static Specification<Author> nameEndsWith(String endsWith) {
        return (root, query, builder) -> builder.like(root.get(Author_.name), "%" + endsWith);
    }

    public static Specification<Author> hasBookThatEndsWith(String endsWith) {
        return (root, query, builder) ->
                builder.like(root.join(Author_.books, JoinType.INNER).get(Book_.title), "%" + endsWith);
    }

    public static Specification<Author> hasBookThatHasPublisherThatEndsWith(String endsWith) {
        return (root, query, builder) ->
                builder.like(root.join(Author_.books, JoinType.INNER)
                        .join(Book_.publisher, JoinType.INNER).get(Publisher_.name), "%" + endsWith);
    }

}
