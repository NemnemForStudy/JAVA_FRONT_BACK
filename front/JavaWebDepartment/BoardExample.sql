# 1. Board DB를 생성
CREATE DATABASE PEED;
USE PEED;
# 2. User 테이블 생성
CREATE TABLE User (
	id int PRIMARY KEY AUTO_INCREMENT, 	# 아이디 / 정수형태 / 기본키 / 자동 증가
    password VARCHAR(30) NOT NULL,		# 패스워드 / 길이30의 가변문자열 / 필수값
    name VARCHAR(50) NOT NULL,			# 이름 / 길이50의 가변문자열 / 필수값
    phoneNumber VARCHAR(30) NULL UNIQUE # 전화번호 / 길이15의 가변문자열 / 필수값 / 중복이 불가능함
);

# 아래는 요구사항이라 생각하자.

# Board 테이블 생성
# 게시물 번호(boardNumber)가 존재하고 정수형태 및 자동 증가로 관리 게시물 번호로 각 데이터를 구분
# 게시물 제목(boardTitle) 존재하고 길이 200의 가변문자열 및 필수 값으로 관리
# 게시물 내용(boardContent) 존재하고 길이의 제한 없는 문자열 및 필수값으로 관리
# 게시물 작성일(boardDate) 존재하고 날짜 타입 및 필수값으로 관리
# 게시물 작성자(boardWriter) 존재, 정수 형태 및 필수값으로 관리

CREATE TABLE Board(
	boardNumber INT AUTO_INCREMENT PRIMARY KEY,
    boardTitle VARCHAR(200) NOT NULL,
    boardContent TEXT NOT NULL,
    boardDate DATE NOT NULL,
    boardWriter INT NOT NULL
);

SELECT * FROM Board;

# User 레코드를 생성
# 아이디는 자동값을 그대로 사용
# 아이디는 자동값을 그대로 사용, 비밀번호는 '!passw0rd', 이름 '넴넴', 전화번호 '010-1234-5678'인 데이터 생성

INSERT INTO User(password, name, phoneNumber) VALUES('!passw0rd', '넴넴', '010-1234-5678');

SELECT * FROM User;

# User 테이블에서 이름이 '넴넴'인 레코드의 비밀번호를 'qwer1234!!' 수정
UPDATE User SET password = 'qwer1234!!' WHERE name = '넴넴';

# User 테이블에서 id가 1인 레코드를 삭제
DELETE FROM User WHERE id = 1;

INSERT INTO Board (boardTitle, boardContent, boardDate, boardWriter) 
	VALUES ('10번 게시글', '10번 게시글입니다.', '2023-01-10', 2);

# 전체 게시물 보기
SELECT * FROM Board;

# 최신 글 순으로 보기
SELECT * FROM Board ORDER BY boardDate DESC;

# 오래된 글 순으로 보기
SELECT * FROM Board ORDER BY boardDate ASC;

# 10일 이내 작성된 글 보기
SELECT * FROM Board WHERE boardDate >= '2023-01-08';

# 10일 이내 작성된 글을 최신 순 보기
SELECT * FROM Board WHERE boardDate >= '2023-01-08' ORDER BY boardDate DESC;

# 작성자가 1 이면서 10일 이내에 작성된 글을 최신순으로 보기
SELECT * FROM Board WHERE boardWriter = 1 AND boardDate >= '2023-01-08' ORDER BY boardDate DESC;

# 게시물 제목에 '안녕하세요'가 포함된 게시글 보기
INSERT INTO Board (boardTitle, boardContent, boardDate, boardWriter) 
	VALUES ('안녕히가세요.', '안녕하세요. 반갑습니다. 안녕히가세요.', '2023-01-11', 3);

SELECT * FROM Board WHERE boardTitle Like '%안녕하세요%';

# 게시물 내용에 '반갑습니다'가 포함된 게시글 보기
SELECT * FROM Board WHERE boardTitle Like '%반갑습니다%';

# 게시물 제목 + 내용에 '안녕히가세요'가 포함된 게시글 보기
SELECT * FROM Board WHERE boardTitle Like '%안녕히가세요%' OR boardContent Like '%안녕히가세요%';

# board 테이블에서 boardWriter가 id이거나 2인 레코드에서 모든 컬럼 선택

SELECT * FROM Board;
SELECT * FROM Board WHERE boardTitle LIKE '%%';

# 1. OR 연산 사용
SELECT * FROM Board WHERE boardWriter = 1 OR boardWriter = 2;

# 2. IN 연산 사용
SELECT * FROM Board WHERE boardWriter IN(1, 2);

# Board 테이블에서 boardDate가 2023-01-03 부터 2023-01-10까지 레코드에서 모든 컬럼 선택
SELECT * FROM Board WHERE boardDate BETWEEN '2023-01-03' AND '2023-01-10';

# Board 테이블에서 작성일자가 1월인 레코드에서 모든 컬럼을 선택
# 1. LIKE _ 연산 사용
SELECT * FROM Board WHERE boardDate LIKE '____01___';
# 2. LIKE % 연산 사용 -> 그냥 %01%하면 2월에 1일인 애도 나올 수도 있으니 정확한 규칙을 적용해야함.
SELECT * FROM Board WHERE boardDate LIKE '%-01-%';
# 3. BETWEEN AND 연산 사용(2월 사용 불가)
SELECT * FROM Board WHERE boardDate BETWEEN '2023-01-01' AND '2023-01-31';

DROP TABLE Board;

CREATE TABLE Board(
	id INT AUTO_INCREMENT PRIMARY KEY,
    boardTitle VARCHAR(200) NOT NULL,
    boardContent TEXT NOT NULL,
    boardDateTime DATETIME NOT NULL,
    boardLike INT DEFAULT 0,
    boardWriter INT NOT NULL,
    
    # boardWriter, id 참조 시킬거임
    # 아래것만 있는 상태는 Restrict상태
    CONSTRAINT Board_FK FOREIGN KEY (boardWriter)
    REFERENCES User (id)
);

SELECT * FROM User; 
SELECT * FROM Board;

INSERT INTO User (password, name, phoneNumber) VALUES ('P!ssw0rd', 'John doe', '010-1111-4444');
INSERT INTO User (password, name, phoneNumber) VALUES ('qwer1234!!', 'Alies', '010-2222-8888');
INSERT INTO User (password, name, phoneNumber) VALUES ('qlalfqjsgh12#$', 'Brown', '010-3333-6666');

# id가 1이 없으니 안들어 가짐 그래서 2로 바꿔서 넣었음
# 주의) 안 들어가지면 User에 id가 몇번인지 확인하고 숫자를 바꿔줘야한다.
INSERT INTO Board (boardTitle, boardContent, boardDateTime, boardWriter)
VALUES ('HelloWorld!', 'Hello MySQL', now(), 4);
INSERT INTO Board (boardTitle, boardContent, boardDateTime, boardWriter)
VALUES ('HelloWorld!@', 'Hello MySQL@', now(), 4);
INSERT INTO Board (boardTitle, boardContent, boardDateTime, boardWriter)
VALUES ('Good Bye World!', 'Good Bye MySQL', now(), 5);

# 게시물을 작성한 경험(기준)이 있는 유저의 이름과 전화번호, 작성한 게시물 제목을 구하시오.(JOIN)
SELECT U.name, U.phoneNumber, B.boardTitle
FROM User AS U INNER JOIN Board AS B
WHERE U.id = B.boardWriter;

SELECT U.name, U.phoneNumber, B.boardTitle
FROM User AS U RIGHT JOIN Board AS B
ON U.id = B.boardWriter;

# 게시물을 작성한 경험이 있는 유저의 이름과 전화번호를 구하시오.
# 하나만 구할거면 서브쿼리 써도 됨.
SELECT DISTINCT U.name, U.phoneNumber
FROM User AS U, Board AS B
WHERE U.id = B.boardWriter;

SELECT name, phoneNumber
FROM User
WHERE id IN (
	SELECT DISTINCT boardWriter
    FROM Board
);






