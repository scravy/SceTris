<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://technodrom.scravy.de/2010/queries" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:xhtml="http://www.w3.org/1999/xhtml" xmlns:pg="http://technodrom.scravy.de/2010/postgres">

<xsl:import href="sql.common.xsl" />

<xsl:template match="en:entity | en:relationship" mode="deleteEverything">
	<xsl:text>DROP TABLE IF EXISTS </xsl:text>
	<xsl:call-template name="makeTableName">
		<xsl:with-param name="table" select="@name" />
	</xsl:call-template>
	<xsl:text> CASCADE;&#xA;</xsl:text>
</xsl:template>

<xsl:template match="text()|@*" mode="deleteEverything" />

</xsl:transform>