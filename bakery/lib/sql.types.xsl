<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://technodrom.scravy.de/2010/queries" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:xhtml="http://www.w3.org/1999/xhtml" xmlns:pg="http://technodrom.scravy.de/2010/postgres">
	
	<xsl:import href="sql.common.xsl" />

	<xsl:template name="type">
		<xsl:choose>
			<xsl:when test="@serial = 'true'">
				<xsl:text>SERIAL</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'integer'">
				<xsl:text>INTEGER</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'string'">
				<xsl:text>VARCHAR</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'boolean'">
				<xsl:text>BOOLEAN</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'timestamp'">
				<xsl:text>TIMESTAMP (3)</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'date'">
				<xsl:text>DATE</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'text'">
				<xsl:text>TEXT</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'time'">
				<xsl:text>TIME</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'password'">
				<xsl:text>VARCHAR (32)</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:message>Unkown type <xsl:value-of select="@type" /></xsl:message>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

</xsl:transform>
