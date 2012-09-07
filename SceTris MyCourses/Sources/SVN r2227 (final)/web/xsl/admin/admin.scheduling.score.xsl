<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<!-- NOTE THAT THIS TEMPLATE IS DESIGNED TO RUN IN AN IFRAME ON SCHEDULER-PANEL -->

<xsl:output method="html" encoding="UTF-8" indent="no" />
<!--
	Some initial variable declarations for convenience. 
 -->
<xsl:variable name="context-path" select="/*/@context-path" />
<xsl:variable name="servlet-path" select="/*/@servlet-path" />
<xsl:variable name="module-path" select="/*/@module-path" />
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
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="refresh" content="3"/>
			<title>Scetris - SchedulerPanelScore</title>
			<style type="text/css">@import "<xsl:value-of select="$context-path" />/css/scetris.css" screen; @media screen{.noscreen{display:none;}} @media print{.noprint{display:none;}}
			</style>
			<style type="text/css">
				html, body {
					padding: 0;
					margin: 0;	
				}
			</style>
		</head>
		<body>
			<xsl:if test="//vars:status">
				<xsl:if test="//vars:status/text() = 'busy'">
					busy
				</xsl:if>
				<xsl:if test="//vars:status/text() = 'ready'">
					<xsl:if test="//vars:lastScheduled/item:lastScheduled/@id &gt; 0">
						<h2>Scheduling ended</h2>
						<p>Click <b><a href="schedulerPanel/{//vars:lastScheduled/item:lastScheduled/@id}" target="_parent">here</a></b> to view results</p>
					</xsl:if>
					<!-- 
					<script type="text/javascript">
						top.location.reload();
					</script>
					-->
				</xsl:if>
			</xsl:if>
			
			<xsl:if test="//vars:scheduleScore">
				<xsl:variable name="ss" select="//vars:scheduleScore/item:scheduleScore"/>
				<xsl:variable name="width" select="550"/>
				
				<p>
					<xsl:variable name="hardC" select="$ss/@numberOfHardConstraintsSatisfied"/>
					<xsl:variable name="hardCSum" select="$ss/@numberOfHardConstraints"/>
					<xsl:variable name="softC" select="$ss/@numberOfSoftConstraintsSatisfied"/>
					<xsl:variable name="softCSum" select="$ss/@numberOfSoftConstraints"/>
					<xsl:variable name="hard_done" select="(($width * $hardC) div $hardCSum)" />
					<xsl:variable name="soft_done" select="(($width * $softC) div $softCSum)" />
	
	
					<xsl:variable name="hardCx" select="$ss/@sumHardConstraintsSatisfied"/>
					<xsl:variable name="hardCSumx" select="$ss/@sumHardConstraints"/>
					<xsl:variable name="softCx" select="$ss/@sumSoftConstraintsSatisfied"/>
					<xsl:variable name="softCSumx" select="$ss/@sumSoftConstraints"/>
					<xsl:variable name="hardx_done" select="(($width * $hardCx) div $hardCSumx)" />
					<xsl:variable name="softx_done" select="(($width * $softCx) div $softCSumx)" />
					
						
					<xsl:if test="$hardCSum>0">
						<p>
						hard constraints:
						<xsl:value-of select="$hardC" />
						of
						<xsl:value-of select="$hardCSum" />
						<div style="background-image: url(http://userpage.fu-berlin.de/andrez/ladebalken.gif); background-color: #D1D1D1; height:20px; width: {$width - $hard_done}px; border: 1px solid #D1D1D1; border-left: {$hard_done}px solid #D1D1D1;"/>
					
						</p>
					</xsl:if>
					<xsl:if test="$softCSum>0">
						<p>
						soft constraints:
						<xsl:value-of select="$softC" />
						of
						<xsl:value-of select="$softCSum" />
						<div style="background-image: url(http://userpage.fu-berlin.de/andrez/ladebalken.gif); background-color: #D1D1D1; height:20px; width: {$width - $soft_done}px; border: 1px solid #D1D1D1; border-left: {$soft_done}px solid #D1D1D1;"/>
						</p>
					</xsl:if>
					
					<xsl:if test="$hardCSumx>0">
					<hr/>
						<p>
						hard constraints:
						<xsl:value-of select="$hardCx" />
						of
						<xsl:value-of select="$hardCSumx" />
						<div style="background-image: url(http://userpage.fu-berlin.de/andrez/ladebalken.gif); background-color: #D1D1D1; height:20px; width: {$width - $hardx_done}px; border: 1px solid #D1D1D1; border-left: {$hardx_done}px solid #D1D1D1;"/>
						</p>
					</xsl:if>
					<xsl:if test="$softCSumx>0">
						<p>				
						soft constraints:
						<xsl:value-of select="$softCx" />
						of
						<xsl:value-of select="$softCSumx" />
						<div style="background-image: url(http://userpage.fu-berlin.de/andrez/ladebalken.gif); background-color: #D1D1D1; height:20px; width: {$width - $softx_done}px; border: 1px solid #D1D1D1; border-left: {$softx_done}px solid #D1D1D1;"/>
						<br style="clear: both;"/>
						</p>			
					</xsl:if>
				</p>	
					
			</xsl:if>

		</body>
	</html>	
</xsl:template>


</xsl:transform>