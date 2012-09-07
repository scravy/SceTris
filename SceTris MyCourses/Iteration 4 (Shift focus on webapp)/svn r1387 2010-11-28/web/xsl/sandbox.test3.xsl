<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta">
	<h2>This is test3.</h2>
	<xsl:if test="meta:exception">
	<p>Ouuuh, there was an Exception! <small>NOTE: This is expected to happen in this showcase.</small></p>
	</xsl:if>
	<xsl:if test="not(meta:exception)">
	<p>There was no Exception.</p>
	</xsl:if>
</xsl:template>

</xsl:transform>