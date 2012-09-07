<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../common/global.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	
	<xsl:choose>
	
		<xsl:when test="not($logged-in)">
			<xsl:copy-of select="document(concat('../i18n/public-home.', /vars:meta/@lang, '.xml'))" />
		</xsl:when>
	
		<xsl:when test="$logged-in">
	
	<div style="float: right; width: 200px; padding-top: 10px;">
	<dl style="color: #666666;">
		<dt>Last Login</dt>
		<dd>
			<xsl:choose>
				<xsl:when test="//item:user/@lastLogin">
					<xsl:value-of select="//item:user/@lastLogin" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$lang/never-logged-in" />
				</xsl:otherwise>
			</xsl:choose>
		</dd>
		<dt>Username</dt>
		<dd><xsl:value-of select="//item:user/@loginName" /></dd>

		<xsl:if test="//item:user/@emailAddress">
			<dt>E-Mail</dt>
			<dd><xsl:value-of select="//item:user/@emailAddress" /></dd>
		</xsl:if>
	</dl>
	</div>	

	
	<div style="float: left; padding-right: 30px; width: 250px;">
	<h3><xsl:value-of select="$lang/welcome" />, <xsl:value-of select="//item:user/@firstName" /><xsl:text> </xsl:text>
		<xsl:value-of select="//item:user/@lastName" /></h3>
		
		
	<h3>Your Courses 
		<xsl:if test="//vars:currentTerm/item:currentTerm/@name != ''">
			(<span style="font-weight: 100;"><xsl:value-of select="//vars:currentTerm/item:currentTerm/@name"/></span>)
		</xsl:if>
	</h3>
	<xsl:choose>
		<xsl:when test="//vars:mycourses/item:mycourses">
			<ul>
				<xsl:for-each select="//vars:mycourses/item:mycourses">
					<li><a href="{$servlet-path}/lectures/showCourseInstance/{@courseInstance}">
						<xsl:value-of select="vars:courseInstance/item:courseInstance/vars:instanceOf/item:instanceOf/@name"/></a></li>	
				</xsl:for-each>
			</ul>	
		</xsl:when>
		<xsl:otherwise>
			<p>You have no course this academic term. Either it's not set properly(see System settings) or you aren't enrolled in any courseinstances.</p>
		</xsl:otherwise>
	</xsl:choose>
	
	<h3>Quicklinks</h3>
	<ul>
		<li><a href="{$servlet-path}/my/schedule">Timetable</a></li>
		<li><a href="{$servlet-path}/preferences">Personal data</a></li>
		<li><a href="{$servlet-path}/lectures">Coursearchive</a></li>
	</ul>
	</div>

		</xsl:when>
	</xsl:choose>
	
	
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

<xsl:template mode="style" match="vars:meta">

</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
