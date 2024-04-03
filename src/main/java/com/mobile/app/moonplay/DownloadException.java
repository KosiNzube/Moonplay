package com.mobile.app.moonplay;


import java.io.IOException;

/** Thrown on an error during downloading. */
public final class DownloadException extends IOException {

    /** @param message The message for the exception. */
    public DownloadException(String message) {
        super(message);
    }

    /** @param cause The cause for the exception. */
    public DownloadException(Throwable cause) {
        super(cause);
    }

}