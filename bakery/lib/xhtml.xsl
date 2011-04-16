<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/">

<xsl:output omit-xml-declaration="yes" method="xml" indent="yes" />

<xsl:template match="/en:relations">
	<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html>&#xA;</xsl:text>
	<html>
		<head>
			<title>Entities &amp; Relationships</title>
			<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
			<link rel="stylesheet" type="text/css" href="xhtml.css" title="fancy" />
			<link rel="alternate stylesheet" type="text/css" href="xhtml-plain.css" title="plain" />
		</head>
		<body>
			<h1>Entities &amp; Relationships</h1>
			<div id="toc">
				<h2>Entities (<xsl:value-of select="count(en:entity)" />)</h2>
				<ul>
					<xsl:apply-templates select="en:entity" mode="toc" />
				</ul>
				<h2>Relationships (<xsl:value-of select="count(en:relationship)" />)</h2>
				<ul>
					<xsl:apply-templates select="en:relationship" mode="toc" />
				</ul>
			</div>
			<hr />
			<div id="content">
				<div id="entities">
					<xsl:apply-templates select="en:entity" />
				</div>
				<div id="relationships">
					<xsl:apply-templates select="en:relationship" />
				</div>
			</div>
		</body>
	</html>
</xsl:template>

<xsl:template match="en:entity | en:relationship">
<xsl:variable name="name" select="@name" />
<div>
	<xsl:attribute name="id">
		<xsl:value-of select="local-name()" />
		<xsl:text>.</xsl:text>
		<xsl:value-of select="@name" />
	</xsl:attribute>
<table>
	<xsl:attribute name="class">
		<xsl:value-of select="local-name()" />
	</xsl:attribute>
	
	<thead>
		<tr>
			<th colspan="4">
				<xsl:value-of select="@name" />
				<xsl:if test="local-name() = 'relationship'">
					<xsl:text> (</xsl:text>
					<a>
						<xsl:attribute name="href">
							<xsl:text>#entity.</xsl:text>
							<xsl:value-of select="@subject" />
						</xsl:attribute>
						<xsl:value-of select="@subject" />
					</a>
					<xsl:text> → </xsl:text>
					<a>
						<xsl:attribute name="href">
							<xsl:text>#entity.</xsl:text>
							<xsl:value-of select="@object" />
						</xsl:attribute>
						<xsl:value-of select="@object" />
					</a>
					<xsl:text>)</xsl:text>
				</xsl:if>
			</th>
		</tr>
		<xsl:if test="local-name() = 'entity'">
			<xsl:if test="/en:relations/en:relationship[@subject = $name]">
				<tr>
					<td>related as subject:</td>
					<td colspan="3">
						<xsl:for-each select="/en:relations/en:relationship[@subject = $name]">
								<a>
									<xsl:attribute name="href">
										<xsl:text>#entity.</xsl:text>
										<xsl:value-of select="@object" />
									</xsl:attribute>
									<xsl:value-of select="@object" />
								</a>
								<xsl:text> via </xsl:text>
								<a>
									<xsl:attribute name="href">
										<xsl:text>#relationship.</xsl:text>
										<xsl:value-of select="@name" />
									</xsl:attribute>
									<xsl:value-of select="@name" />
								</a>
								<xsl:if test="position() != last()">
									<xsl:text>, </xsl:text>
								</xsl:if>
						</xsl:for-each>
					</td>
				</tr>
			</xsl:if>
			<xsl:if test="/en:relations/en:relationship[@object = $name]">
				<tr>
					<td>related as object:</td>
					<td colspan="3">
						<xsl:for-each select="/en:relations/en:relationship[@object = $name]">
								<a>
									<xsl:attribute name="href">
										<xsl:text>#entity.</xsl:text>
										<xsl:value-of select="@subject" />
									</xsl:attribute>
									<xsl:value-of select="@subject" />
								</a>
								<xsl:text> via </xsl:text>
								<a>
									<xsl:attribute name="href">
										<xsl:text>#relationship.</xsl:text>
										<xsl:value-of select="@name" />
									</xsl:attribute>
									<xsl:value-of select="@name" />
								</a>
								<xsl:if test="position() != last()">
									<xsl:text>, </xsl:text>
								</xsl:if>
						</xsl:for-each>
					</td>
				</tr>
			</xsl:if>
			<xsl:if test="/en:relations/en:entity/en:attribute[@ref = $name] | /en:relations/en:relationship/en:attribute[@ref = $name and parent::*/@subject != $name and parent::*/@object != $name]">
				<tr>
					<td>referenced (one-to-many):</td>
					<td colspan="3">
							<xsl:for-each select="/en:relations/en:entity/en:attribute[@ref = $name] | /en:relations/en:relationship/en:attribute[@ref = $name and parent::*/@subject != $name and parent::*/@object != $name]">
							<xsl:text>by </xsl:text>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="concat('#', local-name(parent::*), '.', parent::*/@name, '.', @name)" />
								</xsl:attribute>
								<xsl:value-of select="@name" />
							</a>
							<xsl:text> in </xsl:text>
							<a>
								<xsl:attribute name="href">
									<xsl:value-of select="concat('#', local-name(parent::*), '.', parent::*/@name)" />
								</xsl:attribute>
								<xsl:value-of select="parent::*/@name" />
							</a>
							<xsl:if test="position() != last()">
								<xsl:text>, </xsl:text>
							</xsl:if>
					</xsl:for-each>
					</td>
				</tr>
			</xsl:if>
		</xsl:if>
	</thead>
	<tbody>
		<xsl:apply-templates select="en:attribute" />
	</tbody>
	<tfoot>
		<tr>
			<td>Constraint</td>
			<td colspan="3">PRIMARY KEY (<xsl:apply-templates select="en:primary/en:ref" />)</td>
		</tr>
		<xsl:for-each select="en:unique">
			<tr>
				<td>Constraint</td>
				<td colspan="3">UNIQUE (<xsl:apply-templates select="en:ref" />)</td>
			</tr>
		</xsl:for-each>
		<xsl:if test="dc:title[@xml:lang = 'en']">
		<tr>
			<xsl:attribute name="class">dc_description dc</xsl:attribute>
			<td>Title</td>
			<td colspan="3"><xsl:value-of select="dc:title[@xml:lang = 'en']/text()" /></td>
		</tr>
		</xsl:if>
		<xsl:if test="dc:description[@xml:lang = 'en']">
		<tr>
			<xsl:attribute name="class">dc_description dc</xsl:attribute>
			<td>Description</td>
			<td colspan="3"><xsl:value-of select="dc:description[@xml:lang = 'en']/text()" /></td>
		</tr>
		</xsl:if>
	</tfoot>
</table>
</div>
</xsl:template>

<xsl:template match="en:ref">
<a>
	<xsl:attribute name="href">
		<xsl:value-of select="concat('#', local-name(parent::*/parent::*), '.', parent::*/parent::*/@name, '.', @field)" /> 
	</xsl:attribute>
	<xsl:value-of select="@field" />
</a>
<xsl:if test="position() != last()">
	<xsl:text>, </xsl:text>
</xsl:if>
</xsl:template>

<xsl:template match="en:attribute">
<xsl:variable name="name" select="@name" />
	<tr>
		<xsl:attribute name="id">
			<xsl:value-of select="concat(local-name(parent::*), '.', parent::*/@name, '.', @name)" />
		</xsl:attribute>
		<xsl:attribute name="class">
			<xsl:if test="@ref">
				<xsl:text>foreignKey</xsl:text>
			</xsl:if>
			<xsl:text> </xsl:text>
			<xsl:if test="parent::*/en:primary/en:ref[@field = $name]">
				<xsl:text>primaryKey</xsl:text>
			</xsl:if>
		</xsl:attribute>
		<th>
			<a>
				<xsl:attribute name="href">
					<xsl:value-of select="concat('#', local-name(parent::*), '.', parent::*/@name, '.', @name)" />
				</xsl:attribute>
				<xsl:value-of select="@name" />
			</a>
		</th>
		<xsl:if test="@ref">
		<td>
			<xsl:text>FOREIGN KEY → </xsl:text>
			<a>
				<xsl:attribute name="href">
					<xsl:text>#entity.</xsl:text>
					<xsl:value-of select="@ref" />
				</xsl:attribute>
				<xsl:value-of select="@ref" />
			</a>
		</td>
		</xsl:if>
		<xsl:if test="not(@ref)">
		<td>
			<xsl:value-of select="@type" />
		</td>
		</xsl:if>
		<td>
			<xsl:if test="not(@nullable = 'true')">
				<xsl:text>NOT NULL</xsl:text>
			</xsl:if>
		</td>
		<td>
			<xsl:if test="@default">
				<xsl:text>DEFAULT </xsl:text>
				<xsl:if test="@type = 'string'">'</xsl:if>
				<xsl:value-of select="@default" />
				<xsl:if test="@type = 'string'">'</xsl:if>
			</xsl:if>
			<xsl:choose>
				<xsl:when test="@min and not(@max)">
					<xsl:text>≥ </xsl:text>
					<xsl:value-of select="@min" />					
				</xsl:when>
				<xsl:when test="@max and not(@min)">	
					<xsl:text>≤ </xsl:text>
					<xsl:value-of select="@max" />
				</xsl:when>
				<xsl:when test="@max and @min">
					<xsl:value-of select="@min" />	
					<xsl:text> ≤ </xsl:text>	
					<xsl:value-of select="@name" />
					<xsl:text> ≤ </xsl:text>
					<xsl:value-of select="@max" />
				</xsl:when>
			</xsl:choose>
		</td>
	</tr>
	<xsl:if test="dc:*">
		<tr>
			<td colspan="4">
				<dl>
					<xsl:for-each select="dc:*">
						<dt><xsl:value-of select="local-name()" /> (<xsl:value-of select="@xml:lang" />)</dt>
						<dd><xsl:value-of select="child::node()" /></dd>
					</xsl:for-each>
				</dl>
			</td>
		</tr>
	</xsl:if>
</xsl:template>

<xsl:template match="en:entity | en:relationship" mode="toc">
	<li>
		<a>
			<xsl:attribute name="href">
				<xsl:text>#</xsl:text>
				<xsl:value-of select="local-name()" />
				<xsl:text>.</xsl:text>
				<xsl:value-of select="@name" />
			</xsl:attribute>
			<xsl:value-of select="@name" />
		</a>
	</li>
</xsl:template>


<xsl:template match="text()|@*" mode="toc" />
<xsl:template match="text()|@*" />

</xsl:transform>