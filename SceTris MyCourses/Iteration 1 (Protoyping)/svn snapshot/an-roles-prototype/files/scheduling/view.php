<?php

class view {
	
	function run() {
		$docRoot = "/Users/andre/score/score-myCourses/branches/";
		$pathToXML = $docRoot."ju-scheduler/testdata/out/2.xml";
		$pathToXSL = $docRoot."ju-scheduler/testdata/out/list.xsl";
		if(file_exists($pathToXML)) {			

			# LOAD XML FILE 
			$XML = new \DOMDocument(); 
			$XML->load( $pathToXML ); 
			
			# START XSLT 
			$xslt = new \XSLTProcessor(); 
			
			# IMPORT STYLESHEET 1 
			$XSL = new \DOMDocument(); 
			$XSL->load( $pathToXSL ); 
			$xslt->importStylesheet( $XSL ); 
			
			#PRINT 
			print $xslt->transformToXML( $XML ); 		
			
		} else {
			echo "404";
		}
	}
}

$sch = new view();
$sch->run();

?>