package backend.jobportal.exception;

import backend.jobportal.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> BlogAppExceptionHandler(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {

        return new ResponseEntity<>(

                new ErrorDto(exception.getMessage(), new Date(),
                        webRequest.getDescription(false))
                ,
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler(JobPortalException.class)
    public ResponseEntity<ErrorDto> BlogAppExceptionHandler(
            JobPortalException exception,
            WebRequest webRequest
    ) {

        return new ResponseEntity<>(

                new ErrorDto(exception.getMessage(), new Date(), webRequest.getDescription(false))
                ,
                HttpStatus.BAD_REQUEST
        );

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> globalExceptionHandling(
            Exception exception,
            WebRequest webRequest
    ) {

        return new ResponseEntity<>(

                new ErrorDto(exception.getMessage(), new Date(),
                        webRequest.getDescription(false))
                ,
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }

}
