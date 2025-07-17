package com.lukaszwodniak.recruitment_task;

import com.lukaszwodniak.recruitment_task.dto.GitReposResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecruitmentTaskApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnUserNotForkedReposWithBranchesAndCommitsSha() throws Exception {
        // Given
        String username = "octocat";
        String requestURL = String.format("/github/%s/repos", username);

        // When
        ResponseEntity<List<GitReposResponseDto>> reposResponse = restTemplate.exchange(
                requestURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        // Then
        assertThat(reposResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<GitReposResponseDto> repos = reposResponse.getBody();
        assertThat(repos).isNotNull();

        // Checking first element
        assertThat(repos.size()).isNotEqualTo(0);
        assertThat(repos.getFirst()).isNotNull();
        GitReposResponseDto repo = repos.getFirst();
        assertThat(repo.ownerLogin()).isNotBlank();
        assertThat(repo.repositoryName()).isNotBlank();
        assertThat(repo.branches()).isNotNull();
        assertThat(repo.branches()
                .size()).isNotEqualTo(0);
        assertThat(repo.ownerLogin()).isEqualTo(username);

    }

}
