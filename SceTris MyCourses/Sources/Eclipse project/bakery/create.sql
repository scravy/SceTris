SET client_min_messages TO warning;
DROP SCHEMA IF EXISTS "scetris" CASCADE;
CREATE SCHEMA "scetris";
SET search_path = "scetris";
CREATE TABLE "scetris"."AcademicTerm" (
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"start" DATE NOT NULL,
	"end" DATE NOT NULL,
	"start_lessons" DATE NOT NULL,
	"end_lessons" DATE NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Attribute" (
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"type" VARCHAR NOT NULL DEFAULT 'string',
	"unique_value" BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Building" (
	"id" SERIAL NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"name" VARCHAR,
	"address" VARCHAR NOT NULL,
	"department" INTEGER,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Configuration" (
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"key" VARCHAR NOT NULL,
	"value" VARCHAR,
	PRIMARY KEY ("key")
);
CREATE TABLE "scetris"."Course" (
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"department" INTEGER,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."CourseAttribute" (
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"type" VARCHAR NOT NULL DEFAULT 'string',
	"unique_value" BOOLEAN NOT NULL DEFAULT false,
	"required" BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."CourseElement" (
	"id" SERIAL NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"name" VARCHAR,
	"part_of" INTEGER NOT NULL,
	"duration" INTEGER NOT NULL,
	"type" INTEGER,
	"required" BOOLEAN NOT NULL DEFAULT true,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."CourseElementInstance" (
	"id" SERIAL NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"course_instance" INTEGER NOT NULL,
	"course_element" INTEGER NOT NULL,
	"starting_timeslot" INTEGER,
	"lecturer" INTEGER,
	"duration" INTEGER NOT NULL,
	"schedulable_lesson" BOOLEAN NOT NULL DEFAULT true,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."CourseElementType" (
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."CourseInstance" (
	"id" SERIAL NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"program" INTEGER NOT NULL,
	"instance_of" INTEGER NOT NULL,
	"start" DATE NOT NULL,
	"end" DATE NOT NULL,
	"main_lecturer" INTEGER NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Day" (
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Department" (
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"superordinate_department" INTEGER,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Feature" (
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"description" TEXT,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Person" (
	"id" SERIAL NOT NULL,
	"ctime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"mtime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"created_by" INTEGER,
	"modified_by" INTEGER,
	"first_name" VARCHAR NOT NULL,
	"additional_names" VARCHAR,
	"last_name" VARCHAR NOT NULL,
	"email_address" VARCHAR,
	"login_name" VARCHAR NOT NULL,
	"login_password" VARCHAR (32) NOT NULL,
	"home_department" INTEGER,
	"current_year" INTEGER,
	"last_login" TIMESTAMP (3),
	"login_count" INTEGER NOT NULL DEFAULT 0,
	"is_superuser" BOOLEAN NOT NULL DEFAULT false,
	"deleted" TIMESTAMP (3),
	"deleted_by" INTEGER,
	"is_student" BOOLEAN NOT NULL DEFAULT false,
	"is_lecturer" BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Privilege" (
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Program" (
	"id" SERIAL NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"academic_term" INTEGER NOT NULL,
	"department" INTEGER NOT NULL,
	"freezed" BOOLEAN NOT NULL DEFAULT false,
	"published" BOOLEAN NOT NULL DEFAULT false,
	"program_manager" INTEGER NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."ProposedScheduling" (
	"id" SERIAL NOT NULL,
	"priority" INTEGER NOT NULL,
	"ctime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"mtime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"created_by" INTEGER,
	"modified_by" INTEGER,
	"element_instance" INTEGER NOT NULL,
	"timeslot" INTEGER,
	"room" INTEGER,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Role" (
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Room" (
	"id" SERIAL NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"name" VARCHAR,
	"number" VARCHAR NOT NULL,
	"building" INTEGER NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Timeslot" (
	"id" SERIAL NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"day" INTEGER NOT NULL,
	"startingTime" TIME NOT NULL,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."Year" (
	"id" SERIAL NOT NULL,
	"name" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("id")
);
CREATE TABLE "scetris"."courseHasCourseAttribute" (
	"course" INTEGER NOT NULL,
	"attribute" INTEGER NOT NULL,
	"time" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"value" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("course", "attribute", "time")
);
CREATE TABLE "scetris"."courseRecommendedForYear" (
	"course" INTEGER NOT NULL,
	"year" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("course", "year")
);
CREATE TABLE "scetris"."courseRequiresCourse" (
	"course" INTEGER NOT NULL,
	"dependency" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("course", "dependency")
);
CREATE TABLE "scetris"."elementInstancePrefersRoom" (
	"element_instance" INTEGER NOT NULL,
	"room" INTEGER NOT NULL,
	"priority" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("element_instance", "room")
);
CREATE TABLE "scetris"."elementInstancePrefersTimeslot" (
	"element_instance" INTEGER NOT NULL,
	"timeslot" INTEGER NOT NULL,
	"priority" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("element_instance", "timeslot")
);
CREATE TABLE "scetris"."elementInstanceRequiresFeature" (
	"element_instance" INTEGER NOT NULL,
	"feature" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"quantity_min" INTEGER NOT NULL,
	"quantity_better" INTEGER NOT NULL,
	PRIMARY KEY ("element_instance", "feature")
);
CREATE TABLE "scetris"."elementInstanceTakesPlaceInRoom" (
	"element_instance" INTEGER NOT NULL,
	"room" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"timeslot" INTEGER NOT NULL,
	PRIMARY KEY ("element_instance", "room")
);
CREATE TABLE "scetris"."elementRequiresFeature" (
	"course_element" INTEGER NOT NULL,
	"feature" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"quantity_min" INTEGER NOT NULL,
	"quantity_better" INTEGER NOT NULL,
	PRIMARY KEY ("course_element", "feature")
);
CREATE TABLE "scetris"."personEnrolledInCourseInstance" (
	"user" INTEGER NOT NULL,
	"course_instance" INTEGER NOT NULL,
	"ctime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"mtime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"created_by" INTEGER,
	"modified_by" INTEGER,
	PRIMARY KEY ("user", "course_instance")
);
CREATE TABLE "scetris"."personGivesCourse" (
	"person" INTEGER NOT NULL,
	"course" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"priority" INTEGER NOT NULL,
	PRIMARY KEY ("person", "course")
);
CREATE TABLE "scetris"."personHasAttribute" (
	"user" INTEGER NOT NULL,
	"attribute" INTEGER NOT NULL,
	"time" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"value" VARCHAR NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("user", "attribute", "time")
);
CREATE TABLE "scetris"."personHasPrivilege" (
	"user" INTEGER NOT NULL,
	"privilege" INTEGER NOT NULL,
	"ctime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"mtime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"created_by" INTEGER,
	"modified_by" INTEGER,
	"target" VARCHAR NOT NULL DEFAULT '',
	"because_of_role" BOOLEAN,
	PRIMARY KEY ("user", "privilege", "target")
);
CREATE TABLE "scetris"."personHasRole" (
	"user" INTEGER NOT NULL,
	"role" INTEGER NOT NULL,
	"ctime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"mtime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"created_by" INTEGER,
	"modified_by" INTEGER,
	PRIMARY KEY ("user", "role")
);
CREATE TABLE "scetris"."personPrefersTimeslot" (
	"user" INTEGER NOT NULL,
	"timeslot" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"priority" INTEGER NOT NULL,
	PRIMARY KEY ("user", "timeslot")
);
CREATE TABLE "scetris"."personSuccessfullyPassedCourse" (
	"user" INTEGER NOT NULL,
	"course" INTEGER NOT NULL,
	"ctime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"mtime" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"created_by" INTEGER,
	"modified_by" INTEGER,
	"grade" VARCHAR,
	"notes" TEXT,
	PRIMARY KEY ("user", "course")
);
CREATE TABLE "scetris"."personTakesPartInElementInstance" (
	"user" INTEGER NOT NULL,
	"element_instance" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"timeslot" INTEGER NOT NULL,
	PRIMARY KEY ("user", "element_instance")
);
CREATE TABLE "scetris"."roleImpliesAttribute" (
	"role" INTEGER NOT NULL,
	"attribute" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"default" VARCHAR,
	"required" BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY ("role", "attribute")
);
CREATE TABLE "scetris"."roleImpliesPrivilege" (
	"role" INTEGER NOT NULL,
	"privilege" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"target" VARCHAR NOT NULL DEFAULT '',
	PRIMARY KEY ("role", "privilege", "target")
);
CREATE TABLE "scetris"."roomPrefersTimeslot" (
	"room" INTEGER NOT NULL,
	"timeslot" INTEGER NOT NULL,
	"priority" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("room", "timeslot")
);
CREATE TABLE "scetris"."roomProvidesFeature" (
	"room" INTEGER NOT NULL,
	"feature" INTEGER NOT NULL,
	"timekey" TIMESTAMP (3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"quantity" INTEGER NOT NULL,
	PRIMARY KEY ("room", "feature")
);
CREATE UNIQUE INDEX uniq_id35820444_name ON "scetris"."AcademicTerm" ("name");

CREATE UNIQUE INDEX uniq_id35820600_name ON "scetris"."Attribute" ("name");

CREATE UNIQUE INDEX uniq_id35820756_name ON "scetris"."Building" ("name");

CREATE UNIQUE INDEX uniq_id35820763_address ON "scetris"."Building" ("address");

CREATE UNIQUE INDEX uniq_id35820949_name ON "scetris"."Course" ("name");

CREATE UNIQUE INDEX uniq_id35821138_name ON "scetris"."CourseAttribute" ("name");

CREATE UNIQUE INDEX uniq_id35821733_name ON "scetris"."CourseElementType" ("name");

CREATE UNIQUE INDEX uniq_id35822047_name ON "scetris"."Day" ("name");

CREATE UNIQUE INDEX uniq_id35822189_name ON "scetris"."Department" ("name");

CREATE UNIQUE INDEX uniq_id35822318_name ON "scetris"."Feature" ("name");

CREATE UNIQUE INDEX uniq_id35822931_email_address ON "scetris"."Person" ("email_address");

CREATE UNIQUE INDEX uniq_id35822938_login_name ON "scetris"."Person" ("login_name");

CREATE UNIQUE INDEX uniq_id35823052_name ON "scetris"."Privilege" ("name");

CREATE UNIQUE INDEX uniq_id35823542_element_instance ON "scetris"."ProposedScheduling" ("element_instance", "timeslot", "room");

CREATE UNIQUE INDEX uniq_id35823665_name ON "scetris"."Role" ("name");

CREATE UNIQUE INDEX uniq_id35823828_name ON "scetris"."Room" ("name", "building");

CREATE UNIQUE INDEX uniq_id35823840_number ON "scetris"."Room" ("number", "building");

CREATE UNIQUE INDEX uniq_id35824098_name ON "scetris"."Year" ("name");

CREATE UNIQUE INDEX uniq_id35826040_user ON "scetris"."personTakesPartInElementInstance" ("user", "timeslot");

ALTER TABLE "scetris"."Building" ADD FOREIGN KEY ("department") REFERENCES "scetris"."Department" ON DELETE CASCADE;
ALTER TABLE "scetris"."Course" ADD FOREIGN KEY ("department") REFERENCES "scetris"."Department" ON DELETE CASCADE;
ALTER TABLE "scetris"."CourseElement" ADD FOREIGN KEY ("part_of") REFERENCES "scetris"."Course" ON DELETE CASCADE;
ALTER TABLE "scetris"."CourseElement" ADD FOREIGN KEY ("type") REFERENCES "scetris"."CourseElementType" ON DELETE CASCADE;
ALTER TABLE "scetris"."CourseElementInstance" ADD FOREIGN KEY ("course_instance") REFERENCES "scetris"."CourseInstance" ON DELETE CASCADE;
ALTER TABLE "scetris"."CourseElementInstance" ADD FOREIGN KEY ("course_element") REFERENCES "scetris"."CourseElement" ON DELETE CASCADE;
ALTER TABLE "scetris"."CourseElementInstance" ADD FOREIGN KEY ("starting_timeslot") REFERENCES "scetris"."Timeslot" ON DELETE CASCADE;
ALTER TABLE "scetris"."CourseElementInstance" ADD FOREIGN KEY ("lecturer") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."CourseInstance" ADD FOREIGN KEY ("program") REFERENCES "scetris"."Program" ON DELETE CASCADE;
ALTER TABLE "scetris"."CourseInstance" ADD FOREIGN KEY ("instance_of") REFERENCES "scetris"."Course" ON DELETE CASCADE;
ALTER TABLE "scetris"."CourseInstance" ADD FOREIGN KEY ("main_lecturer") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."Department" ADD FOREIGN KEY ("superordinate_department") REFERENCES "scetris"."Department" ON DELETE CASCADE;
ALTER TABLE "scetris"."Person" ADD FOREIGN KEY ("created_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."Person" ADD FOREIGN KEY ("modified_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."Person" ADD FOREIGN KEY ("home_department") REFERENCES "scetris"."Department" ON DELETE CASCADE;
ALTER TABLE "scetris"."Person" ADD FOREIGN KEY ("current_year") REFERENCES "scetris"."Year" ON DELETE CASCADE;
ALTER TABLE "scetris"."Person" ADD FOREIGN KEY ("deleted_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."Program" ADD FOREIGN KEY ("academic_term") REFERENCES "scetris"."AcademicTerm" ON DELETE CASCADE;
ALTER TABLE "scetris"."Program" ADD FOREIGN KEY ("department") REFERENCES "scetris"."Department" ON DELETE CASCADE;
ALTER TABLE "scetris"."Program" ADD FOREIGN KEY ("program_manager") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."ProposedScheduling" ADD FOREIGN KEY ("created_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."ProposedScheduling" ADD FOREIGN KEY ("modified_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."ProposedScheduling" ADD FOREIGN KEY ("element_instance") REFERENCES "scetris"."CourseElementInstance" ON DELETE CASCADE;
ALTER TABLE "scetris"."ProposedScheduling" ADD FOREIGN KEY ("timeslot") REFERENCES "scetris"."Timeslot" ON DELETE CASCADE;
ALTER TABLE "scetris"."ProposedScheduling" ADD FOREIGN KEY ("room") REFERENCES "scetris"."Room" ON DELETE CASCADE;
ALTER TABLE "scetris"."Room" ADD FOREIGN KEY ("building") REFERENCES "scetris"."Building" ON DELETE CASCADE;
ALTER TABLE "scetris"."Timeslot" ADD FOREIGN KEY ("day") REFERENCES "scetris"."Day" ON DELETE CASCADE;
ALTER TABLE "scetris"."courseHasCourseAttribute" ADD FOREIGN KEY ("course") REFERENCES "scetris"."Course" ON DELETE CASCADE;
ALTER TABLE "scetris"."courseHasCourseAttribute" ADD FOREIGN KEY ("attribute") REFERENCES "scetris"."CourseAttribute" ON DELETE CASCADE;
ALTER TABLE "scetris"."courseRecommendedForYear" ADD FOREIGN KEY ("course") REFERENCES "scetris"."Course" ON DELETE CASCADE;
ALTER TABLE "scetris"."courseRecommendedForYear" ADD FOREIGN KEY ("year") REFERENCES "scetris"."Year" ON DELETE CASCADE;
ALTER TABLE "scetris"."courseRequiresCourse" ADD FOREIGN KEY ("course") REFERENCES "scetris"."Course" ON DELETE CASCADE;
ALTER TABLE "scetris"."courseRequiresCourse" ADD FOREIGN KEY ("dependency") REFERENCES "scetris"."Course" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementInstancePrefersRoom" ADD FOREIGN KEY ("element_instance") REFERENCES "scetris"."CourseElementInstance" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementInstancePrefersRoom" ADD FOREIGN KEY ("room") REFERENCES "scetris"."Room" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementInstancePrefersTimeslot" ADD FOREIGN KEY ("element_instance") REFERENCES "scetris"."CourseElementInstance" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementInstancePrefersTimeslot" ADD FOREIGN KEY ("timeslot") REFERENCES "scetris"."Timeslot" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementInstanceRequiresFeature" ADD FOREIGN KEY ("element_instance") REFERENCES "scetris"."CourseElementInstance" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementInstanceRequiresFeature" ADD FOREIGN KEY ("feature") REFERENCES "scetris"."Feature" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementInstanceTakesPlaceInRoom" ADD FOREIGN KEY ("element_instance") REFERENCES "scetris"."CourseElementInstance" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementInstanceTakesPlaceInRoom" ADD FOREIGN KEY ("room") REFERENCES "scetris"."Room" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementInstanceTakesPlaceInRoom" ADD FOREIGN KEY ("timeslot") REFERENCES "scetris"."Timeslot" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementRequiresFeature" ADD FOREIGN KEY ("course_element") REFERENCES "scetris"."CourseElement" ON DELETE CASCADE;
ALTER TABLE "scetris"."elementRequiresFeature" ADD FOREIGN KEY ("feature") REFERENCES "scetris"."Feature" ON DELETE CASCADE;
ALTER TABLE "scetris"."personEnrolledInCourseInstance" ADD FOREIGN KEY ("user") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personEnrolledInCourseInstance" ADD FOREIGN KEY ("course_instance") REFERENCES "scetris"."CourseInstance" ON DELETE CASCADE;
ALTER TABLE "scetris"."personEnrolledInCourseInstance" ADD FOREIGN KEY ("created_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personEnrolledInCourseInstance" ADD FOREIGN KEY ("modified_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personGivesCourse" ADD FOREIGN KEY ("person") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personGivesCourse" ADD FOREIGN KEY ("course") REFERENCES "scetris"."Course" ON DELETE CASCADE;
ALTER TABLE "scetris"."personHasAttribute" ADD FOREIGN KEY ("user") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personHasAttribute" ADD FOREIGN KEY ("attribute") REFERENCES "scetris"."Attribute" ON DELETE CASCADE;
ALTER TABLE "scetris"."personHasPrivilege" ADD FOREIGN KEY ("user") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personHasPrivilege" ADD FOREIGN KEY ("privilege") REFERENCES "scetris"."Privilege" ON DELETE CASCADE;
ALTER TABLE "scetris"."personHasPrivilege" ADD FOREIGN KEY ("created_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personHasPrivilege" ADD FOREIGN KEY ("modified_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personHasRole" ADD FOREIGN KEY ("user") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personHasRole" ADD FOREIGN KEY ("role") REFERENCES "scetris"."Role" ON DELETE CASCADE;
ALTER TABLE "scetris"."personHasRole" ADD FOREIGN KEY ("created_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personHasRole" ADD FOREIGN KEY ("modified_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personPrefersTimeslot" ADD FOREIGN KEY ("user") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personPrefersTimeslot" ADD FOREIGN KEY ("timeslot") REFERENCES "scetris"."Timeslot" ON DELETE CASCADE;
ALTER TABLE "scetris"."personSuccessfullyPassedCourse" ADD FOREIGN KEY ("user") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personSuccessfullyPassedCourse" ADD FOREIGN KEY ("course") REFERENCES "scetris"."Course" ON DELETE CASCADE;
ALTER TABLE "scetris"."personSuccessfullyPassedCourse" ADD FOREIGN KEY ("created_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personSuccessfullyPassedCourse" ADD FOREIGN KEY ("modified_by") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personTakesPartInElementInstance" ADD FOREIGN KEY ("user") REFERENCES "scetris"."Person" ON DELETE CASCADE;
ALTER TABLE "scetris"."personTakesPartInElementInstance" ADD FOREIGN KEY ("element_instance") REFERENCES "scetris"."CourseElementInstance" ON DELETE CASCADE;
ALTER TABLE "scetris"."personTakesPartInElementInstance" ADD FOREIGN KEY ("timeslot") REFERENCES "scetris"."Timeslot" ON DELETE CASCADE;
ALTER TABLE "scetris"."roleImpliesAttribute" ADD FOREIGN KEY ("role") REFERENCES "scetris"."Role" ON DELETE CASCADE;
ALTER TABLE "scetris"."roleImpliesAttribute" ADD FOREIGN KEY ("attribute") REFERENCES "scetris"."Attribute" ON DELETE CASCADE;
ALTER TABLE "scetris"."roleImpliesPrivilege" ADD FOREIGN KEY ("role") REFERENCES "scetris"."Role" ON DELETE CASCADE;
ALTER TABLE "scetris"."roleImpliesPrivilege" ADD FOREIGN KEY ("privilege") REFERENCES "scetris"."Privilege" ON DELETE CASCADE;
ALTER TABLE "scetris"."roomPrefersTimeslot" ADD FOREIGN KEY ("room") REFERENCES "scetris"."Room" ON DELETE CASCADE;
ALTER TABLE "scetris"."roomPrefersTimeslot" ADD FOREIGN KEY ("timeslot") REFERENCES "scetris"."Timeslot" ON DELETE CASCADE;
ALTER TABLE "scetris"."roomProvidesFeature" ADD FOREIGN KEY ("room") REFERENCES "scetris"."Room" ON DELETE CASCADE;
ALTER TABLE "scetris"."roomProvidesFeature" ADD FOREIGN KEY ("feature") REFERENCES "scetris"."Feature" ON DELETE CASCADE;
CREATE OR REPLACE FUNCTION ping () RETURNS BOOLEAN AS $BAZINGA$
BEGIN
	RETURN TRUE;
END
$BAZINGA$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION enroll (_person VARCHAR, _coinid INT) RETURNS VOID AS $FOO$
		DECLARE persid INT;
		DECLARE celocal RECORD;
		DECLARE req RECORD;
		BEGIN
			SELECT INTO persid id FROM "scetris"."Person" AS P WHERE P."login_name"=_person;
			RAISE NOTICE 'Userid here is %', persid;

			SELECT INTO req CRC.* FROM "scetris"."courseRequiresCourse" AS CRC 
						JOIN "scetris"."CourseInstance" AS CI ON CRC.course = CI.instance_of
						WHERE CI.id = _coinid AND CRC.dependency NOT IN 
						(
							SELECT PC.course FROM "scetris"."personSuccessfullyPassedCourse" AS PC 
								WHERE PC.user=persid
						);
			IF FOUND THEN
				RAISE EXCEPTION 'Userid does not fulfill all requirements';
			ELSE
				RAISE NOTICE 'Userid fulfills all requirements';
			END IF;


			INSERT INTO "scetris"."personEnrolledInCourseInstance" ("user","course_instance") VALUES (persid, _coinid);
			
			FOR celocal IN 
				SELECT CEIX.* FROM "CourseElementInstance" AS CEIX, 
					(	SELECT CEI.course_element, SUM(CEI.duration), CE.duration 
						FROM "CourseElement" AS CE 
						JOIN "CourseElementInstance" AS CEI ON CE.id = CEI.course_element 
						WHERE CEI.course_instance = _coinid
						GROUP BY CEI.course_element, CE.duration 
						HAVING SUM(CEI.duration) = CE.duration) AS TMP 
					WHERE TMP.course_element=CEIX.course_element 
			LOOP
				INSERT INTO "scetris"."personTakesPartInElementInstance" ("user","element_instance","timeslot") VALUES (persid, celocal.id, celocal.starting_timeslot);
			END LOOP;
			

		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION may (_person VARCHAR, _privilege VARCHAR, _target VARCHAR) RETURNS BOOLEAN AS $FOO$
		BEGIN 
			PERFORM * FROM "scetris"."personHasPrivilege" AS pHP 
										JOIN "scetris"."Person" AS P ON pHP.user=P.id 
										JOIN "scetris"."Privilege" AS Pr ON pHP."privilege" = Pr."id" 
										WHERE P."login_name"=_person AND Pr."name"=_privilege AND (pHP."target"=_target OR pHP."target"=''); 
			IF FOUND THEN
				RETURN true;
			END IF;
			PERFORM * FROM "scetris"."Person" AS P
										JOIN "scetris"."personHasRole" AS pHR ON P."id"=pHR."user"
										JOIN "scetris"."Role" AS R ON pHR."role"=R."id"
										JOIN "scetris"."roleImpliesPrivilege" AS rIP ON R."id"=rIP.role
										JOIN "scetris"."Privilege" AS Pr ON rIP."privilege" = Pr.id
										WHERE P."login_name"=_person AND Pr."name"=_privilege AND (rIP."target"=_target OR rIP."target"='');
			IF FOUND THEN
				RETURN true;
			END IF;
			RETURN false;
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION may (_person VARCHAR, _privilege VARCHAR) RETURNS BOOLEAN AS $FOO$
		BEGIN
			RETURN may(_person, _privilege, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';		


CREATE OR REPLACE FUNCTION personHasPrivilege (_personid INT, _privid INT, _target VARCHAR) RETURNS BOOLEAN AS $FOO$
		BEGIN
			PERFORM * FROM "scetris"."personHasPrivilege" WHERE "user"=_personid AND "privilege"=_privid AND "target"=_target;
			IF FOUND THEN
				RETURN TRUE;
			ELSE
				RETURN FALSE;
			END IF;
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION personHasPrivilege (_personid INT, _privid INT) RETURNS BOOLEAN AS $FOO$
		BEGIN
			RETURN personHasPrivilege(_personid, _privid, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';		


CREATE OR REPLACE FUNCTION personHasAnyPrivilege (_personid INT, _privid INT, _target VARCHAR) RETURNS BOOLEAN AS $FOO$
		BEGIN
			PERFORM * FROM "scetris"."personHasPrivilege" WHERE "user"=_personid AND "privilege"=_privid;
			IF FOUND THEN
				RETURN TRUE;
			ELSE
				RETURN FALSE;
			END IF;
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION personHasAnyPrivilege (_personid INT, _privid INT) RETURNS BOOLEAN AS $FOO$
		BEGIN
			RETURN personHasAnyPrivilege (_personid, _privid, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';

	
CREATE OR REPLACE FUNCTION allow (_person VARCHAR, _privilege VARCHAR, _target VARCHAR) RETURNS BOOLEAN AS $FOO$
		DECLARE userid INT;
		DECLARE privid INT;
		BEGIN
			SELECT INTO userid id FROM "scetris"."Person" AS P WHERE P."login_name"=_person;
			SELECT INTO privid id FROM "scetris"."Privilege" AS P WHERE P."name"=_privilege;
			IF personHasPrivilege(userid, privid, _target) THEN
				RETURN FALSE;
			ELSE 
				INSERT INTO "scetris"."personHasPrivilege" ("user","privilege", "target") VALUES (userid, privid, _target);
				RETURN TRUE;
			END IF;
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION allow (_person VARCHAR, _privilege VARCHAR ) RETURNS BOOLEAN AS $FOO$
		BEGIN
			RETURN allow (_person, _privilege, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION disallow (_person VARCHAR, _privilege VARCHAR, _target VARCHAR) RETURNS BOOLEAN AS $FOO$
		DECLARE userid INT;
		DECLARE privid INT;
		BEGIN
			SELECT INTO userid id FROM "scetris"."Person" AS P WHERE P."login_name"=_person;
			SELECT INTO privid id FROM "scetris"."Privilege" AS P WHERE P."name"=_privilege; 
			IF personHasAnyPrivilege(userid, privid) THEN
				IF (_target = '') THEN
					DELETE FROM "scetris"."personHasPrivilege" WHERE "user"=userid AND "privilege"=privid;
					RETURN TRUE;
				ELSIF personHasPrivilege(userid, privid, _target)	THEN
					DELETE FROM "scetris"."personHasPrivilege" WHERE "user"=userid AND "privilege"=privid AND "target"=_target;
					RETURN TRUE;
				END IF;				
			END IF;
			RETURN FALSE;
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION disallow (_person VARCHAR, _privilege VARCHAR) RETURNS BOOLEAN AS $FOO$
		BEGIN
			RETURN disallow (_person, _privilege, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION roleImplPrivilege (_role VARCHAR, _privilege VARCHAR, _target VARCHAR) RETURNS BOOLEAN AS $FOO$
		DECLARE roleid INT;
		DECLARE privid INT;
		BEGIN
			SELECT INTO roleid id FROM "scetris"."Role" AS R WHERE R."name"=_role; 
			SELECT INTO privid id FROM "scetris"."Privilege" AS P WHERE P."name"=_privilege; 
			RETURN roleImplPrivilege(roleid, privid, _target);
		END; $FOO$	
		LANGUAGE 'plpgsql';	


CREATE OR REPLACE FUNCTION roleImplPrivilege (_role VARCHAR, _privilege VARCHAR) RETURNS BOOLEAN AS $FOO$
		BEGIN
			RETURN roleImplPrivilege (_role, _privilege, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION roleImplPrivilege (_roleid INT, _privid INT, _target VARCHAR) RETURNS BOOLEAN AS $FOO$
		BEGIN
			PERFORM * FROM "scetris"."roleImpliesPrivilege" WHERE "privilege"=_privid AND "role"=_roleid AND ("target"=_target OR "target"='');
			IF FOUND THEN
				RETURN TRUE;
			ELSE
				RETURN FALSE;
			END IF;
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION roleImplPrivilege (_roleid INT, _privid INT) RETURNS BOOLEAN AS $FOO$
		BEGIN
			RETURN roleImplPrivilege (_roleid, _privid, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';

	
CREATE OR REPLACE FUNCTION roleallow (_role VARCHAR, _privilege VARCHAR, _target VARCHAR) RETURNS BOOLEAN AS $FOO$
		DECLARE roleid INT;
		DECLARE privid INT;
		BEGIN
			SELECT INTO roleid id FROM "scetris"."Role" AS R WHERE R."name"=_role; 
			SELECT INTO privid id FROM "scetris"."Privilege" AS P WHERE P."name"=_privilege; 
			IF roleImplPrivilege(roleid, privid, _target) THEN
				RETURN FALSE;
			ELSE
				INSERT INTO "scetris"."roleImpliesPrivilege" ("role","privilege", "target") VALUES (roleid, privid, _target);
				RETURN TRUE;
			END IF;
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION roleallow (_role VARCHAR, _privilege VARCHAR) RETURNS BOOLEAN AS $FOO$
		BEGIN
			RETURN roleallow (_role, _privilege, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';

	
CREATE OR REPLACE FUNCTION roledisallow (_role VARCHAR, _privilege VARCHAR, _target VARCHAR) RETURNS BOOLEAN AS $FOO$
		DECLARE roleid INT;
		DECLARE privid INT;
		BEGIN
			SELECT INTO roleid id FROM "scetris"."Role" AS R WHERE R."name"=_role; 
			SELECT INTO privid id FROM "scetris"."Privilege" AS P WHERE P."name"=_privilege; 
			IF roleImplPrivilege(roleid, privid, _target) THEN
				DELETE FROM "scetris"."roleImpliesPrivilege" WHERE "role"=roleid AND "privilege"=privid AND ("target"=_target OR _target='');
				RETURN TRUE;
			ELSE
				RETURN FALSE;
			END IF;
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION roledisallow (_role VARCHAR, _privilege VARCHAR) RETURNS BOOLEAN AS $FOO$
		BEGIN
			RETURN roledisallow (_role, _privilege, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION personHasRole (_personid INT, _roleid INT) RETURNS BOOLEAN AS $FOO$
		BEGIN
			PERFORM * FROM "scetris"."personHasRole" WHERE "user"=_personid AND "role"=_roleid;
			IF FOUND THEN
				RETURN TRUE;
			ELSE
				RETURN FALSE;
			END IF;
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION assign (_person VARCHAR, _role VARCHAR) RETURNS BOOLEAN AS $FOO$
		DECLARE userid INT;
		DECLARE roleid INT;
		BEGIN
			SELECT INTO userid id FROM "scetris"."Person" AS P WHERE P."login_name"=_person;
			SELECT INTO roleid id FROM "scetris"."Role" AS R WHERE R."name"=_role; 
			IF personHasRole(userid, roleid) THEN
				RETURN FALSE;
			ELSE
				INSERT INTO "scetris"."personHasRole" ("user","role") VALUES (userid, roleid);
				RETURN TRUE;
			END IF;
		END; $FOO$ 
		LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION unassign (_person VARCHAR, _role VARCHAR) RETURNS BOOLEAN AS $FOO$
		DECLARE userid INT;
		DECLARE roleid INT;
		BEGIN
			SELECT INTO userid id FROM "scetris"."Person" AS P WHERE P."login_name"=_person;
			SELECT INTO roleid id FROM "scetris"."Role" AS R WHERE R."name"=_role;
			IF personHasRole(userid, roleid) THEN
				DELETE FROM "scetris"."personHasRole" WHERE "user"=userid AND "role"=roleid;
				RETURN TRUE;
			ELSE
				RETURN FALSE;
			END IF;
		END; $FOO$ 
		LANGUAGE 'plpgsql';					


CREATE OR REPLACE FUNCTION set_config (_key VARCHAR, _data VARCHAR) RETURNS VOID AS $FOO$
	BEGIN
    LOOP
        UPDATE "scetris"."Configuration" SET "value" = _data WHERE "key" = _key;
        IF found THEN
            RETURN;
        END IF;
        BEGIN
            INSERT INTO "scetris"."Configuration" ("key", "value") VALUES (_key, _data);
            RETURN;
        EXCEPTION WHEN unique_violation THEN
        END;
    END LOOP;
	END; $FOO$
	LANGUAGE 'plpgsql';


