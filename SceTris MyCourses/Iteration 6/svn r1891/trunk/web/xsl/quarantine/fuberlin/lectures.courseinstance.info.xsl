<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:vars="http://technodrom.scravy.de/2010/data" 
	xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />
<xsl:import href="../fuberlin/lego.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<xsl:variable name="cei" select="vars:courseinstanceinfo/item:courseinstanceinfo"/>
	<xsl:variable name="iof" select="$cei/vars:instanceOf/item:instanceOf"/>
	<xsl:variable name="ml"  select="$cei/vars:mainLecturer/item:mainLecturer"/>
	<xsl:variable name="pro" select="$cei/vars:program/item:program"/>
	
	<h1>
		<xsl:value-of select="$iof/@name"/>
		-
		<xsl:value-of select="$pro/vars:academicTerm/item:academicTerm/@name"/>
	</h1>	

	<!-- course instance info -->
	<dl>
		<dt>Timespan</dt>
		<dd><xsl:value-of select="$cei/@start"/> - <xsl:value-of select="$cei/@end"/></dd>
		
		<dt>Mainlecturer</dt>
		<dd><xsl:value-of select="concat($ml/@firstName,' ',$ml/@lastName)"/></dd>
	
		<dt>More infos to come</dt>
		<dd>probably</dd>
	</dl>
	<br />	
	<h3>Course dates</h3>
	<table>
	<tr>
		<th>Date</th>
		<th>Location</th>
		<th>Type</th>
		<th>Duration<br />(in Timeslots)</th>
		<th>Required</th>
	</tr>
	<xsl:for-each select="vars:cei/item:cei">
		<xsl:sort select="not(vars:courseElement/item:courseElement/@required)"/>
		<xsl:sort select="@id"/>
		
		<xsl:variable name="foobar" select="@id"/>
		<tr>
			<td>
				<xsl:value-of select="vars:startingTimeslot/item:startingTimeslot/vars:day/item:day/@name"/>,
				<xsl:value-of select="vars:startingTimeslot/item:startingTimeslot/@startingTime"/>				
			</td>
			<td><a href="{$servlet-path}/Resources/roomtimetable/?data={//vars:roomceis/item:roomceis[@elementInstance=$foobar]/@room}">
				<xsl:value-of select="//vars:roomceis/item:roomceis[@elementInstance=$foobar]/vars:room/item:room/vars:building/item:building/@name"/>,
				<xsl:value-of select="//vars:roomceis/item:roomceis[@elementInstance=$foobar]/vars:room/item:room/@name"/>
				</a>
			</td>
			<td><xsl:value-of select="vars:courseElement/item:courseElement/vars:type/item:type/@name"/></td>
			<td><xsl:value-of select="@duration"/></td>
			<td>
				<xsl:if test="not(vars:courseElement/item:courseElement/@required='false')">
					<xsl:value-of select="'is required'"/>
				</xsl:if>
			</td>
		</tr>
	</xsl:for-each>
	</table>

	<form method="post">
	<input type="hidden" name="data" value="{vars:data/item:data/@id}"/>	


	<xsl:call-template name="input-submit">
		<xsl:with-param name="id" select="vars:crud"/>
		<xsl:with-param name="label" select="vars:crud"/>
	</xsl:call-template>

	</form>


</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
