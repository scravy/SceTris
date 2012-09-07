<?php

global $database, $username, $password, $host; 

$database = "myCourses";
$username = "postgres";
$password = "postgres";
$host = 	"localhost";

abstract class dbcon {
	protected $db;
	
	public function __construct() {
		$this->db = new \PDO("pgsql:host=".$GLOBALS["host"].";dbname=".$GLOBALS["database"], $GLOBALS["username"], $GLOBALS["password"]);
	}
	
}


?>