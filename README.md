# CS3343_2019_HRLeave_System
CS3343 project 

## Pre-requisite
1. MySQL Server
	- on mac: `brew services start mysql`
	- on windows: `C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqld`  # your version could be different change accordingly; If you have path setup then use `mysqld`
	- database data is on: `SQL/database`
	- `mysqldump` is a command to push this data into the sql db. E.g. `mysqldump -u root -p cs3343db > ./SQL/database/cs3343db.sql` # your username might not be root, change accordingly. And cs3343db is the database of this project.
2. Java installed