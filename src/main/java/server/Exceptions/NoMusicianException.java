package server.Exceptions;

/**
 * Created by IgorPc on 11/20/2018.
 */
public class NoMusicianException extends Exception {
    private static final String MESSAGE="No musician";

    public NoMusicianException() {
        super(MESSAGE);
    }
}
