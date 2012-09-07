<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://technodrom.scravy.de/2010/queries" xmlns:q="http://technodrom.scravy.de/2010/queries" xmlns:en="http://technodrom.scravy.de/2010/relations/c">

<xsl:import href="sql.deleteEverything.xsl" />
<xsl:import href="sql.createTable.xsl" />
<xsl:import href="sql.uniqueKeys.xsl" />
<xsl:import href="sql.foreignKeys.xsl" />

<xsl:output method="xml" />

<xsl:param name="table_name_prefix"><!-- the empty string --></xsl:param>
<xsl:param name="uniq_prefix">uniq_</xsl:param>
<xsl:param name="unique_indexes" select="boolean(1)" />
<xsl:param name="scheme_name" select="'scetris'" />
<xsl:param name="quotes" select="boolean(1)" />

<xsl:template match="/en:relations">
<queries>
	<query>SET client_min_messages TO warning;</query>
	<query>DROP SCHEMA IF EXISTS "scetris" CASCADE;</query>
	<query>CREATE SCHEMA "scetris";</query>
	<query>SET search_path = "scetris";</query>
	<xsl:apply-templates select="en:entity | en:relationship" mode="createTable" />
	<xsl:apply-templates select="en:entity | en:relationship" mode="createUniqueKeys" />
	<xsl:apply-templates select="en:entity | en:relationship" mode="createForeignKeys" />
	<query>CREATE OR REPLACE FUNCTION ping () RETURNS BOOLEAN AS $BAZINGA$
BEGIN
	RETURN TRUE;
END
$BAZINGA$ LANGUAGE 'plpgsql';</query>

	<xsl:copy-of select="document('../templates/sql-procedures.xml')/q:queries/q:query" />
	
</queries>
</xsl:template>

<xsl:template match="text()|@*" />

</xsl:transform>