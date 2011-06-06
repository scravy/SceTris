<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
				
	<table class="users">
		<tr><th>
			<xsl:value-of select="$mod-lang/name" />
		</th><th>
			<xsl:value-of select="$mod-lang/labels/courseElement.partOf" />
		</th><th>
			<xsl:value-of select="$mod-lang/labels/courseElement.type" />
		</th><th>
			<xsl:value-of select="$mod-lang/options" />
		</th></tr>
		<xsl:apply-templates />
	</table>

</xsl:template>

<xsl:template match="item:courseElements">
	<xsl:variable name="id" select="generate-id(.)" />
	<tr>
		<td>
			<span class="name">
			<xsl:value-of select="@name" />
			</span>
			<span class="time"><xsl:value-of select="@timekey" /></span>
		</td>
		<td>
			<span class="name">
			<xsl:value-of select="vars:partOf/item:partOf/@name" />
			</span>
		</td>
		<td>
			<span class="name">
			<xsl:value-of select="vars:type/item:type/@name" />
			</span>
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
	
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
