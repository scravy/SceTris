<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />
<xsl:import href="../lego/timetable-builder.xsl" />

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
	
	
	<xsl:variable name="editable" select="//vars:editable/text()"/>
	
	
	
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
			<xsl:for-each select="//vars:proposedScheduling/item:proposedScheduling">
				<xsl:sort select="@timeslot"/>
				<xsl:variable name="ts" select="@timeslot - 1"/>
				<xsl:variable name="cells" select="vars:elementInstance/item:elementInstance/@duration"/>
			
				<xsl:call-template name="pscell">
					<xsl:with-param name="ps_id" select="@id"/>
									
					<xsl:with-param name="ts" select="$ts"/>
					<xsl:with-param name="timerow" select="$ts mod 12 + 1"/>
					<xsl:with-param name="cells" select="$cells"/>
					
					<xsl:with-param name="conflict" select="not(generate-id(//vars:proposedScheduling/item:proposedScheduling[@timeslot = $ts+1 or @timeslot = $ts+$cells][1]) = generate-id())"/>

					<xsl:with-param name="cellpath" select="concat($servlet-path, '/lectures/showCourseInstance/', vars:elementInstance/item:elementInstance/@courseInstance)"/>
					<xsl:with-param name="celltext" select="vars:elementInstance/item:elementInstance/vars:courseInstance/item:courseInstance/vars:instanceOf/item:instanceOf/@name"/>
					<xsl:with-param name="hinthead" select="concat(@elementInstance, ' - ', vars:elementInstance/item:elementInstance/vars:courseInstance/item:courseInstance/vars:instanceOf/item:instanceOf/@name)"/>
					<xsl:with-param name="hinttext">
						<xsl:value-of select="//vars:tpir/item:tpir[@elementInstance = current()/@elementInstance]/vars:room/item:room/vars:building/item:building/@name"/>
						<xsl:text> </xsl:text> (<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseElement/item:courseElement/vars:type/item:type/@name"/>
						<xsl:text>) </xsl:text> 
						Lecturer: <xsl:value-of select="concat(vars:elementInstance/item:elementInstance/vars:lecturer/item:lecturer/@lastName, ', ', vars:elementInstance/item:elementInstance/vars:lecturer/item:lecturer/@firstName)"/>
					</xsl:with-param>
				</xsl:call-template>
			</xsl:for-each>
		</div>
	</div>	
	<br style="clear: both;"/>


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
