<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item" xmlns:req="http://technodrom.scravy.de/2010/request">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta" mode="style">

#phail p:first-child input[type="button"][value="-"] {
	display: none;
}

input {
	font-size: 1.1em;
	margin: 0.2em;
}

.wrong span {
	display: block;
	font-weight: bold;
}

.wrong {
	border: 1px solid red;
	background: #ffcccc;
}

</xsl:template>

<xsl:template match="meta:meta">
	<h2>This is test2.</h2>
	<form method="get" id="aForm" action="{/*/@module-path}/test2?~type=xml">
		<p>
			<xsl:if test="0 > number(meta:yippie) and /*/req:req/req:arg[@name = 'yippie']">
				<xsl:attribute name="class">wrong</xsl:attribute>
				<span>Please enter a positive integer!</span>
			</xsl:if>
			<label for="num">An integer (≥ 0): </label> <input type="text" name="yippie" id="num" value="{/*/req:req/req:arg[@name = 'yippie']}" />
		</p>
		<p>
			<label for="dub">A double: </label> <input type="text" name="something" id="dub" value="{meta:something}" />
		</p>
		<div id="phail">
		<xsl:for-each select="//item:modules">
			<xsl:variable name="id" select="generate-id(.)" />
			<p>
				<label for="{$id}">A string:</label>
				<input type="text" name="modules" id="{$id}">
					<xsl:attribute name="value">
						<xsl:value-of select="." />
					</xsl:attribute>
				</input>
				<input type="button" value="+">
					<xsl:attribute name="onclick">
						<xsl:text>document.getElementById('phail').insertBefore(this.parentNode.cloneNode(true), this.parentNode.nextSibling);</xsl:text>
						<xsl:text>this.parentNode.nextSibling.getElementsByTagName('input').item(0).value = '';</xsl:text>
						<xsl:text>this.parentNode.nextSibling.getElementsByTagName('input').item(0).focus();</xsl:text>
					</xsl:attribute>
				</input>
				<input type="button" value="-">
					<xsl:attribute name="onclick">
						<xsl:text>document.getElementById('phail').removeChild(this.parentNode);</xsl:text>
					</xsl:attribute>
				</input>
			</p>
		</xsl:for-each>
		</div>
		<input type="submit" id="aSubmit" value="nuke it." />
	</form>
</xsl:template>

</xsl:transform>