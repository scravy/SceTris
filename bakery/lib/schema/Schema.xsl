<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:h="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema">

<xsl:template match="/">
<h:html>
	<h:head>
		<h:title>Schema for <xsl:value-of select="xs:schema/@targetNamespace" /></h:title>
		<h:style>
			@namespace url("http://www.w3.org/1999/xhtml");
			body, p, th, td {
				font-family: "Trebuchet MS", "Lucida Grande", "DejaVu Sans", "Helvetica", sans-serif;
				font-size: 10pt;
			}
			h2 {
				border-bottom: 0.1em solid silver;
			}
			dt {
				display: block;
				width: 10em;
				float: left;
				font-weight: bold;
				font-style: italic;
			}
		</h:style>
	</h:head>
	<h:body>
	<h:h1>Schema for <xsl:value-of select="xs:schema/@targetNamespace" /></h:h1>
	<h:h2>Complex Types</h:h2>
	<xsl:apply-templates mode="types" select="xs:schema/xs:complexType" />
	<h:h2>Elements</h:h2>
	<xsl:apply-templates mode="elements" select="xs:schema/xs:element" />
	</h:body>
</h:html>
</xsl:template>

<xsl:template match="xs:element" mode="elements">
	<h:h3><xsl:value-of select="@name" /></h:h3>
	<h:dl>
		<xsl:if test="@type">
		<h:dt>type</h:dt>
		<h:dd><xsl:value-of select="@type" /></h:dd>
		</xsl:if>
	</h:dl>
	<xsl:apply-templates mode="documentation" />
</xsl:template>

<xsl:template match="xs:complexType" mode="types">
	<h:h3><xsl:value-of select="@name" /></h:h3>
	<xsl:apply-templates mode="documentation" />
</xsl:template>

<xsl:template match="xs:annotation" mode="documentation">
	<xsl:if test="xs:documentation">
	<xsl:copy-of select="xs:documentation/h:*" />
	</xsl:if>
</xsl:template>

<xsl:template match="text()" />
<xsl:template match="*" />

</xsl:stylesheet>