![logo](src/main/resources/static/img/logo.jpg)
  
# :pushpin: 소셜 미디어 통합 Feed 서비스 MOA
> **소셜 미디어 Feed 여기 다 MOA!** 
> 
> 복수의 SNS에 게시된 게시물 중 유저의 해시태그가 포함된 게시물들을 하나의 서비스에서 확인할 수 있는 통합 Feed 어플리케이션
  
## <포트폴리오 순서>
1. [제작기간 및 참여인원](#1제작기간-및-참여인원) 
2. [개요](#2개요)  
3. [사용기술](#3사용기술)  
4. [ERD 설계](#4erd-설계)
5. [본인 구현 기능](#5본인-구현-기능)
6. [중요 트러블 슈팅](#6중요-트러블-슈팅)
7. [기타 트러블 슈팅](#7기타-트러블-슈팅)
  
- [Git commit 메시지 컨벤션](#Git-commit-메시지-컨벤션)
- [API Reference](#api-reference)
  
## 1.제작기간 및 참여인원
- 제작기간 : 2023년 10월 25일 ~ 10월 30일
- 팀 프로젝트 (총 5명 / 모두 Java, Spring 기반 백엔드 개발자)
  
## 2.개요
  
본 서비스는 유저 계정의 해시태그(”#dani”) 를 기반으로 인스타그램, 스레드, 페이스북, 트위터 등 복수의 SNS에 게시된 게시물 중 유저의 해시태그가 포함된 게시물들을 하나의 서비스에서 확인할 수 있는 통합 Feed 어플리케이션 입니다.
  
## 3.사용기술
- 언어 및 프레임워크 : ![Static Badge](https://img.shields.io/badge/Java-17-F58232) ![Static Badge](https://img.shields.io/badge/Spring_boot-3.0.12-6CB52C)<br/>
- 데이터베이스 : ![Static Badge](https://img.shields.io/badge/h2-2.1.214-1021FF) ![Static Badge](https://img.shields.io/badge/Spring_Data_JPA-3.1.1-80E96E)<br/>
- 배포 : ![Static Badge](https://img.shields.io/badge/Gradle-8.3-02303A) <br/>
  
## 4.ERD 설계
> ![coverage](src/main/resources/static/img/table.png) <br/>
사용자는 자신의 해시태그를 등록하고, 해당 해시태그를 기반으로 게시물을 조회할 수 있습니다.
    
## 5.본인 구현 기능

- 사용자(Member), 팀(Team), 작업상황(Progress), 티켓(Ticket) 대상으로 16개의 API 구현
  
<details>
<summary>1st phase (2023.11.16~11.24) ; 사용자 생성 및 JWT를 통한 인증, (프로젝트 진행할) 팀 생성 및 구성, 작업상황 및 티켓 구현을 통해  서비스 구현</summary>

1. 사용자 회원가입 및 <u>**JWT**를 통한 사용자 인증</u> 구현
    - 사용자 로그인과 동시에 액세스 토큰 발급 후, API 요청 헤더에 담아 사용자 인증
    - 액세스 토큰 유효시간(30분) 만료 시, 토큰 타입을 “refresh”로 입력하여 유효기간이 1주일로 연장된 새로운 액세스 토큰 발급
  
  
2. 팀 생성, 팀원 초대를 통한 팀 구성
    - <u>**팀 생성**</u> ; Team 및 TeamSetting 엔티티를 통해 팀의 정보를 구성
        - Member, Team의 관계는 다수 Member가 다수 Team의 구성원이 될 수 있는 N : M이므로  → TeamSetting 엔티티를 생성하여 Member, Team과의 연관관계를 N : 1, M : 1 로 설정
            - 관계형 데이터베이스에서 Member, Team 테이블은 연결할 TeamSetting 테이블을 엔티티로 생성한다.
        - TeamSetting 엔티티는 enum 타입의 InviteStatus를 설정하여 팀과 관련된 사용자의 권한을 4가지로 설정하는 역할을 수행
            - INVITING ; 팀을 생성한 사용자에게만 주어짐 (팀장 권한)
            - RECEIVED ; 팀장이 보낸 팀으로의 초대를 받은 사용자
            - ACCEPTED ; 초대를 수락하여 팀원이 된 사용자
            - REFUSED ; 초대를 거절한 사용자
        - 팀 생성 과정 중 팀명 등의 기본 정보는 Team 타입으로 저장되지만, 해당 팀의 구성원 정보는 TeamSetting 타입으로 저장됨.  
    - <u>**팀원으로 초대 및 수락/거절**</u> ; TeamSetting의 InviteStatus 필드(enum타입) 값에 따라 팀원을 초대, 초대장 수신, 초대를 수락, 초대를 거절한 팀원의 상태를 구분. 따라서 초대와 관련된 DB테이블을 별도로 만들 필요가 없음.
        - 팀장만이 다른 사용자를 팀원으로 초대할 수 있음
            - TeamSetting에서 InviteStatus.INVITING인 사용자만 초대가 가능하도록 구현
        - 특정 사용자가 특정 팀으로의 초대에 대해 수락 또는 거절
            - TeamSetting에서 InviteStatus.RECEIVED인 사용자만 초대에 대해 수락 또는 거절을 할 수 있음
            - InviteStatus는 수락 시 ACCEPTED, 거절 시 REFUSED로 변경됨
        - (본 프로젝트는 REST API 구현하므로, InviteStatus에 따라 초대와 관련된 액션을 구현하는 것은 프런트엔드에서 처리 가능)
  
  
3. 진행상황(Progress) / 티켓 (Ticket) ; 생성, 조회, 수정, 삭제
    - 팀 내부에 생성할 수 있는 진행상황(Progress)는 칸반보드에서 column과 같은 역할
    - 진행상황 내부에 생성할 수 있는 티켓(Ticket)은 칸반보드에서의 개별작업과 같은 역할
    - 진행상황/티켓 생성과 동시에 순서가 정해지며, 순서는 변경이 가능
  
  
4. 서버 구동과 동시에 dummy data 생성
    - ApplicationRunner 인터페이스를 구현한 DummyDataLoader 클래스, dummy로 생성되어야 할 객체의 정보들을 입력한 DummyDataService 클래스를 통해 Member, Team, TeamSetting, Progress, Ticket의 dummy data가 각각 20, 4, 15, 2, 5개가 서버 구동과 동시에 생성됨
</details>

<details>
<summary>2nd phase (2023.12. 1~현재) ; 메서드 모듈화를 통해 코드 중복 제거, Test code 작성, Swagger 도입</summary>
  
1. 메서드 모듈화를 통해 코드 중복 제거  
    - 1st phase에서 진행상황(Progress), 티켓(Ticket)의 Service 클래스에 작성된 메서드들은 내부에는 많은 중복이 존재. 이를 별도의 메서드로 작성하여 중복 제거.  
        - 중복되는 로직 : (1) 팀, 진행상황, 티켓의 존재 여부 (2) 로그인한 사용자의 팀내에서의 역할  
        - 중복 로직을 별도의 메서드로 작성하여 제거  
    - 개선 결과 : 총 9개의 비즈니스 로직 담당 메서드 별 최소 5라인, 최대 13라인의 코드 감소 및 가독성 개선  
</details>
  
## 6.중요 트러블 슈팅

1. member, team 의 다대다 관계 (1) teamSetting 연결테이블로 풀어보기 (<a href="https://github.com/upqnu/prj_uc/wiki/member,%20team%20%EC%9D%98%20%EB%8B%A4%EB%8C%80%EB%8B%A4%20%EA%B4%80%EA%B3%84%20(1)%20teamSetting%20%EC%97%B0%EA%B2%B0%ED%85%8C%EC%9D%B4%EB%B8%94%EB%A1%9C%20%ED%92%80%EC%96%B4%EB%B3%B4%EA%B8%B0">클릭 & detail 확인</a>)
  
2. member, team 의 다대다 관계 (2) teamSetting으로 초대 관련 기능을 쉽게 구현 (<a href="https://github.com/upqnu/prj_uc/wiki/member,%20team%20%EC%9D%98%20%EB%8B%A4%EB%8C%80%EB%8B%A4%20%EA%B4%80%EA%B3%84%20(2)%20teamSetting%EC%9C%BC%EB%A1%9C%20%EC%B4%88%EB%8C%80%20%EA%B4%80%EB%A0%A8%20%EA%B8%B0%EB%8A%A5%EC%9D%84%20%EC%89%BD%EA%B2%8C%20%EA%B5%AC%ED%98%84">클릭 & detail 확인</a>)
  
## 7.기타 트러블 슈팅
  
  
  
  
---
  
## Git commit 메시지 컨벤션
- `Feat` : 새로운 기능 추가
- `Fix` : 기능 개선 또는 수정, 버그 수정
- `Docs` : 문서 수정
- `Style` : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- `Refactor` : 코드 리펙토링
- `Test` : 테스트 코드, 리펙토링 테스트 코드 추가
- `Chore` : 빌드 업무 수정, 패키지 매니저 수정
  
## API Reference
  
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
  
