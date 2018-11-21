package server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.Exceptions.DateIsBusyException;
import server.Model.Compositions;
import server.Model.Concert;
import server.Model.Musician;
import server.Model.Payment;
import server.Repository.ConcertRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IgorPc on 11/20/2018.
 */
@Service
public class ConcertServiceImpl implements ConcertService {

    private ConcertRepository concertRepository;

    @Autowired
    public ConcertServiceImpl(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    @Override
    public void placeConcert(Concert concert) throws DateIsBusyException {
        concertRepository.addConcert(concert);
    }

    @Override
    public void payForConcert(Payment payment) {
        List<Concert> concerts = concertRepository.concerts();
        if(concerts.stream().filter(c->c.getDate().isEqual(payment.getLocalDate())).count()==0){
            throw new IllegalArgumentException("No concerts for this date");
        }
        concerts.stream().filter(c->c.getDate().isEqual(payment.getLocalDate())).forEach(c->c.setPaid(true));
    }

    @Override
    public void declineConcert(LocalDate localDate) {
        concertRepository.deleteConcert(localDate);
    }

    @Override
    public void playConcert(LocalDate date) {

         if(concertRepository.getConcert(date)!=null){
            if(concertRepository.getConcert(date).isPaid()){
                 concertRepository.deleteConcert(date);
            }else {
                throw new IllegalArgumentException("You have to pay for concert");
            }
         }else {
             throw new IllegalArgumentException("No concerts for this date");
         }
    }

    @Override
    public List<String> getCompositions() {
        return Compositions.compositions();
    }

    @Override
    public List<Concert> getConcerts() {
        return  concertRepository.concerts();
    }
}
