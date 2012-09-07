<?php

// namespace de\fu\anjuhakoda;
include_once "dbcon.php";

class RolesPrototype extends dbcon{
	protected static $instance = NULL;
	protected $template = NULL;
	protected $data = '<data xmlns="http://technodrom.scravy.de/score" />';
	
	public function __construct() {
		parent::__construct();
	}
	
	public static function create() {
		if (self::$instance === NULL) {
			self::$instance = new RolesPrototype();
		}
		return self::$instance;
	}
	
	public function run() {
		session_start();
		switch ($_REQUEST['action']) {
			case 'einloggen':
				$this->checkUser();
				break;
			case 'ausloggen':
				$this->checkOut();
				break;
			default:
				// do nothing
		}

		echo "<br />action :: ".$_REQUEST['action'];
		echo "<br />session :: ".$_SESSION['logged_in'];
		if(ISSET($_SESSION['logged_in']) && $_SESSION['logged_in'] != "")
			$this->setTemplate("start.xsl");
		else
			$this->setTemplate("login.xsl");
				
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
	
	protected function setTemplate($xslfile) {
		$this->template = new \DOMDocument();
		$this->template->load($xslfile);
	}
	
	public function checkUser() {
		if(isset($_REQUEST['u_name']) && isset($_REQUEST['u_pass'])) { 
			// Section for logging process ----------- 
			$name = trim($_REQUEST['u_name']); 
			$pass = trim($_REQUEST['u_pass']);
			$stmt = $this->db->prepare("SELECT u_name FROM public.user WHERE u_name = :user AND u_password = :pass");
			$stmt->bindParam(":user",$name);
			$stmt->bindParam(":pass",md5($pass));
			if($stmt->execute() && count($stmt->fetchAll())==1) {
				// Successful login ------------------ 
				$_SESSION['logged_in'] = $name;
				session_write_close();
			} else {
				echo "<h3>fehler fehler fehler</h3>";
			}
		} 
	} 
	
	public function checkOut() {
		session_start();
		session_unset();
		session_destroy();
	}
	
	public function printAllRights() {
		$stmt = $this->db->prepare("SELECT * FROM public.user");
		$stmt2 = $this->db->prepare("SELECT p_name FROM permission(:user)");
		$stmt2->bindParam(":user", $username);
		if ($stmt->execute()) {
			foreach ($stmt->fetchAll() as $user) {
				$username = $user["u_name"];
				$stmt2->execute();
				echo $user["u_name"]. "\n";
				foreach ($stmt2->fetchAll() as $row) {
					echo "\t{$row['p_name']}\n";
				}
				echo "\n";
			}
		} else {
			die("Shit like fuck.");
		}
	}
}

RolesPrototype::create()->run();
__halt_compiler();

?>