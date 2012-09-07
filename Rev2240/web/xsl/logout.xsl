<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common/global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Logout</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<xsl:choose>
		<xsl:when test="vars:exception">
			<h1>Logout failed.</h1>
			<p><xsl:value-of select="vars:exception/item:exception/@message" /></p>
		</xsl:when>
		<xsl:otherwise>
			<h1>Logged out</h1>
			<p>You were successfully logged out. <a href="{/vars:meta/@servlet-path}">Go to start</a>.</p>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
