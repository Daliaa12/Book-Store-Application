package service.book;

import model.EBook;
import repository.book.EBookRepository;
import service.book.EBookService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EBookServiceImpl implements EBookService {
    private final EBookRepository EbookRepository;

    public EBookServiceImpl(EBookRepository ebookRepository){
        this.EbookRepository = ebookRepository;
    }

    @Override
    public List<EBook> findAll() {
        return EbookRepository.findAll();
    }

    @Override
    public EBook findById(Long id) {
        return EbookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with id: %d not found".formatted(id)));
    }

    @Override
    public boolean save(EBook eBook) {
        return EbookRepository.save(eBook);
    }

    @Override
    public int getAgeOfBook(Long id) {
        EBook eBook = this.findById(id);
        LocalDate now = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(eBook.getPublishedDate(), now);
    }
}