import database.JDBConnectionWrapper;
import model.AudioBook;
import model.Book;
import model.EBook;
import model.builder.AudioBookBuilder;
import model.builder.BookBuilder;
import model.builder.EBookBuilder;
import repository.book.*;
import service.AudioBookService;
import service.BookService;
import service.BookServiceImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");


        BookRepository bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(connectionWrapper.getConnection()),
                new Cache<>()
        );

        BookService bookService = new BookServiceImpl(bookRepository);

        Book book = new BookBuilder()
                .setAuthor("', '', null); SLEEP(20); --")
                .setTitle("Fram Ursul Polar")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();
        AudioBook audiobook = new AudioBookBuilder()
                .setAuthor("', '', null); SLEEP(20); --")
                .setTitle("Audio Fram Ursul Polar")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .setRunTime(6)
                .build();
        EBook eBook = new EBookBuilder()
                .setAuthor("', '', null); SLEEP(20); --")
                .setTitle("Ebook Fram Ursul Polar")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .setFormat("pdf")
                .build();


        bookService.save(book);


        System.out.println(bookService.findAll());



        System.out.println(bookService.findAll());
        System.out.println(bookService.findAll());
        System.out.println(bookService.findAll());

    }
}