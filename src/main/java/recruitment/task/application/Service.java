package recruitment.task.application;

import lombok.AllArgsConstructor;
import recruitment.task.application.dto.ResponseRepositories;
import recruitment.task.infrastruture.RepoMapper;
import recruitment.task.infrastruture.clientapi.GitService;
import recruitment.task.model.RepoModel;

import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {

    private final GitService gitService;
    private final RepoMapper repoMapper;


    public List<ResponseRepositories> getRepositoriesWithOutForks(String login){

        List<RepoModel> repositoriesWithOutForks = gitService.getRepositoriesWithOutForks(login);

        List<ResponseRepositories> responseRepositories = repositoriesWithOutForks.stream().map(repoMapper::mapToResponseRepositories).toList();

        return responseRepositories;
    }

}
