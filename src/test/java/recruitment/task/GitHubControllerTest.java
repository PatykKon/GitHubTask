package recruitment.task;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import recruitment.task.infrastruture.GitHubController;
import recruitment.task.application.Service;
import recruitment.task.application.dto.ResponseRepositories;
import recruitment.task.application.exceptions.AppRuntimeException;
import recruitment.task.application.exceptions.ErrorType;


import static org.mockito.Mockito.*;

import java.util.List;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GitHubController.class)
public class GitHubControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Service service;


    @Test
    public void testGetRepositoriesWithOutFork() throws Exception {
        //given
        String login = "userTest";
        List<ResponseRepositories> repositories = new ArrayList<>();

        //when
        when(service.getRepositoriesWithOutForks(login)).thenReturn(repositories);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/{login}/repo", login)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void returnStatusNoFound_WhenUserDoesNotExist() throws Exception {
        //given
        String login = "userTest";

        //when
        Mockito.when(service.getRepositoriesWithOutForks(login)).thenThrow(new AppRuntimeException(ErrorType.E404));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/{login}/repo", login)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
