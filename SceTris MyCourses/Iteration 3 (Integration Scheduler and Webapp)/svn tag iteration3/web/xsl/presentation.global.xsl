<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:param name="context-path" select="/*/@context-path" />
<xsl:param name="servlet-path" select="/*/@servlet-path" />
<xsl:param name="module-path" select="/*/@module-path" />
<xsl:param name="module" select="/*/@module" />

<xsl:template match="/">

	<xsl:if test="/vars:meta/@type = 'html'">
		<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
	</xsl:if>	
	
	<html vars:foo="bar" item:foo="bar">
		<head>
			<title>Scetris - <xsl:apply-templates mode="title" /></title>
			<link rel="stylesheet" type="text/css" href="{$context-path}/css/main_it3.css" />
			<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
			<style type="text/css">/**/
				<xsl:apply-templates mode="style" />
			/**/</style>
			<script type="text/javascript">/**/
				<xsl:apply-templates mode="script" />
			/**/</script>
			<xsl:apply-templates mode="head" />
		</head>
		
		<body>
		
			<div id="upperNav" class="navBar">
				<a href="{/vars:meta/@servlet-path}/start">Start</a> 
				| <a href="{/vars:meta/@servlet-path}/ressources">Ressources</a>
				| <a href="{/vars:meta/@servlet-path}/users">Usermanagement</a>
				| <a href="{/vars:meta/@servlet-path}/configuration">Systemconfiguration</a>
				| <a href="{/vars:meta/@servlet-path}/start/sitemap">Sitemäp</a>
				
				<span id="loginMessage">
				<xsl:if test="/vars:meta/vars:user/item:user/@superuser = 'true'">
					You're a superuser.
					(<a href="{/vars:meta/@servlet-path}/~logout/">logout</a>)</xsl:if>
				<xsl:if test="not(/vars:meta/vars:user/item:user/@superuser = 'true')">
					<xsl:if test="/vars:meta/@logged-in = 'yes'">
						You are logged in as <xsl:value-of select="/vars:meta/vars:user/item:user/@loginName" />.
						(<a href="{/vars:meta/@servlet-path}/~logout/">logout</a>)
					</xsl:if>
					<xsl:if test="not(/vars:meta/@logged-in = 'yes')">
						You are not logged in.
						(<a href="{/vars:meta/@context-path}/">go to login</a>)
					</xsl:if>
				</xsl:if>
				</span>
			</div>
			
			<div id="contentFrame">
					<div id="contentBody">
				
					<xsl:apply-templates mode="content" />
				
					</div>
			</div>
			
			<div id="lowerNav" class="navBar">
				<a href="?~type=html">html</a>
				<a href="?~type=xhtml">xhtml</a>
				<a href="?~type=xsl">xml+xsl</a>
				<a href="?~type=xml">xml</a>
				<a href="?~type=plain">plain</a>
			</div>
			
		</body>
	</html>
	
</xsl:template>

<xsl:template match="text()|@*" />
<xsl:template match="text()|@*" mode="title" />
<xsl:template match="text()|@*" mode="head" />
<xsl:template match="text()|@*" mode="style" />
<xsl:template match="text()|@*" mode="script" />
<xsl:template match="text()|@*" mode="content" />

</xsl:transform>

