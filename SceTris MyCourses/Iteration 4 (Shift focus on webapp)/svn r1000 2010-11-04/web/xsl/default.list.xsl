<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="presentation.global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<xsl:variable name="next" select="number(vars:page) + 1" />
	
	<h1>List Features</h1>
	
	<form method="get" action="{@module-path}/list/">
	<input type="hidden" name="page" value="{vars:page}" />
	Pro Seite: <input type="text" name="limit" value="{vars:limit}" size="3" /> <input type="submit" value="Anzeigen" />
	</form>
	
	<p><a href="?page=0">Start</a>, <a href="?page={$next}&amp;limit={vars:limit}">Nächste Seite</a></p>
	
	<dl>
		<xsl:apply-templates select="vars:data/item:data" />
	</dl>
</xsl:template>

<xsl:template match="item:data">
	<dt>
		<a href="{/vars:meta/@module-path}/show/?data={@id}">
			<xsl:value-of select="@id" />
		</a>
	</dt>
	<dd>
		<xsl:value-of select="@name" />
	</dd>
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
