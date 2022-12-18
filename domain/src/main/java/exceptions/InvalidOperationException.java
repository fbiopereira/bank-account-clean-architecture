package exceptions;

public class InvalidOperationException extends GenericException {

    public InvalidOperationException(String code, String format, Object... values) {

        super(code, format, values);

    }

}