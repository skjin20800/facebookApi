

# Facebook api 구현


안녕하세요. 
진경빈입니다.  



# 목차
- 기능
- API
- DB
  
# 기능 
- 회원가입
- 로그인
- 회원정보 조회
- 글쓰기
- 피드
    - 게시글 목록 출력
        - 게시글 좋아요 갯수 출력
       - 댓글 목록 출력
       - 댓글 좋아요 갯수 출력
- 댓글 쓰기
- 게시글 좋아요( 중복 좋아요 불가능)
- 댓글 좋아요(중복 좋아요 불가능)

# API

### GET
 ![image](./mdimg/3.png)

### POST
 ![image](./mdimg/1.png)
 ![image](./mdimg/2.png)
 
 # DB

### MYSQL

`
create user 'facebookuser'@'%' identified by 'test1234';
GRANT ALL PRIVILEGES ON *.* TO 'facebookuser'@'%';
create database facebook;
use facebook;

CREATE TABLE user(
    id int primary key auto_increment,
    username varchar(100) not null unique key,
    password varchar(100) not null,
    nickname varchar(100) not null,
    age int not null,
    createDate timestamp
) engine=InnoDB default charset=utf8;

CREATE TABLE boards(
    id int primary key auto_increment,
    userId int not null,
    title varchar(100) not null,
    content varchar(100),
    createDate timestamp
) engine=InnoDB default charset=utf8;

CREATE TABLE replys(
    id int primary key auto_increment,
    userId int not null,
    boardId int not null,
    content varchar(100),
    createDate timestamp
) engine=InnoDB default charset=utf8;


CREATE TABLE likes(
    id int primary key auto_increment,
    userId int not null,
    boardId int ,
    replyId int ,
    createDate timestamp
) engine=InnoDB default charset=utf8;
`

