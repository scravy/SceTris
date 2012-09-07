<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta">

	<h2>Welcome to the Sandbox</h2>
	<ul>
		<li><a href="{@servlet-path}/showcase/test1">showcase (1)</a></li>
		<li><a href="{@servlet-path}/showcase/test2">showcase (2)</a></li>
		<li><a href="{@servlet-path}/showcase/test3">showcase (3)</a></li>
		<li><a href="{@servlet-path}/showcase/test4">showcase (4)</a></li>
	</ul>
</xsl:template>

</xsl:transform>