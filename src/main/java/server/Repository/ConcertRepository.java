package server.Repository;


import server.Exceptions.DateIsBusyException;
import server.Model.Concert;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by IgorPc on 11/20/2018.
 */

public interface ConcertRepository {

    Concert getConcert(LocalDate date);

    void addConcert(Concert concert) throws DateIsBusyException;

    void deleteConcert(LocalDate date);

    List<Concert> concerts();


}
