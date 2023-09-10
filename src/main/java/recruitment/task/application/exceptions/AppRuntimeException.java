package recruitment.task.application.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppRuntimeException extends RuntimeException {
    private final String message;
    private final Integer statusCode;

    public AppRuntimeException(ErrorType error) {
        this.message = error.getStatusMassage();
        this.statusCode = error.getStatusCode();
    }
}
