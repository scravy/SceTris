<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../global.xsl" />
<xsl:import href="../formbuilder.xsl" />

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1>Create new user</h1>

	<form method="post" action="{@module-path}/new/">
	Vorname <input type="text" name="firstname" value="{vars:firstname}" /><br />
	Name <input type="text" name="lastname" value="{vars:lastname}" /><br />
	Login <input type="text" name="loginname" value="{vars:loginname}" /><br />
	PW <input type="text" name="loginpw" /><br />
	superuser <input type="checkbox" name="superuser" value="1" /><br />
	<input type="submit" value="Create" />
	</form>

</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
