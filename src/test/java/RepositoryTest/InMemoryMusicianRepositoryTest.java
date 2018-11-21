package RepositoryTest;

import org.junit.Before;
import org.junit.Test;
import server.Exceptions.NoMusicianException;
import server.Repository.InMemoryMusicianRepository;

/**
 * Created by IgorPc on 11/20/2018.
 */
public class InMemoryMusicianRepositoryTest {
    private InMemoryMusicianRepository musicianRepository;

    @Before
    public void init(){
        musicianRepository=new InMemoryMusicianRepository();
    }

    @Test(expected = NoMusicianException.class)
    public void getMusicianEmptyMethodTest() throws NoMusicianException {
        musicianRepository.getMusician("test");
    }

    @Test
    public void getMusicianNullMethodTest() throws NoMusicianException {
        musicianRepository.getMusician(null);
    }
}
