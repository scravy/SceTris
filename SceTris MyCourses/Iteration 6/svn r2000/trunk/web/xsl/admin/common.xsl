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
				<xsl:if test="contains(',facilities,createDepartment,listDepartments,editDepartment,showDepartment,createBuilding,listBuildings,editBuiling,showBuilding,createRoom,editRoom,showRoom,listRooms,createFeature,listFeatures,editFeature,showFeature,', concat(',', $action, ','))">
				<ul class="inline">
					<li><a href="{$module-path}/listDepartments#list"><xsl:value-of select="$mod-lang/listDepartmentsShort" /></a>
						(<a href="{$module-path}/listDepartments#new"><xsl:value-of select="$mod-lang/createDepartmentShort" /></a>)</li>
					<li><a href="{$module-path}/listBuildings"><xsl:value-of select="$mod-lang/listBuildingsShort" /></a>
						(<a href="{$module-path}/createBuilding"><xsl:value-of select="$mod-lang/createBuildingShort" /></a>)</li>
					<li><a href="{$module-path}/listRooms"><xsl:value-of select="$mod-lang/listRoomsShort" /></a>
						(<a href="{$module-path}/createRoom"><xsl:value-of select="$mod-lang/createRoomShort" /></a>)</li>
					<li><a href="{$module-path}/listFeatures"><xsl:value-of select="$mod-lang/listFeaturesShort" /></a>
						(<a href="{$module-path}/createFeature"><xsl:value-of select="$mod-lang/createFeatureShort" /></a>)</li>
				</ul>
				</xsl:if>
			</li>
			<li>
				<a href="{$module-path}/users"><xsl:value-of select="$lang/admin-users" /></a>
				<xsl:if test="contains(',users,createUser,listUsers,showUser,editUser,createRole,listRoles,showRole,editRole,createPrivilege,listPrivileges,showPrivilege,editPrivilege,', concat(',', $action, ','))">
				<ul class="inline">
					<li><a href="{$module-path}/listUsers"><xsl:value-of select="$mod-lang/listUsersShort" /></a>
						(<a href="{$module-path}/createUser"><xsl:value-of select="$mod-lang/createUserShort" /></a>)</li>
					<li><a href="{$module-path}/listRoles"><xsl:value-of select="$mod-lang/listRolesShort" /></a>
						(<a href="{$module-path}/createRole"><xsl:value-of select="$mod-lang/createRoleShort" /></a>)</li>
					<!-- <li><a href="{$module-path}/listGroups"><xsl:value-of select="$mod-lang/listGroupsShort" /></a>
						(<a href="{$module-path}/createGroup"><xsl:value-of select="$mod-lang/createGroupShort" /></a>)</li> -->
					<li><a href="{$module-path}/listPrivileges"><xsl:value-of select="$mod-lang/listPrivilegesShort" /></a>
						(<a href="{$module-path}/createPrivilege"><xsl:value-of select="$mod-lang/createPrivilegeShort" /></a>)</li>
				</ul>
				</xsl:if>
			</li>
			<li>
				<a href="{$module-path}/courses"><xsl:value-of select="$lang/admin-courses" /></a>
				<xsl:if test="contains(',courses,createCourse,createCourseElement,createCourseElementInstance,createCourseInstance,editCourse,showCourse,showCourseInstance,editCourseInstance,showCourseElement,showCourseElementInstance,editCourseElement,editCourseElementInstance,', concat(',', $action, ','))">
				<ul class="inline">
					<li><a href="{$module-path}/listCourses"><xsl:value-of select="$mod-lang/listCoursesShort" /></a>
						(<a href="{$module-path}/createCourses"><xsl:value-of select="$mod-lang/createCourseShort" /></a>)</li>
					<li><a href="{$module-path}/listAcademicTerms"><xsl:value-of select="$mod-lang/listAcademicTermsShort" /></a>
						(<a href="{$module-path}/createAcademicTerm"><xsl:value-of select="$mod-lang/createAcademicTermShort" /></a>)</li>
					<li><a href="{$module-path}/listYears"><xsl:value-of select="$mod-lang/listYearsShort" /></a>
						(<a href="{$module-path}/createYear"><xsl:value-of select="$mod-lang/createYearShort" /></a>)</li>
					<li><a href="{$module-path}/listCET"><xsl:value-of select="$mod-lang/listCETShort" /></a>
						(<a href="{$module-path}/createCET"><xsl:value-of select="$mod-lang/createCETShort" /></a>)</li>
					<li><a href="{$module-path}/schedulerPanel"><xsl:value-of select="$mod-lang/schedulerPanel" /></a></li>	
				</ul>
				</xsl:if>
			</li>
			<li>
				<a href="{$module-path}/imex"><xsl:value-of select="$lang/admin-imex" /></a>
				<xsl:if test="contains(',imex,import,export,specialImport,specialExport,', concat(',', $action, ','))">
				<ul class="inline">
					<li><a href="{$module-path}/import"><xsl:value-of select="$mod-lang/import" /></a></li>
					<li><a href="{$module-path}/export"><xsl:value-of select="$mod-lang/export" /></a></li>
					<li><a href="{$module-path}/specialImport"><xsl:value-of select="$mod-lang/specialImport" /></a></li>
					<li><a href="{$module-path}/specialExport"><xsl:value-of select="$mod-lang/specialExport" /></a></li>
				</ul>
				</xsl:if>
			</li>
			<li>
				<a href="{$module-path}/sysconfig"><xsl:value-of select="$lang/admin-sysconfig" /></a>
				<xsl:if test="contains(',sysconfig,createPrivilege,listPrivileges,editPrivilege,showPrivilege,createAttribute,editAttribute,showAttribute,listAttributes,', concat(',', $action, ','))">
				<ul class="inline">
					<li><a href="{$module-path}/listPrivileges"><xsl:value-of select="$mod-lang/listPrivilegesShort" /></a>
						(<a href="{$module-path}/createPrivilege"><xsl:value-of select="$mod-lang/createPrivilegeShort" /></a>)</li>
					<li><a href="{$module-path}/listAttributes"><xsl:value-of select="$mod-lang/listAttributesShort" /></a>
						(<a href="{$module-path}/createAttribute"><xsl:value-of select="$mod-lang/createAttributeShort" /></a>)</li>
				</ul>
				</xsl:if>
			</li>
		</ul>
	</li>
</xsl:template>
	
</xsl:transform>
