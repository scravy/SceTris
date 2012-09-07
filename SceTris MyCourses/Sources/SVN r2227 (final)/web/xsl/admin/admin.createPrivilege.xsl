<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">newPrivilege</xsl:with-param>
		<xsl:with-param name="hideOnSuccess" select="boolean(0)" />
	</xsl:call-template>
	
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">newPrivileges</xsl:with-param>
	</xsl:call-template>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

</xsl:transform>
