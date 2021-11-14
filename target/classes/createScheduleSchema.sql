CREATE SCHEMA Schedule;
use schedule;
create table Employee (
id int not null auto_increment,
first_name varchar(50),
last_name varchar(50),
email varchar(50),
address varchar(50),
position varchar(50),
primary key(id)
);
create table Date (
id int not null auto_increment,
fulldate varchar(50) not null unique,
year int,
month int,
day_of_month int,
day_of_week int,
is_weekend boolean,
is_holiday boolean,
primary key(id)
);
CREATE TABLE Schedule (
id INT NOT NULL auto_increment,
scheduled_day_id int not null,
employee_id int not null,
base_shift VARCHAR(50) default null,
start_offset int default null,
shift_extension int default null,
primary key(id),
foreign key(scheduled_day_id) references Date(id),
foreign key(employee_id) references Employee(id)
);