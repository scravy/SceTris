<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />

<xsl:variable name="at">
	<xsl:choose>
	<xsl:when test="//*/*[@name = 'at']/text() &gt; 0">
		<xsl:value-of select="//*/*[@name = 'at']/text()"/>
	</xsl:when>
	<xsl:otherwise>
		<xsl:value-of select="0"/>
	</xsl:otherwise>
	</xsl:choose>
</xsl:variable>	

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<xsl:for-each select="vars:dept/item:dept">
		<xsl:sort select="@name" />
		<xsl:variable name="department" select="@id" />
		<table>
			<tr>
				<th colspan="4" style="background-color: #EDEDED;">
					<xsl:value-of select="@name"/>
				</th>
			</tr>
			<tr>
				<th>CourseInstance</th>
				<th>Mainlecturer</th>
				<th>Start</th>
				<th>End</th>
			</tr>		
		
		<xsl:for-each select="/vars:meta/vars:courseinstances/item:courseinstances[vars:program/item:program/@department = $department and (vars:program/item:program/@academicTerm = $at or $at = 0)]">
			
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
	</xsl:for-each>
</xsl:template>

<xsl:template mode="menu" match="vars:meta">
	<li><span class="heading"><xsl:value-of select="'Academic Terms'" /></span>
		<ul>
			<xsl:for-each select="//vars:at/item:at">
				<li>
					<a href="?at={@id}">
					<xsl:if test="$at = @id">
						<xsl:attribute name="style">
							<xsl:value-of select="'font-weight: bold;'"/>
						</xsl:attribute>
					</xsl:if>
					<xsl:value-of select="@name"/>
					</a>
				</li>
			</xsl:for-each>
			<li>
				<a href=".">
				<xsl:if test="$at = 0">
					<xsl:attribute name="style">
						<xsl:value-of select="'font-weight: bold;'"/>
					</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="'All'"/>
				</a>
			</li>
		</ul>
	</li>
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
