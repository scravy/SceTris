<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta">
	<xsl:text>It’s a shiny, shiny morning.</xsl:text>
</xsl:template>

<xsl:template match="meta:meta">
	
	<h2><u>C</u>reate, <u>R</u>ead, <u>U</u>pdate, <u>D</u>elete</h2>
	
	<xsl:if test="/*/*/item:exception[@name = 'AccessDeniedException']">
		<h1 class="denied">Access denied.</h1>
	</xsl:if>
	
</xsl:template>

</xsl:transform>
