package repository.book;

import model.AudioBook;

import java.util.List;
import java.util.Optional;

public class AudioBookRepositoryCacheDecorator extends AudioBookRepositoryDecorator{
    private Cache<AudioBook> cache;

    public AudioBookRepositoryCacheDecorator(AudioBookRepository audioBookRepository, Cache<AudioBook> cache){
        super(audioBookRepository);
        this.cache = cache;
    }
    @Override
    public List<AudioBook> findAll() {
        if(cache.hasResult()){
            return cache.load();
        }

        List<AudioBook> audioBooks = decoratedRepository.findAll();
        cache.save(audioBooks);
        return audioBooks;
    }

    @Override
    public Optional<AudioBook> findById(Long id) {
        return decoratedRepository.findById(id);
    }

    @Override
    public boolean save(AudioBook audioBook) {
        cache.invalidateCache();
        return decoratedRepository.save(audioBook);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedRepository.removeAll();
    }
}