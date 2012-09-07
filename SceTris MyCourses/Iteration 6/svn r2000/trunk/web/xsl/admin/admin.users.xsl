<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:lang="http://technodrom.scravy.de/2010/lang"
	exclude-result-prefixes="vars item lang form xsl">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:value-of select="$lang/admin-users" />
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	
	<h1><xsl:value-of select="$lang/admin-users" /></h1>
	
	<p>
		<xsl:value-of select="$mod-lang/usersAndRolesDescription" />
	</p>
	
	<h3><xsl:value-of select="$mod-lang/users" /></h3>
	<ul>
		<li><a href="{$module-path}/createUser"><xsl:value-of select="$mod-lang/createUser" /></a></li>
		<li><a href="{$module-path}/listUsers"><xsl:value-of select="$mod-lang/listUsers" /></a></li>
	</ul>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="mixin" select="document('')/*/form:searchUser/*" />
		<xsl:with-param name="form">searchUser</xsl:with-param>
	</xsl:call-template>
	
	<h3><xsl:value-of select="$mod-lang/roles" /></h3>
	<ul>
		<li><a href="{$module-path}/createRole"><xsl:value-of select="$mod-lang/createRole" /></a></li>
		<li><a href="{$module-path}/listRoles"><xsl:value-of select="$mod-lang/listRoles" /></a></li>
		<li><a href="{$module-path}/createPrivilege"><xsl:value-of select="$mod-lang/createPrivilege" /></a></li>
		<li><a href="{$module-path}/listPrivileges"><xsl:value-of select="$mod-lang/listPrivileges" /></a></li>
	</ul>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="mixin" select="document('')/*/form:searchRole/*" />
		<xsl:with-param name="form">searchRole</xsl:with-param>
	</xsl:call-template>
	
	<h3><xsl:value-of select="$mod-lang/groups" /></h3>
	<ul>
		<li><a href="{$module-path}/createGroup"><xsl:value-of select="$mod-lang/createGroup" /></a></li>
		<li><a href="{$module-path}/listGroups"><xsl:value-of select="$mod-lang/listGroups" /></a></li>
	</ul>
	<xsl:call-template name="form-builder">
		<xsl:with-param name="mixin" select="document('')/*/form:searchGroup/*" />
		<xsl:with-param name="form">searchGroup</xsl:with-param>
	</xsl:call-template>
</xsl:template>

<form:searchUser>
	<form:input-text name="q" />
</form:searchUser>

<form:searchGroup>
	<form:input-text name="q" />
</form:searchGroup>

<form:searchRole>
	<form:input-text name="q" />
</form:searchRole>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<xsl:value-of select="$lang/admin-users" />
	</li>
</xsl:template>

</xsl:transform>
