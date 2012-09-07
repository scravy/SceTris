<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta">
	<h2>About Scetris (<a href="{/*/@module-path}/raw">display using debug template</a>)</h2>
	<h3>Modules loaded</h3>
	<table>
		<tbody>
			<tr>
				<th>Module</th>
				<th>Description</th>
				<th>Menu</th>
			</tr>
			<xsl:apply-templates select="meta:modules/child::*" mode="short" />
		</tbody>
	</table>
	<h3>Details</h3>
	<xsl:apply-templates select="meta:modules/child::*" mode="detail" />
</xsl:template>

<xsl:template match="item:modules" mode="short">
	<tr>
		<th>
			<a>
				<xsl:attribute name="href">
					<xsl:value-of select="concat(/*/@servlet-path, '/', @name, '/')" />
				</xsl:attribute>
				<xsl:value-of select="@name" />
			</a>
		</th>
		<td>
			<xsl:value-of select="@description" />
		</td>
		<td>
			<xsl:value-of select="@menu" />
		</td>
	</tr>
</xsl:template>

<xsl:template match="item:modules" mode="detail">
	<h4>
		<xsl:attribute name="id">mod.<xsl:value-of select="@name" /></xsl:attribute>
		<xsl:value-of select="@class" />
	</h4>
	
	<xsl:if test="string-length(@description) > 0">
		<p>
			<xsl:value-of select="@description" />
		</p>
	</xsl:if>
	
	<dl>
		<xsl:for-each select="@*[local-name() != 'description' and local-name() != 'class']">
			<xsl:sort select="@name" />
			<dt>
				<xsl:value-of select="local-name()" />
			</dt>
			<dd>
				<xsl:choose>
					<xsl:when test="string-length(.) = 0"><em>not available</em></xsl:when>
					<xsl:otherwise><xsl:value-of select="." /></xsl:otherwise>
				</xsl:choose>
			</dd>
		</xsl:for-each>
	</dl>
</xsl:template>
	
</xsl:transform>
