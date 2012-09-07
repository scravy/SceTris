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
	<xsl:value-of select="$lang/admin-facilities" />
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	
	<h1><xsl:value-of select="$lang/admin-facilities" /></h1>
	
	<p>
		<xsl:value-of select="$mod-lang/facilitiesDescription" />
	</p>
	
	<h3><xsl:value-of select="$mod-lang/buildings" /></h3>
	<p>
		<xsl:value-of select="$mod-lang/buildingsDescription" />
	</p>
	<ul>
		<li><a href="{$module-path}/createBuilding"><xsl:value-of select="$mod-lang/createBuilding" /></a></li>
		<li><a href="{$module-path}/listBuildings"><xsl:value-of select="$mod-lang/listBuildings" /></a></li>
	</ul>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">quickEditBuildings</xsl:with-param>
	</xsl:call-template>
	
	<h3><xsl:value-of select="$mod-lang/roomsAndFeatures" /></h3>
	<p>
		<xsl:value-of select="$mod-lang/roomsAndFeaturesDescription" />
	</p>
	<ul>
		<li><a href="{$module-path}/createRoom"><xsl:value-of select="$mod-lang/createRoom" /></a></li>
		<li><a href="{$module-path}/createFeature"><xsl:value-of select="$mod-lang/createFeature" /></a></li>
		<li><a href="{$module-path}/listFeatures"><xsl:value-of select="$mod-lang/listFeatures" /></a></li>
	</ul>
	
	<h3><xsl:value-of select="$mod-lang/departments" /></h3>
	<p>
		<xsl:value-of select="$mod-lang/departmentsDescription" />
	</p>
	<ul>
		<li><a href="{$module-path}/createDepartment"><xsl:value-of select="$mod-lang/createDepartment" /></a></li>
		<li><a href="{$module-path}/listDepartments"><xsl:value-of select="$mod-lang/listDepartments" /></a></li>
	</ul>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">quickEditDepartments</xsl:with-param>
	</xsl:call-template>
	
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

</xsl:transform>
