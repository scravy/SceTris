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
	<xsl:variable name="ci" select="vars:ci/item:ci"/>
	<xsl:variable name="iof" select="$ci/vars:instanceOf/item:instanceOf"/>
	<xsl:variable name="ml"  select="$ci/vars:mainLecturer/item:mainLecturer"/>
	<xsl:variable name="pro" select="$ci/vars:program/item:program"/>
	
	<h1>
		<xsl:value-of select="$iof/@name"/>
		-
		<xsl:value-of select="$pro/vars:academicTerm/item:academicTerm/@name"/>
	</h1>	

	<!-- course instance info -->
	<dl>
		<dt>Timespan</dt>
		<dd><xsl:value-of select="$ci/@start"/> - <xsl:value-of select="$ci/@end"/></dd>
		
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
	<xsl:for-each select="vars:ci_parts/item:ci_parts">
		<xsl:sort select="not(vars:courseElement/item:courseElement/@required)"/>
		<xsl:sort select="@id"/>
		
		<xsl:variable name="foobar" select="@id"/>
		<tr>
			<td>
				<xsl:value-of select="vars:startingTimeslot/item:startingTimeslot/vars:day/item:day/@name"/>,
				<xsl:value-of select="vars:startingTimeslot/item:startingTimeslot/@startingTime"/>				
			</td>
			<td><a href="{$servlet-path}/admin/showRoom/{//vars:roomceis/item:roomceis[@elementInstance=$foobar]/@room}">
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
