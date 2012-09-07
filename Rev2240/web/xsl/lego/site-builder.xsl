<?xml version="1.0" encoding="UTF-8"?>
<!--
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
-->
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xhtml="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:lang="http://technodrom.scravy.de/2010/lang"
	xmlns:xxx="http://technodrom.scravy.de/2xxx"
	exclude-result-prefixes="vars item lang form xhtml xsl xxx">

	<xsl:import href="common.xsl" />
	<xsl:variable name="doc" select="/*" />
	
	<xsl:template mode="site-builder" match="xxx:apply-templates">
		<xsl:apply-templates select="$doc" mode="inner-builder">
			<xsl:with-param name="arg" select="@arg" />
		</xsl:apply-templates>
	</xsl:template>
	
	<xsl:template mode="site-builder" match="xhtml:*">
		<xsl:param name="document" />
		<xsl:variable name="name" select="local-name()" /> 
		<xsl:element name="{$name}">
			<xsl:for-each select="@*">
				<xsl:attribute name="{local-name()}">
					<xsl:value-of select="." />
				</xsl:attribute>
			</xsl:for-each>
			<xsl:apply-templates mode="site-builder">
				<xsl:with-param name="document" select="$document" />
			</xsl:apply-templates>
		</xsl:element>
	</xsl:template>
	
	<xsl:template mode="site-builder" match="text()">
		<xsl:value-of select="string(.)" />
	</xsl:template>
	
	<xsl:template mode="site-builder" match="lang:*">
		<xsl:variable name="item" select="local-name()" />
		<xsl:choose>
			<xsl:when test="$mod-lang/*[local-name() = $item]">
				<xsl:value-of select="$mod-lang/*[local-name() = $item]" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$item" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

</xsl:transform>
