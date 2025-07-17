package com.lukaszwodniak.recruitment_task.controlers;

import com.lukaszwodniak.recruitment_task.dto.GitReposResponseDto;
import com.lukaszwodniak.recruitment_task.service.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GithubController {

    private final GithubService githubService;

    public GithubController() {
        githubService = new GithubService();
    }

    @GetMapping("/github/{username}/repos")
    ResponseEntity<List<GitReposResponseDto>> getUserRepos(@PathVariable("username") String username) {
        var repos = githubService.getUserRepos(username);
        return ResponseEntity.ok(repos);
    }
}
