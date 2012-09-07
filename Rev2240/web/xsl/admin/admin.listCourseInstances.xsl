<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:if test="not(//item:courseInstances)">
		<div class="ui-corner-all notice"><xsl:value-of select="$mod-lang/noCourseInstances" /></div>
	</xsl:if>
	<xsl:if test="//item:courseInstances">	
	<table class="users">
		<tr><th>
			<xsl:value-of select="$mod-lang/course" />
		</th><th>
			<xsl:value-of select="$mod-lang/person" />
		</th><th>
			<xsl:value-of select="$mod-lang/options" />
		</th></tr>
		<xsl:apply-templates />
	</table>
	</xsl:if>
</xsl:template>

<xsl:template match="item:courseInstances">
	<tr>
		<td>
			<span class="name">
			<xsl:value-of select="vars:instanceOf/item:instanceOf/@name" />
			</span>
		</td>
		<td>
			<span class="name">
			<xsl:variable name="ml" select="vars:mainLecturer/item:mainLecturer"/>
			<xsl:value-of select="concat($ml/@lastName,', ',$ml/@firstName)" />
			</span>
		</td>
		<td>
		<xsl:text> </xsl:text>
		<a class="editLink optionLink">
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/createCourseElementInstances/', @id)" />
			</xsl:attribute>
			<xsl:value-of select="$mod-lang/add" />
		</a>
		<a class="editLink optionLink">
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/editCourseElementInstances/', @id)" />
			</xsl:attribute>
			<xsl:value-of select="$mod-lang/edit" />
		</a>
		</td>
	</tr>
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
