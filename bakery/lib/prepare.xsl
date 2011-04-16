<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:en="http://technodrom.scravy.de/2010/relations" xmlns:n="http://technodrom.scravy.de/2010/notes" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/" xmlns="http://technodrom.scravy.de/2010/relations/c">

<xsl:output method="xml" indent="yes" />

<xsl:param name="fk_prefix"><xsl:value-of select="/en:relations/en:fk-prefix" /></xsl:param>

<xsl:template match="/en:relations">
	<relations>
	
		<xsl:attribute name="relationships">
			<xsl:value-of select="count(en:relationship)" />
		</xsl:attribute>
		<xsl:attribute name="entities">
			<xsl:value-of select="count(en:entity)" />
		</xsl:attribute>
	
		<xsl:apply-templates select="en:entity">
			<xsl:sort select="@name" />
		</xsl:apply-templates>
		
		<xsl:apply-templates select="en:relationship">
			<xsl:sort select="@name" />
		</xsl:apply-templates>
	</relations>
</xsl:template>

<xsl:template match="en:entity">
	<xsl:variable name="name"><xsl:value-of select="@name" /></xsl:variable>
	<xsl:if test="not(@name)"><xsl:message>entities need to have a name!</xsl:message></xsl:if>
	<xsl:if test="count(/en:relations/en:entity[@name = $name]) > 1">
		<xsl:message>entity::<xsl:value-of select="$name" /> is defined more than once (<xsl:value-of select="count(/en:relations/en:entity[@name = $name])" /> times actually)</xsl:message>
	</xsl:if>
	
	<entity>
		<xsl:if test="@use">
			<xsl:attribute name="use"><xsl:value-of select="@use" /></xsl:attribute>
		</xsl:if>
		<xsl:attribute name="name"><xsl:value-of select="@name" /></xsl:attribute>
		
		<xsl:apply-templates select="dc:*" />
		<xsl:apply-templates select="en:include" />
		<xsl:apply-templates select="en:attribute" />
		
		<primary>
			<xsl:apply-templates mode="primary" />
		</primary>
		<!-- <xsl:if test="en:unique/en:ref[@field]">
			<unique>
			<xsl:for-each select="en:unique/en:ref">
				<ref>
					<xsl:attribute name="field">
						<xsl:variable name="field" select="@field" />
						<xsl:if test="parent::*/parent::*/en:attribute[@name = $field]/@ref">
							<xsl:value-of select="$fk_prefix" />
						</xsl:if>
						<xsl:value-of select="@field" />
					</xsl:attribute>
				</ref>
			</xsl:for-each>
			</unique>
		</xsl:if> -->
		<xsl:apply-templates mode="singleUnique" />
		<xsl:apply-templates mode="uniqueKeys" />
	</entity>
</xsl:template>

<xsl:template match="en:relationship">
	<xsl:variable name="name"><xsl:value-of select="@name" /></xsl:variable>
	<xsl:variable name="subject"><xsl:value-of select="@subject" /></xsl:variable>
	<xsl:variable name="object"><xsl:value-of select="@object" /></xsl:variable>
	<xsl:if test="/en:relations/en:entity[@name = $name]">
		<xsl:message>relationship::<xsl:value-of select="$name" /> conflicts with same named entity.</xsl:message>
	</xsl:if>
	<xsl:if test="count(/en:relations/en:relationship[@name = $name]) > 1">
		<xsl:message>relationship::<xsl:value-of select="$name" /> is defined more than once (<xsl:value-of select="count(/en:relations/en:entity[@name = $name])" /> times actually)</xsl:message>
	</xsl:if>
	<xsl:if test="/en:relations/en:relationship[@name = $subject]">
		<xsl:message>subject of relationship::<xsl:value-of select="$name" /> refers to a relationship.</xsl:message>
	</xsl:if>
	<xsl:if test="/en:relations/en:relationship[@name = $object]">
		<xsl:message>object of relationship::<xsl:value-of select="$name" /> refers to a relationship.</xsl:message>
	</xsl:if>
	<xsl:if test="not(/en:relations/en:entity[@name = $subject])">
		<xsl:message>entity::<xsl:value-of select="$subject" /> referenced as subject by relationship::<xsl:value-of select="$name" /> does not exist.</xsl:message>
	</xsl:if>
	<xsl:if test="not(/en:relations/en:entity[@name = $object])">
		<xsl:message>entity::<xsl:value-of select="$object" /> referenced as subject by relationship::<xsl:value-of select="$name" /> does not exist.</xsl:message>
	</xsl:if>
	
	<xsl:choose>
		<xsl:when test="@subject and @name and @object and en:subject[@fk] and en:object[@fk]">
			<relationship>
				<xsl:attribute name="name"><xsl:value-of select="@name" /></xsl:attribute>
				<xsl:attribute name="subject"><xsl:value-of select="@subject" /></xsl:attribute>
				<xsl:attribute name="object"><xsl:value-of select="@object" /></xsl:attribute>
				
				<xsl:apply-templates select="dc:*" />
				
				<attribute nullable="false" use="subject">
					<xsl:attribute name="name">
						<xsl:value-of select="$fk_prefix" />
						<xsl:value-of select="en:subject/@fk" />
					</xsl:attribute>
					<xsl:attribute name="ref"><xsl:value-of select="$subject" /></xsl:attribute>
					<xsl:attribute name="type">
						<xsl:apply-templates select="/en:relations/en:entity[@name = $subject]" mode="type" />
					</xsl:attribute>
				</attribute>
				<attribute nullable="false" use="object">
					<xsl:attribute name="name">
						<xsl:value-of select="$fk_prefix" />
						<xsl:value-of select="en:object/@fk" />
					</xsl:attribute>
					<xsl:attribute name="ref"><xsl:value-of select="$object" /></xsl:attribute>
					<xsl:attribute name="type">
						<xsl:apply-templates select="/en:relations/en:entity[@name = $object]" mode="type" />
					</xsl:attribute>
				</attribute>
				
				<xsl:apply-templates select="en:include" />
				<xsl:apply-templates select="en:attribute" />
				<primary>
					<xsl:apply-templates mode="primary" />
				</primary>
				<xsl:apply-templates mode="singleUnique" />
				<xsl:apply-templates mode="uniqueKeys" />
			</relationship>
		</xsl:when>
		<xsl:otherwise>
			<xsl:message>relationship::<xsl:value-of select="$name" /> skipped since it’s definition is incomplete.</xsl:message>
			<xsl:if test="not(@subject)">
				<xsl:message>missing subject in relationship::<xsl:value-of select="$name" />.</xsl:message>
			</xsl:if>
			<xsl:if test="not(@object)">
				<xsl:message>missing object in relationship::<xsl:value-of select="$name" />.</xsl:message>
			</xsl:if>
			<xsl:if test="not(@en:subject[@fk])">
				<xsl:message>missing foreign key name for subject in relationship::<xsl:value-of select="$name" />.</xsl:message>
			</xsl:if>
			<xsl:if test="not(@en:object[@fk])">
				<xsl:message>missing foreign key name for object in relationship::<xsl:value-of select="$name" />.</xsl:message>
			</xsl:if>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<xsl:template match="en:trait">
	<xsl:apply-templates select="en:include" />
	<xsl:apply-templates select="en:attribute" />
</xsl:template>

<xsl:template match="en:include">
	<xsl:variable name="trait" select="@trait" />
	<xsl:choose>
		<xsl:when test="/en:relations/en:trait[@name = $trait]">
			<xsl:apply-templates select="/en:relations/en:trait[@name = $trait]" />
		</xsl:when>
		<xsl:otherwise>
			<xsl:message>trait::<xsl:value-of select="$trait" /> to be included in <xsl:value-of select="local-name()" />::<xsl:value-of select="@name" /> not found!</xsl:message>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<xsl:template match="en:attribute">
	<attribute>
		<xsl:if test="@use">
			<xsl:copy-of select="@use" />
		</xsl:if>
		<xsl:attribute name="hidden">
			<xsl:choose>
				<xsl:when test="@hidden = 'true'">
					<xsl:text>true</xsl:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:text>false</xsl:text>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:attribute>
		<xsl:attribute name="name">
			<xsl:if test="@ref">
				<xsl:value-of select="$fk_prefix" />
			</xsl:if>
			<xsl:value-of select="@name" />
		</xsl:attribute>
		<xsl:attribute name="serial">
			<xsl:if test="@serial = 'true'">true</xsl:if>
			<xsl:if test="not(@serial = 'true')">false</xsl:if>
		</xsl:attribute>
		<xsl:choose>
			<xsl:when test="@type">
				<xsl:attribute name="type"><xsl:value-of select="@type" /></xsl:attribute>
				<xsl:if test="@type = 'integer' or @type = 'float' or @type = 'double'">
					<xsl:if test="@min">
						<xsl:attribute name="min">
							<xsl:value-of select="number(@min)" />
						</xsl:attribute>
					</xsl:if>
					<xsl:if test="@max">
						<xsl:attribute name="max">
							<xsl:value-of select="number(@max)" />
						</xsl:attribute>
					</xsl:if>
				</xsl:if>
				<xsl:if test="@type = 'string'">
					<xsl:if test="@min">
						<xsl:attribute name="min">
							<xsl:value-of select="number(@min)" />
						</xsl:attribute>
					</xsl:if>
					<xsl:if test="@max">
						<xsl:attribute name="max">
							<xsl:value-of select="number(@max)" />
						</xsl:attribute>
					</xsl:if>
					<xsl:if test="@match">
						<xsl:attribute name="match">
							<xsl:value-of select="@match" />
						</xsl:attribute>
					</xsl:if>
					<xsl:if test="@only">
						<xsl:attribute name="only">
							<xsl:value-of select="@only" />
						</xsl:attribute>
					</xsl:if>
				</xsl:if>
				<xsl:if test="@java-profile">
					<xsl:attribute name="java-profile">
						<xsl:value-of select="@java-profile" />
					</xsl:attribute>
				</xsl:if>
		</xsl:when>
			<xsl:when test="@ref">
				<xsl:variable name="ref"><xsl:value-of select="@ref" /></xsl:variable>
				<xsl:choose>
					<xsl:when test="/en:relations/en:entity[@name = $ref]">
						<xsl:attribute name="ref"><xsl:value-of select="@ref" /></xsl:attribute>
						
						<xsl:attribute name="type">
							<xsl:apply-templates select="/en:relations/en:entity[@name = $ref]" mode="type" />
						</xsl:attribute>
						
					</xsl:when>
					<xsl:otherwise>
							<xsl:message><xsl:value-of select="@name" /> in <xsl:value-of select="local-name(parent::*)" />::<xsl:value-of select="parent::*/@name" /> references <xsl:value-of select="@ref" />, which does not exist!</xsl:message>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:message>attribute::<xsl:value-of select="@name" /> in <xsl:value-of select="local-name(parent::*)" />::<xsl:value-of select="parent::*/@name" /> does not have a type!</xsl:message>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:choose>
			<xsl:when test="@nullable = 'true'">
				<xsl:attribute name="nullable">true</xsl:attribute>
			</xsl:when>
			<xsl:otherwise>
				<xsl:attribute name="nullable">false</xsl:attribute>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:if test="@default">
			<xsl:attribute name="default"><xsl:value-of select="@default" /></xsl:attribute>
		</xsl:if>
		
		<xsl:apply-templates select="dc:*" />
	</attribute>
</xsl:template>


<!-- mode: uniqueKeys -->

<xsl:template match="en:include" mode="uniqueKeys">
	<xsl:variable name="trait" select="@trait" />
	<xsl:apply-templates select="/en:relations/en:trait[@name = $trait]" mode="uniqueKeys" />
</xsl:template>

<xsl:template match="en:trait" mode="uniqueKeys">
	<xsl:apply-templates mode="uniqueKeys" />
</xsl:template>

<xsl:template match="en:unique" mode="uniqueKeys">
	<unique>
		<xsl:for-each select="en:ref[@field]">
			<ref>
				<xsl:attribute name="field">
					<xsl:variable name="field" select="@field" />
					<xsl:if test="parent::*/parent::*/en:attribute[@name = $field]/@ref">
						<xsl:value-of select="$fk_prefix" />
					</xsl:if>
					<xsl:value-of select="@field" />
				</xsl:attribute>
			</ref>
		</xsl:for-each>
	</unique>
</xsl:template>


<!-- mode: singleUnique -->

<xsl:template match="en:attribute[@unique='true']" mode="singleUnique">
	<unique>
		<ref>
			<xsl:attribute name="field">
				<xsl:if test="@ref">
					<xsl:value-of select="$fk_prefix" />
				</xsl:if>
				<xsl:value-of select="@name" />
			</xsl:attribute>
		</ref>
	</unique>
</xsl:template>

<xsl:template match="en:include" mode="singleUnique">
	<xsl:variable name="trait" select="@trait" />
	<xsl:apply-templates select="/en:relations/en:trait[@name = $trait]" mode="singleUnique" />
</xsl:template>

<xsl:template match="en:trait" mode="singleUnique">
	<xsl:apply-templates mode="singleUnique" />
</xsl:template>


<!-- mode: type -->

<xsl:template match="en:attribute" mode="type">
	<xsl:if test="@primary = 'true'">
		<xsl:value-of select="@type" />
	</xsl:if>
</xsl:template>

<xsl:template match="en:primary" mode="type">
	<xsl:choose>
		<xsl:when test="count(en:ref) > 1">
			<xsl:message>Foreign key references multi-value primary key (not supported).</xsl:message>
		</xsl:when>
		<xsl:when test="count(en:ref) = 1">
			<xsl:variable name="name"><xsl:value-of select="parent::*/@name" /></xsl:variable>
			<xsl:apply-templates select="/en:relations/en:entity[@name = $name]" mode="typeOfField">
				<xsl:with-param name="field" select="@field" />
			</xsl:apply-templates>
		</xsl:when>
	</xsl:choose>
</xsl:template>

<xsl:template match="en:include" mode="type">
	<xsl:variable name="trait" select="@trait" />
	<xsl:apply-templates select="/en:relations/en:trait[@name = $trait]" mode="type" />
</xsl:template>

<xsl:template match="en:trait" mode="type">
	<xsl:apply-templates mode="type" />
</xsl:template>


<!-- mode: typeOfField -->

<xsl:template match="en:attribute" mode="typeOfField">
	<xsl:param name="field" />
	<xsl:if test="@name = $field">
		<xsl:choose>
			<xsl:when test="@type">
				<xsl:value-of select="@type" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:message>Möpp.</xsl:message>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:if>
</xsl:template>

<xsl:template match="en:include" mode="typeOfField">
	<xsl:param name="field" />
	<xsl:variable name="trait" select="@trait" />
	<xsl:apply-templates select="/en:relations/en:trait[@name = $trait]" mode="typeOfField">
		<xsl:with-param name="field" select="$field" />
	</xsl:apply-templates>
</xsl:template>

<xsl:template match="en:trait" mode="typeOfField">
	<xsl:param name="field" />
	<xsl:apply-templates mode="typeOfField">	
		<xsl:with-param name="field" select="@field" />
	</xsl:apply-templates>
</xsl:template>


<!-- mode: primary -->

<xsl:template match="en:attribute" mode="primary">	
	<xsl:if test="@primary = 'true'">
		<ref>
			<xsl:attribute name="field">
				<xsl:value-of select="@name" />
			</xsl:attribute>
		</ref>
	</xsl:if>
</xsl:template>

<xsl:template match="en:subject | en:object" mode="primary">
	<xsl:if test="local-name(parent::*) != 'relationship'">
		<xsl:message><xsl:value-of select="local-name()" /> not allowed in <xsl:value-of select="local-name(parent::*)" />::<xsl:value-of select="parent::*/@name" />, applies only to relationships.</xsl:message>
	</xsl:if>
	<ref>
		<xsl:attribute name="field">
			<xsl:value-of select="$fk_prefix" />
			<xsl:value-of select="@fk" />
		</xsl:attribute>
	</ref>
</xsl:template>

<xsl:template match="en:include" mode="primary">
	<xsl:variable name="trait" select="@trait" />
	<xsl:apply-templates select="/en:relations/en:trait[@name = $trait]" mode="primary" />
</xsl:template>

<xsl:template match="en:trait" mode="primary">
	<xsl:apply-templates mode="primary" />
</xsl:template>

<xsl:template match="en:primary" mode="primary">
	<xsl:for-each select="en:ref[@field]">
		<ref>
			<xsl:attribute name="field">
				<xsl:value-of select="@field" />
			</xsl:attribute>
		</ref>
	</xsl:for-each>
</xsl:template>

<!-- DublinCore -->

<xsl:template match="dc:title">
	<xsl:if test="string-length(normalize-space(text())) > 0">
		<dc:title>
			<xsl:attribute name="xml:lang">
				<xsl:if test="@xml:lang">
					<xsl:value-of select="@xml:lang" />
				</xsl:if>
				<xsl:if test="not(@xml:lang)">en</xsl:if>
			</xsl:attribute>
			<xsl:value-of select="text()" />
		</dc:title>
	</xsl:if>
</xsl:template>

<xsl:template match="dc:description">
	<xsl:if test="string-length(normalize-space(text())) > 0">
		<dc:description>
			<xsl:attribute name="xml:lang">
				<xsl:if test="@xml:lang">
					<xsl:value-of select="@xml:lang" />
				</xsl:if>
				<xsl:if test="not(@xml:lang)">en</xsl:if>
			</xsl:attribute>
			<xsl:value-of select="text()" />
		</dc:description>
	</xsl:if>
</xsl:template>


<xsl:template match="text()|@*" />
<xsl:template match="text()|@*" mode="singleUnique" />
<xsl:template match="text()|@*" mode="uniqueKeys" />
<xsl:template match="text()|@*" mode="primary" />
<xsl:template match="text()|@*" mode="type" />
<xsl:template match="text()|@*" mode="typeOfField" />
<xsl:template match="@*" mode="xhtml" />

</xsl:transform>
