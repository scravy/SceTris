<?xml version="1.0"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:h="http://www.w3.org/1999/xhtml">

<xsl:template match="/">

<h:html>
<h:head>
	<h:title></h:title>
</h:head>
<h:body>
	<h:table>
	<xsl:for-each select="log/logentry">
		<xsl:variable name="name" select="author/text()" />
		<xsl:if test="generate-id(/log/logentry[author/text() = $name][1]/author) = generate-id(author)">
		<h:tr>
			<h:td><xsl:value-of select="$name" /></h:td>
			<h:td><xsl:value-of select="count(/log/logentry[author/text() = $name])" /></h:td>
			<h:td><xsl:value-of select="round(count(/log/logentry[author/text() = $name]) div count(/log/logentry) * 10000) div 100" /></h:td>
		</h:tr>
		</xsl:if>
	</xsl:for-each>
	</h:table>
</h:body>
</h:html>
</xsl:template>

<xsl:template match="text()" />
<xsl:template match="*" />

</xsl:transform>