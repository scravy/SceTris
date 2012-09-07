<?xml version="1.0" encoding="UTF-8"?>
<!--
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
-->
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xhtml="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:item="http://technodrom.scravy.de/2010/item"
	xmlns:lang="http://technodrom.scravy.de/2010/lang"
	exclude-result-prefixes="vars item lang form xhtml xsl">

	<xsl:import href="bricks.xsl" />
	<xsl:import href="site-builder.xsl" />
	
	<xsl:template name="form-builder">
		<xsl:param name="form" />
		<xsl:param name="mixin" />
		<xsl:param name="method">post</xsl:param>
		<xsl:param name="action"><xsl:value-of select="concat($module-path, '/', $action)" /></xsl:param>
		
		<form method="{$method}" action="{$action}" name="{$form}" id="form.{$module}.{$action}.{$form}">
		
		<xsl:choose>
			<xsl:when test="$mixin">
				<xsl:apply-templates mode="form-builder" select="form:*[local-name() = $form]/* | $mixin">
					<xsl:sort select="number(@pos)" />
				</xsl:apply-templates>
			</xsl:when>
			<xsl:otherwise>
				<xsl:apply-templates mode="form-builder" select="form:*[local-name() = $form]/*">
					<xsl:sort select="number(@pos)" />
				</xsl:apply-templates>
			</xsl:otherwise>
		</xsl:choose>
		
		<xsl:call-template name="input-submit">
			<xsl:with-param name="id" select="concat($form, '.button.submit')" />
		</xsl:call-template>
		
		<xsl:call-template name="debug">
			<xsl:with-param name="content">
			<p>
				<input type="radio" name="~type" value="xml" id="debug.type.xml" />
				<label for="debug.type.xml">XML</label>
				
				<input type="radio" name="~type" value="xhtml" id="debug.type.xhtml" />
				<label for="debug.type.xhtml">XHTML</label>
				
				<input type="radio" name="~type" value="html" id="debug.type.html" checked="checked" />
				<label for="debug.type.html">HTML</label>
			</p>
			</xsl:with-param>
		</xsl:call-template>
		
		<input type="hidden" name="~formType" value="{form:*[local-name() = $form]/@formType}" />
		<input type="hidden" name="~formName" value="{$form}" />
		
		</form>
		
	</xsl:template>
		
	<xsl:template mode="form-builder" match="form:additional">
		<xsl:apply-templates mode="site-builder" />
	</xsl:template>
	
	<xsl:template mode="form-builder" match="form:input-alternatives">
		<xsl:call-template name="input-alternatives">
			<xsl:with-param name="id" select="concat(local-name(parent::*), '.', @name) " />
			<xsl:with-param name="options">
				<xsl:apply-templates mode="build-options" select="form:o">
					<xsl:with-param name="value">
						<xsl:value-of select="@value" />
					</xsl:with-param>
				</xsl:apply-templates>
			</xsl:with-param>
			<xsl:with-param name="error-type" select="@error-type" />
			<xsl:with-param name="error-code" select="@error-code" />
		</xsl:call-template>
	</xsl:template>
	
	<xsl:template mode="build-options" match="form:o">
		<xsl:param name="value" />
	
		<xsl:choose>
			<xsl:when test="(not(not(@k)) and string($value) = string(@k)) or (not(@k) and string($value) = string(@v))">
				<option selected="selected">
					<xsl:attribute name="value">
						<xsl:if test="@k">
							<xsl:value-of select="@k" />
						</xsl:if>
						<xsl:if test="not(@k)">
							<xsl:value-of select="position()" />
						</xsl:if>
					</xsl:attribute>
					<xsl:value-of select="@v" />
				</option>
			</xsl:when>
			<xsl:otherwise>
				<option>
					<xsl:attribute name="value">
						<xsl:if test="@k">
							<xsl:value-of select="@k" />
						</xsl:if>
						<xsl:if test="not(@k)">
							<xsl:value-of select="position()" />
						</xsl:if>
					</xsl:attribute>
					<xsl:value-of select="@v" />
				</option>
			</xsl:otherwise>
		</xsl:choose>
		
	</xsl:template>
	
	<xsl:template mode="form-builder" match="form:input-date">
		<xsl:call-template name="input-date">
			<xsl:with-param name="id" select="concat(local-name(parent::*), '.', @name) " />
			<xsl:with-param name="value" select="@value" />
			<xsl:with-param name="error-type" select="@error-type" />
			<xsl:with-param name="error-code" select="@error-code" />
		</xsl:call-template>
	</xsl:template>
	
	<xsl:template mode="form-builder" match="form:input-checkbox">
		<xsl:call-template name="input-checkbox">
			<xsl:with-param name="id" select="concat(local-name(parent::*), '.', @name) " />
			<xsl:with-param name="value" select="@value" />
			<xsl:with-param name="error-type" select="@error-type" />
			<xsl:with-param name="error-code" select="@error-code" />
		</xsl:call-template>
	</xsl:template>
	
	<xsl:template mode="form-builder" match="form:input-textarea">
		<xsl:call-template name="input-textarea">
			<xsl:with-param name="id" select="concat(local-name(parent::*), '.', @name) " />
			<xsl:with-param name="value" select="@value" />
			<xsl:with-param name="error-type" select="@error-type" />
			<xsl:with-param name="error-code" select="@error-code" />
		</xsl:call-template>
	</xsl:template>
	
	<xsl:template mode="form-builder" match="form:input-password">
		<xsl:call-template name="input-password">
			<xsl:with-param name="id" select="concat(local-name(parent::*), '.', @name) " />
			<xsl:with-param name="value" select="@value" />
			<xsl:with-param name="error-type" select="@error-type" />
			<xsl:with-param name="error-code" select="@error-code" />
		</xsl:call-template>
	</xsl:template>
	
	<xsl:template mode="form-builder" match="form:input-text">
		<xsl:call-template name="input-text">
			<xsl:with-param name="id" select="concat(local-name(parent::*), '.', @name) " />
			<xsl:with-param name="value" select="@value" />
			<xsl:with-param name="error-type" select="@error-type" />
			<xsl:with-param name="error-code" select="@error-code" />
		</xsl:call-template>
	</xsl:template>
	
	<xsl:template mode="form-builder" match="form:input-numeric">
		<xsl:call-template name="input-numeric">
			<xsl:with-param name="id" select="concat(local-name(parent::*), '.', @name) " />
			<xsl:with-param name="value" select="@value" />
			<xsl:with-param name="min" select="@min" />
			<xsl:with-param name="max" select="@max" />
			<xsl:with-param name="step" select="@step" />
			<xsl:with-param name="error-type" select="@error-type" />
			<xsl:with-param name="error-code" select="@error-code" />
		</xsl:call-template>
	</xsl:template>
	
	<xsl:template mode="form-builder" match="form:input-hidden">
		<xsl:call-template name="input-hidden">
			<xsl:with-param name="value" select="@value" />
		</xsl:call-template>
	</xsl:template>
	
</xsl:transform>
