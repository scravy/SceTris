<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:q="http://technodrom.scravy.de/2010/queries">

<xsl:output methode="text" omit-xml-declaration="yes" />

<xsl:template match="/q:queries">
	<xsl:for-each select="q:query">
		<xsl:value-of select="text()" disable-output-escaping="yes" />
		<xsl:text>&#xA;</xsl:text>
	</xsl:for-each>
</xsl:template>

</xsl:transform>
