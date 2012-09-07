<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:output method="xml" indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />

<xsl:template match="/">

	<xsl:if test="/vars:meta/@type = 'html'">
		<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
	</xsl:if>
	
	<html vars:foo="bar" item:foo="bar">
		<head>
			<title>Scetris - <xsl:apply-templates mode="title" /></title>
			<style type="text/css">/**/
				body {
					position: absolute;
					width: 100%;
					height: 100%;
					margin: 0;
					padding: 0;
					
					background: #ffffff;
				}
			
				.navBar {
					height: 18px;
					padding: 4px;
					background: rgba(190,220,170,.8);
					width: 100%;
					display: block;
					position: fixed;
				}
			
				#upperNav {
					top: 0;
					left: 0;
					border-bottom: 1px solid black;
				}
				
				#lowerNav {
					bottom: 0;
					left: 0;
					border-top: 1px solid black;
				}
				
				#contentFrame {
					margin: 24px 0;
					padding: 10px;
				}
				
				#loginMessage {
					display: block;
					text-align: right;
					position: absolute;
					top: 4px;
					right: 1em;
				}
				
				a {
					text-decoration: none;
					color: green;
				}
				
				#lowerNav a + a::before {
					content: ", ";
					color: black;
				}
				
				table, tr, td {
					border-collapse: collapse;
					border: 1px solid black;
				}
				
				td {
					padding: .25em;
				}
				
				table {
					margin: 1em;
				}
				
				
#theForm {
    width: 300px;
    /*margin: 10px auto;
    padding: 100px 10px;    */
    text-align: center;
    position: absolute;
    top: 100px;
    left: 50%;
    margin-left: -150px;
}

#debug {
	height: 10px;
	margin: 30px auto 10px;	
}
              
.logo {
	width: 100%;
}

.formField, .formControls {
	display: block;
	margin: 4px;
	width: 100%;
}

.formField label {
	display: block;
	width: 100px;
	float: left;
	font-family: "Baskerville", sans-serif;
	font-size: 20px;
	cursor: pointer;
	vertical-align: base;
	line-height: 30px;
	height: 30px;
}

.formField label:after {
	content: ": ";
}

.formField input[type=text], .formField input[type=password] {
	border: 2px solid black;
	font-family: "Baskerville", sans-serif;
	font-size: 20px;
	line-height: 22px;
	margin: 0;
	padding: 2px;
	width: 160px;
}

.formField input[type=text]:focus, .formField input[type=password]:focus {
	background: #f0fff0;
}

.formControls input[type=submit] {
	font-size: 20px;
	text-align: center;
	width: 100px;
	float: right;
}

.formControls + .kleingedrucktes {
	line-height: 26px;
	vertical-align: bottom;
}

.info {
	display: none;
	position: absolute;
}

*:focus + .info {
	display: block;
	left: 300px;
	width: 200px;
	padding: 3px;
	margin-top: -30px;
	
	color: white;
	background: rgb(0,0,0);
}


				
				<xsl:apply-templates mode="style" />
			/**/</style>
			<script type="text/javascript">/**/
				<xsl:apply-templates mode="script" />
			/**/</script>
			<xsl:apply-templates mode="head" />
		</head>
		
		<body>
		
			<div id="upperNav" class="navBar">
			<a href="/scetris/web/start/">Start</a> | <a href="/scetris/web/start/sitemap/">Sitemap</a>
			
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

