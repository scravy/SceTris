<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../presentation.global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Usermanagement Menu</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Usermanagement</h1>
	
	<ul style="float: left; width: 300px; margin:0; ">
		<li>Persons
			<ul>
				<li><a href="{/vars:meta/@module-path}/#">Edit Persons</a></li>
				<li><a href="{/vars:meta/@module-path}/#">New Person</a></li>
				<li><a href="{/vars:meta/@module-path}/#">Edit Lecturers</a></li>
				<li><a href="{/vars:meta/@module-path}/#">New Lecturer</a></li>

			</ul></li>
		
	</ul>
	<ul style="margin-left: 300px;">
		<li>Rights Management
			<ul>
				<li><a href="{/vars:meta/@module-path}/#">New Role</a></li>
				<li><a href="{/vars:meta/@module-path}/#">New Privilege</a></li>
				<li><a href="{/vars:meta/@module-path}/#">New Groups</a></li>
				<li><a href="{/vars:meta/@module-path}/#">Edit Roles</a></li>
				<li><a href="{/vars:meta/@module-path}/#">Edit Privileges</a></li>
				<li><a href="{/vars:meta/@module-path}/#">Edit Groups</a></li>
				<li><a href="{/vars:meta/@module-path}/#">Assign Roles</a></li>
				<li><a href="{/vars:meta/@module-path}/#">Assign Privileges</a></li>
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
