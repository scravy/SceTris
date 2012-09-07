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
	<h2><xsl:value-of select="$mod-lang/createUser" /></h2>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">newUser</xsl:with-param>
		<xsl:with-param name="hideOnSuccess" select="boolean(0)" />
		<xsl:with-param name="mixin" select="document('')/*/form:newUser/*" />
	</xsl:call-template>
</xsl:template>

<xsl:template match="vars:meta" mode="inner-builder">
	<xsl:param name="arg" />
	
	<xsl:if test="//item:attributes">
	<h2>Further properties:</h2>
		<xsl:for-each select="//item:attributes">
		<p class="formField">
			<input type="hidden" name="newUsers.attributesId">
				<xsl:attribute name="value">
					<xsl:value-of select="@id" />
				</xsl:attribute>
			</input>
			<label for="feature{position()}">
				<xsl:value-of select="@name" />
			</label>
			<input id="feature{position()}" class="text-input"
				type="text" name="newUsers.attributesValue" value="" />
		</p>
		</xsl:for-each>
	</xsl:if>
	
</xsl:template>

<form:newUser>
	<form:additional pos="9.5">
		<h2>Login credentials</h2>
	</form:additional>
	
	<form:additional pos="11.5">
		<h2>Adminsitrative stuff</h2>
	</form:additional>
	
	<form:additional pos="15.5">
		<h2>Privileges &amp; Roles</h2>
	</form:additional>

	<form:additional pos="23">
		<xxx:apply-templates arg="chicita" />
	</form:additional>
</form:newUser>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/users"><xsl:value-of select="$lang/admin-users" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/createUser" />
	</li>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
