<?php

// namespace de\fu\anjuhakoda;
include_once "lecturerPDO.php";

class lecturerGUI {
	protected static $instance = NULL;
	protected static $db;
	protected $template = NULL;
	protected $data = '<data xmlns="http://technodrom.scravy.de/score" />';
	protected $dao;	
	
	public function __construct() {
		$this->dao = new lecturerPDO();
	}
	
	public function run() {
		switch ($_REQUEST['action']) {
			case 'anlegen':
				$this->insertLecturer();
				break;
			default:
				
		}

		echo "<br />action :: ".$_REQUEST['action'];
		echo "<br />session :: ".$_SESSION['logged_in'];
		UNSET($_REQUEST["action"]);
		
		if($this->template == "")
			$this->setTemplate("lecturer_start.xsl");
		
		
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
	
	private function insertLecturer() {
		/* get data, quick and dirty */
		$firstname = $_REQUEST["firstname"];
		$surname = $_REQUEST["surname"];
		if(trim($firstname)!="" && trim($surname)!="")
			$this->dao->insertLecturer($firstname, $surname);
	}
	
	protected function setTemplate($xslfile) {
		$this->template = new \DOMDocument();
		$this->template->load($xslfile);
	}
	
}

$lec = new lecturerGUI();
$lec->run();

?>