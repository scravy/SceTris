<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="presentation.global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>User details</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Details</h1>
	<xsl:if test="count(vars:users/item:users) &lt; 1"><p>Unbekannter Benutzer.</p></xsl:if>
	<xsl:apply-templates select="vars:users/item:users" />
</xsl:template>

<xsl:template match="item:users">
	<table>
	<xsl:for-each select="@*">
		<tr>
			<th><xsl:value-of select="local-name()" /></th>
			<td><xsl:value-of select="." /></td>
		</tr>
	</xsl:for-each>
	<tr>
		<td colspan="2">
				<a href="{/vars:meta/@module-path}/edit/?user={@loginName}">	edit</a>
		</td>
	</tr>
	</table>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
