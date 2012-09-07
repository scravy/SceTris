<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:variable name="myroom" select="vars:room/item:room/."/>	
	<h1>
		<!--<a href="{$module-path}/editroom/{$myroom/@id}">-->
			<xsl:value-of select="concat($myroom/vars:building/item:building/@name,', ',$myroom/@name)"/>
		<!--</a>-->
	</h1>
	<dl>
	<dt>Adress</dt>
	<dd><xsl:value-of select="$myroom/vars:building/item:building/@address"/></dd>
	<dt>Department</dt>
	<dd><xsl:value-of select="$myroom/vars:building/item:building/vars:department/item:department/@name"/></dd>
	
	<xsl:for-each select="vars:myroomfeature/item:myroomfeature">
		<dt><xsl:value-of select="//vars:feature/item:feature[@id = current()/@feature]/@name"/></dt>
		<dd><xsl:value-of select="@quantity"/></dd>
	</xsl:for-each>
	
	</dl>
	
	
	
	
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
			<xsl:for-each select="//vars:proposedScheduling/item:proposedScheduling">
				<xsl:sort select="@timeslot"/>
				<xsl:variable name="myself" select="@elementInstance" />
				<xsl:variable name="ts" select="@timeslot - 1"/>
				<xsl:variable name="timerow">
					<xsl:value-of select="$ts mod 12 + 1"/>
				</xsl:variable>
				<xsl:variable name="cells">
					<xsl:value-of select="vars:elementInstance/item:elementInstance/@duration"/>
				</xsl:variable>
			
				<div class="timeslot">
					<xsl:attribute name="style">
						<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*92)+92,'px;')"/>
						<xsl:value-of select="concat('top: ',(($timerow)*30),'px;')"/>						
						<xsl:value-of select="concat('height: ',$cells*29-4,'px;')"/>
					</xsl:attribute>
						<!-- <b><xsl:value-of select="@timeslot"/></b><br /> -->
					<a href="{$servlet-path}/courses/showCourseInstance/{vars:elementInstance/item:elementInstance/@courseInstance}">
						<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseInstance/item:courseInstance/vars:instanceOf/item:instanceOf/@name"/>
					</a>
				</div>
				<div class="info">
					<xsl:attribute name="style">
							<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*91)+175,'px;')"/>
							<xsl:value-of select="concat('top: ',(($timerow)*30)-8,'px;')"/>				
					</xsl:attribute>					
					<div class="infotext" >
					<h2><xsl:value-of select="@id"/> - <xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseInstance/item:courseInstance/vars:instanceOf/item:instanceOf/@name"/></h2>
					(<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseElement/item:courseElement/vars:type/item:type/@name"/>)
					<br />
					<span style="color: #666666;">
					<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:lecturer/item:lecturer/@lastName"/><xsl:value-of select="' '"/>
					<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:lecturer/item:lecturer/@firstName"/></span>
				</div></div>
			</xsl:for-each>
		</div>
	</div>	
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/facilities"><xsl:value-of select="$lang/admin-buildingsandrooms" /></a>
	</li>
	<li>
		<a href="{$module-path}/listRooms"><xsl:value-of select="$mod-lang/listRooms" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/showRoom" />
	</li>
</xsl:template>

</xsl:transform>
