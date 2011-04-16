<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:en="http://technodrom.scravy.de/2010/relations/c" xmlns:dc="http://dublincore.org/documents/dcmi-namespace/">

<xsl:output omit-xml-declaration="yes" method="xml" indent="yes" />

<xsl:template match="/en:relations">
	<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html>&#xA;</xsl:text>
	<html>
		<head>
			<title>Entities &amp; Relationships</title>
			<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
			<style type="text/css">
			
body, p, legend, label {
	font: 12px "Trebuchet MS";
}

label {
	cursor: pointer;
}

legend {
	font-weight: bold;
	padding: 3px;
}

fieldset {
	border: 1px solid gray;
	padding: 0;
	margin: 10px;
}

fieldset > div {
	padding: 10px;
}

fieldset div:hover {
	background: #fffff0;
}
		
#corpus {
	width: 500px;
}

.brauchen-wa-nuescht:checked ~ fieldset {
	display: none;
}

#result {
	width: 250px;
	height: 400px;
	position: fixed;
	top: 10px;
	left: 520px;
	background: maroon;
	color: white;
	overflow: auto;
	border: none;
}

			</style>
			<script type="text/javascript">

function update() {
	document.getElementById("result").value = "";
<xsl:for-each select="en:entity | en:relationship">
<xsl:variable name="name" select="@name" />
	if (document.getElementById('<xsl:value-of select="$name" />_1').checked) {
		document.getElementById("result").value += "<xsl:value-of select="$name" />(\n";
<xsl:for-each select="en:attribute">
		if (document.getElementById('<xsl:value-of select="concat($name, '_', @name)" />_1').checked) {
					document.getElementById("result").value += "  <xsl:value-of select="@name" />\n";
		}
		if (document.getElementById('<xsl:value-of select="concat($name, '_', @name)" />_3').checked) {
					document.getElementById("result").value += "  !! <xsl:value-of select="@name" /> !!\n";
		}
</xsl:for-each>
		document.getElementById("result").value += ")\n";
	}
</xsl:for-each>
}

			</script>
		</head>
		<body onload="window.setInterval('update()', 500);">
			<h1>Entities &amp; Relationships</h1>
		<form>
			<div id="corpus">
			<xsl:apply-templates />
			</div>
			<textarea id="result"><xsl:text> </xsl:text>
			</textarea>
		</form>
		</body>
	</html>
</xsl:template>

<xsl:template match="en:entity | en:relationship">
<xsl:variable name="name" select="@name" />
<div>
<h2>Relation <xsl:value-of select="$name" /></h2>

<input type="radio" name="{$name}" id="{$name}_1" />
<label for="{$name}_1"> brauchen wir</label><br />

<input type="radio" name="{$name}" id="{$name}_2" class="brauchen-wa-nuescht" />
<label for="{$name}_2"> brauchen wir nicht</label><br />

<input type="radio" name="{$name}" id="{$name}_3" class="brauchen-wa-nuescht" />
<label for="{$name}_3"> weiß nicht wofür das irgendwer braucht, oder was das ist, oder wieso ich mich dafür interessieren sollte</label>

<fieldset><legend>Attribute</legend>
<xsl:for-each select="en:attribute">
<div>
<h3><xsl:value-of select="@name" /></h3>
<p>
	<input type="radio" name="{$name}_{@name}" id="{$name}_{@name}_1" />
	<label for="{$name}_{@name}_1"> brauchen wir</label><br />
	
	<input type="radio" name="{$name}_{@name}" id="{$name}_{@name}_2" />
	<label for="{$name}_{@name}_2"> brauchen wir nicht</label><br />
	
	<input type="radio" name="{$name}_{@name}" id="{$name}_{@name}_3" />
	<label for="{$name}_{@name}_3"> weiß nicht wofür das irgendwer braucht, oder was das ist, oder wieso ich mich dafür interessieren sollte</label>
</p></div>
</xsl:for-each>
</fieldset>
</div>

</xsl:template>


<xsl:template match="text()|@*" />

</xsl:transform>