package recruitment.task.model;

import lombok.*;

import java.util.List;


@Getter
@Builder
public class RepoModel {
    String repoName;
    private String ownerName;
    private boolean isFork;
    private List<BranchModel> branchList;

    public void addBranches(List<BranchModel> branchList){
        this.branchList.addAll(branchList);
    }
}


