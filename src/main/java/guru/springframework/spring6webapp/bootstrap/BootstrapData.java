package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final  PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author_eric = new Author();
        author_eric.setFirstName("Eric");
        author_eric.setLastName("Kumar");

        Book book_ddd = new Book();
        book_ddd.setTitle("Domain Driven Design");
        book_ddd.setIsbn("123456789");

        Publisher publisher_abba = new Publisher();
        publisher_abba.setPublisherName("Abba");
        publisher_abba.setAddress("Einigestrasse 23");
        publisher_abba.setCity("Berburg");
        publisher_abba.setState("Deutschland");
        publisher_abba.setZip("33948");

        Author author_eric_saved = authorRepository.save(author_eric);
        Book book_ddd_saved = bookRepository.save(book_ddd);
        Publisher publisher_abba_saved = publisherRepository.save(publisher_abba);

        Author author_rod = new Author();
        author_rod.setFirstName("Rod");
        author_rod.setLastName("Johnson");

        Book book_noEJB = new Book();
        book_noEJB.setTitle("J2EE Development without EJB");
        book_noEJB.setIsbn("987654321");

        Author author_rod_saved = authorRepository.save(author_rod);
        Book book_noEJB_saved = bookRepository.save(book_noEJB);

        author_eric_saved.getBooks().add(book_ddd_saved);
        author_rod_saved.getBooks().add(book_noEJB_saved);
        book_ddd_saved.setPublisher(publisher_abba_saved);
        book_noEJB_saved.setPublisher(publisher_abba_saved);
        book_ddd_saved.getAuthors().add(author_eric_saved);
        book_noEJB_saved.getAuthors().add(author_rod_saved);


        authorRepository.save(author_eric_saved);
        authorRepository.save(author_rod_saved);
        bookRepository.save(book_ddd_saved);
        bookRepository.save(book_noEJB_saved);

        System.out.println("In Bootstrap");

        System.out.println("Author Count:" + authorRepository.count());
        System.out.println("Book Count:" + bookRepository.count());
        System.out.println("Publisher Count:" + publisherRepository.count());
    }
}
