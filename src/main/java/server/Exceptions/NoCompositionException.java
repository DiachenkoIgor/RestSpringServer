package server.Exceptions;

/**
 * Created by IgorPc on 11/20/2018.
 */
public class NoCompositionException extends Exception {
    private static final String MESSAGE="Concert have to has composition";

    public NoCompositionException() {
        super(MESSAGE);
    }
}
