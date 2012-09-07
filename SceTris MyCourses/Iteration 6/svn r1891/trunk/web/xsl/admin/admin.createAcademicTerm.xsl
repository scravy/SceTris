<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<h2><xsl:value-of select="$mod-lang/createAcademicTerm" /></h2>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">newAcademicTerm</xsl:with-param>
	</xsl:call-template>
	
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">newAcademicTerms</xsl:with-param>
	</xsl:call-template>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/courses"><xsl:value-of select="$lang/admin-courses" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/createAcademicTerm" />
	</li>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
