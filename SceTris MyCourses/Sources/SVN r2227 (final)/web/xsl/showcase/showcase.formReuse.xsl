<?xml version="1.0" encoding="UTF-8"?>
<!--
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
-->
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:lang="http://technodrom.scravy.de/2010/lang"
	exclude-result-prefixes="vars item lang form">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:call-template name="intro" />

	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">reuse</xsl:with-param>
		<xsl:with-param name="hideOnSuccess" select="boolean(0)" />
	</xsl:call-template>
	
	<h2>~.~</h2>
	<table class="niceBorder">
	<xsl:for-each select="//item:pages[@key]">
	<tr>
		<th><xsl:value-of select="@key" /></th>
		<td><xsl:value-of select="text()" /></td>
	</tr>
	</xsl:for-each>
	</table>
</xsl:template>

</xsl:transform>
