package by.tc.task05.parser.exception;

public class ParserException extends RuntimeException {
    private final String message;
    private final boolean critical;

    public ParserException(String message) {
        this.message = message;
        this.critical = true;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ParserException(String message, boolean critical) {
        this.message = message;
        this.critical = critical;
    }

    public boolean isCritical() {
        return critical;
    }
}
