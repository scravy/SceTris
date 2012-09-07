<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="formbuilder.xsl" />
<xsl:import href="functions.xsl" />


<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" cdata-section-elements="style" />

<xsl:param name="context-path" select="/*/@context-path" />
<xsl:param name="servlet-path" select="/*/@servlet-path" />
<xsl:param name="module-path" select="/*/@module-path" />
<xsl:param name="module" select="/*/@module" />

<xsl:param name="style" select="/*/@style" />

<!-- MAIN TEMPLATE -->
<xsl:template match="/">
	<xsl:if test="/*/@type = 'html'">
		<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
	</xsl:if>
	<html meta:version="pi-2.0" item:ce="popkultur">
		<xsl:call-template name="hmtlhead" />
		<body>
			
			
			<!-- NAVIGATION -->
			<!-- <xsl:variable name="pos" >
				<xsl:call-template name="tokenize">
					<xsl:with-param	name="inputString" select="$module-path" />
					<xsl:with-param	name="separator" select="'/'" />
				</xsl:call-template>
			</xsl:variable> -->
			<xsl:call-template name="navigation" >
				<xsl:with-param name="position" select="$module" />
			</xsl:call-template>
	        
	        
	        <!-- CONTENT START -->	        
	        <div id="mainContent">
	        	<!-- <h1>SCETRIS</h1>
				<p>
					<a href="{$servlet-path}">
						<xsl:text>SCETRIS</xsl:text>
					</a> (<a href="?~type=html">html</a>, <a href="?~type=xhtml">xhtml</a>, <a href="?~type=xsl">xml+xsl</a>, <a href="?~type=xml">xml</a>, <a href="?~type=plain">plain</a>)
				</p> -->
				<xsl:apply-templates />
				
	        </div>
	        
	        
	        <!-- FOOTER -->
	        <xsl:call-template name="footer" />	
			
		</body>
	</html>
</xsl:template>





<!--
#################
END MAIN TEMPLATE
#################
-->

<!-- HTML HEAD -->
<xsl:template name="hmtlhead">
	<head>
		<title><xsl:apply-templates mode="title" /><xsl:text>...</xsl:text></title>
		<link rel="stylesheet" type="text/css" href="{$context-path}/{$style}" />
		<d http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<style type="text/css">/**/
			<xsl:apply-templates mode="style" />
		</style>
		<script type="text/javascript">/**/
			<xsl:apply-templates mode="script" />
		</script>
		<xsl:apply-templates mode="head" />
	</head>
</xsl:template>


<!-- DEFINES THE MENUS IN THE TOP BAR -->
<xsl:template name="navigation">
	<xsl:param name="position" />
	<div id="navigation" >
		<div id="rootmenu">
			<ul>
				<xsl:apply-templates mode="listmenu" select="document('menu.xml')/menu" />
			</ul>
			<div class="logout">
				<xsl:choose>
					<xsl:when test="/*/@loggedIn = 'yes'">
						<a href="{$servlet-path}/~logout">Logout (<xsl:value-of select="/*/meta:user/item:user/@loginName" />)</a>
					</xsl:when>
					<xsl:otherwise>
						<a href="{$context-path}">not logged in</a>
					</xsl:otherwise>
				</xsl:choose>
			</div>
		</div>		
		<div id="submenu">
			<ul>
				<xsl:apply-templates mode="listmenu" select="document('menu.xml')/menu/item/submenu[@name=$position]" >
					<xsl:with-param name="relative" select="1" />
				</xsl:apply-templates>
			</ul>
		</div>
	</div>
</xsl:template>

<xsl:template match="item" mode="listmenu" name="listmenu">
	<xsl:param name="relative" />
	<li>
		<a>
			<xsl:attribute name="href">
				<xsl:if test="$relative = 1" >
					<xsl:value-of select="@target" />
				</xsl:if>
				<xsl:if test="$relative != 1" >
					<xsl:value-of select="concat($servlet-path,'/',@target)" />
				</xsl:if>
			</xsl:attribute>
			<xsl:value-of select="text()" />
		</a>
	</li>
</xsl:template>


<!-- DEFINES THE MENUS IN THE BOTTOM BAR -->
<xsl:template name="footer">
	<div id="footer" class="navigation">
		<ul>
			<li>
				<a href="{$servlet-path}/start/sitemap">Sitemap</a> |
				Your style: <xsl:value-of select="$style" />
			</li>
			<li><a href="?~type=html">html</a></li>
			<li><a href="?~type=xhtml">xhtml</a></li>
			<li><a href="?~type=xsl">xml+xsl</a></li>
			<li><a href="?~type=xml">xml</a></li>
			<li><a href="?~type=plain">plain</a></li>
		</ul>
			<ul id="csschooser" class="right">
				<xsl:if test="not($style = 'scetris-blue.css')"><li><a href="?~stylesheet=scetris-blue.css" title="change theme to blue" class="blue">[x]</a></li></xsl:if>
				<xsl:if test="not($style = 'scetris-red.css')"><li><a href="?~stylesheet=scetris-red.css" title="change theme to red" class="red">[x]</a></li></xsl:if>
				<xsl:if test="not($style = 'scetris-green.css')"><li><a href="?~stylesheet=scetris-green.css" title="change theme to green" class="green">[x]</a></li></xsl:if>
				<xsl:if test="not($style = 'scetris-mono.css')"><li><a href="?~stylesheet=scetris-mono.css" title="change theme to grey" class="mono">[x]</a></li></xsl:if>
			</ul>
	</div>
</xsl:template>


<xsl:template match="@*|*|text()" />
<xsl:template match="@*|*|text()" mode="title" />
<xsl:template match="@*|*|text()" mode="head" />
<xsl:template match="@*|*|text()" mode="style" />
<xsl:template match="@*|*|text()" mode="script" />

</xsl:transform>