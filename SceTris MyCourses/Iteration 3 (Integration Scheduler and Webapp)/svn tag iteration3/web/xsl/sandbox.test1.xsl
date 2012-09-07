<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta" mode="style">

li {
	display: inline;
	list-item-style: none;
}

li:not(:last-child)::after {
	content: " / ";
}  

</xsl:template>

<xsl:template match="meta:meta">
	<h2>This is test1.</h2>
	<p>Depth is <xsl:value-of select="count(meta:target/*)" /></p>
	<h3>Try it out (<a href="test1/">infinite loop</a>)</h3>
	<ul>
		<li><a href="{/*/@module-path}/test1">(none)</a></li>
		<li><a href="{/*/@module-path}/test1/">(empty)</a></li>
		<li><a href="{/*/@module-path}/test1/and">and</a></li>
		<li><a href="{/*/@module-path}/test1/and/now">now</a></li>
		<li><a href="{/*/@module-path}/test1/and/now/for">for</a></li>
		<li><a href="{/*/@module-path}/test1/and/now/for/something">something</a></li>
		<li><a href="{/*/@module-path}/test1/and/now/for/something/completely">completely</a></li>
		<li><a href="{/*/@module-path}/test1/and/now/for/something/completely/different">different</a></li>
	</ul>
	
	<h3>See the result here</h3>
	<ul>
	<xsl:for-each select="//item:target">
		<li>Target: <xsl:value-of select="." /></li>
	</xsl:for-each>
	</ul>
</xsl:template>

</xsl:transform>