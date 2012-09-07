<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />
<xsl:import href="lego.xsl" />

<xsl:variable name="mod-lang" select="document('i18n/showcase.xml')/lang" />

<xsl:template mode="title" match="vars:meta">
	<xsl:value-of select="$lang/help" />
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="$lang/help" /></h1>

	<xsl:call-template name="showcase-form" />

</xsl:template>

	<xsl:template name="showcase-form">
	
		<form onsubmit="return false;">
	
		<xsl:call-template name="input-text">
			<xsl:with-param name="id" select="'text'" />
		</xsl:call-template>
	
		<xsl:call-template name="input-date">
			<xsl:with-param name="id" select="'date'" />
		</xsl:call-template>

		<xsl:call-template name="input-alternatives">
			<xsl:with-param name="id">select1</xsl:with-param>
			<xsl:with-param name="options">
				<option value="">Bitte auswählen...</option>
				<option value="0">Angola</option>
				<option value="1">Andorra</option>
				<option value="2">Austria</option>
				<option value="3">Germania</option>
				<option value="7">Galia</option>
				<option value="6">Helvetia</option>
				<option value="4">Italia</option>
				<option value="5">Judäa</option>
			</xsl:with-param>
		</xsl:call-template>
		
		<xsl:call-template name="input-alternatives">
			<xsl:with-param name="id">select2</xsl:with-param>
			<xsl:with-param name="selected">7</xsl:with-param>
			<xsl:with-param name="options">
				<option value="0">Angola</option>
				<option value="1">Andorra</option>
				<option value="2">Austria</option>
				<option value="3">Germania</option>
				<option value="7">Galia</option>
				<option value="6" selected="selected">Helvetia</option>
				<option value="4">Italia</option>
				<option value="5">Judäa</option>
			</xsl:with-param>
		</xsl:call-template>
		
		</form>
	</xsl:template>

</xsl:transform>
