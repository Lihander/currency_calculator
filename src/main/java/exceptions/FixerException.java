package exceptions;

public class FixerException extends Exception {
    public FixerException(String message) {
        super("Ошибка от Fixer.io:" + message);
    }
}
