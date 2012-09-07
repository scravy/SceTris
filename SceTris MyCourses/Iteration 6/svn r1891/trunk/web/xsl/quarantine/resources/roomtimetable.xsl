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
	<xsl:if test="vars:departments">
		<h1>Please choose a department</h1>
		<form>
		
		<xsl:call-template name="input-alternatives">
			<xsl:with-param name="id" select="'data'"/>
			<xsl:with-param name="label" select="'Department'"/>
			<xsl:with-param name="options">
				<xsl:for-each select="vars:departments/item:departments">
					<xsl:variable name="myid" select="@id"/>
					<option>
						<xsl:if test="1 &lt; 0">
							<xsl:attribute name="selected">selected</xsl:attribute>
						</xsl:if>	
						<xsl:attribute name="value">
							<xsl:value-of select="//vars:firstroom/*/item:firstroom[@key=$myid]/." />
						</xsl:attribute>
						<xsl:value-of select="@name" />
					</option>
				</xsl:for-each>
			</xsl:with-param>
		</xsl:call-template>
		
		<xsl:call-template name="input-submit">
			<xsl:with-param name="id" select="'select'"/>
			<xsl:with-param name="label" select="'select'"/>
		</xsl:call-template>
		</form>


	</xsl:if>
	<xsl:if test="not(vars:departments)">
		<xsl:variable name="myroom" select="vars:rooms/item:rooms[@id=//*/*[@name='data']/.]"/>	

		<h1><a href="{$module-path}/roomtimetableedit/?data={//*/*[@name='data']/.}"><xsl:value-of select="concat($myroom/vars:building/item:building/@name,', ',$myroom/@name)"/></a></h1>
		<dl>
		<dt>Adress</dt>
		<dd><xsl:value-of select="$myroom/vars:building/item:building/@address"/></dd>
		<dt>Department</dt>
		<dd><xsl:value-of select="$myroom/vars:building/item:building/vars:department/item:department/@name"/></dd>
		
		<xsl:for-each select="vars:myroomfeature/item:myroomfeature">
			<dt><xsl:value-of select="vars:feature/item:feature/@name"/></dt>
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
				<xsl:for-each select="//vars:proposedScheduling/item:proposedScheduling">
					<xsl:sort select="@timeslot"/>
					<xsl:variable name="myself" select="@elementInstance" />
					<xsl:variable name="ts" select="@timeslot - 1"/>
					<xsl:variable name="timerow">
						<xsl:value-of select="$ts mod 12 + 1"/>
					</xsl:variable>
					<xsl:variable name="cells">
						<xsl:value-of select="//vars:metaceis/item:metaceis[@id=$myself]/@duration"/>
					</xsl:variable>
				
					<div class="timeslot">
						<xsl:attribute name="style">
							<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*90)+90,'px;')"/>
							<xsl:value-of select="concat('top: ',(($timerow)*30),'px;')"/>						
							<xsl:value-of select="concat('height: ',$cells*29-4,'px;')"/>
						</xsl:attribute>
							<!-- <b><xsl:value-of select="@timeslot"/></b><br /> -->
						<a href="{$servlet-path}/Lectures/courseinstanceinfo/?data={vars:elementInstance/item:elementInstance/@courseInstance}">
							<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseElement/item:courseElement/vars:partOf/item:partOf/@name"/>
						</a>
					</div>
					<div class="info">
						<xsl:attribute name="style">
								<xsl:value-of select="concat('left: ',((($ts - $timerow + 1) div 12)*90)+175,'px;')"/>
								<xsl:value-of select="concat('top: ',(($timerow)*30)-8,'px;')"/>				
						</xsl:attribute>					
						<div class="infotext" >
						<h2><xsl:value-of select="@id"/> - <xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseElement/item:courseElement/vars:partOf/item:partOf/@name"/></h2>
						(<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:courseElement/item:courseElement/vars:type/item:type/@name"/>)
						<br />
						<span style="color: #666666;">
						<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:lecturer/item:lecturer/@lastName"/><xsl:value-of select="' '"/>
						<xsl:value-of select="vars:elementInstance/item:elementInstance/vars:lecturer/item:lecturer/@firstName"/></span>
					</div></div>
				</xsl:for-each>
			</div>
		</div>		
	</xsl:if>
</xsl:template>

<xsl:template mode="search_results" match="vars:rooms">
	<xsl:variable name="mydepartment" select="item:rooms[@id=//*/*[@name='data']/.]/vars:building/item:building/vars:department/item:department/@id"/>	
	
	<h3>Rooms of Department </h3>

	<!-- this removes duplicates, so every building is only printed once! -->
	<xsl:variable name="unique-list" select="item:rooms/vars:building/item:building[@department=$mydepartment][not(.=following::item:rooms/vars:building/item:building[@department=$mydepartment]) or item:rooms/vars:building/item:building[@department=$mydepartment]]" />   

	<ul>
	   <xsl:for-each select="$unique-list">
	   		<xsl:sort select="@id"/>
	   		<xsl:variable name="mybuilding" select="@id"/>
		 	<li><xsl:value-of select="@name"/>
		 	<ul>
			<xsl:for-each select="../../../item:rooms[@building=$mybuilding]">
		   		<xsl:sort select="@id"/>		
				<li>
					<a href="?data={@id}">
					<xsl:if test="@id=//*/*[@name='data']/.">
						<xsl:attribute name="style">
							<xsl:value-of select="'font-weight: bold;'"/>
						</xsl:attribute>
					</xsl:if>
					<xsl:value-of select="@name"/></a>
				</li>
			</xsl:for-each>		 	
			</ul>
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
