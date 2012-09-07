<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xhtml="http://www.w3.org/1999/xhtml"
	exclude-result-prefixes="xhtml">

<xsl:output method="text" encoding="UTF-8" />

<xsl:template match="/">
	<xsl:apply-templates select="xhtml:html/xhtml:body/xhtml:*" />
</xsl:template>

<xsl:template match="xhtml:p">
<xsl:value-of select="." />
<xsl:text>&#xA;&#xA;</xsl:text>
</xsl:template>

<xsl:template match="xhtml:ul | xhtml:ol">
<xsl:apply-templates />
<xsl:text>&#xA;&#xA;</xsl:text>
</xsl:template>

<xsl:template match="xhtml:li">
<xsl:text> * </xsl:text>
<xsl:apply-templates mode="inline" />
<xsl:text>&#xA;</xsl:text>
</xsl:template>

<xsl:template match="a[@href]" mode="inline">
<xsl:value-of select="." />
<xsl:text> [</xsl:text>
<xsl:value-of select="@href" />
<xsl:text>] </xsl:text>
</xsl:template>

<xsl:template match="text()" mode="inline">
<xsl:value-of select="translate(., '&#xA;', ' ')" />
</xsl:template>

<xsl:template match="xhtml:h1">
<xsl:text>
= </xsl:text>
<xsl:value-of select="." />
<xsl:text> =
</xsl:text>
</xsl:template>

<xsl:template match="xhtml:h2">
<xsl:text>
== </xsl:text>
<xsl:value-of select="." />
<xsl:text> ==
</xsl:text>
</xsl:template>

<xsl:template match="xhtml:h3">
<xsl:text>
=== </xsl:text>
<xsl:value-of select="." />
<xsl:text> ===
</xsl:text>
</xsl:template>

<xsl:template match="xhtml:h4">
<xsl:text>
==== </xsl:text>
<xsl:value-of select="." />
<xsl:text> ====
</xsl:text>
</xsl:template>

<xsl:template match="xhtml:h5">
<xsl:text>
===== </xsl:text>
<xsl:value-of select="." />
<xsl:text> =====
</xsl:text>
</xsl:template>

<xsl:template match="xhtml:h6">
<xsl:text>
====== </xsl:text>
<xsl:value-of select="." />
<xsl:text> ======
</xsl:text>
</xsl:template>

<xsl:template match="@*|text()" />
<xsl:template match="@*" />

</xsl:transform>
