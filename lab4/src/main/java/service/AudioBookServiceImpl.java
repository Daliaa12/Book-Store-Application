package service;

import model.AudioBook;
import model.Book;
import model.EBook;
import repository.book.AudioBookRepository;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import repository.book.EBookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AudioBookServiceImpl implements AudioBookService{
    private final AudioBookRepository audioBookRepository;

    public AudioBookServiceImpl(AudioBookRepository audioBookRepository){
        this.audioBookRepository = audioBookRepository;
    }

    @Override
    public List<AudioBook> findAll() {
        return audioBookRepository.findAll();
    }

    @Override
    public AudioBook findById(Long id) {
        return audioBookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with id: %d not found".formatted(id)));
    }

    @Override
    public boolean save(AudioBook audioBook) {
        return audioBookRepository.save(audioBook);
    }

    @Override
    public int getAgeOfBook(Long id) {
        AudioBook audioBook = this.findById(id);

        LocalDate now = LocalDate.now();

        return (int) ChronoUnit.YEARS.between(audioBook.getPublishedDate(), now);
    }
}