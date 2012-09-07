<?xml version="1.0" encoding="UTF-8"?>
<!--
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
-->
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	>

	<xsl:template name="timetablegrid">
		<xsl:param name="days"/>
		<xsl:param name="timeslots"/>
		<xsl:param name="isClickable">
			<xsl:value-of select="0"/>
		</xsl:param>
		<xsl:param name="edit_path"/>
		<xsl:param name="edit_room">
			<xsl:value-of select="0"/>
		</xsl:param>

		<!-- grid for timetable -->			
		<table>
			<tr>
			<th><a name="timetable">Time</a></th>
			<xsl:for-each select="$days">
				<th><xsl:value-of select="@name"/></th>
			</xsl:for-each>
			</tr>
			
			<xsl:for-each select="$timeslots">
			<tr>
				<th>
					<xsl:value-of select="@startingTime"/>
				</th>
				<xsl:call-template name="timeslot">
					<xsl:with-param name="ts_left" select="0"/>
					<xsl:with-param name="col" select="((@id - 1) mod 12) + 1 "/>
					<xsl:with-param name="isClickable" select="$isClickable"/>
					<xsl:with-param name="edit_path" select="$edit_path"/>
					<xsl:with-param name="edit_room" select="$edit_room"/>
				</xsl:call-template> 
			</tr>
			</xsl:for-each>
		</table>
	</xsl:template>	
	
	<xsl:template name="timeslot">
		<xsl:param name="ts_left"/>
		<xsl:param name="col"/>
		<xsl:param name="isClickable"/>
		<xsl:param name="edit_path"/>
		<xsl:param name="edit_room"/>		
		<td>
		<xsl:if test="$isClickable = 1">
			<form method="post">
				<input type="hidden" name="~formType" value="{$edit_path}"/>
				<input type="hidden" name="~formName" value="xkcd"/>
				<input type="hidden" name="xkcd.newts" value="{$ts_left*12 + $col}"/>
				<xsl:if test="$edit_room &gt; 0">
					<input type="hidden" name="xkcd.room_id" value="{$edit_room}"/>
				</xsl:if>
				<input type="submit" value="{$ts_left*12 + $col}" name="xkcd.commit" style="float: right; background-color: #CCCCCC; border: none; padding: 1px;"/>
			</form>
		</xsl:if>
		
		<xsl:if test="not($isClickable = 1)">
			.
		</xsl:if>
		</td>
		
		<xsl:if test="$ts_left &lt; 4">
			<xsl:call-template name="timeslot">
				<xsl:with-param name="ts_left" select="$ts_left + 1"/>
				<xsl:with-param name="isClickable" select="$isClickable"/>
				<xsl:with-param name="col" select="$col"/>
				<xsl:with-param name="edit_path" select="$edit_path"/>
				<xsl:with-param name="edit_room" select="$edit_room"/>
			</xsl:call-template>
		</xsl:if>
	</xsl:template>
	
	
	<!-- places a clickable block over the grid (used to swap to THIS block) -->
	<xsl:template name="freetimeslots">
		<xsl:param name="slots"/>
		<xsl:param name="edit_path"/>
		<xsl:param name="edit_room">
			<xsl:value-of select="0"/>
		</xsl:param>
		
		<xsl:param name="space_needed">
			<xsl:value-of select="1"/>
		</xsl:param>

		<xsl:for-each select="$slots">
			<xsl:call-template name="timeslot_x">
				<xsl:with-param name="x" select=" @day "/>
				<xsl:with-param name="y" select="((@id - 1) mod 12) + 1 "/>
				<xsl:with-param name="submitpath" select="$edit_path"/>
				<xsl:with-param name="currentroom" select="$edit_room"/>
				<xsl:with-param name="duration" select="$space_needed"/>
			</xsl:call-template> 
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template name="timeslot_x">
		<xsl:param name="x"/>
		<xsl:param name="y"/>
		<xsl:param name="submitpath"/>
		<xsl:param name="currentroom"/>
		<xsl:param name="duration"/>

		<xsl:variable name="ts" select="($x - 1) * 12 + $y"/>
		<xsl:if test="$duration &lt; 14 - $y">	
			<div class="timeslot swap">
				<xsl:attribute name="style">
					<xsl:value-of select="concat('left: ',($x * 90),'px;')"/>			
					<xsl:value-of select="concat('top: ', ($y * 30)-2,'px;')"/>	
					<xsl:value-of select="concat('height: ', ($duration * 30)-4, 'px;')"/>
				</xsl:attribute>
				<form method="post">
					<input type="hidden" name="~formType" value="{$submitpath}"/>
					<input type="hidden" name="~formName" value="xkcd"/>
					<input type="hidden" name="xkcd.newts" value="{$ts}"/>
					<xsl:if test="$currentroom &gt; 0">
						<input type="hidden" name="xkcd.room_id" value="{$currentroom}"/>
					</xsl:if>
					<input type="submit" value="" name="xkcd.commit" class="swapbutton"/>
				</form>
			</div>	
		</xsl:if>
	</xsl:template>
	
	
	<!-- places a block for a courseelementinstance -->
	<xsl:template name="pscell">
		<xsl:param name="ps_id"/>
		<xsl:param name="editable"/>
		<xsl:param name="edit_path"/>
					
		<xsl:param name="ts"/>
		<xsl:param name="timerow"/>
		<xsl:param name="cells"/>
		<xsl:param name="conflict"/>
		<xsl:param name="cellpath"/>
		<xsl:param name="celltext"/>
		<xsl:param name="hinthead">
			<xsl:value-of select="''"/>
		</xsl:param>
		<xsl:param name="hinttext">
			<xsl:value-of select="''"/>
		</xsl:param>
		
		
		<xsl:if test="$ts &gt; -1 and $timerow &gt; 0">
			<div class="timeslot">
				<xsl:attribute name="style">
					<xsl:if test="$conflict">
							<xsl:value-of select="'background-color: #FFBBBB;'"/>
							<xsl:value-of select="'width: 40px;'"/>
							<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*90)+132,'px;')"/>		
					</xsl:if>
					<xsl:if test="not($conflict)">
							<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*90)+92,'px;')"/>			
					</xsl:if>
					<xsl:value-of select="concat('top: ',(($timerow)*30),'px;')"/>						
					<xsl:value-of select="concat('height: ',$cells*29-4,'px;')"/>						
				</xsl:attribute>
				
				<xsl:if test="$editable = 1">
					<form method="post" style="height: 8px;">
						<input type="hidden" name="~formType" value="{$edit_path}"/>
						<input type="hidden" name="~formName" value="xkcd"/>
						<input type="hidden" name="xkcd.ps_id" value="{$ps_id}"/>
						<input type="hidden" name="xkcd.ps_dura" value="{$cells}"/>
						<input type="submit" value="" name="xkcd.commit" style="width: 100%; height: 8px; background-color: #D1D1D1; border: none; padding: 0;"/>
					</form>
				</xsl:if>
				<a href="{$cellpath}"><xsl:value-of select="$celltext"/></a>
			</div>	
			
			<xsl:if test="$hinthead!='' and $hinttext!=''">
				<div class="info">
					<xsl:attribute name="style">
						<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*90)+175,'px;')"/>
						<xsl:value-of select="concat('top: ',(($timerow)*30)-8,'px;')"/>				
					</xsl:attribute>					
					<div class="infotext">
						<h2><xsl:value-of select="$hinthead"/></h2>
						<span style="color: #666666;">
						<xsl:call-template name="insertBreaks">
							<xsl:with-param name="pText" select="$hinttext"/>
						</xsl:call-template>
						</span>
					</div>
				</div>
			</xsl:if>
		</xsl:if>
	</xsl:template>
	
	
	<!-- Given a text the template replaces all \n with <br /> -->
	<xsl:template match="text()" name="insertBreaks">
		<xsl:param name="pText" select="."/>
		
		<xsl:choose>
			<xsl:when test="not(contains($pText, '&#xA;'))">
				<xsl:copy-of select="$pText"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="substring-before($pText, '&#xA;')"/>
				<br />
				<xsl:call-template name="insertBreaks">
					<xsl:with-param name="pText" select="substring-after($pText, '&#xA;')"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

</xsl:transform>
