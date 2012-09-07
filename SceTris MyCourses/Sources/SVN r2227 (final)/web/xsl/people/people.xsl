<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../common/global.xsl" />
<xsl:import href="common.xsl" />

<xsl:variable name="letters">ABCDEFGHIJKLMNOPQRSTUVWXYZ</xsl:variable>
<xsl:variable name="from">qaywsxedcrfvtgbzhnujmikolp</xsl:variable>
<xsl:variable name="to">QAYWSXEDCRFVTGBZHNUJMIKOLP</xsl:variable>

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<xsl:if test="not(//item:lecturer)">
		<div class="ui-corner-all notice">No users in database.</div>
	</xsl:if>
	<div>
	<xsl:if test="//item:lecturer">
		<ul class="letters"><xsl:call-template name="letters" /></ul>
		<xsl:call-template name="lecturers" />
	</xsl:if>
	</div>
</xsl:template>

<xsl:template name="show-lecturer">
	<li>
		<a href="showLecturer/{@id}">
		<span class="name">
			<xsl:value-of select="concat(@lastName, ', ', @firstName, ' ', @additionalNames)" />
		</span>
		</a>
	</li><xsl:text>&#xA;</xsl:text>
</xsl:template>

<xsl:template name="lecturers">
	<xsl:param name="i" select="1" />
	<xsl:variable name="letter" select="substring($letters, $i, 1)" />
	<h3 id="letter-{$letter}"><xsl:value-of select="$letter" /></h3>
	<xsl:variable name="pivot" select="(count(//item:lecturer[translate(substring(@lastName, 1, 1), $from, $to) = $letter]) + 1) div 2" />
	
	<ul class="names">
		<xsl:for-each select="//item:lecturer[translate(substring(@lastName, 1, 1), $from, $to) = $letter][position() &lt;= $pivot]">
			<xsl:call-template name="show-lecturer" />
		</xsl:for-each>
	</ul>
	<ul class="names">
		<xsl:for-each select="//item:lecturer[translate(substring(@lastName, 1, 1), $from, $to) = $letter][position() > $pivot]">
			<xsl:call-template name="show-lecturer" />
		</xsl:for-each>
	</ul>
	
	<xsl:if test="26 &gt; $i">
		<xsl:call-template name="lecturers">
			<xsl:with-param name="i" select="$i + 1" />
		</xsl:call-template>
	</xsl:if>
</xsl:template>

<xsl:template name="letters">
	<xsl:param name="i" select="1" />
	<xsl:variable name="letter" select="substring($letters, $i, 1)" />
	
	<li>
		<xsl:choose>
			<xsl:when test="//item:lecturer[translate(substring(@lastName, 1, 1), $from, $to) = $letter][1]">
				<a href="#letter-{$letter}"><xsl:value-of select="$letter" /></a>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$letter" />
			</xsl:otherwise>
		</xsl:choose>
	</li>
	
	<xsl:if test="26 &gt; $i">
		<xsl:call-template name="letters">
			<xsl:with-param name="i" select="$i + 1" />
		</xsl:call-template>
	</xsl:if>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

<xsl:template mode="style" match="vars:meta">
	.letters li {
		display: block;
		float: left;
		padding: 0;
		padding-right: .5em;
		margin: 0;
		font-weight: bold;
	}
	
	.names {
		display: block;
		float: left;
		width: 45%;
	}
	
	h3 {
		clear: left;
	}
</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
