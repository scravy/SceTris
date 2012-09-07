<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:data="http://technodrom.scravy.de/score">

<xsl:import href="../../global.xsl" />

<xsl:template match="data:data" mode="title">
	<xsl:text>course - start!</xsl:text>
</xsl:template>

<xsl:template match="data:data" mode="content">
	<h2>geben sie daten zum eingeben an.</h2>	
	<form action="courseGUI.php" method="POST">
		<fieldset>
			<legend>Daten</legend>
			<table>
				<tr><td><label for="name">Name:</label></td>
				<td><input id="name" type="text" size="20" name="name" /></td></tr>
				<tr><td><label for="dura">Dauer:</label></td>
				<td><input id="dura" type="text" size="20" name="duration" /></td></tr>
				<tr><td><label for="seats">Sitze:</label></td>
				<td><input id="seats" type="text" size="20" name="seats" /></td></tr>
				<tr><td><label for="lect">Lecturer-ID:</label></td>
				<td><input id="lect" type="text" size="20" name="lecturer" /></td></tr>
			</table>
		</fieldset>
		<input type="hidden" name="action" value="anlegen" />
		<input type="submit" name="anlegen" value="anlegen" />
	</form>
	
	<a href="/">back</a>
	
</xsl:template>





</xsl:transform>