<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:template match="*" mode="debug">
	<div>
		<span>Element: <xsl:value-of select="local-name()" /> (NS: <xsl:value-of select="namespace-uri()" />)</span>
		<xsl:if test="@*">
			<div>
				<span>Attributes:</span>
				<xsl:apply-templates select="@*" mode="debug-attrs" />
			</div>
		</xsl:if>
		<div>
			<span>Content:</span>
			<xsl:apply-templates mode="debug" />
		</div>
	</div>
</xsl:template>

<xsl:template match="@*" mode="debug-attrs">
	<div>
		<xsl:value-of select="local-name()" />: <xsl:value-of select="." />
	</div>
</xsl:template>

<xsl:template match="text()" mode="debug">
	<div>Text: <xsl:value-of select="." /></div>
</xsl:template>

</xsl:transform>
