<?php

// namespace de\fu\anjuhakoda;
include_once "../../dbcon.php";

class coursePDO extends dbcon{
		
	function __construct() {
		parent::__construct();
	}
	
	public function insertCourse($name, $duration, $seats, $lecturer) {
		echo "FŸge Daten zur Datenbank hinzu :: <br />coursename :: $name <br />duration :: $duration<br />seats :: $seats <br />lecturer :: $lecturer<br />";
		$stmt = $this->db->prepare("INSERT INTO course VALUES (nextval('course_id_seq'), :name, :duration, :seats, :lecturer)");
		$stmt->bindParam(":name", $name);
		$stmt->bindParam(":duration", $duration);
		$stmt->bindParam(":seats", $seats);
		$stmt->bindParam(":lecturer", $lecturer);
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