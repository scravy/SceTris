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
	
	<xsl:template name="print-exception">
		<xsl:param name="formException" />
		<xsl:if test="$formException">
		<li>
			<xsl:value-of select="$formException/@type" />
			<xsl:text>: </xsl:text>
			<xsl:value-of select="$formException/@message" />
		</li>
			<xsl:call-template name="print-exception">
				<xsl:with-param name="formException" select="$formException/form:cause" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="form-builder">
		<xsl:param name="form" />
		<xsl:param name="mixin" />
		<xsl:param name="method">post</xsl:param>
		<xsl:param name="action"><xsl:value-of select="concat($module-path, '/', $action)" /></xsl:param>
		<xsl:param name="hideOnSuccess" select="boolean(1)" />
		<xsl:param name="content" />
		
		<xsl:param name="theForm" select="form:*[local-name() = $form]" />
		<xsl:variable name="name" select="local-name($theForm)" />
		
		<xsl:if test="not(not($mixin) and not($theForm))">
		
		<xsl:if test="$theForm/form:successfullyCommitted">
			<div class="ui-corner-all commit-success dismissable-message">
				<p>
					<xsl:choose>
						<xsl:when test="$mod-lang/*[local-name() = concat($name, '.success')]">
							<xsl:value-of select="$mod-lang/*[local-name() = concat($name, '.success')]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="concat($name, '.success')" />
						</xsl:otherwise>
					</xsl:choose>
				</p>
			</div>
		</xsl:if>
		<xsl:if test="$theForm/form:error">
			<div class="ui-corner-all commit-failure dismissable-message">
				<p>
					<xsl:choose>
						<xsl:when test="$mod-lang/*[local-name() = concat($name, '.failure')]">
							<xsl:value-of select="$mod-lang/*[local-name() = concat($name, '.failure')]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="concat($name, '.failure')" />
						</xsl:otherwise>
					</xsl:choose>
				</p>
				<ul>
					<xsl:call-template name="print-exception">
						<xsl:with-param name="formException" select="$theForm/form:error" />
					</xsl:call-template>
				</ul>
			</div>
		</xsl:if>
		
		<xsl:if test="not(not(not($theForm/form:successfullyCommitted)) and $hideOnSuccess)">
		<form method="{$method}" action="{$action}" name="{$name}" id="form.{$module}.{$action}.{$name}" class="lego-form">
		
		<xsl:choose>
		<xsl:when test="not($content)">
			
			<xsl:choose>
				<xsl:when test="$mixin">
					<xsl:apply-templates mode="form-builder" select="$theForm/* | $mixin">
						<xsl:sort select="number(@pos)" data-type="number" />
					</xsl:apply-templates>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates mode="form-builder" select="$theForm/*">
						<xsl:sort select="number(@pos)" data-type="number" />
					</xsl:apply-templates>
				</xsl:otherwise>
			</xsl:choose>
			
		</xsl:when>
		<xsl:otherwise>
			
			<xsl:copy-of select="$content" />
			
		</xsl:otherwise>
		</xsl:choose>
		
		<input type="hidden" name="~formType" value="{$theForm/@formType}" />
		<input type="hidden" name="~formName" value="{$name}" />
		
		<xsl:call-template name="input-submit">
			<xsl:with-param name="id" select="concat($name, '.button.submit')" />
		</xsl:call-template>
		
		<xsl:call-template name="debug">
			<xsl:with-param name="content">
			<p>
				<input type="radio" name="~type" value="xml" id="debug.type.xml">
					<xsl:if test="@type = 'xml'">
						<xsl:attribute name="checked">checked</xsl:attribute>
					</xsl:if>
				</input>
				<label for="debug.type.xml">XML</label>
				<xsl:text> </xsl:text>
				<input type="radio" name="~type" value="xhtml" id="debug.type.xhtml">
					<xsl:if test="@type = 'xhtml'">
						<xsl:attribute name="checked">checked</xsl:attribute>
					</xsl:if>
				</input>
				<label for="debug.type.xhtml">XHTML</label>
				<xsl:text> </xsl:text>
				<input type="radio" name="~type" value="html" id="debug.type.html">
					<xsl:if test="@type = 'html'">
						<xsl:attribute name="checked">checked</xsl:attribute>
					</xsl:if>
				</input>
				<label for="debug.type.html">HTML</label>
				<xsl:text> </xsl:text>
				<input type="radio" name="~type" value="xsl" id="debug.type.xsl">
					<xsl:if test="@type = 'xsl'">
						<xsl:attribute name="checked">checked</xsl:attribute>
					</xsl:if>
				</input>
				<label for="debug.type.xsl">XML+XSL</label>
			</p>
			</xsl:with-param>
		</xsl:call-template>
		
		
		</form>
		</xsl:if>
		</xsl:if>
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
	
	<xsl:template mode="form-builder" match="form:input-options">
		<xsl:call-template name="input-options">
			<xsl:with-param name="id" select="concat(local-name(parent::*), '.', @name) " />
			<xsl:with-param name="value" select="@value" />
			<xsl:with-param name="options" select="form:o" />
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
			<xsl:with-param name="id" select="concat(local-name(parent::*), '.', @name) " />
			<xsl:with-param name="value" select="@value" />
		</xsl:call-template>
	</xsl:template>
	
</xsl:transform>
