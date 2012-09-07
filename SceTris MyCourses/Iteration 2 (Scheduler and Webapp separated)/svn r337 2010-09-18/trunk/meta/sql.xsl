<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:en="http://technodrom.scravy.de/2010/entities-lite" xmlns:xhtml="http://www.w3.org/1999/xhtml" xmlns:pg="http://technodrom.scravy.de/2010/postgres">

<xsl:output method="text" />

<xsl:param name="table_name_prefix">sc_</xsl:param>
<xsl:param name="uniq_prefix">uniq_</xsl:param>
<xsl:param name="unique_indexes" select="boolean(1)" />

<xsl:template match="/en:entities">
	<xsl:apply-templates select="en:entity | en:relationship" mode="deleteEverything" />
	<xsl:text>&#xA;</xsl:text>
	<xsl:apply-templates select="en:entity | en:relationship" mode="createTable" />
	<xsl:text>&#xA;</xsl:text>
	<xsl:apply-templates select="en:entity | en:relationship" mode="createUniqueKeys" />
	<xsl:text>&#xA;</xsl:text>
	<xsl:apply-templates select="en:entity | en:relationship" mode="createForeignKeys" />
</xsl:template>

<xsl:template name="makeTableName">
	<xsl:if test="$scheme_name">
		<xsl:if test="$quotes">&quot;</xsl:if>
		<xsl:value-of select="$scheme_name" />
		<xsl:if test="$quotes">&quot;</xsl:if>
		<xsl:text>.</xsl:text>
	</xsl:if>
	<xsl:if test="$quotes">&quot;</xsl:if>
	<xsl:value-of select="concat($table_name_prefix, @name)" />
	<xsl:if test="$quotes">&quot;</xsl:if>
</xsl:template>

<xsl:template match="en:entity | en:relationship" mode="deleteEverything">
	<xsl:text>DROP TABLE IF EXISTS </xsl:text>
	<xsl:call-template name="makeTableNname">
		<xsl:with-param name="table" select="@name" />
	</xsl:call-template>
	<xsl:text> CASCADE;&#xA;</xsl:text>
</xsl:template>

<xsl:template match="en:entity | en:relationship" mode="createTable">
	<xsl:text>CREATE TABLE </xsl:text>
		<xsl:call-template name="makeTableNname">
		<xsl:with-param name="table" select="@name" />
	</xsl:call-template>
	<xsl:text> (&#xA;</xsl:text>
	<xsl:for-each select="en:attribute">
		<xsl:text>&#x9;&quot;</xsl:text>
		<xsl:value-of select="@name" />
		<xsl:text>&quot; </xsl:text>
		<xsl:choose>
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
				<xsl:text>TIMESTAMP</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'date'">
				<xsl:text>DATE</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'text'">
				<xsl:text>TEXT</xsl:text>
			</xsl:when>
			<xsl:when test="@type = 'password'">
				<xsl:text>BYTEA[32]</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:message>Unkown type <xsl:value-of select="@type" /></xsl:message>
			</xsl:otherwise>
		</xsl:choose>
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
	
	<xsl:text>&#xA;);&#xA;&#xA;</xsl:text>
</xsl:template>

<xsl:template match="en:ref">
	<xsl:text>&quot;</xsl:text>
	<xsl:value-of select="@field" />
	<xsl:text>&quot;</xsl:text>
	<xsl:if test="position() != last()">, </xsl:if>
</xsl:template>

<xsl:template match="en:entity | en:relationship" mode="createUniqueKeys">
	<xsl:variable name="name" select="@name" />
	<xsl:for-each select="en:unique">
		<xsl:if test="$unique_indexes">
			<xsl:text>CREATE UNIQUE INDEX </xsl:text>
			<xsl:value-of select="concat($uniq_prefix, generate-id())" />
			<xsl:text> ON </xsl:text>
			<xsl:call-template name="makeTableNname">
				<xsl:with-param name="table" select="$name" />
			</xsl:call-template>
			<xsl:text> (</xsl:text>
			<xsl:apply-templates select="en:ref" />
			<xsl:text>);&#xA;</xsl:text>
		</xsl:if>
		<xsl:if test="not($unique_indexes)">
			<xsl:text>ALTER TABLE 
			
			<xsl:call-template name="makeTableNname">
				<xsl:with-param name="table" select="$name" />
			</xsl:call-template>
			
			&quot;</xsl:text>
			<xsl:value-of select="concat($table_name_prefix, $name)" />
			<xsl:text>&quot; ADD CONSTRAINT </xsl:text>
			<xsl:value-of select="concat($uniq_prefix, generate-id())" />
			<xsl:text> UNIQUE (</xsl:text>
			<xsl:apply-templates select="en:ref" />
			<xsl:text>);&#xA;</xsl:text>
		</xsl:if>
	</xsl:for-each>
	<xsl:if test="en:unique"><xsl:text>&#xA;</xsl:text></xsl:if>
</xsl:template>

<xsl:template match="en:entity | en:relationship" mode="createForeignKeys">
	<xsl:variable name="name" select="@name" />
	<xsl:for-each select="en:attribute[@ref]">
		<xsl:text>ALTER TABLE &quot;</xsl:text>
		<xsl:value-of select="concat($table_name_prefix, $name)" />
		<xsl:text>&quot; ADD FOREIGN KEY (&quot;</xsl:text>
		<xsl:value-of select="@name" />
		<xsl:text>&quot;) REFERENCES &quot;</xsl:text>
		<xsl:value-of select="concat($table_name_prefix, @ref)" />
		<xsl:text>&quot;;&#xA;</xsl:text>
	</xsl:for-each>
	<xsl:if test="en:attribute[@ref]"><xsl:text>&#xA;</xsl:text></xsl:if>
</xsl:template>


<xsl:template match="text()|@*" mode="deleteEverything" />
<xsl:template match="text()|@*" mode="createTable" />
<xsl:template match="text()|@*" mode="createUniqueKeys" />
<xsl:template match="text()|@*" mode="createForeignKeys" />
<xsl:template match="text()|@*" />

</xsl:transform>