<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form">

<xsl:import href="../common/global.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:if test="//vars:no-such-user">
		<h2>No such user!</h2>
	</xsl:if>
	<xsl:for-each select="//item:person">
		<h2>
			<xsl:value-of select="@firstName" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="@additionalNames" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="@lastName" />
		</h2>
		<h3>Roles</h3>
		<xsl:if test="not(//item:roles)">
		<p>The user does not have any roles.</p>
		</xsl:if>
		<xsl:if test="//item:roles">
			<ul>
				<xsl:for-each select="//item:roles">
					<li><xsl:value-of select="@name" /></li>
				</xsl:for-each>
			</ul>
		</xsl:if>
		<h3>Privileges</h3>
		<xsl:if test="not(//item:privileges)">
		<p>The user does not have any privileges.</p>
		</xsl:if>
		<xsl:if test="//item:privileges">
		<ul>
			<xsl:for-each select="//item:privileges">
			<li><xsl:value-of select="@name" /></li>
			</xsl:for-each>
		</ul>
		</xsl:if>
	</xsl:for-each>
	
	<xsl:call-template name="timetable_lecturer"/>
</xsl:template>

<xsl:template name="timetable_lecturer">




	<div class="timetable">
		<xsl:attribute name="style">
			<xsl:value-of select="concat('height: ',13*30,'px;')"/>
		</xsl:attribute>
		<div style="position: absolute;">
		
			<!-- grid for timetable -->			
			<table>
				<tr>
				<th>Time</th>
				<xsl:for-each select="//vars:days/item:days">
					<th><xsl:value-of select="@name"/></th>
				</xsl:for-each>
				</tr>
				
				<xsl:for-each select="//vars:timeslots/item:timeslots[@day=1]">
				<tr>
					<th>
						<xsl:value-of select="@startingTime"/>
					</th>
					<td>.</td>
					<td>.</td>
					<td>.</td>
					<td>.</td>
					<td>.</td>
				</tr>
				</xsl:for-each>
			</table>
			
			
			<!-- free spaced courses -->
			<xsl:for-each select="//vars:ceis/item:ceis">
				<xsl:sort select="@startingTimeslot"/>
				<xsl:variable name="ts" select="@startingTimeslot - 1"/>
				<xsl:variable name="timerow">
					<xsl:value-of select="$ts mod 12 + 1"/>
				</xsl:variable>
				<xsl:variable name="cells">
					<xsl:value-of select="@duration"/>
				</xsl:variable>
			
				<div class="timeslot">
					<xsl:attribute name="style">
						<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*92)+92,'px;')"/>
						<xsl:value-of select="concat('top: ',(($timerow)*30),'px;')"/>						
						<xsl:value-of select="concat('height: ',$cells*29-4,'px;')"/>
					</xsl:attribute>
						<!-- <b><xsl:value-of select="@timeslot"/></b><br /> -->
					<a href="{$servlet-path}/courses/showCourseInstance/{@courseInstance}">
						<xsl:value-of select="vars:courseElement/item:courseElement/@name"/>
					</a>
				</div>
				
				<div class="info">
					<xsl:attribute name="style">
							<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*91)+175,'px;')"/>
							<xsl:value-of select="concat('top: ',(($timerow)*30)-8,'px;')"/>				
					</xsl:attribute>					
					<div class="infotext" >
					<h2><xsl:value-of select="@id"/> - <xsl:value-of select="vars:courseElement/item:courseElement/vars:type/item:type/@name"/></h2>
					<span style="color: #666666;">
					<xsl:value-of select="//vars:tpir/item:tpir[@elementInstance = current()/@id]/vars:room/item:room/vars:building/item:building/@name"/>
					<br />(<xsl:value-of select="//vars:tpir/item:tpir[@elementInstance = current()/@id]/vars:room/item:room/vars:building/item:building/@address"/>)
					<br />Room: 
					<xsl:value-of select="//vars:tpir/item:tpir[@elementInstance = current()/@id]/vars:room/item:room/@number"/></span>
				</div></div>
			</xsl:for-each>
		</div>
	</div>	





</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<!--
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/users"><xsl:value-of select="$lang/admin-users" /></a>
	</li>
	<li>
		<a href="{$module-path}/listUsers"><xsl:value-of select="$mod-lang/listUsers" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/editUser" />
	</li>
	-->
</xsl:template>

</xsl:transform>
