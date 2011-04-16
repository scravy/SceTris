<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/">

<xsl:output method="text" charset="UTF-8" />

<xsl:template match="/en:relations">
	<xsl:for-each select="en:entity">
		<xsl:value-of select="@name" />
		<xsl:text> </xsl:text>
	</xsl:for-each>
	<xsl:text>&#xA;</xsl:text>
</xsl:template>

</xsl:transform>
