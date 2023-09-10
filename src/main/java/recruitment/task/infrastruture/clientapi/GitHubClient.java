package recruitment.task.infrastruture.clientapi;


import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import recruitment.task.application.exceptions.AppRuntimeException;
import recruitment.task.infrastruture.clientapi.dto.branch.BranchDTO;
import recruitment.task.infrastruture.clientapi.dto.repo.RepoDTO;

import java.util.ArrayList;
import java.util.List;

import static recruitment.task.application.exceptions.ErrorType.E404;

@Component
class GitHubClient {

    private static final String GITHUB_URL_USERS = "/users/";
    private static final String GITHUB_URL_REPOS = "/repos/";


    private final WebClient webClient;

    @Autowired
    GitHubClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<RepoDTO>> repoDate(String login) {

        return webClient
                .get()
                .uri(GITHUB_URL_USERS+"/"+login+"/repos")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> {
                    if(clientResponse.statusCode()== HttpStatus.NOT_FOUND){
                        return Mono.error(new AppRuntimeException(E404));
                    }
                    return Mono.error(new RuntimeException());
                })
                .bodyToFlux(RepoDTO.class)
                .collectList();

    }

    public Mono<List<BranchDTO>> branchDate(String login,String nameRepo) {
        return webClient
                .get()
                .uri(GITHUB_URL_REPOS+"/"+login+"/"+nameRepo+"/branches")
                .retrieve()
                .bodyToFlux(BranchDTO.class)
                .collectList();
    }

    List<RepoDTO> getRepositories(String login) {
        Mono<List<RepoDTO>> repoMono = repoDate(login);
        List<RepoDTO> repoList = repoMono.block();
        return repoList;
    }
    List<BranchDTO> getBranches(String login, String nameRepo){
        Mono<List<BranchDTO>> branches = branchDate(login,nameRepo);
        List<BranchDTO> branchDTOS = branches.block();
        return branchDTOS;
    }
}
