<?xml version="1.0" encoding="UTF-8"?>
<!--
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
-->
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../common/global.xsl" />

<xsl:variable name="mod-lang" select="document(concat('showcase.', /vars:meta/@lang, '.xml'))/lang" />

<xsl:template name="intro">
	<h1><xsl:value-of select="$mod-lang/*[local-name() = $module]/heading" /></h1>
	<p>
		<xsl:value-of select="$mod-lang/*[local-name() = $module]/description" />
	</p>
</xsl:template>

<xsl:template mode="title" match="vars:meta">
	<xsl:value-of select="$mod-lang/*[local-name() = $module]/title" />
</xsl:template>

<xsl:template mode="menu" match="vars:meta">
	<li><span class="heading">Showcase</span>
		<ul>
			<li><a href="{$servlet-path}/showcase_formControls">
				<xsl:value-of select="$mod-lang/showcase_formControls/heading" />
			</a></li>
			<li><a href="{$servlet-path}/showcase_formBuilder">
				<xsl:value-of select="$mod-lang/showcase_formBuilder/heading" />
			</a></li>
			<li><a href="{$servlet-path}/showcase_formValidation">
				<xsl:value-of select="$mod-lang/showcase_formValidation/heading" />
			</a></li>
			<li><a href="{$servlet-path}/showcase_formCommitException">
				<xsl:value-of select="$mod-lang/showcase_formCommitException/heading" />
			</a></li>
			<li><a href="{$servlet-path}/showcase_advancedFormValidation">
				<xsl:value-of select="$mod-lang/showcase_advancedFormValidation/heading" />
			</a></li>
			<li><a href="{$servlet-path}/showcase_formReuse">
				<xsl:value-of select="$mod-lang/showcase_formReuse/heading" />
			</a></li>
			<li><a href="{$servlet-path}/showcase_formMultipleOptions">
				<xsl:value-of select="$mod-lang/showcase_formMultipleOptions/heading" />
			</a></li>
		</ul>
	</li>
</xsl:template>
	
</xsl:transform>
