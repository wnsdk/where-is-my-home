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
    
update members set userPwd=HEX(AES_ENCRYPT(userPwd, 'key'));

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
CREATE PROCEDURE insertDataBoard()
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
values ('ssafy', '서울 영등포구 미담오피스텔 거주 후기', '❓ 장점 
💬: 공기가 좋고 신축건물이라 깨끗하고 근처에 큰 공원이 있어서 좋습니다

❓ 단점 
💬: 주변 인프라가 좋지는 않습니다');
insert into `myhome`.`board` (userId, subject, content)
values ('ssafy', '인천연수구월세/작은타운하우스복복층1-3층/2000:120/관리비없음/애완견,묘 가능', '💡상세 설명 
1.2.3층 다 쓰는 복복층 구조로 
1층엔 주방,세면대,거실
2충엔 방두개 화장실하나
3층엔 방두개 화장실하나 베란다 
이렇게 되어있습니다.

가족분들이 아니더라도 룸메끼리 공간 나눠서 살기 딱 좋구여 사무실 겸용 거주 하셔도 좋을거같아여 주차장은 건물 밑에 있는데 거주자만  이용 할 수 있어서 주차 걱정없으시고.
관리비도 집앞,주차장 깔끔하게 사용하실수있으면 별도로 내는거 없습니다:)
사진은 사생활 보호로 안올렸으나 궁금하시면 연락 주세요^^

이사는 2월18일 생각 하고있습니다
계약은 1년으로 재연장도 가능합니다
');
insert into `myhome`.`board` (userId, subject, content)
values ('ssafy', '오늘도 수고 많으셨습니다', '내일 비온대요~~~');
insert into `myhome`.`board` (userId, subject, content)
values ('ssafy', '신혼부부임차보증금 이자지원사업 잘 아시는분 ! ⚡️⚡️⚡️', '아이 한명있고 ㅠㅠ 2년 살고 나왔는데
혹시 다른집으로는 연장 한번 더 못하나요? 
동사무소에서는 보통 살던집 연장만 해봐서 
다른집 연장 안되는 걸로 안다고 
안될거라 하셔서요 ㅠㅠ 분명 전에 통화했을땐 
된다하셔서 저희가 다른집 계약하고 연장신청
한거거든요 ㅠㅠㅠ 안되면 다른전세대출 
받아야하는데 걱정이네요 😭 ');
insert into `myhome`.`board` (userId, subject, content)
values ('ssafy', '월요일 오전은 항상 힘드네요.. ', '직장을 10년을 넘게 다녀도 
이건 적응이 안되네요.ㅜㅜ
');
insert into `myhome`.`board` (userId, subject, content)
values ('2541959090K', '오늘 무조건 치킨 뜯으렵니다', '드디어 오늘이네요 ㅜ 오늘 같은 날 아니면 언제 치킨 맛있게 먹겠습니까ㅜ
할인 쿠폰 생긴 걸로 치킨 시키고 편의점가서 캔맥 사와서 축구 딱 보면 환상이겠네요');
insert into `myhome`.`board` (userId, subject, content)
values ('2541959090K', '국민임대 신청 질문 있습니다', '안녕하세요 이번에 국민임대를 신청하려고 하는데요 질문이 있어서 도움 부탁 드립니다.

저희집은 아빠 엄마 오빠 저 이렇게 4인가구 입니다.

집안사정으로 아빠는 따로 사시고 (전입신고는 본가로 되어있음) 

저는 내년에 혼자 살 계획이고 엄마랑 오빠는 둘이서 살 집을 구하고 있어요

저랑 오빠가 국민임대를 신청해서 집을 구하려고 하는데요 .');
insert into `myhome`.`board` (userId, subject, content)
values ('2541959090K', '요즘 가을날씨 정말 좋네요', '11월말이지만 예전만큼 춥지도 않고
너무 좋아요~~..
산책하기도 좋고, 가을날씨 최고네요~!');
insert into `myhome`.`board` (userId, subject, content)
values ('2541959090K', '수원에서 대구까지 이사 업체 구합니다', '소개해주실분???
큰짐은 세탁기 건조기 냉장고 탁자 책장 정도에요~~
일단 3룸에서 나가는건데 tv나 tv다이 침대는 버리고 갈거라
큰 짐은 저게 다지만, 문제는 이사갈때도 
들어갈곳도 사다리차는 써야하네요ㅠㅠ
혹시 괜찮은곳 있으면 추천부탁드립니다
이사 형태는 반포장할거에요~~');
insert into `myhome`.`board` (userId, subject, content)
values ('2541959090K', '거주자우선주차 공유주차장 이용하면 포인트 준답니다 !!', '방금 받은 문자 공유합니다.

이제는 주차장 찾으러 빙빙 돌지 마세요.
편리한 부천시 공유주차장 이용만 해도 22만원 상당의 포인트까지 받을 수 있어요.');


--
-- Table structure for table `boardComment`
--
DROP TABLE IF EXISTS `myhome`.`comment` ;

CREATE TABLE IF NOT EXISTS `myhome`.`comment` (
  `commentNo` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(16) NULL DEFAULT NULL,
  `comment` VARCHAR(500) NULL DEFAULT NULL,
  `articleNo` INT NULL DEFAULT NULL,
  `registerTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentNo`),
  INDEX `comment_to_board_fk` (`articleNo` ASC) VISIBLE,
  INDEX `comment_to_members_fk_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `comment_to_board_fk`
    FOREIGN KEY (`articleNo`)
    REFERENCES `myhome`.`board` (`articleNo`),
  CONSTRAINT `comment_to_members_fk`
    FOREIGN KEY (`userId`)
    REFERENCES `myhome`.`members` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into comment (userId, comment, articleNo)
values ('admin', 'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ', 110);
insert into comment (userId, comment, articleNo)
values ('admin', '24시간 아무때나 궁금한 사항이 있으시면 편안하게 문자나 전화 주세요.
카톡으로도 언제든지 문의주세요..
실시간 답변은 그래도 문자나 전화가 빨라요.
최선을 다해 사시는 분들 늘 건강하시고 매일 매일 행운이 함께하시길 바라겠습니다.
약속합니다. 집주인으로써 최선을 다해 입주민분들이 평안함을 갖도록 늘 최선을 다하겠습니다.', 105);
insert into comment (userId, comment, articleNo)
values ('ssafy', '싸피 짱~~', 110);

--
-- Table structure for table `qna`
--

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
CREATE PROCEDURE insertDataQna()
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
검색하면 가능하다고 나오는데  문의한 부동산은 안된다고 하는데 햇갈리네요.', '갱신권은 거주 기간 중에 한번만 사용이 가능한데 지금 2년 지나고 집주인도 별말 없고 세입자분도 별말없이 그냥 계약 기간이 지난 즉! 묵시적 갱신이 되신 상태이면 추가 2년 거주 후에 갱신권 사용 가능 합니다. 부동산에서 모르시고 말하는거 같네요.');
insert into `myhome`.`qna` (userId, subject, content)
values ('ssafy', '오늘 월세 계약서 쓰는데요..', '첫 독립이라.. 토욜날 가계약금 넣었고 오늘 저녁에 계약서 쓰기로 했는데요
보증금 2000 월세 40
근데 부동산에서 늦은시간에 진행된다고 인적사항을 미리 문자로 넣어달라고 하는데요
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
insert into `myhome`.`qna` (userId, subject, content, answer)
values ('2541959090K', '공인중개사가 가계약 하자고 아침, 저녁 시간을 가리지 않고 전화를 합니다.(내용이 길어요 ㅠㅠ)', '안녕하세요, 
요즘 많은 분들이 알다시피 금리인상, 내년 부동산 가격 하락 예상 등으로 부동산 거래가 절벽이라 거래 성사가 어렵다고 하더군요.
아주 가끔씩 집을 보러 오시는 분들도 있지만 저 역시 아직 계약까지는 이어지지 못했습니다.
그런데 문제는 어느 한 공인중개사가 시간 상관없이 계약을 재촉합니다.', '안녕하세요. 여러 불편사항이 있으시네요.
매수인쪽에서 조건부계약(기존 부동산처분조건)을 원하시는것 같은데 이럴때는 단호하게 말씀하시는게 좋습니다.
예로들어, 2주~4주안에 정확한 답변을 주고 그안에 해결이 안될시 계약금 반환조건 등 그 이후에는 계약금을 위약금으로 보신다고 하시면 좋을것 같습니다.(녹취 및 문자로 남겨놓아야함.)(계약금도 좀 많이 받아놓으시구요.)

무작정 피하시기보다는 이른아침,늦은저녁 전화하지말라고 하시고 꼭 당당하게 말씀하시는것도 하나의 방법입니다.
매도인인 글쓴이님께서 스트레스 받을 필요는 없어보입니다.');
insert into `myhome`.`qna` (userId, subject, content, answer)
values ('2541959090K', '도움 주세요! 임대차계약서의 효력?', '안녕하세요!
5000만원 보증금으로 월세(반전세?) 가계약을 오늘 하려고 합니다.

부동산 끼고 하는게 아니라 집주인(인지 딸인지..)과 직접 만나는데요.
회사 근처 카페에서 만나 1000만원 정도 가계약금 드리면 영수증을 준다고 하네요.

현재 11월 중순이고 12월 말 정도 입주할 예정인데요
계약서는 입주할 때 쯤 작성할거 같아요.

1천만원 주고 가계약하는데 이 "영수증"이라는게 무슨 효력이 있는건지..ㅎㅎ
미국에서 살다 와서 이런걸 잘 모른다고 하는데...저도 사회초년생이라 맞는건지 잘 판단이 안서네요.

1천만원 가계약금 이체시 준다는 영수증 ,
그리고 보증금이 몇 백도 아니고 몇 천인데 표준임대차계약서를 작성하면
나중에 계약만료 후 보증금을 제 때 돌려받을 수 있는 법적 강제성이 있는건지
매우 궁금합니다.', '부동산 통해서 계약 하시기를 추천드립니다. 가계약시에 확인할 것들이 있는데 중개사에게 맡기시는게 좋을 듯 합니다.
원래 부동산에서 계약서를 작성하게 되면 중간에서 중개를 해주면서 영수증도 주는데 그것은 입금을 했다 라는 증거자료로 쓰는 것입니다. 현금으로 주면 받아놓고 안받았다고 할 수 있으니까요.

다시 한번 말씀드리지만 부동산 통해서 진행하시길 권해드립니다.');
insert into `myhome`.`qna` (userId, subject, content)
values ('2541959090K', '계약서 부동산 대리작성 비용', '12월에 이사 예정입니다.
계약기간 전 이사라 제가 새로들어 올 임차인을 구했는데요 부동산에서 계약서 작성하려 하는데 비용이 어떻게 될까요?
보증금 9000만원
월세33만원 관리비8만원 입니다');
insert into `myhome`.`qna` (userId, subject, content, answer)
values ('2541959090K', '1월 말 입주 목표로 원룸 전세 찾아다니고 있는데요', '오늘 부동산 여러군데 다녀보니, 너무 어정쩡한 시기에 와서 12월 중순에 다시 오래는 중개사 분들이 많더라구요. 전세로 들어가신 분들은 입주 얼마 전부터 매물 찾아다니셨나요?', '2달전부터요 대출받으시고들어가시려면 2달전부터는 찾으셔야해요');
insert into `myhome`.`qna` (userId, subject, content)
values ('2541959090K', '질문이여!', '저는 1인 사무실 구하고 있는데 사업용 목적이면
계약서 쓴 뒤에 전입신고나 이런 과정 없이 그냥 입주하고시 사용하면 되는건가요?

용도가 주거시설인데 사업용으로 사용하는 건 전입 등의 신고를 거쳐야하는 건가요?

궁금해요! :)');