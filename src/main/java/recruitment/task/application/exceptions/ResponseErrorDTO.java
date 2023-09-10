package recruitment.task.application.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseErrorDTO {
    private Integer statusCode;
    private String massage;

    public ResponseErrorDTO(Integer statusCode, String massage) {
        this.statusCode = statusCode;
        this.massage = massage;
    }
}
