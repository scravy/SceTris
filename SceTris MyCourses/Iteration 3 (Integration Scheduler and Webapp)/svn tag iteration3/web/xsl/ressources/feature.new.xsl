<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../presentation.global.xsl" />
<xsl:import href="../formbuilder.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="vars:crud" /> feature</h1>	

	<form method="post" action="{@module-path}/featurevalidate/">
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Name'" />
		<xsl:with-param name="attr" select="'feature'" />
		<xsl:with-param name="value" select="vars:feature/item:feature/@name" />
	</xsl:call-template>
	
	<!-- Name <input type="text" name="feature" value="{vars:feature}" /><br />  -->
	<input type="hidden" name="data" value="{vars:feature/item:feature/@id}"/>	
	<input type="hidden" name="crud" value="{vars:crud}"/>
	<input type="submit" value="{vars:crud}" />	</form>

</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
