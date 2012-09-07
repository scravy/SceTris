<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">editDepartment</xsl:with-param>
		<xsl:with-param name="hideOnSuccess" select="boolean(1)" />
	</xsl:call-template>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/users"><xsl:value-of select="$lang/admin-users" /></a>
	</li>
	<li>
		<a href="{$module-path}/listUsers"><xsl:value-of select="$mod-lang/listUsers" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/editUser" />
	</li>
</xsl:template>

</xsl:transform>
