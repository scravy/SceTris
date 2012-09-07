<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
			
	<xsl:if test="not(//item:programs)">
		<div class="ui-corner-all notice"><xsl:value-of select="$mod-lang/noPrograms" /></div>
	</xsl:if>
	<xsl:if test="//item:programs">	
	<table class="users">
		<tr><th>
			<xsl:value-of select="$mod-lang/academicTerm" />
		</th><th>
			<xsl:value-of select="$mod-lang/department" />
		</th><th>
			<xsl:value-of select="$mod-lang/options" />
		</th></tr>
		<xsl:apply-templates />
	</table>
	</xsl:if>
</xsl:template>

<xsl:template match="item:programs">
	<tr>
		<td>
			<span class="name">
			<xsl:value-of select="vars:academicTerm/item:academicTerm/@name" />
			</span>
			<span class="time"><xsl:value-of select="vars:academicTerm/item:academicTerm/@start" /> - <xsl:value-of select="vars:academicTerm/item:academicTerm/@end" /></span>
		</td>
		<td>
			<span class="name">
			<xsl:value-of select="vars:department/item:department/@name" />
			</span>
		</td>
		<td>
		<xsl:text> </xsl:text>
		<a class="editLink optionLink">
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/editProgram/', @id)" />
			</xsl:attribute>
			<xsl:value-of select="$mod-lang/edit" />
		</a>
		<a class="editLink optionLink">
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/listCourseInstanceFromProgram/', @id)" />
			</xsl:attribute>
			<xsl:value-of select="$mod-lang/edit" />CI</a>
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
		<xsl:value-of select="$mod-lang/listPrograms" />
	</li>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
