

ALTER TABLE author
ADD CONSTRAINT uniqueauthname 
UNIQUE (authname);


select * from author