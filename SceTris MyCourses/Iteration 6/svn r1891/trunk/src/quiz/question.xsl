<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:q="http://technodrom.scravy.de/2011/alp5-quiz"
  xmlns="http://www.w3.org/1999/xhtml">

<xsl:import href="global.xsl" />

<xsl:template match="q:question">
	<h2>Question #<xsl:value-of select="@num + 1" /></h2>
	<form method="post" action="{/q:page/@rand}">
	<p><xsl:value-of select="q:ask" /></p>
	<ul>
	<xsl:for-each select="q:answer">
		<li>
			<input type="checkbox" name="answers" value="{text()}" id="a{generate-id(.)}" />
			<label for="a{generate-id(.)}"><xsl:value-of select="text()" /></label>
		</li>
	</xsl:for-each>
	</ul>
	<input type="submit" value="Check this answer" />
	<input type="hidden" name="action" value="answerQuestion" />
	</form>
</xsl:template>

</xsl:transform>