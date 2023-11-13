package repository.book;
import model.EBook;
import java.util.List;
import java.util.Optional;

public class EBookRepositoryCacheDecorator extends EBookRepositoryDecorator{
    private Cache<EBook> cache;

    public EBookRepositoryCacheDecorator(EBookRepository eBookRepository, Cache<EBook> cache){
        super(eBookRepository);
        this.cache = cache;
    }
    @Override
    public List<EBook> findAll() {
        if(cache.hasResult()){
            return cache.load();
        }

        List<EBook> eBooks = decoratedRepository.findAll();
        cache.save(eBooks);
        return eBooks;
    }

    @Override
    public Optional<EBook> findById(Long id) {
        return decoratedRepository.findById(id);
    }

    @Override
    public boolean save(EBook eBook) {
        cache.invalidateCache();
        return decoratedRepository.save(eBook);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }
}