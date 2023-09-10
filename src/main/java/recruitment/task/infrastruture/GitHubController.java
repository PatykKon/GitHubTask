package recruitment.task.infrastruture;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recruitment.task.application.Service;
import recruitment.task.application.dto.ResponseRepositories;

import java.util.List;

@RestController
@AllArgsConstructor
public class GitHubController {

    private final Service service;

     @GetMapping("/{login}/repo")
     public ResponseEntity<List<ResponseRepositories>> getRepositoriesWithOutForks(@PathVariable String login) {

         List<ResponseRepositories> repositories = service.getRepositoriesWithOutForks(login);
         return new ResponseEntity<>(repositories, HttpStatus.OK);

     }
}
