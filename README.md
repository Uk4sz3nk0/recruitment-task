# Recruitment Task - Simple GitHub repositories API

This project exposes a REST API that lists all non-fork repositories of a given GitHub user, 
including each repository branches and their last commit SHA.

## Endpoint
#### `GET /repositories/{username}`

Example Request
```curl
   GET http://localhost:8080/github/octocat/repos
```

Example response
```json
[
  {
    "repositoryName": "octocat.github.io",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "gh-pages",
        "lastCommitSha": "c0e4a095428f36b81f0bd4239d353f71918cbef3"
      },
      {
        "name": "master",
        "lastCommitSha": "3a9796cf19902af0f7e677391b340f1ae4128433"
      }
    ]
  }
]
```

## Error handling
If user not exists, API will return:
```json
{
  "status": 404,
  "message": "User with given username not exists in github"
}
```

## Tests
Run test using:
```bash
mvn test
```
Includes:
 * One integration test covering happy path