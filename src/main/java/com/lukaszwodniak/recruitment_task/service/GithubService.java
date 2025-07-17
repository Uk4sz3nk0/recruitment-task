package com.lukaszwodniak.recruitment_task.service;

import com.lukaszwodniak.recruitment_task.client.GithubClient;
import com.lukaszwodniak.recruitment_task.dto.BranchDto;
import com.lukaszwodniak.recruitment_task.dto.GitBranchResponseDto;
import com.lukaszwodniak.recruitment_task.dto.GitReposResponseDto;
import com.lukaszwodniak.recruitment_task.dto.RepoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GithubService {

    private final GithubClient client;

    public GithubService() {
        client = new GithubClient();
    }

    public List<GitReposResponseDto> getUserRepos(String username) {
        var repos = client.getUserRepositories(username);
        return repos.entrySet()
                .stream()
                .map(repoEntry -> {
                    RepoDto repo = repoEntry.getKey();
                    List<BranchDto> branches = repoEntry.getValue();
                    List<GitBranchResponseDto> mappedBranches = branches.stream()
                            .map(this::mapBranch)
                            .toList();
                    return mapRepo(repo, mappedBranches);
                })
                .toList();
    }

    private GitBranchResponseDto mapBranch(BranchDto branch) {
        return new GitBranchResponseDto(branch.name(), branch.commit()
                .sha());
    }

    private GitReposResponseDto mapRepo(RepoDto repo, List<GitBranchResponseDto> branches) {
        return new GitReposResponseDto(repo.name(), repo.owner()
                .login(), branches);
    }
}
