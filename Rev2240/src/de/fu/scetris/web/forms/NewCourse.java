/* NewCourse.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import java.util.List;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.Department;

@Author("André Zoufahl")
public class NewCourse extends Course.Form {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    /*
     * @Field
     * 
     * @Multiple
     * 
     * @FormControl(Control.HIDDEN) public int[] requiredFeatureId;
     * 
     * 
     * @Field
     * 
     * @Multiple
     * 
     * @FormControl(Control.HIDDEN) public int[] requiredFeatureMinQuantity;
     * 
     * @Field
     * 
     * @Multiple
     * 
     * @FormControl(Control.HIDDEN) public int[] requiredFeatureBetterQuantity;
     */

    @Override
    public boolean commit() throws Exception {
        Course $new = manager().newCourse(name);
        if (department != null) {
            $new.setDepartment(manager().getDepartment(department));
        }
        $new.create();
        /*
         * for (int i = 0; i < requiredFeatureMinQuantity.length; i++) {
         * manager().fullyCreateCourseRequiresFeature($new,
         * manager().getFeature(requiredFeatureId[i]), requiredFeatureMinQuantity[i],
         * requiredFeatureBetterQuantity[i]); }
         */
        return true;
    }

    @Override
    public void init() {
        try {
            List<Department> $departments = manager().getDepartment();
            for (Department $dep : $departments) {
                department$alternatives.put($dep.id(), $dep.getName());
            }
        } catch (Exception $exc) {}
    }
}
