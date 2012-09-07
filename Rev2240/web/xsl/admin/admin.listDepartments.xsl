<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:if test="$isSuperuser or $privileges/admin.createDepartments">
		<h2><xsl:value-of select="createDepartment" /><a name="new"><xsl:text> </xsl:text></a></h2>
		<xsl:call-template name="form-builder">
			<xsl:with-param name="form">newDepartment</xsl:with-param>
			<xsl:with-param name="hideOnSuccess" select="boolean(0)" />
		</xsl:call-template>
	</xsl:if>
	
	<h2 id="list"><xsl:value-of select="departmentsListHeading" /></h2>
	<xsl:if test="not(//item:departments)">
		<div class="ui-corner-all notice"><xsl:value-of select="$mod-lang/noDepartments" /></div>
	</xsl:if>
	<xsl:if test="//item:departments">
		<xsl:call-template name="showDepartments" />
	</xsl:if>
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
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/facilities"><xsl:value-of select="$lang/admin-facilities" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/listDepartments" />
	</li>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

<xsl:template mode="dep" match="text()" />

</xsl:transform>
