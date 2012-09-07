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

<xsl:template mode="menu" match="vars:meta">

<h5>Showcase</h5>
<ul>
	<li><a href="{$servlet-path}/showcase_formBuilder">
		<xsl:value-of select="$mod-lang/formBuilder/heading" />
	</a></li>
	<li><a href="{$servlet-path}/showcase_formControls">
		<xsl:value-of select="$mod-lang/formControls/heading" />
	</a></li>
	<li><a href="{$servlet-path}/showcase_formValidation">
		<xsl:value-of select="$mod-lang/formValidation/heading" />
	</a></li>
	<li><a href="{$servlet-path}/showcase_multipleForms">
		<xsl:value-of select="$mod-lang/multipleForms/heading" />
	</a></li>
	<li><a href="{$servlet-path}/showcase_extendedForm">
		<xsl:value-of select="$mod-lang/extendedForm/heading" />
	</a></li>
	<li><a href="{$servlet-path}/showcase_acgtForms">
		<xsl:value-of select="$mod-lang/acgtForms/heading" />
	</a></li>
</ul>

</xsl:template>
	
</xsl:transform>
