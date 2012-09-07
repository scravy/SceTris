<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../fuberlin/global.xsl" />
<xsl:import href="../fuberlin/lego.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="vars:crud" /> program, step1</h1>	

	<form method="post">
	
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="'Academic Term'" />
	</xsl:call-template>
	<select id="Academic Term" name="academicterm">
		<xsl:apply-templates select="vars:academicterms/item:academicterms" />
	</select><br /><br />
	
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="'Department'" />
	</xsl:call-template>
	<select id="Department" name="department">
		<xsl:apply-templates select="vars:departments/item:departments" />
	</select><br /><br />
	
	<xsl:call-template name="checkboxinput">
		<xsl:with-param name="label" select="'Freezed'" />
		<xsl:with-param name="attr" select="'freezed'" />
		<xsl:with-param name="value" select="vars:data/item:data/@freezed" />
	</xsl:call-template>	
	
	<xsl:call-template name="checkboxinput">
		<xsl:with-param name="label" select="'Published'" />
		<xsl:with-param name="attr" select="'published'" />
		<xsl:with-param name="value" select="vars:data/item:data/@published" />
	</xsl:call-template>	
	
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="'Manager of Program'" />
	</xsl:call-template>
	<select id="Manager of Program" name="pmanager">
		<xsl:apply-templates select="vars:persons/item:persons" />
	</select><br /><br />


	<xsl:if test="vars:bookedcourses/item:bookedcourses" >
		<xsl:call-template name="selectinput">
			<xsl:with-param name="label" select="'Following Courses are booked already'" />
		</xsl:call-template>
		<select id="program_courses" name="bookedcourses" size="{count(vars:bookedcourses/item:bookedcourses)}" multiple="multiple">
			<xsl:apply-templates select="vars:bookedcourses/item:bookedcourses" />
		</select><br /><br />	
	</xsl:if>

	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="'Please select courses for the program (multiple selection possible)'" />
	</xsl:call-template>
	<select id="program_courses" name="courses" size="{count(vars:courses/item:courses)}" multiple="multiple">
		<xsl:apply-templates select="vars:courses/item:courses" />
	</select><br /><br />	
		
	<input type="hidden" name="data" value="{vars:data/item:data/@id}"/>	
	<input type="submit" value="{vars:crud}" />
	</form>

</xsl:template>

<xsl:template match="item:departments">
	<option>
		<xsl:if test="//vars:data/item:data/@department=@id">
			<xsl:attribute name="selected">selected</xsl:attribute>
		</xsl:if>
		<xsl:attribute name="value">
			<xsl:value-of select="@id" />
		</xsl:attribute>
		<xsl:value-of select="@name" />
	</option>
</xsl:template>

<xsl:template match="item:academicterms">
	<option>
		<xsl:if test="//vars:data/item:data/@academicTerm=@id">
			<xsl:attribute name="selected">selected</xsl:attribute>
		</xsl:if>
		<xsl:attribute name="value">
			<xsl:value-of select="@id" />
		</xsl:attribute>
		<xsl:value-of select="@name" />
	</option>
</xsl:template>

<xsl:template match="item:persons">
	<option>
		<xsl:if test="//vars:data/item:data/@programManager=@id">
			<xsl:attribute name="selected">selected</xsl:attribute>
		</xsl:if>
		<xsl:attribute name="value">
			<xsl:value-of select="@id" />
		</xsl:attribute>
		<xsl:value-of select="@lastName" />, <xsl:value-of select="@firstName" />
	</option>
</xsl:template>

<xsl:template match="item:courses">
	<option>
		<xsl:attribute name="value">
			<xsl:value-of select="@id" />
		</xsl:attribute>
		<xsl:value-of select="@name" />
	</option>
</xsl:template>

<xsl:template match="item:bookedcourses">
	<option>
		<xsl:attribute name="value">
			<xsl:value-of select="@id" />
		</xsl:attribute>
		<xsl:value-of select="@instanceOf" />
	</option>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
