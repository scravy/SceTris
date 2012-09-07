<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="/">
<html>
	<head>
		<title>State of the art</title>
		<style type="text/css">
		
		table, tr, td {
			border-collapse: collapse;
			border: 1px solid black;
		}
		
		</style>
	</head>
	<body>
		<xsl:if test="//vars:notStarted">
			<form method="get">
				<input type="submit" value="Start Scheduling" name="start"/>
				</form>
			<p>Status: <xsl:value-of select="//vars:status" /></p></xsl:if>
			
		<xsl:for-each select="//vars:timetable/item:timetable">
			<table>
			<xsl:for-each select="vars:fixtures/item:fixtures">
				<tr>
				<td>
				<xsl:value-of select="@start" />
				</td>
				<td>
				<xsl:value-of select="@courseName" />
				</td>
				</tr>
			</xsl:for-each>
			</table>
		</xsl:for-each>
	</body>
</html>
</xsl:template>

</xsl:transform>
