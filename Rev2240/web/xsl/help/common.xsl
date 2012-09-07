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

<xsl:variable name="mod-lang" select="document(concat('../i18n/admin.', /vars:meta/@lang, '.xml'))/lang" />

<xsl:template mode="title" match="vars:meta">
	<xsl:value-of select="$mod-lang/*[local-name() = concat($action, '.title')]" />
</xsl:template>

<xsl:template mode="menu" match="vars:meta">
	<li><span class="heading"><xsl:value-of select="$lang/admin" /></span>
		<ul>
			<li>
				<a href="{$module-path}/facilities"><xsl:value-of select="$lang/admin-facilities" /></a>
				<ul class="inline">
					<li><a href="{$module-path}/listDepartments#list"><xsl:value-of select="$mod-lang/listDepartmentsShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createDepartment">
						(<a href="{$module-path}/listDepartments#new"><xsl:value-of select="$mod-lang/createDepartmentShort" /></a>)</xsl:if></li>
				</ul>
			</li>
		</ul>
	</li>
</xsl:template>
	
</xsl:transform>
