package recruitment.task.infrastruture.clientapi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import recruitment.task.infrastruture.clientapi.dto.branch.BranchDTO;
import recruitment.task.infrastruture.clientapi.dto.repo.RepoDTO;
import recruitment.task.model.BranchModel;
import recruitment.task.model.RepoModel;

import java.util.ArrayList;

@Component
@AllArgsConstructor

public class GitHubMapper {
    private final GitHubClient gitHubClient;

    public RepoModel mapToRepo(RepoDTO repoDTO){
        RepoModel repoModel = RepoModel.builder()
                .ownerName(repoDTO.owner().login())
                .repoName(repoDTO.name())
                .isFork(repoDTO.fork())
                .branchList(new ArrayList<>())
                .build();
        return repoModel;
    }

    public BranchModel mapToBranch(BranchDTO branchDTO){
        BranchModel branchModel = BranchModel.builder()
                .lastCommitSha(branchDTO.commit().sha())
                .name(branchDTO.name())
                .build();
        return branchModel;
    }


}


