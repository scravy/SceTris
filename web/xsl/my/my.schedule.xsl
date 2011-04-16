<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/timetable-builder.xsl" />

<xsl:template mode="title" match="vars:meta">
	
</xsl:template>

<xsl:template mode="content" match="vars:meta">

	<div class="timetable">
		<xsl:attribute name="style">
			<xsl:value-of select="concat('height: ',13*30,'px;')"/>
		</xsl:attribute>
		<div style="position: absolute;">
			<xsl:call-template name="timetablegrid">
				<xsl:with-param name="days" select="//vars:days/item:days"/>
				<xsl:with-param name="timeslots" select="//vars:timeslots/item:timeslots[@day=1]"/>
			</xsl:call-template>
			
			
			
			<xsl:for-each select="//vars:ptpiei/item:ptpiei">
				<xsl:sort select="number(@timeslot)" data-type="number"/>
				<xsl:variable name="ts" select="@timeslot - 1"/>
				<xsl:variable name="cells" select="vars:elementInstance/item:elementInstance/@duration"/>
			
				<xsl:call-template name="pscell">
					<xsl:with-param name="ps_id" select="@id"/>
					
					<xsl:with-param name="ts" select="$ts"/>
					<xsl:with-param name="timerow" select="$ts mod 12 + 1"/>
					<xsl:with-param name="cells" select="$cells"/>
					
					<xsl:with-param name="cellpath" select="concat($servlet-path, '/lectures/showCourseInstance/', vars:elementInstance/item:elementInstance/@courseInstance)"/>
					<xsl:with-param name="celltext" select="vars:elementInstance/item:elementInstance/vars:courseElement/item:courseElement/vars:partOf/item:partOf/@name"/>
					<xsl:with-param name="hinthead" select="concat(@elementInstance, ' - ', vars:elementInstance/item:elementInstance/vars:courseElement/item:courseElement/vars:type/item:type/@name)"/>
					<xsl:with-param name="hinttext">
						<xsl:variable name="short" select="//vars:eitpir/item:eitpir[@elementInstance = current()/@elementInstance]" />
						<xsl:value-of select="$short/vars:room/item:room/vars:building/item:building/@name"/>
						<xsl:text> 	</xsl:text>
						(<xsl:value-of select="$short/vars:room/item:room/vars:building/item:building/@address"/>)
						<xsl:text> 	Room: </xsl:text>
						<xsl:value-of select="$short/vars:room/item:room/@number"/>
					</xsl:with-param> 
				</xsl:call-template>
			</xsl:for-each>
			
		
				
		</div>
	</div>	
	<br style="clear: both;"/>

</xsl:template>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
