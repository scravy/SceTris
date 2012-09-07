/* Configuration.aj / 5:17:34 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.aspects;

import static de.fu.junction.MD5.*;

import org.apache.log4j.Logger;

import java.sql.Time;

import de.fu.scetris.data.Person;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.orm.DatabaseException;
import de.fu.scetris.data.Day;

/**
 * This aspect hooks additional special installation actions into the RelationManager
 * <p>
 * By hooking into the RelationManagers install-method, we can
 * do some special actions without touching the auto-generated code.
 * Since install() is used throughout all installers (both the
 * CLI as well as the GUI installer, including ant-build-scripts) it
 * is used everywhere.
 * 
 * @author Julian Fleischer
 */
public aspect InstallRoles {
    
    String[] $days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"}; 
        
    after() returning: call(boolean RelationManager.install()) {
        RelationManager $this = (RelationManager) thisJoinPoint.getTarget();
        
        int $j = 0;
        try {
            for (String $dayName : $days) {
                Day $day = $this.createDay($dayName);
                for (int $i = 0; $i < 12; $i++) {
                    $this.createTimeslot($day, Time.valueOf("0" + ($i + 8) + ":00:00"));
                    $j++;
                }
            }
        String admin = "admin";
        String admin_pw = "score2011";
        String pwd = "score2011";
        
        $this.createPerson(admin, admin, admin, md5(admin_pw)).setIsSuperuser(true).pushChanges();
        $this.createPerson("Stein", "der Beisser", "steinbeisser", md5("dreitagewach")).setIsSuperuser(true).pushChanges();

        Person person = $this.createPerson("Thomas", "Leveque", "tleveque", md5(pwd));
        person.setIsSuperuser(true);
        person.pushChanges();

        person = $this.createPerson("Ivica", "Crnkovi", "icrnkovi", md5(pwd));
        person.setIsSuperuser(true);
        person.pushChanges();

        person = $this.createPerson("David", "Bialik", "dbialik", md5(pwd));
        person = $this.createPerson("Julian", "Fleischer", "jfleischer", md5(pwd));
        person = $this.createPerson("Hagen", "Mahnke", "hmahnke", md5(pwd));
        person = $this.createPerson("Konrad", "Reiche", "kreiche", md5(pwd));
        person = $this.createPerson("André", "Zoufahl", "azoufahl", md5(pwd));
        
        } catch (DatabaseException $exc) {}
    }


}
