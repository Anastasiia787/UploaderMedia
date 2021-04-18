package tech.itpark.uploader.exception;

public class MediaUploadException extends RuntimeException{
    public MediaUploadException() {
        super();
    }

    public MediaUploadException(String message) {
        super(message);
    }

    public MediaUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public MediaUploadException(Throwable cause) {
        super(cause);
    }

    protected MediaUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
