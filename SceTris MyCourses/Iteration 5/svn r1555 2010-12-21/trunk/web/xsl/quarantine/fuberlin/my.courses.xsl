<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Meine Kurse</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Meine Kurse</h1>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<xsl:text> &#x2192; Meine Kurse</xsl:text>
</xsl:template>

</xsl:transform>
