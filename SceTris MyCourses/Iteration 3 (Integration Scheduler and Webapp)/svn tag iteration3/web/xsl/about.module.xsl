<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta">
	<h2>About “<xsl:value-of select="data:module-name" />”</h2>
	<table>
		<thead>
			<tr>
				<th colspan="2">Actions</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Name</th>
				<th>Template</th>
			</tr>
			<xsl:for-each select="data:action">
			<tr>
				<td>
					<a>
						<xsl:attribute name="href">
							<xsl:text>../../</xsl:text>
							<xsl:value-of select="/data:data/data:module-name/@name" />
							<xsl:text>/</xsl:text>
							<xsl:value-of select="." />
						</xsl:attribute>
						<xsl:choose>
							<xsl:when test="string-length(string(.)) > 0">
								<xsl:value-of select="." />
							</xsl:when>
							<xsl:otherwise>
								<i>default</i>
							</xsl:otherwise>
						</xsl:choose>
					</a>
				</td>
				<td>
					<xsl:value-of select="@template" />
				</td>
			</tr>
			</xsl:for-each>
		</tbody>
	</table>
</xsl:template>

</xsl:transform>