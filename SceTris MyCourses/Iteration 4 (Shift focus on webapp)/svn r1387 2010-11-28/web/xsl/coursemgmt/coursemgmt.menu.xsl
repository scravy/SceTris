<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Coursemanagement Menu</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<!-- main content -->
	<h1>Coursemanagement</h1>
	<ul style="float: left; width: 300px; margin:0; ">
		<li>Course
			<ul>
				<li><a href="{/vars:meta/@module-path}/courselist">Edit Courses</a></li> 
				<li><a href="{/vars:meta/@module-path}/coursenew">New Course</a></li>
			</ul></li>	
		<li>CourseElements
			<ul>
				<li><a href="{/vars:meta/@module-path}/courseelementlist">Edit Courseelements</a></li> 
				<li><a href="{/vars:meta/@module-path}/courseelementnew">New Courseelements</a></li>
			</ul></li>	
		<li>Scheduling
			<ul>
				<li><a href="{/vars:meta/@servlet-path}/scheduler/control">Controlpanel</a></li> 
			</ul></li>
	</ul>
	<ul style="margin-left: 300px;">
		<li>Academic Term
			<ul>
				<li><a href="{/vars:meta/@module-path}/academictermlist">Edit Academic Terms</a></li> 
				<li><a href="{/vars:meta/@module-path}/academictermnew">New Academic Term</a></li>
			</ul></li>
		<li>Programs
			<ul>
				<li><a href="{/vars:meta/@module-path}/programlist">Edit Programs</a></li> 
				<li><a href="{/vars:meta/@module-path}/programnew">New Program</a></li>
			</ul></li>
		<li>CourseInstances
			<ul>
				<li><a href="{/vars:meta/@module-path}/courseinstancelist">Edit Courseinstances</a></li> 
			</ul></li>	
		<li>CourseElementsInstances
			<ul>
				<li><a href="{/vars:meta/@module-path}/courseelementinstancelist">Edit Courseelementinstances</a></li> 
			</ul></li>			
	</ul>
	<br style="clear:both"/>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
