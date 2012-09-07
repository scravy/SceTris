DROP TABLE IF EXISTS "access_policy" CASCADE;
DROP TABLE IF EXISTS "access_policy_useritem" CASCADE;
DROP TABLE IF EXISTS "access_policy_roleitem" CASCADE;
DROP TABLE IF EXISTS "user_has_role" CASCADE;
DROP TABLE IF EXISTS "role_has_permission" CASCADE;
DROP TABLE IF EXISTS "user_has_permission" CASCADE;
DROP TABLE IF EXISTS "user_has_attribute" CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS "role" CASCADE;
DROP TABLE IF EXISTS "permission" CASCADE;
DROP TABLE IF EXISTS "attribute" CASCADE;

DROP SEQUENCE IF EXISTS lecturer_id_seq CASCADE;
DROP SEQUENCE IF EXISTS course_id_seq CASCADE;
DROP SEQUENCE IF EXISTS room_id_seq CASCADE;

DROP TABLE IF EXISTS lecturer CASCADE;
DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS room CASCADE;




-- basic entities

CREATE TABLE "user" (
	"u_name" VARCHAR(200) NOT NULL,
	"u_superuser" BOOLEAN DEFAULT false,
	"u_password" CHAR(32) NOT NULL,
	
	PRIMARY KEY ("u_name")
);

CREATE TABLE "role" (
	"r_name" VARCHAR(200) NOT NULL,
	
	PRIMARY KEY ("r_name")
);

CREATE TABLE "permission" (
	"p_name" VARCHAR(200) NOT NULL,
	
	PRIMARY KEY ("p_name")
);

CREATE TABLE "attribute" (
	"a_name" VARCHAR(200) NOT NULL,
	"a_type" VARCHAR,
	
	PRIMARY KEY ("a_name")
);

CREATE TABLE "access_policy" (
	"ap_id" SERIAL,
	
	PRIMARY KEY ("ap_id")
);

CREATE TABLE "access_policy_useritem" (
	"ap_id" SERIAL NOT NULL REFERENCES "access_policy",
	"ap_action" VARCHAR(200) NOT NULL,
	"ap_user" VARCHAR(200) NOT NULL REFERENCES "user",
	
	PRIMARY KEY ("ap_id", "ap_action", "ap_user")
);

CREATE TABLE "access_policy_roleitem" (
	"ap_id" SERIAL NOT NULL REFERENCES "access_policy",
	"ap_action" VARCHAR(200) NOT NULL,
	"ap_role" VARCHAR(200) NOT NULL REFERENCES "role",
	
	PRIMARY KEY ("ap_id", "ap_action", "ap_role")
);

-- relationships between entities

CREATE TABLE "user_has_role" (
	"u_name" VARCHAR(200) NOT NULL REFERENCES "user"
		ON DELETE CASCADE,
	"r_name" VARCHAR(200) NOT NULL REFERENCES "role"
		ON DELETE CASCADE,
	
	PRIMARY KEY ("u_name", "r_name")
);

CREATE TABLE "user_has_attribute" (
	"u_name" VARCHAR(200) NOT NULL REFERENCES "user"
		ON DELETE CASCADE,
	"a_name" VARCHAR(200) NOT NULL REFERENCES "attribute"
		ON DELETE CASCADE,
		
	value VARCHAR,
	
	PRIMARY KEY ("u_name", "a_name")
);

CREATE TABLE "role_has_permission" (
	"r_name" VARCHAR(200) NOT NULL REFERENCES "role"
		ON DELETE CASCADE,
	"p_name" VARCHAR(200) NOT NULL REFERENCES "permission"
		ON DELETE CASCADE,
	
	"may_grant" BOOLEAN DEFAULT false,
	
	PRIMARY KEY ("r_name", "p_name")
);

CREATE TABLE "user_has_permission" (
	"u_name" VARCHAR(200) NOT NULL REFERENCES "user"
		ON DELETE CASCADE,
	"p_name" VARCHAR(200) NOT NULL REFERENCES "permission"
		ON DELETE CASCADE,
	
	"may_grant" BOOLEAN DEFAULT false,
	
	PRIMARY KEY ("u_name", "p_name")
);

-- scheduler entities

CREATE SEQUENCE lecturer_id_seq;
CREATE SEQUENCE course_id_seq;
CREATE SEQUENCE room_id_seq;

CREATE TABLE lecturer (
	id BIGINT DEFAULT nextval('lecturer_id_seq') NOT NULL,
	firstname VARCHAR(200) NOT NULL,
	surname VARCHAR(200) NOT NULL,

	CONSTRAINT pk_lecturer_id PRIMARY KEY (id)
);


CREATE TABLE course (
	id BIGINT DEFAULT nextval('course_id_seq') NOT NULL,
	course_name VARCHAR(255) NOT NULL,
	duration INT NOT NULL,
	seats INT NOT NULL,
	lecturer BIGINT NOT NULL,

	CONSTRAINT pk_course_id PRIMARY KEY (id),
	CONSTRAINT fk_course_lecturer FOREIGN KEY (lecturer) REFERENCES lecturer(id)
);



CREATE TABLE room (
	id BIGINT DEFAULT nextval('room_id_seq') NOT NULL,
	room_name VARCHAR(200) NOT NULL,
	capacity INT NOT NULL,

	CONSTRAINT pk_room_id PRIMARY KEY (id)
);
	

-- some stored procedures for ease of programming

DROP LANGUAGE IF EXISTS plpgsql CASCADE;
CREATE LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "create_superuser" (name VARCHAR(200), password TEXT) RETURNS BOOLEAN AS $BAZINGA$
BEGIN
	BEGIN
		INSERT INTO "user" ("u_name", "u_password", "u_superuser") VALUES (name, MD5(password), true);
	EXCEPTION WHEN unique_violation THEN
		RETURN FALSE;
	END;
	RETURN TRUE;
END;
$BAZINGA$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "create_user" (
	name VARCHAR(200),
	password TEXT,
	roles VARCHAR(200)[] = ARRAY[]::VARCHAR(200)[]
	) RETURNS BOOLEAN AS $BAZINGA$
DECLARE
	res BOOLEAN;
BEGIN
	BEGIN
		INSERT INTO "user" ("u_name", "u_password") VALUES (name, MD5(password));
		IF array_length(roles, 1) > 0 THEN
		FOR i IN 1..array_length(roles, 1) LOOP
			SELECT "grant_role"(name, roles[i]) INTO res;
		END LOOP;
	END IF;
	EXCEPTION WHEN unique_violation THEN
		RETURN FALSE;
	END;
	RETURN TRUE;
END;
$BAZINGA$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "create_permission" (name VARCHAR(200)) RETURNS BOOLEAN AS $BAZINGA$
BEGIN
	BEGIN
		INSERT INTO "permission" ("p_name") VALUES (name);
	EXCEPTION WHEN unique_violation THEN
		RETURN FALSE;
	END;
	RETURN TRUE;
END;
$BAZINGA$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "create_role" (name VARCHAR(200), perms VARCHAR(200)[] = ARRAY[]::VARCHAR(200)[])
	RETURNS BOOLEAN AS $BAZINGA$
DECLARE
	res BOOLEAN;
BEGIN
	BEGIN
		INSERT INTO "role" ("r_name") VALUES (name);
	EXCEPTION WHEN unique_violation THEN
		RETURN FALSE;
	END;
	IF array_length(perms, 1) > 0 THEN
		FOR i IN 1..array_length(perms, 1) LOOP
			SELECT create_permission(perms[i]) INTO res;
			INSERT INTO "role_has_permission" ("r_name", "p_name") VALUES (name, perms[i]);
		END LOOP;
	END IF;
	RETURN TRUE;
END;
$BAZINGA$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "permissions" ("name" VARCHAR(200))
	RETURNS SETOF VARCHAR(200) AS $BAZINGA$
DECLARE
	"row" RECORD;
BEGIN
	FOR "row" IN SELECT "p_name" FROM "user_has_role" NATURAL JOIN "role_has_permission" WHERE "u_name" = "name"
	LOOP
		RETURN NEXT "row"."p_name";
	END LOOP;
	RETURN;
END;
$BAZINGA$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION may (name VARCHAR(200), permission VARCHAR(200)) RETURNS BOOLEAN AS $BAZINGA$
DECLARE
	num RECORD;
BEGIN
	SELECT * INTO num FROM "user_has_role" NATURAL JOIN "role_has_permission"
		WHERE ("u_name" = name AND "p_name" = permission) OR "u_superuser" = true;
	IF NOT FOUND THEN
		SELECT * INTO num FROM "user_has_permission"
			WHERE ("u_name" = name AND "p_name" = permission);
		IF NOT FOUND THEN
			RETURN false;
		ELSE
			RETURN true;
		END IF;
	ELSE
		RETURN true;
	END IF;
END;
$BAZINGA$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "grant_role" (name VARCHAR(200), role VARCHAR(200)) RETURNS BOOLEAN AS $BAZINGA$
BEGIN
	BEGIN
		INSERT INTO "user_has_role" ("u_name", "r_name") VALUES (name, role);
	EXCEPTION WHEN unique_violation THEN
		RETURN true;
	END;
	RETURN true;
END;
$BAZINGA$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION "grant_permission" (name VARCHAR(200), permission VARCHAR(200)) RETURNS BOOLEAN AS $BAZINGA$
DECLARE allowed BOOLEAN;
BEGIN
	SELECT may(name, permission) INTO allowed;
	IF allowed <> true THEN
		INSERT INTO "user_has_permission" ("u_name", "p_name") VALUES (name, permission);
	END IF;
	RETURN true;
END;
$BAZINGA$ LANGUAGE plpgsql;
