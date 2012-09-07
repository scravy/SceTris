<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../global.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Details</h1>
	<xsl:if test="count(vars:users/item:users) &lt; 1"><p>Unbekannter Benutzer.</p></xsl:if>
	<xsl:apply-templates select="vars:users/item:users" />
</xsl:template>

<xsl:template match="item:users">
	<form method="get" action="{/vars:meta/@module-path}/editsubmit/">
	Vorname <input type="text" name="firstname" value="{@firstName}" /><br />
	Name <input type="text" name="lastname" value="{@lastName}" /><br />
	Login <input type="text" name="" value="{@loginName}" disabled="disabled" /><br />
	Login <input type="hidden" name="loginname" value="{@loginName}" /><br />
	PW <input type="text" name="loginPassword" /><br />
	superuser <input type="checkbox" name="superuser" value="{@isSuperuser}" /><br />
	<input type="submit" value="Change" />
	</form>
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
