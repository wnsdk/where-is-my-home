DROP DATABASE IF EXISTS myhome;
CREATE SCHEMA IF NOT EXISTS `myhome` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `myhome` ;

-- -----------------------------------------------------
DROP TABLE IF EXISTS `myhome`.`members` ;

CREATE TABLE IF NOT EXISTS `myhome`.`members` (
  `userId` VARCHAR(16) NOT NULL,
  `userName` VARCHAR(20) NOT NULL,
  `userPwd` VARCHAR(32) NULL DEFAULT NULL,
  `userEmail` VARCHAR(40) NULL DEFAULT NULL,
  `userPhone` VARCHAR(20) NULL DEFAULT NULL,
  `userImgUrl` VARCHAR(2083) NULL DEFAULT NULL,
  `userRole` VARCHAR(16) NOT NULL,
  `userDel` INT DEFAULT 0,
  `loginType` INT NOT NULL DEFAULT 0,	-- 일반 로그인(0) / 카카오 로그인(1)
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  
  PRIMARY KEY (`userId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into `myhome`.`members` (userId, userName, userPwd, userEmail, userPhone, userRole)
values     ('admin', '관리자', '1234', 'admin@google.com', '010-1234-5678', 'admin'),
	('ssafy', '김싸피', '1234', 'ssafy@ssafy.com', '010-1234-5678', 'member');
    
update members set userPwd=md5(userPwd);

-- -----------------------------------------------------
DROP TABLE IF EXISTS `myhome`.`board` ;

CREATE TABLE IF NOT EXISTS `myhome`.`board` (
  `articleNo` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `registerTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`articleNo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

delimiter $$
create procedure insertDataBoard()
BEGIN
    DECLARE i INT default 1;
    WHILE (i <= 100) do
    insert into board(userid, subject, content)
    values('ssafy', concat('안녕하세요 ', i), '반갑습니다');
    set i=i+1;
    end while;
    end $$
delimiter ;

call insertDataBoard();

insert into `myhome`.`board` (userId, subject, content)
values ('ssafy', '서울 영등포구 미담오피스텔 거주 후기', 'ㅎㅎ');
insert into `myhome`.`board` (userId, subject, content)
values ('ssafy', '인천연수구월세/작은타운하우스복복층1-3층/2000:120/관리비없음/애완견,묘 가능', 'ㅎㅎ');
insert into `myhome`.`board` (userId, subject, content)
values ('ssafy', '오늘도 수고 많으셨습니다', 'ㅎㅎ');
insert into `myhome`.`board` (userId, subject, content)
values ('ssafy', '신혼부부임차보증금 이자지원사업 잘 아시는분 ! ⚡️⚡️⚡️', 'ㅎㅎ');
insert into `myhome`.`board` (userId, subject, content)
values ('ssafy', '월요일 오전은 항상 힘드네요.. ', 'ㅎㅎ');

-- -----------------------------------------------------
DROP TABLE IF EXISTS `myhome`.`qna` ;

CREATE TABLE IF NOT EXISTS `myhome`.`qna` (
  `articleNo` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `answer` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `registerTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`articleNo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

delimiter $$
create procedure insertDataQna()
BEGIN
    DECLARE i INT default 1;
    WHILE (i <= 100) do
    insert into qna(userid, subject, content, answer)
    values('ssafy', concat('궁금한 게 있습니다! ', i), '반갑습니다', '수고하세요~');
    set i=i+1;
    end while;
    end $$
delimiter ;

call insertDataQna();

insert into `myhome`.`qna` (userId, subject, content, answer)
values ('ssafy', '매물과 다른 방 보여주는 부동산?', '조금 아까 집을 보고 왔는데 다시

와서 비교해보니 보고 간 매물과 구조가 좀 다르더라구요

방을 보러갈땐 말씀 안햊 셔서 몰랐구요.

다시 확인하고 부동산에 여쭤보니 세입자가 비협조적이라 옆호수 사진을 찍었다, 하지만 직접 보고 오신 방이 평수가 더 넓다고 하시는데

혹시 다른 호수의 방을 보여주시는건 무슨 문제가 있을수도 있나요?', '저라면 수리나 도배를 해주더라도 들어갈 집을 보고 결정 할것 같아요. 간혼 비협조적인 세입자가 있는건 사실이지만 그래도 전 확인후 들어갑니닽');

insert into `myhome`.`qna` (userId, subject, content, answer)
values ('ssafy', '월세 묵시적 갱신 후, 2년뒤 갱신권 사용 가능한가요???', '2년 살고 묵시적 갱신한지 3개월차인데요.... 2년후 재계약시 갱신권 사용 가능한가요???

​

검색하면 가능하다고 나오는데  문의한 부동산은 안된다고 하는데 햇갈리네요.', '갱신권은 거주 기간 중에 한번만 사용이 가능한데 지금 2년 지나고 집주인도 별말 없고 세입자분도 별말없이 그냥 계약 기간이 지난 즉! 묵시적 갱신이 되신 상태이면 추가 2년 거주 후에 갱신권 사용 가능 합니다. 부동산에서 모르시고 말하는거 같네요.');

insert into `myhome`.`qna` (userId, subject, content)
values ('ssafy', '오늘 월세 계약서 쓰는데요..', '첫 독립이라.. 토욜날 가계약금 넣었고 오늘 저녁에 계약서 쓰기로 했는데요

​

보증금 2000 월세 40

​

근데 부동산에서 늦은시간에 진행된다고 인적사항을 미리 문자로 넣어달라고 하는데요

​

이름 주소(등본상 주소) 전화번호 주민번호 보내달라고 하는데 좀 찝찝한데 미리 문자로 넣어도 되는건가요??');

insert into `myhome`.`qna` (userId, subject, content)
values ('ssafy', '월세 세입자인데, 집주인이 전출을 안해요~', '이미 월세 두번이나 냈고, 두달 넘게 잘 살고 있습니다만

자꾸 집주인 우편물이 날라와서 우편물 가져가시라고 연락을 드렸는데,

갑자기 집주인이 전출을 안하면 안되겠냐고 하네요

이유는 주택을 30년 이상 보유해야 절세가 가능하다고 합니다. 

그냥 주소지에 자기네 있게만 해달라고 하는데 ...

계약 전에 그런 말 없었는데, 이대로 냅둬도 괜찮을까요? ');

insert into `myhome`.`qna` (userId, subject, content)
values ('ssafy', '새아파트 입주를 앞두고 대출문의', '혹시 

전세를 놓았을 경우에는

남는 잔금이 대출이 가능한가요?

전세는 3억2천에 놓을 예정이고

필요한 금액은 1억3천 정도 더 필요할 것 같아요');