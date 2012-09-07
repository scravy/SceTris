/* SimpleQuiz.java / 5:38:21 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package quiz;

import quiz.Quizlet.Answer;
import quiz.Quizlet.Question;
import quiz.Quizlet.Quiz;
import static quiz.Quizlet.Option.*;

@Quiz(title = "Simple Quiz",
	  questions = {
		@Question(ask = "What is “drölf”?",
				  answers = {
						  @Answer(truth = RIGHT, answer = "12"),
						  @Answer(truth = RIGHT, answer = "13"),
						  @Answer(truth = WRONG, answer = "42")
		})
})
public class SimpleQuiz {

}
