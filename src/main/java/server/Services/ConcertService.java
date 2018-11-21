package server.Services;

import server.Exceptions.DateIsBusyException;
import server.Model.Concert;
import server.Model.Payment;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IgorPc on 11/20/2018.
 */
public interface ConcertService {

    void placeConcert(Concert concert) throws DateIsBusyException;

    void payForConcert(Payment payment);

    void declineConcert(LocalDate localDate);

    void playConcert(LocalDate date);

    List<String> getCompositions();

    List<Concert> getConcerts();

}
