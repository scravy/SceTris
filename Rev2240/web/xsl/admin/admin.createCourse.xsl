<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:xxx="http://technodrom.scravy.de/2xxx">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<h2><xsl:value-of select="$mod-lang/createCourse" /></h2>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">newCourse</xsl:with-param>
		<xsl:with-param name="hideOnSuccess" select="boolean(0)" />
		<!-- <xsl:with-param name="mixin" select="document('')/*/form:newCourse/*" /> -->
	</xsl:call-template>
</xsl:template>

<form:newCourse>
	<form:additional pos="0.1">
		<h1>Create a new Course</h1>
	</form:additional>

	<form:additional pos="8">
		<h2>Features required by this course</h2>
		<xxx:apply-templates arg="chicita" />
	</form:additional>
</form:newCourse>

<xsl:template match="vars:meta" mode="inner-builder">
	<xsl:param name="arg" />
	
	<xsl:for-each select="//item:features">
	<p class="formField">
		<input type="hidden" name="newCourse.requiredFeatureId">
			<xsl:attribute name="value">
				<xsl:value-of select="@id" />
			</xsl:attribute>
		</input>
		<label for="feature{position()}">
			<xsl:value-of select="@name" />
		</label>
		<input id="feature{position()}" class="text-input input-numeric" type="text" name="newCourse.requiredFeatureMinQuantity" value="0" />
		<input id="feature{position()}x" class="text-input input-numeric" type="text" name="newCourse.requiredFeatureBetterQuantity" value="0" />
	</p>
	</xsl:for-each>
	
</xsl:template>
<xsl:template mode="path" match="vars:meta">

</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">
	
</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
