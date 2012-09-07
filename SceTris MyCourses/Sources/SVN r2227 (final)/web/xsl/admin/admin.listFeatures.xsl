<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:form="http://technodrom.scravy.de/2010/form">

<xsl:import href="common.xsl" />
<xsl:import href="../lego/form-builder.xsl" />

<xsl:template mode="content" match="vars:meta">
	<xsl:if test="not(//form:editFeature)">
	<xsl:if test="not(//item:features)">
		<div class="ui-corner-all notice"><xsl:value-of select="$mod-lang/noFeatures" /></div>
	</xsl:if>
	<xsl:if test="//item:features">
		<xsl:if test="//form:featureChanged">
			<xsl:call-template name="form-builder">
			<xsl:with-param name="form">featureChanged</xsl:with-param>
		</xsl:call-template>
		</xsl:if>
		<ul class="features">
			<xsl:for-each select="//item:features">
			<li>
				<xsl:if test="$isSuperuser or $privileges/admin.deleteFeature">
					<input type="checkbox" /><xsl:text> </xsl:text>
				</xsl:if>
				<xsl:choose>
					<xsl:when test="$isSuperuser or $privileges/admin.editFeature">
						<a href="listFeatures/{@id}"><xsl:value-of select="@name" /></a>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="@name" />
					</xsl:otherwise>
				</xsl:choose>
				<span>
					<xsl:value-of select="@description" />
				</span>
			</li>
			</xsl:for-each>
		</ul>
	</xsl:if>
	<xsl:if test="$isSuperuser or $privileges/admin.createFeature">
		<h2 id="new"><xsl:value-of select="$mod-lang/createFeature" /></h2>
		<xsl:call-template name="form-builder">
			<xsl:with-param name="form">newFeature</xsl:with-param>
			<xsl:with-param name="hideOnSuccess" select="boolean(false)" />
		</xsl:call-template>
	</xsl:if>
	</xsl:if>
	<xsl:if test="//form:editFeature">
		<h2 id="edit"><xsl:value-of select="$mod-lang/editFeature" /></h2>
		<xsl:call-template name="form-builder">
			<xsl:with-param name="form">editFeature</xsl:with-param>
		</xsl:call-template>
	</xsl:if>
</xsl:template>

<xsl:template mode="path" match="vars:meta">
	<li>
		<xsl:value-of select="$lang/admin" />
		<xsl:text>: </xsl:text>
		<a href="{$module-path}/facilities"><xsl:value-of select="$lang/admin-facilities" /></a>
	</li>
	<xsl:if test="not(//form:editFeature)">
		<li>
			<xsl:value-of select="$mod-lang/listFeatures" />
		</li>
	</xsl:if>
	<xsl:if test="//form:editFeature">
		<li>
			<a href="{$module-path}/listFeatures">
			<xsl:value-of select="$mod-lang/listFeaturesShort" />
			</a>
		</li>
		<li>
			<xsl:value-of select="$mod-lang/editFeature" />
		</li>
	</xsl:if>
</xsl:template>

<xsl:template mode="style" match="vars:meta">
	.features li span {
		display: block;
		border: 1px solid gray;
		background: white;
		visibility: hidden;
		position: absolute;
		left: 400px;
		width: 200px;
	}
	
	.features li:hover span {
		visibility: visible;
	}
</xsl:template>

</xsl:transform>
