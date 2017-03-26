package fraglab.application.library;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne()
    private Author author;

    public Book() {
    }

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }

    public static class Builder {

        private Book book;

        private Builder() {
            this.book = new Book();
        }

        public static Book.Builder createBuilder() {
            return new Book.Builder();
        };

        public Book.Builder title(String title) {
            this.book.setTitle(title);
            return this;
        }

        public Book.Builder genre(Genre genre) {
            this.book.setGenre(genre);
            return this;
        }

        public Book.Builder author(Author author) {
            this.book.setAuthor(author);
            author.addBook(this.book);
            return this;
        }

        public Book build() {
            return this.book;
        }

    }


}
