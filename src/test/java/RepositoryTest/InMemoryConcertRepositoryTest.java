package RepositoryTest;

        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import server.Exceptions.DateIsBusyException;
        import server.Model.Concert;
        import server.Repository.InMemoryConcertRepository;

        import java.time.LocalDate;
        import java.util.List;

/**
 * Created by IgorPc on 11/20/2018.
 */

public class InMemoryConcertRepositoryTest {
    private InMemoryConcertRepository concertRepository;

    @Before
    public void initializeRepository() {
        concertRepository = new InMemoryConcertRepository();
    }

    @Test
    public void addConcertMethodTest() throws DateIsBusyException {
        concertRepository.addConcert(new Concert(LocalDate.now(),"Test"));
    }

    @Test(expected = DateIsBusyException.class)
    public void addConcertTwiceMethodTest() throws DateIsBusyException {
        concertRepository.addConcert(new Concert(LocalDate.now(),"Test"));
        concertRepository.addConcert(new Concert(LocalDate.now(),"Test"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getConcertEmptyMethodTest() {
        concertRepository.getConcert(LocalDate.now());
    }

    @Test
    public void getConcertMethodTest() throws DateIsBusyException {
        concertRepository.addConcert(new Concert(LocalDate.now(),"Test"));
        concertRepository.getConcert(LocalDate.now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteConcertEmptyMethodTest()  {
        concertRepository.deleteConcert(LocalDate.now());
    }

    @Test
    public void deleteConcertMethodTest() throws DateIsBusyException {
        concertRepository.addConcert(new Concert(LocalDate.now(),"Test"));
        concertRepository.deleteConcert(LocalDate.now());
    }

    @Test
    public void concertsEmptyCheck(){
        List<Concert> concerts = concertRepository.concerts();
        if(concerts.size()!=0){
            Assert.assertTrue(false);
        }
    }

    @Test
    public void concertsMethodCheck() throws DateIsBusyException {
        concertRepository.addConcert(new Concert(LocalDate.now(),"Test"));
        concertRepository.addConcert(new Concert(LocalDate.now().plusDays(1),"Test"));
        List<Concert> concerts = concertRepository.concerts();
        if(concerts.size()!=2){
            Assert.assertTrue(false);
        }
    }
}
