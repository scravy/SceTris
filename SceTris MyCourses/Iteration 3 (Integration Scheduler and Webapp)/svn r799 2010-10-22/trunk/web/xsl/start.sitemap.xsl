<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta">
	<h2>Scetris Sitemap</h2>
	<xsl:apply-templates mode="modules" select="meta:modules/child::*" />
</xsl:template>


<xsl:template match="item:modules" mode="modules">
	<xsl:variable name="module" select="@name" />
	
	<h3><xsl:value-of select="@name" /></h3>
	
	<ul>
		<xsl:for-each select="meta:actions/item:actions">
			<li>
				<a href="{/*/@servlet-path}/{$module}/{@name}">
					<xsl:choose>
						<xsl:when test="string-length(@name) > 0">
							<xsl:value-of select="@name" />
						</xsl:when>
						<xsl:otherwise><i>default action</i></xsl:otherwise>
					</xsl:choose>
				</a>
			</li>
		</xsl:for-each>
	</ul>
</xsl:template>

<xsl:template match="text() | @*" mode="modules" />


</xsl:transform>
