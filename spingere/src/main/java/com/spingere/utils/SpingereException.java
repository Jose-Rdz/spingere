package com.spingere.utils;

/**
 *
 * @author G13380
 */
public class SpingereException extends Exception {

    private static final long serialVersionUID = 1098184093168728019L;

    public SpingereException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpingereException(String message) {
        super(message);
    }

}
