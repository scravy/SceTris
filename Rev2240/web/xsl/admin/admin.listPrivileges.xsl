<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:if test="not(//item:privileges)">
		<div class="ui-corner-all notice"><xsl:value-of select="$mod-lang/noPrivileges" /></div>
	</xsl:if>
	<xsl:if test="//item:privileges">
		<ul>
		<xsl:for-each select="//item:privileges">
			<li><xsl:value-of select="@name" /></li>
		</xsl:for-each>
		</ul>
	</xsl:if>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/sysconfig"><xsl:value-of select="$lang/admin-sysconfig" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/listPrivileges" />
	</li>
</xsl:template>

</xsl:transform>
