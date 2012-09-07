<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="/">
<html>
	<head>
		<title>State of the art</title>
	</head>
	<body>
		<p>Status: <xsl:value-of select="//vars:status" /></p>
	</body>
</html>
</xsl:template>

</xsl:transform>
