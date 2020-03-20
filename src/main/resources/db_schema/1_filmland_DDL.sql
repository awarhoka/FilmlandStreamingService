
/*All User's stored in user table*/	
CREATE TABLE user_info (
user_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
username VARCHAR(64), 
email VARCHAR(256), 
password VARCHAR(64), 
UNIQUE (email));
CREATE UNIQUE INDEX user_info_password_idx ON user_info (username, password);

/*All film categories stored in film_category table*/
CREATE TABLE film_category (
film_category_id BIGINT AUTO_INCREMENT PRIMARY KEY, 
name VARCHAR(256), 
availablecontent INTEGER , 
price NUMERIC(12,4),
UNIQUE (name));

/* JOIN TABLE for MANY-TO-MANY relationship*/  
/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE user_film_category (
    user_id BIGINT NOT NULL,
    film_category_id BIGINT NOT NULL,
	startdate TIMESTAMP,
    CONSTRAINT FK_USER_INFO FOREIGN KEY (user_id) REFERENCES user_info (user_id),
    CONSTRAINT FK_FILM_CATEGORY FOREIGN KEY (film_category_id) REFERENCES film_category (film_category_id)
);
CREATE UNIQUE INDEX user_film_category_unique_idx ON user_film_category (user_id, film_category_id);

CREATE TABLE share_film_category (
    user_id BIGINT NOT NULL,
    cust_id BIGINT NOT NULL,
    film_category_id BIGINT NOT NULL,
    CONSTRAINT FK_share_film_category_USER_ID FOREIGN KEY (user_id) REFERENCES user_info (user_id),
    CONSTRAINT FK_share_film_category_CUST_ID FOREIGN KEY (cust_id) REFERENCES user_info (user_id),
    CONSTRAINT FK_share_film_category_ID FOREIGN KEY (film_category_id) REFERENCES film_category (film_category_id)
);
CREATE UNIQUE INDEX share_film_category_unique_idx ON share_film_category (user_id, cust_id, film_category_id);
