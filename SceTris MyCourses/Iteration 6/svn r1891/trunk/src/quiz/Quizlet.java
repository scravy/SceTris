/* Quizlet.java / 9:37:18 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package quiz;

import static quiz.Quizlet.Option.*;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Quizlet extends HttpServlet {
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Answer {
		String answer();

		Option truth();
	}

	public static enum Option {
		RIGHT,
		WRONG,
		MAYBE
	}

	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Question {
		Answer[] answers();

		String ask();

		boolean randomize() default true;
	}

	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Quiz {
		Question[] questions();

		String title();
	}

	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Quizzes {
		Quiz[] value();
	}

	public static String getArg(final Map<String,String[]> $p, final String $k, final String $fallback) {
		String[] $args = $p.get($k);
		return ($args != null) && ($args.length > 0) ? $args[0] : $fallback;
	}

	public static void main(final String... $args) throws Exception {
		int $port = 4444;
		try {
			$port = Integer.parseInt($args[0]);
		} catch (Exception $exc) {
		}
		Server $server = new Server($port);
		Context $root = new Context($server, "/", Context.SESSIONS);
		$root.addServlet(Quizlet.class, "/*");
		$server.start();
	}

	final String NS = "http://technodrom.scravy.de/2011/alp5-quiz";

	private static final long serialVersionUID = 1224427804631953555L;

	private final Map<String,Transformer> $templates = new HashMap<String,Transformer>();

	private final String[] $templateFiles = { "index.xsl", "question.xsl", "summary.xsl" };

	protected Class<?>[] $dataClasses = { TypeSystemsQuiz.class, SimpleQuiz.class };

	final private Map<String,Quiz> $quizzes = new HashMap<String,Quiz>();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(final HttpServletRequest $req, final HttpServletResponse $resp)
		throws ServletException {
		reply(serve($req.getParameterMap(), $req.getSession()), $resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(final HttpServletRequest $req, final HttpServletResponse $resp)
		throws ServletException {
		reply(serve($req.getParameterMap(), $req.getSession()), $resp);
	}

	@Override
	public void init() throws ServletException {
		try {
			TransformerFactory $f = TransformerFactory.newInstance();
			for (String $file : $templateFiles) {
				URL $resource = getClass().getResource($file);
				StreamSource $template = new StreamSource(new File($resource.getPath()));
				Transformer $t = $f.newTransformer($template);
				$templates.put($file, $t);
			}
			for (Class<?> $c : $dataClasses) {
				Quiz $q = $c.getAnnotation(Quiz.class);
				$quizzes.put($q.title(), $q);
			}
		} catch (Exception $exc) {
			throw new ServletException($exc);
		}
	}

	private void prepare(final Document $doc) {
		$doc.getDocumentElement().setAttribute("rand",
			Integer.toString(Math.abs(new Random(System.currentTimeMillis()).nextInt())));
	}

	private void reply(final Document $doc, final HttpServletResponse $resp) throws ServletException {
		prepare($doc);

		try {
			$resp.setCharacterEncoding("UTF-8");
			$resp.setContentType("text/html");
			StreamResult $res = new StreamResult($resp.getOutputStream());

			String $template = $doc.getDocumentElement().getAttribute("template");
			Transformer $t = $templates.get($template);
			$t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			$t.setOutputProperty(OutputKeys.METHOD, "html");
			$t.setOutputProperty(OutputKeys.MEDIA_TYPE, "text/html");
			$t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			$t.transform(new DOMSource($doc), $res);
		} catch (Exception $exc) {
			throw new ServletException($exc);
		}
	}

	private Document serve(final Map<String,String[]> $p, final HttpSession $s) throws ServletException {
		Document $doc = null;
		try {
			$doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (Exception $exc) {
			throw new ServletException($exc);
		}

		$doc.appendChild($doc.createElementNS(NS, "page"));
		Element $top = $doc.getDocumentElement();
		$top.setAttribute("template", "index.xsl");
		try {
			String $action = getArg($p, "action", "index");
			if ($action.equals("answerQuestion")) {
				String[] $answers = $p.get("answers");
				$answers = $answers == null ? new String[] {} : $answers;
				Arrays.sort($answers);
				String $quiz = (String) $s.getAttribute("quiz");
				Integer $num = (Integer) $s.getAttribute("question");
				Quiz $q = $quizzes.get($quiz);
				int $yourScore = 0;
				for (Answer $a : $q.questions()[$num].answers()) {
					$yourScore += (($a.truth() == RIGHT) && (Arrays.binarySearch($answers, $a.answer()) >= 0))
							|| (($a.truth() == WRONG) && (Arrays.binarySearch($answers, $a.answer()) < 0))
							? 1 : 0;
				}
				$s.setAttribute($num.toString(), $yourScore);
				$s.setAttribute("question", ++$num);
			} else if ($action.equals("startQuiz")) {
				String $quiz = getArg($p, "quiz", "");
				if ($quizzes.containsKey($quiz)) {
					$s.setAttribute("quiz", $quiz);
					$s.setAttribute("question", 0);
					$s.setAttribute("start", System.currentTimeMillis());
				}
			} else {
				for (Quiz $q : $quizzes.values()) {
					Element $e = $doc.createElementNS(NS, "quiz");
					$e.setAttribute("query",
						"action=startQuiz&quiz=" + URLEncoder.encode($q.title(), "UTF-8"));
					$e.appendChild($doc.createTextNode($q.title()));
					$top.appendChild($e);
				}
				return $doc;
			}

			if ($s.getAttribute("quiz") != null) {
				Quiz $quiz = $quizzes.get($s.getAttribute("quiz"));
				Integer $question = (Integer) $s.getAttribute("question");
				if ($question >= $quiz.questions().length) {
					$top.setAttribute("template", "summary.xsl");
					$s.setAttribute("quiz", null);
					Element $e = $doc.createElementNS(NS, "summary");
					Integer $score = 0;
					Integer $maxScore = 0;
					Integer $i = 0;
					for (Question $q : $quiz.questions()) {
						Integer $partialScore = (Integer) $s.getAttribute($i.toString());
						if ($partialScore == null) {
							$partialScore = 0;
						}
						$score += $partialScore;
						$maxScore += $q.answers().length;
						$i++;
					}
					$e.setAttribute("score", $score.toString());
					$e.setAttribute("maxScore", $maxScore.toString());
					$top.appendChild($e);
				} else {
					Question $q = $quiz.questions()[$question];
					$top.setAttribute("template", "question.xsl");
					Element $qElement = $doc.createElementNS(NS, "question");
					$qElement.setAttribute("num", $question.toString());
					$qElement
							.appendChild($doc.createElementNS(NS, "ask"))
							.appendChild($doc.createTextNode($q.ask()));
					List<String> $answers = new ArrayList<String>($q.answers().length);
					for (Answer $a : $q.answers()) {
						$answers.add($a.answer());
					}
					if ($q.randomize()) {
						Collections.shuffle($answers);
					}
					for (String $a : $answers) {
						$qElement
								.appendChild($doc.createElementNS(NS, "answer"))
								.appendChild($doc.createTextNode($a));
					}
					$top.appendChild($qElement);
				}
			}
		} catch (Exception $exc) {
			Element $e = $doc.createElementNS(NS, "exception");
			$e.setAttribute("type", $exc.getClass().getCanonicalName());
			$e.appendChild($doc.createTextNode($exc.getMessage()));
			$doc.getDocumentElement().appendChild($e);
		}
		return $doc;
	}
}
