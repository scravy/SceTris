<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">

<xsl:template match="/xsl:transform | /xsl:stylesheet">
	
<html>
	<head>
		<title></title>
		<style type="text/css">
		
p {
	border-top: 1px solid black;
	border-bottom: 1px solid black;
	margin: .75em 0;
	padding: .25em;
}
		
		</style>
	</head>
	<body>
	
		<xsl:apply-templates>
			<xsl:sort select="@mode" />
			<xsl:sort select="@match" />
			<xsl:sort select="@name" />
		</xsl:apply-templates>
	
	</body>
</html>
	
</xsl:template>

<xsl:template match="xsl:template">

<p><xsl:if test="@mode">Mode: <code><xsl:value-of select="@mode" /></code><br /></xsl:if>
<xsl:if test="not(@name)">Match: <code>
	<xsl:value-of select="@match" />
</code></xsl:if>
<xsl:if test="@name">Name: <code>
	<xsl:value-of select="@name" />
</code></xsl:if></p>

<pre>
	<xsl:apply-templates mode="trace" />
</pre>

</xsl:template>

<xsl:template name="repeat-string">
	<xsl:param name="count" />
	<xsl:param name="theString" />
	<xsl:if test="$count > 0">
		<xsl:value-of select="$theString" />
		<xsl:call-template name="repeat-string">
			<xsl:with-param name="theString" select="$theString" />
			<xsl:with-param name="count" select="$count - 1" />
		</xsl:call-template>
	</xsl:if>
</xsl:template>

<xsl:template mode="trace" match="*">
	<xsl:param name="indent" select="0" />


	<xsl:call-template name="repeat-string">
		<xsl:with-param name="count" select="$indent" />
		<xsl:with-param name="theString">&#160;</xsl:with-param>
	</xsl:call-template>

	<xsl:text>&lt;</xsl:text>
	<xsl:value-of select="local-name()" />
	
	<xsl:choose>
		<xsl:when test="count(child::node()) > 1 or count(child::*) > 0">
			<xsl:text>&gt;&#xA;</xsl:text>
			<xsl:apply-templates mode="trace">
				<xsl:with-param name="indent" select="$indent + 1" />
			</xsl:apply-templates>
			
			<xsl:call-template name="repeat-string">
				<xsl:with-param name="count" select="$indent" />
				<xsl:with-param name="theString">&#160;</xsl:with-param>
			</xsl:call-template>
			
			<xsl:text>&lt;/</xsl:text>
			<xsl:value-of select="local-name()" />
		</xsl:when>
		<xsl:when test="count(child::node()) = 1">
			<xsl:text>&gt;</xsl:text>
			<xsl:apply-templates mode="trace">
				<xsl:with-param name="indent" select="$indent + 1" />
			</xsl:apply-templates>
			
			<xsl:text>&lt;/</xsl:text>
			<xsl:value-of select="local-name()" />
		</xsl:when>
		<xsl:when test="count(child::node()) = 0">
			<xsl:text> /</xsl:text>
		</xsl:when>
	</xsl:choose>
	
	<xsl:text>&gt;&#xA;</xsl:text>

</xsl:template>

<xsl:template mode="trace" match="text()">
	<xsl:choose>
		<xsl:when test="local-name(parent::node()) = 'text'">
			<xsl:value-of select="translate(., ' ', '&#160;')" />
		</xsl:when>
	</xsl:choose>
</xsl:template>

</xsl:transform>