create database bugtrackingsystem;
use bugtrackingsystem;

create table User(user_id int, user_name varchar(30) NOT NULL, user_email varchar(40) NOT NULL, user_password varchar(20) NOT NULL,user_type varchar(20) NOT NULL, PRIMARY KEY (user_id));

create table Teams ( team_id int, user_id int NOT NULL, PRIMARY KEY (team_id), FOREIGN KEY(user_id) references User(user_id));

create table Project (project_id varchar(40), team_id int NOT NULL,  project_name varchar(40) NOT NULL, project_description varchar(300) NOT NULL, project_start_date datetime NOT NULL, project_status varchar(30) NOT NULL, PRIMARY KEY (project_id), FOREIGN KEY (team_id) references Teams(team_id));

create table Bug ( bug_id varchar(20), bug_title varchar(40) NOT NULL, bug_description varchar(300) NOT NULL, project_id varchar(40) NOT NULL, created_by int NOT NULL, open_date datetime DEFAULT CURRENT_TIMESTAMP, assigned_by int NOT NULL, marked_for_closing boolean DEFAULT 0, closed_by int, closed_on_date datetime, status varchar(30) NOT NULL, severity varchar(20) NOT NULL, PRIMARY KEY (bug_id), FOREIGN KEY (project_id) references Project(project_id));

