<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />
<xsl:import href="../fuberlin/lego.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	
	<!--
	<xsl:call-template name="listplayer" >
		<xsl:with-param name="action" select="concat(@module-path,'/roomlist/')" />
	</xsl:call-template>
	-->
	
	<h1><a href="{/vars:meta/@module-path}">↤</a> Courseattributes (<a href="{/vars:meta/@module-path}/courseattributenew">new</a>)</h1>

	
	<xsl:variable name="o_dir" select="(vars:o_dir)" />
	<xsl:variable name="o_col" select="(vars:o_col)" />	
	<table>
		<tr>
		<th><p>Actions</p></th>
		<th><p>Name</p> <!--<a href="?o_col=name&amp;o_dir=ascending">␆</a><a href="?o_col=name&amp;o_dir=descending">␁</a>--> </th>
		<th><p>Type</p> <!--<a href="?o_col=building&amp;o_dir=ascending">␆</a><a href="?o_col=building&amp;o_dir=descending">␁</a>--> </th>
		<th><p>Unique</p></th>
		<th><p>Required</p></th>
		</tr>
		<xsl:apply-templates select="vars:data/item:data">
		    <xsl:sort select="@*[name()=$o_col]" order="{$o_dir}" />
		</xsl:apply-templates>
	</table>
</xsl:template>


<xsl:template match="item:data">
	<tr>
	<td>
		<a href="{/vars:meta/@module-path}/courseattributeedit/?data={@id}" class="edit">⚒</a>
	</td>
	<td>
		<xsl:value-of select="@name" />
	</td>
	<td>
		<xsl:value-of select="@type" />
	</td>
	<td>
		<xsl:value-of select="@uniqueValue" />
	</td>
	<td>
		<xsl:value-of select="@required" />
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
