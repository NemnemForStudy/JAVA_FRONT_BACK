USE EXAMPLE_DB;

-- Employee(employee_number, position, name, age, gender,
--          academic_ability, birth, tel_Number, address,
--          address_detail, join_date, resignation_date,
--          dept, annual_income, note
--          )

-- Human Resources Management(HRM) 인사관리

CREATE Table Employee(
	employee_number INT AUTO_INCREMENT PRIMARY KEY COMMENT '사번',
    position VARCHAR(10) NOT NULL COMMENT '직급',
    name VARCHAR(20) NOT NULL COMMENT '이름',
    age INT NOT NULL COMMENT '나이',
    gender VARCHAR(10) NOT NULL COMMENT '성별',
    academic_ability VARCHAR(20) NOT NULL COMMENT '학력',
    birth DATE NOT NULL COMMENT '생년월일',
    tel_number VARCHAR(15) NOT NULL UNIQUE COMMENT '폰번호',
    address TEXT NOT NULL COMMENT '주소',
    address_detail TEXT NOT NULL COMMENT '상세주소',
    join_date DATE NOT NULL COMMENT '입사일',
    resignation_date DATE COMMENT '퇴사일',
    dept VARCHAR(45) DEFAULT '미정' COMMENT '부서',
    annual_income INT NOT NULL COMMENT '연봉',
    note TEXT COMMENT '비고'
);

-- 부서
-- - 부서코드 - 부서명 - 부서장 - 부서전화번호
-- dept(dept_code, name, cheif, tel_number)

-- cheif는 employee 사원 중 한명이므로 int를 적어주자

CREATE TABLE Dept (
	dept_code VARCHAR(5) PRIMARY KEY COMMENT '부서코드',
    name VARCHAR(100) NOT NULL COMMENT '부서명',
    cheif INT NOT NULL COMMENT '부서장',
    tel_number VARCHAR(15) NOT NULL COMMENT '부서전화번호',
    
    CONSTRAINT dept_fk_cheif
    FOREIGN KEY (cheif)
    REFERENCES EMPLOYEE (employee_number)
);

ALTER TABLE EMPLOYEE MODIFY COLUMN dept VARCHAR(5) COMMENT '부서코드';

ALTER TABLE EMPLOYEE ADD CONSTRAINT employee_fk_dept
FOREIGN KEY (dept)
REFERENCES Dept (dept_code);

-- 사원 등록 프로세스
-- Input
-- 이름, 나이, 직급, 성별, 학력, 생년월일, 폰번호, 상세주소, 주소, 입사일, 연봉
-- [퇴사일, 부서, 비교]

-- Output
-- 성공 / 실패 
-- (
-- 필수값 중 빈 값이 있을 때, 
-- 데이터 타입이 다를 때, 
-- 데이터 크기가 클 때,
-- DB 접속 실패,
-- 동일한 전화번호가 있을 때,
-- 존재하지 않는 부서코드 입력했을 때
-- )

select * from eampledb.employee;
