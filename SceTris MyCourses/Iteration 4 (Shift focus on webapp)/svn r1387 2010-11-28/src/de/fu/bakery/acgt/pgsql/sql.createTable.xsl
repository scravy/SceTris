<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://technodrom.scravy.de/2010/queries" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:xhtml="http://www.w3.org/1999/xhtml" xmlns:pg="http://technodrom.scravy.de/2010/postgres">

<xsl:import href="sql.common.xsl" />
<xsl:import href="sql.types.xsl" />

<xsl:template match="en:entity | en:relationship" mode="createTable">
	<query>
	<xsl:text>CREATE TABLE </xsl:text>
		<xsl:call-template name="makeTableName">
		<xsl:with-param name="table" select="@name" />
	</xsl:call-template>
	<xsl:text> (&#xA;</xsl:text>
	<xsl:for-each select="en:attribute">
		<xsl:text>&#x9;</xsl:text>
		<xsl:if test="$quotes">&quot;</xsl:if>
		<xsl:value-of select="@name" />
		<xsl:if test="$quotes">&quot;</xsl:if>
		<xsl:text> </xsl:text>
		<xsl:call-template name="type" />
		<xsl:if test="@nullable = 'false'">
			<xsl:text> NOT NULL</xsl:text>
		</xsl:if>
		<xsl:if test="@default">
			<xsl:text> DEFAULT </xsl:text>
			<xsl:choose>
				<xsl:when test="@type = 'string'">&apos;<xsl:value-of select="@default" />&apos;</xsl:when>
				<xsl:when test="@type = 'text'">&apos;<xsl:value-of select="@default" />&apos;</xsl:when>
				<xsl:when test="@type = 'timestamp'">
					<xsl:if test="@default = 'now'">CURRENT_TIMESTAMP</xsl:if>
					<xsl:if test="not(@default = 'now')">
						<xsl:message>default-values for timestamp other than 'now' not supported.</xsl:message>
					</xsl:if>
				</xsl:when>
				<xsl:when test="@type = 'integer'"><xsl:value-of select="number(@default)" /></xsl:when>
				<xsl:when test="@type = 'boolean'">
					<xsl:choose>
						<xsl:when test="@default = 'yes' or @default = 'true' or number(@default) > 0">true</xsl:when>
						<xsl:otherwise>false</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:otherwise>
					<xsl:message>unsupported type <xsl:value-of select="@type" />. no default value set.</xsl:message>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
		<xsl:if test="position() != last()">,&#xA;</xsl:if>
	</xsl:for-each>
	
	<xsl:choose>
		<xsl:when test="en:primary and count(en:primary/en:ref) > 0">
			<xsl:text>,&#xA;&#x9;PRIMARY KEY (</xsl:text>
			<xsl:apply-templates select="en:primary/en:ref" />
			<xsl:text>)</xsl:text>
		</xsl:when>
		<xsl:otherwise>
			<xsl:message>no primary key defined in table <xsl:value-of select="@name" />.</xsl:message>
		</xsl:otherwise>
	</xsl:choose>
	
	<xsl:text>&#xA;);</xsl:text>
	</query>
</xsl:template>

<xsl:template match="text()|@*" mode="createTable" />

</xsl:transform>