package recruitment.task.application.dto;
import java.util.List;

public record ResponseRepositories(
        String repoName,
        String ownerName,
        List<ResponseBranch>branchList
) {
}
