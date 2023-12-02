package service.book;

import model.Book;
import repository.book.BookRepository;
import service.book.BookService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with id: %d not found".formatted(id)));
    }

    @Override
    public boolean save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public int getAgeOfBook(Long id) {
        Book book = this.findById(id);

        LocalDate now = LocalDate.now();

        return (int) ChronoUnit.YEARS.between(book.getPublishedDate(), now);
    }
    @Override
    public Boolean buyBook(Long bookId, int quantity) {
        Book book = this.findById(bookId);
        int updatedStock=book.getStock() - quantity;
        if (book.getStock() >= quantity) {
               bookRepository.updateStock(bookId,updatedStock);
               return Boolean.TRUE;
            }
        else {
            return Boolean.FALSE;
        }
    }
    @Override
    public Boolean deleteBook(Long id){
        return bookRepository.deleteBook(id);
    }
    public void updateStock(Long bookId, int newStock){
        bookRepository.updateStock(bookId,newStock);
    }
    public void updatePrice(Long bookId, Double price){
        bookRepository.updatePrice(bookId,price);
    }
}