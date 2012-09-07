<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:data="http://technodrom.scravy.de/score">

<xsl:import href="../../global.xsl" />

<xsl:template match="data:data" mode="title">
	<xsl:text>lecturer - start!</xsl:text>
</xsl:template>

<xsl:template match="data:data" mode="content">
	<h2>geben sie daten zum eingeben an.</h2>	
	<form action="roomGUI.php" method="POST">
		<fieldset>
			<legend>Daten</legend>
			<table>
				<tr><td><label for="name">Raumname:</label></td>
				<td><input id="name" type="text" size="20" name="roomname" /></td></tr>
				<tr><td><label for="capacity">Capacity:</label></td>
				<td><input id="capacity" type="text" size="20" name="capacity" /></td></tr>
			</table>
		</fieldset>		
		
		<input type="hidden" name="action" value="anlegen" />
		<input type="submit" name="anlegen" value="anlegen" />
	</form>
	
	<a href="/">back</a>
	
</xsl:template>





</xsl:transform>