CREATE DATABASE IF NOT EXISTS shipsDB;
use shipsDB
create user 'shipsmicro'@'%' identified by 'secret';
grant all privileges on shipsDB.* to 'shipsmicro'@'%' identified by 'secret';
grant all privileges on shipsDB.* to 'shipsmicro'@'localhost' identified by 'secret';
