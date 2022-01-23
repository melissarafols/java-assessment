DROP TABLE IF EXISTS TBL_USERS;

CREATE TABLE TBL_USERS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  username VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS TBL_TASKS;

CREATE TABLE TBL_TASKS (
  task_id INT AUTO_INCREMENT  PRIMARY KEY,
  user_id INT,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  status VARCHAR(250) NOT NULL,
  date_time DATETIME DEFAULT NULL
);