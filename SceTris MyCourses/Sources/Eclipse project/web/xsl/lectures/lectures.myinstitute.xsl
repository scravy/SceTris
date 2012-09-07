<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />

<xsl:key name="courseinstances-by-department" match="//vars:courseinstances/item:courseinstances" use="vars:program/item:program/@department" />


<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<xsl:for-each select="vars:courseinstances/item:courseinstances[count(. | key('courseinstances-by-department', vars:program/item:program/@department)[1]) = 1]">
		<xsl:sort select="@id" />
		<xsl:if test="vars:program/item:program/@department = //vars:mydept/item:mydept/@id">		
			<table>
				<tr>
					<th colspan="4" style="background-color: #EDEDED;">
						<xsl:value-of select="//vars:dept/item:dept[@id = current()/vars:program/item:program/@department]/@name"/>
					</th>
				</tr>
				<tr>
					<th>CourseInstance</th>
					<th>Mainlecturer</th>
					<th>Start</th>
					<th>End</th>
				</tr>		
			
			<xsl:for-each select="key('courseinstances-by-department', vars:program/item:program/@department)">
				<xsl:sort select="@id" />
				<xsl:variable name="id" select="generate-id(.)" />
				<tr>
					<td>
						<a href="{$servlet-path}/lectures/showCourseInstance/{@id}">
							<xsl:value-of select="//vars:courses/item:courses[@id = current()/@instanceOf]/@name"/>
						</a>
					</td>
					<td>
						<xsl:variable name="ml" select="vars:mainLecturer/item:mainLecturer"/>
						<xsl:value-of select="concat($ml/@lastName, ', ', $ml/@firstName)"/>
					</td>
					<td>
						<xsl:value-of select="@start"/>
					</td>
					<td>
						<xsl:value-of select="@end"/>
					</td>
				</tr>
			</xsl:for-each>
			</table>
		</xsl:if>
	</xsl:for-each>
</xsl:template>

<xsl:template mode="menu" match="vars:meta">

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
