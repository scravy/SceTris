<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:vars="http://technodrom.scravy.de/2010/data" 
	xmlns:item="http://technodrom.scravy.de/2010/item">

<!--  <xsl:import href="fuberlin/global.xsl" />  -->

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="vars:meta" mode="style">
.method {
	font-weight: bold;
}

.file {
	color: gray;
}

li {
	font-family: monospace;
	margin: .34em;
	padding: .17em;
}

ul, li, li > span {
	display: block;
	list-item-style: none;
}

li:nth-child(even) {
	background: #f0f0ff;
}

li:nth-child(odd) {
	background: #fffff0;
}

h2 a {
	text-decoration: inherit;
	color: inherit;
}

h2 a:hover {
	text-decoration: underline;
}

div:not(:target) ul {
	display: none;
}

div:target ul {
	display: block;
}

div.error div:not(:target) a:before {
	content: "\25BA\A0";
}

div.error div:target a:before {
	content: "\25BC\A0";
	color: #ffa500;
}

#subnavigation {
	display: none;	
}

</xsl:template>

<xsl:template match="vars:meta" mode="title">Exception</xsl:template>

<xsl:template match="vars:meta" mode="script" />
<xsl:template match="vars:meta" mode="head" />

<xsl:template mode="content" match="vars:meta">
		<h1>Ouuuh, aaah! An Error occured :-(</h1>
		
		<xsl:apply-templates select="vars:exception/item:exception" />
</xsl:template>

<xsl:template match="item:exception">
	<div class="error">
		<xsl:for-each select="vars:cause/item:cause">
			<xsl:variable name="id" select="generate-id(.)" />
			<div id="{$id}">
			<h2><a href="#{$id}"><xsl:value-of select="@class" />
				<xsl:if test="@message">: &#187;<xsl:value-of select="@message" />&#171;</xsl:if></a>
			</h2>
			<ul>
			<xsl:for-each select="vars:trace/item:trace">
				<li>
					<span class="method-call">
						<span class="class">
							<xsl:value-of select="@class" />
						</span>
						<xsl:text>.</xsl:text>
						<span class="method">
							<xsl:value-of select="@method" />
						</span>
						<xsl:text>()</xsl:text>
					</span>

					<span class="file">
						<xsl:value-of select="@file" />
						<xsl:text> : </xsl:text>
						<xsl:value-of select="@line" />
					</span>
				
				</li>
			</xsl:for-each>
			</ul>
			</div>
		</xsl:for-each>
	</div>
</xsl:template>



</xsl:transform>