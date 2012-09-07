<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:data="http://technodrom.scravy.de/2010/data">

<xsl:import href="global.xsl" />

<xsl:template match="data:data">
	<h1>About scetris</h1>
	<xsl:choose>
		<xsl:when test="data:message">
			<h2>About <xsl:value-of select="data:message" /></h2>
			<table>
				<xsl:for-each select="data:action">
					<tr>
						<td><xsl:value-of select="." /></td>
						<td><xsl:value-of select="@template" /></td>
					</tr>
				</xsl:for-each>
			</table>
		</xsl:when>
		<xsl:otherwise>
			<h2>Loaded Modules</h2>
			<ul>
			<xsl:for-each select="data:module">
				<li>
					<a>
						<xsl:attribute name="href">
							<xsl:text>module/</xsl:text>
							<xsl:if test="@name">
								<xsl:value-of select="@name" />
							</xsl:if>
						</xsl:attribute>
						<xsl:value-of select="." />
					</a>
				</li>
			</xsl:for-each>
			</ul>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

</xsl:transform>