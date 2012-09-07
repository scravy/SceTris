<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../presentation.global.xsl" />
<xsl:import href="../formbuilder.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="vars:crud" /> room</h1>	
	<form method="post" action="{@module-path}/roomvalidate">
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Name'" />
		<xsl:with-param name="attr" select="'roomname'" />
		<xsl:with-param name="value" select="vars:room/item:room/@name" />
	</xsl:call-template>
	
	<div>
	<label for="'Building'">Building</label></div>
		<select id="'Building'" name="building">
			<xsl:apply-templates select="vars:buildings/item:buildings" />
		</select><br /><br />
	
	
	<div>Features</div><br />
		<xsl:apply-templates select="vars:features/item:features" />

	<input type="hidden" name="data" value="{vars:room/item:room/@id}"/>	
	<input type="hidden" name="crud" value="{vars:crud}"/>
	<input type="submit" value="{vars:crud}" />
	</form>

</xsl:template>

<xsl:template match="item:features">
	<input type="hidden" name="featurekey" value="{@name}"/>
	<xsl:variable name="foo" select="@id" />
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="@name" />
		<xsl:with-param name="attr" select="'feature'" />
		<xsl:with-param name="value" select="//vars:roomfeature/item:roomfeature[@feature=$foo]/@quantity" />
	</xsl:call-template>

</xsl:template>

<xsl:template match="item:buildings">
	<option>
		<xsl:if test="//vars:room/item:room/@building=@id">
			<xsl:attribute name="selected">selected</xsl:attribute>
		</xsl:if>
		<xsl:attribute name="value">
			<xsl:value-of select="@id" />
		</xsl:attribute>
		<xsl:value-of select="@name" />
	</option>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
