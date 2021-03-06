<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />
<xsl:import href="../fuberlin/lego.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="vars:crud" /> program, step2, additional information</h1>	

	<form method="post">
			
	<xsl:apply-templates select="vars:courseinstances/item:courseinstances" />
		
	<input type="submit" value="{vars:crud}" />
	</form>

</xsl:template>

<xsl:template match="item:courseinstances">
	<p>
		<b><xsl:value-of select="@instanceOf" /></b>
		<br />
		<xsl:call-template name="selectinput">
			<xsl:with-param name="label" select="'Mainlecturer'" />
		</xsl:call-template>
		<select id="Mainlecturer" name="mainlecturer">
			<xsl:for-each select="//vars:persons/item:persons" >
				<option>
					<xsl:attribute name="value">
						<xsl:value-of select="@id" />
					</xsl:attribute>				
					<xsl:value-of select="@lastName" />, <xsl:value-of select="@firstName" />
				</option>
			</xsl:for-each>
		</select><br />
		
		<xsl:call-template name="textinput">
			<xsl:with-param name="label" select="'Starting time'" />
			<xsl:with-param name="attr" select="'start'" />
			<xsl:with-param name="value" select="@start" />
		</xsl:call-template>
		<xsl:call-template name="textinput">
			<xsl:with-param name="label" select="'Ending time'" />
			<xsl:with-param name="attr" select="'end'" />
			<xsl:with-param name="value" select="@end" />
		</xsl:call-template>
		<input type="hidden" name="data" value="{@id}"/>	

	</p>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
