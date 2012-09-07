<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../presentation.global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Ressources Menu</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<!-- tooltip -->
	<p>
		<xsl:if test="//vars:roomcreated">
		<xsl:attribute name="class">success</xsl:attribute>
		<span>Room was created !</span>
		</xsl:if>
		<xsl:if test="//vars:roomupdated">
		<xsl:attribute name="class">success</xsl:attribute>
		<span>Room was updated !</span>
		</xsl:if>
		<xsl:if test="//vars:roomdeleted">
		<xsl:attribute name="class">success</xsl:attribute>
		<span>Room was deleted !</span>
		</xsl:if>
		<xsl:if test="//vars:dbsuccess">
		<xsl:attribute name="class">success</xsl:attribute>
		<span><xsl:value-of select="//vars:dbsuccess" /></span>
		</xsl:if>
		<xsl:if test="//vars:dbfail">
		<xsl:attribute name="class">wrong</xsl:attribute>
		<span><xsl:value-of select="//vars:dbfail" /></span>
		</xsl:if>
	</p>
	
	<!-- main content -->
	<h1>Ressources</h1>
	<ul style="float: left; width: 300px; margin:0; ">
		<li>Buildings
			<ul>
				<li><a href="{/vars:meta/@module-path}/buildinglist">Edit Buildings</a> 
				    <a href="#" style="color: #DDDDDD">  ?</a> <span class="info">add buildings to the database</span></li>
				<li><a href="{/vars:meta/@module-path}/buildingnew">New Building</a></li>
			</ul></li>
		<li>Departments
			<ul>
				<li><a href="{/vars:meta/@module-path}/roomlist">Edit Departments</a></li>
				<li><a href="{/vars:meta/@module-path}/roomnew">New Department</a></li>
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
				<li><a href="{/vars:meta/@module-path}/roomlist">Edit Rooms</a>
					<a href="#" style="color: #DDDDDD">  ?</a> <span class="info">set building and features</span></li>
				<li><a href="{/vars:meta/@module-path}/roomnew">New Room</a></li>
				<li><a href="{/vars:meta/@module-path}/#">View Timetables</a></li>
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
