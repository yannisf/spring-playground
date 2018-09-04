package fraglab.application.library;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class AuthorSpecifications {

    private static final String SQL_WILDCARD = "%";

    public static Specification<Author> nameStartsWith(String startsWith) {
        return (root, query, builder) -> builder.like(root.get(Author_.name), startsWith + SQL_WILDCARD);
    }

    public static Specification<Author> nameEndsWith(String endsWith) {
        return (root, query, builder) -> builder.like(root.get(Author_.name), SQL_WILDCARD + endsWith);
    }

    public static Specification<Author> hasBookThatEndsWith(String endsWith) {
        return (root, query, builder) ->
                builder.like(root.join(Author_.books, JoinType.INNER).get(Book_.title), SQL_WILDCARD + endsWith);
    }

    public static Specification<Author> hasBookThatHasPublisherThatEndsWith(String endsWith) {
        return (root, query, builder) ->
                builder.like(root.join(Author_.books, JoinType.INNER)
                        .join(Book_.publisher, JoinType.INNER).get(Publisher_.name), SQL_WILDCARD + endsWith);
    }

}
