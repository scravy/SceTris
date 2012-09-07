<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:data="http://technodrom.scravy.de/score">

	<xsl:import href="global.xsl" />
	
	<xsl:template match="data:data" mode="title">
		<xsl:text>Login</xsl:text>
	</xsl:template>
	
	<xsl:template match="data:data" mode="content">
		<h1>Login</h1>
		<h2>User und Passwort angeben</h2>
		<form action="prototype.php" method="POST" name="login">
			<fieldset>
				<legend>Daten</legend>
				<table>
					<tr><td><label for="u_name">Name:</label></td>
					<td><input id="u_name" type="text" size="20" name="u_name" /></td></tr>
					<tr><td><label for="u_pass">Passwort:</label></td>
					<td><input id="u_pass" type="password" size="20" name="u_pass" /></td></tr>
					<tr><td colspan="2">
						<input type="hidden" name="action" value="einloggen" />
						<input type="submit" value="einloggen" name="submit"/></td></tr>
				</table>
			</fieldset>
		</form>	
		
	</xsl:template>

</xsl:transform>
