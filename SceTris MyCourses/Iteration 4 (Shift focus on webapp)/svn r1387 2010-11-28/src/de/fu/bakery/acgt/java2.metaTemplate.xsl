<?xml version="1.0" encoding="UTF-8"?>
<transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/TransformAlias" xmlns:jt="http://technodrom.scravy.de/2010/meta/java" xmlns="http://www.w3.org/1999/XSL/Transform" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:xt="http://www.w3.org/1999/XSL/Transform" exclude-result-prefixes="jt #default" xmlns:q="http://technodrom.scravy.de/2010/queries">
<!--
 * java2.xml / 2010
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
-->
<output method="xml" indent="yes" encoding="UTF-8" />

<namespace-alias stylesheet-prefix="xsl" result-prefix="xt" />


<!--
    the MAIN template
 -->

<template match="/jt:template">
	<xsl:transform version="1.0" xmlns:date="http://exslt.org/dates-and-times" extension-element-prefixes="date">
		<xsl:import href="lib/common.xsl" />
	
		<xsl:output method="text" />
		
		<xsl:param name="relation">#manager</xsl:param>
		<xsl:param name="managerName">EntityManager</xsl:param>
		<xsl:param name="targetPackage"></xsl:param>
		<xsl:param name="weavePackage">de.fu.weave</xsl:param>
		<xsl:param name="weavePackageOrm">de.fu.meta.java</xsl:param>
		<xsl:param name="installer">unknown</xsl:param>

		<xsl:template match="/en:relations">
			
			<xsl:variable name="fileName">
				<xsl:choose>
					<xsl:when test="$relation = '#manager'">
						<xsl:value-of select="$managerName" />
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$relation" />
					</xsl:otherwise>
				</xsl:choose>
				<xsl:text>.java</xsl:text>
			</xsl:variable>
			
			<apply-templates mode="header" select="jt:header/child::node()" />
			
			<xsl:choose>
				<xsl:when test="$relation = '#manager'">
					<xsl:call-template name="manager" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates select="en:entity | en:relationship" />
				</xsl:otherwise>
			</xsl:choose>
			
		</xsl:template>
		
		<for-each select="//jt:for-required | //jt:for-fields | //jt:for-all-fields | //jt:for-primaries">
			<xsl:template match="en:attribute">
				<call-template name="manager-mode-id" />
				
				<apply-templates mode="param" />
			</xsl:template>
		</for-each>
		
		<for-each select="//jt:for-queries">
			<xsl:template match="q:queries">
				<call-template name="manager-mode-id" />
				
				<apply-templates mode="query" />
			</xsl:template>
			<xsl:template match="text()|*|@*">
				<call-template name="manager-mode-id" />
			</xsl:template>
		</for-each>
		
		<apply-templates select="jt:manager" mode="manager" />
		<apply-templates select="jt:manager/jt:for-entities | jt:entity" mode="entity" />
		<apply-templates select="jt:manager/jt:for-relationships | jt:relationship" mode="relationship" />
		
		<xsl:template match="text()|*|@*" />
		
		<xsl:template name="comment-common">
			<xsl:text>@since Iteration2</xsl:text>
		</xsl:template>
		
		<xsl:template name="type">
			<xsl:choose>
				<xsl:when test="@ref"><xsl:value-of select="@ref" /></xsl:when>
				<xsl:when test="@type = 'string'">String</xsl:when>
				<xsl:when test="@type = 'text'">String</xsl:when>
				<xsl:when test="@type = 'integer'">
					<xsl:if test="@nullable = 'true'">Integer</xsl:if>
					<xsl:if test="not(@nullable = 'true')">int</xsl:if>
				</xsl:when>
				<xsl:when test="@type = 'float'">
					<xsl:if test="@nullable = 'true'">Float</xsl:if>
					<xsl:if test="not(@nullable = 'true')">float</xsl:if>
				</xsl:when>
				<xsl:when test="@type = 'double'">
					<xsl:if test="@nullable = 'true'">Double</xsl:if>
					<xsl:if test="not(@nullable = 'true')">double</xsl:if>
				</xsl:when>
				<xsl:when test="@type = 'boolean'">
					<xsl:if test="@nullable = 'true'">Boolean</xsl:if>
					<xsl:if test="not(@nullable = 'true')">boolean</xsl:if>
				</xsl:when>
				<xsl:when test="@type = 'timestamp'">java.sql.Timestamp</xsl:when>
				<xsl:when test="@type = 'time'">java.sql.Time</xsl:when>
				<xsl:when test="@type = 'date'">java.sql.Date</xsl:when>
				<xsl:when test="@type = 'password'">String</xsl:when>
				<xsl:otherwise>
					<xsl:text>Object</xsl:text>
					<xsl:message>Unknown type <xsl:value-of select="@type" />. Using 'Object' instead.</xsl:message>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:template>
		
		<xsl:template name="javasqltype">
			<xsl:choose>
				<xsl:when test="@ref"><xsl:value-of select="@ref" /></xsl:when>
				<xsl:when test="@type = 'string'">String</xsl:when>
				<xsl:when test="@type = 'text'">String</xsl:when>
				<xsl:when test="@type = 'integer'">Int</xsl:when>
				<xsl:when test="@type = 'float'">Float</xsl:when>
				<xsl:when test="@type = 'double'">Double</xsl:when>
				<xsl:when test="@type = 'boolean'">Boolean</xsl:when>
				<xsl:when test="@type = 'timestamp'">Timestamp</xsl:when>
				<xsl:when test="@type = 'time'">Time</xsl:when>
				<xsl:when test="@type = 'date'">Date</xsl:when>
				<xsl:when test="@type = 'password'">String</xsl:when>
				<xsl:otherwise>
					<xsl:text>Object</xsl:text>
					<xsl:message>Unknown type <xsl:value-of select="@type" />. Using 'Object' instead.</xsl:message>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:template>
		
		<xsl:template name="javasqltype2">
			<xsl:param name="type" />
			<xsl:choose>
				<xsl:when test="$type = 'string'">java.sql.Types.VARCHAR</xsl:when>
				<xsl:when test="$type = 'text'">java.sql.Types.LONGVARCHAR</xsl:when>
				<xsl:when test="$type = 'integer'">java.sql.Types.INTEGER</xsl:when>
				<xsl:when test="$type = 'float'">java.sql.Types.FLOAT</xsl:when>
				<xsl:when test="$type = 'double'">java.sql.Types.DOUBLE</xsl:when>
				<xsl:when test="$type = 'boolean'">java.sql.Types.BOOLEAN</xsl:when>
				<xsl:when test="$type = 'timestamp'">java.sql.Types.TIMESTAMP</xsl:when>
				<xsl:when test="$type = 'date'">java.sql.Types.DATE</xsl:when>
				<xsl:when test="$type = 'time'">java.sql.Types.TIME</xsl:when>
				<xsl:when test="$type = 'password'">java.sql.Types.VARCHAR</xsl:when>
				<xsl:otherwise>
					<xsl:text>Object</xsl:text>
					<xsl:message>Unknown javasql-type2 <xsl:value-of select="$type" />. Using 'Object' instead.</xsl:message>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:template>

	</xsl:transform>
</template>


<!--
    HEADER related templates
 -->

<template mode="header" match="jt:target-package">
	<xsl:value-of select="$targetPackage" />
</template>

<template mode="header" match="text()">
	<xsl:text><value-of select="." /></xsl:text>
</template>

<template mode="header" match="jt:file">
	<xsl:value-of select="$fileName" />
</template>

<template mode="header" match="jt:date">
	<xsl:value-of select="date:date()" />
</template>

<template mode="header" match="jt:processor">
	<xsl:value-of select="system-property('xsl:vendor')" />
</template>


<!--
    MANAGER related templates
 -->

<template mode="manager" match="jt:manager">

	<xsl:template name="manager">
		<apply-templates mode="manager" />
	</xsl:template>
	
</template>

<template mode="manager" match="text()">
	<xsl:text><value-of select="." /></xsl:text>
</template>

<template mode="manager" match="jt:weave-orm">
	<xsl:value-of select="$weavePackageOrm" />
</template>

<template mode="manager" match="jt:class-name">
	<xsl:value-of select="$managerName" />
</template>

<template mode="manager" match="jt:common">
	<xsl:call-template name="comment-common" />
</template>

<template mode="manager" match="jt:for-entities">
	<xsl:apply-templates select="en:entity">
		<call-template name="manager-mode-id" />
	</xsl:apply-templates>
</template>

<template mode="manager" match="jt:for-relationships">
	<xsl:apply-templates select="en:relationship">
		<call-template name="manager-mode-id" />
	</xsl:apply-templates>
</template>

<template mode="manager" match="jt:for-queries">
	<xsl:for-each select="document($installer)/q:queries/q:query">
		<apply-templates mode="query" />
	</xsl:for-each>
</template>


<template mode="query" match="jt:query[not(@escape)]">
	<xsl:value-of select="./text()" />
</template>

<template mode="query" match="jt:query[@escape = 'literal']">
	<xsl:call-template name="escapeLiterally">
		<xsl:with-param name="theString" select="translate(./text(), '&#xA;', ' ')" />
	</xsl:call-template>
</template>

<template mode="query" match="text()">
	<xsl:text><value-of select="." /></xsl:text>
</template>

<!--
    ENTITIES related templates
 -->

<template mode="entity" match="jt:for-entities">
	<xsl:template match="en:entity">
		<call-template name="manager-mode-id" />
		<apply-templates mode="entity" />
	</xsl:template>
</template>

<template mode="relationship" match="jt:for-relationships">
	<xsl:template match="en:relationship">
		<call-template name="manager-mode-id" />
		<apply-templates mode="entity" />
	</xsl:template>
</template>

<template mode="entity" match="text()">
	<xsl:text><value-of select="." /></xsl:text>
</template>

<template mode="entity" match="jt:entity-class">
	<xsl:value-of select="@name" />
</template>

<template mode="entity" match="jt:common">
	<call-template name="comment-common" />
</template>

<template mode="entity" match="jt:for-required">
	<xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default)]">
		<call-template name="manager-mode-id" />
	</xsl:apply-templates>
</template>

<template mode="entity" match="jt:for-fields">
	<xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp')]">
		<call-template name="manager-mode-id" />
	</xsl:apply-templates>
</template>

<template mode="entity" match="jt:for-all-fields">
	<xsl:apply-templates select="en:attribute">
		<call-template name="manager-mode-id" />
	</xsl:apply-templates>
</template>

<template mode="entity" match="jt:for-subject-of">
	<xsl:for-each select="/en:relations/en:relationship[@subject = $entity-name]">
		<apply-templates mode="entity" />
	</xsl:for-each>
</template>

<template mode="entity" match="jt:for-object-of">
	<xsl:for-each select="/en:relations/en:relationship[@object = $entity-name]">
		<apply-templates mode="entity" />
	</xsl:for-each>
</template>

<template mode="entity" match="jt:relationship-class">
	<xsl:call-template name="javaize">
		<xsl:with-param name="first" select="boolean(false)" />
		<xsl:with-param name="theString" select="@name" />
	</xsl:call-template>
</template>

<template mode="entity" match="jt:relationship-table[@escape = 'literal']">
	<xsl:call-template name="escapeLiterally">
		<xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')" />
	</xsl:call-template>
</template>

<template mode="entity" match="jt:subject-class">
	<xsl:call-template name="javaize">
		<xsl:with-param name="first" select="boolean(false)" />
		<xsl:with-param name="theString" select="@object" />
	</xsl:call-template>
</template>

<template mode="entity" match="jt:object-class">
	<xsl:call-template name="javaize">
		<xsl:with-param name="first" select="boolean(false)" />
		<xsl:with-param name="theString" select="@subject" />
	</xsl:call-template>
</template>

<template mode="entity" match="jt:subject-field[not(@escape)]">
	<xsl:value-of select="en:attribute[@use = 'subject']/@name" />
</template>

<template mode="entity" match="jt:object-field[not(@escape)]">
	<xsl:value-of select="en:attribute[@use = 'object']/@name" />	
</template>

<template mode="entity" match="jt:subject-field[@escape = 'literal']">
	<xsl:text>&quot;</xsl:text>
	<xsl:value-of select="en:attribute[@use = 'subject']/@name" />
	<xsl:text>&quot;</xsl:text>
</template>

<template mode="entity" match="jt:object-field[@escape = 'literal']">
	<xsl:text>&quot;</xsl:text>
	<xsl:value-of select="en:attribute[@use = 'subject']/@name" />
	<xsl:text>&quot;</xsl:text>
</template>


<template mode="entity" match="jt:entity">
	<xsl:template match="en:entity">
		<xsl:variable name="entity-name" select="@name" />
		<xsl:variable name="javaizedName">
			<xsl:call-template name="javaize">
				<xsl:with-param name="theString">
					<xsl:value-of select="@name" />
				</xsl:with-param>
				<xsl:with-param name="first" test="false" />
			</xsl:call-template>
		</xsl:variable>
		<xsl:if test="$javaizedName = $relation">
			<apply-templates mode="entity" />
		</xsl:if>
	</xsl:template>
</template>

<template mode="entity" match="jt:relationship">
	<xsl:template match="en:relationship">
		<xsl:variable name="javaizedName">
			<xsl:call-template name="javaize">
				<xsl:with-param name="theString">
					<xsl:value-of select="@name" />
				</xsl:with-param>
				<xsl:with-param name="first" test="false" />
			</xsl:call-template>
		</xsl:variable>
		<xsl:if test="$javaizedName = $relation">
			<apply-templates mode="entity" />
		</xsl:if>
	</xsl:template>
</template>

<template mode="entity" match="jt:weave-orm">
	<xsl:value-of select="$weavePackageOrm" />
</template>

<template mode="entity" match="jt:weave">
	<xsl:value-of select="$weavePackage" />
</template>

<template mode="entity" match="jt:entity-name">
	<xsl:value-of select="@name" />
</template>

<template mode="entity" match="jt:class-name">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString" select="@name" />
		<xsl:with-param name="first" select="false" />
	</xsl:call-template>
</template>

<template mode="entity" match="jt:table[@escape = 'literal']">
	<xsl:call-template name="escapeLiterally">
		<xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')" />
	</xsl:call-template>
</template>

<template mode="entity" match="jt:is-user | jt:if-user">
	<xsl:if test="@use = 'user'">
		<apply-templates mode="entity" />
	</xsl:if>
</template>

<template mode="entity" match="jt:if-serial | jt:is-serial">
	<xsl:if test="en:attribute[@use = 'id']/@serial = 'true'">
		<apply-templates mode="entity" />
	</xsl:if>
</template>

<template mode="entity" match="jt:not-serial">
	<xsl:if test="not(en:attribute[@use = 'id']/@serial = 'true')">
		<apply-templates mode="entity" />
	</xsl:if>
</template>

<template mode="entity" match="jt:getters">
	<xsl:for-each select="en:attribute">
		<apply-templates mode="param" select="/jt:template/jt:getter" />
	</xsl:for-each>
</template>

<template mode="entity" match="jt:id-field">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString" select="en:attribute[@use = 'id']/@name" />
	</xsl:call-template>
</template>

<!-- <template mode="entity" match="jt:id[@escape = 'literal']">
	<xsl:text>"id"</xsl:text>
</template> -->

<template mode="entity" match="jt:id-name">
	<xsl:value-of select="en:attribute[@use = 'id']/@name" />
</template>

<template mode="entity" match="jt:id-type">
	<xsl:for-each select="en:attribute[@use = 'id']">
		<xsl:call-template name="type" />
	</xsl:for-each>
</template>

<template mode="entity" match="jt:id-jdbc-type">
	<xsl:for-each select="en:attribute[@use = 'id']">
		<xsl:call-template name="javasqltype" />
	</xsl:for-each>
</template>

<template mode="entity" match="jt:subject-jdbc-type">
	<xsl:for-each select="/en:relations/en:entity[@name = current()/@subject]/en:attribute[@use = 'id']">
		<xsl:call-template name="javasqltype" />
	</xsl:for-each>
</template>

<template mode="entity" match="jt:object-jdbc-type">
	<xsl:for-each select="/en:relations/en:entity[@name = current()/@object]/en:attribute[@use = 'id']">
		<xsl:call-template name="javasqltype" />
	</xsl:for-each>
</template>

<template mode="entity" match="jt:setters">
	<xsl:for-each select="en:attribute">
		<apply-templates mode="param" select="/jt:template/jt:setter" />
	</xsl:for-each>
</template>

<template mode="entity" match="jt:subject-class | jt:subject">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString" select="@subject" />
		<xsl:with-param name="first" select="false" />
	</xsl:call-template>
</template>

<template mode="entity" match="jt:object-class | jt:object-class">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString" select="@object" />
		<xsl:with-param name="first" select="false" />
	</xsl:call-template>	
</template>

<template mode="entity" match="jt:object-id-field">
	<xsl:value-of select="/en:relations/en:entity[@name = current()/@object]/en:attribute[@use = 'id']/@name" />
</template>

<template mode="entity" match="jt:subject-id-field">
	<xsl:value-of select="/en:relations/en:entity[@name = current()/@subject]/en:attribute[@use = 'id']/@name" />
</template>

<template mode="entity" match="jt:object-id-jdbc-type">
	<xsl:for-each select="/en:relations/en:entity[@name = current()/@object]/en:attribute[@use = 'id']">
		<xsl:call-template name="javasqltype" />
	</xsl:for-each>
</template>

<template mode="entity" match="jt:subject-id-jdbc-type">
	<xsl:for-each select="/en:relations/en:entity[@name = current()/@subject]/en:attribute[@use = 'id']">
		<xsl:call-template name="javasqltype" />		
	</xsl:for-each>
</template>

<template mode="entity" match="jt:object-table[not(@escape)]">
	<!-- TODO: prefix shall be generic -->
	<xsl:text>scetris.</xsl:text>
	<xsl:value-of select="@object" />
</template>

<template mode="entity" match="jt:subject-table[not(@escape)]">
	<!-- TODO: prefix shall be generic -->
	<xsl:text>scetris.</xsl:text>
	<xsl:value-of select="@subject" />
</template>


<template mode="entity" match="jt:object-table[@escape = 'literal']">
	<xsl:call-template name="escapeLiterally">
		<xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @object, '&quot;')" />
	</xsl:call-template>
</template>

<template mode="entity" match="jt:subject-table[@escape = 'literal']">
	<xsl:call-template name="escapeLiterally">
		<xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @subject, '&quot;')" />
	</xsl:call-template>
</template>


<template mode="entity" match="jt:manager-class">
	<xsl:value-of select="$managerName" />
</template>

<template mode="entity" match="jt:description">
	<xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]" />
</template>

<template mode="entity" match="jt:title">
	<xsl:value-of select="dc:title[@xml:lang = 'en' or not(@xml:lang)]" />
</template>

<template mode="entity" match="jt:name-field">
	<xsl:if test="en:attribute[@use = 'title']">
	<xsl:value-of select="en:attribute[@use = 'title']/@name" />
	</xsl:if>
	<xsl:if test="not(en:attribute[@use = 'title'])">
	<xsl:value-of select="en:attribute[@use = 'id']/@name" />
	</xsl:if>
</template>

<template mode="entity" match="jt:timestamp">
	<xsl:value-of select="en:attribute[@use = 'timestamp']/@name" />
</template>



<!--
	RELATIONSHIP related templates
-->

<template mode="relationship" match="jt:for-entities">
	<xsl:template match="en:entity">
		<call-template name="manager-mode-id" />
		<apply-templates mode="relationship" />
	</xsl:template>
</template>

<template mode="relationship" match="jt:for-relationships">
	<xsl:template match="en:relationship">
		<call-template name="manager-mode-id" />
		<apply-templates mode="relationship" />
	</xsl:template>
</template>

<template mode="relationship" match="text()">
	<xsl:text><value-of select="." /></xsl:text>
</template>

<template mode="relationship" match="jt:relationship-class">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString" select="@name" />
		<xsl:with-param name="first" select="boolean(0)" />
	</xsl:call-template>
</template>

<template mode="relationship" match="jt:relationship-name">
	<xsl:value-of name="theString" select="@name" />
</template>

<template mode="relationship" match="jt:common">
	<call-template name="comment-common" />
</template>

<template mode="relationship" match="jt:for-required">
	<xsl:apply-templates select="en:attribute[@nullable != 'true' and not(@serial = 'true') and not(@use = 'timestamp') and not(@default) and not(@use = 'subject') and not(@use = 'object')]">
		<call-template name="manager-mode-id" />
	</xsl:apply-templates>
</template>

<template mode="relationship" match="jt:for-fields">
	<xsl:apply-templates select="en:attribute[not(@serial = 'true') and not(@use = 'timestamp') and not(@use = 'subject') and not(@use = 'object')]">
		<call-template name="manager-mode-id" />
	</xsl:apply-templates>
</template>

<template mode="relationship" match="jt:for-all-fields">
	<xsl:apply-templates select="en:attribute">
		<call-template name="manager-mode-id" />
	</xsl:apply-templates>
</template>

<template mode="relationship" match="jt:for-primaries">
	<xsl:apply-templates select="en:attribute[@name = current()/en:primary/en:ref/@field and @name != current()/@subject and @name != current()/@object]">
		<call-template name="manager-mode-id" />
	</xsl:apply-templates>
</template>

<template mode="relationship" match="jt:entity">
	<xsl:template match="en:entity">
		<xsl:variable name="javaizedName">
			<xsl:call-template name="javaize">
				<xsl:with-param name="theString">
					<xsl:value-of select="@name" />
				</xsl:with-param>
				<xsl:with-param name="first" test="false" />
			</xsl:call-template>
		</xsl:variable>
		<xsl:if test="$javaizedName = $relation">
			<apply-templates mode="relationship" />
		</xsl:if>
	</xsl:template>
</template>

<template mode="relationship" match="jt:relationship">
	<xsl:template match="en:relationship">
		<xsl:variable name="javaizedName">
			<xsl:call-template name="javaize">
				<xsl:with-param name="theString">
					<xsl:value-of select="@name" />
				</xsl:with-param>
				<xsl:with-param name="first" test="false" />
			</xsl:call-template>
		</xsl:variable>
		<xsl:if test="$javaizedName = $relation">
			<apply-templates mode="relationship" />
		</xsl:if>
	</xsl:template>
</template>

<template mode="relationship" match="jt:weave-orm">
	<xsl:value-of select="$weavePackageOrm" />
</template>

<template mode="relationship" match="jt:weave">
	<xsl:value-of select="$weavePackage" />
</template>

<template mode="relationship" match="jt:class-name">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString" select="@name" />
		<xsl:with-param name="first" select="false" />
	</xsl:call-template>
</template>

<template mode="relationship" match="jt:id[@escape = 'literal']">
	<xsl:text>"id"</xsl:text>
</template>

<template mode="relationship" match="jt:table[@escape = 'literal']">
	<xsl:call-template name="escapeLiterally">
		<xsl:with-param name="theString" select="concat('&quot;', 'scetris', '&quot;', '.', '&quot;', @name, '&quot;')" />
	</xsl:call-template>
</template>

<template mode="relationship" match="jt:if-fields">
	<xsl:if test="en:attribute[not(@use = 'subject') and not(@use = 'object') and not(@use = 'timestamp')]">
		<apply-templates mode="relationship" />
	</xsl:if>
</template>

<template mode="relationship" match="jt:is-user | jt:if-user">
	<xsl:if test="@use = 'user'">
		<apply-templates mode="relationship" />
	</xsl:if>
</template>

<template mode="relationship" match="jt:if-serial | jt:is-serial">
	<xsl:if test="en:attribute[@use = 'id']/@serial = 'true'">
		<apply-templates mode="relationship" />
	</xsl:if>
</template>

<template mode="relationship" match="jt:not-serial">
	<xsl:if test="not(en:attribute[@use = 'id']/@serial = 'true')">
		<apply-templates mode="relationship" />
	</xsl:if>
</template>

<template mode="relationship" match="jt:getters">
	<xsl:for-each select="en:attribute">
		<apply-templates mode="param" select="/jt:template/jt:getter" />
	</xsl:for-each>
</template>

<template mode="relationship" match="jt:id-field">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString" select="en:attribute[@use = 'id']/@name" />
	</xsl:call-template>
</template>

<template mode="relationship" match="jt:id-name">
	<xsl:value-of select="en:attribute[@use = 'id']/@name" />
</template>

<template mode="relationship" match="jt:id-type">
	<xsl:for-each select="en:attribute[@use = 'id']">
		<xsl:call-template name="type" />
	</xsl:for-each>
</template>

<template mode="relationship" match="jt:id-jdbc-type">
	<xsl:for-each select="en:attribute[@use = 'id']">
		<xsl:call-template name="javasqltype" />
	</xsl:for-each>
</template>

<template mode="relationship" match="jt:setters">
	<xsl:for-each select="en:attribute">
		<apply-templates mode="param" select="/jt:template/jt:setter" />
	</xsl:for-each>
</template>

<template mode="relationship" match="jt:subject-name">
	<xsl:value-of select="@subject" />
</template>

<template mode="relationship" match="jt:object-name">
	<xsl:value-of select="@object" />
</template>

<template mode="relationship" match="jt:subject | jt:subject-class">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString" select="@subject" />
		<xsl:with-param name="first" select="false" />
	</xsl:call-template>
</template>

<template mode="relationship" match="jt:object | jt:object-class">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString" select="@object" />
		<xsl:with-param name="first" select="false" />
	</xsl:call-template>	
</template>

<template mode="relationship" match="jt:subject-ref-type">
	<xsl:text>int</xsl:text>
</template>

<template mode="relationship" match="jt:object-ref-type">
	<xsl:text>int</xsl:text>
</template>

<template mode="relationship" match="jt:subject-fk-name">
	<xsl:value-of select="en:attribute[@use = 'subject']/@name" />
</template>

<template mode="relationship" match="jt:object-fk-name">
	<xsl:value-of select="en:attribute[@use = 'object']/@name" />
</template>

<template mode="relationship" match="jt:manager-class">
	<xsl:value-of select="$managerName" />
</template>

<template mode="relationship" match="jt:description">
	<xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]" />
</template>

<template mode="relationship" match="jt:title">
	<xsl:value-of select="dc:title[@xml:lang = 'en' or not(@xml:lang)]" />
</template>

<template mode="relationship" match="jt:timestamp">
	<xsl:value-of select="en:attribute[@use = 'timestamp']/@name" />
</template>


<!--
    FIELD related templates
 -->
 
<template mode="param" match="jt:not-last">
	<xsl:if test="position() != last()">
		<xsl:text><value-of select="text()" /></xsl:text>
	</xsl:if>
</template>

<template mode="param" match="jt:field-type">
	<xsl:call-template name="type" />
</template>

<template mode="param" match="jt:field-title">
	<xsl:value-of select="dc:title[@xml:lang = 'en' or not(@xml:lang)]" />
</template>

<template mode="param" match="jt:field-description">
	<xsl:value-of select="dc:description[@xml:lang = 'en' or not(@xml:lang)]" />
</template>

<template mode="param" match="jt:field-name">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString">
			<xsl:value-of select="@name" />
		</xsl:with-param>
	</xsl:call-template>
</template>

<template mode="param" match="jt:field-Name">
	<xsl:call-template name="javaize">
		<xsl:with-param name="theString">
			<xsl:value-of select="@name" />
		</xsl:with-param>
		<xsl:with-param name="first" select="false" />
	</xsl:call-template>
</template>

<template mode="param" match="jt:field-jdbc-type">
	<xsl:call-template name="javasqltype" />
</template>

<template mode="param" match="jt:field-jdbc-constant">
	<xsl:if test="@type and not(@ref)">
		<xsl:call-template name="javasqltype2">
			<xsl:with-param name="type" select="@type" />
		</xsl:call-template>
	</xsl:if>
	<xsl:if test="@ref">java.sql.Types.INTEGER</xsl:if>
</template>

<template mode="param" match="jt:sql-col">
	<xsl:value-of select="@name" />
</template>

<template mode="param" match="jt:weave-orm">
	<xsl:value-of select="$weavePackageOrm" />
</template>

<template mode="param" match="jt:weave">
	<xsl:value-of select="$weavePackage" />
</template>

<template mode="param" match="jt:ref-id-type">
	<xsl:if test="@nullable = 'true'">
		<xsl:text>Integer</xsl:text>
	</xsl:if>
	<xsl:if test="not(@nullable = 'true')">
		<xsl:text>int</xsl:text>
	</xsl:if>
</template>

<template mode="param" match="jt:if-nullable | jt:is-nullable">
	<xsl:if test="@nullable = 'true'">
		<apply-templates mode="param" />
	</xsl:if>
</template>

<template mode="param" match="jt:not-default">
	<xsl:if test="not(@default)">
		<apply-templates mode="param" />
	</xsl:if>
</template>

<template mode="param" match="jt:has-default | jt:if-default">
	<xsl:if test="@default">
		<apply-templates mode="param" />
	</xsl:if>
</template>

<template mode="param" match="jt:default">
	<xsl:choose>
		<xsl:when test="@type = 'timestamp'">new java.sql.Timestamp(System.currentTimeMillis())</xsl:when>
		<xsl:when test="@type = 'string'"><xsl:value-of select="concat('&quot;', @default, '&quot;')" /></xsl:when>
		<xsl:otherwise><xsl:value-of select="@default" /></xsl:otherwise>
	</xsl:choose>
</template>

<template mode="param" match="jt:not-nullable">
	<xsl:if test="not(@nullable = 'true')">
		<apply-templates mode="param" />
	</xsl:if>
</template>

<template mode="param" match="jt:if-ref | jt:is-ref">
	<xsl:if test="@ref">
		<apply-templates mode="param" />
	</xsl:if>
</template>

<template mode="param" match="jt:if-use">
	<xsl:if test="@use">
		<apply-templates mode="param" />
	</xsl:if>
</template>

<template mode="param" match="jt:if-autoinc">
	<xsl:if test="@serial">
		<apply-templates mode="param" />
	</xsl:if>
</template>

<template mode="param" match="jt:if-hidden">
	<xsl:if test="@hidden = 'true'">
		<apply-templates mode="param" />
	</xsl:if>
</template>

<template mode="param" match="jt:is-id | jt:if-is-id">
	<xsl:if test="@use = 'id'">
		<apply-templates mode="param" />
	</xsl:if>
</template>

<template mode="param" match="jt:use">
	<xsl:value-of select="@use" />
</template>

<template mode="param" match="jt:ref-rel">
	<xsl:value-of select="@ref" />
</template>

<template mode="param" match="jt:not-ref">
	<xsl:if test="not(@ref)">
		<apply-templates mode="param" />
	</xsl:if>
</template>




<template mode="param" match="text()">
	<xsl:text><value-of select="." /></xsl:text>
</template>


<!--
    COMMON templates
 -->

<template name="manager-mode-id">
	<attribute name="mode">
		<text>manager_</text>
		<value-of select="generate-id()" />	
	</attribute>
</template>

<template name="comment-common">
	<xsl:call-template name="comment-common" />
</template>


<template match="jt:include[@src]" mode="manager">
	<apply-templates mode="manager" select="document(concat('../templates/', @src))" />
</template>

<template match="jt:include[@src]" mode="header">
	<apply-templates mode="header" select="document(concat('../templates/', @src))" />
</template>

<template match="jt:include[@src]" mode="entity">
	<apply-templates mode="entity" select="document(concat('../templates/', @src))" />
</template>

<template match="jt:include[@src]" mode="relationship">
	<apply-templates mode="relationship" select="document(concat('../templates/', @src))" />
</template>

<template match="jt:include[@src]">
	<apply-templates select="document(concat('../templates/', @src))" />
</template>

<template match="jt:fragment" mode="manager">
	<apply-templates mode="manager" />
</template>

<template match="jt:fragment" mode="header">
	<apply-templates mode="header" />
</template>

<template match="jt:fragment" mode="entity">
	<apply-templates mode="entity" />
</template>

<template match="jt:fragment" mode="relationship">
	<apply-templates mode="relationship" />
</template>

<template match="jt:fragment">
	<apply-templates />
</template>



<template match="text()|*|@*" mode="entities-required" />
<template match="text()|*|@*" />
<template match="*|@*" mode="manager" />
<template match="*|@*" mode="header" />
<template match="*|@*" mode="entity" />
<template match="*|@*" mode="relationship" />

</transform>
