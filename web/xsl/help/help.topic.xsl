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

<xsl:template mode="title" match="vars:meta">
	<xsl:value-of select="$lang/admin-users" />
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<xsl:copy-of select="document(concat($lang-code, '/', string(//vars:page[1]), '.xml'))/*" />
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/help" />
		<xsl:text>: </xsl:text>
		<xsl:value-of select="$mod-lang/help-topic" />
	</li>
</xsl:template>

</xsl:transform>
