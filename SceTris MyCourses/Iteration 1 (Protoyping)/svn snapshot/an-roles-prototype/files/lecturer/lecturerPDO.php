<?php

// namespace de\fu\anjuhakoda;
include_once "../../dbcon.php";

class lecturerPDO extends dbcon{
		
	function __construct() {
		parent::__construct();
	}
	
	public function insertLecturer($firstname, $surname) {
		echo "FŸge Daten zur Datenbank hinzu :: <br />firstname :: $firstname <br />surname :: $surname<br />";
		$stmt = $this->db->prepare("INSERT INTO lecturer VALUES (nextval('lecturer_id_seq'), :first, :sur)");
		$stmt->bindParam(":first", $firstname);
		$stmt->bindParam(":sur", $surname);
		echo $stmt->queryString;
		if($stmt->execute()) {
			echo "<p>SUCCESS</p>";
		} else {
			echo "<p>FAIL</p>";
			$info = $stmt->errorInfo();
			echo $info[2];
		}
	}
}
?>