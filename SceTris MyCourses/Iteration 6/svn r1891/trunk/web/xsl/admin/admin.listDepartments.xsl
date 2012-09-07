<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<!--<table>
		<tr>
			<th>
				<xsl:value-of select="$mod-lang/labels/newDepartment.name" />
			</th>
			<th>
				<xsl:value-of select="$mod-lang/labels/newDepartment.superordinateDepartment" />
			</th>
			<th>
				<xsl:value-of select="$mod-lang/options" />
			</th>
		</tr>
	<xsl:for-each select="//item:departments">
		<tr>
			<td>
				<xsl:value-of select="@name" />
			</td>
			<td>
				<xsl:choose>
					<xsl:when test="@superordinateDepartment">
						<xsl:value-of select="//item:departments[@id = current()/@superordinateDepartment]/@name" />
					</xsl:when>
					<xsl:otherwise>
						<em>
							<xsl:value-of select="$mod-lang/no-superordinate-department" />
						</em>
					</xsl:otherwise>
				</xsl:choose>
			</td>
			<td>
				<a class="detailLink optionLink">
					<xsl:attribute name="href">
						<xsl:value-of select="concat($module-path, '/showDepartment/', @id)" />
					</xsl:attribute>
					<xsl:value-of select="$mod-lang/details" />
				</a>
				<a class="editLink optionLink">
					<xsl:attribute name="href">
						<xsl:value-of select="concat($module-path, '/editDepartment/', @id)" />
					</xsl:attribute>
					<xsl:value-of select="$mod-lang/edit" />
				</a>
			</td>
		</tr>
	</xsl:for-each>
	</table> -->
	
	<h2><xsl:value-of select="createDepartment" /><a name="new"><xsl:text> </xsl:text></a></h2>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">newDepartment</xsl:with-param>
		<xsl:with-param name="hideOnSuccess" select="boolean(0)" />
	</xsl:call-template>
	
	<h2>...<a name="list"><xsl:text> </xsl:text></a></h2>
	<xsl:call-template name="showDepartments" />
	
</xsl:template>

<xsl:template name="showDepartments">
	<ul class="topMostDepartments">
		<xsl:apply-templates select="//item:departments[not(@superordinateDepartment)]" mode="dep" />
	</ul>
</xsl:template>

<xsl:template mode="dep" match="item:departments">
	<li>
		<a>
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/showDepartment/', @id)" />
			</xsl:attribute>
			<xsl:value-of select="@name" />
		</a>
		<xsl:text> (</xsl:text>
		<a>
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/editDepartment/', @id)" />
			</xsl:attribute>
			<xsl:value-of select="$mod-lang/edit" />
		</a>
		<xsl:text>)</xsl:text>
		<ul>
			<xsl:apply-templates select="//item:departments[@superordinateDepartment = current()/@id]" mode="dep" />
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

<xsl:template mode="dep" match="text()" />

</xsl:transform>
