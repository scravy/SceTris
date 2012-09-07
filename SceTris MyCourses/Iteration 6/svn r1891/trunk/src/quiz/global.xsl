<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:q="http://technodrom.scravy.de/2011/alp5-quiz"
  xmlns="http://www.w3.org/1999/xhtml">

<xsl:template match="/">
<html>
	<head>
		<title>Alp5-Quiz</title>
		<style type="text/css"><![CDATA[
			
		]]></style>
	</head>
	<body>
		<xsl:choose>
			<xsl:when test="//q:exception">
				<p>Exception!</p>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates />
			</xsl:otherwise>
		</xsl:choose>
	</body>
</html>
</xsl:template>

<xsl:template match="@*|text()" />

</xsl:transform>
