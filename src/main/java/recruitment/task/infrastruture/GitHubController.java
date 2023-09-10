package recruitment.task.infrastruture;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import recruitment.task.application.GetResponseRepositoriesService;
import recruitment.task.application.dto.ResponseRepositories;

import java.util.List;

@RestController
@AllArgsConstructor
public class GitHubController {

    private final GetResponseRepositoriesService service;

     @GetMapping("/{login}/repo")
     public List<ResponseRepositories> getRepositoriesWithOutForks(@PathVariable String login) {
         return service.getRepositoriesWithOutForks(login);

     }
}
