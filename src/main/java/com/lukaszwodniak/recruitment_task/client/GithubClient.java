package com.lukaszwodniak.recruitment_task.client;

import com.lukaszwodniak.recruitment_task.dto.BranchDto;
import com.lukaszwodniak.recruitment_task.dto.RepoDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class GithubClient {

    private final RestTemplate restTemplate;

    public GithubClient() {
        restTemplate = new RestTemplate();
    }

    public Map<RepoDto, List<BranchDto>> getUserRepositories(String username) {
        List<RepoDto> repos = getRepos(username);
        return repos.stream()
                .filter(repo -> !repo.fork())
                .collect(Collectors.toMap(repo -> repo, repo -> getRepoBranches(username, repo.name())));
    }

    private List<RepoDto> getRepos(String username) {
        var url = String.format("https://api.github.com/users/%s/repos", username);
        return handleClientGetRequest(url, new ParameterizedTypeReference<>() {
        });
    }

    private List<BranchDto> getRepoBranches(String username, String repo) {
        var url = String.format("https://api.github.com/repos/%s/%s/branches", username, repo);
        return handleClientGetRequest(url, new ParameterizedTypeReference<>() {
        });
    }

    private <T> List<T> handleClientGetRequest(String url, ParameterizedTypeReference<List<T>> responseType) {
        var response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        return response.getBody();
    }
}
