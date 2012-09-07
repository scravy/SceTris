<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:vars="http://technodrom.scravy.de/2010/data" xmlns:item="http://technodrom.scravy.de/2010/item">

<xsl:import href="../common/global.xsl" />
<xsl:import href="../lego/bricks.xsl" />

<xsl:template mode="title" match="vars:meta">
	<xsl:value-of select="$lang/imex" />
</xsl:template>

<xsl:template mode="content" match="vars:meta">
	<h1><xsl:value-of select="$lang/imex" /></h1>

	<div class="fancy-tabs">
		<ul>
			<li><a href="#export"><xsl:value-of select="$lang/export" /></a></li>
			<li><a href="#import"><xsl:value-of select="$lang/import" /></a></li>
			<li><a href="#kvv-import">KVV-Import</a></li>
		</ul>
		<div id="export">
		<h3><xsl:value-of select="$lang/export" /></h3>
		<ul>
			<li><a href="{$module-path}/export/~all"><xsl:value-of select="$lang/export-all" /></a></li>
		</ul>
		</div>
		
		<div id="import">
		<h3><xsl:value-of select="$lang/import" /></h3>
		<form method="post" action="{$module-path}/import">
			<textarea id="import-data" class="input-text" />
			<xsl:call-template name="input-submit">
				<xsl:with-param name="id" select="'import-submit'" />
			</xsl:call-template>
		</form>
		</div>
		
		<div id="kvv-import">
		<h3>KVV-Import</h3>
		<p>...</p>
		</div>
	</div>
	
</xsl:template>

<xsl:template mode="style" match="vars:meta">
#import-data { width: 100%; height: 15em; }
</xsl:template>

</xsl:transform>
