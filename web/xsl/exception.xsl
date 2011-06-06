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
	xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common/global.xsl" />

<!-- <xsl:variable name="mod-lang" select="document(concat('../i18n/admin.', /vars:meta/@lang, '.xml'))/lang" /> -->

<xsl:template mode="title" match="vars:meta">
	<xsl:value-of select="$lang/exceptions/*[local-name() = current()/vars:exception]/title" />
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<xsl:variable name="xxx">
		<xsl:value-of select="$lang/exceptions/*[local-name() = current()/vars:exception]/title" />
	</xsl:variable>
	<div class="ui-corner-all exception">
	<h1>
		<xsl:if test="string-length($xxx) &gt;= 1"><xsl:value-of select="$xxx" /></xsl:if>
		<xsl:if test="string-length($xxx) &lt; 1"><xsl:value-of select="vars:exception" /></xsl:if>
	</h1>
		<div class="explanation">
		<p>
			<xsl:value-of select="$lang/exceptions/*[local-name() = current()/vars:exception]/explanation" />
		</p>
		</div>
	</div>
</xsl:template>

<xsl:template mode="menu" match="vars:meta">
	
</xsl:template>
	
</xsl:transform>
