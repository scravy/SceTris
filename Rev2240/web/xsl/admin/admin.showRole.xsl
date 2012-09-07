<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:if test="//vars:no-such-user">
		<h2>No such user!</h2>
	</xsl:if>
	<xsl:for-each select="//item:person">
		<h2>
			<xsl:value-of select="@firstName" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="@additionalNames" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="@lastName" />
		</h2>
		<h3>Roles</h3>
		<xsl:if test="not(//item:roles)">
		<p>The user does not have any roles.</p>
		</xsl:if>
		<xsl:if test="//item:roles">
			<ul>
				<xsl:for-each select="//item:roles">
					<li><xsl:value-of select="@name" /></li>
				</xsl:for-each>
			</ul>
		</xsl:if>
		<h3>Privileges</h3>
		<xsl:if test="not(//item:privileges)">
		<p>The user does not have any privileges.</p>
		</xsl:if>
		<xsl:if test="//item:privileges">
		<ul>
			<xsl:for-each select="//item:privileges">
			<li><xsl:value-of select="@name" /></li>
			</xsl:for-each>
		</ul>
		</xsl:if>
	</xsl:for-each>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/users"><xsl:value-of select="$lang/admin-users" /></a>
	</li>
	<li>
		<a href="{$module-path}/listUsers"><xsl:value-of select="$mod-lang/listUsers" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/editUser" />
	</li>
</xsl:template>

</xsl:transform>
