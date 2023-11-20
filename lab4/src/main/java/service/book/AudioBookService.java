package service.book;

import model.AudioBook;

import java.util.List;

public interface AudioBookService {

    List<AudioBook> findAll();
    AudioBook findById(Long id);

   boolean save(AudioBook audioBook);

    int getAgeOfBook(Long id);
}