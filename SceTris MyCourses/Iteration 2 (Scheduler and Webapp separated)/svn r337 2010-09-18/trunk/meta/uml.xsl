<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:en="http://technodrom.scravy.de/2010/entities">

<xsl:template match="/en:entities">
	
</xsl:template>

<xsl:template match="en:entity">
	<xsl:apply-templates select="en:attribute" />
</xsl:template>

<xsl:template match="en:relationship">
	<xsl:apply-templates select="en:attribute" />
</xsl:template>

<xsl:template match="en:attribute">
	
</xsl:template>

</xsl:transform>
