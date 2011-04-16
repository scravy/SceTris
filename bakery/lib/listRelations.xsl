<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:en="http://technodrom.scravy.de/2010/relations" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/">

<xsl:import href="common.xsl" />

<xsl:param name="javaize" />

<xsl:output method="text" charset="UTF-8" />

<xsl:template match="/en:relations">
	<xsl:for-each select="en:entity | en:relationship">
		<xsl:if test="$javaize = 'yes'">
			<xsl:call-template name="javaize">
				<xsl:with-param name="theString" select="@name" />
				<xsl:with-param name="first" select="false" />
			</xsl:call-template>
		</xsl:if>
		<xsl:if test="not($javaize = 'yes')">
			<xsl:value-of select="@name" />
		</xsl:if>
		<xsl:text> </xsl:text>
	</xsl:for-each>
	<xsl:text>&#xA;</xsl:text>
</xsl:template>

<xsl:template match="text()" />

</xsl:transform>
