package server.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import server.Model.Musician;
import server.Services.MusicianService;

import java.awt.*;

/**
 * Created by IgorPc on 11/20/2018.
 */
@RestController
@RequestMapping("/musicians")
public class MusicianController {
    @Autowired
    private MusicianService musicianService;

    @RequestMapping(path = "/add",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Musician createMusician(@RequestBody Musician musician){
        musicianService.addMusician(musician);
        return musician;
    }


}
