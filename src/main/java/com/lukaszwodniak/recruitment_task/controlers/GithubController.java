package com.lukaszwodniak.recruitment_task.controlers;

import com.lukaszwodniak.recruitment_task.dto.ErrorResponseDto;
import com.lukaszwodniak.recruitment_task.service.GithubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubController {

    private final GithubService githubService;

    public GithubController() {
        githubService = new GithubService();
    }

    @GetMapping("/github/{username}/repos")
    ResponseEntity<?> getUserRepos(@PathVariable("username") String username) {
        var repos = githubService.getUserRepos(username);
        try {
            return ResponseEntity.ok(repos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto(0, ""));
        }
    }
}
