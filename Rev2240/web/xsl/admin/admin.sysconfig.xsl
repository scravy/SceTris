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
	<xsl:value-of select="$lang/admin-sysconfig" />
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	
	<h1><xsl:value-of select="$lang/admin-sysconfig" /></h1>
	
	<p>
		<xsl:value-of select="$mod-lang/sysconfigDescription" />
	</p>
	
	<h3><xsl:value-of select="$mod-lang/userAttributes" /></h3>
	<p>
		<xsl:value-of select="$mod-lang/userAttributesDescription" />
	</p>
	<ul>
		<li><a href="{$module-path}/createAttribute"><xsl:value-of select="$mod-lang/createAttribute" /></a></li>
		<li><a href="{$module-path}/listAttributes"><xsl:value-of select="$mod-lang/listAttributes" /></a></li>
	</ul>
	
	<h3><xsl:value-of select="$mod-lang/courseAttributes" /></h3>
	<p>
		<xsl:value-of select="$mod-lang/courseAttributesDescription" />
	</p>
	<ul>
		<li><a href="{$module-path}/createCourseAttribute"><xsl:value-of select="$mod-lang/createCourseAttribute" /></a></li>
		<li><a href="{$module-path}/listCourseAttributes"><xsl:value-of select="$mod-lang/listCourseAttributes" /></a></li>
	</ul>
	
	<h3><xsl:value-of select="$mod-lang/schedulerConfiguration"></xsl:value-of></h3>
	<p>
		<xsl:value-of select="$mod-lang/schedulerConfigurationDescription" />
	</p>
	<ul>
		<li><a href="#"><xsl:value-of select="$mod-lang/configureScheduler" /></a></li>
	</ul>
	
	<h3><xsl:value-of select="$mod-lang/systemStatus" /></h3>
	<p>
		<xsl:value-of select="$mod-lang/systemStatusDescription" />
	</p>
	<ul>
		<li><a href="#"><xsl:value-of select="$mod-lang/showStatistics" /></a></li>
		<li><a href="#"><xsl:value-of select="$mod-lang/showInstallationDetails" /></a></li>
	</ul>
	
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

</xsl:transform>
