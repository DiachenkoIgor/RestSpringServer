package server.Repository;

import org.springframework.stereotype.Repository;
import server.Exceptions.NoMusicianException;
import server.Model.Musician;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by IgorPc on 11/20/2018.
 */
@Repository
public class InMemoryMusicianRepository implements MusicianRepository {
    private List<Musician> musicians;

    public InMemoryMusicianRepository() {
        musicians=new LinkedList<>();
        musicians.add(new Musician("Igor","guitar",1000));
        musicians.add(new Musician("Paganini","violin-guitar",10000));
        musicians.add(new Musician("Beethoven","piano",10000));
        musicians.add(new Musician("Mozart","piano",50000));
    }

    @Override
    public Musician getMusician(String name) throws NoMusicianException {
        Optional<Musician> first = musicians.stream().filter(m -> m.equals(name)).findFirst();
        if(!first.isPresent()){
            throw new NoMusicianException();
        }else {
            return first.get();
        }
    }

    @Override
    public List<Musician> getMusicians() throws NoMusicianException {
        return new LinkedList<>(musicians);
    }

    @Override
    public void addMusician(Musician musician) {
        musicians.add(musician);
    }
}
