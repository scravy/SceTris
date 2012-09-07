<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:meta="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="meta:meta">
	<h2>This is test2.</h2>
	<form method="get" id="aForm">
		<xsl:for-each select="//item:modules">
			<p>
				<input type="text" name="modules">
					<xsl:attribute name="value">
						<xsl:value-of select="." />
					</xsl:attribute>
				</input>
				<input type="button" value="+">
					<xsl:attribute name="onclick">
						<xsl:text>document.getElementById('aForm').insertBefore(this.parentElement.cloneNode(true), this.parentNode.nextSibling);</xsl:text>
						<xsl:text>this.parentNode.nextSibling.getElementsByTagName('input').item(0).value = '';</xsl:text>
						<xsl:text>this.parentNode.nextSibling.getElementsByTagName('input').item(0).focus();</xsl:text>
					</xsl:attribute>
				</input>
				<xsl:if test="position() > 1">
					<input type="button" value="-">
						<xsl:attribute name="onclick">
							<xsl:text>document.getElementById('aForm').removeChild(this.parentNode);</xsl:text>
						</xsl:attribute>
					</input>
				</xsl:if>
			</p>
		</xsl:for-each>
		<input type="submit" id="aSubmit" value="nuke it." />
	</form>
</xsl:template>

</xsl:transform>