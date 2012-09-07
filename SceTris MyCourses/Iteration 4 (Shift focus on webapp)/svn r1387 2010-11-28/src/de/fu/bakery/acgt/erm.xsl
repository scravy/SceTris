<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/">

<xsl:import href="common.xsl" />

<xsl:output omit-xml-declaration="yes" method="text" indent="yes" />

<xsl:template match="/en:relations"><xsl:text>
digraph Relations {
	overlap=true;
	
</xsl:text>

<xsl:apply-templates select="en:entity" />

<xsl:text>
	edge [len=3];
</xsl:text>
<xsl:apply-templates select="en:relationship | en:entity/en:attribute" />

<xsl:text>
}
</xsl:text>

</xsl:template>

<xsl:template match="en:entity">

	<xsl:text>&#9;</xsl:text>
	<xsl:value-of select="generate-id()" />
	<xsl:text>[label=</xsl:text>
	<xsl:value-of select="@name" />
	<xsl:text>];&#10;</xsl:text>

</xsl:template>

<xsl:template match="en:relationship">

	<xsl:text>&#9;</xsl:text>
	<xsl:value-of select="generate-id(/en:relations/en:entity[@name = current()/@subject])" />
	<xsl:text> -> </xsl:text>
	<xsl:value-of select="generate-id(/en:relations/en:entity[@name = current()/@object])" />
	<xsl:text> [label=</xsl:text>
	<xsl:value-of select="@name" />
	<xsl:text>];&#10;</xsl:text>
	
</xsl:template>

<xsl:template match="en:attribute">
	<xsl:variable name="ref" select="@ref" />

	<xsl:if test="@ref">XXX</xsl:if>

	<xsl:text>&#9;</xsl:text>
	<xsl:value-of select="generate-id(/en:relations/en:entity[@name = $ref])" />
	<xsl:text> -> </xsl:text>
	<xsl:value-of select="generate-id(parent::node())" />
	<xsl:text> [label=</xsl:text>
	<xsl:value-of select="@name" />
	<xsl:text>];&#10;</xsl:text>
	
</xsl:template>

<xsl:template match="en:attribute[@ref]">
	
</xsl:template>

<xsl:template match="text()|@*" />

</xsl:transform>
