package ru.vood.freemarker.ext.exeption;

public class FtlException extends RuntimeException {
    public FtlException(String message, Throwable cause) {
        super(message, cause);
    }
}
