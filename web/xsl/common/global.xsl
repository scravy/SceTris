<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	exclude-result-prefixes="vars item">

<!--
	Output declaration. Please don’t change anything here.
	For debugging purposed, “indent” may be turned to “yes”,
	however we recommend using an inspector tool like
	Webkits Web-Inspector or Firefox’ Firebug. 
 -->
<xsl:output method="html" encoding="UTF-8" indent="no" />

<!--
	Some initial variable declarations for convenience. 
 -->
<xsl:variable name="context-path" select="/*/@context-path" />
<xsl:variable name="servlet-path" select="/*/@servlet-path" />
<xsl:variable name="module-path" select="/*/@module-path" />
<xsl:variable name="module" select="/*/@module" />
<xsl:variable name="action" select="/*/@action" />
<xsl:variable name="query-string" select="/*/@query-string" />
<xsl:variable name="action-path" select="concat($module-path, '/', $action)" />
<xsl:variable name="type" select="/*/@type" />
<xsl:variable name="style" select="/*/@style" />
<xsl:variable name="date" />
<xsl:variable name="logged-in" select="/*/@logged-in = 'yes'" />
<xsl:variable name="privileges" select="/vars:meta/vars:privileges" />
<xsl:variable name="user" select="/vars:meta/vars:user/item:user" />
<xsl:variable name="isSuperuser" select="/vars:meta/vars:user/item:user/@isSuperuser = 'true'" />
<xsl:variable name="lang-code" select="/vars:meta/@lang" />

<!--
	The following variables contain the language-related nodes.
	Override $mod-lang in your own module, to include specific
	lang-strings. 
 -->
<xsl:variable name="lang" select="document(concat('../i18n/global.', $lang-code, '.xml'))/lang" />
<xsl:variable name="mod-lang" select="$lang" />

<!--
	The main template which matches the root-node.
	It just outlines the rough structure of elements in the
	result document. The individual sections live within
	their own template which is called by name. 
 -->
<xsl:template match="/">
	<xsl:if test="/vars:meta/@type = 'html' or /vars:meta/@type = 'html5' ">
		<!--
			This workaround is needed to make sure to include the
			proper HTML-Doctype (iff type is html or html5) 
		 -->
		<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
	</xsl:if>
	<html lang="{$lang-code}" xml:lang="{$lang-code}">
		<head>
			<title>Scetris - <xsl:apply-templates mode="title" /></title>
			<xsl:call-template name="global-head" />
		</head>
		<body class="mod-{$module} act-{$action}">

			<div id="global-jump-container" class="noprint noscreen">
				<p><xsl:value-of select="$lang/global-jump" /></p>
				<ul id="global-jump">
					<li><a href="#global-main"><xsl:value-of select="$lang/global-main" /></a></li>
					<li><a href="#global-navbar"><xsl:value-of select="$lang/global-navbar" /></a></li>
					<li><a href="#global-menu"><xsl:value-of select="$lang/global-menu" /></a></li>
					<li><a href="#global-meta"><xsl:value-of select="$lang/global-meta" /></a></li>
				</ul>
			</div>

			<!--
				To know whether you’re logged in or not is a very
				critical information, which is therefore at the top
				of the page. 
			 -->
			<xsl:call-template name="global-status" />
			
			<div id="global-corpus-container">
				<div id="global-corpus">
					<xsl:call-template name="global-menu" />
					<xsl:call-template name="global-meta" />
					<xsl:call-template name="global-main" />
					<div class="terminator" />
				</div>
			</div>
			
			<div id="global-header-container">
				<div id="global-header">
					<div id="top-line" class="line"><xsl:text> </xsl:text></div>
					<a href="http://www.fu-berlin.de/" id="logo-fu"><span>Freie Universität Berlin</span></a>
					<div id="ident">
						<a id="logo-scetris" href="{$servlet-path}"><span>Scetris</span></a>
						<div id="ident-pic"><xsl:text> </xsl:text></div>
					</div>
					<xsl:call-template name="global-navbar" />
					<xsl:call-template name="global-topnav" />
				</div>
			</div>
			
			<div id="global-footer">
				<div id="bottom-line" class="line"><xsl:text> </xsl:text></div>
				<div id="bottom-right">
					<xsl:text>Stand: </xsl:text>
					<xsl:choose>
						<xsl:when test="$date"><xsl:value-of select="$date" /></xsl:when>
						<xsl:otherwise>2010-12-27</xsl:otherwise>
					</xsl:choose>
				</div>
				<div id="bottom-left">&#xA9; <xsl:value-of select="$lang/footer-text" /></div>
			</div>
			<xsl:comment>
				<xsl:text>[if lt IE 8]>&lt;div style="color:white;font-weight:bold;padding:5px;text-align:center;background:#cc0000;z-index:15;position:absolute;top:0;left:0;width:100%;filter:alpha(opcacity=0.8);">&lt;p></xsl:text>
				<xsl:value-of select="$lang/bad-browser/message" />
				<xsl:text> (</xsl:text>
				<xsl:for-each select="$lang/bad-browser/alternative">
					<xsl:text>&lt;a href="</xsl:text>
					<xsl:value-of select="@link" />
					<xsl:text>"></xsl:text>
					<xsl:value-of select="text()" />
					<xsl:text>&lt;/a></xsl:text>
					<xsl:if test="position() &lt; last()">, </xsl:if>
				</xsl:for-each>
				<xsl:text>)!&lt;/p>&lt;/div>&lt;![endif]</xsl:text>
			</xsl:comment>
		</body>
	</html>	
</xsl:template>

<xsl:template name="global-head">
	<!-- <link rel="stylesheet" type="text/css" href="{$context-path}/css/{$style}" media="screen" /> -->
	<xsl:comment>
		<xsl:text>[if IE 6]>&lt;style type="text/css">@import url("</xsl:text>
		<xsl:value-of select="$context-path" />
		<xsl:text>/css/scetris-ie6.css");&lt;/style>&lt;![endif]</xsl:text>
	</xsl:comment>
	<xsl:comment>
		<xsl:text>[if IE 7]>&lt;style type="text/css">@import url("</xsl:text>
		<xsl:value-of select="$context-path" />
		<xsl:text>/css/scetris-ie7.css");&lt;/style>&lt;![endif]</xsl:text>
	</xsl:comment>
	<style type="text/css">@import "<xsl:value-of select="$context-path" />/css/scetris.css" screen; @media screen{.noscreen{display:none;}} @media print{.noprint{display:none;}}
<xsl:apply-templates mode="style" /></style>
	<link rel="stylesheet" type="text/css" href="{$context-path}/css/scetris-print.css" media="print" />
	<script type="text/javascript">
	$contextPath="<xsl:value-of select="$context-path" />";$servletPath="<xsl:value-of select="$servlet-path" />";$modulePath="<xsl:value-of select="$module-path" />";$lang="<xsl:value-of select="/*/@lang" />";
	</script>
	<script type="text/javascript" src="{$context-path}/js/jquery-1.4.2.min.js" />
	<script type="text/javascript" src="{$context-path}/js/jquery-ui-1.8.6.custom.min.js" />
	<script type="text/javascript" src="{$context-path}/js/i18n/jquery.ui.datepicker-{/*/@lang}.js" />
	<script type="text/javascript" src="{$context-path}/js/fuberlin.js" />
	<script type="text/javascript">
		<xsl:apply-templates mode="script" />
	</script>
	<xsl:apply-templates mode="head" />
</xsl:template>

<xsl:template name="global-meta">
<div id="global-meta-container" class="content">
	<div id="global-meta">
		<xsl:apply-templates mode="meta" />
	</div>
</div>
</xsl:template>

<xsl:template name="global-menu">
<div id="global-menu-container" class="noprint nav">
	<ul id="global-menu">
		<xsl:apply-templates mode="menu" />
		<li><span class="heading"><xsl:value-of select="$lang/alternate-versions" /></span>
			<ul>
				<xsl:if test="not($type = 'html')">
					<li><a href="?~type=html&amp;{$query-string}">HTML</a></li>
				</xsl:if>
				<xsl:if test="$type = 'html'">
					<li class="emphasized">HTML</li>
				</xsl:if>
				
				<xsl:if test="not($type = 'xhtml')">
					<li><a href="?~type=xhtml&amp;{$query-string}">XHTML</a></li>
				</xsl:if>
				<xsl:if test="$type = 'xhtml'">
					<li class="emphasized">XHTML</li>
				</xsl:if>
				
				<xsl:if test="not($type = 'xsl')">
					<li><a href="?~type=xsl&amp;{$query-string}">XML+XSL</a></li>
				</xsl:if>
				<xsl:if test="$type = 'xsl'">
					<li class="emphasized">XML+XSL</li>
				</xsl:if>
				
				<!-- <li><a href="?~type=pdf">PDF</a></li> -->
				<li><a href="?~type=xml">text/xml</a></li>
				<li><a href="?~type=plain">text/plain</a></li>
			</ul>
		</li>
	</ul>
</div>
</xsl:template>

<xsl:template name="global-main">
	<div id="global-main-container">
		<div id="global-main">
			<div id="global-breadcrumb-container">
				<ul id="global-breadcrumb">
					<li>
						<a href="{$servlet-path}"><xsl:value-of select="$lang/list-of-lectures" /></a>
					</li>
					<xsl:text> </xsl:text>
					<xsl:apply-templates mode="path" />
				</ul>
			</div>
			
			<div id="global-content-container">
				<div id="global-content" class="content">
					<xsl:apply-templates mode="content" />
				</div>
			</div>
		</div>
	</div>
</xsl:template>

<xsl:template name="global-status">
<div id="global-status-container" class="noprint">
	<p id="global-status">
		<xsl:choose>
			<xsl:when test="$logged-in">
				<xsl:value-of select="$lang/logged-in-as" />&#160;<strong><xsl:value-of select="concat(/*/vars:user/*/@firstName,' ',/*/vars:user/*/@lastName)"/></strong>&#160;<em>(<xsl:value-of select="/*/vars:user/*/@loginName"/>)</em>&#160;<a href="{$servlet-path}/~logout" class="logout-link">&#x2192; <xsl:value-of select="$lang/do-logout" /></a>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$lang/not-logged-in" />&#160;<a href="{$servlet-path}/login">&#x2192; <xsl:value-of select="$lang/do-login" /></a>
			</xsl:otherwise>
		</xsl:choose>
	</p>
</div>
</xsl:template>

<xsl:template name="global-navbar">
<div id="global-navbar-container" class="noprint">
	<div id="global-navbar">
		<ul>
			<li><a href="{$servlet-path}/lectures"><xsl:value-of select="$lang/lectures" /></a>
				<xsl:if test="$logged-in">
					<ul>
						<!--<li><a href="{$servlet-path}/lectures/myRecommendations">TRANSLATE: Für mein Fachsemester empfohlene Kurse</a></li>-->
						<li><a href="{$servlet-path}/lectures/myInstitute">Courses for my department</a></li>
						<li><a href="{$servlet-path}/lectures/">All Courses</a></li>
					</ul>
				</xsl:if>
			</li>
		<xsl:if test="$logged-in">
			<li><a href="{$servlet-path}/my/courses/"><xsl:value-of select="$lang/my-courses" /></a>
				<ul>
					<xsl:for-each select="//vars:mycourses/item:mycourses">
						<li><a href="{$servlet-path}/lectures/showCourseInstance/{@courseInstance}">
							<xsl:value-of select="vars:courseInstance/item:courseInstance/vars:instanceOf/item:instanceOf/@name"/></a></li>	
					</xsl:for-each>
				</ul>
			</li>
			<li><a href="{$servlet-path}/my/schedule"><xsl:value-of select="$lang/my-schedule" /></a></li>
		
		</xsl:if>
		<xsl:if test="$isSuperuser or $privileges/admin.facilities or $privileges/admin.users or $privileges/admin.courses or $privileges/admin.imex or $privileges/admin.sysconfig">
			<li><a href="{$servlet-path}/admin"><xsl:value-of select="$lang/admin" /></a>
				<ul>
					<xsl:if test="$isSuperuser or $privileges/admin.facilities">
						<li><a href="{$servlet-path}/admin/facilities"><xsl:value-of select="$lang/admin-facilities" /></a></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.users">
						<li><a href="{$servlet-path}/admin/users"><xsl:value-of select="$lang/admin-users" /></a></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.courses">
						<li><a href="{$servlet-path}/admin/courses"><xsl:value-of select="$lang/admin-courses" /></a></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.imex">
						<li><a href="{$servlet-path}/admin/imex"><xsl:value-of select="$lang/admin-imex" /></a></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.sysconfig">
						<li><a href="{$servlet-path}/admin/sysconfig"><xsl:value-of select="$lang/admin-sysconfig" /></a></li>
					</xsl:if>
					<xsl:if test="$isSuperuser or $privileges/admin.schedulerPanel">
						<li><a href="{$servlet-path}/admin/schedulerPanel"><xsl:value-of select="$lang/admin-schedulerPanel" /></a></li>
					</xsl:if>
				</ul>
			</li>
		</xsl:if>
		<xsl:if test="$logged-in">
			<li><a href="{$servlet-path}/preferences"><xsl:value-of select="$lang/preferences" /></a></li>
		</xsl:if>
		</ul>
	</div>
</div>
</xsl:template>

<xsl:template name="global-topnav">
<div id="global-topnav-container" class="nav">
	<ul id="global-topnav">
		<li><a href="{$servlet-path}/"><xsl:value-of select="$lang/home" /></a></li>
		<xsl:if test="/vars:meta/@lang != 'en'">
		<li><a href="?~lang=en&amp;{$query-string}">English</a></li>
		</xsl:if>
		<xsl:if test="not(/vars:meta/@lang != 'en')">
		<li><a href="?~lang=de&amp;{$query-string}">Deutsch</a></li>
		</xsl:if>
		<li><a href="{$servlet-path}/people"><xsl:value-of select="$lang/people" /></a></li>
	<!--	<li><a href="{$servlet-path}/index"><xsl:value-of select="$lang/index" /></a></li>	-->
		<li><a href="{$servlet-path}/showcase">Showcase</a></li>
		<li><a href="{$servlet-path}/help"><xsl:value-of select="$lang/help" /></a></li>
	</ul>
</div>
</xsl:template>


<xsl:template match="text()|@*" />
<xsl:template match="text()|@*" mode="title" />
<xsl:template match="text()|@*" mode="head" />
<xsl:template match="text()|@*" mode="style" />
<xsl:template match="text()|@*" mode="script" />
<xsl:template match="text()|@*" mode="menu" />
<xsl:template match="text()|@*" mode="meta" />
<xsl:template match="text()|@*" mode="path" />
<xsl:template match="text()|@*" mode="content" />

</xsl:transform>

