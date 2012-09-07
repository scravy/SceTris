<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:date="http://exslt.org/dates-and-times"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:str="http://exslt.org/strings"
	xmlns:rn="http://technodrom.scravy.de/xmlns/2011/release-notes"
	xmlns="http://www.w3.org/1999/xhtml">

<xsl:param name="node" />
<xsl:output method="html" />

<xsl:template match="/">
	<xsl:apply-templates select="rn:release-notes/rn:*[local-name() = $node]" />
</xsl:template>

<xsl:template match="rn:*[@file]">
	<xsl:choose>
		<xsl:when test="rn:author">
			<xsl:for-each select="rn:author">
				<xsl:sort select="substring-after(rn:name, ' ')" />
				<xsl:value-of select="rn:name" />
				<xsl:text>&#xA;</xsl:text>
				<xsl:if test="rn:mail">
					<xsl:value-of select="substring-before(rn:mail, '@')" />
					<xsl:text> at </xsl:text>
					<xsl:value-of select="substring-after(rn:mail, '@')" />
					<xsl:text>&#xA;</xsl:text>
				</xsl:if>
				<xsl:text>&#xA;</xsl:text>
			</xsl:for-each>
		</xsl:when>
		<xsl:otherwise>
			<xsl:apply-templates />
			<xsl:text>&#xA;</xsl:text>
			<xsl:text>$ File: </xsl:text>
			<xsl:value-of select="@file" />
			<xsl:text> / Date: </xsl:text>
			<xsl:value-of select="date:date-time()" />
			<xsl:text> $&#xA;</xsl:text>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<xsl:template match="rn:title | rn:h1">
	<xsl:if test="position() > 0">&#xA;</xsl:if>
	<xsl:text>= </xsl:text>
	<xsl:value-of select="normalize-space(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'))" />
	<xsl:text> =&#xA;</xsl:text>
	<xsl:if test="local-name(following-sibling::node()) != 'subtitle'">&#xA;</xsl:if>
</xsl:template>

<xsl:template match="rn:subtitle">
	<xsl:apply-templates mode="inline" />
	<xsl:text>&#xA;&#xA;</xsl:text>
</xsl:template>

<xsl:template match="rn:section | rn:s | rn:h2">
	<xsl:text>&#xA;== </xsl:text>
	<xsl:apply-templates mode="inline" />
	<xsl:text> ==&#xA;&#xA;</xsl:text>
</xsl:template>

<xsl:template match="rn:subsection | rn:ss | rn:h3">
	<xsl:text>&#xA;== </xsl:text>
	<xsl:apply-templates mode="inline" />
	<xsl:text> ==&#xA;&#xA;</xsl:text>
</xsl:template>

<xsl:template match="rn:p">
	<xsl:variable name="text">
		<xsl:apply-templates mode="inline" />
	</xsl:variable>
	<xsl:call-template name="wrap-string">
		<xsl:with-param name="str" select="translate(normalize-space($text), '&#xA;', ' ')" />
		<xsl:with-param name="wrap-col" select="$colwidth" />
		<xsl:with-param name="break-mark" select="'&#xA;'" />
	</xsl:call-template>
	<xsl:text>&#xA;&#xA;</xsl:text>
</xsl:template>

<xsl:template match="rn:l | rn:ul | rn:list | rn:itemize">
	<xsl:apply-templates mode="list" />
	<xsl:text>&#xA;</xsl:text>
</xsl:template>

<xsl:template match="rn:i | rn:item | rn:li" mode="list">
	<xsl:variable name="text">
		<xsl:apply-templates mode="inline" />
	</xsl:variable>
	<xsl:variable name="normalized-text" select="translate(normalize-space($text), '&#xA;', ' ')" />
	<xsl:choose>
		<xsl:when test="substring($normalized-text, 2, 1) = ')'">
			<xsl:text></xsl:text>				
		</xsl:when>
		<xsl:otherwise>
			<xsl:text> * </xsl:text>
			<xsl:value-of select="substring($normalized-text, 2, 3)" />
		</xsl:otherwise>
	</xsl:choose>
	<xsl:call-template name="wrap-string">
		<xsl:with-param name="str" select="$normalized-text" />
		<xsl:with-param name="wrap-col" select="$colwidth - 3" />
		<xsl:with-param name="break-mark" select="'&#xA;   '" />
	</xsl:call-template>
	<xsl:text>&#xA;</xsl:text>
</xsl:template>

<xsl:template match="text()" mode="inline">
	<xsl:value-of select="." />
</xsl:template>

<xsl:template match="*" mode="inline">
	<xsl:if test="/rn:release-notes/*[local-name() = local-name(current())]">
		<xsl:value-of select="/rn:release-notes/*[local-name() = local-name(current())]/text()" />
	</xsl:if>
</xsl:template>

<xsl:template match="@*|text()" mode="list" />
<xsl:template match="@*|text()" />

<!-- The following lines of code are from
     http://plasmasturm.org/log/xslwordwrap/
     Credits to Aristotle Pagaltzis -->
<xsl:template name="wrap-string" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="str" />
    <xsl:param name="wrap-col" />
    <xsl:param name="break-mark" />
    <xsl:param name="pos" select="0" />
    <xsl:choose>

        <xsl:when test="contains( $str, ' ' )">
            <xsl:variable name="first-word" select="substring-before( $str, ' ' )" />
            <xsl:variable name="pos-now" select="$pos + 1 + string-length( $first-word )" />
            <xsl:choose>

                <xsl:when test="$pos > 0 and $pos-now >= $wrap-col">
                    <xsl:copy-of select="$break-mark" />
                    <xsl:call-template name="wrap-string">
                        <xsl:with-param name="str" select="$str" />
                        <xsl:with-param name="wrap-col" select="$wrap-col" />
                        <xsl:with-param name="break-mark" select="$break-mark" />
                        <xsl:with-param name="pos" select="0" />
                    </xsl:call-template>
                </xsl:when>

                <xsl:otherwise>
                    <xsl:value-of select="$first-word" />
                    <xsl:text> </xsl:text>
                    <xsl:call-template name="wrap-string">
                        <xsl:with-param name="str" select="substring-after( $str, ' ' )" />
                        <xsl:with-param name="wrap-col" select="$wrap-col" />
                        <xsl:with-param name="break-mark" select="$break-mark" />
                        <xsl:with-param name="pos" select="$pos-now" />
                    </xsl:call-template>
                </xsl:otherwise>

            </xsl:choose>
        </xsl:when>

        <xsl:otherwise>
            <xsl:if test="$pos + string-length( $str ) >= $wrap-col">
                <xsl:copy-of select="$break-mark" />
            </xsl:if>
            <xsl:value-of select="$str" />
        </xsl:otherwise>

    </xsl:choose>
</xsl:template>

</xsl:transform>
