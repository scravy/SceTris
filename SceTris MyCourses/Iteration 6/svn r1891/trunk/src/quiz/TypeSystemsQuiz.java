package quiz;

import quiz.Quizlet.Answer;
import quiz.Quizlet.Question;
import quiz.Quizlet.Quiz;
import static quiz.Quizlet.Option.*;

@Quiz(
	  title = "Type systems in programming languages",
	  questions = {
			  @Question(
						ask = "Which properties apply to the type system in Java 6?",
						answers = {
								@Answer(truth = RIGHT, answer = "static typing"),
								@Answer(truth = WRONG, answer = "dynamic typing"),
								@Answer(truth = WRONG, answer = "inferred types"),
								@Answer(truth = WRONG, answer = "weak typing"),
								@Answer(truth = RIGHT, answer = "strong typing"),
								@Answer(truth = RIGHT, answer = "requires manifest typing"),
								@Answer(truth = WRONG, answer = "optional manifest typing"),
								@Answer(truth = WRONG, answer = "dependent typing"),
								@Answer(truth = WRONG, answer = "duck typing")
			  }),
			  @Question(
						ask = "Which properties apply to the type system in C99?",
						answers = {
								@Answer(truth = RIGHT, answer = "static typing"),
								@Answer(truth = WRONG, answer = "dynamic typing"),
								@Answer(truth = WRONG, answer = "inferred types"),
								@Answer(truth = RIGHT, answer = "weak typing"),
								@Answer(truth = WRONG, answer = "strong typing"),
								@Answer(truth = RIGHT, answer = "requires manifest typing"),
								@Answer(truth = WRONG, answer = "optional manifest typing"),
								@Answer(truth = WRONG, answer = "dependent typing"),
								@Answer(truth = WRONG, answer = "duck typing")
			  }),
			  @Question(
						ask = "What features does Javas type hierarchy offer?",
						answers = {
								@Answer(truth = WRONG, answer = "multiple inheritance of classes"),
								@Answer(truth = RIGHT, answer = "multiple inheritance of interfaces"),
								@Answer(truth = RIGHT, answer = "generic types / type templates"),
								@Answer(truth = WRONG, answer = "multiple inheritance via mixins"),
								@Answer(truth = WRONG, answer = "prototype-based inheritance"),
								@Answer(truth = RIGHT, answer = "class-based inheritance")
			  }),
			  @Question(
						ask = "What features does the type hierarchy and object system in JavaScript offer?",
						answers = {
								@Answer(truth = WRONG, answer = "multiple inheritance of classes"),
								@Answer(truth = WRONG, answer = "multiple inheritance of interfaces"),
								@Answer(truth = WRONG, answer = "generic types / type templates"),
								@Answer(truth = WRONG, answer = "multiple inheritance via mixins"),
								@Answer(truth = RIGHT, answer = "prototype-based inheritance"),
								@Answer(truth = WRONG, answer = "class-based inheritance"),
								@Answer(truth = RIGHT, answer = "information-hiding")
			  }),
			  @Question(
						ask = "What features does C++’s type hierarchy and object system offer?",
						answers = {
								@Answer(truth = RIGHT, answer = "multiple inheritance of classes"),
								@Answer(truth = RIGHT, answer = "multiple inheritance of interfaces"),
								@Answer(truth = RIGHT, answer = "generic types / type templates"),
								@Answer(truth = WRONG, answer = "multiple inheritance via mixins"),
								@Answer(truth = WRONG, answer = "prototype-based inheritance"),
								@Answer(truth = RIGHT, answer = "class-based inheritance"),
								@Answer(truth = RIGHT, answer = "information-hiding")
			  }),
			  @Question(
						ask = "Which properties apply to the type System in Haskell 98?",
						answers = {
								@Answer(truth = RIGHT, answer = "static typing"),
								@Answer(truth = WRONG, answer = "dynamic typing"),
								@Answer(truth = RIGHT, answer = "inferred types"),
								@Answer(truth = WRONG, answer = "weak typing"),
								@Answer(truth = RIGHT, answer = "strong typing"),
								@Answer(truth = WRONG, answer = "requires manifest typing"),
								@Answer(truth = RIGHT, answer = "optional manifest typing"),
								@Answer(truth = RIGHT, answer = "dependent typing"),
								@Answer(truth = WRONG, answer = "duck typing")
			  }
			  ),
			  @Question(
						ask = "Which properties apply to the type System in Scala 2.8?",
						answers = {
								@Answer(truth = RIGHT, answer = "static typing"),
								@Answer(truth = WRONG, answer = "dynamic typing"),
								@Answer(truth = RIGHT, answer = "inferred types"),
								@Answer(truth = WRONG, answer = "weak typing"),
								@Answer(truth = RIGHT, answer = "strong typing"),
								@Answer(truth = WRONG, answer = "requires manifest typing"),
								@Answer(truth = RIGHT, answer = "optional manifest typing"),
								@Answer(truth = WRONG, answer = "dependent typing"),
								@Answer(truth = WRONG, answer = "duck typing")
			  }
			  ),
			  @Question(
						ask = "What features does Scalas type hierarchy offer?",
						answers = {
								@Answer(truth = WRONG, answer = "multiple inheritance of classes"),
								@Answer(truth = RIGHT, answer = "multiple inheritance of interfaces"),
								@Answer(truth = RIGHT, answer = "generic types"),
								@Answer(truth = RIGHT, answer = "multiple inheritance via mixins"),
								@Answer(truth = WRONG, answer = "prototype-based inheritance"),
								@Answer(truth = RIGHT, answer = "class-based inheritance")
			  }),
			  @Question(
						ask = "Which properties apply to the type System in PHP 4?",
						answers = {
								@Answer(truth = WRONG, answer = "static typing"),
								@Answer(truth = RIGHT, answer = "dynamic typing"),
								@Answer(truth = WRONG, answer = "inferred types"),
								@Answer(truth = RIGHT, answer = "weak typing"),
								@Answer(truth = WRONG, answer = "strong typing"),
								@Answer(truth = WRONG, answer = "requires manifest typing"),
								@Answer(truth = WRONG, answer = "optional manifest typing"),
								@Answer(truth = WRONG, answer = "dependent typing"),
								@Answer(truth = WRONG, answer = "duck typing")
			  }
			  ),
			  @Question(
						ask = "Which properties apply to the type System in Clojure & Common Lisp?",
						answers = {
								@Answer(truth = RIGHT, answer = "static typing"),
								@Answer(truth = WRONG, answer = "dynamic typing"),
								@Answer(truth = RIGHT, answer = "inferred types"),
								@Answer(truth = WRONG, answer = "weak typing"),
								@Answer(truth = RIGHT, answer = "strong typing"),
								@Answer(truth = WRONG, answer = "requires manifest typing"),
								@Answer(truth = RIGHT, answer = "optional manifest typing"),
								@Answer(truth = WRONG, answer = "dependent typing"),
								@Answer(truth = WRONG, answer = "duck typing")
			  }
			  ),
			  @Question(
						ask = "Which properties apply to the type System in Python?",
						answers = {
								@Answer(truth = WRONG, answer = "static typing"),
								@Answer(truth = RIGHT, answer = "dynamic typing"),
								@Answer(truth = WRONG, answer = "inferred types"),
								@Answer(truth = WRONG, answer = "weak typing"),
								@Answer(truth = RIGHT, answer = "strong typing"),
								@Answer(truth = WRONG, answer = "requires manifest typing"),
								@Answer(truth = WRONG, answer = "optional manifest typing"),
								@Answer(truth = WRONG, answer = "dependent typing"),
								@Answer(truth = RIGHT, answer = "duck typing")
			  }
			  ),
			  @Question(
						ask = "Which programming language(s) do you like best?",
						randomize = false,
						answers = {
								@Answer(truth = MAYBE, answer = "Ada"),
								@Answer(truth = MAYBE, answer = "Alef"),
								@Answer(truth = MAYBE, answer = "B"),
								@Answer(truth = MAYBE, answer = "BCPL"),
								@Answer(truth = MAYBE, answer = "Boo"),
								@Answer(truth = MAYBE, answer = "Brainfuck"),
								@Answer(truth = MAYBE, answer = "Brainfuck 2D"),
								@Answer(truth = MAYBE, answer = "C"),
								@Answer(truth = MAYBE, answer = "C#"),
								@Answer(truth = MAYBE, answer = "C++"),
								@Answer(truth = MAYBE, answer = "C++ templates (only!)"),
								@Answer(truth = MAYBE, answer = "Cω"),
								@Answer(truth = MAYBE, answer = "Clojure"),
								@Answer(truth = MAYBE, answer = "Cobra"),
								@Answer(truth = MAYBE, answer = "Common Lisp"),
								@Answer(truth = MAYBE, answer = "D"),
								@Answer(truth = WRONG, answer = "Delphi"),
								@Answer(truth = MAYBE, answer = "Dylan"),
								@Answer(truth = MAYBE, answer = "ECMAScript 4 (O.o)"),
								@Answer(truth = MAYBE, answer = "Eiffel"),
								@Answer(truth = MAYBE, answer = "Erlang"),
								@Answer(truth = MAYBE, answer = "F#"),
								@Answer(truth = MAYBE, answer = "Fortress"),
								@Answer(truth = MAYBE, answer = "Go"),
								@Answer(truth = MAYBE, answer = "Groovy"),
								@Answer(truth = MAYBE, answer = "Haskell"),
								@Answer(truth = MAYBE, answer = "Java"),
								@Answer(truth = MAYBE, answer = "JavaScript"),
								@Answer(truth = MAYBE, answer = "λ-calculus"),
								@Answer(truth = MAYBE, answer = "Limbo"),
								@Answer(truth = MAYBE, answer = "Nemerle"),
								@Answer(truth = MAYBE, answer = "Objective C"),
								@Answer(truth = MAYBE, answer = "Pascal"),
								@Answer(truth = MAYBE, answer = "Perl 5"),
								@Answer(truth = MAYBE, answer = "Perl 6"),
								@Answer(truth = MAYBE, answer = "PHP 4"),
								@Answer(truth = MAYBE, answer = "PHP 5"),
								@Answer(truth = MAYBE, answer = "Prolog"),
								@Answer(truth = MAYBE, answer = "Python"),
								@Answer(truth = MAYBE, answer = "Racket"),
								@Answer(truth = MAYBE, answer = "Ruby"),
								@Answer(truth = MAYBE, answer = "Scala"),
								@Answer(truth = MAYBE, answer = "Scheme"),
								@Answer(truth = MAYBE, answer = "Simula"),
								@Answer(truth = MAYBE, answer = "Smalltalk"),
								@Answer(truth = MAYBE, answer = "The input tape of the turing machine"),
								@Answer(truth = MAYBE, answer = "Vala"),
								@Answer(truth = MAYBE, answer = "XSL-T"),
								@Answer(truth = MAYBE, answer = "[other]")
			  }
			  )
})
public class TypeSystemsQuiz {

}
