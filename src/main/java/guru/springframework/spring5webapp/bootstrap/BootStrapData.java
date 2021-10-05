package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.PortUnreachableException;

@Component
public class BootStrapData implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher manning = new Publisher("Manning", "123 Hampton Boulevard",
                "New York", "New York State", "1234-567");

        publisherRepository.save(manning);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123456");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(manning);
        manning.getBooks().add(ddd);

        // saves them into the H2 database
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(manning);


        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "46582393");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(manning);
        manning.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(manning);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of books for publisher: " + manning.getBooks().size());



    }
}
