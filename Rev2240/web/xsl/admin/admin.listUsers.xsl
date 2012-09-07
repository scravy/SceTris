<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:if test="//item:users">
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">deleteUsers</xsl:with-param>
		<xsl:with-param name="hideOnSuccess" select="boolean(0)" />
		<xsl:with-param name="content">
				
			<table class="users">
				<tr><th>
					<xsl:value-of select="$mod-lang/labels/newUser.loginName" />
				</th><th>
					<xsl:value-of select="$mod-lang/name" />
				</th><th>
					<xsl:value-of select="$mod-lang/options" />
				</th></tr>
				<xsl:apply-templates />
			</table>
			
		</xsl:with-param>
	</xsl:call-template>
	</xsl:if>
	<xsl:if test="not(//item:users) and not(//vars:pattern)">
		<div class="ui-corner-all notice"><xsl:value-of select="$mod-lang/noUsers" /></div>
	</xsl:if>
	<xsl:if test="not(//item:users) and (//vars:pattern)">
		<div class="ui-corner-all notice"><xsl:value-of select="$mod-lang/noPatternUsers" /></div>
	</xsl:if>


</xsl:template>

<xsl:template match="item:users">
	<xsl:variable name="id" select="generate-id(.)" />
	<tr>
		<td>
			<input type="checkbox" name="deleteUsers.users" value="{@id}" id="{$id}" />
			<xsl:text> </xsl:text>
			<label for="{$id}">
			<xsl:value-of select="@loginName" />
			</label>
		</td>
		<td>
			<span class="name">
			<xsl:value-of select="concat(@firstName, ' ' , @additionalNames, ' ', @lastName)" />
			<xsl:if test="@isSuperuser = 'true'">
				<xsl:text> (</xsl:text>
				<xsl:value-of select="$mod-lang/superuser" />
				<xsl:text>)</xsl:text>
			</xsl:if></span>
			<span class="time"><xsl:value-of select="@ctime" /></span>
		</td>
		<td>
		<a class="detailLink optionLink">
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/showUser/', @id)" />
			</xsl:attribute>
			<xsl:value-of select="$mod-lang/details" />
		</a>
		<xsl:text> </xsl:text>
		<a class="editLink optionLink">
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/editUser/', @id)" />
			</xsl:attribute>
			<xsl:value-of select="$mod-lang/edit" />
		</a>
		</td>
	</tr>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/users"><xsl:value-of select="$lang/admin-users" /></a>
	</li>
	<li>
		<xsl:value-of select="$mod-lang/listUsersShort" />
	</li>
</xsl:template>

</xsl:transform>
