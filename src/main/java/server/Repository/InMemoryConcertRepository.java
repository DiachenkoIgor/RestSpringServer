package server.Repository;

import org.springframework.stereotype.Repository;
import server.Exceptions.DateIsBusyException;
import server.Model.Concert;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IgorPc on 11/20/2018.
 */
@Repository
public class InMemoryConcertRepository implements ConcertRepository {

    private Map<LocalDate,Concert> concertMap;

    public InMemoryConcertRepository() {
        concertMap=new HashMap<>();

    }

    @Override
    public Concert getConcert(LocalDate date) {
        Concert concert = concertMap.get(date);
        if(concert==null){
            throw new IllegalArgumentException("No concert");
        }
        return concert;
    }

    @Override
    public void addConcert(Concert concert) throws DateIsBusyException {
        if(concertMap.get(concert.getDate())!=null){
            throw new DateIsBusyException();
        }
        concertMap.put(concert.getDate(),concert);
    }

    @Override
    public void deleteConcert(LocalDate date) {
        Concert concert = concertMap.remove(date);
        if(concert==null){
            throw new IllegalArgumentException("No concert");
        }

    }

    @Override
    public List<Concert> concerts() {
        return concertMap.values().stream().sorted((c1,c2)->{
            if(c1.getDate().isBefore(c2.getDate())){
                return -1;
            }else if(c1.getDate().isEqual(c2.getDate())){
                return 0;
            }
            return 1;
        }).collect(Collectors.toList());
    }
}
