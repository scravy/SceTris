<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Sitemap</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Sitemap</h1>
	<xsl:apply-templates select="vars:modules/item:modules" />
</xsl:template>

<xsl:template match="item:modules">
	<xsl:variable name="module" select="@name" />
	<h3><xsl:value-of select="@name" /></h3>
	<ul>
		<xsl:for-each select="vars:actions/item:actions">
			<li>
				<a href="{/vars:meta/@servlet-path}/{$module}/{@name}">
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

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
