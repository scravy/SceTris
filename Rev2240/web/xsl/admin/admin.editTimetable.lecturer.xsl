<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form">

<xsl:import href="../common/global.xsl" />
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
	
	
	<xsl:call-template name="timetable_lecturer">
		<xsl:with-param name="editable" select="//vars:enableEdit/text()"/>
	</xsl:call-template>
</xsl:template>





<xsl:template name="timetable_lecturer">
	<xsl:param name="editable">
		<xsl:value-of select="0"/>
	</xsl:param>


	<div class="timetable">
		<xsl:attribute name="style">
			<xsl:value-of select="concat('height: ',13*30,'px;')"/>
		</xsl:attribute>
		<div style="position: absolute;">
			<xsl:call-template name="timetablegrid">
				<xsl:with-param name="isClickable" select="0"/>
				<xsl:with-param name="days" select="//vars:days/item:days"/>
				<xsl:with-param name="timeslots" select="//vars:timeslots/item:timeslots[@day=1]"/>
				<xsl:with-param name="edit_path" select="'de.fu.scetris.web.forms.EditTimetableByLecturer'"/>
			</xsl:call-template>
			
			
			
			<xsl:for-each select="//vars:proposedScheduling/item:proposedScheduling">
				<xsl:sort select="@timeslot"/>
				<xsl:variable name="ts" select="@timeslot - 1"/>
				<xsl:variable name="cells" select="vars:elementInstance/item:elementInstance/@duration"/>
			
				<xsl:call-template name="pscell">
					<xsl:with-param name="ps_id" select="@id"/>
					<xsl:with-param name="ts" select="$ts"/>
					<xsl:with-param name="timerow" select="$ts mod 12 + 1"/>
					<xsl:with-param name="cells" select="$cells"/>
					<xsl:with-param name="conflict" select="not(generate-id(//vars:proposedScheduling/item:proposedScheduling[@timeslot = $ts+1 or @timeslot = $ts+$cells][1]) = generate-id(.))"/>
					<xsl:with-param name="cellpath" select="concat($servlet-path, '/admin/showCourseInstance/', vars:elementInstance/item:elementInstance/@courseInstance)"/>
					<xsl:with-param name="celltext" select="vars:elementInstance/item:elementInstance/vars:courseInstance/item:courseInstance/vars:instanceOf/item:instanceOf/@name"/>
				</xsl:call-template>
			</xsl:for-each>
			
			<xsl:if test="$editable = 1">
				<xsl:call-template name="freetimeslots">
					<xsl:with-param name="slots" select="//vars:timeslots/item:timeslots"/>
					<xsl:with-param name="edit_path" select="'de.fu.scetris.web.forms.EditTimetableByLecturer'"/>
					<xsl:with-param name="space_needed" select="//vars:swapMe/item:swapMe/vars:elementInstance/item:elementInstance/@duration"/>
				</xsl:call-template>
			</xsl:if>
				
		</div>
	</div>	
	<br style="clear: both;"/>

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
