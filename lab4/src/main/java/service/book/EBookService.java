package service.book;

import model.EBook;

import java.util.List;

public interface EBookService {

    List<EBook> findAll();
        EBook findById(Long id);

    boolean save(EBook eBook);

    int getAgeOfBook(Long id);
}