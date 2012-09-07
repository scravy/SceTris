<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />
<xsl:import href="../fuberlin/lego.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="vars:crud" /> department</h1>	

	<form method="post">
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Name'" />
		<xsl:with-param name="attr" select="'name'" />
		<xsl:with-param name="value" select="vars:data/item:data/@name" />
	</xsl:call-template>

	
	<xsl:call-template name="input-alternatives">
		<xsl:with-param name="id" select="'superordinateDepartment'"/>
		<xsl:with-param name="label" select="'Superordinate Department'"/>
		<xsl:with-param name="options">
			<xsl:for-each select="vars:departments/item:departments">
				<option>
					<xsl:if test="//vars:data/item:data/@superordinateDepartment=@id">
						<xsl:attribute name="selected">selected</xsl:attribute>
					</xsl:if>	
					<xsl:attribute name="value">
						<xsl:value-of select="@id"/>
					</xsl:attribute>
					<xsl:value-of select="@name"/>
				</option>
			</xsl:for-each>
		</xsl:with-param>
	</xsl:call-template>
	
	<!--
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="'Superordinate Department'" />
		<xsl:with-param name="attr" select="'superordinateDepartment'" />
	</xsl:call-template>
	<select id="'Superordinate Department'" name="superordinateDepartment">
		<xsl:apply-templates select="vars:departments/item:departments" />
	</select><br /><br />-->
	
	<input type="hidden" name="data" value="{vars:data/item:data/@id}" />

	<xsl:call-template name="input-submit">
		<xsl:with-param name="id" select="vars:crud"/>
		<xsl:with-param name="label" select="vars:crud"/>
	</xsl:call-template>

	</form>

</xsl:template>

<xsl:template match="item:departments">
	<option>
		<xsl:if test="//vars:data/item:data/@superordinateDepartment=@id">
			<xsl:attribute name="selected">selected</xsl:attribute>
		</xsl:if>
		<xsl:attribute name="value">
			<xsl:value-of select="@id" />
		</xsl:attribute>
		<xsl:value-of select="@name" />
	</option>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
