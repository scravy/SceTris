<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../common/global.xsl" />


<xsl:template mode="title" match="vars:meta">
	<xsl:text></xsl:text>
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<div class="abc">
	<a name="abc"/><br />
	<a href="#A">A</a>
	<a href="#B">B</a>
	<a href="#C">C</a>
	<a href="#D">D</a>
	<a href="#E">E</a>
	<a href="#F">F</a>
	<a href="#G">G</a>
	<a href="#H">H</a>
	<a href="#I">I</a>
	<a href="#J">J</a>
	<a href="#K">K</a>
	<a href="#L">L</a>
	<a href="#M">M</a>
	<a href="#N">N</a>
	<a href="#O">O</a>
	<a href="#P">P</a>
	<a href="#Q">Q</a>
	<a href="#R">R</a>
	<a href="#S">S</a>
	<a href="#T">T</a>
	<a href="#U">U</a>
	<a href="#V">V</a>
	<a href="#W">W</a>
	<a href="#X">X</a>
	<a href="#Y">Y</a>
	<a href="#Z">Z</a>
	</div>
	
	<ul>
	<xsl:call-template name="rekurs">
		<xsl:with-param name="list" select="//vars:lecturer/item:lecturer[@isLecturer='true']" />
		<xsl:with-param name="posi" select="1" />
	</xsl:call-template>
	</ul>

	<!--
	<ul>
	<xsl:apply-templates>
		<xsl:sort select="@lastName"/>
	</xsl:apply-templates>
	</ul>
	-->
</xsl:template>



<xsl:template name="rekurs">
	<xsl:param name="list" />
	<xsl:param name="posi" />
	<xsl:param name="previousLetter" />
	<xsl:param name="firstItem" select="$list[position()=$posi]" />
	<xsl:variable name="firstLetter" select="substring($firstItem/@lastName,1,1)"/>
	
	<!--
	<xsl:param name="secondItem" select="$list[position()=$posi+1]" />
	<xsl:variable name="secondLetter" select="substring($secondItem/@lastName,1,1)"/>
	-->
	
	
	<xsl:if test="$previousLetter!=$firstLetter">
		<a name="{$firstLetter}" href="#abc"><h3><xsl:value-of select="$firstLetter"/></h3></a><br />
	</xsl:if>
	
	<!--
	<xsl:value-of select="concat($firstItem/@lastName, ', ', $firstItem/@firstName)"/><br />
	-->
	
	<xsl:apply-templates select="$firstItem" />
	
	<xsl:if test="count($list) > $posi">
		<xsl:call-template name="rekurs">
			<xsl:with-param name="list" select="$list" />
			<xsl:with-param name="posi" select="$posi+1" />
			
			<xsl:with-param name="previousLetter" select="$firstLetter" />
		</xsl:call-template>
	</xsl:if>	
</xsl:template>





<xsl:template match="item:lecturer[@isLecturer='true']">
	<xsl:variable name="id" select="generate-id(.)" />
	<li>
		<a href="showLecturer/{@id}">
		<span class="name">
		<xsl:value-of select="concat(@lastName, ', ' , @firstName, ' ', @additionalNames)" />
		<xsl:if test="@isSuperuser = 'true'">
			<xsl:text> (</xsl:text>
			<xsl:value-of select="$mod-lang/superuser" />
			<xsl:text>)</xsl:text>
		</xsl:if></span>
		</a>
		<!-- <span class="time"><xsl:value-of select="@ctime" /></span> -->
	</li>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	
</xsl:template>

<xsl:template mode="style" match="vars:meta">
	.abc a {
		color: #000000;
		margin: 0 4px 10px 4px;
		font-weight: bold;
	}
</xsl:template>

<xsl:template mode="script" match="vars:meta">

</xsl:template>

<xsl:template mode="head" match="vars:meta">

</xsl:template>

</xsl:transform>
