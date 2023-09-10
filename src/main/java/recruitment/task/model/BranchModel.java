package recruitment.task.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BranchModel {
    String name;
    String lastCommitSha;

}
