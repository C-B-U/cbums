-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- TB_member Table Create SQL
CREATE TABLE TB_member
(
    `m_email`       VARCHAR(40)    NOT NULL    COMMENT '사용자 이메일(id대용)', 
    `password`      VARCHAR(64)    NULL    COMMENT '비밀번호(sha256 암호화)', 
    `m_name`        VARCHAR(12)    NOT NULL    COMMENT '이름', 
    `m_rg_no`       int(3)         NULL        COMMENT '기수', 
    `class_no`      int(10)        NOT NULL    COMMENT '학번', 
    `department`    VARCHAR(15)    NOT NULL    COMMENT '학과', 
    `m_image`       VARCHAR(45)    NULL        COMMENT '프로필 사진',
    `m_introduce`   VARCHAR(60)    NULL        COMMENT '자기소개',
    `sysop_status`  TINYINT(1)     NOT NULL    DEFAULT 0 COMMENT '운영자 여부(일반 사용자: 0, 운영자: 1)', 
    CONSTRAINT  PRIMARY KEY (m_email)
);

ALTER TABLE TB_member COMMENT '사용자';

CREATE INDEX IX_TB_member_1
    ON TB_member(m_email, m_rg_no, m_name);


-- TB_tag Table Create SQL
CREATE TABLE TB_tag
(
    `tag_name`           VARCHAR(24)    NOT NULL    COMMENT '태그명', 
    `tag_production_dt`  DATETIME       NOT NULL    COMMENT '제작일자', 
    `producer`           VARCHAR(40)    NOT NULL    COMMENT '제작자', 
    CONSTRAINT  PRIMARY KEY (tag_name)
);

ALTER TABLE TB_tag COMMENT '태그';

ALTER TABLE TB_tag
    ADD CONSTRAINT FK_TB_tag_producer_TB_member_m_email FOREIGN KEY (producer)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_project Table Create SQL
CREATE TABLE TB_project
(
    `p_seq`             INT(4)         NOT NULL    AUTO_INCREMENT COMMENT '프로젝트 번호', 
    `p_name`            VARCHAR(45)    NOT NULL    COMMENT '프로젝트 명', 
    `p_rg_dt`           DATETIME       NOT NULL    COMMENT '개설일자', 
    `maximum_m`         INT(20)        NULL        COMMENT '최대인원', 
    `p_producer`        VARCHAR(40)    NOT NULL    COMMENT '개설자', 
    `producer_hidden`   TINYINT        NOT NULL    DEFAULT 0 COMMENT '개설자 익명 여부(공개: 0, 익명: 1)', 
    `p_image`           VARCHAR(45)    NULL        COMMENT '프로젝트 타이틀 이미지', 
    `p_rg_no`           INT(3)         NOT NULL    COMMENT '프로젝트 기수', 
    `p_recruit_status`  TINYINT        NOT NULL    COMMENT '프로젝트 모집 여부',
    CONSTRAINT  PRIMARY KEY (p_seq)
);

ALTER TABLE TB_project COMMENT '프로젝트';

ALTER TABLE TB_project
    ADD CONSTRAINT FK_TB_project_p_producer_TB_member_m_email FOREIGN KEY (p_producer)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_board Table Create SQL
CREATE TABLE TB_board
(
    `board_name`           VARCHAR(45)    NOT NULL    COMMENT '게시판명', 
    `board_producer`       VARCHAR(40)    NOT NULL    COMMENT '개설자', 
    `board_production_dt`  DATETIME       NOT NULL    COMMENT '개설일자', 
    CONSTRAINT  PRIMARY KEY (board_name)
);

ALTER TABLE TB_board COMMENT '게시판';

ALTER TABLE TB_board
    ADD CONSTRAINT FK_TB_board_board_producer_TB_member_m_email FOREIGN KEY (board_producer)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_post Table Create SQL
CREATE TABLE TB_post
(
    `post_seq`           INT(10)          NOT NULL    AUTO_INCREMENT COMMENT '게시글번호', 
    `board_name`         VARCHAR(45)      NOT NULL    COMMENT '게시판명', 
    `post_title`         VARCHAR(150)     NOT NULL    COMMENT '게시글 제목', 
    `post_content`       VARCHAR(9000)    NOT NULL    COMMENT '게시글 내용', 
    `post_image`         VARCHAR(45)      NULL        COMMENT '개시글 이미지', 
    `post_rg_dt`         DATETIME         NOT NULL    COMMENT '작성일자', 
    `post_writer`        VARCHAR(40)      NULL        COMMENT '작성자', 
    `post_likes_count`   int(4)           NOT NULL    DEFAULT 0 COMMENT '좋아요 수', 
    `post_visits_count`  int(4)           NOT NULL    DEFAULT 0 COMMENT '방문자 수', 
    `parent_post_seq`    int(10)          NULL        COMMENT '상위 게시글', 
    CONSTRAINT  PRIMARY KEY (post_seq)
);

ALTER TABLE TB_post COMMENT '게시글';

ALTER TABLE TB_post
    ADD CONSTRAINT FK_TB_post_parent_post_seq_TB_post_post_seq FOREIGN KEY (parent_post_seq)
        REFERENCES TB_post (post_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_post
    ADD CONSTRAINT FK_TB_post_post_writer_TB_member_m_email FOREIGN KEY (post_writer)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_post
    ADD CONSTRAINT FK_TB_post_board_name_TB_board_board_name FOREIGN KEY (board_name)
        REFERENCES TB_board (board_name) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_book Table Create SQL
CREATE TABLE TB_book
(
    `isbn`           INT(13)        NOT NULL    COMMENT 'isbn', 
    `book_owner`     VARCHAR(40)    NOT NULL    COMMENT '도서 주인', 
    `book_name`      VARCHAR(45)    NOT NULL    COMMENT '도서명', 
    `book_borrower`  VARCHAR(40)    NULL        COMMENT '도서 대여자', 
    `book_rg_dt`     DATETIME       NOT NULL    COMMENT '도서 등록일', 
    CONSTRAINT  PRIMARY KEY (isbn, book_owner)
);

ALTER TABLE TB_book COMMENT '도서';

ALTER TABLE TB_book
    ADD CONSTRAINT FK_TB_book_book_owner_TB_member_m_email FOREIGN KEY (book_owner)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_book
    ADD CONSTRAINT FK_TB_book_book_borrower_TB_member_m_email FOREIGN KEY (book_borrower)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_project_progress Table Create SQL
CREATE TABLE TB_project_progress
(
    `p_seq`             INT(4)           NOT NULL    COMMENT '프로젝트 번호', 
    `progress_date`     DATE             NOT NULL    COMMENT '진행 날짜', 
    `progress_content`  VARCHAR(6000)    NOT NULL    COMMENT '진행내용', 
    `homework`          VARCHAR(100)     NULL        COMMENT '숙제', 
    `progress_image`    VARCHAR(45)      NULL        COMMENT '활동사진', 
    CONSTRAINT  PRIMARY KEY (p_seq, progress_date)
);

ALTER TABLE TB_project_progress COMMENT '프로젝트 활동';

ALTER TABLE TB_project_progress
    ADD CONSTRAINT FK_TB_project_progress_p_seq_TB_project_p_seq FOREIGN KEY (p_seq)
        REFERENCES TB_project (p_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_form_question Table Create SQL
CREATE TABLE TB_form_question
(
    `question_seq`       INT(4)          NOT NULL    AUTO_INCREMENT COMMENT '문항 번호', 
    `question_content`   VARCHAR(100)    NOT NULL    COMMENT '문항 내용', 
    `question_producer`  VARCHAR(40)     NOT NULL    COMMENT '제작자', 
    `producer_dt`        DATETIME        NOT NULL    COMMENT '제작일자', 
    CONSTRAINT  PRIMARY KEY (question_seq)
);

ALTER TABLE TB_form_question COMMENT '지원서 문항';

ALTER TABLE TB_form_question
    ADD CONSTRAINT FK_TB_form_question_question_producer_TB_member_m_email FOREIGN KEY (question_producer)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_form Table Create SQL
CREATE TABLE TB_form
(
    `form_seq`        INT(4)           NOT NULL    AUTO_INCREMENT COMMENT '지원서 번호', 
    `form_title`      VARCHAR(45)      NOT NULL    COMMENT '지원서 제목', 
    `form_introduce`  VARCHAR(1500)    NULL        COMMENT '지원서 설명', 
    `form_start_dt`   DATETIME         NOT NULL    COMMENT '시작일자', 
    `form_end_dt`     DATETIME         NOT NULL    COMMENT '마감일자', 
    `rg_no`           int(3)           NULL        COMMENT '모집기수( 비모집 지원서일 경우는 null)', 
    `producer`        VARCHAR(40)      NOT NULL    COMMENT '제작자', 
    CONSTRAINT  PRIMARY KEY (form_seq)
);

ALTER TABLE TB_form COMMENT '지원서';

ALTER TABLE TB_form
    ADD CONSTRAINT FK_TB_form_producer_TB_member_m_email FOREIGN KEY (producer)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_project_member Table Create SQL
CREATE TABLE TB_project_member
(
    `p_seq`           INT(4)         NOT NULL    COMMENT '프로젝트 번호', 
    `p_m`             VARCHAR(40)    NOT NULL    COMMENT '프로젝트 참가자', 
    `m_role`          VARCHAR(30)    NULL        COMMENT '역할', 
    `sign_up_dt`      DATETIME       NOT NULL    COMMENT '신청 날짜', 
    `sign_up_status`  TINYINT(1)     NOT NULL    DEFAULT 1 COMMENT '승인 여부(반려:0, 미정:1, 승인:2)', 
    CONSTRAINT  PRIMARY KEY (p_seq, p_m)
);

ALTER TABLE TB_project_member COMMENT '프로젝트';

ALTER TABLE TB_project_member
    ADD CONSTRAINT FK_TB_project_member_p_seq_TB_project_p_seq FOREIGN KEY (p_seq)
        REFERENCES TB_project (p_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_project_member
    ADD CONSTRAINT FK_TB_project_member_p_m_TB_member_m_email FOREIGN KEY (p_m)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_member_tag Table Create SQL
CREATE TABLE TB_member_tag
(
    `m_email`   VARCHAR(40)    NOT NULL    COMMENT '태그 적용자', 
    `tag_name`  VARCHAR(24)    NOT NULL    COMMENT '태그명', 
    CONSTRAINT  PRIMARY KEY (m_email, tag_name)
);

ALTER TABLE TB_member_tag COMMENT '사용자 태그';

ALTER TABLE TB_member_tag
    ADD CONSTRAINT FK_TB_member_tag_tag_name_TB_tag_tag_name FOREIGN KEY (tag_name)
        REFERENCES TB_tag (tag_name) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_member_tag
    ADD CONSTRAINT FK_TB_member_tag_m_email_TB_member_m_email FOREIGN KEY (m_email)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_book_tag Table Create SQL
CREATE TABLE TB_book_tag
(
    `isbn`        INT(13)        NOT NULL    COMMENT '도서 isbn', 
    `book_owner`  VARCHAR(40)    NOT NULL    COMMENT '도서 주인', 
    `tag_name`    VARCHAR(24)    NOT NULL    COMMENT '태그명', 
    CONSTRAINT  PRIMARY KEY (isbn, book_owner, tag_name)
);

ALTER TABLE TB_book_tag COMMENT '도서 태그';

ALTER TABLE TB_book_tag
    ADD CONSTRAINT FK_TB_book_tag_tag_name_TB_tag_tag_name FOREIGN KEY (tag_name)
        REFERENCES TB_tag (tag_name) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_book_tag
    ADD CONSTRAINT FK_TB_book_tag_book_owner_TB_book_book_owner FOREIGN KEY (book_owner, isbn)
        REFERENCES TB_book (book_owner, isbn) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_form_content Table Create SQL
CREATE TABLE TB_form_content
(
    `form_seq`      INT(4)    NOT NULL    COMMENT '지원서 번호', 
    `question_seq`  INT(4)    NOT NULL    COMMENT '문항 번호', 
    CONSTRAINT  PRIMARY KEY (form_seq, question_seq)
);

ALTER TABLE TB_form_content COMMENT '지원서에 포함된 내용';

ALTER TABLE TB_form_content
    ADD CONSTRAINT FK_TB_form_content_question_seq_TB_form_question_question_seq FOREIGN KEY (question_seq)
        REFERENCES TB_form_question (question_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_form_content
    ADD CONSTRAINT FK_TB_form_content_form_seq_TB_form_form_seq FOREIGN KEY (form_seq)
        REFERENCES TB_form (form_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_form_answer Table Create SQL
CREATE TABLE TB_form_answer
(
    `form_seq`        INT(4)           NOT NULL    COMMENT '지원서 번호', 
    `question_seq`    INT(4)           NOT NULL    COMMENT '문항번호', 
    `form_writer`     VARCHAR(40)      NOT NULL    COMMENT '작성자', 
    `answer_content`  VARCHAR(6000)    NOT NULL    COMMENT '문항내용', 
    CONSTRAINT  PRIMARY KEY (form_seq, question_seq, form_writer)
);

ALTER TABLE TB_form_answer COMMENT '지원서 각 문항별 답변';


-- TB_project_plan Table Create SQL
CREATE TABLE TB_project_plan
(
    `p_seq`         INT(4)          NOT NULL    COMMENT '프로젝트 번호', 
    `plan_date`     DATE            NOT NULL    COMMENT '진행 날짜', 
    `plan_content`  VARCHAR(100)    NOT NULL    COMMENT '계획 내용', 
    `homework`      VARCHAR(100)    NULL        COMMENT '숙제', 
    CONSTRAINT  PRIMARY KEY (p_seq, plan_date)
);

ALTER TABLE TB_project_plan COMMENT '프로젝트 계획';

ALTER TABLE TB_project_plan
    ADD CONSTRAINT FK_TB_project_plan_p_seq_TB_project_p_seq FOREIGN KEY (p_seq)
        REFERENCES TB_project (p_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_project_tag Table Create SQL
CREATE TABLE TB_project_tag
(
    `p_seq`     INT(4)         NOT NULL, 
    `tag_name`  VARCHAR(24)    NOT NULL, 
    CONSTRAINT  PRIMARY KEY (p_seq, tag_name)
);

ALTER TABLE TB_project_tag COMMENT '프로젝트 태그';

ALTER TABLE TB_project_tag
    ADD CONSTRAINT FK_TB_project_tag_p_seq_TB_project_p_seq FOREIGN KEY (p_seq)
        REFERENCES TB_project (p_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_project_tag
    ADD CONSTRAINT FK_TB_project_tag_tag_name_TB_tag_tag_name FOREIGN KEY (tag_name)
        REFERENCES TB_tag (tag_name) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_project_participation Table Create SQL
CREATE TABLE TB_project_participation
(
    `p_seq`                   INT(4)         NOT NULL    COMMENT '프로젝트 번호', 
    `progress_date`           DATE           NOT NULL    COMMENT '활동일자', 
    `p_m`                     VARCHAR(40)    NOT NULL    COMMENT '참여자', 
    `attend_status`           TINYINT(1)     NOT NULL    COMMENT '참여형태(결석:0, 지각:1, 출석:2)', 
    `homework_submit_status`  TINYINT(1)     NULL        COMMENT '숙제 여부(미제출: 0, 지연제출:1, 제출: 2)', 
    CONSTRAINT  PRIMARY KEY (p_seq, progress_date, p_m)
);

ALTER TABLE TB_project_participation COMMENT '프로젝트참여';

ALTER TABLE TB_project_participation
    ADD CONSTRAINT FK_TB_project_participation_p_seq_TB_project_progress_p_seq FOREIGN KEY (p_seq, progress_date)
        REFERENCES TB_project_progress (p_seq, progress_date) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_project_participation
    ADD CONSTRAINT FK_TB_project_participation_p_m_TB_project_member_p_m FOREIGN KEY (p_m)
        REFERENCES TB_project_member (p_m) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_board_subscription Table Create SQL
CREATE TABLE TB_board_subscription
(
    `board_name`       VARCHAR(45)    NOT NULL    COMMENT '게시판 명', 
    `subscriber`       VARCHAR(40)    NOT NULL    COMMENT '구독자', 
    `subscription_dt`  DATETIME       NOT NULL    COMMENT '구독일자', 
    CONSTRAINT  PRIMARY KEY (board_name, subscriber)
);

ALTER TABLE TB_board_subscription COMMENT '게시판 구독';

ALTER TABLE TB_board_subscription
    ADD CONSTRAINT FK_TB_board_subscription_subscriber_TB_member_m_email FOREIGN KEY (subscriber)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_board_subscription
    ADD CONSTRAINT FK_TB_board_subscription_board_name_TB_board_board_name FOREIGN KEY (board_name)
        REFERENCES TB_board (board_name) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_post_tag Table Create SQL
CREATE TABLE TB_post_tag
(
    `post_seq`  INT(10)        NOT NULL    COMMENT '게시글 번호', 
    `tag_name`  VARCHAR(24)    NOT NULL    COMMENT '태그명', 
    CONSTRAINT  PRIMARY KEY (post_seq, tag_name)
);

ALTER TABLE TB_post_tag COMMENT '게시글 태그';

ALTER TABLE TB_post_tag
    ADD CONSTRAINT FK_TB_post_tag_post_seq_TB_post_post_seq FOREIGN KEY (post_seq)
        REFERENCES TB_post (post_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_post_tag
    ADD CONSTRAINT FK_TB_post_tag_tag_name_TB_tag_tag_name FOREIGN KEY (tag_name)
        REFERENCES TB_tag (tag_name) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- TB_comment Table Create SQL
CREATE TABLE TB_comment
(
    `comment_seq`          INT(10)          NOT NULL    AUTO_INCREMENT COMMENT '댓글 번호', 
    `post_seq`             INT(10)          NOT NULL    COMMENT '게시글 번호', 
    `comment_content`      VARCHAR(3000)    NOT NULL    COMMENT '댓글 내용', 
    `comment_writer`       VARCHAR(40)      NOT NULL    COMMENT '댓글 작성자', 
    `comment_likes_count`  INT(4)           NOT NULL    COMMENT '좋아요 수', 
    `comment_rg_dt`        DATETIME         NOT NULL    COMMENT '작성일자', 
    CONSTRAINT  PRIMARY KEY (comment_seq)
);

ALTER TABLE TB_comment COMMENT '댓글';

ALTER TABLE TB_comment
    ADD CONSTRAINT FK_TB_comment_post_seq_TB_post_post_seq FOREIGN KEY (post_seq)
        REFERENCES TB_post (post_seq) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE TB_comment
    ADD CONSTRAINT FK_TB_comment_comment_writer_TB_member_m_email FOREIGN KEY (comment_writer)
        REFERENCES TB_member (m_email) ON DELETE RESTRICT ON UPDATE RESTRICT;


