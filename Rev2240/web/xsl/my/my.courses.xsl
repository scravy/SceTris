<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../common/global.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<xsl:choose>
		<xsl:when test="$logged-in">
			<h3>Your Courses</h3>
			<xsl:choose>
				<xsl:when test="//vars:mycourses/item:mycourses">
					<ul>
						<xsl:for-each select="//vars:mycourses/item:mycourses">
							<li><a href="{$servlet-path}/lectures/showCourseInstance/{@courseInstance}">
								<xsl:value-of select="vars:courseInstance/item:courseInstance/vars:instanceOf/item:instanceOf/@name"/></a>
								<ul>
									<xsl:for-each select="//vars:mycourseelements/item:mycourseelements/vars:elementInstance/item:elementInstance[@courseInstance = current()/vars:courseInstance/item:courseInstance/@id]">
										<li>
										<xsl:value-of select="vars:courseElement/item:courseElement/vars:type/item:type/@name"/>
										-
										<xsl:value-of select="vars:startingTimeslot/item:startingTimeslot/vars:day/item:day/@name"/>
										,
										<xsl:value-of select="vars:startingTimeslot/item:startingTimeslot/@startingTime"/>
										</li>
									</xsl:for-each>
								</ul>
							</li>	
						</xsl:for-each>
					</ul>	
				</xsl:when>
				<xsl:otherwise>
					<p>You have no course this academic term. Either it's not set properly(see System settings) or you aren't enrolled in any courseinstances.</p>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:when>
		<xsl:otherwise>
			<p>You need to be logged in to use this page, please log in.</p>
		</xsl:otherwise>
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
