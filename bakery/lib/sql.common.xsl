<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://technodrom.scravy.de/2010/queries" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:xhtml="http://www.w3.org/1999/xhtml">

<xsl:param name="table_name_prefix" />
<xsl:param name="quotes" />
<xsl:param name="scheme_name" />

<xsl:template name="makeTableName">
	<xsl:param name="table" />
	<xsl:if test="$scheme_name">
		<xsl:if test="$quotes">&quot;</xsl:if>
		<xsl:value-of select="$scheme_name" />
		<xsl:if test="$quotes">&quot;</xsl:if>
		<xsl:text>.</xsl:text>
	</xsl:if>
	<xsl:if test="$quotes">&quot;</xsl:if>
	<xsl:value-of select="concat($table_name_prefix, $table)" />
	<xsl:if test="$quotes">&quot;</xsl:if>
</xsl:template>

<xsl:template match="en:ref">
	<xsl:if test="$quotes">&quot;</xsl:if>
	<xsl:value-of select="@field" />
	<xsl:if test="$quotes">&quot;</xsl:if>
	<xsl:if test="position() != last()">, </xsl:if>
</xsl:template>

<xsl:template match="text()|@*" />

</xsl:transform>