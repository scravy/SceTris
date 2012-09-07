<?php

class scheduling {
	
	function run() {
		echo "<p>START</p>";
		system('cd ../../../ju-scheduler && java -classpath bin:src/scheduler/dao/lib/* scheduler.Client -o 2.xml');
		echo "<p>STOP</p>";
		
	}
}

$sch = new scheduling();
$sch->run();

?>