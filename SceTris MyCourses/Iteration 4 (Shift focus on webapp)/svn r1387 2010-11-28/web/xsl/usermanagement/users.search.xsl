<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Search users</h1>
	
	<form method="get" action="{@module-path}/search/">
	by name: <input type="text" name="searchName" value="{vars:searchName}" /><br />
	by mail: <input type="text" name="searchMail" value="{vars:searchMail}" /><br />
	<input type="submit" value="Search" />
	</form>
	
	<xsl:choose>
		<xsl:when test="count(vars:users/item:users) &lt; 1">No users found</xsl:when>
		<xsl:otherwise>
			<p><b><xsl:value-of select="count(vars:users/item:users)" /></b> results.</p>
			<ul>
				<xsl:apply-templates select="vars:users/item:users" />
			</ul>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<xsl:template match="item:users">
	<li>
		<a href="{/vars:meta/@module-path}/show/?user={@loginName}"><xsl:value-of select="concat(@firstName, ' ', @lastName)" /></a>
		<xsl:if test="@emailAddress"><br />
		<xsl:value-of select="@emailAddress" />
		</xsl:if>
	</li>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
