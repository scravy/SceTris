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
	<h1><xsl:value-of select="vars:crud" /> academic term</h1>	

	<form method="post">
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Name of Academic Term'" />
		<xsl:with-param name="attr" select="'name'" />
		<xsl:with-param name="value" select="vars:data/item:data/@name" />
	</xsl:call-template><br />
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Start of Term'" />
		<xsl:with-param name="attr" select="'start'" />
		<xsl:with-param name="value" select="vars:data/item:data/@start" />
	</xsl:call-template><br />
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'End of Term'" />
		<xsl:with-param name="attr" select="'end'" />
		<xsl:with-param name="value" select="vars:data/item:data/@end" />
	</xsl:call-template><br />
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Startdate of Lessons'" />
		<xsl:with-param name="attr" select="'startlesson'" />
		<xsl:with-param name="value" select="vars:data/item:data/@startLessons" />
	</xsl:call-template><br />
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Enddate of Lessons'" />
		<xsl:with-param name="attr" select="'endlesson'" />
		<xsl:with-param name="value" select="vars:data/item:data/@endLessons" />
	</xsl:call-template><br />

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
