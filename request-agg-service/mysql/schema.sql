

create database msgr;

use msgr;

/**
  enteties
*/
create table tag(
	tag varchar(20) UNIQUE PRIMARY KEY NOT NULL
);

create table request(
	_id varchar(40) PRIMARY KEY NOT NULL,
	request varchar(100),
    user_id varchar(40),
    request_date DATETIME DEFAULT CURRENT_TIMESTAMP
);
create table watch(
	_id varchar(40) PRIMARY KEY UNIQUE NOT NULL,
    user_id varchar(40)
);


/**

  groupings

*/

create table tag_request_set(
	tag varchar(20) NOT NULL,
	request_id varchar(40) NOT NULL,
	index(tag, request_id),
	PRIMARY KEY (tag, request_id),
	FOREIGN KEY (tag) REFERENCES tag(tag),
	FOREIGN KEY (request_id) REFERENCES request(_id)
);
