<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml"	xmlns:data="http://technodrom.scravy.de/score">

<xsl:template match="/">

	<html>
		<head>
			<title><xsl:apply-templates mode="title" /></title>
			<style type="text/css"><![CDATA[

@namespace xhtml url("http://www.w3.org/1999/xhtml");

xhtml|h1 {
	font-family: "Baskerville", serif;
}

			]]></style>
		</head>
		
		<body>
			<xsl:apply-templates mode="content" />
		</body>
	</html>

</xsl:template>

</xsl:transform>