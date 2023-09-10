package recruitment.task.application.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseErrorDTO {
    private Integer status;
    private String massage;

    public ResponseErrorDTO(Integer status, String massage) {
        this.status = status;
        this.massage = massage;
    }
}
