package repository.book;

import model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    boolean save(Book book);

    void removeAll();
    void updateStock(Long bookId, int newStock);
    Boolean deleteBook(Long id);
     void updatePrice(Long bookId, Double price);
}