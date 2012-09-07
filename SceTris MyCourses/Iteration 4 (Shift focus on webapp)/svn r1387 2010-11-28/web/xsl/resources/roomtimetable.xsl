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
	<h1>timetable</h1>	
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
			
			<!--
			<xsl:for-each select="//vars:days/item:days">
				<div class="timeslot_head">
					<xsl:attribute name="style">
						<xsl:value-of select="concat('left: ',@id*90,'px;')"/>
					</xsl:attribute>
					<xsl:value-of select="@name"/>
				</div>
			</xsl:for-each>
			<xsl:for-each select="//vars:timeslots/item:timeslots[@day=1]">
				<div class="timeslot_head">
					<xsl:attribute name="style">
						<xsl:value-of select="concat('top: ',@id*30,'px;')"/>
					</xsl:attribute>
					<xsl:value-of select="@startingTime"/>
				</div>
			</xsl:for-each> -->
			
			<!-- free spaced courses -->
			<xsl:for-each select="//vars:eitpir/item:eitpir">
				<xsl:variable name="myself" select="@elementInstance" />
				<xsl:variable name="fmod">
					<xsl:value-of select="@timeslot mod 12"/>
				</xsl:variable>
				<xsl:variable name="cells">
					<xsl:value-of select="//vars:metaceis/item:metaceis[@id=$myself]/@duration"/>
				</xsl:variable>
			
				<div class="timeslot">
					<xsl:attribute name="style">
						<xsl:value-of select="concat('left: ',(((@timeslot - $fmod) div 12)*90)+90,'px;')"/>
						<xsl:value-of select="concat('top: ',(($fmod)*30),'px;')"/>						
						<xsl:value-of select="concat('height: ',$cells*30-4,'px;')"/>
					</xsl:attribute>
					<b><xsl:value-of select="@timeslot"/></b><br />
					<xsl:value-of select="."/>
				</div>
			</xsl:for-each>
			
			
		</div>
	</div>
	
</xsl:template>

<xsl:template mode="search_results" match="vars:meta">
	<ul>
		<xsl:for-each select="//vars:rooms/item:rooms">
			<li>
				<a href="?data={@id}"><xsl:value-of select="@name"/></a>
			</li>
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
