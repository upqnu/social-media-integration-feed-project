![logo](https://static.wanted.co.kr/images/events/3178/58ac3248.jpg)

# 소셜 미디어 통합 Feed 서비스 MOA
**소셜 미디어 Feed 여기 다 MOA!**

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
본 서비스는 유저 계정의 해시태그(”#dani”) 를 기반으로 `인스타그램`, `스레드`, `페이스북`, `트위터` 등 복수의 SNS에 게시된 게시물 중 유저의 해시태그가 포함된 게시물들을 하나의 서비스에서 확인할 수 있는 통합 Feed 어플리케이션 입니다. <br/>

## Skils
언어 및 프레임워크: ![Static Badge](https://img.shields.io/badge/Java-17-Green) ![Static Badge](https://img.shields.io/badge/Spring_boot-REST-Green)<br/>
데이터베이스 및 테스트: ![Static Badge](https://img.shields.io/badge/h2-2.1.214-blue) ![Static Badge](https://img.shields.io/badge/JUnit-Green) <br/>
배포 : ![Static Badge](https://img.shields.io/badge/Gradle-039BC6) <br/>

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

> Coverage ScreenShot ![Static Badge](https://img.shields.io/badge/Test_Passed-7/7-green)<br/>
![coverage](src/main/resources/static/img/test.png)

## API Reference
### User
<details>
<summary>회원가입 - click</summary>

#### Request
```javascript
  GET /users/sign-up
```

#### Response
```http
    HTTP/1.1 201
    Content-Type: application/json

    {
        "id": 1,
        "email": "test@test.com",
        "hashtag": "#test"
    }
```
</details>
<details>
<summary>로그인 - click</summary>

#### Request
```javascript
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Response
```http
    HTTP/1.1 200
    Content-Type: application/json

    [{
        "id": 10,
        "name": "shirt",
        "color": "red",
        "price": "$23"
    },...
    ]
```
</details>
<details>
<summary> 유저 회원가입 승인 - click</summary>

#### Request
```javascript
  GET /users/{id}/approval
```

| Parameter | Type   | Description             |
|:----------|:-------|:------------------------|
| `id`      | `Long` | **Required**. User's ID |

#### Response
```http
    HTTP/1.1 204
    Content-Type: application/json
```
</details>

### Post

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

## 프로젝트 진행 및 이슈 관리
[![Github Project](https://img.shields.io/badge/Github-%23000000.svg?style=for-the-badge&logo=Github&logoColor=white)](https://github.com/orgs/Team-Enigma23/projects/2)

## 구현과정(설계 및 의도)
![coverage](src/main/resources/static/img/table.png) <br/>
(노션, 블로그 등의 페이지로 안내 가능)
<details>
<summary>작성 중..</summary>

- 의존성 문제
    1. Press `Ctrl` + `f` on your keyboard, to bring out the search modal.
    2. Enter the name of the badge you need.
    3. Copy the appropriate `![Name](link)` element and paste it in your Markdown file (e.g. README.md)
- 00가 00 하는 문제

</details>

<details>
<summary>RESTful API 설계 - click</summary>

- 의존성 문제
    1. Press `Ctrl` + `f` on your keyboard, to bring out the search modal.
    2. Enter the name of the badge you need.
    3. Copy the appropriate `![Name](link)` element and paste it in your Markdown file (e.g. README.md)
- 00가 00 하는 문제

</details>

## TIL 및 회고
- [Dirty Checking에 대한 짧은 고찰](https://diligent-mangosteen-06d.notion.site/Dirty-Checking-e16ca9140ef24613b3766bee37156957?pvs=4)
- [Security 3.0에서 주의할 점](https://diligent-mangosteen-06d.notion.site/Security-3-0-3a70cd637c3a472dbfefe4faadd793ca?pvs=4)
- [jjwt 0.9와 jjwt 0.10 이상 버전 차이](https://chickencoc.tistory.com/21)
- [QueryDsl](https://gabang2.notion.site/QueryDsl-43b0901997104bb89c96bcc4d9da78ed)

## Authors
- [U-jinLee](https://github.com/U-jinLee)
- [upqnu](https://github.com/upqnu)
- [gabang2](https://github.com/gabang2)
- [chickencoc](https://github.com/chickencoc)