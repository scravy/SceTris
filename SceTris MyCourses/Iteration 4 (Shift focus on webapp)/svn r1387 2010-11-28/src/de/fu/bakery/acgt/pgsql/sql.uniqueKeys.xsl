<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://technodrom.scravy.de/2010/queries" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:xhtml="http://www.w3.org/1999/xhtml" xmlns:pg="http://technodrom.scravy.de/2010/postgres">

<xsl:import href="sql.common.xsl" />

<xsl:param name="uniq_prefix" />

<xsl:template match="en:entity | en:relationship" mode="createUniqueKeys">
	<xsl:variable name="name" select="@name" />
	<xsl:for-each select="en:unique">
		<query>
		<xsl:if test="$unique_indexes">
			<xsl:text>CREATE UNIQUE INDEX </xsl:text>
			<xsl:value-of select="concat($uniq_prefix, generate-id(), '_', en:ref[1]/@field)" />
			<xsl:text> ON </xsl:text>
			<xsl:call-template name="makeTableName">
				<xsl:with-param name="table" select="$name" />
			</xsl:call-template>
			<xsl:text> (</xsl:text>
			<xsl:apply-templates select="en:ref" />
			<xsl:text>);&#xA;</xsl:text>
		</xsl:if>
		<xsl:if test="not($unique_indexes)">
			<xsl:text>ALTER TABLE </xsl:text>
			<xsl:call-template name="makeTableName">
				<xsl:with-param name="table" select="$name" />
			</xsl:call-template>
			<xsl:text> ADD CONSTRAINT </xsl:text>
			<xsl:value-of select="concat($uniq_prefix, generate-id(), '_', en:ref[1]/@field)" />
			<xsl:text> UNIQUE (</xsl:text>
			<xsl:apply-templates select="en:ref" />
			<xsl:text>);&#xA;</xsl:text>
		</xsl:if>
		</query>
	</xsl:for-each>
</xsl:template>

<xsl:template match="text()|@*" mode="createUniqueKeys" />

</xsl:transform>