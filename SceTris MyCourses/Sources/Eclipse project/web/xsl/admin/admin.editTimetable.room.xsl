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
				<xsl:with-param name="isClickable" select="0"/>
				
				<xsl:with-param name="edit_path" select="'de.fu.scetris.web.forms.EditTimetableByRoom'"/>
				<xsl:with-param name="edit_room" select="$myroom/@id"/>
			</xsl:call-template>
			
			
			<!-- free spaced courses -->
			<xsl:for-each select="//vars:proposedScheduling/item:proposedScheduling">
				<xsl:sort select="@timeslot"/>
				<xsl:variable name="ts" select="@timeslot - 1"/>
				<xsl:variable name="cells" select="vars:elementInstance/item:elementInstance/@duration"/>
			
				<xsl:call-template name="pscell">
									
					<xsl:with-param name="ts" select="$ts"/>
					<xsl:with-param name="timerow" select="$ts mod 12 + 1"/>
					<xsl:with-param name="cells" select="$cells"/>
					
					<xsl:with-param name="conflict" select="not(generate-id(//vars:proposedScheduling/item:proposedScheduling[@timeslot = $ts+1 or @timeslot = $ts+$cells][1]) = generate-id())"/>

					<xsl:with-param name="cellpath" select="concat($servlet-path, '/admin/showCourseInstance/', vars:elementInstance/item:elementInstance/@courseInstance)"/>
					<xsl:with-param name="celltext" select="vars:elementInstance/item:elementInstance/vars:courseInstance/item:courseInstance/vars:instanceOf/item:instanceOf/@name"/>
				</xsl:call-template>
			</xsl:for-each>
			
			
			<xsl:if test="$editable = 1">
				<xsl:call-template name="freetimeslots">
					<xsl:with-param name="slots" select="//vars:timeslots/item:timeslots"/>
					<xsl:with-param name="edit_path" select="'de.fu.scetris.web.forms.EditTimetableByRoom'"/>
					<xsl:with-param name="edit_room" select="$myroom/@id"/>
					<xsl:with-param name="space_needed" select="//vars:swapMe/item:swapMe/vars:elementInstance/item:elementInstance/@duration"/>
				</xsl:call-template>
			</xsl:if>
		</div>
	</div>
	<br style="clear: both;"/>
	

	<xsl:if test="$editable = 1">
		<form method="post">
			<input type="hidden" name="~formType" value="de.fu.scetris.web.forms.EditTimetableByRoom"/>
			<input type="hidden" name="~formName" value="xkcd"/>
			<input type="hidden" name="xkcd.newts" value="1"/>
			<input type="submit" value="goto" name="xkcd.commit" style="float: right; display: inline;"/>
			<xsl:call-template name="input-alternatives">
				<xsl:with-param name="id" select="'xkcd.room_id'" />
				<xsl:with-param name="label" select="'Other room'" />
				<xsl:with-param name="options">
					<xsl:for-each select="//vars:availableRooms/item:availableRooms">
						<option value="{@id}">
							<xsl:value-of select="concat(@id,' :: ',@name,' (',@number,')')" />
						</option>
					</xsl:for-each>
				</xsl:with-param>
			</xsl:call-template>
			
		</form>
	</xsl:if>	
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
