<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	exclude-result-prefixes="vars item">

<xsl:output method="text" encoding="UTF-8" />

<xsl:template match="/vars:meta">
{
	<xsl:for-each select="@*">
	<xsl:value-of select="concat('$', local-name())" />
	<xsl:text>:"</xsl:text>
	<xsl:value-of select="." />
	<xsl:text>"</xsl:text>
	<xsl:if test="position() != last()">,</xsl:if>
	</xsl:for-each>
	<xsl:call-template name="array" />
}
</xsl:template>

<xsl:template name="array">
	<xsl:for-each select="vars:*">
	<xsl:text>,</xsl:text>
	<xsl:value-of select="local-name()" />
		<xsl:text>[</xsl:text>
		<xsl:for-each select="item:*">
		<xsl:text>{$value:"</xsl:text>
		<xsl:value-of select="text()" />
		<xsl:text>"</xsl:text>
		<xsl:for-each select="@*">
		<xsl:text>,</xsl:text>
			<xsl:value-of select="local-name()" />:"<xsl:value-of select="." />"</xsl:for-each>
			<xsl:call-template name="array" />
		<xsl:text>}</xsl:text>
		<xsl:if test="position() != last()">,</xsl:if>
		</xsl:for-each>]</xsl:for-each>
</xsl:template>	

</xsl:transform>