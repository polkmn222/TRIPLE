CREATE TABLE truser(
	 
    uid 	VARCHAR(10) NOT NULL  PRIMARY KEY,
    pwd 	VARCHAR(16) NOT NULL ,
    name 	VARCHAR(10) NOT NULL,
    phone	VARCHAR(14) NOT NULL,
    address VARCHAR(50) NOT NULL,
    email   VARCHAR(30),
    root	VARCHAR(20)
);

SELECT * FROM truser;
DESC truser;

DROP TABLE trreview;
DROP TABLE truser;
DROP TABLE trattach;
DROP TABLE trreply;

  CREATE TABLE trreview(
    num 	INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title 	VARCHAR(16) NOT NULL ,
    author 	VARCHAR(10) NOT NULL REFERENCES truser(uid),
    contents VARCHAR(2000),
    bdate 	DATE
);     

SELECT * FROM trreview;
DESC trreview;
 
CREATE TABLE trattach(
	 
    att_num 	INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    board_num 	INT(10),
    filename 	VARCHAR(4000),
    filesize 	INT(10)
);
SELECT * FROM trattach;
DESC trattach;


CREATE TABLE trreply ( 
	reply_no INT NOT NULL AUTO_INCREMENT, 
    article_no INT DEFAULT 0, 
    reply_text VARCHAR(1000), 
    reply_writer VARCHAR(50), 
    reg_date DATE, 
    PRIMARY KEY (reply_no) );

SELECT * FROM trreply;
DESC trreply;

ALTER TABLE trreply ADD CONSTRAINT FK_ARTICLE FOREIGN KEY (article_no) REFERENCES trreveiw (num);

CREATE TABLE trcomment(
  cnum INT NOT NULL AUTO_INCREMENT,
  comment_num INT,
  comment_writer VARCHAR(50),
  comment VARCHAR(100) NOT NULL,  
  comment_date Date,   
  PRIMARY KEY (cnum));

SELECT * FROM trcomment;
DESC trcomment;  