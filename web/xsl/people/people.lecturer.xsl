<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form">

<xsl:import href="../common/global.xsl" />
<xsl:import href="common.xsl" />
<xsl:import href="../lego/timetable-builder.xsl" />

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
		<h3>Personal Info</h3>
	</xsl:for-each>
	
	<h3>Timetable</h3>
	<xsl:call-template name="timetable_lecturer"/>
</xsl:template>

<xsl:template name="timetable_lecturer">




	<div class="timetable">
		<xsl:attribute name="style">
			<xsl:value-of select="concat('height: ',13*30,'px;')"/>
		</xsl:attribute>
		<div style="position: absolute;">
		
			<!-- grid for timetable -->
			<xsl:call-template name="timetablegrid">
				<xsl:with-param name="days" select="//vars:days/item:days"/>
				<xsl:with-param name="timeslots" select="//vars:timeslots/item:timeslots[@day=1]"/>
			</xsl:call-template>
			
			
			<!-- free spaced courses -->
			<xsl:for-each select="//vars:ceis/item:ceis">
				<xsl:sort select="@startingTimeslot"/>			
				
				
				<xsl:variable name="ts" select="@startingTimeslot - 1"/>
				<xsl:variable name="cells" select="@duration"/>
				<xsl:call-template name="pscell">
					<xsl:with-param name="ps_id" select="@id"/>
					<xsl:with-param name="editable" select="0"/>
					
					<xsl:with-param name="ts" select="$ts"/>
					<xsl:with-param name="timerow" select="$ts mod 12 + 1"/>
					<xsl:with-param name="cells" select="$cells"/>
					

					<xsl:with-param name="cellpath" select="concat($servlet-path, '/lectures/showCourseInstance/', @courseInstance)"/>
					<xsl:with-param name="celltext" select="vars:courseElement/item:courseElement/@name"/>
					
					<xsl:with-param name="hinthead" select="concat(@id, ' - ', vars:courseElement/item:courseElement/vars:type/item:type/@name)"/>
					<xsl:with-param name="hinttext">
						<xsl:value-of select="//vars:tpir/item:tpir[@elementInstance = current()/@id]/vars:room/item:room/vars:building/item:building/@name"/>
						<xsl:text> 	</xsl:text>
						(<xsl:value-of select="//vars:tpir/item:tpir[@elementInstance = current()/@id]/vars:room/item:room/vars:building/item:building/@address"/>)
						<xsl:text> 	Room: </xsl:text>
						<xsl:value-of select="//vars:tpir/item:tpir[@elementInstance = current()/@id]/vars:room/item:room/@number"/>
					</xsl:with-param> 
				</xsl:call-template>	
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
