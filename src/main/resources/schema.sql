DROP TABLE IF EXISTS foster_location;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS dog;
DROP TABLE IF EXISTS foster;

CREATE TABLE foster (
  foster_id int NOT NULL AUTO_INCREMENT,
  first_name varchar(128) NOT NULL,
  last_name varchar(128) NOT NULL,
  foster_address varchar(128),
  foster_phone varchar(128),
  email varchar(128),
  PRIMARY KEY (foster_id)
  
); 
CREATE TABLE dog (
  dog_id int NOT NULL AUTO_INCREMENT,
  foster_id int NOT NULL,
  age varchar(60),
  name varchar(60) NOT NULL,
  color varchar(60),
  breed varchar (128),
  PRIMARY KEY (dog_id),
  FOREIGN KEY (foster_id) REFERENCES foster (foster_id) ON DELETE CASCADE
);

CREATE TABLE location (
  location_id int NOT NULL AUTO_INCREMENT,
  business_name varchar(256) NOT NULL,
  street_address varchar(128),
  city varchar(128),
  state varchar(128),
  zip varchar(128),
  phone varchar(256),
  PRIMARY KEY (location_id)
); 
 
CREATE TABLE foster_location (
  foster_id int NOT NULL,
  location_id int NOT NULL,
  FOREIGN KEY (foster_id) REFERENCES foster (foster_id) ON DELETE CASCADE,
  FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE CASCADE
  
);