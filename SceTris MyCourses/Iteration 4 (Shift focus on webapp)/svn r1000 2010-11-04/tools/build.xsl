<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:h="http://www.w3.org/1999/xhtml">

<xsl:template match="/project">	
<h:html>
	<h:head>
		<h:title>build.xml for project <xsl:value-of select="@name" /></h:title>
		<h:style>
			div { margin: 1em 0; background: #f0f0f0; }
			code { color: purple; }
			#bounce { position: fixed; top: 0; right: 0; margin: 0; padding: .5em; }
		</h:style>
	</h:head>
	<h:body>
	
	<h:h1><xsl:value-of select="@name" /></h:h1>
	<h:p id="bounce">Bounce to: 
		<h:a href="#targets">Targets</h:a> |
		<h:a href="#properties">Properties</h:a>
	</h:p>
	
	<h:h2><h:a id="targets" xml:id="targets">&#187;</h:a> Targets</h:h2>
	<xsl:for-each select="//target[@description]">
	<h:div>
		<h:h3><xsl:value-of select="@name" /></h:h3>
		<h:p><xsl:value-of select="@description" /></h:p>
	</h:div>
	</xsl:for-each>
	
	<h:h2><h:a id="properties" xml:id="properties">&#187;</h:a>Properties</h:h2>
	<xsl:for-each select="//property[not(@file)]">
	<xsl:sort select="@name" />
	<h:div>
		<h:h3>$<xsl:value-of select="@name" /></h:h3>
		<h:p>Default value: <h:code><xsl:value-of select="@value" /></h:code>.</h:p>
		<h:p><xsl:value-of select="@description" /></h:p>
	</h:div>
	</xsl:for-each>
	</h:body>
</h:html>
</xsl:template>

<xsl:template match="*|@*|text()" />

</xsl:transform>
