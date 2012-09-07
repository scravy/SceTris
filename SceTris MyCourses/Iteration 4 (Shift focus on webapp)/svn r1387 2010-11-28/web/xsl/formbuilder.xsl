<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
		xmlns="http://www.w3.org/1999/xhtml" 
		xmlns:item="http://technodrom.scravy.de/2010/item" 
		xmlns:ent="http://technodrom.scravy.de/2010/relations/c" 
		xmlns:dc="http://dublincore.org/documents/dcmi-namespace/" 
		xmlns:fn="http://www.w3.org/2005/xpath-functions" 
		xmlns:vars="http://technodrom.scravy.de/2010/data" >

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" cdata-section-elements="style" />

<xsl:param name="lang" select="'en'" />


<xsl:template name="textinput">
	<xsl:param name="label" />
	<xsl:param name="attr" />
	<xsl:param name="value" />
	
	<div>
		<label for="{$label}">
			<xsl:value-of select="$label" />
		</label>
	</div>
	<input type="text" id="{$label}" name="{$attr}" value="{$value}" />
	<br />
</xsl:template>


<xsl:template name="selectinput">
	<xsl:param name="label" />
	<div>
		<label for="{$label}">
			<xsl:value-of select="$label" />
		</label>
	</div>
</xsl:template>



<xsl:template name="checkboxinput">
	<xsl:param name="label" />
	<xsl:param name="attr" />
	<xsl:param name="value" />
	
	<div>
		<label for="{$label}">
			<xsl:value-of select="$label" />
		</label>
	</div>
	<input type="checkbox" id="{$label}" name="{$attr}" value="true">
		<xsl:if test="$value='true'" >
			<xsl:attribute name="checked">
				<xsl:value-of select="'checked'" />
			</xsl:attribute>
		</xsl:if>
	</input>
	<br />
</xsl:template>



<xsl:template name="selectperson">
	<xsl:param name="label" />
	<xsl:param name="attr" />
	<xsl:param name="options" />
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="$label" />
	</xsl:call-template>
	<select id="{$label}" name="{$attr}">
		<xsl:for-each select="$options">
			<xsl:sort select="@id" />
			<option>
				<xsl:if test="//vars:data/item:data/@programManager=@id">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>
				<xsl:attribute name="value">
					<xsl:value-of select="@id" />
				</xsl:attribute>
				<xsl:value-of select="@lastName" />, <xsl:value-of select="@firstName" />
			</option>
		</xsl:for-each>
	</select><br />
</xsl:template>


<xsl:template name="selectprogram">
	<xsl:param name="label" />
	<xsl:param name="attr" />
	<xsl:param name="options" />
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="$label" />
	</xsl:call-template>
	<select id="{$label}" name="{$attr}">
		<xsl:for-each select="$options">
			<xsl:sort select="@id" />
			<option>
				<!-- <xsl:if test="//vars:data/item:data/@programManager=@id">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>	-->
				<xsl:attribute name="value">
					<xsl:value-of select="@id" />
				</xsl:attribute>
				<xsl:value-of select="@id" /> :: <xsl:value-of select="@academicTerm" />#<xsl:value-of select="@department" />
			</option>
		</xsl:for-each>
	</select><br />
</xsl:template>

<xsl:template name="selectcourse">
	<xsl:param name="label" />
	<xsl:param name="attr" />
	<xsl:param name="options" />
	<xsl:call-template name="selectinput">
		<xsl:with-param name="label" select="$label" />
	</xsl:call-template>
	<select id="{$label}" name="{$attr}">
		<xsl:for-each select="$options">
			<xsl:sort select="@id" />
			<option>
				<!-- <xsl:if test="//vars:data/item:data/@programManager=@id">
					<xsl:attribute name="selected">selected</xsl:attribute>
				</xsl:if>	-->
				<xsl:attribute name="value">
					<xsl:value-of select="@id" />
				</xsl:attribute>
				<xsl:value-of select="@name" />
			</option>
		</xsl:for-each>
	</select><br />
</xsl:template>

<xsl:template name="listplayer_old">
	<xsl:param name="action" />
	<xsl:param name="limit" />
	<xsl:param name="pages" />
	<xsl:param name="page" />

	<div id="listplayer" class="">
		<form method="get" action="{$action}">
		<!-- <input type="hidden" name="page" value="{$page}" /> -->
		Entries per page: <input type="text" name="limit" value="{$limit}" size="2" /> <input type="submit" value="show" />
		<br />
		<xsl:if test="$pages">
			Page:
		</xsl:if>	
		<ul>
			<xsl:for-each select="$pages">
				<li><a href="?page={.}&amp;limit={$limit}">
					<xsl:if test=".=$page">
						<xsl:attribute name="class">currentpage</xsl:attribute>
					</xsl:if>
					<xsl:value-of select=".+1" />
				</a></li>
			</xsl:for-each>
		</ul>
		</form>
		
	</div>
</xsl:template>


<xsl:template name="listplayer">
	<xsl:param name="action" />
	<xsl:variable name="limit" select="vars:limit" />
	<xsl:variable name="page" select="number(vars:page)" />
	<xsl:variable name="pages" select="vars:pages/item:pages" />

	<div id="listplayer" class="">
		<form method="get" action="{$action}">
		<!-- <input type="hidden" name="page" value="{$page}" /> -->
		Entries par page: <input type="text" name="limit" value="{$limit}" size="2" /> <input type="submit" value="show" />
		<br />
		<xsl:if test="$pages">
			Page:
		</xsl:if>	
		<ul>
			<xsl:for-each select="$pages">
				<li><a href="?page={.}&amp;limit={$limit}">
					<xsl:if test=".=$page">
						<xsl:attribute name="class">currentpage</xsl:attribute>
					</xsl:if>
					<xsl:value-of select=".+1" />
				</a></li>
			</xsl:for-each>
		</ul>
		</form>
		
	</div>
</xsl:template>


<xsl:template match="@*|text()" mode="entities" />


</xsl:transform>