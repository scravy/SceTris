<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:template mode="menu" match="vars:meta">
	<h6>Suche</h6>
	<ul>
		<li><a href="#">Einen freien Raum finden</a></li>
		<li><a href="#">Dozentenliste</a></li>
		<li><a href="#">Raumliste</a></li>
	</ul>
</xsl:template>

</xsl:transform>
