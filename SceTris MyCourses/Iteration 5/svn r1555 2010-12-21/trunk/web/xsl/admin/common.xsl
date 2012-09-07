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

<xsl:template mode="menu" match="vars:meta">

<h5><xsl:value-of select="$lang/admin" /></h5>
<ul>
	<li><a href="{$module-path}/facilities">
		<xsl:value-of select="$lang/admin-facilities" />
	</a></li>
	<li><a href="{$module-path}/users">
		<xsl:value-of select="$lang/admin-users" />
	</a></li>
	<li><a href="{$module-path}/courses">
		<xsl:value-of select="$lang/admin-courses" />
	</a></li>
	<li><a href="{$module-path}/imex">
		<xsl:value-of select="$lang/admin-imex" />
	</a></li>
	<li><a href="{$module-path}/sysconfig">
		<xsl:value-of select="$lang/admin-sysconfig" />
	</a></li>
</ul>

</xsl:template>
	
</xsl:transform>
