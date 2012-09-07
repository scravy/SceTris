<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/">

<xsl:import href="common.xsl" />

<xsl:output omit-xml-declaration="yes" method="text" indent="yes" />

<xsl:template match="/en:relations"><xsl:text>
digraph Relations {
	overlap=scale;
	splines=true

	sep=.5;
	
	node [shape=box, color=red,fontsize=96];
</xsl:text>
	<xsl:apply-templates select="en:entity" />
<xsl:text>
	node [shape=box, color=green,fontsize=14];
</xsl:text>
	<xsl:apply-templates select="en:relationship | en:entity/en:attribute[@ref]" />

<xsl:text>
	edge [color=black];
</xsl:text>
	<xsl:apply-templates select="en:entity" mode="connections" />
<xsl:text>
	edge [color=black];
</xsl:text>
	<xsl:apply-templates select="en:relationship" mode="connections" />
<xsl:text>
}
</xsl:text>
</xsl:template>

<xsl:template match="en:entity">
	<xsl:text>&#9;</xsl:text>
	<xsl:value-of select="@name "/>
	<xsl:text>;&#10;</xsl:text>
</xsl:template>

<xsl:template match="en:relationship">
	<xsl:text>&#9;</xsl:text>
	<xsl:value-of select="@name "/>
	<xsl:text>;&#10;</xsl:text>
</xsl:template>

<xsl:template match="en:attribute[@ref]">
	<xsl:text>&#9;</xsl:text>
	<xsl:call-template name="weakEntityRefName" />
	<xsl:text>&#10;</xsl:text>
</xsl:template>

<xsl:template match="en:entity" mode="connections">
	<xsl:variable name="entity" select="@name" />
	<xsl:for-each select="en:attribute[@ref]">
		<xsl:text>&#9;</xsl:text>
		<xsl:value-of select="$entity" />
		<xsl:text> -> </xsl:text>
		<xsl:call-template name="weakEntityRefName" />
		<xsl:text> -> </xsl:text>
		<xsl:value-of select="@ref" />
		<xsl:text>;&#10;</xsl:text>
	</xsl:for-each>
</xsl:template>

<xsl:template match="en:relationship" mode="connections">
	<xsl:text>&#9;</xsl:text>
	<xsl:value-of select="@subject" />
	<xsl:text> -> </xsl:text>
	<xsl:value-of select="@name" />
	<xsl:text> -> </xsl:text>
	<xsl:value-of select="@object" />
	<xsl:text>;&#10;</xsl:text>
</xsl:template>

<xsl:template name="weakEntityRefName">
	<xsl:value-of select="parent::node()/@name" />
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString" select="@name" />
		<xsl:with-param name="first" select="boolean(0)" />
	</xsl:call-template>
</xsl:template>

<xsl:template match="text()|@*" />
<xsl:template match="text()|@*" mode="connections" />

</xsl:transform>