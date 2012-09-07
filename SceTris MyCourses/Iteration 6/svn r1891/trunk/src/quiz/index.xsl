<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:q="http://technodrom.scravy.de/2011/alp5-quiz"
  xmlns="http://www.w3.org/1999/xhtml">

<xsl:import href="global.xsl" />

<xsl:template match="q:page">
	<xsl:for-each select="q:quiz">
		<li><a href="?{@query}"><xsl:value-of select="text()" /></a></li>
	</xsl:for-each>
</xsl:template>

</xsl:transform>
