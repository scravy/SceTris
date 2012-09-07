<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">

	<h2><xsl:value-of select="createBuilding" /><a name="new"><xsl:text> </xsl:text></a></h2>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">newBuilding</xsl:with-param>
		<xsl:with-param name="hideOnSuccess" select="boolean(0)" />
	</xsl:call-template>
	
	<table>
		<tr>
			<th>
				<xsl:value-of select="$mod-lang/labels/newBuilding.name" />
			</th>
			<th>
				<xsl:value-of select="$mod-lang/labels/newBuilding.address" />
			</th>
			<th>
				<xsl:value-of select="$mod-lang/labels/newBuilding.department" />
			</th>
			<th>
				<xsl:value-of select="$mod-lang/options" />
			</th>
		</tr>
	<xsl:for-each select="//item:buildings">
		<tr>
			<td>
				<xsl:value-of select="@name" />
			</td>
			<td>
				<xsl:value-of select="@address" />
			</td>
			<td>
				<xsl:choose>
					<xsl:when test="//form:o[@k = current()/@department]">
						<xsl:value-of select="//form:o[@k = current()/@department]/@v" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$mod-lang/belongs-to-no-dep" />
					</xsl:otherwise>
				</xsl:choose>
			</td>
			<td>
				<a class="detailLink optionLink">
					<xsl:attribute name="href">
						<xsl:value-of select="concat($module-path, '/showBuilding/', @id)" />
					</xsl:attribute>
					<xsl:value-of select="$mod-lang/details" />
				</a>
				<a class="editLink optionLink">
					<xsl:attribute name="href">
						<xsl:value-of select="concat($module-path, '/editBuilding/', @id)" />
					</xsl:attribute>
					<xsl:value-of select="$mod-lang/edit" />
				</a>
			</td>
		</tr>
	</xsl:for-each>
	</table>
	
</xsl:template>


<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

<xsl:template mode="dep" match="text()" />

</xsl:transform>
