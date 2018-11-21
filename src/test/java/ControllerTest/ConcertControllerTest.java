package ControllerTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import server.Controllers.ConcertController;
import server.Exceptions.DateIsBusyException;
import server.Exceptions.NoCompositionException;
import server.Model.Concert;
import server.Services.ConcertService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IgorPc on 11/21/2018.
 */
public class ConcertControllerTest {
    private ConcertController concertController;
    private ConcertService concertService;

    @Before
    public void init(){
        concertService= Mockito.mock(ConcertService.class);
        concertController=new ConcertController(concertService);
    }

    @Test(expected = NoCompositionException.class)
    public void placeConcertTestWithoutComposition() throws DateIsBusyException, NoCompositionException {
        Concert concert = new Concert(LocalDate.now(), null);

        concertController.placeConcert(concert);

    }

    @Test
    public void placeConcertTestWithComposition() throws DateIsBusyException, NoCompositionException {
        Concert concert = new Concert(LocalDate.now(), "Test");

        Assert.assertTrue(concertController.placeConcert(concert).equals(concert));

    }

    @Test
    public void getConcertsTest(){
        Mockito.when(concertService.getConcerts()).thenReturn(new ArrayList<>());
        Assert.assertTrue(concertController.getConcerts().size()==0);
    }

    @Test
    public void getConcertsAfterAddingTest() throws DateIsBusyException, NoCompositionException {
        Concert concert = new Concert(LocalDate.now(), "test");
        concertController.placeConcert(concert);

        Mockito.verify(concertService).placeConcert(concert);

        Mockito.when(concertService.getConcerts()).thenReturn(new ArrayList<>(Arrays.asList(concert)));

        Assert.assertTrue(concertController.getConcerts().get(0).equals(concert));
    }


}
