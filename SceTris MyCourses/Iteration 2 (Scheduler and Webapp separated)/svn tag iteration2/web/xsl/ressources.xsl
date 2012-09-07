<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />


<xsl:template match="meta:meta">

	<h2>Welcome to scetris, Ressources</h2>
	<h3>Select a submenu to edit specific data</h3>
	
	<xsl:call-template name="form_construct">
		<xsl:with-param name="name" select="'ressources'" />
		<xsl:with-param name="action" select="'POST'" />
		<xsl:with-param name="method" select="'path/to/script'" />
		<xsl:with-param name="entity" select="'CourseInstance'" />		<!-- tokenize then take the last -->
	</xsl:call-template>


	<xsl:call-template name="form_construct">
		<xsl:with-param name="name" select="'ressources'" />
		<xsl:with-param name="action" select="'POST'" />
		<xsl:with-param name="method" select="'path/to/script'" />
		<xsl:with-param name="entity" select="'Person'" />		<!-- tokenize then take the last -->
	</xsl:call-template>
		
</xsl:template>

</xsl:transform>