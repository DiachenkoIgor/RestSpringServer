package ServicesTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import server.Exceptions.DateIsBusyException;
import server.Model.Concert;
import server.Model.Payment;
import server.Repository.ConcertRepository;
import server.Services.ConcertService;
import server.Services.ConcertServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

/**
 * Created by IgorPc on 11/21/2018.
 */
public class ConcertServiceImplTest {
    private ConcertService concertService;
    private ConcertRepository repositoryMock;

    @Before
    public void init(){
        repositoryMock= Mockito.mock(ConcertRepository.class);
        concertService=new ConcertServiceImpl(repositoryMock);
    }

    @Test
    public void placeConcertAddTest() throws DateIsBusyException {

        Concert concert=new Concert(LocalDate.now(),"Mozart");



        concertService.placeConcert(concert);

        Mockito.verify(repositoryMock).addConcert(concert);

    }
    @Test(expected = DateIsBusyException.class)
    public void placeConcertAddTwiceSameConcertTest() throws DateIsBusyException {

        Concert concert=new Concert(LocalDate.now(),"Mozart");

        concertService.placeConcert(concert);

        Mockito.verify(repositoryMock).addConcert(concert);


        Mockito.doThrow(new DateIsBusyException()).when(repositoryMock).addConcert(concert);

        concertService.placeConcert(concert);


    }

    @Test
    public void payForConcertTest() throws DateIsBusyException {
        LocalDate now = LocalDate.now();
        Concert concert=new Concert(now,"Mozart");

        concertService.placeConcert(concert);

        Mockito.when(repositoryMock.concerts()).then(t->new ArrayList<>(Arrays.asList(concert)));

        concertService.payForConcert(new Payment(now,0,null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void payForConcertNoConcertTest() throws DateIsBusyException {

        Mockito.when(repositoryMock.concerts()).then(t->new ArrayList<>());

        concertService.payForConcert(new Payment(LocalDate.now(),0,null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void declineConcertEmptyConcertsTest(){
        LocalDate date = LocalDate.now();

        Mockito.doThrow(IllegalArgumentException.class).when(repositoryMock).deleteConcert(date);

        concertService.declineConcert(date);
    }

    @Test
    public void declineConcertConcertsTest() throws DateIsBusyException {
        LocalDate now = LocalDate.now();
        Concert concert = new Concert(now, "Mozart");
        concertService.placeConcert(concert);
        concertService.declineConcert(now);

        Mockito.verify(repositoryMock).addConcert(concert);
        Mockito.verify(repositoryMock).deleteConcert(now);
    }

    @Test(expected = IllegalArgumentException.class)
    public void declineConcertTwiceTest() throws DateIsBusyException {
        LocalDate now = LocalDate.now();
        Concert concert = new Concert(now, "Mozart");
        concertService.placeConcert(concert);
        concertService.declineConcert(now);

        Mockito.verify(repositoryMock).addConcert(concert);
        Mockito.verify(repositoryMock).deleteConcert(now);

        Mockito.doThrow(IllegalArgumentException.class).when(repositoryMock).deleteConcert(now);
        concertService.declineConcert(now);
    }

    @Test(expected = IllegalArgumentException.class)
    public void playConcertEmptyConcerts(){
        LocalDate now = LocalDate.now();
        Mockito.when(repositoryMock.getConcert(now)).thenReturn(null);

        concertService.playConcert(now);
    }

    @Test(expected = IllegalArgumentException.class)
    public void playConcertForNotPaidConcert(){
        LocalDate now = LocalDate.now();
        Mockito.when(repositoryMock.getConcert(now)).thenReturn(new Concert(now,"Test"));

        concertService.playConcert(now);
    }

    @Test
    public void playConcertForPaidConcert(){
        LocalDate now = LocalDate.now();
        Concert concert = new Concert(now, "Test");
        concert.setPaid(true);
        Mockito.when(repositoryMock.getConcert(now)).thenReturn(concert);

        concertService.playConcert(now);
    }

    @Test
    public void getConcertsEmpty(){
        Mockito.when(repositoryMock.concerts()).thenReturn(new ArrayList<>());

        Assert.assertTrue(concertService.getConcerts().size()==0);
    }

    @Test
    public void getConcertsFull(){
        Mockito.when(repositoryMock.concerts()).thenReturn(
                new ArrayList<>(
                        Arrays.asList(
                                new Concert(LocalDate.now(),"Test"),
                                new Concert(LocalDate.now(),"Test"),
                                new Concert(LocalDate.now(),"Test")
                        )));

        Assert.assertTrue(concertService.getConcerts().size()==3);
    }








}
