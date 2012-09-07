<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:data="http://technodrom.scravy.de/score">

	<xsl:import href="global.xsl" />
	
	<xsl:template match="data:data" mode="title">
		<xsl:text>Error</xsl:text>
	</xsl:template>
	
	<xsl:template match="data:data" mode="content">
		<h1>Error</h1>
		<h2>Irgendwas stimmt nicht</h2>
		
	</xsl:template>

</xsl:transform>
