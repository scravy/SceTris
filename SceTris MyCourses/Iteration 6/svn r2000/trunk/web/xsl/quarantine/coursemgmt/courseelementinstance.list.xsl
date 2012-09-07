<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />
<xsl:import href="../fuberlin/lego.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	
	<xsl:call-template name="listplayer" >
		<xsl:with-param name="action" select="concat(@module-path,'/courseelementinstancelist/')" />
	</xsl:call-template>

	<h1><a href="{/vars:meta/@module-path}">↤</a> CourseElementInstances (<a href="{/vars:meta/@module-path}/courseelementinstancenew">new</a>)</h1>
	
	<table cellpadding="5px">
		<tr>
		<th>Edit</th><th>CourseInstance</th><th>Element</th><th>Type</th>
		</tr>
		<xsl:apply-templates select="vars:data/item:data" />
	</table>
</xsl:template>

<xsl:template match="item:data">
	<tr>
	<td align="center" style="padding-bottom:7px;">
		<a href="{/vars:meta/@module-path}/courseelementinstancemeta/?data={@courseInstance}" class="list edit">⚒</a>
	</td>
	<td>
		<xsl:value-of select="vars:courseInstance/item:courseInstance/@program" />
	</td>
	<td>
		<xsl:value-of select="vars:courseElement/item:courseElement/@name" />
	</td>
	<td>
		<xsl:value-of select="vars:courseElement/item:courseElement/vars:type/item:type/@name" />
	</td>
	</tr>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
