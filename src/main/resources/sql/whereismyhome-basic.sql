drop database myhome;
CREATE SCHEMA IF NOT EXISTS `myhome` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `myhome` ;

-- -----------------------------------------------------
DROP TABLE IF EXISTS `myhome`.`members` ;

CREATE TABLE IF NOT EXISTS `myhome`.`members` (
  `userId` VARCHAR(16) NOT NULL,
  `userName` VARCHAR(20) NOT NULL,
  `userPassword` VARCHAR(32) NOT NULL,
  `userEmail` VARCHAR(40) NULL DEFAULT NULL,
  `userPhone` VARCHAR(20) NULL DEFAULT NULL,
  `userRole` VARCHAR(16) NOT NULL,
  `userDel` INT DEFAULT 0,
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into `myhome`.`members` (userId, userName, userPassword, userEmail, userPhone, userRole)
values     ('admin', '관리자', '1234', 'admin@google.com', '010-1234-5678', 'admin'),
	('ssafy', '김싸피', '1234', 'ssafy@ssafy.com', '010-1234-5678', 'member');
    
update members set userPassword=md5(userPassword);

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
create procedure insertData()
BEGIN
    DECLARE i INT default 1;
    WHILE (i<=300) do
    insert into board(userid, subject, content)
    values('ssafy', concat('안녕하세요 ',i), '반갑습니다');
    set i=i+1;
    end while;
    end $$
delimiter ;

call insertData();

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

insert into `myhome`.`qna` (userId, subject, content, answer)
values ('ssafy', '답변이 등록된 게시물입니다.', '질문입니다.', '답변입니다.');

insert into `myhome`.`qna` (userId, subject, content)
values ('ssafy', '답변이 등록된 게시물입니다.', '질문입니다.');
