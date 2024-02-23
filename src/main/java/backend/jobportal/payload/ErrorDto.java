package backend.jobportal.payload;


import java.util.Date;

public class ErrorDto {

    private String message;
    private Date timestamp;

    private String errorDetails;

    public ErrorDto() {
    }

    public ErrorDto(String message, Date timestamp, String errorDetails) {
        this.message = message;
        this.timestamp = timestamp;
        this.errorDetails = errorDetails;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
