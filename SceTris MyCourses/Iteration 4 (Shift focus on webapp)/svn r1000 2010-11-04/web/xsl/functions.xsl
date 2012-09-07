<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/">

<xsl:template name="capitalize">
	<xsl:param name="theString" />
	<xsl:value-of select="translate(substring($theString, 1, 1), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')" />
	<xsl:value-of select="substring($theString, 2)" />
</xsl:template>


<xsl:template name="tokenize">
	<xsl:param name="inputString" />
	<xsl:param name="separator" />
	<xsl:variable name="token" select="substring-before($inputString, $separator)" />
	<xsl:variable name="nextToken" select="substring-after($inputString, $separator)" />
	<!-- input :: <xsl:value-of select="$inputString" />
	token :: <xsl:value-of select="$token" /><br />	-->
	<xsl:if test="$nextToken">
		<xsl:call-template name="tokenize">
			<xsl:with-param	name="inputString" select="$nextToken"/>
			<xsl:with-param	name="separator" select="$separator"/>
		</xsl:call-template>
	</xsl:if>
	<xsl:if test="not($nextToken)"><xsl:value-of select="$inputString" /></xsl:if>

</xsl:template>

</xsl:transform>