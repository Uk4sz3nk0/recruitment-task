package com.lukaszwodniak.recruitment_task.dto;

import java.util.List;

public record GitReposResponseDto(String repositoryName, String ownerLogin, List<GitBranchResponseDto> branches) {
}
