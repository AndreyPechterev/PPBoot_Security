package ru.kata.spring.boot_security.demo.exceptiom_handling;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String message) {
        super(message);
    }
}
