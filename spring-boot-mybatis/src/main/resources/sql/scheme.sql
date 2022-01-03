CREATE TABLE country (
 id BIGINT NOT NULL auto_increment,
 countrycode VARCHAR ( 255 ),
 countryname VARCHAR ( 255 ),
 PRIMARY KEY ( id )) ENGINE = INNODB;

CREATE TABLE t_user (
id BIGINT NOT NULL auto_increment,
email VARCHAR ( 255 ),
login_time datetime ( 6 ),
mobile VARCHAR ( 255 ),
note VARCHAR ( 255 ),
hobbies VARCHAR ( 500 ),
position_id INTEGER,
sex INTEGER,
user_name VARCHAR ( 255 ),
PRIMARY KEY ( id )) ENGINE = INNODB