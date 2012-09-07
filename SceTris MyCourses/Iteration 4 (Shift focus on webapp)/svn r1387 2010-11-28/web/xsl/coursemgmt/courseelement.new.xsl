<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />
<xsl:import href="../fuberlin/lego.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="vars:crud" /> courseelement</h1>	

	<form method="post">
	
	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Name of Courseelement'" />
		<xsl:with-param name="attr" select="'courseelement'" />
		<xsl:with-param name="value" select="vars:data/item:data/@name" />
	</xsl:call-template>
	
	<!-- deprecated
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="'Type'" />
		<xsl:with-param name="attr" select="'type'" />
	</xsl:call-template>
	<select id="'Type'" name="type">
		<xsl:apply-templates select="vars:courseelementtypes/item:courseelementtypes" />
	</select><br /><br />
	-->
	
	<xsl:call-template name="input-alternatives">
		<xsl:with-param name="id" select="'type'"/>
		<xsl:with-param name="label" select="'Type'"/>
		<xsl:with-param name="options">
			<xsl:for-each select="vars:courseelementtypes/item:courseelementtypes">
				<option>
					<xsl:if test="//vars:data/item:data/@type=@id">
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
	
	
	
	<xsl:call-template name="input-alternatives">
		<xsl:with-param name="id" select="'course'"/>
		<xsl:with-param name="label" select="'Course'"/>
		<xsl:with-param name="options">
			<xsl:for-each select="vars:courses/item:courses">
				<option>
					<xsl:if test="//vars:data/item:data/@partOf=@id">
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
	

	<xsl:call-template name="textinput">
		<xsl:with-param name="label" select="'Duration'" />
		<xsl:with-param name="attr" select="'duration'" />
		<xsl:with-param name="value" select="vars:data/item:data/@duration" />
	</xsl:call-template>
	
	<xsl:call-template name="input-checkbox">
		<xsl:with-param name="id" select="'required'" />
		<xsl:with-param name="label" select="'Required'" />
		<xsl:with-param name="value" select="vars:data/item:data/@required" />	
	</xsl:call-template>	
	
	<!-- features -->
	<xsl:for-each select="vars:features/item:features">
		<input type="hidden" name="featurekey" value="{@name}"/>
		<xsl:variable name="foo" select="@id" />
		<xsl:call-template name="input-text">
			<xsl:with-param name="id" select="@id" />
			<xsl:with-param name="name" select="'featuremin'" />			
			<xsl:with-param name="label" select="@name" />
			<xsl:with-param name="info" select="'Needed quantity'" />
			<xsl:with-param name="value" select="//vars:coursefeatures/item:coursefeatures[@feature=$foo]/@quantityMin" />	
		</xsl:call-template>
		<xsl:call-template name="input-text">
			<xsl:with-param name="id" select="@id" />
			<xsl:with-param name="name" select="'featurebetter'" />
			<xsl:with-param name="label" select="''" />
			<xsl:with-param name="info" select="'Optimal quantity'" />
			<xsl:with-param name="value" select="//vars:coursefeatures/item:coursefeatures[@feature=$foo]/@quantityBetter" />	
		</xsl:call-template>
	</xsl:for-each>

		
	<input type="hidden" name="data" value="{vars:data/item:data/@id}"/>	
	<xsl:call-template name="input-submit">
		<xsl:with-param name="id" select="vars:crud"/>
		<xsl:with-param name="label" select="vars:crud"/>
	</xsl:call-template>	
	</form>

</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
