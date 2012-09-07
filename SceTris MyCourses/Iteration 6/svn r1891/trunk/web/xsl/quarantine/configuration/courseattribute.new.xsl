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
	<h1><xsl:value-of select="vars:crud" /> courseattribute</h1>	
	<form method="post">
	
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Name'" />
		<xsl:with-param name="attr" select="'name'" />
		<xsl:with-param name="value" select="vars:data/item:data/@name" />
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
		<xsl:with-param name="id" select="'type'"/>
		<xsl:with-param name="label" select="'Type'"/>
		<xsl:with-param name="options">
			<option value="String">
				<xsl:if test="//vars:data/item:data/@type='String'">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="'Text'"/>
			</option>	
			<option value="Integer">
				<xsl:if test="//vars:data/item:data/@type='Integer'">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:value-of select="'Zahl'"/>
			</option>
		</xsl:with-param>
	</xsl:call-template>
	

	<xsl:call-template name="input-checkbox">
		<xsl:with-param name="id" select="'unique'" />
		<xsl:with-param name="label" select="'Unique'" />
		<xsl:with-param name="value" select="vars:data/item:data/@uniqueValue" />	
	</xsl:call-template>

	<xsl:call-template name="input-checkbox">
		<xsl:with-param name="id" select="'required'" />
		<xsl:with-param name="label" select="'Required'" />
		<xsl:with-param name="value" select="vars:data/item:data/@required" />	
	</xsl:call-template>		


	<input type="hidden" name="data" value="{vars:data/item:data/@id}"/>	
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
