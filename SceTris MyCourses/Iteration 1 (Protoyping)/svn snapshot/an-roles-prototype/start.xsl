<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:data="http://technodrom.scravy.de/score">

<xsl:import href="global.xsl" />

<xsl:template match="data:data" mode="title">
	<xsl:text>bugachacka!</xsl:text>
</xsl:template>

<xsl:template match="data:data" mode="content">
	<h2>you've successfully logged in.</h2>
	<a href="files/lecturer/lecturerGUI.php" >Lecturer eintragen</a><br />
	<a href="files/room/roomGUI.php" >Room eintragen </a><br />
	<a href="files/course/courseGUI.php" >Course eintragen </a><br />
	<a href="files/scheduling/test.php" >Schedulen</a><br />
	<a href="files/scheduling/view.php" >Plan angucken</a><br />
	
	<form action="prototype.php" method="POST">
		<input type="hidden" name="action" value="ausloggen" />
		<input type="submit" name="ausloggen" value="ausloggen" />
	</form>
	</xsl:template>





</xsl:transform>