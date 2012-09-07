<?php

// namespace de\fu\anjuhakoda;
include_once "coursePDO.php";

class courseGUI {
	protected static $instance = NULL;
	protected static $db;
	protected $template = NULL;
	protected $data = '<data xmlns="http://technodrom.scravy.de/score" />';
	protected $dao;	
	
	public function __construct() {
		$this->dao = new coursePDO();
	}
	
	public function run() {
		switch ($_REQUEST['action']) {
			case 'anlegen':
				$this->insertCourse();
				break;
			default:
				
		}

		echo "<br />action :: ".$_REQUEST['action'];
		echo "<br />session :: ".$_SESSION['logged_in'];
		UNSET($_REQUEST["action"]);
		
		if($this->template == "")
			$this->setTemplate("course_start.xsl");
		
		
		$xsltproc = new \XSLTProcessor();
		$xsltproc->registerPHPFunctions();		
		$xsltproc->importStyleSheet($this->template);
		
		if (!($this->data instanceof \DOMDocument)) {
			$dom = new \DOMDocument();
			$dom->loadXML($this->data);
			$this->data = $dom;
		}
		
		echo $xsltproc->transformToXML($this->data);
	}
	
	private function insertCourse() {
		/* get data, quick and dirty */
		$name = $_REQUEST["name"];
		$duration = $_REQUEST["duration"];
		$seats = $_REQUEST["seats"];
		$lecturer = $_REQUEST["lecturer"];
		if(trim($name)!="" && trim($duration)!="" && trim($seats)!="" && trim($lecturer)!="" && is_numeric($duration) && is_numeric($seats))
			$this->dao->insertCourse($name, $duration, $seats, $lecturer);
	}
	
	protected function setTemplate($xslfile) {
		$this->template = new \DOMDocument();
		$this->template->load($xslfile);
	}
	
}

$course = new courseGUI();
$course->run();

?>