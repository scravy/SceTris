<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml">

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" />

<xsl:template match="/">
	<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
	<html>
		<head>
			<xsl:apply-templates mode="head" />
			<link rel="stylesheet" type="text/css">
				<xsl:attribute name="href">
					<xsl:value-of select="@stylesheet-href" />
				</xsl:attribute>
			</link>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		</head>
		<body>
			<h1>scetris header</h1>
			<hr />
			<xsl:apply-templates />
			<hr />
			<h1>scetris footer</h1>
		</body>
	</html>
</xsl:template>

<xsl:template match="@*|text()" />
<xsl:template match="@*|text()" mode="head" />

</xsl:transform>