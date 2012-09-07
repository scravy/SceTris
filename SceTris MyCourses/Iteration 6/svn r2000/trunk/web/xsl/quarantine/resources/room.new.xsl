<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:vars="http://technodrom.scravy.de/2010/data" 
	xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />
<xsl:import href="../fuberlin/lego.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="vars:crud" /> room</h1>	
	<form method="post">
	
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Name'" />
		<xsl:with-param name="attr" select="'roomname'" />
		<xsl:with-param name="value" select="vars:room/item:room/@name" />
	</xsl:call-template>
	

	<!-- deprecated -->
	<!--
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="'Building'" />
		<xsl:with-param name="attr" select="'building'" />
		<xsl:with-param name="options" select="vars:buildings/item:buildings" />
	</xsl:call-template>
	<select id="'Building'" name="building">
		<xsl:apply-templates select="vars:buildings/item:buildings" />
	</select><br /><br /> -->
	
	<xsl:call-template name="input-alternatives">
		<xsl:with-param name="id" select="'building'"/>
		<xsl:with-param name="label" select="'Building'"/>
		<xsl:with-param name="options">
			<xsl:for-each select="vars:buildings/item:buildings">
				<option>
					<xsl:if test="//vars:room/item:room/@building=@id">
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
	
	
	
	<xsl:for-each select="vars:features/item:features">
		<input type="hidden" name="featurekey" value="{@name}"/>
		<xsl:variable name="foo" select="@id" />
		<xsl:call-template name="input-text">
			<xsl:with-param name="id" select="'feature'" />
			<xsl:with-param name="label" select="@name" />
			<xsl:with-param name="info" select="'Some even more random info'" />
			<xsl:with-param name="value" select="//vars:roomfeature/item:roomfeature[@feature=$foo]/@quantity" />	
		</xsl:call-template>
	</xsl:for-each>
	
	<!--
	<xsl:if test="vars:features/item:features">
			<xsl:apply-templates select="vars:features/item:features" />
	</xsl:if>
	-->
	
	<input type="hidden" name="data" value="{vars:room/item:room/@id}"/>	
	<input type="hidden" name="crud" value="{vars:crud}"/>
	
	<xsl:call-template name="input-submit">
		<xsl:with-param name="id" select="vars:crud"/>
		<xsl:with-param name="label" select="vars:crud"/>
	</xsl:call-template>

	</form>

</xsl:template>


<!-- subtemplates -->
<xsl:template match="item:features">
	<input type="hidden" name="featurekey" value="{@name}"/>
	<xsl:variable name="foo" select="@id" />
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="@name" />
		<xsl:with-param name="attr" select="'feature'" />
		<xsl:with-param name="value" select="//vars:roomfeature/item:roomfeature[@feature=$foo]/@quantity" />
	</xsl:call-template>

</xsl:template>

<xsl:template match="item:buildings">
	<option>
		<xsl:if test="//vars:room/item:room/@building=@id">
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
