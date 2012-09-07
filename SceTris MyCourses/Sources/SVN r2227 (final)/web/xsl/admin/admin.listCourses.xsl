<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:key name="courses-by-department" match="vars:courses/item:courses" use="@department" />


<xsl:template mode="content" match="vars:meta">
	<xsl:if test="//item:courses">
	<xsl:for-each select="vars:courses/item:courses[count(. | key('courses-by-department', @department)[1]) = 1]">
		<xsl:sort select="@department" />
		<h2><xsl:value-of select="//vars:department/item:department[@id = current()/@department]/@name" /></h2>
		<table class="users">
		<tr><th>
			<xsl:value-of select="$mod-lang/name" />
		</th><th>
			<xsl:value-of select="$mod-lang/options" />
		</th></tr>
		<xsl:for-each select="key('courses-by-department', @department)">
			<xsl:sort select="@name" />
			<xsl:variable name="id" select="generate-id(.)" />
			<tr>
				<td>
					<span class="name">
					<xsl:value-of select="@name" />
					</span>
					<span class="time"><xsl:value-of select="@timekey" /></span>
				</td>
				<td>
				<xsl:text> </xsl:text>
				<a class="editLink optionLink">
					<xsl:attribute name="href">
						<xsl:value-of select="concat($module-path, '/editCourse/', @id)" />
					</xsl:attribute>
					<xsl:value-of select="$mod-lang/edit" />
				</a>
				</td>
			</tr>
		</xsl:for-each>
		</table>
	</xsl:for-each>
	</xsl:if>
	<xsl:if test="not(//item:courses)">
		<div class="notice ui-corner-all"><xsl:value-of select="$mod-lang/noCourses" /></div>
	</xsl:if>
</xsl:template>

<xsl:template match="item:courses">
	<xsl:variable name="id" select="generate-id(.)" />
	<tr>
		<td>
			<span class="name">
			<xsl:value-of select="@name" />
			</span>
			<span class="time"><xsl:value-of select="@timekey" /></span>
		</td>
		<td>
		<xsl:text> </xsl:text>
		<a class="editLink optionLink">
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/editCourse/', @id)" />
			</xsl:attribute>
			<xsl:value-of select="$mod-lang/edit" />
		</a>
		</td>
	</tr>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/courses"><xsl:value-of select="$lang/admin-courses" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/listCourses" />
	</li>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
