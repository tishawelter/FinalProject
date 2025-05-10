--DROP TABLE IF EXISTS plant_location;
--DROP TABLE IF EXISTS location;
--DROP TABLE IF EXISTS plant;
--DROP TABLE IF EXISTS source;

CREATE TABLE source (
	source_id int NOT NULL AUTO_INCREMENT,
	source_name varchar(256) NOT NULL,
	PRIMARY KEY (source_id)
);

CREATE TABLE plant (
	plant_id int NOT NULL AUTO_INCREMENT,
	source_id int NULL,
	plant_name varchar(128) NOT NULL,
	flower_color varchar(128),
	light varchar(128),
	PRIMARY KEY (plant_id),
	FOREIGN KEY (source_id) REFERENCES source (source_id) ON DELETE CASCADE
);

CREATE TABLE location (
	location_id int NOT NULL AUTO_INCREMENT,
	location_name varchar(128) NOT NULL,	
	PRIMARY KEY (location_id)
);

CREATE TABLE plant_location (
	plant_id int NOT NULL,
	location_id int NOT NULL,
	FOREIGN KEY (plant_id) REFERENCES plant (plant_id) ON DELETE CASCADE,
	FOREIGN KEY (location_id) REFERENCES location (location_id) ON DELETE CASCADE
);