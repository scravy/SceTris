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
	<li><span class="heading"><xsl:value-of select="$lang/lectures" /></span>
		<ul>
			<xsl:if test="//vars:mode/text() = 'mode'">
			<li>
				<a href="{$module-path}/facilities"><xsl:value-of select="$lang/admin-facilities" /></a>
				<xsl:if test="contains(',,facilities,createDepartment,listDepartments,editDepartment,showDepartment,createBuilding,listBuildings,editBuiling,showBuilding,createRoom,editRoom,showRoom,listRooms,createFeature,listFeatures,editFeature,showFeature, ', concat(',', $action, ','))">
				<ul class="inline">
					<xsl:if test="$isSuperuser or $privileges/admin.listDepartments">
					<li><a href="{$module-path}/listDepartments#list"><xsl:value-of select="$mod-lang/listDepartmentsShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createDepartment">
						(<a href="{$module-path}/listDepartments#new"><xsl:value-of select="$mod-lang/createDepartmentShort" /></a>)</xsl:if></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.listBuildings">
					<li><a href="{$module-path}/listBuildings#list"><xsl:value-of select="$mod-lang/listBuildingsShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createBuilding">
						(<a href="{$module-path}/listBuildings#new"><xsl:value-of select="$mod-lang/createBuildingShort" /></a>)</xsl:if></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.listRooms">
					<li><a href="{$module-path}/listRooms"><xsl:value-of select="$mod-lang/listRoomsShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createRoom">
						(<a href="{$module-path}/createRoom"><xsl:value-of select="$mod-lang/createRoomShort" /></a>)</xsl:if></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.listFeatures">
					<li><a href="{$module-path}/listFeatures"><xsl:value-of select="$mod-lang/listFeaturesShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createFeature">
						(<a href="{$module-path}/listFeatures#new"><xsl:value-of select="$mod-lang/createFeatureShort" /></a>)</xsl:if></li>
					</xsl:if>
				</ul>
				</xsl:if>
			</li>
			</xsl:if>		
		</ul>
	</li>
</xsl:template>
	
</xsl:transform>
