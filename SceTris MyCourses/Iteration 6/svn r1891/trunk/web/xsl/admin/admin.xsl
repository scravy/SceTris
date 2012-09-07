<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text> SceTris </xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	
	<div style="float: right; width: 200px; padding-top: 10px;">
	<dl style="color: #666666;">
		<dt>Last Login</dt>
		<dd>2011-01-13</dd>
		<dt>Username</dt>
		<dd>andrez</dd>

		<dt>E-Mail</dt>
		<dd>andre.zoufahl@fu-berlin.de</dd>				
	
	</dl>
	</div>	

	
	<div style="float: left; padding-right: 30px; width: 250px;">
	<h3>Welcome Andre Zoufahl</h3>
		
	<h3>Your Courses (<span style="font-weight: 100;">Winter 2010/2011</span>)</h3>
	<ul>
		<li><a href="">Advanced Micro Theory</a></li>
		<li><a href="">Mathematics for Economists</a></li>
	</ul>
	
	<h3>Quicklinks</h3>
	<ul>
		<li><a href="">Timetable</a></li>
		<li><a href="">Grades</a></li>
		<li><a href="">Personal data</a></li>
		<li><a href="">Upcoming events</a></li>
		<li><a href="">Coursearchive</a></li>
	</ul>
	</div>


</xsl:template>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

<xsl:template mode="style" match="vars:meta">
	dt {
		padding-right: 20px;
	}
	dd {
		font-weight: 600;
		padding-left: 10px;
	}
	
</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
