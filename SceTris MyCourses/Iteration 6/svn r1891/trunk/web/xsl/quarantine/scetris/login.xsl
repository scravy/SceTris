<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common/global.xsl" />
<xsl:import href="common/forms.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:value-of select="$lang/login" />
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="$lang/login" /></h1>

	<xsl:call-template name="login-form" />

</xsl:template>

</xsl:transform>
