<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:call-template name="form-builder">
		<xsl:with-param name="form">deleteRoles</xsl:with-param>
		<xsl:with-param name="hideOnSuccess" select="boolean(0)" />
		<xsl:with-param name="content">
				
			<table class="users">
				<tr><th>
					<xsl:value-of select="$mod-lang/labels/newUser.loginName" />
				</th><th>
					<xsl:value-of select="$mod-lang/options" />
				</th></tr>
				<xsl:apply-templates />
			</table>
			
		</xsl:with-param>
	</xsl:call-template>

</xsl:template>

<xsl:template match="item:roles">
	<xsl:variable name="id" select="generate-id(.)" />
	<tr>
		<td>
			<span class="name">
			<input type="checkbox" name="deleteRoles.roles" value="{@id}" id="{$id}" />
			<xsl:text> </xsl:text>
			<label for="{$id}">
			<xsl:value-of select="@name" />
			</label>
			</span>
			
			<span class="time"><!-- <xsl:value-of select="@timekey" /> -->
			<xsl:for-each select="//item:*[local-name() = concat('role', current()/@id, 'privilege')]">
				<xsl:value-of select="@name" />
				<xsl:if test="position() != last()">, </xsl:if>
			</xsl:for-each>
			</span>
		</td>
		<td>
		<a class="detailLink optionLink">
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/showRole/', @id)" />
			</xsl:attribute>
			<xsl:value-of select="$mod-lang/details" />
		</a>
		<xsl:text> </xsl:text>
		<a class="editLink optionLink">
			<xsl:attribute name="href">
				<xsl:value-of select="concat($module-path, '/editRole/', @id)" />
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
