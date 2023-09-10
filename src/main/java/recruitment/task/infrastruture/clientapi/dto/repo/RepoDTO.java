package recruitment.task.infrastruture.clientapi.dto.repo;

public record RepoDTO(
        String name,
        UserDTO owner,
        boolean fork
) {
}
