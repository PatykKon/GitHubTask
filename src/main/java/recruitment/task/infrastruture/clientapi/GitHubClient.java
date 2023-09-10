package recruitment.task.infrastruture.clientapi;


import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import recruitment.task.infrastruture.clientapi.dto.branch.BranchDTO;
import recruitment.task.infrastruture.clientapi.dto.repo.RepoDTO;
import recruitment.task.application.exceptions.AppRuntimeException;

import java.util.Arrays;
import java.util.List;

import static recruitment.task.application.exceptions.ErrorType.E404;

@Component
class GitHubClient {

    private static final String GITHUB_URL_USERS = "https://api.github.com/users/";
    private static final String GITHUB_URL_REPOS = "https://api.github.com/repos/";

    private RestTemplate restTemplate = new RestTemplate();

    List<RepoDTO> getRepositories(String login){

        try {
            List<RepoDTO> repos = callUsers("/"+login+"/repos",RepoDTO[].class,login);
            return repos;

        } catch (HttpClientErrorException.NotFound e) {

            throw new AppRuntimeException(E404);
        }

    }
    List<BranchDTO> getBranches(String login, String nameRepo){
        List<BranchDTO> branches = callRepos("/"+login+"/"+nameRepo+"/branches",BranchDTO[].class,login,nameRepo);
        return branches;
    }


    private <T> List<T> callUsers(String url, Class<T[]> responseType, Object... objects) {
        ResponseEntity<T[]>response  =restTemplate.exchange(GITHUB_URL_USERS + url, HttpMethod.GET,null, responseType);
        return Arrays.asList(response.getBody());
    }
    private <T> List<T> callRepos(String url, Class<T[]> responseType, Object... objects) {
        ResponseEntity<T[]>response  =restTemplate.exchange(GITHUB_URL_REPOS + url, HttpMethod.GET,null, responseType);
        return Arrays.asList(response.getBody());
    }
}
