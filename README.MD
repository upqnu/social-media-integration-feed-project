![logo](src/main/resources/static/img/logo.jpg)

# 소셜 미디어 통합 Feed 서비스 MOA
## Table of Contents
- [개요](#개요)
- [Skils](#skils)
- [Installation](#Installation)
- [Running Tests](#running-tests)
- [API Reference](#api-reference)
- [프로젝트 진행 및 이슈 관리](#프로젝트-진행-및-이슈-관리)
- [구현과정(설계 및 의도)](#구현과정(설계-및-의도))
- [TIL 및 회고](#til-및-회고)
- [Authors](#authors)

## 개요
**소셜 미디어 Feed 여기 다 MOA!** 본 서비스는 유저 계정의 해시태그(”#dani”) 를 기반으로 `인스타그램`, `스레드`, `페이스북`, `트위터` 등 복수의 SNS에 게시된 게시물 중 유저의 해시태그가 포함된 게시물들을 하나의 서비스에서 확인할 수 있는 통합 Feed 어플리케이션 입니다. <br/>

## Skils
언어 및 프레임워크: ![Static Badge](https://img.shields.io/badge/Java-17-Green) ![Static Badge](https://img.shields.io/badge/Spring_boot-REST-Green)<br/>
데이터베이스 및 테스트: ![Static Badge](https://img.shields.io/badge/h2-2.1.214-blue) ![Static Badge](https://img.shields.io/badge/JUnit-Green) <br/>
배포 : ![Static Badge](https://img.shields.io/badge/Gradle-039BC6) <br/>
ETC: ![Static Badge](https://img.shields.io/badge/SMTP-039BC6)

## Installation
Install MOA project with gradle
```bash
./gradlew bootjar
./gradlew bootrun
```

## Running Tests
To run tests, run the following command
```bash
  ./gradlew test
```
>![Static Badge](https://img.shields.io/badge/Test_Passed-23/23-green)<br/>
> ![coverage](src/main/resources/static/img/test.png)

## API Reference
### User
<details>
<summary>회원가입 - click</summary>

#### Request
```javascript
  GET /users/sign-up
```
```http
Content-Type: application/json

{
  "username":"test1234",
  "email":"yoojinlee.dev@gmail.com",
  "password":"1q2w3e4r!",
  "hashtag":"test"
}
```
#### Response
```http
HTTP/1.1 201
Content-Type: application/json

{
  "id": 1,
  "username": "test1234",
  "email": "yoojinlee.dev@gmail.com",
  "hashtag": "test"
}
```
</details>

<details>
<summary>로그인 - click</summary>

#### Request
```javascript
  GET /users/sign-in
```
```http
Content-Type: application/json

{
    "username": "tester1234",
    "password": "1q2w3e4r!",
}
```

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json

{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzNCIsImV4cCI6MTY5ODc3MjE5OSwiaWF0IjoxNjk4Njg1Nzk5LCJlbWFpbCI6Inlvb2ppbmxlZS5kZXZAZ21haWwuY29tIiwidXNlcklkIjoxLCJhdXRoIjoiUk9MRV9NRU1CRVIifQ.EFjPkcgIjepNwh0qel9H9ZQMbcJ2dZ-fBl9sRuUG5tU"
}
```
</details>
<details>
<summary> 유저 회원가입 승인 - click</summary>

#### Request
```javascript
  GET /users/{id}/approval
```

| Path | Type   | Description             |
|:-----|:-------|:------------------------|
| `id` | `Long` | **Required**. User's ID |

#### Response
```http
    HTTP/1.1 204
    Content-Type: application/json
```
</details>

### Post
<details>
<summary> 게시물 불러오기 - click</summary>

#### Request
```javascript
  GET /posts
```

| Parameter    | Type     | Description                                |
|:-------------|:---------|:-------------------------------------------|
| `hashtag`    | `String` |                                            |
| `page`       | `int`    |                |
| `page_count` | `int`    |  |
| `createdAt`  | `String` |  |
| `desc`       | `String` |  |


#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json
    
    {
    "content": [
        {
            "id": 1,
            "contentId": "fb1",
            "type": "FACEBOOK",
            "title": "페북 피드_1",
            "content": "good #dev #java",
            "viewCount": 2,
            "likeCount": 0,
            "shareCount": 3,
            "createdAt": "2023-10-31T02:00:30.682465",
            "updatedAt": "2023-10-31T02:00:30.682465"
        },
        //...
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 5,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalElements": 20,
    "totalPages": 4,
    "size": 5,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 5,
    "empty": false
}
```
</details>

<details>
<summary> 게시물 상세 - click</summary>

#### Request
```javascript
  GET /posts/{postId}
```

| Path | Type   | Description             |
|:-----|:-------|:------------------------|
| `id` | `Long` | **Required**. User's ID |

#### Response
```http
HTTP/1.1 200
Content-Type: application/json

{
  "id": 0,
  "contentId": "string",
  "type": "FACEBOOK",
  "title": "string",
  "content": "string",
  "viewCount": 0,
  "likeCount": 0,
  "shareCount": 0,
  "createdAt": "2023-11-02T03:02:34.687Z",
  "updatedAt": "2023-11-02T03:02:34.687Z"
}
```
</details>

<details>
<summary> 게시물 좋아요 - click</summary>

#### Request
```javascript
  GET /posts/{postId}/likes
```

| Path | Type   | Description             |
|:-----|:-------|:------------------------|
| `id` | `Long` | **Required**. User's ID |

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json
```
</details>

<details>
<summary> 게시물 공유 - click</summary>

#### Request
```javascript
  GET /posts/{postId}/shares
```

| Path | Type   | Description             |
|:-----|:-------|:------------------------|
| `id` | `Long` | **Required**. User's ID |

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json
```
</details>

### Hashtag
<details>
<summary>Hot Hashtag 찾기 - click</summary>

#### Request
```javascript
  GET /hashtags/hot
```

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json

    {
        "hashtagName": "#test",
        "count": 15
    }
```
</details>

### Statisitcs
<details>
<summary>통계 - click</summary>

#### Request
```javascript
  GET /statistics
```
| Parameter  | Type      | Description                                |
|:-----------|:----------|:-------------------------------------------|
| `hashtag`  | `String`  |                                            |
| `type`     | `String`  | **Required**.`date`, `hour`                |
| `start`    | `date`    | 2023-10-01 과 같이 데이트 형식이며 조회 기준 시작일을 의미합니다. |
| `end`      | `date`    | 2023-10-25 과 같이 데이트 형식이며 조회 기준 시작일을 의미합니다. |
| `value`    | `String`  | count, view_count, like_count, share_count |

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json

    {
        "time": "#test",
        "count": 15
    }
```
</details>

## 프로젝트 진행 및 이슈 관리
프로젝트의 Issue, PR 진행은 아래 버튼을 눌러 확인할 수 있습니다.
<br>
[![Github Project](https://img.shields.io/badge/Github-%23000000.svg?style=for-the-badge&logo=Github&logoColor=white)](https://github.com/Team-Enigma23/location-based-gourmet-recommendation-project)
[![Github Project](https://img.shields.io/badge/Github_Repository-%23000000.svg?style=for-the-badge&logo=Github&logoColor=white)](https://github.com/orgs/Team-Enigma23/projects/2)

## 구현과정(설계 및 의도)
![coverage](src/main/resources/static/img/table.png) <br/>
사용자는 자신의 해시태그를 등록하고, 해당 해시태그를 기반으로 게시물을 조회할 수 있습니다.

<details>
<summary>JWT 유저인증 - click</summary>
- JWT를 활용한 유저 인증을 구현했습니다.<br>
- SMTP를 활용해 유저의 이메일에 유저의 인증키를 전송하는 기능을 구현했습니다.
</details>
<details>
<summary>게시물 확인 - click</summary>
- 게시물 확인 및 상세, 좋아요, 공유 기능을 구현했습니다.
</details>
<details>
<summary>통계 - click</summary>
- 유저, 또는 브랜드 해시태그의 통계를 확인할 수 있습니다.<br>
</details>
<details>
<summary>HOT Hashtag - click</summary>
- 최근 3시간 이내에 게시물에서 가장 많이 사용된 해시태그를 조회할 수 있습니다.<br>
</details>
<details>
<summary>RESTful API 설계 - click</summary>
- Open API를 활용한 Self-descriptive Message 충족<br>
  - "/swagger-ui"에서 해당 내용 확인 가능

</details>

## 이슈관리
- [SMTP Email 사용 속도 개선](https://diligent-mangosteen-06d.notion.site/SMTP-Email-57124080d4cb4e3383a014df0af2c95f?pvs=4)

## TIL 및 회고
- [Dirty Checking에 대한 짧은 고찰](https://diligent-mangosteen-06d.notion.site/Dirty-Checking-e16ca9140ef24613b3766bee37156957?pvs=4)
- [Security 3.0에서 주의할 점](https://diligent-mangosteen-06d.notion.site/Security-3-0-3a70cd637c3a472dbfefe4faadd793ca?pvs=4)
- [jjwt 0.9와 jjwt 0.10 이상 버전 차이](https://chickencoc.tistory.com/21)
- [QueryDsl](https://gabang2.notion.site/QueryDsl-43b0901997104bb89c96bcc4d9da78ed)
- [GitHub에서 Pull Request의 Merge 종류 차이](https://chickencoc.tistory.com/26)

## Authors
- [U-jinLee](https://github.com/U-jinLee)
- [upqnu](https://github.com/upqnu)
- [gabang2](https://github.com/gabang2)
- [chickencoc](https://github.com/chickencoc)