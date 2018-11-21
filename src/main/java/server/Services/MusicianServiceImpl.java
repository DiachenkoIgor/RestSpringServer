package server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.Model.Musician;
import server.Repository.MusicianRepository;

/**
 * Created by IgorPc on 11/20/2018.
 */
@Service
public class MusicianServiceImpl implements MusicianService {

    private MusicianRepository musicianRepository;

    @Autowired
    public MusicianServiceImpl(MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    @Override
    public void addMusician(Musician musician) {
        musicianRepository.addMusician(musician);
    }
}
