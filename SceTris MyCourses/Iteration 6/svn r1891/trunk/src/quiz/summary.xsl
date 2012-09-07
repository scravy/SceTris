<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:q="http://technodrom.scravy.de/2011/alp5-quiz"
  xmlns="http://www.w3.org/1999/xhtml">

<xsl:import href="global.xsl" />

<xsl:template match="q:page">
	<h2>Summary</h2>
	<p>Your score: <xsl:value-of select="q:summary/@score" /></p>
	<p>Max score: <xsl:value-of select="q:summary/@maxScore" /></p>
</xsl:template>

</xsl:transform>