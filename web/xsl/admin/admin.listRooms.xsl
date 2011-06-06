<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:if test="not(//item:rooms)">
	<div class="ui-corner-all notice">
		<xsl:value-of select="$mod-lang/noRooms" />
	</div>
	</xsl:if>
	<xsl:if test="//item:rooms">
	<table>
		<tr>
			<th>
				<xsl:value-of select="$mod-lang/labels/newRoom.building" />
			</th>
			<th>
				<xsl:value-of select="$mod-lang/labels/newRoom.number" />
			</th>
			<th>
				<xsl:value-of select="$mod-lang/labels/newRoom.name" />
			</th>
			<th>
				<xsl:value-of select="$mod-lang/options" />
			</th>
		</tr>
	<xsl:for-each select="//item:rooms">
		<tr>
			<td>
				<xsl:value-of select="@building" />
			</td>
			<td>
				<xsl:value-of select="@number" />
			</td>
			<td>
				<xsl:value-of select="@name" />
			</td>
			
			
			<td>
				<a class="detailLink optionLink">
					<xsl:attribute name="href">
						<xsl:value-of select="concat($module-path, '/showRoom/', @id)" />
					</xsl:attribute>
					<xsl:value-of select="$mod-lang/details" />
				</a>
				<a class="editLink optionLink">
					<xsl:attribute name="href">
						<xsl:value-of select="concat($module-path, '/editRoom/', @id)" />
					</xsl:attribute>
					<xsl:value-of select="$mod-lang/edit" />
				</a>
			</td>
			
		</tr>
	</xsl:for-each>
	</table></xsl:if>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/facilities"><xsl:value-of select="$lang/admin-facilities" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/listRooms" />
	</li>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
