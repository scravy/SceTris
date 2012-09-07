<?php

// namespace de\fu\anjuhakoda;
include_once "roomPDO.php";

class roomGUI {
	protected static $instance = NULL;
	protected static $db;
	protected $template = NULL;
	protected $data = '<data xmlns="http://technodrom.scravy.de/score" />';
	protected $dao;	
	
	public function __construct() {
		$this->dao = new roomPDO();
	}
	
	public function run() {
		switch ($_REQUEST['action']) {
			case 'anlegen':
				$this->insertRoom();
				break;
			default:
				
		}

		echo "<br />action :: ".$_REQUEST['action'];
		echo "<br />session :: ".$_SESSION['logged_in'];

		if($this->template == "")
			$this->setTemplate("room_start.xsl");
		
		
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
	
	private function insertRoom() {
		/* get data, quick and dirty */
		$roomname = $_REQUEST["roomname"];
		$capacity = $_REQUEST["capacity"];
		if(trim($roomname)!="" && trim($capacity)!="")
			$this->dao->insertRoom($roomname, $capacity);
	}
	
	protected function setTemplate($xslfile) {
		$this->template = new \DOMDocument();
		$this->template->load($xslfile);
	}
	
}

$room = new roomGUI();
$room->run();

?>