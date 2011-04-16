<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/">


<xsl:template name="capitalize">
	<xsl:param name="theString" />
	<xsl:value-of select="translate(substring($theString, 1, 1), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')" />
	<xsl:value-of select="substring($theString, 2)" />
</xsl:template>

<xsl:template name="javaize">
	<xsl:param name="theString" />
	<xsl:param name="first" select="boolean(1)" />

	<xsl:choose>	
		<xsl:when test="string-length(substring-after($theString, '_')) > 0">
			<xsl:choose>
				<xsl:when test="$first">
					<xsl:value-of select="substring-before($theString, '_')" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="capitalize">
						<xsl:with-param name="theString" select="substring-before($theString, '_')" />
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:call-template name="javaize">
				<xsl:with-param name="theString" select="substring-after($theString, '_')" />
				<xsl:with-param name="first" select="boolean(0)" />
			</xsl:call-template>
		</xsl:when>
		<xsl:otherwise>
			<xsl:choose>
				<xsl:when test="$first">
					<xsl:value-of select="$theString" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="capitalize">
						<xsl:with-param name="theString" select="$theString" />
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<xsl:template name="escape">
	<xsl:param name="theString" />

	<xsl:choose>
		<xsl:when test="contains($theString, '&quot;')">
			<xsl:value-of select="substring-before($theString, '&quot;')" />
			<xsl:text>\&quot;</xsl:text>
			<xsl:call-template name="escape">
				<xsl:with-param name="theString" select="substring-after($theString, '&quot;')" />
			</xsl:call-template>
		</xsl:when>
		<xsl:otherwise>
			<xsl:value-of select="$theString" />
		</xsl:otherwise>
	</xsl:choose>
	
</xsl:template>

<xsl:template name="escapeLiterally">
	<xsl:param name="theString" />
	
	<xsl:text>&quot;</xsl:text>
	<xsl:call-template name="escape">
		<xsl:with-param name="theString" select="$theString" />
	</xsl:call-template>
	<xsl:text>&quot;</xsl:text>
	
</xsl:template>

</xsl:transform>
