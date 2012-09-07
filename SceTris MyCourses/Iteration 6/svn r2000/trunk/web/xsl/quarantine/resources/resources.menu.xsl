<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:vars="http://technodrom.scravy.de/2010/data" 
	xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />


<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Resources Menu</xsl:text>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<xsl:text> &#x2192; Resources</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<!-- main content -->
	<h2>Resources</h2>
	
	<!--	
	</ul>	
	<xsl:call-template name="menulist">
		<xsl:with-param name="options">
			<option value="buildinglist">Edit Buildgings</option>
			<option value="buildingnew">Create new buildging</option>
			<option value="departmentlist">Edit departments</option>
		</xsl:with-param>
	</xsl:call-template>
	-->
	
	<!--
	<ul>
		<li><a href="{$servlet-path}/resources/buildinglist" title="Edit buildings">Edit Buildings</a></li>
		<li><a href="{$servlet-path}/resources/buildingnew"  title="Create new building">Create new building</a></li>
		<li><a href="{$servlet-path}/resources/departmentlist" title="Edit departments">Edit Departments</a></li>
		<li><a href="{$servlet-path}/resources/departmentnew" title="Create new department">Create new department</a></li>
		<li><a href="{$servlet-path}/resources/roomlist" title="Edit rooms">Edit Rooms</a></li>
		<li><a href="{$servlet-path}/resources/roomnew"  title="Create new room">Create new room</a></li>
	</ul> -->
	
	
	<ul style="float: left; width: 300px; margin:0; ">
		<li>Buildings
			<ul>
				<li><a href="{/vars:meta/@module-path}/buildinglist">Edit Buildings</a></li> 
				<li><a href="{/vars:meta/@module-path}/buildingnew">New Building</a></li>
			</ul></li>
		<li>Departments
			<ul>
				<li><a href="{/vars:meta/@module-path}/departmentlist">Edit Departments</a></li>
				<li><a href="{/vars:meta/@module-path}/departmentnew">New Department</a></li>
			</ul></li>		
	</ul>
	<ul style="margin-left: 300px;">
		<li>Features
			<ul>
				<li><a href="{/vars:meta/@module-path}/featurelist">Edit Features</a></li>
				<li><a href="{/vars:meta/@module-path}/featurenew">New Feature</a></li>
			</ul></li>
		<li>Rooms
			<ul>
				<li><a href="{/vars:meta/@module-path}/roomlist">Edit Rooms</a></li>
				<li><a href="{/vars:meta/@module-path}/roomnew">New Room</a></li>
				<li><a href="{/vars:meta/@module-path}/roomtimetable">View Timetables</a></li>
			</ul></li>	
	</ul>
	<br style="clear:both"/>
</xsl:template>

<xsl:template name="menulist">
	<xsl:param name="options" />
	<ul>
		<xsl:for-each select="$options">
			<li><a href="{@target}" title="{text()}"><xsl:value-of select="text()"/></a></li>
		</xsl:for-each>
	</ul>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
