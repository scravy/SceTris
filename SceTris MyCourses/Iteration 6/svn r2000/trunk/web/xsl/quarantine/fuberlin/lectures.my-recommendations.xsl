<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text>Für mein Semester empfohlene Veranstaltungen</xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Für mein Semester empfohlene Veranstaltungen</h1>
	
	<ul>
		<xsl:apply-templates select="vars:courseinstances/item:courseinstances"/>
	</ul>
</xsl:template>


<xsl:template match="item:courseinstances">
	<li>
		<form method="post">
		<input type="hidden" name="enrollin" value="{@id}" />
		<input type="submit" value="enroll" class="inlinebutton"/>
		 - <a href="courseinstanceinfo/?data={@id}"><xsl:value-of select="vars:instanceOf/item:instanceOf/@name"/></a>
		 </form>
	</li>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<xsl:text> &#x2192; </xsl:text>
	<a href="{$module-path}">Veranstaltungen</a>
	<xsl:text> &#x2192; Für mein Semester empfohlen</xsl:text>
</xsl:template>

</xsl:transform>
