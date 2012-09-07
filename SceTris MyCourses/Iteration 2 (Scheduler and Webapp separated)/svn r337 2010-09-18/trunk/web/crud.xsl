<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:data="http://technodrom.scravy.de/2010/data">

<xsl:import href="global.xsl" />

<xsl:template match="data:data" mode="head">
	<xsl:value-of select="data:title" />
</xsl:template>

<xsl:template match="data:data">
	<h1>Welcome to scetris, blablubb</h1>
	<h2>This is bazooka circus.</h2>
			
	<ul>
		<li><a href="../about/">About</a></li>
		<li><a href="../crud/">GenericCRUD</a></li>
	</ul>

</xsl:template>

</xsl:transform>