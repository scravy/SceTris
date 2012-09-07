<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:vars="http://technodrom.scravy.de/2010/data" 
	xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />
<xsl:import href="../fuberlin/lego.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="vars:crud" /> courseinstance</h1>	

	<form method="post">


	<xsl:call-template name="selectcourse">
		<xsl:with-param name="label" select="'Course'" />
		<xsl:with-param name="attr" select="'course'" />
		<xsl:with-param name="options" select="vars:courses/item:courses" />
	</xsl:call-template>	

	<xsl:call-template name="selectprogram">
		<xsl:with-param name="label" select="'Program'" />
		<xsl:with-param name="attr" select="'program'" />
		<xsl:with-param name="options" select="vars:programs/item:programs" />
	</xsl:call-template>

	<xsl:call-template name="selectperson">
		<xsl:with-param name="label" select="'Mainlecturer'" />
		<xsl:with-param name="attr" select="'mainlecturer'" />
		<xsl:with-param name="options" select="vars:persons/item:persons" />
	</xsl:call-template>

	
	
	<input type="hidden" name="data" value="{vars:data/item:data/@id}"/>	
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
