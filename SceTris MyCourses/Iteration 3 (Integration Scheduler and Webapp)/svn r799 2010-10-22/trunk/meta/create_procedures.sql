DROP LANGUAGE IF EXISTS plpgsql CASCADE;
CREATE LANGUAGE plpgsql;

-- Schreibt eine Person in eine CourseInstance? ein, das heißt: Setzt personEnrolledInCourseInstance. 
-- Die Mindestanforderungen an die Funktion ist, dass die CourseInstance? gebucht ist.
-- Darüber hinaus werden zwei weitere Requirements unterschieden:

-- Req1: Die Funktion bucht automatisch CourseElementInstances? zu denen es keine Alternativen gibt. 
-- Dies ist genau dann der Fall, wenn die Summe der Eigenschaft duration aller CourseElementInstances? die zu einem CourseElement? 
-- gehören gleich der Eigenschaft duration dieses CourseElements? ist. Buchen heißt hier, dass sie personTakesPartInElementInstance setzt.

-- Req2: Die Funktion prüft, ob die benötigten Kurse erfolgreich abgeschlossen wurden – also, ob alle Courses die von dem 
-- Course der zu buchenden CourseInstance? via courseRequiresCourse als Vorraussetzung definiert wurden von der buchenden Person 
-- via personSuccessfullyPassedCourse abgeschlossen wurden.
--
-- Min: { Person, CourseInstance?, personEnrolledInCourseInstance }
-- Req1: + { personTakesPartInElementInstance, CourseElement? }
-- Req2: + { personSuccessfullyPassedCourse, courseRequiresCourse, Course }


CREATE OR REPLACE FUNCTION enroll (_person VARCHAR, _coinid INT) RETURNS VOID AS $FOO$
		DECLARE persid INT;
		DECLARE celocal RECORD;
		DECLARE req RECORD;
		BEGIN
			SELECT INTO persid id FROM "scetris"."Person" AS P WHERE P."login_name"=_person;
			RAISE NOTICE 'Userid here is %', persid;

			-- req2
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


			-- min
			INSERT INTO "scetris"."personEnrolledInCourseInstance" ("user","course_instance") VALUES (persid, _coinid);
			
			-- req1
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


-- CHECK PERSON <-> (ROLE) <-> PRIVILEGE
CREATE OR REPLACE FUNCTION may (_person VARCHAR, _privilege VARCHAR, _target VARCHAR DEFAULT '') RETURNS BOOLEAN AS $FOO$
		DECLARE ret INT; 
		BEGIN 
			SELECT INTO ret (count(*)) 	FROM "scetris"."personHasPrivilege" AS pHP 
										JOIN "scetris"."Person" AS P ON pHP.user=P.id 
										JOIN "scetris"."Privilege" AS Pr ON pHP."privilege" = Pr."id" 
										WHERE P."login_name"=_person AND Pr."name"=_privilege AND (pHP."target"=_target OR pHP."target"=''); 
			IF ret > 0 THEN
				RETURN true;
			END IF;
			SELECT INTO ret (count(*)) 	FROM "scetris"."Person" AS P
										JOIN "scetris"."personHasRole" AS pHR ON P."id"=pHR."user"
										JOIN "scetris"."Role" AS R ON pHR."role"=R."id"
										JOIN "scetris"."roleImpliesPrivilege" AS rIP ON R."id"=rIP.role
										JOIN "scetris"."Privilege" AS Pr ON rIP."privilege" = Pr.id
										WHERE P."login_name"=_person AND Pr."name"=_privilege;
			IF ret > 0 THEN
				RETURN true;
			END IF;
			RETURN false;
		END; $FOO$ 
		LANGUAGE 'plpgsql';
		
		
-- PERSON <-> PRIVILEGE		
CREATE OR REPLACE FUNCTION allow (_person VARCHAR, _privilege VARCHAR) RETURNS VOID AS $FOO$
		DECLARE userid INT;
		DECLARE privid INT;
		BEGIN
			SELECT INTO userid id FROM "scetris"."Person" AS P WHERE P."login_name"=_person;
			SELECT INTO privid id FROM "scetris"."Privilege" AS P WHERE P."name"=_privilege; 
			INSERT INTO "scetris"."personHasPrivilege" ("user","privilege", "target") VALUES (userid, privid, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';
	
CREATE OR REPLACE FUNCTION disallow (_person VARCHAR, _privilege VARCHAR) RETURNS VOID AS $FOO$
		DECLARE userid INT;
		DECLARE privid INT;
		BEGIN
			SELECT INTO userid id FROM "scetris"."Person" AS P WHERE P."login_name"=_person;
			SELECT INTO privid id FROM "scetris"."Privilege" AS P WHERE P."name"=_privilege; 
			DELETE FROM "scetris"."personHasPrivilege" WHERE "user"=userid AND "privilege"=privid;
		END; $FOO$ 
		LANGUAGE 'plpgsql';
		
		
				
-- PRIVILEGE <-> ROLE		
CREATE OR REPLACE FUNCTION roleallow (_privilege VARCHAR, _role VARCHAR) RETURNS VOID AS $FOO$
		DECLARE privid INT;
		DECLARE roleid INT;
		BEGIN
			SELECT INTO privid id FROM "scetris"."Privilege" AS P WHERE P."name"=_privilege; 
			SELECT INTO roleid id FROM "scetris"."Role" AS R WHERE R."name"=_role; 
			INSERT INTO "scetris"."roleImpliesPrivilege" ("role","privilege", "target") VALUES (roleid, privid, '');
		END; $FOO$ 
		LANGUAGE 'plpgsql';
	
CREATE OR REPLACE FUNCTION roledisallow (_privilege VARCHAR, _role VARCHAR) RETURNS VOID AS $FOO$
		DECLARE privid INT;
		DECLARE roleid INT;
		BEGIN
			SELECT INTO privid id FROM "scetris"."Privilege" AS P WHERE P."name"=_privilege; 
			SELECT INTO roleid id FROM "scetris"."Role" AS R WHERE R."name"=_role; 
			DELETE FROM "scetris"."roleImpliesPrivilege" WHERE "role"=roleid AND "privilege"=privid;
		END; $FOO$ 
		LANGUAGE 'plpgsql';
	
	
	
-- PERSON <-> ROLE	
CREATE OR REPLACE FUNCTION assign (_person VARCHAR, _role VARCHAR) RETURNS VOID AS $FOO$
		DECLARE userid INT;
		DECLARE roleid INT;
		BEGIN
			SELECT INTO userid id FROM "scetris"."Person" AS P WHERE P."login_name"=_person;
			SELECT INTO roleid id FROM "scetris"."Role" AS R WHERE R."name"=_role; 
			INSERT INTO "scetris"."personHasRole" ("user","role") VALUES (userid, roleid);
		END; $FOO$ 
		LANGUAGE 'plpgsql';
	
CREATE OR REPLACE FUNCTION unassign (_person VARCHAR, _role VARCHAR) RETURNS VOID AS $FOO$
		DECLARE userid INT;
		DECLARE roleid INT;
		BEGIN
			SELECT INTO userid id FROM "scetris"."Person" AS P WHERE P."login_name"=_person;
			SELECT INTO roleid id FROM "scetris"."Role" AS R WHERE R."name"=_role; 
			DELETE FROM "scetris"."personHasRole" WHERE "user"=userid AND "role"=privid;
		END; $FOO$ 
		LANGUAGE 'plpgsql';			
		
		
		
--CREATE OR REPLACE FUNCTION allow (_person VARCHAR, _privilege VARCHAR) RETURNS VOID AS $FOO$
--		BEGIN
--
--		END; $FOO$ 
--		LANGUAGE 'plpgsql';