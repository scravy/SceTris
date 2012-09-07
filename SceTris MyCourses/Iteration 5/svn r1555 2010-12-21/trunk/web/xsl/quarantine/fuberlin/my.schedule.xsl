<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Mein Stundenplan</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Mein Stundenplan</h1>
	
	
	
	
	<div class="timetable" name="#timetable">
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
			<xsl:for-each select="//vars:mycourseinstances/item:mycourseinstances">
				<!-- <xsl:sort select="@timeslot"/> -->
				<xsl:variable name="myself" select="@elementInstance" />
				<xsl:variable name="ts" select="vars:timeslot/item:timeslot/@id - 1"/>
				<xsl:variable name="timerow">
					<xsl:value-of select="$ts mod 12 + 1"/>
				</xsl:variable>
				<xsl:variable name="cells">
					<xsl:value-of select="vars:elementInstance/item:elementInstance/@duration"/>
				</xsl:variable>
			
				<div class="timeslot">
					<xsl:attribute name="style">
						<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*90)+90,'px;')"/>
						<xsl:value-of select="concat('top: ',(($timerow)*30),'px;')"/>						
						<xsl:value-of select="concat('height: ',$cells*29-4,'px;')"/>
					</xsl:attribute>
						<!-- <b><xsl:value-of select="@timeslot"/></b><br /> -->
						<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseElement/item:courseElement/vars:partOf/item:partOf/@name"/>
				</div>
				
				
				
				<div class="info">
					<xsl:attribute name="style">
							<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*90)+175,'px;')"/>
							<xsl:value-of select="concat('top: ',(($timerow)*30)-8,'px;')"/>					
					</xsl:attribute>					
					<div class="infotext" >
					<h2><xsl:value-of select="@elementInstance"/> - <xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseElement/item:courseElement/vars:partOf/item:partOf/@name"/></h2>
					<dl>
					<dt>Type</dt>					
					<dd><xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseElement/item:courseElement/vars:type/item:type/@name"/></dd>
					<dt>Lecturer</dt>
					<dd><xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseInstance/item:courseInstance/vars:mainLecturer/item:mainLecturer/@lastName"/><xsl:value-of select="' '"/>
					<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseInstance/item:courseInstance/vars:mainLecturer/item:mainLecturer/@firstName"/>
					</dd>
					<dt>Building</dt>
					<dd><xsl:value-of select="//vars:roomsofcei/item:roomsofcei[@elementInstance=$myself]/vars:room/item:room/vars:building/item:building/@name"/></dd>
					<dt>Room</dt>
					<dd><xsl:value-of select="//vars:roomsofcei/item:roomsofcei[@elementInstance=$myself]/vars:room/item:room/@name"/></dd>
					</dl>
				</div></div>
			</xsl:for-each>
		</div>
	</div>	
	
	
	
	
	
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<xsl:text> &#x2192; Mein Stundenplan</xsl:text>
</xsl:template>

</xsl:transform>
