package server.Repository;

import server.Exceptions.NoMusicianException;
import server.Model.Musician;

import java.util.List;

/**
 * Created by IgorPc on 11/20/2018.
 */
public interface MusicianRepository {
    Musician getMusician(String name) throws NoMusicianException;

    List<Musician> getMusicians() throws NoMusicianException;

    void addMusician(Musician musician);

}
