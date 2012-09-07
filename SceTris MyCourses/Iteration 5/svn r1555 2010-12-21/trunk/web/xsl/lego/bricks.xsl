<?xml version="1.0" encoding="UTF-8"?>
<!--
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
-->
<xsl:transform version="1.0"
	xmlns="http://www.w3.org/1999/xhtml"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:vars="http://technodrom.scravy.de/2010/data"
	xmlns:form="http://technodrom.scravy.de/2010/form"
	xmlns:item="http://technodrom.scravy.de/2010/item">

	<xsl:import href="common.xsl" />
	
	<xsl:template name="input-checkbox">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:choose>
				<xsl:when test="$mod-lang/labels/*[local-name() = $id]">
					<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$name" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />
		
		<xsl:param name="error-type" />
		<xsl:param name="error-code" />
		<xsl:param name="error-message">
			<xsl:choose>
				<xsl:when test="$error-type = 'InvalidSyntaxException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-syntax/*[local-name() = $id]">
							<xsl:value-of select="$mod-lang/error-syntax/*[local-name() = $id]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid syntax</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$error-type = 'ValidationException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]">
							<xsl:value-of select="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:param>
		
		<xsl:param name="class">
			<xsl:text>formField</xsl:text>
			<xsl:choose>
				<xsl:when test="$error-type = 'InvalidSyntaxException'">
					<xsl:text> errorneous invalid-syntax</xsl:text>
				</xsl:when>
				<xsl:when test="$error-type = 'ValidationException'">
					<xsl:text> errorneous invalid</xsl:text>
				</xsl:when>
			</xsl:choose>
		</xsl:param>
		
		<p class="{$class}">
			<label for="{$id}" id="label.{$id}">
				<xsl:value-of select="$label" />
			</label>			
			<input type="checkbox" id="{$id}" name="{$name}" class="input-checkbox" value="{$value}">
				<xsl:if test="$value = 'true'">
					<xsl:attribute name="checked">checked</xsl:attribute>
				</xsl:if>
			</input>
		</p>
	</xsl:template>
	
	<xsl:template name="input-password">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:choose>
				<xsl:when test="$mod-lang/labels/*[local-name() = $id]">
					<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$name" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />
		<xsl:param name="maxlength" />

		<xsl:param name="error-type" />
		<xsl:param name="error-code" />
		<xsl:param name="error-message">
			<xsl:choose>
				<xsl:when test="$error-type = 'InvalidSyntaxException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-syntax/*[local-name() = $id]">
							<xsl:value-of select="$mod-lang/error-syntax/*[local-name() = $id]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid syntax</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$error-type = 'ValidationException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]">
							<xsl:value-of select="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:param>
		<p class="formField">
			<label for="{$id}">
				<xsl:value-of select="$label" />
			</label>

			<input type="password" id="{$id}" name="{$name}" class="input-password text-input">
				<xsl:if test="$value">
					<xsl:attribute name="value">
					<xsl:value-of select="$value" />
				</xsl:attribute>
				</xsl:if>
			</input>

			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
		</p>
	</xsl:template>

	<xsl:template name="input-text">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:choose>
				<xsl:when test="$mod-lang/labels/*[local-name() = $id]">
					<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$name" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />
		<xsl:param name="min" />
		<xsl:param name="max" />
		<xsl:param name="error-type" />
		<xsl:param name="error-code" />
		<xsl:param name="error-message">
			<xsl:choose>
				<xsl:when test="$error-type = 'InvalidSyntaxException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-syntax/*[local-name() = $id]">
							<xsl:value-of select="$mod-lang/error-syntax/*[local-name() = $id]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid syntax</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$error-type = 'ValidationException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]">
							<xsl:value-of select="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:param>
		
		<xsl:param name="class">
			<xsl:text>formField</xsl:text>
			<xsl:choose>
				<xsl:when test="$error-type = 'InvalidSyntaxException'">
					<xsl:text> errorneous invalid-syntax</xsl:text>
				</xsl:when>
				<xsl:when test="$error-type = 'ValidationException'">
					<xsl:text> errorneous invalid</xsl:text>
				</xsl:when>
			</xsl:choose>
		</xsl:param>
		
		<p class="{$class}">
			<label for="{$id}" id="label.{$id}">
				<xsl:value-of select="$label" />
			</label>

			<input type="text" id="{$id}" name="{$name}" class="input-text text-input">
				<xsl:if test="$value">
					<xsl:attribute name="value">
						<xsl:value-of select="$value" />
					</xsl:attribute>
				</xsl:if>
				<xsl:if test="$max">
					<xsl:attribute name="maxlength">
						<xsl:value-of select="$max" />
					</xsl:attribute>
				</xsl:if>
				<xsl:if test="$max">
					<xsl:attribute name="minlength">
						<xsl:value-of select="$min" />
					</xsl:attribute>
				</xsl:if>
			</input>
			
			<xsl:if test="$error-message">
				<span class="error">
					<xsl:value-of select="$error-message" />
				</span>
			</xsl:if>
			
			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
			
		</p>
	</xsl:template>

	<xsl:template name="input-email">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:choose>
				<xsl:when test="$mod-lang/labels/*[local-name() = $id]">
					<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$name" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />

		<xsl:param name="error-type" />
		<xsl:param name="error-code" />
		<xsl:param name="error-message">
			<xsl:choose>
				<xsl:when test="$error-type = 'InvalidSyntaxException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-syntax/*[local-name() = $id]">
							<xsl:value-of select="$mod-lang/error-syntax/*[local-name() = $id]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid syntax</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$error-type = 'ValidationException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]">
							<xsl:value-of select="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:param>
		
		<p class="formField">
			<label for="{$id}" id="label.{$id}">
				<xsl:value-of select="$label" />
			</label>

			<input type="email" id="{$id}" name="{$name}" class="input-email text-input">
				<xsl:if test="$value">
					<xsl:attribute name="value">
						<xsl:value-of select="$value" />
					</xsl:attribute>
				</xsl:if>
			</input>

			<xsl:if test="$error-message">
				<span class="error">
					<xsl:value-of select="$error-message" />
				</span>
			</xsl:if>
			
			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
		</p>
	</xsl:template>

	<xsl:template name="input-textarea">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:choose>
				<xsl:when test="$mod-lang/labels/*[local-name() = $id]">
					<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$name" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />
		<xsl:param name="min" />
		<xsl:param name="max" />

		<xsl:param name="error-type" />
		<xsl:param name="error-code" />
		<xsl:param name="error-message">
			<xsl:choose>
				<xsl:when test="$error-type = 'InvalidSyntaxException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-syntax/*[local-name() = $id]">
							<xsl:value-of select="$mod-lang/error-syntax/*[local-name() = $id]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid syntax</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$error-type = 'ValidationException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]">
							<xsl:value-of select="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:param>
		
		<p class="formField">
			<label for="{$id}" id="label.{$id}">
				<xsl:value-of select="$label" />
			</label>

			<textarea id="{$id}" name="{$name}" class="input-textarea text-input">
				<xsl:if test="$value">
					<xsl:value-of select="$value" />
				</xsl:if>
				
				<xsl:if test="$max">
					<xsl:attribute name="maxlength">
						<xsl:value-of select="$max" />
					</xsl:attribute>
				</xsl:if>
				<xsl:if test="$max">
					<xsl:attribute name="minlength">
						<xsl:value-of select="$min" />
					</xsl:attribute>
				</xsl:if>
			</textarea>

			<xsl:if test="$error-message">
				<span class="error">
					<xsl:value-of select="$error-message" />
				</span>
			</xsl:if>
			
			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
		</p>
	</xsl:template>
	
	<xsl:template name="input-numeric">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:choose>
				<xsl:when test="$mod-lang/labels/*[local-name() = $id]">
					<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$name" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />
		<xsl:param name="min" />
		<xsl:param name="max" />
		<xsl:param name="step" />

		<xsl:param name="error-type" />
		<xsl:param name="error-code" />
		<xsl:param name="error-message">
			<xsl:choose>
				<xsl:when test="$error-type = 'InvalidSyntaxException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-syntax/*[local-name() = $id]">
							<xsl:value-of select="$mod-lang/error-syntax/*[local-name() = $id]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid syntax</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$error-type = 'ValidationException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]">
							<xsl:value-of select="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:param>
		
		<p class="formField">
			<label for="{$id}" id="label.{$id}">
				<xsl:value-of select="$label" />
			</label>

			<input type="number" id="{$id}" name="{$name}" class="input-numeric text-input">
				<xsl:if test="$value">
					<xsl:attribute name="value">
						<xsl:value-of select="$value" />
					</xsl:attribute>
				</xsl:if>
				<xsl:if test="$min">
					<xsl:attribute name="min">
						<xsl:value-of select="$min" />
					</xsl:attribute>
				</xsl:if>
				<xsl:if test="$max">
					<xsl:attribute name="max">
						<xsl:value-of select="$max" />
					</xsl:attribute>
				</xsl:if>
				<xsl:if test="$step">
					<xsl:attribute name="step">
						<xsl:value-of select="$step" />
					</xsl:attribute>
				</xsl:if>
			</input>

			<xsl:if test="$error-message">
				<span class="error">
					<xsl:value-of select="$error-message" />
				</span>
			</xsl:if>
			
			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
		</p>
	</xsl:template>
	
	<xsl:template name="input-hidden">
	
		<xsl:param name="id" />
		<xsl:param name="name" />
		
	</xsl:template>
	
	<xsl:template name="input-date">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:choose>
				<xsl:when test="$mod-lang/labels/*[local-name() = $id]">
					<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$name" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<xsl:param name="value" />

		<xsl:param name="error-type" />
		<xsl:param name="error-code" />
		<xsl:param name="error-message">
			<xsl:choose>
				<xsl:when test="$error-type = 'InvalidSyntaxException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-syntax/*[local-name() = $id]">
							<xsl:value-of select="$mod-lang/error-syntax/*[local-name() = $id]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid syntax</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$error-type = 'ValidationException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]">
							<xsl:value-of select="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:param>
		
		<p class="formField">
			<label for="{$id}">
				<xsl:value-of select="$label" />
			</label>

			<input type="text" id="{$id}" name="{$name}" class="js-datepicker input-date text-input">
				<xsl:if test="$value">
					<xsl:attribute name="value">
					<xsl:value-of select="$value" />
				</xsl:attribute>
				</xsl:if>
			</input>

			<xsl:if test="$error-message">
				<span class="error">
					<xsl:value-of select="$error-message" />
				</span>
			</xsl:if>
			
			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
		</p>
	</xsl:template>
	
	<xsl:template name="input-alternatives">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:choose>
				<xsl:when test="$mod-lang/labels/*[local-name() = $id]">
					<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$name" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:param>
		<xsl:param name="info">
			<xsl:value-of select="$mod-lang/info/*[local-name() = $id]" />
		</xsl:param>
		<!-- <xsl:param name="selected" />  -->
		<xsl:param name="options" />
		
		<xsl:param name="error-type" />
		<xsl:param name="error-code" />
		<xsl:param name="error-message">
			<xsl:choose>
				<xsl:when test="$error-type = 'InvalidSyntaxException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-syntax/*[local-name() = $id]">
							<xsl:value-of select="$mod-lang/error-syntax/*[local-name() = $id]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid syntax</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="$error-type = 'ValidationException'">
					<xsl:choose>
						<xsl:when test="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]">
							<xsl:value-of select="$mod-lang/error-invalid/*[local-name() = $id]/*[local-name() = $error-code]" />
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>invalid</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
			</xsl:choose>
		</xsl:param>
		
		<p class="formField">
			<label for="{$id}" id="label.{$id}"><xsl:value-of select="$label" /></label>
			<select id="{$id}" name="{$name}">
				<xsl:copy-of select="$options" />
			</select>
			
			<xsl:if test="$error-message">
				<span class="error">
					<xsl:value-of select="$error-message" />
				</span>
			</xsl:if>
			
			<xsl:if test="$info">
				<span class="info">
					<xsl:value-of select="$info" />
				</span>
			</xsl:if>
		</p>
		
	</xsl:template>

	<xsl:template name="input-submit">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:choose>
				<xsl:when test="$mod-lang/labels/*[local-name() = $id]">
					<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$name" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:param>
		<xsl:param name="disabled" />

		<p class="formControls">
			<input type="submit" id="{$id}" name="{$name}" value="{$label}" class="button-input">
			<xsl:if test="$disabled">
				<xsl:attribute name="disabled">
					<xsl:value-of select="'disabled'"/>
				</xsl:attribute>
			</xsl:if>
			</input>
		</p>
	</xsl:template>

	<xsl:template name="input-submit-with-cancel">
		<xsl:param name="id" />
		<xsl:param name="name">
			<xsl:value-of select="$id" />
		</xsl:param>
		<xsl:param name="label">
			<xsl:choose>
				<xsl:when test="$mod-lang/labels/*[local-name() = $id]">
					<xsl:value-of select="$mod-lang/labels/*[local-name() = $id]" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$name" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:param>
		<xsl:param name="disabled" />
		<xsl:param name="cancel-action" />

		<p class="formControls">
			<input type="submit" id="{$id}" name="{$name}" value="{$label}" class="button-input">
			<xsl:if test="$disabled">
				<xsl:attribute name="disabled">
					<xsl:value-of select="'disabled'"/>
				</xsl:attribute>
			</xsl:if>
			</input>
			<input type="submit" id="{$id}" name="~{$name}" value="{$label}" class="button-input">
			<xsl:if test="$disabled">
				<xsl:attribute name="disabled">
					<xsl:value-of select="'disabled'"/>
				</xsl:attribute>
			</xsl:if>
			</input>
		</p>
	</xsl:template>

</xsl:transform>
