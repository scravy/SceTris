<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />
<xsl:import href="../fuberlin/lego.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="vars:crud" /> courseelementtype</h1>	

	<form method="post">
	
	<!-- old deprecated
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Type'" />
		<xsl:with-param name="attr" select="'coursetype'" />
		<xsl:with-param name="value" select="vars:coursetype/item:coursetype/@name" />
	</xsl:call-template> -->
	
	
	<xsl:call-template name="input-text">
		<xsl:with-param name="id" select="'coursetype'" />
		<xsl:with-param name="label" select="'CE Type'" />
		<xsl:with-param name="info" select="'Some even more random info'" />
		<xsl:with-param name="value" select="vars:coursetype/item:coursetype/@name" />
	</xsl:call-template>
	
	
	<input type="hidden" name="data" value="{vars:coursetype/item:coursetype/@id}"/>	
	
	<xsl:call-template name="input-submit">
		<xsl:with-param name="id" select="vars:crud"/>
		<xsl:with-param name="label" select="vars:crud"/>
	</xsl:call-template>
	
	<input type="submit" value="{vars:crud}" />
	
	
	</form>

</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
