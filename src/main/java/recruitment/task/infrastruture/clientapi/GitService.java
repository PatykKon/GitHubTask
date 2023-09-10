package recruitment.task.infrastruture.clientapi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import recruitment.task.infrastruture.clientapi.dto.branch.BranchDTO;
import recruitment.task.infrastruture.clientapi.dto.repo.RepoDTO;
import recruitment.task.model.BranchModel;
import recruitment.task.model.RepoModel;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GitService {
    private final GitHubMapper gitHubMapper;
    private final GitHubClient gitHubClient;

    public List<RepoModel> getRepositoriesWithOutForks(String login){

        List<RepoDTO> repoDTOS = gitHubClient.getRepositories(login);
        List<RepoModel> repositoriesWithOutForks = new ArrayList<>();

        for(RepoDTO repoDTO:repoDTOS){

            List<BranchModel> branchModels = getBranches(login,repoDTO.name());
            RepoModel repoModel = gitHubMapper.mapToRepo(repoDTO);
            repoModel.addBranches(branchModels);

            if(!repoModel.isFork()){
                repositoriesWithOutForks.add(repoModel);
            }
        }
        return repositoriesWithOutForks;
    }
    private List<BranchModel> getBranches(String login,String name){
        List<BranchDTO> branchDTOS = gitHubClient.getBranches(login,name);
        List<BranchModel> branchModels = branchDTOS.stream()
                .map(gitHubMapper::mapToBranch)
                .toList();

        return branchModels;
    }
}
