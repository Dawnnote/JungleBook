# 📚Project name : JungleBook



**Springboot를 활용한 중고 도서 거래 사이트**

![KakaoTalk_20231108_165455679](https://github.com/Dawnnote/JungleBook/assets/148731548/6a33492b-6761-42ae-809e-dcc065f32cc5)

## 💁🏻‍♀️프로젝트 소개


JungleBook은 번개장터를 모티브로 제작된 중고 도서 거래 사이트입니다. 팝니다/삽니다로 구분되어 있는 게시판을 통해 중고 도서 판매는 물론, 구매를 위한 소통도 가능합니다. 판매 물품에 대한 문의와 거래 진행을 위한 댓글 기능이 구현되어 있고, 의심되는 물품 판매에 대한 신고 기능을 제공합니다.

## 📅 프로젝트 기간


**2023.11.13 ~ 2023.11.30**

| 기간 | 설명 |
| --- | --- |
| 11.13 ~ 11.14  | 비즈니스 요구사항 분석, 프로젝트 기능 정의, 역할 분담 |
| 11.15 ~ 11.17 | 화면 레이아웃 구성, API 설계, ERD 구성 |
| 11.20 ~ 11.22 | Entity 클래스 설계 및 JPA 연관관계 설정 |
| 11.23 ~ 11.30 | 기능 구현 진행 |

### Weekly Meeting

회의를 진행하며 진행 상황 및 코드 공유

🔗[23.11.13](https://www.notion.so/23-11-13-2deb6ee6d6504b99aeef275542210b6e?pvs=21)

🔗[23.11.14](https://www.notion.so/23-11-14-a9975f4781494d19b8d59bca4370a8be?pvs=21)

🔗[23.11.17](https://www.notion.so/23-11-17-0cbb14427ecf4520b500988f947f6d7f?pvs=21)

🔗[23.11.20](https://www.notion.so/23-11-20-7fc09583cf084029bcdf1d4500f7ab37?pvs=21)

🔗[23.11.22](https://www.notion.so/23-11-22-929a5c4568734278b4c8304276cd3604?pvs=21)

🔗[23.11.26](https://www.notion.so/23-11-26-1e52cd63d45b4ab4b6580952cd7746c3?pvs=21)

## 📑프로젝트 주요 기능


- 게시판 : 게시물/댓글 CRUD 기능, 조회수, 페이징 및 검색 처리
- 회원 : Security 회원가입 및 로그인, 회원정보 수정, 회원가입 시 유효성 검사 및 중복 검사
- 관리자 : 회원 리스트 조회 
- 추가 기능 : 게시물 이미지 첨부 기능, 게시물 신고하기, 찜하기(위시리스트)

## 🛠️Stacks


### Environment

<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">

### Development

<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
<img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">

### Communication

<img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white">
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white">
<img src="https://img.shields.io/badge/GoogleSheets-34A853?style=for-the-badge&logo=GoogleSheets&logoColor=white">


## ☁️**ERD 다이어그램**
![erd](https://github.com/Dawnnote/JungleBook/assets/148731548/0bfc6d3c-0d02-4c56-a4e8-e6f9d1bb165f)


## 💻결과 화면

## 💬프로젝트 진행 소감


김연수

김근영

  프로젝트를 진행하면서 코드의 작성부터 구현하기까지 모르는 부분도 많았고 오류나는 부분도 많아 어려움을 느꼈지만 팀으로 진행하면서 서로의
  부족한 부분을 보완해주며 서로 도와줘 잘 진행되었다고 생각합니다. 아쉬운 부분으론 짧은시간동안 배우고 프로젝트를 만들어서 프로젝트 완성도가 부족한 부분이 많아서
  그부분이 제일 아쉬웠습니다.
  
안국범

  피그마 라던지 AWS RDS로 클라우드에서 DB를 참조하는것, API명세서등 팀 프로젝트를 하면서 처음 해보는 기능들을 많이 접했고
  깃허브로 협업할때 다른 팀들과 PULL, PUSH, MERGE 로 브렌치의 내용을 같이 수정하고 합쳐 보면서 두렵기만 했던
  깃허브랑 좀 친해질 수 있었던거 같습니다.좋은 팀원분들 만나서 분위기 좋게 프로젝트를 진행할 수 있어서 좋았고
  다음에 협업을 할때는 팀적으로 더 많은 기능을 활용하면서 해보고 싶습니다.
  
양미예

 팀 프로젝트를 통해서 서로 코드 리뷰를 통해 오류를 해결하는 과정에서 협업의 중요성을 알게되었습니다

이상훈

  Spring에 대해 감을 잡지 못하고 있었는데 프로젝트를 진행하면서 많이 배우다보니 Spring 개발에 대해 감을 잡을 수 있었고 앞으로 배워 나가고싶은 방향도 생겼습니다
