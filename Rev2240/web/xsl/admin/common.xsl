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
			<xsl:if test="$isSuperuser or $privileges/admin.facilities">
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
			<xsl:if test="$isSuperuser or $privileges/admin.users">
			<li>
				<a href="{$module-path}/users"><xsl:value-of select="$lang/admin-users" /></a>
				<xsl:if test="contains(',,users,createUser,listUsers,showUser,editUser,createRole,listRoles,showRole,editRole, ', concat(',', $action, ','))">
				<ul class="inline">
					<xsl:if test="$isSuperuser or $privileges/admin.listUsers">
					<li><a href="{$module-path}/listUsers"><xsl:value-of select="$mod-lang/listUsersShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createUser">
						(<a href="{$module-path}/createUser"><xsl:value-of select="$mod-lang/createUserShort" /></a>)</xsl:if></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.listRoles">
					<li><a href="{$module-path}/listRoles"><xsl:value-of select="$mod-lang/listRolesShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createRole">
						(<a href="{$module-path}/createRole"><xsl:value-of select="$mod-lang/createRoleShort" /></a>)</xsl:if></li>
					</xsl:if>
				</ul>
				</xsl:if>
			</li>
			</xsl:if>
			<xsl:if test="$isSuperuser or $privileges/admin.courses">
			<li>
				<a href="{$module-path}/courses"><xsl:value-of select="$lang/admin-courses" /></a>
				<xsl:if test="contains(',,courses,schedulerPanel,listPrograms,createProgram,editProgram,editProgramLecturer,listAcademicTerms,createAcademicTerm,listCourses,createCourse,listCourseElements,createCourseElement,createCourseElementInstance,createCourseInstance,editCourse,showCourse,showCourseInstance,editCourseInstance,showCourseElement,showCourseElementInstance,editCourseElement,editCourseElementInstance,listYears,createYear,listCET,editCET,createCET, ', concat(',', $action, ','))">
				<ul class="inline">
					<xsl:if test="$isSuperuser or $privileges/admin.createCourse">
					<li><a href="{$module-path}/listCourses"><xsl:value-of select="$mod-lang/listCoursesShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createCourse">
						(<a href="{$module-path}/createCourse"><xsl:value-of select="$mod-lang/createCourseShort" /></a>)</xsl:if></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.createCourseElement">
					<li><a href="{$module-path}/listCourseElements"><xsl:value-of select="$mod-lang/listCourseElementsShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createCourseElement">
						(<a href="{$module-path}/createCourseElement"><xsl:value-of select="$mod-lang/createCourseElementShort" /></a>)</xsl:if></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.listPrograms">
					<li><a href="{$module-path}/listPrograms"><xsl:value-of select="$mod-lang/listProgramsShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createProgram">
						(<a href="{$module-path}/createProgram"><xsl:value-of select="$mod-lang/createProgramShort" /></a>)</xsl:if></li>
					</xsl:if>					
					<xsl:if test="$isSuperuser or $privileges/admin.listAcademicTerms">
					<li><a href="{$module-path}/listAcademicTerms"><xsl:value-of select="$mod-lang/listAcademicTermsShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createAcademicTerm">
						(<a href="{$module-path}/createAcademicTerm"><xsl:value-of select="$mod-lang/createAcademicTermShort" /></a>)</xsl:if></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.listYears">
					<li><a href="{$module-path}/listYears"><xsl:value-of select="$mod-lang/listYearsShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createYear">
						(<a href="{$module-path}/createYear"><xsl:value-of select="$mod-lang/createYearShort" /></a>)</xsl:if></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.listCET">
					<li><a href="{$module-path}/listCET"><xsl:value-of select="$mod-lang/listCETShort" /></a>
						<xsl:if test="$isSuperuser or $privileges/admin.createCET">
						(<a href="{$module-path}/createCET"><xsl:value-of select="$mod-lang/createCETShort" /></a>)</xsl:if></li>
					</xsl:if>
				</ul>
				</xsl:if>
			</li>
			</xsl:if>
			<xsl:if test="$isSuperuser or $privileges/admin.imex">
			<li>
				<a href="{$module-path}/imex"><xsl:value-of select="$lang/admin-imex" /></a>
				<xsl:if test="contains(',,imex,import,export,specialImport,specialExport,', concat(',', $action, ','))">
				<ul class="inline">
					<xsl:if test="$isSuperuser or $privileges/admin.import">
					<li><a href="{$module-path}/import"><xsl:value-of select="$mod-lang/import" /></a></li>
					</xsl:if>
					
					<xsl:if test="$isSuperuser or $privileges/admin.export">
					<li><a href="{$module-path}/export"><xsl:value-of select="$mod-lang/export" /></a></li>
					</xsl:if>
					<!-- 
					<xsl:if test="$isSuperuser or $privileges/admin.specialImport">
					<li><a href="{$module-path}/specialImport"><xsl:value-of select="$mod-lang/specialImport" /></a></li>
					</xsl:if>
					
					<xsl:if test="$isSuperuser or $privileges/admin.specialExport">
					<li><a href="{$module-path}/specialExport"><xsl:value-of select="$mod-lang/specialExport" /></a></li>
					</xsl:if> -->
				</ul>
				</xsl:if>
			</li>
			</xsl:if>
			<xsl:if test="$isSuperuser or $privileges/admin.sysconfig">
			<li>
				<a href="{$module-path}/sysconfig"><xsl:value-of select="$lang/admin-sysconfig" /></a>
				<xsl:if test="contains(',,sysconfig,createPrivilege,listPrivileges,editPrivilege,showPrivilege,createCourseAttribute,createAttribute,editAttribute,showAttribute,listAttributes, ', concat(',', $action, ','))">
				<ul class="inline">
					<xsl:if test="$isSuperuser or $privileges/admin.listPrivileges">
					<li><a href="{$module-path}/listPrivileges"><xsl:value-of select="$mod-lang/listPrivilegesShort" /></a>
						(<a href="{$module-path}/createPrivilege"><xsl:value-of select="$mod-lang/createPrivilegeShort" /></a>)</li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.listAttributes">
					<li><a href="{$module-path}/listAttributes"><xsl:value-of select="$mod-lang/listAttributesShort" /></a>
						(<a href="{$module-path}/createAttribute"><xsl:value-of select="$mod-lang/createAttributeShort" /></a>)</li>
					</xsl:if>

					<xsl:if test="$isSuperuser or $privileges/admin.listCourseAttributes">
					<li><a href="{$module-path}/listCourseAttributes"><xsl:value-of select="$mod-lang/listCourseAttributesShort" /></a>
						(<a href="{$module-path}/createCourseAttribute"><xsl:value-of select="$mod-lang/createCourseAttributeShort" /></a>)</li>
					</xsl:if>
					
					<xsl:if test="$isSuperuser or $privileges/admin.setSystemconfig">
					<li><a href="{$module-path}/listSystemconfig"><xsl:value-of select="$mod-lang/listSystemconfigShort" /></a></li>
					</xsl:if>
				</ul>
				</xsl:if>
			</li>
			</xsl:if>
			<xsl:if test="$isSuperuser or $privileges/admin.schedulerPanel">
				<li><a href="{$servlet-path}/admin/schedulerPanel"><xsl:value-of select="$mod-lang/listSchedulerPanel" /></a></li>
			</xsl:if>	
		</ul>
	</li>
</xsl:template>
	
</xsl:transform>
