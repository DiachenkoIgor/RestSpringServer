package server.Exceptions;

/**
 * Created by IgorPc on 11/20/2018.
 */
public class DateIsBusyException extends Exception {

    private static final String MESSAGE="Date is busy";

    public DateIsBusyException() {
        super(MESSAGE);
    }
}
