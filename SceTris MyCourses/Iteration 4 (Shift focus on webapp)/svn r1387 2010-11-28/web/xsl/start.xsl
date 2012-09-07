<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Start</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Start</h1>
	
	<ul>
		<li><a href="{/vars:meta/@servlet-path}/users">Benutzerverwaltung</a></li>
		<li><a href="{/vars:meta/@servlet-path}/rights">Rechteverwaltung</a></li>
		<li><a href="sitemap">Sitemap</a></li>
	</ul>

</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
