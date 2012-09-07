<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:lang="http://technodrom.scravy.de/2010/lang"
	xmlns:xxx="http://technodrom.scravy.de/2xxx"
	exclude-result-prefixes="vars item lang form xsl xxx">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">newRoom</xsl:with-param>
		<xsl:with-param name="mixin" select="document('')/*/form:newRoom/*" />
	</xsl:call-template>
</xsl:template>

<xsl:template match="vars:meta" mode="inner-builder">
	<xsl:param name="arg" />
	
	<xsl:for-each select="//item:features">
	<p class="formField">
		<input type="hidden" name="newRoom.featureId">
			<xsl:attribute name="value">
				<xsl:value-of select="@id" />
			</xsl:attribute>
		</input>
		<label for="feature{position()}">
			<xsl:value-of select="@name" />
		</label>
		<input id="feature{position()}" class="text-input input-numeric" type="text" name="newRoom.featureQuantity" value="0" />
	</p>
	</xsl:for-each>
	
</xsl:template>

<form:newRoom>
	<form:additional pos="0.1">
		<h1>Create a new Room</h1>
	</form:additional>

	<form:additional pos="10">
		<h2>Features:</h2>
		
		<xxx:apply-templates arg="chicita" />
	</form:additional>
</form:newRoom>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">
function newFeature(node) {
	
}
</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
