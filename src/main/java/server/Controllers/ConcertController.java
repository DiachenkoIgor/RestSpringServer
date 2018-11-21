package server.Controllers;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpEntity;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import server.Exceptions.DateIsBusyException;
        import server.Exceptions.NoCompositionException;
        import server.Model.Concert;
        import server.Model.Payment;

        import server.Services.ConcertService;

        import java.util.List;

/**
 * Created by IgorPc on 11/20/2018.
 */
@RestController
@RequestMapping("/concerts")
public class ConcertController {
    @Autowired
    private ConcertService concertService;


    public ConcertController() {
    }
    //For Testing
    public ConcertController(ConcertService concertService) {
        this.concertService=concertService;
    }

    @RequestMapping(path = "/place",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Concert placeConcert(@RequestBody Concert concert) throws NoCompositionException, DateIsBusyException {
       if(concert.getComposition()==null){
           throw  new NoCompositionException();
       }
       concertService.placeConcert(concert);
       return concert;
    }


    @RequestMapping(path = "/getConcerts",method = RequestMethod.GET,

            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})

    public List<Concert> getConcerts() {
       return concertService.getConcerts();
    }

    @RequestMapping(path = "/pay",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public boolean pay(@RequestBody Payment payment) {
        concertService.payForConcert(payment);
        return true;
    }

    @RequestMapping(path = "/decline",method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Concert declineConcert(@RequestBody Concert concert) {
        concertService.declineConcert(concert.getDate());
        return concert;
    }

    @RequestMapping(path = "/play",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Concert playConcert(@RequestBody Concert concert) throws DateIsBusyException {
        concertService.playConcert(concert.getDate());
        return concert;
    }

    @RequestMapping(path = "/getCompositions",method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public List<String> getCompositions() {
        return concertService.getCompositions();
    }


    @ExceptionHandler(value = {DateIsBusyException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT,reason = "Date is busy")
    public void handleBusyException(){}


    @ExceptionHandler(value = {NoCompositionException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT,reason = "No compositions at the moment")
    public void handleCompositionException(){}

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException text){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(text.getMessage());
    }
}
