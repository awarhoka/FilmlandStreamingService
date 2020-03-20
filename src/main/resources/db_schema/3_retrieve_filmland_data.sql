select * from user;
select * from film_category;
select * from user_film_category;

select u.username, u.email, fc.name as film_category_name, fc.availablecontent, fc.price 
from user_info u, film_category fc, user_film_category ufc
where u.user_id = ufc.user_id and fc.film_category_id = ufc.film_category_id;

DELETE FROM SHARE_FILM_CATEGORY;
DELETE FROM USER_FILM_CATEGORY;
DELETE FROM USER_INFO;