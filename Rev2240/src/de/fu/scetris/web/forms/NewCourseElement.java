/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import java.util.List;

import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementType;

public class NewCourseElement extends CourseElement.Form {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3830194784280425511L;

    @Field
    @Multiple
    @FormControl(Control.HIDDEN)
    public int[] requiredFeatureId;

    @Field
    @Multiple
    @FormControl(Control.HIDDEN)
    public int[] requiredFeatureMinQuantity;

    @Field
    @Multiple
    @FormControl(Control.HIDDEN)
    public int[] requiredFeatureBetterQuantity;

    @Override
    public boolean commit() throws Exception {
        Course $c = manager().getCourse(partOf);
        CourseElementType $cet = manager().getCourseElementType(type);

        CourseElement $ce = manager().newCourseElement($c, duration);
        $ce
                .setName(name)
                .setRequired(required)
                .setType($cet)
                .create();

        for (int i = 0; i < requiredFeatureMinQuantity.length; i++) {
            if (requiredFeatureBetterQuantity[i] < requiredFeatureMinQuantity[i]) {
                requiredFeatureBetterQuantity[i] = requiredFeatureMinQuantity[i];
            }
            if (requiredFeatureMinQuantity[i] > 0) {
                manager().fullyCreateElementRequiresFeature(
                    $ce,
                    manager().getFeature(requiredFeatureId[i]),
                    requiredFeatureMinQuantity[i],
                    requiredFeatureBetterQuantity[i]
                        );
            }
        }

        return true;
    }

    @Override
    public void init() {
        try {
            List<Course> $courses = manager().getCourse();
            for (Course $course : $courses) {
                partOf$alternatives.put($course.id(), $course.getName());
            }

            List<CourseElementType> $tmp = manager().getCourseElementType();
            for (CourseElementType $cet : $tmp) {
                type$alternatives.put($cet.id(), $cet.getName());
            }
        } catch (Exception $exc) {}
    }
}
