<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
	<project name="scetris-release-notes" default="scetris-release-notes">
	<description />
	<target name="scetris-release-notes">
		<xsl:apply-templates mode="antcall" />
	</target>
		<xsl:apply-templates />
	</project>
</xsl:template>

<xsl:template match="*[@file]" mode="antcall">
	<antcall>
		<xsl:attribute name="target">
			<xsl:text>make-</xsl:text>
			<xsl:value-of select="local-name()" />
		</xsl:attribute>
	</antcall>
</xsl:template>

<xsl:template match="*[@file]">
	<target>
		<xsl:attribute name="name">
			<xsl:text>make-</xsl:text>
			<xsl:value-of select="local-name()" />
			<xsl:text>-html</xsl:text>
		</xsl:attribute>
		<xslt in="release-notes.xml" style="tools/release-notes-html.xsl">
			<xsl:attribute name="out">
				<xsl:value-of select="@file" />
				<xsl:text>.html</xsl:text>
			</xsl:attribute>
			<param name="node">
				<xsl:attribute name="expression">
					<xsl:value-of select="local-name()" />
				</xsl:attribute>
			</param>
		</xslt>
	</target>
	<target>
		<xsl:attribute name="name">
			<xsl:text>make-</xsl:text>
			<xsl:value-of select="local-name()" />
		</xsl:attribute>
		<xslt in="release-notes.xml" style="tools/release-notes.xsl">
			<xsl:attribute name="out">
				<xsl:value-of select="@file" />
			</xsl:attribute>
			<param name="node">
				<xsl:attribute name="expression">
					<xsl:value-of select="local-name()" />
				</xsl:attribute>
			</param>
		</xslt>
	</target>
</xsl:template>

<xsl:template match="@*|text()" />
<xsl:template match="@*|text()" mode="antcall" />

</xsl:transform>
