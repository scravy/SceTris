<?php

// namespace de\fu\anjuhakoda;
include_once "../../dbcon.php";

class roomPDO extends dbcon {
	
	public function __construct() {
		parent::__construct();
	}
 	
	public function insertRoom($roomname, $capacity) {
		echo "FŸge Daten zur Datenbank hinzu :: <br />roomname :: $roomname <br />capacity :: $capacity<br />";
		$stmt = $this->db->prepare("INSERT INTO room VALUES (nextval('room_id_seq'), :roomname, :capacity)");
		$stmt->bindParam(":roomname", $roomname);
		$stmt->bindParam(":capacity", $capacity);		
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