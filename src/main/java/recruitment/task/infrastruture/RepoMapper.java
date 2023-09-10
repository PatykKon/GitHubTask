package recruitment.task.infrastruture;

import org.springframework.stereotype.Component;
import recruitment.task.application.dto.ResponseBranch;
import recruitment.task.application.dto.ResponseRepositories;
import recruitment.task.model.BranchModel;
import recruitment.task.model.RepoModel;

import java.util.List;

@Component
public class RepoMapper {

    public ResponseBranch mapToResponseBranch(BranchModel branchModel){

        ResponseBranch responseBranch = new ResponseBranch(
                branchModel.getName(),
                branchModel.getLastCommitSha());

        return responseBranch;
    }
    public ResponseRepositories mapToResponseRepositories(RepoModel repoModel){

        List<ResponseBranch> responseBranches = repoModel.getBranchList()
                .stream()
                .map(this::mapToResponseBranch)
                .toList();

        ResponseRepositories repositories = new ResponseRepositories(
                repoModel.getRepoName(),
                repoModel.getOwnerName(),
                responseBranches);

        return repositories;
    }
}
