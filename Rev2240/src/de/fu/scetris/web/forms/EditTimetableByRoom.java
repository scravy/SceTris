/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import org.apache.log4j.Logger;

import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.data.formsupport.AbstractForm;

public class EditTimetableByRoom extends AbstractForm {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    @Field
    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public int newts;

    @Field
    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public int room_id;

    public int changeRoom = 0;

    @Override
    public boolean commit() throws Exception {
        Logger $logger = Logger.getLogger(getClass());
        $logger.info("\n ##################\n" +
                "room_id :: " + room_id);

        ProposedScheduling $ps = (ProposedScheduling) session().getValue("x_ps");
        if ($ps != null) {
            $logger.info("\n psroomid ::" + $ps.getRoom().getId());
            if (room_id != $ps.getRoom().getId()) {
                changeRoom = room_id;
                Room $r = manager().getRoom(room_id);
                $ps.setRoom($r);
                session().setValue("x_ps", $ps);
                return true;
                // session().setValue("x_newroom", $r);
            } else {
                Timeslot $ts = manager().getTimeslot(newts);
                Room $r = manager().getRoom(room_id);
                $ps
                        .setTimeslot($ts)
                        .setRoom($r)
                        .pushChanges();
                session().setValue("x_ps", null);
                session().setValue("x_newroom", null);
            }
            return true;
        }
        return false;
    }

    @Override
    public void init() {}
}
