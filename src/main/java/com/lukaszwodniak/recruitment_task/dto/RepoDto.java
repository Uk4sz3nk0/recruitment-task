package com.lukaszwodniak.recruitment_task.dto;

public record RepoDto(long id, String name, boolean fork, OwnerDto owner) {
}
