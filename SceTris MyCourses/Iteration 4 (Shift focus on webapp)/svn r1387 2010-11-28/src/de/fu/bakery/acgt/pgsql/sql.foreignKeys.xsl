<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://technodrom.scravy.de/2010/queries" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:xhtml="http://www.w3.org/1999/xhtml" xmlns:pg="http://technodrom.scravy.de/2010/postgres">

<xsl:import href="sql.common.xsl" />

<xsl:template match="en:entity | en:relationship" mode="createForeignKeys">
	<xsl:variable name="name" select="@name" />
	<xsl:for-each select="en:attribute[@ref]">
		<query>
			<xsl:text>ALTER TABLE </xsl:text>
			<xsl:call-template name="makeTableName">
				<xsl:with-param name="table" select="$name" />
			</xsl:call-template>
			<xsl:text> ADD FOREIGN KEY (</xsl:text>
			<xsl:if test="$quotes">&quot;</xsl:if>
			<xsl:value-of select="@name" />
			<xsl:if test="$quotes">&quot;</xsl:if>
			<xsl:text>) REFERENCES </xsl:text>
			<xsl:call-template name="makeTableName">
				<xsl:with-param name="table" select="@ref" />
			</xsl:call-template>		
			<xsl:text> ON DELETE CASCADE;</xsl:text>
		</query>
	</xsl:for-each>
</xsl:template>

<xsl:template match="text()|@*" mode="createForeignKeys" />

</xsl:transform>