DROP DATABASE IF EXISTS "scetris";
DROP DATABASE IF EXISTS "scetris-testing";
DROP ROLE IF EXISTS "scetris";
DROP ROLE IF EXISTS "scetris-testing";

-- create user scetris
CREATE ROLE scetris LOGIN
 ENCRYPTED PASSWORD 'md5081c8ce9a74663a9b1efcfb7652448bb'
 NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;

-- create database scetris
CREATE DATABASE "scetris"
 WITH OWNER = "scetris"
      ENCODING = 'UTF8'
      TABLESPACE = pg_default
      --LC_COLLATE = 'de_DE.UTF-8'
      --LC_CTYPE = 'de_DE.UTF-8'
      --TEMPLATE = template0
      CONNECTION LIMIT = -1;
      
-- create user scetris-testing
CREATE ROLE "scetris-testing" LOGIN
 ENCRYPTED PASSWORD 'md51a2dcb00f21ab52f47ec2db503ea80d0'
 NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;

-- create database scetris-testing
CREATE DATABASE "scetris-testing"
 WITH OWNER = "scetris-testing"
      ENCODING = 'UTF8'
      TABLESPACE = pg_default
      --LC_COLLATE = 'de_DE.UTF-8'
      --LC_CTYPE = 'de_DE.UTF-8'
      --TEMPLATE = template0
      CONNECTION LIMIT = -1;