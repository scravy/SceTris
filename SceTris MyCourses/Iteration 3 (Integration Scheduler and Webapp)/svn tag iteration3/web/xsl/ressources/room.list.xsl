<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../presentation.global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<xsl:variable name="next" select="number(vars:page) + 1" />
	<xsl:variable name="prev" select="number(vars:page) - 1" />

	<h1>List Rooms</h1>
	
	<form method="get" action="{@module-path}/roomlist/">
	<input type="hidden" name="page" value="{vars:page}" />
	Pro Seite: <input type="text" name="limit" value="{vars:limit}" size="3" /> <input type="submit" value="Anzeigen" />
	</form>
	
	<p><a href="?page={$prev}&amp;limit={vars:limit}">Vorherige Seite</a>, <a href="?page=0">Start</a>, <a href="?page={$next}&amp;limit={vars:limit}">Nächste Seite</a></p>
	
	<table cellpadding="5px">
		<tr>
		<th>Edit</th><th>Delete</th><th>Roomname</th>
		</tr>
		<xsl:apply-templates select="vars:data/item:data" />
	</table>
</xsl:template>

<xsl:template match="item:data">
	<tr>
	<td align="center" style="padding-bottom:7px;">
		<a href="{/vars:meta/@module-path}/roomedit/?data={@id}" class="list edit">⚒</a>
	</td>
	<td align="center">
		<a href="{/vars:meta/@module-path}/roomvalidate/?data={@id}&amp;crud=delete" class="list delete">×</a>
		</td>
	<td>
		<xsl:value-of select="@name" />
	</td>
	</tr>
</xsl:template>

<xsl:template mode="style" match="vars:meta">
	dt { font-weight: bold; }
	dd { padding-left: 1.5em; }
</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
