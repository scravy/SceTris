<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:lang="http://technodrom.scravy.de/2010/lang"
	exclude-result-prefixes="vars item lang form xsl">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:import href="common.xsl" />
<xsl:import href="../lego/bricks.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:value-of select="$lang/admin-imex" />
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="$lang/admin-imex" /></h1>
	<p>
		<xsl:value-of select="$mod-lang/imexDescription" />
	</p>

	<h3><xsl:value-of select="$mod-lang/export" /></h3>
	<p>
		<xsl:value-of select="$mod-lang/exportDescription" />
	</p>
	<ul>
		<li><a href="{$module-path}/export"><xsl:value-of select="$lang/export-all" /></a></li>
	</ul>

	<h3><xsl:value-of select="$mod-lang/import" /></h3>
	<p>
		<xsl:value-of select="$mod-lang/importDescription" />
	</p>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="mixin" select="document('')/*/form:importXML/*" />
		<xsl:with-param name="form">importXML</xsl:with-param>
	</xsl:call-template>
	
</xsl:template>

<form:importXML>
	<form:input-textarea name="data" />
</form:importXML>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

</xsl:transform>
