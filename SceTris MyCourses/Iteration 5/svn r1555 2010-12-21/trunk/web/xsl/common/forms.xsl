<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:item="http://technodrom.scravy.de/2010/item">

	<xsl:import href="../lego/bricks.xsl" />
	
	<xsl:template name="login-form">
		<form method="post" name="login" action="../~login/" id="login-form">
			<xsl:call-template name="login-form-content" />
		</form>
	</xsl:template>

	<xsl:template name="login-form-content">

		<xsl:call-template name="input-text">
			<xsl:with-param name="id">username</xsl:with-param>
		</xsl:call-template>

		<xsl:call-template name="input-password">
			<xsl:with-param name="id">password</xsl:with-param>
		</xsl:call-template>
		
		<xsl:call-template name="input-submit">
			<xsl:with-param name="id">submit-login</xsl:with-param>
		</xsl:call-template>
	</xsl:template>

</xsl:transform>
