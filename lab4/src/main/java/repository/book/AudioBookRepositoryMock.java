package repository.book;

import model.AudioBook;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AudioBookRepositoryMock implements AudioBookRepository{
    private final List<AudioBook> audiobooks;

    public AudioBookRepositoryMock(){
        audiobooks = new ArrayList<>();
    }

    @Override
    public List<AudioBook> findAll() {
        return audiobooks;
    }

    @Override
    public Optional<AudioBook> findById(Long id) {
        return audiobooks.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean save(AudioBook book) {
        return audiobooks.add(book);
    }

    @Override
    public void removeAll() {
        audiobooks.clear();
    }
}
