package backend.jobportal.exception;

import org.springframework.http.HttpStatus;

public class JobPortalException extends RuntimeException {

    HttpStatus status;
    String msg;

    public JobPortalException(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }




}
