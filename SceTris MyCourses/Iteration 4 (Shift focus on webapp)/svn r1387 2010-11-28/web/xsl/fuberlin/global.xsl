<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	exclude-result-prefixes="vars item">

<xsl:output method="html" encoding="UTF-8" />

<xsl:variable name="context-path" select="/*/@context-path" />
<xsl:variable name="servlet-path" select="/*/@servlet-path" />
<xsl:variable name="module-path" select="/*/@module-path" />
<xsl:variable name="module" select="/*/@module" />

<xsl:variable name="lang" select="document(concat('i18n/global.', /vars:meta/@lang, '.xml'))/lang" />
<xsl:variable name="mod-lang" select="$lang" />
<xsl:variable name="type" select="/*/@type" />

<xsl:template match="/">

	<xsl:if test="/vars:meta/@type = 'html' or /vars:meta/@type = 'html5' ">
		<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
	</xsl:if>
	
	<html id="html">
		<head>
			<title>Scetris - <xsl:apply-templates mode="title" /></title>
			<link rel="stylesheet" type="text/css" href="{$context-path}/css/fuberlin.css" />
			<link rel="stylesheet" type="text/css" href="{$context-path}/css/ui/jquery-ui-1.8.6.custom.css" />
			<style type="text/css">
				<xsl:apply-templates mode="style" />
			</style>
			<script type="text/javascript">$contextPath = "<xsl:value-of select="$context-path" />"; $servletPath = "<xsl:value-of select="$servlet-path" />"; $modulePath = "<xsl:value-of select="$module-path" />"; $lang = "<xsl:value-of select="/*/@lang" />";</script>
			<script type="text/javascript" src="{$context-path}/js/jquery-1.4.2.min.js" />
			<script type="text/javascript" src="{$context-path}/js/jquery-ui-1.8.6.custom.min.js" />
			<script type="text/javascript" src="{$context-path}/js/i18n/jquery.ui.datepicker-{/*/@lang}.js" />
			<script type="text/javascript" src="{$context-path}/js/fuberlin.js" />
			<script type="text/javascript">
				<xsl:apply-templates mode="script" />
			</script>
			<xsl:apply-templates mode="head" />
		</head>
		
		<body id="body">
<div id="top-line" class="line">&#160;</div>

<div>
	<a id="header" href="http://www.fu-berlin.de/"><span>Freie Universität Berlin</span></a>
</div>

<div id="status">
	<p>
		<xsl:choose>
			<xsl:when test="/*/@logged-in = 'yes'">
				<xsl:value-of select="$lang/logged-in-as" />&#160;<strong>Julian Fleischer</strong>&#160;<em>(scravy)</em>&#160;<a href="{$servlet-path}/~logout" class="logout-link">&#x2192; <xsl:value-of select="$lang/do-logout" /></a>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$lang/not-logged-in" />&#160;<a href="{$servlet-path}/login">&#x2192; <xsl:value-of select="$lang/do-login" /></a>
			</xsl:otherwise>
		</xsl:choose>
	</p>
</div>

<div id="content-container">
	<div id="content">
		<div id="path">
			<a href="{$servlet-path}"><xsl:value-of select="$lang/list-of-lectures" /></a>
			<xsl:text> </xsl:text>
			<xsl:apply-templates mode="path" />
		</div>
		<xsl:apply-templates mode="content" />
	</div>
</div>

<div id="menu">
	<xsl:apply-templates mode="menu" />
	<h6><xsl:value-of select="$lang/alternate-versions" /></h6>
	<ul>
		<li><a href="?~type=html">HTML 4.01</a></li>
		<li><a href="?~type=html5">HTML 5</a></li>
		<li><a href="?~type=xhtml">XHTML</a></li>
		<li><a href="?~type=xsl">XML+XSL</a></li>
		<li><a href="?~type=pdf">PDF</a></li>
		<li><a href="?~type=latex">LaTeX</a></li>
		<li><a href="?~type=xml">text/xml</a></li>
		<li><a href="?~type=plain">text/plain</a></li>
		<li><a href="?~type=json">JSON</a></li>
	</ul>
	
</div>

<div id="ident">
	<a href="{$servlet-path}" id="logo" />
	<div id="pic" />
	<form action="{$servlet-path}/search/" method="get" id="search">
		<label for="search-field"><xsl:value-of select="$lang/search" />:</label>
		<xsl:text>&#160;</xsl:text>
		<input type="text" id="search-field" />
		<xsl:text>&#160;</xsl:text>
		<input type="submit" value="&#x2192;" />
	</form>
</div>

<div id="search-results">
	<xsl:apply-templates mode="search_results" />
</div>	
	
<div id="subnavigation"><div>
	<ul>
		<li><a href="{$servlet-path}/lectures"><xsl:value-of select="$lang/lectures" /></a>
			<ul>
				<li><a href="{$servlet-path}/lectures/my-recommendations">Für mein Fachsemester empfohlene Kurse</a></li>
				<li><a href="{$servlet-path}/lectures/my-institutes">Kurse an meinen Instituten</a></li>
				<li><a href="{$servlet-path}/lectures/">Alle Kurse (nach Institut)</a></li>
			</ul>
		</li>
		<li><a href="{$servlet-path}/my/courses/"><xsl:value-of select="$lang/my-courses" /></a>
			<ul>
				<li><a href="#m1">Modelchecking</a></li>
				<li><a href="#m2">Softwareprojekt: SCORE</a></li>
				<li><a href="#m3">Übersetzberbau</a></li>
				<li><a href="#m4">Technische Informatik I</a></li>
				<li><a href="#m5">Algorithmen und Programmieren V</a></li>
				<li><a href="#m6">Einführung in die theoretische Philosophie</a></li>
				<li><a href="#m7">Arbeits- und Lebensmethodik</a></li>
				<li><a href="#m8">Mathematik für Informatiker III: Lineare Algebra</a></li>
			</ul>
		</li>
		<li><a href="{$servlet-path}/my/schedule"><xsl:value-of select="$lang/my-schedule" /></a></li>
		<li><a href="{$servlet-path}/my/grades"><xsl:value-of select="$lang/my-grades" /></a></li>
		<li><a href="{$servlet-path}/administration"><xsl:value-of select="$lang/administration" /></a>
			<ul>
				<li><a href="{$servlet-path}/resources"><xsl:value-of select="$lang/facilities" /></a></li>
				<li><a href="{$servlet-path}/facilities"><xsl:value-of select="$lang/facilities" />-deprecated</a></li>
				<li><a href="{$servlet-path}/users">Benutzer &amp; Rollen</a></li>
				<li><a href="{$servlet-path}/coursemgmt">Semester &amp; Kurse</a></li>
				<li><a href="{$servlet-path}/imex"><xsl:value-of select="$lang/imex" /></a></li>
				<li><a href="{$servlet-path}/configuration"><xsl:value-of select="$lang/sysconfig" /></a></li>
			</ul>
		</li>
		<li><a href="{$servlet-path}/settings"><xsl:value-of select="$lang/preferences" /></a></li>
	</ul>
</div></div>

<div id="navigation">
	<ul>
		<li><a href="{$servlet-path}/"><xsl:value-of select="$lang/home" /></a></li>
		<xsl:if test="/vars:meta/@lang != 'en'">
		<li><a href="?~lang=en">English</a></li>
		</xsl:if>
		<xsl:if test="not(/vars:meta/@lang != 'en')">
		<li><a href="?~lang=de">Deutsch</a></li>
		</xsl:if>
		<li><a href="{$servlet-path}/people"><xsl:value-of select="$lang/people" /></a></li>
		<li><a href="{$servlet-path}/index"><xsl:value-of select="$lang/index" /></a></li>
		<li><a href="{$servlet-path}/help"><xsl:value-of select="$lang/help" /></a></li>
	</ul>
</div>

<div id="bottom-line" class="line">
	<div class="bottom-right">Stand: 2010-11-10</div>
	<div class="bottom-left">&#xA9; Freie Universität Berlin – Projekt Scetris</div>
</div>

		</body>
	</html>
			
</xsl:template>

<xsl:template match="text()|@*" mode="search_results">
	<div id="lectures">
	<h6><xsl:value-of select="$lang/courses" /></h6>
	<ul id="lectures-results">
	<xsl:text> </xsl:text>
	</ul>
	</div>
	<div id="lecturers">
	<h6><xsl:value-of select="$lang/lecturers" /></h6>
	<ul id="lecturers-results">
	<xsl:text> </xsl:text>
	</ul>
	</div>
</xsl:template>


<xsl:template match="text()|@*" />
<xsl:template match="text()|@*" mode="title" />
<xsl:template match="text()|@*" mode="head" />
<xsl:template match="text()|@*" mode="style" />
<xsl:template match="text()|@*" mode="script" />
<xsl:template match="text()|@*" mode="menu" />
<xsl:template match="text()|@*" mode="path" />
<xsl:template match="text()|@*" mode="content" />

</xsl:transform>

