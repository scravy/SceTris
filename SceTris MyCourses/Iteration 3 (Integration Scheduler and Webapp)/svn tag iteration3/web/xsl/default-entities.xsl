<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:meta="http://technodrom.scravy.de/2010/data" 
	xmlns:item="http://technodrom.scravy.de/2010/item" >

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta">
	<xsl:variable name="module">
		<xsl:call-template name="capitalize">
			<xsl:with-param	name="theString" select="/*/@action"/>
		</xsl:call-template>
	</xsl:variable>		
	
	<h4>module :: <xsl:value-of select="$module" /></h4>
	<xsl:variable name="action" select="meta:target/item:target[1]" />
	<xsl:value-of select="$action" />
	<xsl:for-each select="meta:target/item:target">
		<h4>action :: <xsl:value-of select="." /></h4>
	</xsl:for-each>	
	
	<xsl:if test="$action='create'" >
		<xsl:call-template name="form_construct">
			<xsl:with-param name="name" select="'ressources'" />
			<xsl:with-param name="action" select="'POST'" />
			<xsl:with-param name="method" select="'path/to/script'" />
			<xsl:with-param name="entity" select="$module" />		<!-- tokenize then take the last -->
		</xsl:call-template>
	</xsl:if>
	<xsl:if test="$action='list'">
		<xsl:apply-templates mode="displayData" select="meta:data" />
	</xsl:if>
</xsl:template>

<xsl:template match="meta:data" mode="displayData">
	<h1>Hilikus!</h1>
	<table>
	<tr>
		<xsl:for-each select="item:data[1]/@*">
			<th><xsl:value-of select="local-name()" /></th>
		</xsl:for-each>
	</tr>
	<xsl:for-each select="item:data">
	<tr>
		<xsl:for-each select="@*">
			<td><xsl:value-of select="." /></td>
		</xsl:for-each>
	</tr>
	</xsl:for-each>
	</table>
</xsl:template>

</xsl:transform>