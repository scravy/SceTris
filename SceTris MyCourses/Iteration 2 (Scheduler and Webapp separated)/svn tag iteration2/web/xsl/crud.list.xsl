<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta">
	<h1>Hilikus!</h1>
	<table>
	<tr>
		<xsl:for-each select="meta:persons/item:persons[1]/@*">
			<th><xsl:value-of select="local-name()" /></th>
		</xsl:for-each>
	</tr>
	<xsl:for-each select="meta:persons/item:persons">
	<tr>
		<xsl:for-each select="@*">
			<td><xsl:value-of select="." /></td>
		</xsl:for-each>
	</tr>
	</xsl:for-each>
	</table>
</xsl:template>

</xsl:transform>
