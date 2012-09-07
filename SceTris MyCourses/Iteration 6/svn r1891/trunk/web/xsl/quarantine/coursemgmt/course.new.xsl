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
	<h1><xsl:value-of select="vars:crud" /> course</h1>	

	<form method="post">
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Name of Course'" />
		<xsl:with-param name="attr" select="'course'" />
		<xsl:with-param name="value" select="vars:data/item:data/@name" />
	</xsl:call-template>
	<input type="hidden" name="data" value="{vars:data/item:data/@id}"/>	
	<input type="submit" value="{vars:crud}" />
	</form>

	<xsl:if test="vars:ref/item:ref">
		<br />
			Crossreference
			<ul>
				<xsl:apply-templates select="vars:ref/item:ref" />
			</ul>
		<br />
	</xsl:if>
</xsl:template>

<xsl:template match="item:ref">
	<li>
	<a href="{/vars:meta/@module-path}/courseelementedit/?data={@id}"><xsl:value-of select="@name" /></a>
	| Duration<xsl:value-of select="@duration" />
	| Required<xsl:value-of select="@required" />
	| Type<xsl:value-of select="@type" />	
	</li>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
