## 프로젝트 소개
MultiBoard : 게시판 / 포트폴리오 / 블로그

## 일정
1차 : 게시판 (12/4 ~)
- 게시물 (작성, 조회/수정, 삭제)
- -사용자 (로그인, 회원가입, 회원정보 조회/수정)
- 스프링 시큐리티 적용 (사용자, 관리자 화면)
- 공통 유효성검사
- 게시물 페이지 처리
- 게시물 댓글 관리
- 네이버 로그인 OAuth + 구글?

2차 : 블로그
<br>
3차 : 포트폴리오

## 사용 기술
#### 주요 프레임워크 / 라이브러리
- Java 11
- Spring Boot 2.7.13
- JPA(Spring Data JPA)
- Spring Security 5.3
- OAuth 2.0

#### 빌드
- maven

#### 프론트엔드
- thymeleaf
- html css
- javascript
- bootstrap 5.3.3

#### 데이터베이스
- mysql 8.0.33



#### AWS EC2, RDS
1. Ubuntu LTS
2. Git SSH 연동 : 
3. Git clone : rm -rf multiBoard >> '~/.ssh'에서 git clone ~~ >> mv ~/.ssh/multiBoard ~/
4. DB 연결 파일 직접 추가 : 해당 경로 가서 vi -real.yml > ctrl+shift+v > esc > :wq
5. mvn clean package
6. sudo java -jar -Duser.timezone=Asia/Seoul multiboard-x.x.x-SNAPSHOT.jar

